package com.example.moviemingle.services.movies;

import com.example.moviemingle.dtos.movies.MovieInDTO;
import com.example.moviemingle.dtos.movies.MovieOutDTO;
import org.springframework.data.domain.Page;

public interface MovieService {

    Page<MovieOutDTO> findAllMovies(Integer page, Integer size, String title, String actor, String director, String writer, Double minPrice, Double maxPrice);

    MovieOutDTO findMovieById(Long id);

    MovieOutDTO createMovie(MovieInDTO movieInDTO);

    MovieOutDTO updateMovie(Long id, MovieInDTO movieInDTO);

    void deleteMovie(Long id);
}

