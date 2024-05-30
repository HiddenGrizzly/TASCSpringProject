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

    @JsonProperty("Genre")
    @CommaSeparatedStringToList
    private List<String> genres;

    @JsonProperty("Director")
    @CommaSeparatedStringToList
    private List<String> directors;

    @JsonProperty("Actors")
    @CommaSeparatedStringToList
    private List<String> actors;

    @JsonProperty("Writer")
    @CommaSeparatedStringToList
    private List<String> writers;

    @JsonProperty("Ratings")
    private List<RatingDto> ratings;

    @JsonProperty("Language")
    @CommaSeparatedStringToList
    private List<String> language;

    @JsonProperty("Country")
    @CommaSeparatedStringToList
    private List<String> country;

    @JsonProperty("Metascore")
    private String metascore;


    @JsonProperty("Response")
    private Boolean response;
    // Nested class for Ratings
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RatingDto {
        @JsonProperty("Source")
        private String source;

        @JsonProperty("Value")
        private String value;
    }
}
