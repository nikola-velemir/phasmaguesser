package com.ftn.sbnz.service.ghosts.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.model.events.CandleExtinguishedEvent;
import com.ftn.sbnz.model.events.HuntStartedEvent;
import com.ftn.sbnz.model.events.IncenceUsedEvent;
import com.ftn.sbnz.model.events.TraitObservedEvent;
import com.ftn.sbnz.service.ghosts.dto.TimestampPayloadDTO;
import com.ftn.sbnz.service.ghosts.dto.TraitObservedRequestDTO;
import com.ftn.sbnz.service.ghosts.service.EventService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping("/candle-extinguished")
    public void insertCandleExtinguished(@RequestBody TimestampPayloadDTO timestamp) {
        eventService.insertEvent(new CandleExtinguishedEvent(timestamp.getTimestamp()));
    }

    @PostMapping("/hunt-started")
    public void instertHuntStarted(@RequestBody TimestampPayloadDTO timestamp) {
        eventService.insertEvent(new HuntStartedEvent(timestamp.getTimestamp()));
    }

    @PostMapping("/incense-used")
    public void insertIncenceUsed(@RequestBody TimestampPayloadDTO timestamp) {
        eventService.insertEvent(new IncenceUsedEvent(timestamp.getTimestamp()));
    }

    @PostMapping("/trait-observed")
    public void insertTraitObserved(@RequestBody TraitObservedRequestDTO request) {
        System.out.println(request.getTraitName());
        eventService.insertEvent(new TraitObservedEvent(request.getTimestamp(), request.getTraitName()));
    }

    @PostMapping("/clear-events")
    public void clearEvents() {
        eventService.clearSession();
    }
}
