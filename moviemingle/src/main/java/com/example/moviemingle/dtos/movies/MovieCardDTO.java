package com.example.moviemingle.dtos.movies;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieCardDTO {

    private Long id;

    private String movieTitle;

    private String rated;

    private LocalDate released;

    private String runtime;

    private String poster;

    private Double price;

    private Set<String> genres;
}
