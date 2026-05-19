package com.ftn.sbnz.service.config;

import java.io.InputStream;
import org.drools.core.io.impl.ClassPathResource;
import org.drools.decisiontable.ExternalSpreadsheetCompiler;
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
            System.setProperty("drools.dialect.java.compiler.lnglevel", "11");

            // 1. Kompajliranje šablona
            InputStream template = new ClassPathResource("rules/ghosts.drt").getInputStream();
            InputStream data = new ClassPathResource("rules/ghost-identities.xls").getInputStream();
            ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();
            String templateDrl = converter.compile(data, template, 2, 1);

            KieServices ks = KieServices.Factory.get();
            KieFileSystem kfs = ks.newKieFileSystem();

            // Upisujemo kompajlirani šablon (Nivo 0 - Filter)
            kfs.write("src/main/resources/rules/ghosts_compiled.drl", templateDrl);

            // 2. DODAVANJE OSTALIH NIVOA U ISTI FAJLSISTEM
            kfs.write(new ClassPathResource("rules/level1-traits.drl"));
            kfs.write(new ClassPathResource("rules/level2-scoring.drl"));
            kfs.write(new ClassPathResource("rules/level3-profiles.drl"));

            KieBuilder kieBuilder = ks.newKieBuilder(kfs);
            kieBuilder.buildAll();

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
