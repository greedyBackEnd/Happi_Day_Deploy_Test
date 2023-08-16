package com.happiday.Happi_Day.domain.service;

import com.happiday.Happi_Day.domain.entity.board.BoardCategory;
import com.happiday.Happi_Day.domain.entity.board.dto.CreateCategoryDto;
import com.happiday.Happi_Day.domain.repository.BoardCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardCategoryRepository categoryRepository;

    public void create(CreateCategoryDto dto){
        BoardCategory newCategory = new BoardCategory();
        newCategory.setName(dto.getName());
        newCategory.setDescription(dto.getDescription());

        categoryRepository.save(newCategory);
    }
}
