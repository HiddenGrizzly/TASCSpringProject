package com.example.moviemingle.services.movies;

import com.example.moviemingle.dtos.movies.MovieCreateDTO;
import com.example.moviemingle.dtos.movies.MovieDTO;
import com.example.moviemingle.dtos.movies.MovieOmdbDTO;
import com.example.moviemingle.entities.Movie;
import com.example.moviemingle.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {

    Page<MovieDTO> findAllMovies(Pageable pageable, String title, String actor, String director, String writer, String genre, Double minPrice, Double maxPrice);

    MovieDTO findMovieById(Long id);

    Movie createMovie(MovieDTO movieDTO);

    MovieDTO updateMovie(Long id, MovieDTO movieDTO);

    void deleteMovie(Long id);

    Movie creatMovieFromOmdb(MovieOmdbDTO movieOmdbDTO, MovieCreateDTO movieCreateDTO);
    
    List<MovieDTO> getMoviesByIds(List<Long> ids);
    
    Page<MovieDTO> getUserMovies(User user, Pageable pageable);
}

