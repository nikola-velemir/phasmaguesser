package com.ftn.sbnz.service.ghosts.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public final class GhostIdentificationRequestDTO {

    // =========================================================================
    //  DOKAZI (Evidence) — šalje frontend, stringovi iz Evidence enum-a
    //  Primer: "EMF_LEVEL_5", "GHOST_WRITING", "GHOST_ORB" ...
    // =========================================================================
    private String evidence1;
    private String evidence2;
    private String evidence3;

    // =========================================================================
    //  SPIRIT — Long_Smudge_Timer
    // =========================================================================
    @Builder.Default
    private boolean incenseUsed = false;

    @Builder.Default
    private Integer secondsUntilHuntAfterIncense = null;

    // =========================================================================
    //  WRAITH — No_Salt_Footprints
    // =========================================================================
    @Builder.Default
    private boolean saltPlaced = false;

    @Builder.Default
    private boolean saltFootprintFound = false;

    // =========================================================================
    //  PHANTOM — Photo_Disappearance
    // =========================================================================
    @Builder.Default
    private boolean ghostPhotoTaken = false;

    @Builder.Default
    private boolean ghostDisappearedAfterPhoto = false;

    // =========================================================================
    //  POLTERGEIST — Multi_Object_Throw
    // =========================================================================
    @Builder.Default
    private int objectsThrownAtOnce = 0;

    // =========================================================================
    //  BANSHEE — Parabolic_Scream
    // =========================================================================
    @Builder.Default
    private boolean parabolicMicrophoneUsed = false;

    @Builder.Default
    private boolean uniqueScreamHeard = false;

    // =========================================================================
    //  JINN — Speed_Increase_With_Fuse_Box
    // =========================================================================
    @Builder.Default
    private boolean fuseBoxOn = false;

    @Builder.Default
    private boolean ghostAcceleratedWithFuseBox = false;

    // =========================================================================
    //  MARE — Attack_In_Darkness
    // =========================================================================
    @Builder.Default
    private boolean huntStartedInDarkRoom = false;

    // =========================================================================
    //  REVENANT — High_Hunt_Speed
    // =========================================================================
    @Builder.Default
    private int highHuntSpeed = 0;

    // =========================================================================
    //  SHADE — Shy_In_Presence
    //  ONI   — High_Activity_Near_Player
    //  (dele ista polja, suprotni efekti)
    // =========================================================================
    @Builder.Default
    private boolean multiplePlayersPresent = false;

    @Builder.Default
    private boolean activityDroppedWithPlayers = false;

    @Builder.Default
    private boolean activityIncreasedWithPlayers = false;

    @Builder.Default
    private String ghostActivityLevel = "NORMAL"; // "LOW", "NORMAL", "HIGH"

    // =========================================================================
    //  DEMON — Early_Hunt_And_Low_Cooldown
    // =========================================================================
    @Builder.Default
    private boolean huntAtHighSanity = false;

    @Builder.Default
    private Integer secondsBetweenHunts = null;

    // =========================================================================
    //  YUREI — Door_Manipulation
    // =========================================================================
    @Builder.Default
    private boolean doorSlammedAndLockedInRoom = false;

    // =========================================================================
    //  YOKAI — Voice_Sensitivity
    // =========================================================================
    @Builder.Default
    private boolean huntTriggeredByVoice = false;

    // =========================================================================
    //  HANTU — Temperature_Based_Speed
    // =========================================================================
    @Builder.Default
    private boolean freezingTemperatures = false;

    @Builder.Default
    private boolean ghostMovingFastInCold = false;

    // =========================================================================
    //  GORYO — Camera_Only_DOTS
    // =========================================================================
    @Builder.Default
    private boolean dotsVisibleOnCamera = false;

    @Builder.Default
    private boolean dotsVisibleToNakedEye = false;

    // =========================================================================
    //  MYLING — Quiet_Footsteps_During_Hunt
    // =========================================================================
    @Builder.Default
    private boolean footstepsVeryQuietDuringHunt = false;

    // =========================================================================
    //  ONRYO — Candle_Extinguish_Trigger
    // =========================================================================
    @Builder.Default
    private int candlesExtinguishedBeforeHunt = 0;

    @Builder.Default
    private boolean huntStartedAfterCandleOut = false;

    // =========================================================================
    //  THE TWINS — Dual_Interactions
    // =========================================================================
    @Builder.Default
    private boolean simultaneousInteractionsInDifferentRooms = false;

    // =========================================================================
    //  RAIJU — Electronics_Speed_Boost
    // =========================================================================
    @Builder.Default
    private boolean activeElectronicsNearby = false;

    @Builder.Default
    private boolean ghostSpeedIncreasedNearElectronics = false;

    // =========================================================================
    //  OBAKE — Six_Finger_Handprint
    // =========================================================================
    @Builder.Default
    private boolean sixFingerHandprintFound = false;

    // =========================================================================
    //  THE MIMIC — Copy_Other_Ghosts
    // =========================================================================
    @Builder.Default
    private boolean behaviorChangedMidInvestigation = false;

    // =========================================================================
    //  MOROI — Curse_And_Speed_Sanity
    // =========================================================================
    @Builder.Default
    private boolean spiritBoxResponseReceived = false;

    @Builder.Default
    private boolean playerSanityDroppedFastAfterResponse = false;

    // =========================================================================
    //  DEOGEN — Player_Tracking + Close_Slowdown
    // =========================================================================
    @Builder.Default
    private boolean ghostAlwaysKnewPlayerPosition = false;

    @Builder.Default
    private boolean ghostSlowedDownNearPlayer = false;

    // =========================================================================
    //  THAYE — Aging_Mechanic
    // =========================================================================
    @Builder.Default
    private boolean activityDeclinedOverTime = false;

    @Builder.Default
    private boolean ghostSpeedDeclinedOverTime = false;
}