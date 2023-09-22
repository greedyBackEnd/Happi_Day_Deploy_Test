package com.happiday.Happi_Day.domain.repository;

import com.happiday.Happi_Day.domain.entity.event.EventComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCommentRepository extends JpaRepository<EventComment, Long> {
}
