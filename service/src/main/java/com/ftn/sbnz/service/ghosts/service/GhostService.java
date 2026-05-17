package com.ftn.sbnz.service.ghosts.service;

import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.evidence.CurrentEvidence;
import com.ftn.sbnz.model.evidence.Evidence;
import com.ftn.sbnz.model.ghosts.Ghost;
import com.ftn.sbnz.service.config.GhostProvider;
import com.ftn.sbnz.service.ghosts.dto.GhostIndetificationReqestDTO;

@Service
public class GhostService {
    @Autowired
    private ApplicationContext context;

    public ResponseEntity<String> indifyGhost(GhostIndetificationReqestDTO reqest) {
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
            }

            // 3. Mapiraj DTO u CurrentEvidence objekat koji Drools očekuje
            // Koristimo Evidence.valueOf() da pretvorimo String iz JSON-a u tvoj Enum
            CurrentEvidence currentEvidence = new CurrentEvidence(
                    Evidence.valueOf(reqest.getEvidence1().toUpperCase()),
                    Evidence.valueOf(reqest.getEvidence2().toUpperCase()));
            // Ubacujemo trenutni dokaz u radnu memoriju
            ksession.insert(currentEvidence);

            // 4. Pokreni rezoner
            System.out.println("SBNZ: Pokrećem rezoner za identifikaciju...");
            int firedRules = ksession.fireAllRules();
            System.out.println("SBNZ: Broj okinutih pravila: " + firedRules);

            // 5. Vrati rezultat
            // Pošto tvoje pravilo za sada radi samo System.out.println, ovde možemo
            // privremeno
            // vratiti potvrdu, a kasnije možeš dodati polje 'identifiedGhost' u
            // CurrentEvidence pa ga pročitati odatle.
            return ResponseEntity.ok(
                    "Rezoner je završio. Proveri konzolu za 'Identifikovan duh' ispise! Okinuto pravila: "
                            + firedRules);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Greška: Prosleđeni dokaz ne postoji u Evidence enumu! " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Došlo je do greške na serveru: " + e.getMessage());
        } finally {
            // 6. Obavezno oslobađanje resursa
            if (ksession != null) {
                ksession.dispose();
            }
        }
    }
}
