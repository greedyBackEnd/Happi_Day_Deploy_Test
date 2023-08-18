package com.happiday.Happi_Day.domain.entity.event.dto;

import com.happiday.Happi_Day.domain.entity.event.Event;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class EventListResponseDto {
    private Long id;

    private String username;

    private String title;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
    
    private String location;

    private String thumbnailUrl;

    private List<String> artists;

    private List<String> teams;

    public static EventListResponseDto fromEntity(Event event) {
        return EventListResponseDto.builder()
                .id(event.getId())
                .username(event.getUser().getNickName())
                .title(event.getTitle())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .location(event.getLocation())
                .thumbnailUrl(event.getThumbnailUrl())
//                .artists(event.getArtists()) TODO 아티스트 네임, 팀 네임 추가
//                .teams
                .build();
    }
}
