package com.ftn.sbnz.service.ghosts.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.service.ghosts.dto.AdvanceClockRequestDTO;
import com.ftn.sbnz.service.ghosts.service.ClockService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/clock")
@RequiredArgsConstructor
public class ClockController {
    private final ClockService clockService;

    @PostMapping("/advance")
    public void insertCandleExtinguished(@RequestBody AdvanceClockRequestDTO request) {
        clockService.advanceTime(request.getSeconds());
    }

}
