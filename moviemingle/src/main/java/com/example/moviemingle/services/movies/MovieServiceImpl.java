package com.example.moviemingle.services.movies;

import com.example.moviemingle.dtos.movies.MovieInDTO;
import com.example.moviemingle.dtos.movies.MovieOutDTO;
import com.example.moviemingle.entities.Movie;
import com.example.moviemingle.mappers.MovieMapper;
import com.example.moviemingle.repositories.MovieRepository;
import com.example.moviemingle.specifications.movies.MovieSpecifications;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public Page<MovieOutDTO> findAllMovies(Integer page, Integer size, String title, String actor, String director, String writer, Double minPrice, Double maxPrice) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<Movie> spec = Specification.where(null);

        if (title != null) {
            spec = spec.and(MovieSpecifications.titleContainsIgnoreCase(title));
        }
        if (actor != null) {
            spec = spec.and(MovieSpecifications.actorContainsIgnoreCase(actor));
        }
        if (director != null) {
            spec = spec.and(MovieSpecifications.directorContainsIgnoreCase(director));
        }
        if (writer != null) {
            spec = spec.and(MovieSpecifications.writerContainsIgnoreCase(writer));
        }
        if (minPrice != null && maxPrice != null) {
            spec = spec.and(MovieSpecifications.priceBetween(minPrice, maxPrice));
        }

        Page<Movie> moviePage = movieRepository.findAll(spec, pageable);
        return moviePage.map(movieMapper::toOutDto);
    }

    @Override
    public MovieOutDTO findMovieById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.map(movieMapper::toOutDto).orElse(null);
    }

    @Override
    @Transactional
    public MovieOutDTO createMovie(MovieInDTO movieInDTO) {
        Movie movie = movieMapper.toEntity(movieInDTO);
        movie = movieRepository.save(movie);
        return movieMapper.toOutDto(movie);
    }

    @Override
    @Transactional
    public MovieOutDTO updateMovie(Long id, MovieInDTO movieInDTO) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            Movie existingMovie = optionalMovie.get();
            existingMovie = movieMapper.toEntity(movieInDTO);
            existingMovie = movieRepository.save(existingMovie);
            return movieMapper.toOutDto(existingMovie);
        } else {
            return null;
        }
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}