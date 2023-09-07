package com.happiday.Happi_Day.domain.entity.article.dto;

import com.happiday.Happi_Day.domain.entity.article.Article;
import com.happiday.Happi_Day.domain.entity.article.Comment;
import com.happiday.Happi_Day.domain.entity.article.Hashtag;
import com.happiday.Happi_Day.domain.entity.artist.Artist;
import com.happiday.Happi_Day.domain.entity.team.Team;
import com.happiday.Happi_Day.domain.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ReadOneArticleDto {
    private String title;
    private String content;
    private List<Team> teams;
    private List<Artist> artists;
    private List<String> hashtags;
    //    private User user;
    private String createdAt;
//    private List<Comment> comments;
//    private int likeUsersNum;
//    private int scrapUserNum;

    // TODO 유저, 댓글, 좋아요, 스크랩 추가예정
    public static ReadOneArticleDto fromEntity(Article article) {
        return ReadOneArticleDto.builder()
                .title(article.getTitle())
                .content(article.getContent())
                .teams(article.getTeams())
                .artists(article.getArtists())
                .build();
    }
}
