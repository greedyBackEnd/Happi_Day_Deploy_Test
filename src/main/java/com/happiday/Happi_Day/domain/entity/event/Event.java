package com.happiday.Happi_Day.domain.entity.event;

import com.happiday.Happi_Day.domain.entity.BaseEntity;
import com.happiday.Happi_Day.domain.entity.artist.Artist;
import com.happiday.Happi_Day.domain.entity.team.Team;
import com.happiday.Happi_Day.domain.entity.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name ="event")
public class Event extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private String location;

    private String thumbnailUrl;

    private String imageUrl;

    // 이벤트 댓글 관계 설정
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private List<EventComment> comments;

    // 이벤트 좋아요 맵핑
    @ManyToMany
    @JoinTable(
            name = "event_like",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Event> likes = new ArrayList<>();

    // 이벤트 참여하기 맵핑
    @ManyToMany
    @JoinTable(
            name = "event_participation",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Event> participations = new ArrayList<>();

    // 이벤트 팀 매핑
    @ManyToMany(mappedBy = "events")
    private List<Team> teams = new ArrayList<>();

    // 이벤트 아티스트 매핑
    @ManyToMany(mappedBy = "events")
    private List<Artist> artists = new ArrayList<>();


}
