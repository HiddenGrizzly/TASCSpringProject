package com.example.moviemingle.services.movies;

import com.example.moviemingle.dtos.movies.MovieCreateDTO;
import com.example.moviemingle.dtos.movies.MovieDTO;
import com.example.moviemingle.dtos.movies.MovieOmdbDTO;
import com.example.moviemingle.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {

    Page<MovieDTO> findAllMovies(Pageable pageable, String title, String actor, String director, String writer, Double minPrice, Double maxPrice);

    MovieDTO findMovieById(Long id);

    Movie createMovie(MovieDTO movieDTO);

    MovieDTO updateMovie(Long id, MovieDTO movieDTO);

    void deleteMovie(Long id);

    Movie creatMovieFromOmdb(MovieOmdbDTO movieOmdbDTO, MovieCreateDTO movieCreateDTO);
}

