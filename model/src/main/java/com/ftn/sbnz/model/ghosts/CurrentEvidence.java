package com.ftn.sbnz.model.ghosts;

import java.io.Serializable;

public final class CurrentEvidence implements Serializable {
    private Evidence evidence1;
    private Evidence evidence2;
    private Evidence evidence3;

    public CurrentEvidence(Evidence e1, Evidence e2, Evidence e3) {
        this.evidence1 = e1;
        this.evidence2 = e2;
        this.evidence3 = e3;
    }


    public Evidence getEvidence1() {
        return evidence1;
    }

    public Evidence getEvidence2() {
        return evidence2;
    }

    public Evidence getEvidence3() {
        return evidence3;
    }

}