package com.happiday.Happi_Day.domain.controller;

import com.happiday.Happi_Day.domain.entity.article.dto.ReadListArticleDto;
import com.happiday.Happi_Day.domain.entity.article.dto.ReadOneArticleDto;
import com.happiday.Happi_Day.domain.entity.article.dto.WriteArticleDto;
import com.happiday.Happi_Day.domain.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    // 글 작성
    @PostMapping(value ="/{categoryId}/write", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void writeArticle(
            @RequestPart(name="article") WriteArticleDto requestDto,
            @RequestPart(name="artists", required = false) String artists,
            @RequestPart(name="teams", required = false) String teams,
            @RequestPart(name="thumbnailImage", required = false)MultipartFile thumbnailImage,
            @RequestPart(name= "eventAddress", required = false)String address) throws IOException {
        articleService.writeArticle(requestDto, artists, teams, thumbnailImage, address);
    }

    // 글 상세 조회
    @GetMapping("/{articleId}")
    public ReadOneArticleDto readOne(@PathVariable("articleId") Long id){
        return articleService.readOne(id);
    }

    // 글 목록 조회
//    @GetMapping("{categoryId}")
//    public List<ReadListArticleDto> readList(
//            @PathVariable("categoryId") Long categoryId,
//            @RequestPart(name="filter", required = false) String filter){
//        return articleService.readList(categoryId, filter);
//    }

    // 글 수정
    @PutMapping("/{articleId}/update")
    public void updateArticle(
            @PathVariable("articleId") Long articleId,
            @RequestPart(name="article") WriteArticleDto requestDto,
            @RequestPart(name="artists", required = false) String artists,
            @RequestPart(name="teams", required = false) String teams,
            @RequestPart(name="thumbnailImage", required = false)MultipartFile thumbnailImage,
            @RequestPart(name= "eventAddress", required = false)String address) throws IOException {
        articleService.updateArticle(articleId, requestDto, artists, teams, thumbnailImage, address);
    }

    // 글 삭제
    @DeleteMapping("/{articleId}/delete")
    public void deleteArticle(
            @PathVariable("articleId") Long articleId) throws IOException {
        articleService.deleteArticle(articleId);
    }

}
