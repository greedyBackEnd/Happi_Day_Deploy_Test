package com.happiday.Happi_Day.domain.service;

import com.happiday.Happi_Day.domain.entity.event.dto.EventCreateDto;
import com.happiday.Happi_Day.domain.entity.event.dto.EventListResponseDto;
import com.happiday.Happi_Day.domain.entity.event.dto.EventResponseDto;
import com.happiday.Happi_Day.domain.entity.event.dto.EventUpdateDto;
import com.happiday.Happi_Day.domain.entity.event.Event;
import com.happiday.Happi_Day.domain.entity.user.User;
import com.happiday.Happi_Day.domain.repository.EventRepository;
import com.happiday.Happi_Day.domain.repository.UserRepository;
import com.happiday.Happi_Day.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final FileUtils fileUtils;

    @Transactional
    public EventResponseDto createEvent(
            EventCreateDto request, MultipartFile thumbnailFile, MultipartFile imageFile, String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // 이미지 로직 추가
        String thumbnailUrl = fileUtils.uploadFile(thumbnailFile);
        String imageUrl = fileUtils.uploadFile(imageFile);

        Event event = request.toEntity(thumbnailUrl, imageUrl);
        eventRepository.save(event);
        return EventResponseDto.fromEntity(event);
    }

    public  List<EventListResponseDto> readEvents() {
        return eventRepository.findAll().stream()
                .map(EventListResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public EventResponseDto readEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return EventResponseDto.fromEntity(event);
    }

    @Transactional
    public EventResponseDto updateEvent(
            Long eventId, EventUpdateDto request, MultipartFile thumbnailFile, MultipartFile imageFile, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(!user.getUsername().equals(username)) throw new IllegalArgumentException("사용자 정보가 일치하지 않습니다.");

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // 이미지 업로드 추가
        if (thumbnailFile != null && !thumbnailFile.isEmpty()) {
            fileUtils.deleteFile(event.getThumbnailUrl());
            String newThumbnailUrl = fileUtils.uploadFile(thumbnailFile);
            event.setThumbnailUrl(newThumbnailUrl);
        }

        if (imageFile != null && !imageFile.isEmpty()) {
            fileUtils.deleteFile(event.getImageUrl());
            String newImageUrl = fileUtils.uploadFile(imageFile);
            event.setImageUrl(newImageUrl);
        }

        event.update(request.toEntity());
        eventRepository.save(event);
        return EventResponseDto.fromEntity(event);
    }

    @Transactional
    public void deleteEvent(Long eventId, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(!user.getUsername().equals(username)) throw new IllegalArgumentException("사용자 정보가 일치하지 않습니다.");

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        eventRepository.delete(event);
    }
}
