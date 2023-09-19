package com.happiday.Happi_Day.domain.entity.article.dto;

import com.happiday.Happi_Day.domain.entity.article.Comment;
import com.happiday.Happi_Day.domain.entity.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class ReadCommentDto {
    private Long id;
    private User user;
    private String content;
    private LocalDateTime createdAt;

    public static ReadCommentDto fromEntity(Comment comment) {
        return ReadCommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    // TODO
    public static List<ReadCommentDto> toReadCommentDto(List<Comment> commentList){
        List<ReadCommentDto> newList = new ArrayList<>();
        for (Comment comment: commentList) {
            newList.add(ReadCommentDto.fromEntity(comment));
        }
        return newList;
    }

}
