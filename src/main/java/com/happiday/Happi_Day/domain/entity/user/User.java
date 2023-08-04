package com.happiday.Happi_Day.domain.entity.user;

import com.happiday.Happi_Day.domain.entity.BaseEntity;
import com.happiday.Happi_Day.domain.entity.article.Article;
import com.happiday.Happi_Day.domain.entity.article.Comment;
import com.happiday.Happi_Day.domain.entity.artist.Artist;
import com.happiday.Happi_Day.domain.entity.chat.ChatMessage;
import com.happiday.Happi_Day.domain.entity.chat.ChatRoom;
import com.happiday.Happi_Day.domain.entity.event.Event;
import com.happiday.Happi_Day.domain.entity.product.Order;
import com.happiday.Happi_Day.domain.entity.product.Sales;
import com.happiday.Happi_Day.domain.entity.team.Team;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "user")
@EntityListeners(value = AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 유저
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 유저 식별 ID

    // 제약사항 추가
    @Email
    @Column(nullable = false, unique = true)
    private String userEmail; // 이메일형식(중복 X)

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(nullable = false, unique = true)
    private String nickName; // 닉네임(중복 X)
    @Column(nullable = false)
    private String userName; // 실명

    @Column(nullable = false, unique = true)
    private String phoneNumber; // 전화번호(중복 X)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role; // 회원구분

    @Column(nullable = false)
    private Boolean isActive; // 활성화 상태구분(default => true)

    @Column(nullable = false)
    private Boolean isDeleted; // 탈퇴, 관리자에 의한 삭제 구분(default => false)

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastLoginAt; // 마지막 로그인 날짜

    // 활성화 상태구분 & 탈퇴, 관리자에 의한 삭제 구분
    @PrePersist
    public void prePersist() {
        this.isActive = this.isActive == null || this.isActive;
        this.isDeleted = this.isDeleted != null && this.isDeleted;
    }

    // 게시글 작성 매핑(Article) => 커뮤니티(자유게시글)
    @OneToMany(mappedBy = "user")
    private List<Article> articles = new ArrayList<>();

    // 게시글 좋아요 매핑
    @ManyToMany
    @JoinTable(
            name = "user_article_likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<Article> articleLikes = new ArrayList<>();

    // 게시글 스크랩 매핑
    @ManyToMany
    @JoinTable(
            name = "user_article_scrap",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<Article> articleScraps = new ArrayList<>();

    // 댓글 매핑
    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    // 주문 매핑
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    // 판매글 매핑
    @OneToMany(mappedBy = "users") // mappedBy user => users로 수정
    private List<Sales> salesList = new ArrayList<>();

    // 판매글 찜하기 매핑
    @ManyToMany
    @JoinTable(
            name = "user_sale_likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "sales_id")
    )
    private List<Sales> salesLikes = new ArrayList<>();

    // 이벤트 작성 매핑 => (별개)
    @OneToMany(mappedBy = "user")
    private List<Event> events = new ArrayList<>();

    // 이벤트 댓글 매핑
    @OneToMany(mappedBy = "user")
    private List<Event> eventComments = new ArrayList<>();

    // 유저-이벤트 참가하기 매핑
    @ManyToMany
    @JoinTable(
            name = "user_event_participation",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> eventJoinList = new ArrayList<>();

    // 유저-이벤트 좋아요 매핑
    @ManyToMany
    @JoinTable(
            name = "user_event_likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> eventLikes = new ArrayList<>();

    // 유저-아티스트 구독 매핑
    @ManyToMany
    @JoinTable(
            name = "user_artist_subscription",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<Artist> subscribedArtists = new ArrayList<>();

    // 유저-팀 구독 매핑
    @ManyToMany
    @JoinTable(
            name = "user_team_subscription",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> subscribedTeams = new ArrayList<>();

    // 채팅방 매핑
    @OneToMany(mappedBy = "sender") // mappedBy user => sender로 수정
    private List<ChatRoom> chatRooms = new ArrayList<>();

    // 채팅메세지 매핑
    @OneToMany(mappedBy = "sender") // mappedBy user => sender로 수정
    private List<ChatMessage> chatMessages = new ArrayList<>();
}

