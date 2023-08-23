package com.happiday.Happi_Day.domain.entity.event.dto;

import com.happiday.Happi_Day.domain.entity.artist.Artist;
import com.happiday.Happi_Day.domain.entity.event.Event;
import com.happiday.Happi_Day.domain.entity.team.Team;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    private List<String> teams;


    public static EventResponseDto fromEntity(Event event) {
        return EventResponseDto.builder()
                .id(event.getId())
                .username(event.getUser().getNickname())
                .title(event.getTitle())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .description(event.getDescription())
                .location(event.getLocation())
                .imageUrl(event.getImageUrl())
                .artists(event.getArtists().stream().map(Artist::getName).collect(Collectors.toList()))
                .teams(event.getTeams().stream().map(Team::getName).collect(Collectors.toList()))
                .build();
    }
}
