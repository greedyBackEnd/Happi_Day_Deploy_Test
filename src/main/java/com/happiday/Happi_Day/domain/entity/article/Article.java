package com.happiday.Happi_Day.domain.entity.article;

import com.happiday.Happi_Day.domain.entity.BaseEntity;
import com.happiday.Happi_Day.domain.entity.board.Board;
import com.happiday.Happi_Day.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
    @ManyToMany
    @JoinTable(
            name = "article_board",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "board_id")
    )
    private List<Board> boards  = new ArrayList<>();


    // 유저 id
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    // 댓글 매핑
    @OneToMany(mappedBy="article")
    private List<Comment> comments = new ArrayList<>();

    // 게시글 좋아요 매핑
    @ManyToMany(mappedBy = "articleLikes")
    private List<User> likeUsers = new ArrayList<>();

    // 게시글 스크랩 매핑
    @ManyToMany(mappedBy = "articleScraps")
    private List<User> scrapUsers = new ArrayList<>();
}
