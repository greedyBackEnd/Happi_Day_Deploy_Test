package com.happiday.Happi_Day.domain.repository;

import com.happiday.Happi_Day.domain.entity.article.dto.ReadListArticleDto;
import com.happiday.Happi_Day.domain.entity.board.Board;
import com.happiday.Happi_Day.domain.entity.board.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<ReadListArticleDto> findByCategory(BoardCategory category);
}
