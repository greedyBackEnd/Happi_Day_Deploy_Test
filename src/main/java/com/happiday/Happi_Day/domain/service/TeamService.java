package com.happiday.Happi_Day.domain.service;

import com.happiday.Happi_Day.domain.entity.artist.dto.ArtistListResponseDto;
import com.happiday.Happi_Day.domain.entity.event.dto.EventListResponseDto;
import com.happiday.Happi_Day.domain.entity.product.dto.SalesListResponseDto;
import com.happiday.Happi_Day.domain.entity.team.Team;
import com.happiday.Happi_Day.domain.entity.team.dto.TeamListResponseDto;
import com.happiday.Happi_Day.domain.entity.team.dto.TeamRegisterDto;
import com.happiday.Happi_Day.domain.entity.team.dto.TeamDetailResponseDto;
import com.happiday.Happi_Day.domain.entity.team.dto.TeamUpdateDto;
import com.happiday.Happi_Day.domain.repository.TeamRepository;
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
public class TeamService {

    private final TeamRepository teamRepository;
    private final FileUtils fileUtils;

    @Transactional
    public TeamDetailResponseDto registerTeam(TeamRegisterDto requestDto, MultipartFile imageFile) {
        Team teamEntity = requestDto.toEntity();

        // 이미지 저장 로직
        if (imageFile != null && !imageFile.isEmpty()) {
            String saveFileUrl = fileUtils.uploadFile(imageFile);
            teamEntity.setLogoUrl(saveFileUrl);
        }

        teamEntity = teamRepository.save(teamEntity);
        return TeamDetailResponseDto.of(teamEntity);
    }

    @Transactional
    public TeamDetailResponseDto updateTeam(Long teamId, TeamUpdateDto requestDto, MultipartFile imageFile) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new CustomException(ErrorCode.TEAM_NOT_FOUND));

        // 이미지 저장 및 기존 이미지 삭제 로직
        if (imageFile != null && !imageFile.isEmpty()) {
            // 기존 이미지가 있다면 삭제
            if (team.getLogoUrl() != null && !team.getLogoUrl().isEmpty()) {
                try {
                    fileUtils.deleteFile(team.getLogoUrl());
                    log.info("이미지 삭제 완료: " + team.getLogoUrl());
                } catch (Exception e) {
                    log.error("이미지 삭제 실패: " + team.getLogoUrl(), e);
                    throw new CustomException(ErrorCode.FILE_DELETE_BAD_REQUEST);
                }
            }

            // 새로운 이미지 업로드
            String saveFileUrl = fileUtils.uploadFile(imageFile);
            team.setLogoUrl(saveFileUrl);
            log.info("이미지 업데이트: " + saveFileUrl);
        }

        team.update(requestDto.toEntity());
        teamRepository.save(team);

        return TeamDetailResponseDto.of(team);
    }

    @Transactional
    public void deleteTeam(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new CustomException(ErrorCode.TEAM_NOT_FOUND));
        teamRepository.delete(team);
    }

    public TeamDetailResponseDto getTeam(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new CustomException(ErrorCode.TEAM_NOT_FOUND));
        return TeamDetailResponseDto.of(team);
    }

    public List<TeamListResponseDto> getTeams() {
        return teamRepository.findAll().stream()
                .map(TeamListResponseDto::of)
                .collect(Collectors.toList());
    }

    public List<ArtistListResponseDto> getTeamArtists(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new CustomException(ErrorCode.TEAM_NOT_FOUND));

        return team.getArtists().stream()
                .map(ArtistListResponseDto::of)
                .collect(Collectors.toList());
    }

    public List<SalesListResponseDto> getSalesList(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new CustomException(ErrorCode.TEAM_NOT_FOUND));

        return team.getSalesList().stream()
                .map(SalesListResponseDto::of)
                .collect(Collectors.toList());
    }

    public List<EventListResponseDto> getEvents(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new CustomException(ErrorCode.TEAM_NOT_FOUND));

        return team.getEvents().stream()
                .map(EventListResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
