package com.happiday.Happi_Day.domain.service;

import com.happiday.Happi_Day.domain.entity.event.dto.EventCreateDto;
import com.happiday.Happi_Day.domain.entity.event.dto.EventListResponseDto;
import com.happiday.Happi_Day.domain.entity.event.dto.EventResponseDto;
import com.happiday.Happi_Day.domain.entity.event.dto.EventUpdateDto;
import com.happiday.Happi_Day.domain.entity.event.Event;
import com.happiday.Happi_Day.domain.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

// TODO    private final ArtistRepository artistRepository;

    @Transactional
    public EventResponseDto createEvent(EventCreateDto request, List<MultipartFile> eventImages) {
        // TODO - 유저, 이미지 추가

        Event event = request.toEntity();
        eventRepository.save(event);
        return EventResponseDto.fromEntity(event);
    }

    public  List<EventListResponseDto> readEvents() {
        // TODO - 이미지 추가
        return eventRepository.findAll().stream()
                .map(EventListResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public EventResponseDto readEvent(Long eventId) {
        // TODO - 이미지 추가
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return EventResponseDto.fromEntity(event);
    }

    @Transactional
    public EventResponseDto updateEvent(Long eventId, EventUpdateDto request, List<MultipartFile> eventImages) {
        // TODO - 유저, 이미지 추가

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        event.update(request.toEntity());
        eventRepository.save(event);
        return EventResponseDto.fromEntity(event);
    }

    @Transactional
    public void deleteEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        eventRepository.delete(event);
    }
}
