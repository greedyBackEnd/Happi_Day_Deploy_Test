package com.happiday.Happi_Day.domain.repository;

import com.happiday.Happi_Day.domain.entity.artist.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
