package com.ftn.sbnz.service.ghosts.service;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.events.GhostEvent;
import com.ftn.sbnz.model.events.TraitObservedEvent;

@Service
public class EventService {
    private final KieSession cepSession;

    public EventService(@Qualifier("cepGhostSession") KieSession cepSession) {
        this.cepSession = cepSession;
    }
    public synchronized void insertEvent(GhostEvent event) {
        cepSession.insert(event);
    }
    public List<TraitObservedEvent> getDerivedTraits() {
        List<TraitObservedEvent> traits = new ArrayList<>();
        cepSession.getObjects(obj -> obj instanceof TraitObservedEvent)
                  .forEach(obj -> traits.add((TraitObservedEvent) obj));
        return traits;
    }
}
