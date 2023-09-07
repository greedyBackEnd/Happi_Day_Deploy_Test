package com.happiday.Happi_Day.domain.service;

import com.happiday.Happi_Day.domain.entity.board.BoardCategory;
import com.happiday.Happi_Day.domain.entity.board.dto.CreateCategoryDto;
import com.happiday.Happi_Day.domain.repository.BoardCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
    private final BoardCategoryRepository boardCategoryRepository;

    @Transactional
    public BoardCategory createCategory(CreateCategoryDto dto) {
        BoardCategory boardCategory = BoardCategory.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
        return boardCategoryRepository.save(boardCategory);
    }
}
