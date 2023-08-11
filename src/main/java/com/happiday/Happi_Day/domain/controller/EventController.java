package com.happiday.Happi_Day.domain.controller;

import com.happiday.Happi_Day.domain.dto.event.EventRequestDto;
import com.happiday.Happi_Day.domain.dto.event.EventResponseDto;
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
            @Valid @RequestPart(value = "event")EventRequestDto eventRequestDto,
            @RequestPart List<MultipartFile> eventImages
            ){
        EventResponseDto responseDto = eventService.createEvent(eventRequestDto, eventImages);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponseDto> readEvent(@PathVariable Long eventId){
        EventResponseDto responseDto = EventService.readEvent(eventId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> readEvents(){
        List<EventResponseDto> responseDtoList = EventService.readEvents();
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @PutMapping(value = "{eventId}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EventResponseDto> updateEvent(
            @PathVariable Long eventId,
            @Valid @RequestPart(value = "event")EventRequestDto eventRequestDto,
            @RequestPart List<MultipartFile> eventImages
    ){
        EventResponseDto responseDto = eventService.updateEvent(eventId, eventRequestDto, eventImages);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<String> deleteArtlst(@PathVariable Long eventId){
        eventService.deleteEvent(eventId);
        return new ResponseEntity<>("삭제 성공", HttpStatus.NO_CONTENT);
    }

}
