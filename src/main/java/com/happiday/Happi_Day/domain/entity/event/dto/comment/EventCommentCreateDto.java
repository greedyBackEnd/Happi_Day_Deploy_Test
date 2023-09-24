package com.happiday.Happi_Day.domain.entity.event.dto.comment;

import com.happiday.Happi_Day.domain.entity.event.EventComment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EventCommentCreateDto {

    public EventCommentCreateDto(){}

    public EventCommentCreateDto(String content) {
        this.content = content;
    }

    private String content;

    public EventComment toEntity(){
        return EventComment.builder()
                .content(content)
                .build();
    }
}
