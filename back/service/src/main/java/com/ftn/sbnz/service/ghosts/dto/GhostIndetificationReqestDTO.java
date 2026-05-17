package com.ftn.sbnz.service.ghosts.dto;

import lombok.Value;

@Value
public final class GhostIndetificationReqestDTO {
    private String evidence1;
    private String evidence2;

    public String getEvidence1() {
        return evidence1;
    }

    public String getEvidence2() {
        return evidence2;
    }

}
