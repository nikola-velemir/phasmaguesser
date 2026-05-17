package com.ftn.sbnz.model.observations;

import java.io.Serializable;

public class HuntObservation implements Serializable {

    // ── Temperatura i kretanje ──────────────────────────
    private boolean freezingTemperatures;
    private boolean ghostMovingFastInCold;

    // ── Lov i agresija ──────────────────────────────────
    private boolean incenseUsed;
    private Integer secondsUntilHuntAfterIncense; // null = nije lovio

    // ── Sveće ───────────────────────────────────────────
    private int candlesExtinguishedBeforeHunt;

    // ── So / otisci ─────────────────────────────────────
    private boolean saltFootprintFound;

    // ── Vrata ───────────────────────────────────────────
    private boolean doorManipulatedDuringHunt;

    // ── Ostala ponašanja (iz slike trait-ova) ───────────
    private boolean longSmudgeEffect;        // Long_Smudge
    private boolean multipleObjectsThrown;   // Multi_Object_Throw
    private boolean parabolicScreanHeard;    // Parabolic_Scream
    private boolean attackedInDarkness;      // Attack_In_Dark
    private boolean shyInPresence;           // Shy_In_Presence
    private boolean highActivityWhenAlone;   // High_Activity_*
    private boolean voiceSensitive;          // Voice_Sensitive
    private boolean cameraOnly;              // Camera_Only
    private boolean quietFootsteps;          // Quiet_Footsteps
    private boolean dualInteraction;         // Dual_Interaction
    private boolean electronicsSurge;        // Electronics_S*
    private boolean sixFingerHandprint;      // Six_Finger_Ha*
    private boolean copyOtherGhost;          // Copy_Other_*
    private boolean curseAndSummon;          // Curse_And_S*
    private boolean playerTracking;          // Player_Tracki*
    private boolean agingMechanic;           // Aging_Mecha*
    private boolean speedIncrease;           // Speed_Increa*
    private int highHuntSpeed;               // High_Hunt_Sp* (0 ako nije mereno)
    private boolean earlyHuntAbility;        // Early_Hunt_A*

    // ── Getteri i setteri ───────────────────────────────
    public boolean isFreezingTemperatures() { return freezingTemperatures; }
    public void setFreezingTemperatures(boolean v) { this.freezingTemperatures = v; }

    public boolean isGhostMovingFastInCold() { return ghostMovingFastInCold; }
    public void setGhostMovingFastInCold(boolean v) { this.ghostMovingFastInCold = v; }

    public boolean isIncenseUsed() { return incenseUsed; }
    public void setIncenseUsed(boolean v) { this.incenseUsed = v; }

    public Integer getSecondsUntilHuntAfterIncense() { return secondsUntilHuntAfterIncense; }
    public void setSecondsUntilHuntAfterIncense(Integer v) { this.secondsUntilHuntAfterIncense = v; }

    public int getCandlesExtinguishedBeforeHunt() { return candlesExtinguishedBeforeHunt; }
    public void setCandlesExtinguishedBeforeHunt(int v) { this.candlesExtinguishedBeforeHunt = v; }

    public boolean isSaltFootprintFound() { return saltFootprintFound; }
    public void setSaltFootprintFound(boolean v) { this.saltFootprintFound = v; }

    public boolean isDoorManipulatedDuringHunt() { return doorManipulatedDuringHunt; }
    public void setDoorManipulatedDuringHunt(boolean v) { this.doorManipulatedDuringHunt = v; }

    public boolean isLongSmudgeEffect() { return longSmudgeEffect; }
    public void setLongSmudgeEffect(boolean v) { this.longSmudgeEffect = v; }

    public boolean isMultipleObjectsThrown() { return multipleObjectsThrown; }
    public void setMultipleObjectsThrown(boolean v) { this.multipleObjectsThrown = v; }

    public boolean isParabolicScreanHeard() { return parabolicScreanHeard; }
    public void setParabolicScreanHeard(boolean v) { this.parabolicScreanHeard = v; }

    public boolean isAttackedInDarkness() { return attackedInDarkness; }
    public void setAttackedInDarkness(boolean v) { this.attackedInDarkness = v; }

    public boolean isShyInPresence() { return shyInPresence; }
    public void setShyInPresence(boolean v) { this.shyInPresence = v; }

    public boolean isHighActivityWhenAlone() { return highActivityWhenAlone; }
    public void setHighActivityWhenAlone(boolean v) { this.highActivityWhenAlone = v; }

    public boolean isVoiceSensitive() { return voiceSensitive; }
    public void setVoiceSensitive(boolean v) { this.voiceSensitive = v; }

    public boolean isCameraOnly() { return cameraOnly; }
    public void setCameraOnly(boolean v) { this.cameraOnly = v; }

    public boolean isQuietFootsteps() { return quietFootsteps; }
    public void setQuietFootsteps(boolean v) { this.quietFootsteps = v; }

    public boolean isDualInteraction() { return dualInteraction; }
    public void setDualInteraction(boolean v) { this.dualInteraction = v; }

    public boolean isElectronicsSurge() { return electronicsSurge; }
    public void setElectronicsSurge(boolean v) { this.electronicsSurge = v; }

    public boolean isSixFingerHandprint() { return sixFingerHandprint; }
    public void setSixFingerHandprint(boolean v) { this.sixFingerHandprint = v; }

    public boolean isCopyOtherGhost() { return copyOtherGhost; }
    public void setCopyOtherGhost(boolean v) { this.copyOtherGhost = v; }

    public boolean isCurseAndSummon() { return curseAndSummon; }
    public void setCurseAndSummon(boolean v) { this.curseAndSummon = v; }

    public boolean isPlayerTracking() { return playerTracking; }
    public void setPlayerTracking(boolean v) { this.playerTracking = v; }

    public boolean isAgingMechanic() { return agingMechanic; }
    public void setAgingMechanic(boolean v) { this.agingMechanic = v; }

    public boolean isSpeedIncrease() { return speedIncrease; }
    public void setSpeedIncrease(boolean v) { this.speedIncrease = v; }

    public int getHighHuntSpeed() { return highHuntSpeed; }
    public void setHighHuntSpeed(int v) { this.highHuntSpeed = v; }

    public boolean isEarlyHuntAbility() { return earlyHuntAbility; }
    public void setEarlyHuntAbility(boolean v) { this.earlyHuntAbility = v; }
}