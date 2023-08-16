package com.happiday.Happi_Day.domain.controller;

import com.happiday.Happi_Day.domain.entity.board.dto.CreateCategoryDto;
import com.happiday.Happi_Day.domain.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 카테고리 생성
    // 관리자만 접근 가능하도록
    @PostMapping("/category/create")
    public void createCate(@RequestPart(name="category")CreateCategoryDto createCategoryDto){
        boardService.create(createCategoryDto);
    }
}
