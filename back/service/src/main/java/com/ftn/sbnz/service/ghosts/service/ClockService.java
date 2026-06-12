package com.ftn.sbnz.service.ghosts.service;

import java.util.concurrent.TimeUnit;

import org.kie.api.runtime.KieSession;
import org.kie.api.time.SessionPseudoClock;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ClockService {
    private final SessionPseudoClock clock;

    public ClockService(@Qualifier("cepGhostSession") KieSession kSession) {
        this.clock = kSession.getSessionClock();
    }

    public synchronized void advanceTime(int amount) {
        clock.advanceTime(amount, TimeUnit.SECONDS);
    }
}
