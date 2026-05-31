package com.ftn.sbnz.model.ghosts;

public final class GhostEliminated {
    private String ghostName;
    private String reason;

    public GhostEliminated(String ghostName, String reason) {
        this.ghostName = ghostName;
        this.reason = reason;
    }

    public GhostEliminated() {
        
    }

    public String getGhostName() {
        return ghostName;
    }

    public void setGhostName(String ghostName) {
        this.ghostName = ghostName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
}
