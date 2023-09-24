package com.happiday.Happi_Day.domain.entity.event.dto.comment;

import com.happiday.Happi_Day.domain.entity.event.EventComment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class EventCommentResponseDto {

    private Long id;
    private String username;
    private String content;
    private LocalDateTime updatedAt;

    public static EventCommentResponseDto fromEntity(EventComment comment) {
        return EventCommentResponseDto.builder()
                .id(comment.getId())
                .username(comment.getUser().getNickname())
                .content(comment.getContent())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }
}
