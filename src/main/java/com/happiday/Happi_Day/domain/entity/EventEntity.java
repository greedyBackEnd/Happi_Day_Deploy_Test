package com.happiday.Happi_Day.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name ="event")
public class EventEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private UserEntity user;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String location;

    private String thumbnailUrl;

    private String imageUrl;

}
