package com.happiday.Happi_Day.domain.entity.article.dto;

import com.happiday.Happi_Day.domain.entity.article.Article;
import com.happiday.Happi_Day.domain.entity.artist.Artist;
import com.happiday.Happi_Day.domain.entity.board.Board;
import lombok.Data;

import java.util.List;

@Data
public class WriteFreeArticleDto {
    private String title;
    private String content;

//    public Article toEntity() {
//        return Article.builder()
//                .title(title)
//                .content(content)
//                .build();
//    }
}
