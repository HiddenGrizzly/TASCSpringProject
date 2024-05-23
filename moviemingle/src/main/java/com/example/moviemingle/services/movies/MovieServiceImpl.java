package com.example.moviemingle.services.movies;

import com.example.moviemingle.dtos.movies.MovieInOutDTO;
import com.example.moviemingle.entities.Movie;
import com.example.moviemingle.mappers.MovieMapper;
import com.example.moviemingle.repositories.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieMapper movieMapper;

    public Page<MovieInOutDTO> findAllMovies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        return moviePage.map(movieMapper::toDto);
    }

    public MovieInOutDTO findMovieById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.map(movieMapper::toDto).orElse(null);
    }

    @Transactional
    public MovieInOutDTO saveMovie(MovieInOutDTO movieDTO) {
        Movie movie = movieMapper.toEntity(movieDTO);
        Movie savedMovie = movieRepository.save(movie);
        return movieMapper.toDto(savedMovie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}

