package com.ftn.sbnz.model.events;

public abstract class GhostEvent {
    
    private long timestamp;

    public GhostEvent(long timestamp) {
        this.timestamp = timestamp;
    }

    public GhostEvent() {
        timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return timestamp;
    }
    
}
