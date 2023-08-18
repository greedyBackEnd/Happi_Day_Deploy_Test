package com.happiday.Happi_Day.domain.entity.article;

import com.happiday.Happi_Day.domain.entity.BaseEntity;
import com.happiday.Happi_Day.domain.entity.artist.Artist;
import com.happiday.Happi_Day.domain.entity.board.BoardCategory;
import com.happiday.Happi_Day.domain.entity.team.Team;
import com.happiday.Happi_Day.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Table(name = "article")
public class Article extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 게시판 id
//    @ManyToMany
//    @JoinTable(
//            name = "article_board",
//            joinColumns = @JoinColumn(name = "article_id"),
//            inverseJoinColumns = @JoinColumn(name = "board_id")
//    )
//    private List<Board> boards  = new ArrayList<>();


    // 유저 id
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    // 카테고리 -홍보 에만 필요
    private String address;

    // 게시글 카테고리
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private BoardCategory category;

    // 댓글 매핑
    @OneToMany(mappedBy="article")
    private List<Comment> comments = new ArrayList<>();

    // 게시글 좋아요 매핑정
    @ManyToMany(mappedBy = "articleLikes")
    private List<User> likeUsers = new ArrayList<>();

    // 게시글 스크랩 매핑
    @ManyToMany(mappedBy = "articleScraps")
    private List<User> scrapUsers = new ArrayList<>();

    // 게시글 아티스트 매핑
    @ManyToMany(mappedBy ="articleArtists")
    private List<Artist> artists = new ArrayList<>();

    // 게시글 팀 매핑
    @ManyToMany(mappedBy = "articleTeams")
    private List<Team> teams = new ArrayList<>();

}
