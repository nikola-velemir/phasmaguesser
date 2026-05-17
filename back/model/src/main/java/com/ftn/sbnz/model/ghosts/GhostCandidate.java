package com.ftn.sbnz.model.ghosts;

import java.util.ArrayList;
import java.util.List;

public final class GhostCandidate {
    private String ghostName;
    private int score;

    private boolean eliminated;
    private ProfileWeight profileWeight;
    private List<String> appliedRules = new ArrayList<>();

    public GhostCandidate(String ghostName) {
        this.ghostName = ghostName;
        this.score = 0;
        this.eliminated = false;
        this.profileWeight = ProfileWeight.LOW;
    }

    public boolean hasRuleApplied(String ruleName) {
        return this.appliedRules.contains(ruleName);
    }

    public void applyRule(String ruleName) {
        this.appliedRules.add(ruleName);
    }

    public List<String> getAppliedRules() {
        return appliedRules;
    }

    public void addScore(int points) {
        if (!eliminated)
            this.score += points;
    }

    public void eliminate() {
        this.eliminated = true;
        this.score = Integer.MIN_VALUE;
    }

    public String getGhostName() {
        return ghostName;
    }

    public int getScore() {
        return score;
    }

    public boolean isEliminated() {
        return eliminated;
    }

    public ProfileWeight getProfileWeight() {
        return profileWeight;
    }

    private boolean isDefinitive = false; // Inicijalno je false

    public boolean isDefinitive() {
        return isDefinitive;
    }

    public void setDefinitive(boolean definitive) {
        isDefinitive = definitive;
    }
}
