package com.ftn.sbnz.service.config;

import java.io.InputStream;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class KieConfig {
    @Bean
    public KieContainer kieContainer() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("com.ftn.sbnz", "kjar", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        kScanner.start(1000);
        return kContainer;
    }

    @Bean(name = "templateGhostSession")
    @Scope("prototype")
    public KieSession templateGhostSession() {
        try {
            // 1. Eksplicitno forsiramo Drools da koristi Java 11 u runtime-u za MVEL/JDT kompajler
            System.setProperty("drools.dialect.java.compiler.lnglevel", "11");
            
            InputStream template = new ClassPathResource("rules/ghosts.drt").getInputStream();
            InputStream data = new ClassPathResource("rules/ghost-identities.xls").getInputStream();
            
            ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();
            String drl = converter.compile(data, template,2, 1);
            
            // 2. Umesto klasičnog KieHelper-a, napravićemo sesiju preko KieFileSystem-a
            // jer on bolje izoluje i postavlja properties u okviru same Spring aplikacije
            KieServices ks = KieServices.Factory.get();
            KieFileSystem kfs = ks.newKieFileSystem();
            
            // Upisujemo generisani drl direktno u virtuelni resurs
            kfs.write("src/main/resources/rules.drl", drl);
            
            KieBuilder kieBuilder = ks.newKieBuilder(kfs);
            kieBuilder.buildAll(); // Ovde se vrši runtime kompajliranje
            
            if (kieBuilder.getResults().hasMessages(org.kie.api.builder.Message.Level.ERROR)) {
                throw new RuntimeException("Greška u sintaksi pravila: " + kieBuilder.getResults().toString());
            }
            
            KieModule kieModule = kieBuilder.getKieModule();
            return ks.newKieContainer(kieModule.getReleaseId()).newKieSession();
            
        } catch (Exception e) {
            throw new RuntimeException("Greška prilikom inicijalizacije Drools sesije: ", e);
        }
    }
}
