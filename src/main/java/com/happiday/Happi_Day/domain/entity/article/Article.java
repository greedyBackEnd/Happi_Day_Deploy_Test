package com.happiday.Happi_Day.domain.entity.article;

import com.happiday.Happi_Day.domain.entity.BaseEntity;
import com.happiday.Happi_Day.domain.entity.artist.Artist;
import com.happiday.Happi_Day.domain.entity.board.BoardCategory;
import com.happiday.Happi_Day.domain.entity.team.Team;
import com.happiday.Happi_Day.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.*;
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

    // 유저 id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Setter
    private String thumbnailUrl;

    @Setter
    @ElementCollection
    private List<String> imageUrl = new ArrayList<>();

    // 카테고리 홍보 에만 필요
    private String eventAddress;

    // 게시글 카테고리
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private BoardCategory category;

    // 댓글 매핑
    @OneToMany(mappedBy = "article")
    private List<Comment> comments = new ArrayList<>();

    // 게시글 좋아요 매핑
    @ManyToMany(mappedBy = "articleLikes")
    private List<User> likeUsers = new ArrayList<>();

    // 게시글 스크랩 매핑
    @ManyToMany(mappedBy = "articleScraps")
    private List<User> scrapUsers = new ArrayList<>();

    // 게시글 아티스트 매핑
    @ManyToMany
    @JoinTable(
            name = "article_artist",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<Artist> artists = new ArrayList<>();

    // 게시글 팀 매핑
    @ManyToMany
    @JoinTable(
            name = "article_team",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<Team> teams = new ArrayList<>();

    // 해시태그 매핑
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "article_hashtag",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_id")
    )
    private List<Hashtag> hashtags = new ArrayList<>();

    public void update(Article updateArticle) {
        if (updateArticle.getTitle() != null) this.title = updateArticle.getTitle();
        if (updateArticle.getContent() != null) this.content = updateArticle.getContent();
        if (updateArticle.getEventAddress() != null) this.eventAddress = updateArticle.getEventAddress();
        if (updateArticle.getArtists() != null) this.artists = updateArticle.getArtists();
        if (updateArticle.getTeams() != null) this.teams = updateArticle.getTeams();
        if (updateArticle.getHashtags() != null) this.hashtags = updateArticle.getHashtags();
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailUrl = thumbnailImage;
    }
}
