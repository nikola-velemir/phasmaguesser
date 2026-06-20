package com.ftn.sbnz.service.ghosts.dto;

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
public class TraitObservedRequestDTO {
    private Long timestamp;
    private String traitName;
}
