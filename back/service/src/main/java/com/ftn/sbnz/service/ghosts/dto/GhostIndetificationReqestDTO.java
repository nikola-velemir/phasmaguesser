package com.ftn.sbnz.service.ghosts.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized // Omogućava Jacksonu da glatko mapira JSON direktno preko Lombok Builder-a
public final class GhostIndetificationReqestDTO {

    // Tvrdi dokazi sa frontenda
    private String evidence1;
    private String evidence2;

    // --- POLJA ZA NIVO 1 TRAITOVE (Inicijalizovana na false/default ako se ne
    // proslede) ---

    @Builder.Default
    private boolean ghostAlwaysKnewPlayerPosition = false;

    @Builder.Default
    private boolean ghostSlowedDownNearPlayer = false;

    @Builder.Default
    private boolean incenseUsed = false;

    // Integer umesto int da bi frontend mogao eksplicitno da pošalje null ako
    // tajmer nije meren
    private Integer secondsUntilHuntAfterIncense;

    @Builder.Default
    private boolean activityDeclinedOverTime = false;

    @Builder.Default
    private boolean ghostSpeedDeclinedOverTime = false;

    @Builder.Default
    private boolean fuseBoxOn = false;

    @Builder.Default
    private boolean ghostAcceleratedWithFuseBox = false;

    @Builder.Default
    private boolean breathVisibleInCold = false;

    @Builder.Default
    private int objectsThrownAtOnce = 0;

    // Getteri za dokaze (Lombok ih već pravi pod haubom, ali ostaju ako su ti
    // potrebni eksplicitno)
    public String getEvidence1() {
        return evidence1;
    }

    public String getEvidence2() {
        return evidence2;
    }
}