package com.happiday.Happi_Day.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="user_event_participation")
// 유저 이벤트 참여하기 Join Table
public class UserEventParticipation {
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
