package com.happiday.Happi_Day.domain.entity.artist.dto;

import com.happiday.Happi_Day.domain.entity.artist.Artist;
import com.happiday.Happi_Day.domain.entity.artist.ArtistType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArtistDetailResponseDto {
    private Long id;
    private String name;
    private ArtistType type;
    private String description;
    private String profileUrl;
    private String nationality;

    public static ArtistDetailResponseDto of(Artist artist) {
        return ArtistDetailResponseDto.builder()
                .id(artist.getId())
                .name(artist.getName())
                .type(artist.getType())
                .description(artist.getDescription())
                .profileUrl(artist.getProfileUrl())
                .nationality(artist.getNationality())
                .build();
    }
}
