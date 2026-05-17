package com.ftn.sbnz.service.ghosts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.evidence.CurrentEvidence;
import com.ftn.sbnz.model.evidence.Evidence;
import com.ftn.sbnz.model.ghosts.GhostCandidate;
import com.ftn.sbnz.model.observations.HuntObservation;
import com.ftn.sbnz.service.ghosts.dto.GhostIndetificationReqestDTO;
import com.ftn.sbnz.service.ghosts.utils.HuntMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GhostService {
    private final IndentificatorService indentificatorService;

    public List<GhostCandidate> indifyGhost(GhostIndetificationReqestDTO reqest) {
        Evidence ev1 = parseEvidence(reqest.getEvidence1());
        Evidence ev2 = parseEvidence(reqest.getEvidence2());
        CurrentEvidence currentEvidence = new CurrentEvidence(
                ev1, ev2);

        HuntObservation huntObservation = HuntMapper.mapToHuntObservation(reqest);
            

        List<GhostCandidate> candidates = indentificatorService.indifyGhost(currentEvidence, huntObservation);
        return candidates;
    }

    private Evidence parseEvidence(String evidenceString) {
        Evidence ev = null;
        if (evidenceString != null && !evidenceString.trim().isEmpty()) {
            ev = Evidence.valueOf(evidenceString.toUpperCase());
        }
        return ev;

    }
}
