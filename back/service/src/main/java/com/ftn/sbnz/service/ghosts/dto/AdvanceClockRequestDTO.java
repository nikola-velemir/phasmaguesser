package com.ftn.sbnz.service.ghosts.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public final class AdvanceClockRequestDTO {
    private int seconds;
}
