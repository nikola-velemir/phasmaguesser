package com.ftn.sbnz.service.ghosts.dto;

import com.ftn.sbnz.model.ghosts.GhostCandidate;

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
public class IndentificationResponseGhostDTO {
    private String name;
    private int score;

    public static IndentificationResponseGhostDTO from(GhostCandidate candidate) {
        return new IndentificationResponseGhostDTO(candidate.getGhostName(), candidate.getScore());
    }
}
