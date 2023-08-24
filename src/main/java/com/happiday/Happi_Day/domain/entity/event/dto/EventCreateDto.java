package com.happiday.Happi_Day.domain.entity.event.dto;

import com.happiday.Happi_Day.domain.entity.artist.Artist;
import com.happiday.Happi_Day.domain.entity.event.Event;
import com.happiday.Happi_Day.domain.entity.team.Team;
import com.happiday.Happi_Day.domain.repository.ArtistRepository;
import com.happiday.Happi_Day.domain.repository.TeamRepository;
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
    private final ArtistRepository artistRepository;
    private final TeamRepository teamRepository;

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
    private List<Artist> artists;

    private List<Team> teams;

    public Event toEntity(String defaultThumbnailUrl, String defaultImageUrl) {
        // TODO - defaultImageUrl 추가
        if (thumbnailUrl == null || thumbnailUrl.isEmpty()) {
            thumbnailUrl = defaultThumbnailUrl;
        }
        if (imageUrl == null || imageUrl.isEmpty()) {
            imageUrl = defaultImageUrl;
        }

        return Event.builder()
                .title(title)
                .imageUrl(imageUrl)
                .thumbnailUrl(thumbnailUrl)
                .artists(artists)
                .teams(teams)
                .startTime(startTime)
                .endTime(endTime)
                .description(description)
                .location(location)
                .build();
    }
}
