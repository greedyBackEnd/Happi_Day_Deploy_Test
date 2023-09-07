package com.happiday.Happi_Day.domain.entity.article.dto;

import com.happiday.Happi_Day.domain.entity.article.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WriteCommentDto {
    private String content;

    public Comment toEntity(){
        return Comment.builder()
                .content(content)
                .build();
    }
}
