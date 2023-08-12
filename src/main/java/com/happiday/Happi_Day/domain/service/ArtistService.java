package com.happiday.Happi_Day.domain.service;

import com.happiday.Happi_Day.domain.entity.artist.Artist;
import com.happiday.Happi_Day.domain.entity.artist.dto.ArtistListResponseDto;
import com.happiday.Happi_Day.domain.entity.artist.dto.ArtistRegisterDto;
import com.happiday.Happi_Day.domain.entity.artist.dto.ArtistDetailResponseDto;
import com.happiday.Happi_Day.domain.entity.artist.dto.ArtistUpdateDto;
import com.happiday.Happi_Day.domain.repository.ArtistRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Transactional
    public ArtistDetailResponseDto registerArtist(ArtistRegisterDto requestDto, MultipartFile imageFile) {

        // TODO 이미지 저장 로직

        Artist artistEntity = requestDto.toEntity();
        artistEntity = artistRepository.save(artistEntity);
        return ArtistDetailResponseDto.of(artistEntity);
    }

    @Transactional
    public ArtistDetailResponseDto updateArtist(Long artistId, ArtistUpdateDto requestDto, MultipartFile imageFile) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new EntityNotFoundException("Artist를 찾을 수 없습니다. : " + artistId));

        // TODO 이미지 저장 로직

        artist.update(requestDto.toEntity());
        artistRepository.save(artist);

        return ArtistDetailResponseDto.of(artist);
    }

    @Transactional
    public void delete(Long artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new EntityNotFoundException("Artist를 찾을 수 없습니다. " + artistId));
        artistRepository.delete(artist);
    }

    public ArtistDetailResponseDto getArtist(Long artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new EntityNotFoundException("Artist를 찾을 수 없습니다. " + artistId));
        return ArtistDetailResponseDto.of(artist);
    }

    public List<ArtistListResponseDto> getArtists() {
        return artistRepository.findAll().stream()
                .map(ArtistListResponseDto::of)
                .collect(Collectors.toList());
    }
}
