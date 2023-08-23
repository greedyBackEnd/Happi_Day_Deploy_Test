package com.happiday.Happi_Day.domain.entity.event.dto;

import com.happiday.Happi_Day.domain.entity.artist.Artist;
import com.happiday.Happi_Day.domain.entity.event.Event;
import com.happiday.Happi_Day.domain.entity.team.Team;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
                .username(event.getUser().getNickname())
                .title(event.getTitle())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .location(event.getLocation())
                .thumbnailUrl(event.getThumbnailUrl())
                .artists(event.getArtists().stream().map(Artist::getName).collect(Collectors.toList()))
                .teams(event.getTeams().stream().map(Team::getName).collect(Collectors.toList()))
                .build();
    }
}
