package com.ftn.sbnz.model.evidence;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.Set;

public final class CurrentEvidence implements Serializable {
    private Set<Evidence> confirmedEvidence;

    public CurrentEvidence() {
        this.confirmedEvidence = EnumSet.noneOf(Evidence.class);
    }

    public CurrentEvidence(Evidence... evidences) {
        this.confirmedEvidence = EnumSet.noneOf(Evidence.class);
        if (evidences != null) {
            for (Evidence e : evidences) {
                if (e != null) {
                    this.confirmedEvidence.add(e);
                }
            }
        }
    }
    public CurrentEvidence(Set<Evidence> evidences) {
        this.confirmedEvidence = EnumSet.noneOf(Evidence.class);
        if (evidences != null) {
            for (Evidence e : evidences) {
                if (e != null) {
                    this.confirmedEvidence.add(e);
                }
            }
        }
    }
    public boolean hasEvidence(Evidence e) {
        return confirmedEvidence.contains(e);
    }

    public Set<Evidence> getConfirmedEvidence() {
        return confirmedEvidence;
    }

}