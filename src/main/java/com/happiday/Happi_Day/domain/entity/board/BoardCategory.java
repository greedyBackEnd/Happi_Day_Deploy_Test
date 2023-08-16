package com.happiday.Happi_Day.domain.entity.board;

import com.happiday.Happi_Day.domain.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Table(name = "board_category")
public class BoardCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<Board> boards = new ArrayList<>();

}
