package com.ftn.sbnz.model.evidence;

public final class EvidenceMatch {
    private String ghostName;
    private Evidence evidence;

    public EvidenceMatch(String ghostName, Evidence evidence) {
        this.ghostName = ghostName;
        this.evidence = evidence;
    }

    public EvidenceMatch() {
    }

    public String getGhostName() {
        return ghostName;
    }

    public void setGhostName(String ghostName) {
        this.ghostName = ghostName;
    }

    public Evidence getEvidence() {
        return evidence;
    }

    public void setEvidence(Evidence evidence) {
        this.evidence = evidence;
    }
}
