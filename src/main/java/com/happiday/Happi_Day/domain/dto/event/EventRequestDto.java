package com.happiday.Happi_Day.domain.dto.event;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class EventRequestDto {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "이벤트 시작일을 입력해주세요.")
    private LocalDateTime startTime;

    @NotBlank(message = "이벤트 종료일을 입력해주세요.")
    private LocalDateTime endTime;

    @NotBlank(message = "내용을 입력해주세요.")
    private String description;

    @NotBlank(message = "장소를 입력해주세요.")
    private String location;
}
