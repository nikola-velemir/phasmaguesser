package com.ftn.sbnz.model.events;

public class CandleExtinguishedEvent extends GhostEvent {

    public CandleExtinguishedEvent() {
        super();
    }

    public CandleExtinguishedEvent(long timestamp) {
        super(timestamp);
    }

}
