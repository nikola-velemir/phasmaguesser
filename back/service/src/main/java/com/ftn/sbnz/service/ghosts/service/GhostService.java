package com.ftn.sbnz.service.ghosts.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.evidence.CurrentEvidence;
import com.ftn.sbnz.model.evidence.Evidence;
import com.ftn.sbnz.model.ghosts.GhostCandidate;
import com.ftn.sbnz.model.observations.HuntObservation;
import com.ftn.sbnz.service.ghosts.dto.GhostIdentificationRequestDTO;
import com.ftn.sbnz.service.ghosts.dto.IndentificationResponseDTO;
import com.ftn.sbnz.service.ghosts.dto.IndentificationResponseGhostDTO;
import com.ftn.sbnz.service.ghosts.utils.HuntMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GhostService {
    private final IndentificatorService indentificatorService;

    public IndentificationResponseDTO indifyGhost(GhostIdentificationRequestDTO reqest) {
        Set<Evidence> evidence = reqest.getEvidence()
                .stream()
                .map(this::parseEvidence)
                .collect(Collectors.toSet());
        CurrentEvidence currentEvidence = new CurrentEvidence(evidence);

        HuntObservation huntObservation = HuntMapper.mapToHuntObservation(reqest);

        List<GhostCandidate> candidates = indentificatorService.indifyGhost(currentEvidence, huntObservation);

        List<IndentificationResponseGhostDTO> ghostDtos = candidates.stream().map(IndentificationResponseGhostDTO::from)
                .collect(Collectors.toList());

        int totalScore = ghostDtos.stream().mapToInt(IndentificationResponseGhostDTO::getScore).map(s -> Math.max(0, s))
                .reduce(0, (a, b) -> a + b);

        return IndentificationResponseDTO.builder().totalScore(totalScore).ghosts(ghostDtos).build();
    }

    private Evidence parseEvidence(String evidenceString) {
        Evidence ev = null;
        if (evidenceString != null && !evidenceString.trim().isEmpty()) {
            ev = Evidence.valueOf(evidenceString.toUpperCase());
        }
        return ev;

    }
}
