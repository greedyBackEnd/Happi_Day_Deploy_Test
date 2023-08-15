package com.happiday.Happi_Day.domain.service;

import com.happiday.Happi_Day.domain.dto.event.EventCreateDto;
import com.happiday.Happi_Day.domain.dto.event.EventResponseDto;
import com.happiday.Happi_Day.domain.dto.event.EventUpdateDto;
import com.happiday.Happi_Day.domain.entity.event.Event;
import com.happiday.Happi_Day.domain.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public  List<EventResponseDto> readEvents() {

        // TODO - 이미지 추가

        List<Event> events = eventRepository.findAll();
        List<EventResponseDto> eventDtoList = new ArrayList<>();

        for (Event event : events) {
            eventDtoList.add(EventResponseDto.fromEntity(event));
        }

        return eventDtoList;
    }

    public EventResponseDto readEvent(Long eventId) {

        // TODO - 이미지 추가

        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if(optionalEvent.isPresent()) {
            return EventResponseDto.fromEntity(optionalEvent.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public EventResponseDto updateEvent(Long eventId, EventUpdateDto request, List<MultipartFile> eventImages) {

        // TODO - 유저, 이미지 추가

        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if(optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            event.update(request.toEntity());
            return EventResponseDto.fromEntity(event);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public void deleteEvent(Long eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            eventRepository.delete(event);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
