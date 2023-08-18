package com.happiday.Happi_Day.domain.entity.event.dto;

import com.happiday.Happi_Day.domain.entity.artist.Artist;
import com.happiday.Happi_Day.domain.entity.event.Event;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class EventCreateDto {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "이벤트 시작일을 입력해주세요.")
    private LocalDateTime startTime;

    @NotBlank(message = "이벤트 종료일을 입력해주세요.")
    private LocalDateTime endTime;

    @NotBlank(message = "내용을 입력해주세요.")
    private String description;

    @NotBlank(message = "장소를 입력해주세요.")
    private String location;

    private String thumbnailUrl;

    private String imageUrl;

    @NotBlank(message = "아티스트를 입력해주세요")
    private List<String> artists;

    private List<String> teams;

    public Event toEntity() {
//        List<Artist> artistList = artists.stream()
//                .map(artistName -> artistRepository.findByName(artistName)
//                        .orElseThrow(() -> new EntityNotFoundException("Artist not found with name: " + artistName)))
//                .collect(Collectors.toList());
//
//        List<Artist> teamList = teams.stream()
//                .map(teamName -> teamRepository.findByName(teamName)
//                        .orElseThrow(() -> new EntityNotFoundException("Team not found with name: " + teamName)))
//                .collect(Collectors.toList());
//
        return Event.builder()
                .title(title)
//                .imageUrl()
//                .thumbnailUrl()
//                .artists(artistList) TODO 아티스트, 팀 추가
//                .teams(teamList)
                .startTime(startTime)
                .endTime(endTime)
                .description(description)
                .location(location)
                .build();
    }
}