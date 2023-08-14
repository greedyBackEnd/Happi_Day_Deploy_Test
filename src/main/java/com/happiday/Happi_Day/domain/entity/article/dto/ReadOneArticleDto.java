package com.happiday.Happi_Day.domain.entity.article.dto;

import com.happiday.Happi_Day.domain.entity.article.Comment;
import com.happiday.Happi_Day.domain.entity.board.Board;
import com.happiday.Happi_Day.domain.entity.user.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReadOneArticleDto {
    private String title;
    private String content;
//    private User user;
    private String createdAt;
//    private List<Board> boards;
//    private List<Comment> comments;
//    private int likeUsersNum;
//    private int scrapUserNum;
}
