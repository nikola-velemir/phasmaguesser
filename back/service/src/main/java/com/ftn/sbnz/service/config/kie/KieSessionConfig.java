package com.ftn.sbnz.service.config.kie;

import java.io.IOException;

import org.drools.core.io.impl.ClassPathResource;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class KieSessionConfig {

    @Bean(name = "templateGhostSession")
    @Scope("prototype")
    public KieSession templateGhostSession() {
        try {
            System.setProperty("drools.dialect.java.compiler.lnglevel", "11");

            KieServices ks = KieServices.Factory.get();
            KieFileSystem kfs = ks.newKieFileSystem();

            kfs.write(new ClassPathResource("rules/cep.drl"));
            String ghostTemplateDrl = extractGhostTemplate();

            kfs.write("src/main/resources/rules/ghosts_compiled.drl", ghostTemplateDrl);

            String traitTemplateDrl = extractTraitsTemplate();
            kfs.write("src/main/resources/rules/traits_compiled.drl", traitTemplateDrl);

            // kfs.write(new ClassPathResource("rules/level1-traits.drl"));
            kfs.write(new ClassPathResource("rules/scoring.drl"));
            // kfs.write(new ClassPathResource("rules/level3-profiles.drl"));

            String profileTemplateDrl = extractProfileTemplate();
            kfs.write("src/main/resources/rules/profiles_compiled.drl", profileTemplateDrl);

            kfs.write(new ClassPathResource("rules/aggregation_rule.drl"));

            KieBuilder kieBuilder = ks.newKieBuilder(kfs);
            kieBuilder.buildAll();

            if (kieBuilder.getResults().hasMessages(org.kie.api.builder.Message.Level.ERROR)) {
                throw new RuntimeException("Greška u sintaksi pravila: " + kieBuilder.getResults().toString());
            }

            KieModule kieModule = kieBuilder.getKieModule();
                        KieSessionConfiguration sessionConf = ks.newKieSessionConfiguration();
            sessionConf.setOption(ClockTypeOption.get("realtime"));

            return ks.newKieContainer(kieModule.getReleaseId()).newKieSession(sessionConf);

        } catch (Exception e) {
            throw new RuntimeException("Greška prilikom inicijalizacije Drools sesije: ", e);
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
