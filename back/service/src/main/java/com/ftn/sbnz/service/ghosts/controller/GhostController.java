package com.ftn.sbnz.service.ghosts.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.service.ghosts.dto.GhostIdentificationRequestDTO;
import com.ftn.sbnz.service.ghosts.service.GhostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/ghosts")
@RequiredArgsConstructor
public class GhostController {
    private final GhostService ghostService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> indentify(@RequestBody GhostIdentificationRequestDTO request) {
        return ResponseEntity.ok(ghostService.indifyGhost(request));
    }
}
