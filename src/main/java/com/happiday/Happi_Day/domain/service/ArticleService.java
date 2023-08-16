package com.happiday.Happi_Day.domain.service;

import com.happiday.Happi_Day.domain.entity.article.Article;
import com.happiday.Happi_Day.domain.entity.article.dto.ReadListArticleDto;
import com.happiday.Happi_Day.domain.entity.article.dto.ReadOneArticleDto;
import com.happiday.Happi_Day.domain.entity.article.dto.WriteArticleDto;
import com.happiday.Happi_Day.domain.entity.board.BoardCategory;
import com.happiday.Happi_Day.domain.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;
    private final BoardCategoryRepository categoryRepository;

    // 글 작성
    public void writeArticle(WriteArticleDto dto, String artists, String teams, MultipartFile image, String address) throws IOException {
        // 아티스트 목록
        List<String> artistList = Arrays.asList(artists.replace(" ", "").split("#"));
        // 팀 목록
        List<String> teamList = Arrays.asList(teams.replace(" ", "").split("#"));

        // TODO 정보 저장 : 유저, 댓글, 좋아요, 스크랩, 아티스트 리스트, 팀 리스트 추가예정
        Article newArticle = new Article();
        newArticle.setTitle(dto.getTitle());
        newArticle.setContent(dto.getContent());
        newArticle.setAddress(address);

        articleRepository.save(newArticle);

        if (image != null) {
            // 썸네일 이미지 저장
            Files.createDirectories(Path.of(String.format("image/%d", newArticle.getId())));
            Path path = Path.of(String.format("image/%d/thumbnail.png", newArticle.getId()));

            image.transferTo(path);
        }
    }


    // 글 상세 조회
    // TODO user, board, 댓글, 좋아요, 스크랩 정보 추가 예정
    public ReadOneArticleDto readOne(Long articleId) {
        Optional<Article> optionalArticle = articleRepository.findById(articleId);
        if (optionalArticle.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        ReadOneArticleDto dto = new ReadOneArticleDto();
        dto.setTitle(optionalArticle.get().getTitle());
        dto.setContent(optionalArticle.get().getContent());

        LocalDateTime createdDate = optionalArticle.get().getCreatedAt();

        String date = createdDate.getYear() + "년 " + createdDate.getMonthValue() + "월 " + createdDate.getDayOfMonth() + "일 " + createdDate.getHour() + ":" + createdDate.getMinute();
        dto.setCreatedAt(date);
        return dto;
    }

    // 글 목록 조회
//    public List<ReadListArticleDto> readList(Long categoryId, String filter) {
//        Optional<BoardCategory> optionalCategory = categoryRepository.findById(categoryId);
//        if (optionalCategory.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//
//        List<ReadListArticleDto> articles = boardRepository.findByCategory(optionalCategory.get());
//        // TODO 필터 적용 추가 예정
//
//        return articles;
//    }

    // 글 수정
    public void updateArticle(Long articleId, WriteArticleDto dto, String artists, String teams, MultipartFile image, String address) throws IOException {
        // 아티스트 목록
        List<String> artistList = Arrays.asList(artists.replace(" ", "").split("#"));
        // 팀 목록
        List<String> teamList = Arrays.asList(teams.replace(" ", "").split("#"));

        Optional<Article> optionalArticle = articleRepository.findById(articleId);
        if (optionalArticle.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        // TODO 아티스트 리스트, 팀 리스트 추가예정
        Article article = optionalArticle.get();
        if (dto.getTitle() != null) article.setTitle(dto.getTitle());
        if (dto.getContent() != null) article.setContent(dto.getContent());
        // 썸네일 이미지 수정
        if (image != null) {
            Path path = Path.of(String.format("image/%d/thumbnail.png", article.getId()));
            image.transferTo(path);
        }
        article.setAddress(address);

        articleRepository.save(article);
    }

    public void deleteArticle(Long articleId) throws IOException {
        Optional<Article> optionalArticle = articleRepository.findById(articleId);
        if (!optionalArticle.isEmpty()) {
            articleRepository.deleteById(articleId);
        }
    }
}
