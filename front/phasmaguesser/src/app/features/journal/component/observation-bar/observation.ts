export interface Observation {
    freezingTemperatures: boolean;
    ghostMovingFastInCold: boolean;

    incenseUsed: boolean;
    secondsUntilHuntAfterIncense: number | null;

    saltPlaced: boolean;
    saltFootprintFound: boolean;

    ghostPhotoTaken: boolean;
    ghostDisappearedAfterPhoto: boolean;

    objectsThrownAtOnce: number;

    parabolicMicrophoneUsed: boolean;
    uniqueScreamHeard: boolean;

    fuseBoxOn: boolean;
    ghostAcceleratedWithFuseBox: boolean;

    huntStartedInDarkRoom: boolean;

    highHuntSpeed: number;

    multiplePlayersPresent: boolean;
    activityDroppedWithPlayers: boolean;

    huntAtHighSanity: boolean;

    secondsBetweenHunts: number | null;

    doorSlammedAndLockedInRoom: boolean;

    activityIncreasedWithPlayers: boolean;

    huntTriggeredByVoice: boolean;

    dotsVisibleOnCamera: boolean;
    dotsVisibleToNakedEye: boolean;

    footstepsVeryQuietDuringHunt: boolean;

    candlesExtinguishedBeforeHunt: number;

    huntStartedAfterCandleOut: boolean;

    simultaneousInteractionsInDifferentRooms: boolean;

    activeElectronicsNearby: boolean;
    ghostSpeedIncreasedNearElectronics: boolean;

    sixFingerHandprintFound: boolean;

    behaviorChangedMidInvestigation: boolean;

    spiritBoxResponseReceived: boolean;
    playerSanityDroppedFastAfterResponse: boolean;

    ghostAlwaysKnewPlayerPosition: boolean;

    ghostSlowedDownNearPlayer: boolean;

    activityDeclinedOverTime: boolean;

    ghostSpeedDeclinedOverTime: boolean;

    ghostActivityLevel: string | null;
}