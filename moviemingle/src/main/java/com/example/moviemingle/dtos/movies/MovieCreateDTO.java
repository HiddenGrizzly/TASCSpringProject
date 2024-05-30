package com.example.moviemingle.dtos.movies;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieCreateDTO {
    @NotBlank
    private String title;
    private String trailer;
    @DecimalMin("0.0")
    private Double price;
}
