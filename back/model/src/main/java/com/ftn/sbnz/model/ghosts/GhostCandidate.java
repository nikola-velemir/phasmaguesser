package com.ftn.sbnz.model.ghosts;

public final class GhostCandidate {
    private String ghostName;
    private int score;

    private boolean eliminated;
    private ProfileWeight profileWeight;

    public GhostCandidate(String ghostName) {
        this.ghostName = ghostName;
        this.score = 0;
        this.eliminated = false;
        this.profileWeight = ProfileWeight.LOW;
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

}
