package com.ftn.sbnz.service.ghosts.utils;

import com.ftn.sbnz.model.observations.HuntObservation;
import com.ftn.sbnz.service.ghosts.dto.GhostIndetificationReqestDTO;

public class HuntMapper {
    public static HuntObservation mapToHuntObservation(GhostIndetificationReqestDTO dto) {
        HuntObservation observation = new HuntObservation();

        // Mapiramo polja koja trenutno postoje u tvom DTO-u:
        observation.setGhostAlwaysKnewPlayerPosition(dto.isGhostAlwaysKnewPlayerPosition());
        observation.setGhostSlowedDownNearPlayer(dto.isGhostSlowedDownNearPlayer());
        observation.setIncenseUsed(dto.isIncenseUsed());
        observation.setSecondsUntilHuntAfterIncense(dto.getSecondsUntilHuntAfterIncense());
        observation.setActivityDeclinedOverTime(dto.isActivityDeclinedOverTime());
        observation.setGhostSpeedDeclinedOverTime(dto.isGhostSpeedDeclinedOverTime());
        observation.setFuseBoxOn(dto.isFuseBoxOn());
        observation.setGhostAcceleratedWithFuseBox(dto.isGhostAcceleratedWithFuseBox());
        observation.setObjectsThrownAtOnce(dto.getObjectsThrownAtOnce());
        // Napomena: Ako ti pravila traže 'breathVisibleInCold' a nemaš ga u
        // HuntObservation,
        // dodaj ga tamo kao polje, pa ga otkomentariši ovde:
        // observation.setBreathVisibleInCold(dto.isBreathVisibleInCold());

        // Sva ostala polja u HuntObservation (so, slike, sveće...)
        // će automatski ostati na false/null jer smo tek uradili 'new
        // HuntObservation()'

        return observation;
    }
}
