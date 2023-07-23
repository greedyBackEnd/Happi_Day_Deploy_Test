package com.happiday.Happi_Day.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name ="event_comment")
public class EventComment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 유저와 관계 설정
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private UserEntity user;

    // 이벤트 게시글과 관계 설정
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "event_id")
//    private EventEntity event;

    @Column(columnDefinition = "TEXT")
    private String content;
}
