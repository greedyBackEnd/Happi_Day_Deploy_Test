package com.happiday.Happi_Day.domain.entity.Board;

import com.happiday.Happi_Day.domain.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "board")
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private BoardCategory category;

    // 아티스트 id
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name ="artist_id")
//    private Artist artist;

    // 팀 id
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "team_id")
//    private Team team;

    @NotNull
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    // 게시글 매핑
//    @OneToMany(mappedBy = "article")
//    private List<Article> articles = new ArrayList<>();

}
