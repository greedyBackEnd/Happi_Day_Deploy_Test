package com.happiday.Happi_Day.domain.entity.aticle;

import com.happiday.Happi_Day.domain.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "article")
public class Article extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 게시판 id
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="board_id")
//    private Board board;

    // 유저 id
//    @ManyToOne(fetch =FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;

    @NotNull
    private String title;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String content;

    // 댓글 매핑
    @NotNull
    @OneToMany(mappedBy="comment")
    private List<Comment> comments = new ArrayList<>();

    // 게시글 좋아요 매핑
    @NotNull
    @OneToMany(mappedBy="user_article_likes")
    private List<UserArticleLikes> userArticleLikes = new ArrayList<>();

    // 게시글 스크랩 매핑
    @NotNull
    @OneToMany(mappedBy="user_article_scrap")
    private List<UserArticleScrap> userArticleScraps = new ArrayList<>();

}
