package com.ftn.sbnz.model.evidence;

public enum Evidence {
    EMF_LEVEL_5("EMF Level 5", "EMF5"),
    ULTRAVIOLET("Ultraviolet", "UV"),
    GHOST_WRITING("Ghost Writing", "GW"),
    GHOST_ORB("Ghost Orb", "GO"),
    FREEZING_TEMPERATURES("Freezing Temperatures", "FT"),
    SPIRIT_BOX("Spirit Box", "SB"),
    DOTS_PROJECTOR("D.O.T.S. Projector", "DOTS");

    private final String displayName;
    private final String shortCode;

    Evidence(String displayName, String shortCode) {
        this.displayName = displayName;
        this.shortCode = shortCode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getShortCode() {
        return shortCode;
    }

    public static Evidence fromString(String text) {
        for (Evidence e : Evidence.values()) {
            if (e.shortCode.equalsIgnoreCase(text) || e.name().equalsIgnoreCase(text)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Unknown evidence: " + text);
    }
}