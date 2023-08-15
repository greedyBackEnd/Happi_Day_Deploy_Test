package com.happiday.Happi_Day.domain.entity.team.dto;

import com.happiday.Happi_Day.domain.entity.team.Team;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeamDetailResponseDto {
    private Long id;
    private String name;
    private String description;
    private String logoUrl;

    public static TeamDetailResponseDto of(Team team) {
        return TeamDetailResponseDto.builder()
                .id(team.getId())
                .name(team.getName())
                .description(team.getDescription())
                .logoUrl(team.getLogoUrl())
                .build();
    }
}
