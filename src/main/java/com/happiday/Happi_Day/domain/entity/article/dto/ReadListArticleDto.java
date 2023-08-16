package com.happiday.Happi_Day.domain.entity.article.dto;

import com.happiday.Happi_Day.domain.entity.user.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReadListArticleDto {
    private String title;
//    private User user;
    private LocalDateTime date;
}
