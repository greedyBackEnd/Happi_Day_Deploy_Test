package com.happiday.Happi_Day.domain.controller;

import com.happiday.Happi_Day.domain.entity.event.dto.EventCreateDto;
import com.happiday.Happi_Day.domain.entity.event.dto.EventListResponseDto;
import com.happiday.Happi_Day.domain.entity.event.dto.EventResponseDto;
import com.happiday.Happi_Day.domain.entity.event.dto.EventUpdateDto;
import com.happiday.Happi_Day.domain.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EventResponseDto> createEvent(
            @Valid @RequestPart(value = "event") EventCreateDto eventCreateDto,
            @RequestPart List<MultipartFile> eventImages
            ){
        EventResponseDto responseDto = eventService.createEvent(eventCreateDto, eventImages);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponseDto> readEvent(@PathVariable Long eventId){
        EventResponseDto responseDto = eventService.readEvent(eventId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EventListResponseDto>> readEvents(){
        List<EventListResponseDto> responseDtoList = eventService.readEvents();
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @PutMapping(value = "{eventId}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EventResponseDto> updateEvent(
            @PathVariable Long eventId,
            @Valid @RequestPart(value = "event") EventUpdateDto eventUpdateDto,
            @RequestPart List<MultipartFile> eventImages
    ){
        EventResponseDto responseDto = eventService.updateEvent(eventId, eventUpdateDto, eventImages);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<String> deleteArtlst(@PathVariable Long eventId){
        eventService.deleteEvent(eventId);
        return new ResponseEntity<>("삭제 성공", HttpStatus.NO_CONTENT);
    }


}
