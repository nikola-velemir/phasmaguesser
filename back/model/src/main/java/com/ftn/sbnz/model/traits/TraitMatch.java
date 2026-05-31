package com.ftn.sbnz.model.traits;

public final class TraitMatch {
    private String ghostName;
    private String traitName;

    public TraitMatch(String ghostName, String traitName) {
        this.ghostName = ghostName;
        this.traitName = traitName;
    }

    public TraitMatch() {
    }

    public String getGhostName() {
        return ghostName;
    }

    public void setGhostName(String ghostName) {
        this.ghostName = ghostName;
    }

    public String getTraitName() {
        return traitName;
    }

    public void setTraitName(String traitName) {
        this.traitName = traitName;
    }
    
}
