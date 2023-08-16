package com.happiday.Happi_Day.domain.entity.article.dto;

import lombok.Data;

@Data
public class WriteArticleDto {
    private String title;
    private String content;

//    public Article toEntity() {
//        return Article.builder()
//                .title(title)
//                .content(content)
//                .build();
//    }
}
