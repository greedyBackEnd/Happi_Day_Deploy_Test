package com.happiday.Happi_Day.domain.service;

import com.happiday.Happi_Day.domain.entity.board.BoardCategory;
import com.happiday.Happi_Day.domain.entity.board.dto.CreateArticleCategoryDto;
import com.happiday.Happi_Day.domain.entity.product.SalesCategory;
import com.happiday.Happi_Day.domain.entity.product.dto.CreateSalesCategoryDto;
import com.happiday.Happi_Day.domain.repository.BoardCategoryRepository;
import com.happiday.Happi_Day.domain.repository.SalesCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
    private final BoardCategoryRepository boardCategoryRepository;
    private final SalesCategoryRepository salesCategoryRepository;

    @Transactional
    public BoardCategory createCategory(CreateArticleCategoryDto dto) {
        BoardCategory boardCategory = BoardCategory.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
        return boardCategoryRepository.save(boardCategory);
    }

    @Transactional
    public SalesCategory createSalesCategory(CreateSalesCategoryDto dto){
        SalesCategory salesCategory = SalesCategory.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
        return salesCategoryRepository.save(salesCategory);
    }

}
