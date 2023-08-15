package com.happiday.Happi_Day.domain.service;

import com.happiday.Happi_Day.domain.entity.artist.dto.ArtistListResponseDto;
import com.happiday.Happi_Day.domain.entity.team.Team;
import com.happiday.Happi_Day.domain.entity.team.dto.TeamListResponseDto;
import com.happiday.Happi_Day.domain.entity.team.dto.TeamRegisterDto;
import com.happiday.Happi_Day.domain.entity.team.dto.TeamDetailResponseDto;
import com.happiday.Happi_Day.domain.entity.team.dto.TeamUpdateDto;
import com.happiday.Happi_Day.domain.repository.TeamRepository;
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
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public TeamDetailResponseDto registerTeam(TeamRegisterDto requestDto, MultipartFile imageFile) {

        // TODO 이미지 저장 로직

        Team team = requestDto.toEntity();
        team = teamRepository.save(team);
        return TeamDetailResponseDto.of(team);
    }

    @Transactional
    public TeamDetailResponseDto updateTeam(Long teamId, TeamUpdateDto requestDto, MultipartFile imageFile) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team을 찾을 수 없습니다. " + teamId));

        // TODO 이미지 저장 로직

        team.update(requestDto.toEntity());
        teamRepository.save(team);

        return TeamDetailResponseDto.of(team);
    }

    @Transactional
    public void deleteTeam(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team을 찾을 수 없습니다. " + teamId));
        teamRepository.delete(team);
    }

    public TeamDetailResponseDto getTeam(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team을 찾을 수 없습니다. " + teamId));
        return TeamResponseDto.of(team);
    }

    public List<TeamListResponseDto> getTeams() {
        return teamRepository.findAll().stream()
                .map(TeamListResponseDto::of)
                .collect(Collectors.toList());
    }

    public List<ArtistListResponseDto> getTeamArtists(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team을 찾을 수 없습니다. " + teamId));

        return team.getArtists().stream()
                .map(ArtistListResponseDto::of)
                .collect(Collectors.toList());
    }
}
