package com.ftn.sbnz.service.config.kie;

import java.io.IOException;

import org.drools.core.io.impl.ClassPathResource;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class KieContainerConfig {
    @Bean(name = "ghostKieContainer")
    @Scope("singleton")
    public KieContainer ghostKieContainer() {
        try {
            System.setProperty("drools.dialect.java.compiler.lnglevel", "11");

            KieServices ks = KieServices.Factory.get();
            KieFileSystem kfs = ks.newKieFileSystem();

            kfs.write(new ClassPathResource("rules/cep.drl"));
            kfs.write("src/main/resources/rules/ghosts_compiled.drl", extractGhostTemplate());
            kfs.write("src/main/resources/rules/traits_compiled.drl", extractTraitsTemplate());
            kfs.write(new ClassPathResource("rules/scoring.drl"));
            kfs.write("src/main/resources/rules/profiles_compiled.drl", extractProfileTemplate());
            kfs.write(new ClassPathResource("rules/aggregation_rule.drl"));

            KieBuilder kieBuilder = ks.newKieBuilder(kfs);
            kieBuilder.buildAll();

            if (kieBuilder.getResults().hasMessages(org.kie.api.builder.Message.Level.ERROR)) {
                throw new RuntimeException("Rule error: " + kieBuilder.getResults());
            }

            KieModule kieModule = kieBuilder.getKieModule();
            return ks.newKieContainer(kieModule.getReleaseId());

        } catch (Exception e) {
            throw new RuntimeException("Failed to build KieContainer", e);
        }
    }
    private String extractGhostTemplate() throws IOException {
        return TemplateExtractor.extractTemplateAsDrl("rules/ghosts.drt", "rules/ghosts.xls");
    }

    private String extractProfileTemplate() throws IOException {
        return TemplateExtractor.extractTemplateAsDrl("rules/profiles.drt", "rules/profiles.xls");
    }

    private String extractTraitsTemplate() throws IOException {
        return TemplateExtractor.extractTemplateAsDrl("rules/traits.drt", "rules/traits.xls");
    }
}
