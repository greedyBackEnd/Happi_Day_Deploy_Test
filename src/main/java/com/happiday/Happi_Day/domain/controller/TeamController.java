package com.happiday.Happi_Day.domain.controller;

import com.happiday.Happi_Day.domain.entity.artist.dto.ArtistListResponseDto;
import com.happiday.Happi_Day.domain.entity.team.dto.TeamRegisterDto;
import com.happiday.Happi_Day.domain.entity.team.dto.TeamResponseDto;
import com.happiday.Happi_Day.domain.entity.team.dto.TeamUpdateDto;
import com.happiday.Happi_Day.domain.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamResponseDto> registerTeam(@RequestPart(name = "team") TeamRegisterDto requestDto,
                                                        @RequestPart(value = "file", required = false) MultipartFile imageFile) {
        TeamResponseDto responseDto = teamService.registerTeam(requestDto, imageFile);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<TeamResponseDto> updateTeam(@PathVariable Long teamId,
                                                      @RequestPart(name = "team") TeamUpdateDto requestDto,
                                                      @RequestPart(value = "file", required = false) MultipartFile imageFile) {
        TeamResponseDto responseDto = teamService.updateTeam(teamId, requestDto, imageFile);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long teamId) {
        teamService.deleteTeam(teamId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamResponseDto> getTeam(@PathVariable Long teamId) {
        TeamResponseDto responseDto = teamService.getTeam(teamId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TeamResponseDto>> getTeams() {
        List<TeamResponseDto> responseDtos = teamService.getTeams();
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @GetMapping("/{teamId}/artists")
    public ResponseEntity<List<ArtistListResponseDto>> getArtistsByTeam(@PathVariable Long teamId) {
        List<ArtistListResponseDto> teamArtists = teamService.getTeamArtists(teamId);
        return new ResponseEntity<>(teamArtists, HttpStatus.OK);
    }
}