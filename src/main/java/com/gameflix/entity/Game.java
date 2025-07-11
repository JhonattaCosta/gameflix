package com.gameflix.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "games")
public class Game {

    private Long id;
    private String name;
    private String description;
    private LocalDate dateRelease;
}
