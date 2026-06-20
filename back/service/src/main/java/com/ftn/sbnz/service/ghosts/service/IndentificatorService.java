package com.ftn.sbnz.service.ghosts.service;

import com.ftn.sbnz.model.evidence.CurrentEvidence;
import com.ftn.sbnz.model.ghosts.Ghost;
import com.ftn.sbnz.model.ghosts.GhostCandidate;
import com.ftn.sbnz.model.observations.HuntObservation;
import com.ftn.sbnz.model.traits.DerivedTrait;
import com.ftn.sbnz.service.config.GhostProvider;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndentificatorService {

    private final EventService eventService;
    private final ApplicationContext context;

    public List<GhostCandidate> indifyGhost(CurrentEvidence currentEvidence, HuntObservation huntObservation) {

        KieSession ksession = null;

        try {
            ksession = (KieSession) context.getBean("templateGhostSession");

            List<Ghost> staticGhosts = GhostProvider.getStaticGhostsKnowledgeBase();
            for (Ghost g : staticGhosts) {
                ksession.insert(g);
                ksession.insert(new GhostCandidate(g.getName()));
            }

            ksession.insert(currentEvidence);

            if (huntObservation != null) {
                System.out.println("SYS: Inserting HuntObservation...");
                ksession.insert(huntObservation);
            }

            List<DerivedTrait> derivedTraits = eventService.getDerivedTraits();
            System.out.println("Inserting " + derivedTraits.size() + " CEP derived traits...");
            derivedTraits.forEach(ksession::insert);

            System.out.println("Beggining reasoning...");
            int firedRules = ksession.fireAllRules();
            System.out.println("Number of rules fired: " + firedRules);

            List<GhostCandidate> candidates = getCandidates(ksession);
            return getSortedResult(candidates);

        } finally {
            if (ksession != null) {
                ksession.dispose();
            }
        }
    }

    private List<GhostCandidate> getSortedResult(List<GhostCandidate> candidates) {
        Comparator<GhostCandidate> byScoreDesc = Comparator.comparingInt(GhostCandidate::getScore).reversed();
        List<GhostCandidate> active = candidates.stream()
                .filter(c -> c.getScore() > 0)
                .sorted(byScoreDesc)
                .collect(Collectors.toList());
        // boolean isAllGhosts = GhostProvider.getGhostNumber() == active.size();
        // if(isAllGhosts){
        // active = candidates
        // }
        if (!active.isEmpty()) {
            return active;
        }

        return candidates.stream()
                .sorted(byScoreDesc)
                .collect(Collectors.toList());
    }

    private List<GhostCandidate> getCandidates(KieSession ksession) {
        List<GhostCandidate> candidates = new ArrayList<>();
        ksession.getObjects(obj -> obj instanceof GhostCandidate)
                .forEach(obj -> candidates.add((GhostCandidate) obj));
        return candidates;
    }
}
