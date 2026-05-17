package com.ftn.sbnz.service.ghosts.service;

import com.ftn.sbnz.model.evidence.CurrentEvidence;
import com.ftn.sbnz.model.ghosts.Ghost;
import com.ftn.sbnz.model.ghosts.GhostCandidate;
import com.ftn.sbnz.model.observations.HuntObservation;
import com.ftn.sbnz.service.config.GhostProvider;
import java.util.ArrayList;
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
            // 1. Povuci novu, čistu sesiju iz konfiguracije
            // Napomena: Proveri da li ti se bean zove "ghostSession" ili
            // "templateGhostSession"
            ksession = (KieSession) context.getBean("templateGhostSession");

            // 2. Ubaci statičko znanje (listu 24 duha) u runtime
            List<Ghost> staticGhosts = GhostProvider.getStaticGhostsKnowledgeBase();
            for (Ghost g : staticGhosts) {
                ksession.insert(g);
                ksession.insert(new GhostCandidate(g.getName()));
            }

            // 3. Mapiraj DTO u CurrentEvidence objekat koji Drools očekuje
            // Koristimo Evidence.valueOf() da pretvorimo String iz JSON-a u tvoj Enum

            // Ubacujemo trenutni dokaz u radnu memoriju
            ksession.insert(currentEvidence);
            if (huntObservation != null) {
                System.out.println("SBNZ: Ubacujem HuntObservation u sesiju...");
                ksession.insert(huntObservation);
            }
            // 4. Pokreni rezoner
            System.out.println("SBNZ: Pokrećem rezoner za identifikaciju...");
            int firedRules = ksession.fireAllRules();
            System.out.println("SBNZ: Broj okinutih pravila: " + firedRules);

            // 5. Vrati rezultat
            // Pošto tvoje pravilo za sada radi samo System.out.println, ovde možemo
            // privremeno
            // vratiti potvrdu, a kasnije možeš dodati polje 'identifiedGhost' u
            // CurrentEvidence pa ga pročitati odatle.
            List<GhostCandidate> candidates = getCandidates(ksession);

            // 5. Filtriranje i sortiranje (npr. izbaci eliminisane i sortiraj po skoru
            // opadajuće)
            List<GhostCandidate> sortedResult = getSortedResult(candidates);

            return sortedResult;
        } catch (Exception e) {
            throw e;
        } finally {
            if (ksession != null) {
                ksession.dispose();
            }
        }

    }

    private List<GhostCandidate> getSortedResult(List<GhostCandidate> candidates) {
        return candidates.stream()
                .filter(c -> !c.isEliminated()) // vraća samo one koji nisu ispali
                .sorted((c1, c2) -> Integer.compare(c2.getScore(), c1.getScore())) // najveći skor prvi
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    if (list.isEmpty()) {
                        // Ako su svi eliminisani, vrati sve čisto da fronted vidi šta se desilo
                        return candidates;
                    }
                    return list;
                }));
    }

    private List<GhostCandidate> getCandidates(KieSession ksession) {
        List<GhostCandidate> candidates = new ArrayList<>();
        for (Object obj : ksession.getObjects(new ObjectFilter() {
            @Override
            public boolean accept(Object object) {
                return object instanceof GhostCandidate;
            }
        })) {
            candidates.add((GhostCandidate) obj);
        }
        return candidates;
    }
}
