package com.happiday.Happi_Day.domain.controller;

import com.happiday.Happi_Day.domain.entity.board.BoardCategory;
import com.happiday.Happi_Day.domain.entity.board.dto.CreateCategoryDto;
import com.happiday.Happi_Day.domain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<BoardCategory> createCategory(
            @RequestBody CreateCategoryDto dto){
        BoardCategory responseCategory = categoryService.createCategory(dto);
        return new ResponseEntity<>(responseCategory, HttpStatus.CREATED);
    }
}
