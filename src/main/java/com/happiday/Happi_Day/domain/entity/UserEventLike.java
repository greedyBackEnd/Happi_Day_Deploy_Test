package com.happiday.Happi_Day.domain.entity;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name ="user_event_like")
// 유저 이벤트 좋아요 Join Table
public class UserEventLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

}
