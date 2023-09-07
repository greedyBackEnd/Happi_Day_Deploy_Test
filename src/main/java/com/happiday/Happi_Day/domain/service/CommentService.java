package com.happiday.Happi_Day.domain.service;

import com.happiday.Happi_Day.domain.entity.article.Article;
import com.happiday.Happi_Day.domain.entity.article.Comment;
import com.happiday.Happi_Day.domain.entity.article.dto.ReadCommentDto;
import com.happiday.Happi_Day.domain.entity.article.dto.WriteCommentDto;
import com.happiday.Happi_Day.domain.repository.ArticleRepository;
import com.happiday.Happi_Day.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    @Transactional
    public ReadCommentDto writeComment(Long articleId, WriteCommentDto dto){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // TODO user 정보 저장 예정
        Comment newComment = Comment.builder()
                .content(dto.getContent())
                .article(article)
                .build();
        commentRepository.save(newComment);

        // TODO user 정보 저장 예정
        ReadCommentDto response = ReadCommentDto.fromEntity(newComment);
        return response;
    }

    public List<ReadCommentDto> readComment(Long articleId){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // TODO user 정보 저장 예정
        List<ReadCommentDto> comments = commentRepository.findAllByArticle(article);

        return comments;
    }

    @Transactional
    public ReadCommentDto updateComment(Long articleId, Long commentId, WriteCommentDto requestDto){
        articleRepository.findById(articleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        comment.update(requestDto.toEntity());
        commentRepository.save(comment);

        ReadCommentDto response = ReadCommentDto.fromEntity(comment);
        return response;
    }

    public void deleteComment(Long articleId, Long commentId){
        articleRepository.findById(articleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        commentRepository.deleteById(commentId);
    }


}
