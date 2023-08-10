package com.happiday.Happi_Day.domain.entity.team;

import com.happiday.Happi_Day.domain.entity.artist.Artist;
import com.happiday.Happi_Day.domain.entity.board.Board;
import com.happiday.Happi_Day.domain.entity.event.Event;
import com.happiday.Happi_Day.domain.entity.product.Sales;
import com.happiday.Happi_Day.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder(toBuilder = true)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private String logoUrl;

    @ManyToMany(mappedBy = "teams")
    private List<Artist> artists = new ArrayList<>();

    // 이벤트
    @ManyToMany(mappedBy = "teams")
    private List<Event> events = new ArrayList<>();

    // 판매글
    @ManyToMany
    @JoinTable(
            name = "team_sales",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "sales_id")
    )
    private List<Sales> salesList = new ArrayList<>();

    // 유저 구독
    @ManyToMany(mappedBy = "subscribedTeams")
    private List<User> subscribers = new ArrayList<>();

    // 게시판
    @OneToMany(mappedBy = "team")
    private List<Board> boards = new ArrayList<>();

    public void update(Team teamUpdate) {
        this.name  = teamUpdate.getName();
        this.description = teamUpdate.getDescription();
        this.logoUrl =teamUpdate.getLogoUrl();
    }
}
