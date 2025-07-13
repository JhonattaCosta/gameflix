package com.gameflix.dto;

import com.gameflix.model.Category;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GameDTO {

    private Long id;
    private String name;
    private String description;
    private double rating;
    private LocalDate dateRelease;
    private List<CategoryDTO> categories;

}
