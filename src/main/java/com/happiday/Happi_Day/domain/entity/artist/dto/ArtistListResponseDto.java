package com.happiday.Happi_Day.domain.entity.artist.dto;

import com.happiday.Happi_Day.domain.entity.artist.Artist;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArtistListResponseDto {
    private Long id;
    private String name;
    private String profileUrl;

    public static ArtistListResponseDto of(Artist artist) {
        return ArtistListResponseDto.builder()
                .id(artist.getId())
                .name(artist.getName())
                .profileUrl(artist.getProfileUrl())
                .build();
    }
}
