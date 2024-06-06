package com.example.moviemingle.dtos.movies;

import com.example.moviemingle.entities.Actor;
import com.example.moviemingle.entities.Director;
import com.example.moviemingle.entities.Genre;
import com.example.moviemingle.entities.Writer;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class MovieDTO {

    private Long id;

    @NotBlank
    @Size(max = 255)
    private String movieTitle;

    @NotBlank
    @Size(max = 4)
    private String year;

    @NotBlank
    private String imdbId;

    @NotBlank
    private String rated;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate released;

    @NotBlank
    private String runtime;

    @NotBlank
    @Size(max = 1000)
    private String plot;

    @NotBlank
    @Size(max = 100)
    private String awards;

    @NotBlank
    @Size(max = 255)
    private String poster;

    @NotBlank
    @Size(max = 255)
    private String trailer;

    @NotNull
    private Double price;

    @NotNull
    @JsonProperty("writers")
    private Set<String> writers;

    @NotNull
    @JsonProperty("actors")
    private Set<String> actors;

    @NotNull
    @JsonProperty("directors")
    private Set<String> directors;

    @NotNull
    @JsonProperty("genres")
    private Set<String> genres;
}