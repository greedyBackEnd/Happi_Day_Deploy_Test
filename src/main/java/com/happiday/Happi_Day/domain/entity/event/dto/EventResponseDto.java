package com.happiday.Happi_Day.domain.entity.event.dto;

import com.happiday.Happi_Day.domain.entity.event.Event;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class EventResponseDto {

    private Long id;

    private String username;

    private String title;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String description;

    private String location;

    private String imageUrl;

    private List<String> artists;

    public static EventResponseDto fromEntity(Event event) {
        return EventResponseDto.builder()
                .id(event.getId())
                .username(event.getUser().getNickName())
                .title(event.getTitle())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .description(event.getDescription())
                .location(event.getLocation())
                .imageUrl(event.getImageUrl())
//                .artists(event.getArtists()) TODO 아티스트 네임 추가
                .build();
    }
}
