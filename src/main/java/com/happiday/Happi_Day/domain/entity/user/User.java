package com.happiday.Happi_Day.domain.entity.user;

import com.happiday.Happi_Day.domain.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Getter
@Entity
@Table(name = "users")
@EntityListeners(value = AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
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

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt; // 회원가입 날짜

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime updateAt; // 회원정보 수정날짜

    @AssertTrue
    @ColumnDefault("true")
    @Column(nullable = false)
    private Boolean isActive = true; // 활성화 상태구분(default => true)

    @AssertFalse
    @ColumnDefault("false")
    @Column(nullable = false)
    private Boolean isDeleted = false; // 탈퇴, 관리자에 의한 삭제 구분(default => false)

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastLoginAt; // 마지막 로그인 날짜

    // TODO 주문 매핑
    /*
         @ManyToMany
         @JoinTable(
            name = "order",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
         private List<Order> order = new ArrayList<>();
    */

    // TODO 장바구니 매핑
    /*
         @OneToMany(mappedBy = "product_id")
         private List<Cart> cart = new ArrayList<>();
    */

    // TODO 상품 매핑
    /*
         @ManyToMany
         @JoinTable(
            name = "product",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
         private List<Product> product = new ArrayList<>();
    */

    // TODO 상품 찜하기 매핑
    /*
         @ManyToMany
         @JoinTable(
            name = "user_product_likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
         private List<Product> product = new ArrayList<>();
    */

    // TODO 게시글 매핑
    /*
         @ManyToMany
         @JoinTable(
            name = "article",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "board_id"))
         private List<Article> article = new ArrayList<>();
    */

    // TODO 댓글 매핑
    /*
         @ManyToMany
         @JoinTable(
            name = "comment",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id"))
         private List<Comment> comment = new ArrayList<>();
    */

    // TODO 게시글 좋아요 매핑
    /*
         @ManyToMany
         @JoinTable(
            name = "user_article_likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id"))
         private List<Article> article = new ArrayList<>();
    */

    // TODO 게시글 스크랩 매핑
    /*
         @ManyToMany
         @JoinTable(
            name = "user_article_scrap",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id"))
         private List<Article> article = new ArrayList<>();
    */

    // TODO 유저-아티스트 구독 매핑
    /*
         @ManyToMany
         @JoinTable(
            name = "user_article_subscription",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id"))
         private List<Article> article = new ArrayList<>();
    */

    // TODO 유저-팀 구독 매핑
    /*
         @ManyToMany
         @JoinTable(
            name = "user_team_subscription",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
         private List<Team> team = new ArrayList<>();
    */

    // TODO 이벤트 매핑
    /*
         @ManyToMany
         @JoinTable(
            name = "event",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
         private List<Event> Event = new ArrayList<>();
    */

    // TODO 이벤트 댓글 매핑
    /*
         @ManyToMany
         @JoinTable(
            name = "event_comment",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
         private List<Event> Event = new ArrayList<>();
    */

    // TODO 유저-이벤트 참가하기 매핑
    /*
         @ManyToMany
         @JoinTable(
            name = "user_event_participation",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
         private List<Event> Event = new ArrayList<>();
    */

    // TODO 유저-이벤트 참가하기 매핑
    /*
         @ManyToMany
         @JoinTable(
            name = "user_event_likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
         private List<Event> Event = new ArrayList<>();
    */

    // TODO 채팅방 매핑
    /*
         @ManyToMany
         @JoinTable(
            name = "chat_room",
            joinColumns = @JoinColumn(name = "sender_id"),
            inverseJoinColumns = @JoinColumn(name = "receiver_id"))
         private List<ChatRoom> chatRoom = new ArrayList<>();
    */

    // TODO 채팅메세지 매핑
    /*
         @ManyToMany
         @JoinTable(
            name = "chat_message",
            joinColumns = @JoinColumn(name = "sender_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_room_id"))
         private List<ChatRoom> chatRoom = new ArrayList<>();
    */
}
