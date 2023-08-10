package com.happiday.Happi_Day.domain.entity.team.dto;

import com.happiday.Happi_Day.domain.entity.team.Team;
import lombok.Data;

@Data
public class TeamRegisterDto {
    private String name;
    private String description;

    public Team toEntity() {
        return Team.builder()
                .name(name)
                .description(description)
                .build();
    }
}
