package com.ftn.sbnz.model.ghosts;

import java.io.Serializable;
import java.util.Objects;

import com.ftn.sbnz.model.evidence.Evidence;

public final class Ghost implements Serializable {
    private String name;
    private Evidence evidence1;
    private Evidence evidence2;
    private Evidence evidence3;
    private String trait;

    public Ghost() {

    }

    public Ghost(String name, Evidence e1, Evidence e2, Evidence e3, String trait) {
        this.name = name;
        this.evidence1 = e1;
        this.evidence2 = e2;
        this.evidence3 = e3;
        this.trait = trait;
    }

    public String getName() {
        return name;
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

    public String getTrait() {
        return trait;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Ghost ghost = (Ghost) o;
        return Objects.equals(name, ghost.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Ghost{name='" + name + "'}";
    }

}
