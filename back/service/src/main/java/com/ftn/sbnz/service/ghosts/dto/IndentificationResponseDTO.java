package com.ftn.sbnz.service.ghosts.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public final class IndentificationResponseDTO {
    private int totalScore;
    private List<IndentificationResponseGhostDTO> ghosts;

}
