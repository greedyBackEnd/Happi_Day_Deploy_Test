package com.happiday.Happi_Day.domain.repository;

import com.happiday.Happi_Day.domain.entity.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
