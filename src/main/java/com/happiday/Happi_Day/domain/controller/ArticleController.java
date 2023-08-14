package com.happiday.Happi_Day.domain.controller;

import com.happiday.Happi_Day.domain.entity.article.dto.ReadOneArticleDto;
import com.happiday.Happi_Day.domain.entity.article.dto.WriteFreeArticleDto;
import com.happiday.Happi_Day.domain.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    // TODO 글 작성 - 자유, 친목/동행
    @PostMapping("/freeFriend/write")
    public void writeFreeFriend(
            @RequestPart(name = "article") WriteFreeArticleDto requestDto,
            @RequestPart(name ="artists") String artists,
            @RequestPart(name = "teams") String teams){
        articleService.wirteFreeArticle(requestDto,artists, teams);
    }

    // TODO 글 상세 조회
    @GetMapping("/{articleId}")
    public ReadOneArticleDto readOne(@PathVariable("articleId") Long id){
        return articleService.readOne(id);
    }
}
