package com.happiday.Happi_Day.domain.entity.article.dto;

import com.happiday.Happi_Day.domain.entity.article.Article;
import com.happiday.Happi_Day.domain.entity.user.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReadListArticleDto {
    private String title;
    private String nickname;
    private LocalDateTime date;
    private String thumbnailUrl;

    // TODO 댓글, 좋아요, 스크랩 추가예정
    public static ReadListArticleDto fromEntity(Article article) {
        return ReadListArticleDto.builder()
                .nickname(article.getUser().getNickname())
                .title(article.getTitle())
                .date(article.getCreatedAt())
                .thumbnailUrl(article.getThumbnailUrl())
                .build();
    }
}
