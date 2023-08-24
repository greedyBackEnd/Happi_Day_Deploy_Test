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
import org.springframework.security.core.Authentication;
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
            @RequestParam(value = "thumbnailFile", required = false) MultipartFile thumbnailFile,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            Authentication authentication
            ){
        EventResponseDto responseDto = eventService.createEvent(eventCreateDto, thumbnailFile, imageFile, authentication.getName());
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
            @RequestParam(value = "thumbnailFile", required = false) MultipartFile thumbnailFile,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            Authentication authentication
    ){
        EventResponseDto responseDto = eventService.updateEvent(eventId, eventUpdateDto, thumbnailFile, imageFile, authentication.getName());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<String> deleteArtlst(@PathVariable Long eventId,Authentication authentication){
        eventService.deleteEvent(eventId, authentication.getName());
        return new ResponseEntity<>("삭제 성공", HttpStatus.NO_CONTENT);
    }


}
