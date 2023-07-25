package com.happiday.Happi_Day.domain.entity.team;

import com.happiday.Happi_Day.domain.entity.artist.Artist;
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

    // TODO 이벤트 매핑
    /*
    @ManyToMany
    @JoinTable(
            name = "team_event",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events = new ArrayList<>();
     */

    // TODO 상품 매핑
    /*
    @ManyToMany
    @JoinTable(
            name = "team_product",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();
     */
}
