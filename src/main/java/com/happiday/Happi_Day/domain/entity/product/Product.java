package com.happiday.Happi_Day.domain.entity.product;

import com.happiday.Happi_Day.domain.entity.BaseEntity;
import com.happiday.Happi_Day.domain.entity.artist.Artist;
import com.happiday.Happi_Day.domain.entity.team.Team;
import com.happiday.Happi_Day.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 카테고리 id
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="category_id", nullable = false)
//    private ProductCategory productCategory;

    // 판매자 id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private int price;

    private String image_url;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    // 상품 찜하기
    @ManyToMany
    @JoinTable(name="user_product_like",
        joinColumns = @JoinColumn(name="product_id"),
        inverseJoinColumns = @JoinColumn(name="user_id"))
    private List<User> like_users = new ArrayList<>();

    // 아티스트-상품 매핑
    @ManyToMany
    @JoinTable(name="artist_product",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="artist_id"))
    private List<Artist> artists = new ArrayList<>();

    // 팀-상품 매핑
    @ManyToMany
    @JoinTable(name="team_product",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="team_id"))
    private List<Team> teams = new ArrayList<>();
}
