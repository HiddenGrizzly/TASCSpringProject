package com.example.moviemingle.dtos.movies;

import com.example.moviemingle.utils.CommaSeparatedStringToList;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieOmdbDTO {
    @JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private String year;

    @JsonProperty("imdbID")
    private String imdbId;

    @JsonProperty("Rated")
    private String rated;

    @JsonProperty("Released")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMM yyyy")
    private LocalDate released;

    @JsonProperty("Runtime")
    private String runtime;

    @JsonProperty("Plot")
    private String plot;

    @JsonProperty("Awards")
    private String awards;

    @JsonProperty("Poster")
    private String poster;

    @JsonProperty("Genres")
    @CommaSeparatedStringToList
    private Set<String> genres;

    @JsonProperty("Directors")
    @CommaSeparatedStringToList
    private Set<String> directors;

    @JsonProperty("Actors")
    @CommaSeparatedStringToList
    private Set<String> actors;

    @JsonProperty("Writers")
    @CommaSeparatedStringToList
    private Set<String> writers;

    @JsonProperty("Response")
    private Boolean response;
}
