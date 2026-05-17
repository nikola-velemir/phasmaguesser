package com.ftn.sbnz.model.observations;

import java.io.Serializable;

public class HuntObservation implements Serializable {

    // == Polja iz pravila ==
    private boolean freezingTemperatures;
    private boolean ghostMovingFastInCold;
    private boolean incenseUsed;
    private Integer secondsUntilHuntAfterIncense; // null ako nije lovio

    private boolean saltPlaced;
    private boolean saltFootprintFound;
    private boolean ghostPhotoTaken;
    private boolean ghostDisappearedAfterPhoto;
    private int objectsThrownAtOnce;
    private boolean parabolicMicrophoneUsed;
    private boolean uniqueScreamHeard;
    private boolean fuseBoxOn;
    private boolean ghostAcceleratedWithFuseBox;
    private boolean huntStartedInDarkRoom;
    private int highHuntSpeed;
    private boolean multiplePlayersPresent;
    private boolean activityDroppedWithPlayers;
    private boolean huntAtHighSanity;
    private Integer secondsBetweenHunts; // null ako nema podataka
    private boolean doorSlammedAndLockedInRoom;
    private boolean activityIncreasedWithPlayers;
    private boolean huntTriggeredByVoice;
    private boolean dotsVisibleOnCamera;
    private boolean dotsVisibleToNakedEye;
    private boolean footstepsVeryQuietDuringHunt;
    private int candlesExtinguishedBeforeHunt;
    private boolean huntStartedAfterCandleOut;
    private boolean simultaneousInteractionsInDifferentRooms;
    private boolean activeElectronicsNearby;
    private boolean ghostSpeedIncreasedNearElectronics;
    private boolean sixFingerHandprintFound;
    private boolean behaviorChangedMidInvestigation;
    private boolean spiritBoxResponseReceived;
    private boolean playerSanityDroppedFastAfterResponse;
    private boolean ghostAlwaysKnewPlayerPosition;
    private boolean ghostSlowedDownNearPlayer;
    private boolean activityDeclinedOverTime;
    private boolean ghostSpeedDeclinedOverTime;
    private String ghostActivityLevel;

    // == Prazan konstruktor ==
    public HuntObservation() {
    }

    public String getGhostActivityLevel() {
        return ghostActivityLevel;
    }

    public void setGhostActivityLevel(String ghostActivityLevel) {
        this.ghostActivityLevel = ghostActivityLevel;
    }

    // == Getteri i Setteri ==
    public boolean isFreezingTemperatures() {
        return freezingTemperatures;
    }

    public void setFreezingTemperatures(boolean freezingTemperatures) {
        this.freezingTemperatures = freezingTemperatures;
    }

    public boolean isGhostMovingFastInCold() {
        return ghostMovingFastInCold;
    }

    public void setGhostMovingFastInCold(boolean ghostMovingFastInCold) {
        this.ghostMovingFastInCold = ghostMovingFastInCold;
    }

    public boolean isIncenseUsed() {
        return incenseUsed;
    }

    public void setIncenseUsed(boolean incenseUsed) {
        this.incenseUsed = incenseUsed;
    }

    public Integer getSecondsUntilHuntAfterIncense() {
        return secondsUntilHuntAfterIncense;
    }

    public void setSecondsUntilHuntAfterIncense(Integer secondsUntilHuntAfterIncense) {
        this.secondsUntilHuntAfterIncense = secondsUntilHuntAfterIncense;
    }

    public boolean isSaltPlaced() {
        return saltPlaced;
    }

    public void setSaltPlaced(boolean saltPlaced) {
        this.saltPlaced = saltPlaced;
    }

    public boolean isSaltFootprintFound() {
        return saltFootprintFound;
    }

    public void setSaltFootprintFound(boolean saltFootprintFound) {
        this.saltFootprintFound = saltFootprintFound;
    }

    public boolean isGhostPhotoTaken() {
        return ghostPhotoTaken;
    }

    public void setGhostPhotoTaken(boolean ghostPhotoTaken) {
        this.ghostPhotoTaken = ghostPhotoTaken;
    }

    public boolean isGhostDisappearedAfterPhoto() {
        return ghostDisappearedAfterPhoto;
    }

    public void setGhostDisappearedAfterPhoto(boolean ghostDisappearedAfterPhoto) {
        this.ghostDisappearedAfterPhoto = ghostDisappearedAfterPhoto;
    }

    public int getObjectsThrownAtOnce() {
        return objectsThrownAtOnce;
    }

    public void setObjectsThrownAtOnce(int objectsThrownAtOnce) {
        this.objectsThrownAtOnce = objectsThrownAtOnce;
    }

    public boolean isParabolicMicrophoneUsed() {
        return parabolicMicrophoneUsed;
    }

    public void setParabolicMicrophoneUsed(boolean parabolicMicrophoneUsed) {
        this.parabolicMicrophoneUsed = parabolicMicrophoneUsed;
    }

    public boolean isUniqueScreamHeard() {
        return uniqueScreamHeard;
    }

    public void setUniqueScreamHeard(boolean uniqueScreamHeard) {
        this.uniqueScreamHeard = uniqueScreamHeard;
    }

    public boolean isFuseBoxOn() {
        return fuseBoxOn;
    }

    public void setFuseBoxOn(boolean fuseBoxOn) {
        this.fuseBoxOn = fuseBoxOn;
    }

    public boolean isGhostAcceleratedWithFuseBox() {
        return ghostAcceleratedWithFuseBox;
    }

    public void setGhostAcceleratedWithFuseBox(boolean ghostAcceleratedWithFuseBox) {
        this.ghostAcceleratedWithFuseBox = ghostAcceleratedWithFuseBox;
    }

    public boolean isHuntStartedInDarkRoom() {
        return huntStartedInDarkRoom;
    }

    public void setHuntStartedInDarkRoom(boolean huntStartedInDarkRoom) {
        this.huntStartedInDarkRoom = huntStartedInDarkRoom;
    }

    public int getHighHuntSpeed() {
        return highHuntSpeed;
    }

    public void setHighHuntSpeed(int highHuntSpeed) {
        this.highHuntSpeed = highHuntSpeed;
    }

    public boolean isMultiplePlayersPresent() {
        return multiplePlayersPresent;
    }

    public void setMultiplePlayersPresent(boolean multiplePlayersPresent) {
        this.multiplePlayersPresent = multiplePlayersPresent;
    }

    public boolean isActivityDroppedWithPlayers() {
        return activityDroppedWithPlayers;
    }

    public void setActivityDroppedWithPlayers(boolean activityDroppedWithPlayers) {
        this.activityDroppedWithPlayers = activityDroppedWithPlayers;
    }

    public boolean isHuntAtHighSanity() {
        return huntAtHighSanity;
    }

    public void setHuntAtHighSanity(boolean huntAtHighSanity) {
        this.huntAtHighSanity = huntAtHighSanity;
    }

    public Integer getSecondsBetweenHunts() {
        return secondsBetweenHunts;
    }

    public void setSecondsBetweenHunts(Integer secondsBetweenHunts) {
        this.secondsBetweenHunts = secondsBetweenHunts;
    }

    public boolean isDoorSlammedAndLockedInRoom() {
        return doorSlammedAndLockedInRoom;
    }

    public void setDoorSlammedAndLockedInRoom(boolean doorSlammedAndLockedInRoom) {
        this.doorSlammedAndLockedInRoom = doorSlammedAndLockedInRoom;
    }

    public boolean isActivityIncreasedWithPlayers() {
        return activityIncreasedWithPlayers;
    }

    public void setActivityIncreasedWithPlayers(boolean activityIncreasedWithPlayers) {
        this.activityIncreasedWithPlayers = activityIncreasedWithPlayers;
    }

    public boolean isHuntTriggeredByVoice() {
        return huntTriggeredByVoice;
    }

    public void setHuntTriggeredByVoice(boolean huntTriggeredByVoice) {
        this.huntTriggeredByVoice = huntTriggeredByVoice;
    }

    public boolean isDotsVisibleOnCamera() {
        return dotsVisibleOnCamera;
    }

    public void setDotsVisibleOnCamera(boolean dotsVisibleOnCamera) {
        this.dotsVisibleOnCamera = dotsVisibleOnCamera;
    }

    public boolean isDotsVisibleToNakedEye() {
        return dotsVisibleToNakedEye;
    }

    public void setDotsVisibleToNakedEye(boolean dotsVisibleToNakedEye) {
        this.dotsVisibleToNakedEye = dotsVisibleToNakedEye;
    }

    public boolean isFootstepsVeryQuietDuringHunt() {
        return footstepsVeryQuietDuringHunt;
    }

    public void setFootstepsVeryQuietDuringHunt(boolean footstepsVeryQuietDuringHunt) {
        this.footstepsVeryQuietDuringHunt = footstepsVeryQuietDuringHunt;
    }

    public int getCandlesExtinguishedBeforeHunt() {
        return candlesExtinguishedBeforeHunt;
    }

    public void setCandlesExtinguishedBeforeHunt(int candlesExtinguishedBeforeHunt) {
        this.candlesExtinguishedBeforeHunt = candlesExtinguishedBeforeHunt;
    }

    public boolean isHuntStartedAfterCandleOut() {
        return huntStartedAfterCandleOut;
    }

    public void setHuntStartedAfterCandleOut(boolean huntStartedAfterCandleOut) {
        this.huntStartedAfterCandleOut = huntStartedAfterCandleOut;
    }

    public boolean isSimultaneousInteractionsInDifferentRooms() {
        return simultaneousInteractionsInDifferentRooms;
    }

    public void setSimultaneousInteractionsInDifferentRooms(boolean simultaneousInteractionsInDifferentRooms) {
        this.simultaneousInteractionsInDifferentRooms = simultaneousInteractionsInDifferentRooms;
    }

    public boolean isActiveElectronicsNearby() {
        return activeElectronicsNearby;
    }

    public void setActiveElectronicsNearby(boolean activeElectronicsNearby) {
        this.activeElectronicsNearby = activeElectronicsNearby;
    }

    public boolean isGhostSpeedIncreasedNearElectronics() {
        return ghostSpeedIncreasedNearElectronics;
    }

    public void setGhostSpeedIncreasedNearElectronics(boolean ghostSpeedIncreasedNearElectronics) {
        this.ghostSpeedIncreasedNearElectronics = ghostSpeedIncreasedNearElectronics;
    }

    public boolean isSixFingerHandprintFound() {
        return sixFingerHandprintFound;
    }

    public void setSixFingerHandprintFound(boolean sixFingerHandprintFound) {
        this.sixFingerHandprintFound = sixFingerHandprintFound;
    }

    public boolean isBehaviorChangedMidInvestigation() {
        return behaviorChangedMidInvestigation;
    }

    public void setBehaviorChangedMidInvestigation(boolean behaviorChangedMidInvestigation) {
        this.behaviorChangedMidInvestigation = behaviorChangedMidInvestigation;
    }

    public boolean isSpiritBoxResponseReceived() {
        return spiritBoxResponseReceived;
    }

    public void setSpiritBoxResponseReceived(boolean spiritBoxResponseReceived) {
        this.spiritBoxResponseReceived = spiritBoxResponseReceived;
    }

    public boolean isPlayerSanityDroppedFastAfterResponse() {
        return playerSanityDroppedFastAfterResponse;
    }

    public void setPlayerSanityDroppedFastAfterResponse(boolean playerSanityDroppedFastAfterResponse) {
        this.playerSanityDroppedFastAfterResponse = playerSanityDroppedFastAfterResponse;
    }

    public boolean isGhostAlwaysKnewPlayerPosition() {
        return ghostAlwaysKnewPlayerPosition;
    }

    public void setGhostAlwaysKnewPlayerPosition(boolean ghostAlwaysKnewPlayerPosition) {
        this.ghostAlwaysKnewPlayerPosition = ghostAlwaysKnewPlayerPosition;
    }

    public boolean isGhostSlowedDownNearPlayer() {
        return ghostSlowedDownNearPlayer;
    }

    public void setGhostSlowedDownNearPlayer(boolean ghostSlowedDownNearPlayer) {
        this.ghostSlowedDownNearPlayer = ghostSlowedDownNearPlayer;
    }

    public boolean isActivityDeclinedOverTime() {
        return activityDeclinedOverTime;
    }

    public void setActivityDeclinedOverTime(boolean activityDeclinedOverTime) {
        this.activityDeclinedOverTime = activityDeclinedOverTime;
    }

    public boolean isGhostSpeedDeclinedOverTime() {
        return ghostSpeedDeclinedOverTime;
    }

    public void setGhostSpeedDeclinedOverTime(boolean ghostSpeedDeclinedOverTime) {
        this.ghostSpeedDeclinedOverTime = ghostSpeedDeclinedOverTime;
    }
}