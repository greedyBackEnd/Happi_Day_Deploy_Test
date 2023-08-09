package com.happiday.Happi_Day.domain.entity.team.dto;

import com.happiday.Happi_Day.domain.entity.team.Team;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeamResponseDto {
    private Long id;
    private String name;
    private String description;
    private String logoUrl;

    public static TeamResponseDto of(Team team) {
        return TeamResponseDto.builder()
                .id(team.getId())
                .name(team.getName())
                .description(team.getDescription())
                .logoUrl(team.getLogoUrl())
                .build();
    }
}
