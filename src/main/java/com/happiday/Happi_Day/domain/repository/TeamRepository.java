package com.happiday.Happi_Day.domain.repository;

import com.happiday.Happi_Day.domain.entity.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByName(String teamName);
}
