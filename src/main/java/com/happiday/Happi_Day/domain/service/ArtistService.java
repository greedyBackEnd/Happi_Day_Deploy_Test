package com.happiday.Happi_Day.domain.service;

import com.happiday.Happi_Day.domain.entity.artist.Artist;
import com.happiday.Happi_Day.domain.entity.artist.dto.ArtistListResponseDto;
import com.happiday.Happi_Day.domain.entity.artist.dto.ArtistRegisterDto;
import com.happiday.Happi_Day.domain.entity.artist.dto.ArtistDetailResponseDto;
import com.happiday.Happi_Day.domain.entity.artist.dto.ArtistUpdateDto;
import com.happiday.Happi_Day.domain.entity.event.dto.EventListResponseDto;
import com.happiday.Happi_Day.domain.entity.team.dto.TeamListResponseDto;
import com.happiday.Happi_Day.domain.entity.product.dto.SalesListResponseDto;
import com.happiday.Happi_Day.domain.repository.ArtistRepository;
import com.happiday.Happi_Day.exception.CustomException;
import com.happiday.Happi_Day.exception.ErrorCode;
import com.happiday.Happi_Day.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final FileUtils fileUtils;

    @Transactional
    public ArtistDetailResponseDto registerArtist(ArtistRegisterDto requestDto, MultipartFile imageFile) {
        Artist artistEntity = requestDto.toEntity();

        // 이미지 저장 로직
        if (imageFile != null && !imageFile.isEmpty()) {
            String saveFileUrl = fileUtils.uploadFile(imageFile);
            artistEntity.setProfileUrl(saveFileUrl);
        }

        artistEntity = artistRepository.save(artistEntity);
        return ArtistDetailResponseDto.of(artistEntity);
    }

    @Transactional
    public ArtistDetailResponseDto updateArtist(Long artistId, ArtistUpdateDto requestDto, MultipartFile imageFile) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new CustomException(ErrorCode.ARTIST_NOT_FOUND));

        // 이미지 저장 및 기존 이미지 삭제 로직
        if (imageFile != null && !imageFile.isEmpty()) {
            // 기존 이미지가 있다면 삭제
            if (artist.getProfileUrl() != null && !artist.getProfileUrl().isEmpty()) {
                try {
                    fileUtils.deleteFile(artist.getProfileUrl());
                    log.info("이미지 삭제 완료: " + artist.getProfileUrl());
                } catch (Exception e) {
                    log.error("이미지 삭제 실패: " + artist.getProfileUrl(), e);
                    throw new CustomException(ErrorCode.FILE_DELETE_BAD_REQUEST);
                }
            }

            // 새로운 이미지 업로드
            String saveFileUrl = fileUtils.uploadFile(imageFile);
            artist.setProfileUrl(saveFileUrl);
            log.info("이미지 업데이트: " + saveFileUrl);
        }

        artist.update(requestDto.toEntity());
        artistRepository.save(artist);

        return ArtistDetailResponseDto.of(artist);
    }

    @Transactional
    public void delete(Long artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new CustomException(ErrorCode.ARTIST_NOT_FOUND));
        artistRepository.delete(artist);
    }

    public ArtistDetailResponseDto getArtist(Long artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new CustomException(ErrorCode.ARTIST_NOT_FOUND));
        return ArtistDetailResponseDto.of(artist);
    }

    public List<ArtistListResponseDto> getArtists() {
        return artistRepository.findAll().stream()
                .map(ArtistListResponseDto::of)
                .collect(Collectors.toList());
    }

    public List<TeamListResponseDto> getArtistTeams(Long artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new CustomException(ErrorCode.ARTIST_NOT_FOUND));

        return artist.getTeams().stream()
                .map(TeamListResponseDto::of)
                .collect(Collectors.toList());
    }

    public List<SalesListResponseDto> getSalesList(Long artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new CustomException(ErrorCode.ARTIST_NOT_FOUND));

        return artist.getSalesList().stream()
                .map(SalesListResponseDto::of)
                .collect(Collectors.toList());
    }

    public List<EventListResponseDto> getEvents(Long artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new CustomException(ErrorCode.ARTIST_NOT_FOUND));

        return artist.getEvents().stream()
                .map(EventListResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
