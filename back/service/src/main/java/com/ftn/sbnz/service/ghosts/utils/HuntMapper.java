package com.ftn.sbnz.service.ghosts.utils;

import com.ftn.sbnz.model.evidence.CurrentEvidence;
import com.ftn.sbnz.model.evidence.Evidence;
import com.ftn.sbnz.model.observations.HuntObservation;
import com.ftn.sbnz.service.ghosts.dto.GhostIdentificationRequestDTO;

import java.util.Set;
import java.util.stream.Collectors;

public class HuntMapper {

    public static HuntObservation mapToHuntObservation(GhostIdentificationRequestDTO dto) {
        HuntObservation obs = new HuntObservation();

        // SPIRIT
        obs.setIncenseUsed(dto.isIncenseUsed());
        obs.setSecondsUntilHuntAfterIncense(dto.getSecondsUntilHuntAfterIncense());

        // WRAITH
        obs.setSaltPlaced(dto.isSaltPlaced());
        obs.setSaltFootprintFound(dto.isSaltFootprintFound());

        // PHANTOM
        obs.setGhostPhotoTaken(dto.isGhostPhotoTaken());
        obs.setGhostDisappearedAfterPhoto(dto.isGhostDisappearedAfterPhoto());

        // POLTERGEIST
        obs.setObjectsThrownAtOnce(dto.getObjectsThrownAtOnce());

        // BANSHEE
        obs.setParabolicMicrophoneUsed(dto.isParabolicMicrophoneUsed());
        obs.setUniqueScreamHeard(dto.isUniqueScreamHeard());

        // JINN
        obs.setFuseBoxOn(dto.isFuseBoxOn());
        obs.setGhostAcceleratedWithFuseBox(dto.isGhostAcceleratedWithFuseBox());

        // MARE
        obs.setHuntStartedInDarkRoom(dto.isHuntStartedInDarkRoom());

        // REVENANT
        obs.setHighHuntSpeed(dto.getHighHuntSpeed());

        // SHADE + ONI (dele ista polja)
        obs.setMultiplePlayersPresent(dto.isMultiplePlayersPresent());
        obs.setActivityDroppedWithPlayers(dto.isActivityDroppedWithPlayers());
        obs.setActivityIncreasedWithPlayers(dto.isActivityIncreasedWithPlayers());
        obs.setGhostActivityLevel(dto.getGhostActivityLevel());

        // DEMON
        obs.setHuntAtHighSanity(dto.isHuntAtHighSanity());
        obs.setSecondsBetweenHunts(dto.getSecondsBetweenHunts());

        // YUREI
        obs.setDoorSlammedAndLockedInRoom(dto.isDoorSlammedAndLockedInRoom());

        // YOKAI
        obs.setHuntTriggeredByVoice(dto.isHuntTriggeredByVoice());

        // HANTU
        obs.setFreezingTemperatures(dto.isFreezingTemperatures());
        obs.setGhostMovingFastInCold(dto.isGhostMovingFastInCold());

        // GORYO
        obs.setDotsVisibleOnCamera(dto.isDotsVisibleOnCamera());
        obs.setDotsVisibleToNakedEye(dto.isDotsVisibleToNakedEye());

        // MYLING
        obs.setFootstepsVeryQuietDuringHunt(dto.isFootstepsVeryQuietDuringHunt());

        // ONRYO
        obs.setCandlesExtinguishedBeforeHunt(dto.getCandlesExtinguishedBeforeHunt());
        obs.setHuntStartedAfterCandleOut(dto.isHuntStartedAfterCandleOut());

        // THE TWINS
        obs.setSimultaneousInteractionsInDifferentRooms(dto.isSimultaneousInteractionsInDifferentRooms());

        // RAIJU
        obs.setActiveElectronicsNearby(dto.isActiveElectronicsNearby());
        obs.setGhostSpeedIncreasedNearElectronics(dto.isGhostSpeedIncreasedNearElectronics());

        // OBAKE
        obs.setSixFingerHandprintFound(dto.isSixFingerHandprintFound());

        // THE MIMIC
        obs.setBehaviorChangedMidInvestigation(dto.isBehaviorChangedMidInvestigation());

        // MOROI
        obs.setSpiritBoxResponseReceived(dto.isSpiritBoxResponseReceived());
        obs.setPlayerSanityDroppedFastAfterResponse(dto.isPlayerSanityDroppedFastAfterResponse());

        // DEOGEN
        obs.setGhostAlwaysKnewPlayerPosition(dto.isGhostAlwaysKnewPlayerPosition());
        obs.setGhostSlowedDownNearPlayer(dto.isGhostSlowedDownNearPlayer());

        // THAYE
        obs.setActivityDeclinedOverTime(dto.isActivityDeclinedOverTime());
        obs.setGhostSpeedDeclinedOverTime(dto.isGhostSpeedDeclinedOverTime());

        return obs;
    }

    /**
     * Mapira string dokaze iz DTO-a → CurrentEvidence objekat.
     * Ignoriše null i prazne stringove (frontend nije poslao treći dokaz).
     * Ignoriše nevalidne vrednosti uz upozorenje u logu.
     */
    public static CurrentEvidence mapToCurrentEvidence(GhostIdentificationRequestDTO dto) {
        Set<Evidence> confirmed = dto.getEvidence()
                                    .stream()
                                    .map(e-> parseEvidence(e))
                                    .collect(Collectors.toSet());

        return new CurrentEvidence(confirmed);
    }
    private static Evidence parseEvidence(String evidenceString) {
        Evidence ev = null;
        if (evidenceString != null && !evidenceString.trim().isEmpty()) {
            ev = Evidence.valueOf(evidenceString.toUpperCase());
        }
        return ev;

    }
}