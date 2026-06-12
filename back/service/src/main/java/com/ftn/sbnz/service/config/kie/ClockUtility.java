package com.ftn.sbnz.service.config.kie;

import java.util.concurrent.TimeUnit;

import org.drools.core.time.SessionPseudoClock;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClockUtility {
    private final SessionPseudoClock clock;

    public ClockUtility(@Qualifier("cepGhostSession") KieSession kSession) {

        this.clock = kSession.getSessionClock();
        long wallTime = System.currentTimeMillis();
        clock.advanceTime(wallTime, TimeUnit.MILLISECONDS);
        System.out.println("ClockUtility initialized, pseudo clock set to: " + wallTime);
        System.out.println("ClockUtility session: " + kSession.hashCode());
        System.out.println("Clock advanced to: " + clock.getCurrentTime());

    }

    @Scheduled(fixedRate = 500)
    public synchronized void tick() {
        clock.advanceTime(500, TimeUnit.MILLISECONDS);
    }
}
