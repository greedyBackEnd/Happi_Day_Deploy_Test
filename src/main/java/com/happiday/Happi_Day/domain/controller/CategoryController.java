package com.happiday.Happi_Day.domain.controller;

import com.happiday.Happi_Day.domain.entity.board.BoardCategory;
import com.happiday.Happi_Day.domain.entity.board.dto.CreateArticleCategoryDto;
import com.happiday.Happi_Day.domain.entity.product.SalesCategory;
import com.happiday.Happi_Day.domain.entity.product.dto.CreateSalesCategoryDto;
import com.happiday.Happi_Day.domain.repository.SalesCategoryRepository;
import com.happiday.Happi_Day.domain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final SalesCategoryRepository salesCategoryRepository;

    @PostMapping("/articleCategory")
    public ResponseEntity<BoardCategory> createArticleCategory(
            @RequestBody CreateArticleCategoryDto dto){
        BoardCategory responseCategory = categoryService.createCategory(dto);
        return new ResponseEntity<>(responseCategory, HttpStatus.CREATED);
    }

    @PostMapping("/salesCategory")
    public ResponseEntity<SalesCategory> createSalesCategory(
            @RequestBody CreateSalesCategoryDto dto){
        SalesCategory responseCategory = categoryService.createSalesCategory(dto);
        return new ResponseEntity<>(responseCategory, HttpStatus.CREATED);
    }

}
