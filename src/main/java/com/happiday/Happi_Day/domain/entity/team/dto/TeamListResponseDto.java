package com.happiday.Happi_Day.domain.entity.team.dto;

import com.happiday.Happi_Day.domain.entity.team.Team;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeamListResponseDto {
    private Long id;
    private String name;
    private String logoUrl;

    public static TeamListResponseDto of(Team team) {
        return TeamListResponseDto.builder()
                .id(team.getId())
                .name(team.getName())
                .logoUrl(team.getLogoUrl())
                .build();
    }
}
