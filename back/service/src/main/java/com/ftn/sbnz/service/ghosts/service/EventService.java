
package com.ftn.sbnz.service.ghosts.service;
 
import java.util.ArrayList;
import java.util.List;
 
import org.drools.core.time.SessionPseudoClock;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
 
import com.ftn.sbnz.model.events.GhostEvent;
import com.ftn.sbnz.model.ghosts.Ghost;
import com.ftn.sbnz.model.traits.DerivedTrait;
import com.ftn.sbnz.service.config.GhostProvider;
import com.ftn.sbnz.service.config.kie.ClockUtility;
 
@Service
public class EventService {
    private final KieSession cepSession;
    private final SessionPseudoClock clock;
    private final ClockUtility clockUtility;
 
    public EventService(
            @Qualifier("cepGhostSession") KieSession cepSession,
            ClockUtility clockUtility) {
        this.cepSession = cepSession;
        this.clock = cepSession.getSessionClock();
        this.clockUtility = clockUtility;
 
        // Ghost facts must be present in the CEP session so that rules like
        // CEP_Mimic_Behaviour_Shift can verify trait ownership via
        // Ghost(hasTrait(...)) constraints.
        List<Ghost> staticGhosts = GhostProvider.getStaticGhostsKnowledgeBase();
        for (Ghost g : staticGhosts) {
            cepSession.insert(g);
        }
 
        System.out.println("EventService session: " + cepSession.hashCode());
        System.out.println("EventService clock on init: " + clock.getCurrentTime());
        System.out.println("EventService: inserted " + staticGhosts.size() + " Ghost facts into CEP session");
    }
 
    public void insertEvent(GhostEvent event) {
        synchronized (clockUtility) {
            long time = clock.getCurrentTime();
            System.out.println("[insertEvent] clock=" + time + " event=" + event.getClass().getSimpleName());
            event.setTimestamp(time);
            cepSession.insert(event);
            int fired = cepSession.fireAllRules();
            System.out.println("[insertEvent] rules fired: " + fired);
        }
    }
 
    public void clearSession() {
        synchronized (clockUtility) {
           
            cepSession.getFactHandles(obj -> !(obj instanceof Ghost))
                    .forEach(cepSession::delete);
        }
    }
 


    public List<DerivedTrait> getDerivedTraits() {
        synchronized (clockUtility) {
            cepSession.fireAllRules();
            List<DerivedTrait> traits = new ArrayList<>();
            cepSession.getObjects(obj -> obj instanceof DerivedTrait)
                    .forEach(obj -> traits.add((DerivedTrait) obj));
            return traits;
        }
    }
}