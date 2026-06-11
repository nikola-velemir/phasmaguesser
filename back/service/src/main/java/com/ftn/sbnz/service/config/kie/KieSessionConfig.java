package com.ftn.sbnz.service.config.kie;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class KieSessionConfig {

    @Bean(name = "templateGhostSession")
    @Scope("prototype")
    public KieSession templateGhostSession(
            @Qualifier("ghostKieContainer") KieContainer container) {
        KieServices ks = KieServices.Factory.get();
        KieSessionConfiguration sessionConf = ks.newKieSessionConfiguration();
        sessionConf.setOption(ClockTypeOption.get("realtime"));
        return container.newKieSession(sessionConf);
    }

    @Bean(name = "cepGhostSession")
    @Scope("prototype")
    public KieSession cepGhostSession(
            @Qualifier("ghostKieContainer") KieContainer container) {
        KieServices ks = KieServices.Factory.get();
        KieSessionConfiguration sessionConf = ks.newKieSessionConfiguration();
        sessionConf.setOption(ClockTypeOption.get("realtime"));
        return container.newKieSession(sessionConf);
    }

}
