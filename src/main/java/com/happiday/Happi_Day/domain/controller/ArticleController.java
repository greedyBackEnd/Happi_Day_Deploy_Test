package com.happiday.Happi_Day.domain.controller;

import com.happiday.Happi_Day.domain.entity.article.Article;
import com.happiday.Happi_Day.domain.entity.article.dto.ReadListArticleDto;
import com.happiday.Happi_Day.domain.entity.article.dto.ReadOneArticleDto;
import com.happiday.Happi_Day.domain.entity.article.dto.WriteArticleDto;
import com.happiday.Happi_Day.domain.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    @PostMapping(value ="/{categoryId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Article> writeArticle(
            @PathVariable("categoryId") Long id,
            @RequestPart(name="article") WriteArticleDto requestDto,
            @RequestPart(name="thumbnailImage", required = false)MultipartFile thumbnailImage) throws IOException {
        Article responseArticle = articleService.writeArticle(id, requestDto, thumbnailImage);
        return new ResponseEntity<>(responseArticle, HttpStatus.CREATED);
    }

    // 글 상세 조회
    @GetMapping("/{articleId}")
    public ResponseEntity<ReadOneArticleDto> readOne(@PathVariable("articleId") Long id){
        ReadOneArticleDto responseDto = articleService.readOne(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 글 목록 조회
    @GetMapping("/{categoryId}")
    public ResponseEntity<List<ReadListArticleDto>> readList(
            @PathVariable("categoryId") Long categoryId,
            @RequestPart(name="filter", required = false) String filter){
        List<ReadListArticleDto> responseArticles= articleService.readList(categoryId, filter);
        return new ResponseEntity<>(responseArticles, HttpStatus.OK);
    }

    // 글 수정
    @PutMapping("/{articleId}")
    public ResponseEntity<Article> updateArticle(
            @PathVariable("articleId") Long articleId,
            @RequestPart(name="article") WriteArticleDto requestDto,
            @RequestPart(name="thumbnailImage", required = false)MultipartFile thumbnailImage) throws IOException {
        Article responseArticle = articleService.updateArticle(articleId, requestDto, thumbnailImage);
        return new ResponseEntity<>(responseArticle, HttpStatus.OK);
    }

    // 글 삭제
    @DeleteMapping("/{articleId}")
    public ResponseEntity<String> deleteArticle(
            @PathVariable("articleId") Long articleId) throws IOException {
        articleService.deleteArticle(articleId);
        return new ResponseEntity<>("삭제 성공",HttpStatus.NO_CONTENT);
    }

}
