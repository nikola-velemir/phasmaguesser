package com.ftn.sbnz.model.ghosts;

public final class ProfileMatch {
    public ProfileMatch(String ghostName, String ruleName) {
        this.ghostName = ghostName;
        this.ruleName = ruleName;
        this.score = 0;
    }
    public ProfileMatch(String ghostName, String ruleName, int score) {
        this.ghostName = ghostName;
        this.ruleName = ruleName;
        this.score = score;
    }
    private String ghostName;
    private String ruleName;
    private int score;
    
    public String getGhostName() {
        return ghostName;
    }
    public void setGhostName(String ghostName) {
        this.ghostName = ghostName;
    }
    public String getRuleName() {
        return ruleName;
    }
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
}
