package com.happiday.Happi_Day.domain.entity.article.dto;

import com.happiday.Happi_Day.domain.entity.article.Article;
import com.happiday.Happi_Day.domain.entity.board.BoardCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
public class WriteArticleDto {
    private String title;
    private String content;
    private String artists;
    private String teams;
    private String eventAddress;
    private String hashtag;

    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .eventAddress(eventAddress)
                .build();
    }

}
