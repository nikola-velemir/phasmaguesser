package com.ftn.sbnz.service.ghosts.service;

import com.ftn.sbnz.model.evidence.CurrentEvidence;
import com.ftn.sbnz.model.ghosts.Ghost;
import com.ftn.sbnz.model.ghosts.GhostCandidate;
import com.ftn.sbnz.model.observations.HuntObservation;
import com.ftn.sbnz.service.config.GhostProvider;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.drools.core.ObjectFilter;
import org.kie.api.runtime.KieSession;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndentificatorService {

    private final ApplicationContext context;

    public List<GhostCandidate> indifyGhost(CurrentEvidence currentEvidence, HuntObservation huntObservation) {

        KieSession ksession = null;

        try {
            ksession = (KieSession) context.getBean("templateGhostSession");

            // Ubaci Ghost + GhostCandidate — kandidate ubacuje SERVIS, ne Drools pravila
            List<Ghost> staticGhosts = GhostProvider.getStaticGhostsKnowledgeBase();
            for (Ghost g : staticGhosts) {
                ksession.insert(g);
                ksession.insert(new GhostCandidate(g.getName()));
            }

            ksession.insert(currentEvidence);

            if (huntObservation != null) {
                System.out.println("SBNZ: Ubacujem HuntObservation u sesiju...");
                ksession.insert(huntObservation);
            }

            System.out.println("SBNZ: Pokrećem rezoner za identifikaciju...");
            int firedRules = ksession.fireAllRules();
            System.out.println("SBNZ: Broj okinutih pravila: " + firedRules);

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
                .filter(c -> !c.isEliminated())
                .sorted(byScoreDesc)
                .collect(Collectors.toList());

        if (!active.isEmpty()) {
            return active;
        }

        // Svi eliminisani — vrati ih sortirane po skoru da frontend vidi rang
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
