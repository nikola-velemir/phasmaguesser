package com.ftn.sbnz.model.ghosts;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.ftn.sbnz.model.evidence.Evidence;

public final class Ghost implements Serializable {
    private String name;
    private Set<Evidence> evidences;
    private Set<String> traits;

    public Ghost() {

    }

    public Ghost(String name, Set<Evidence> evidences, Set<String> traits) {
        this.name = name;
        this.evidences = evidences != null ? evidences : EnumSet.noneOf(Evidence.class);
        this.traits = traits != null ? traits : new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public boolean hasTrait(String t) {
        return traits.contains(t);
    }

    public Set<Evidence> getEvidences(){
        return this.evidences;
    }
    public Set<String> getTraits(){
        return this.traits;
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
