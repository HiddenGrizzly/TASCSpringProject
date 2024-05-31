package com.example.moviemingle.services.movies;

import com.example.moviemingle.dtos.movies.MovieCreateDTO;
import com.example.moviemingle.dtos.movies.MovieDTO;
import com.example.moviemingle.dtos.movies.MovieOmdbDTO;
import com.example.moviemingle.entities.Movie;
import com.example.moviemingle.exceptions.DuplicateTitleException;
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
    public Page<MovieDTO> findAllMovies(Pageable pageable, String title, String actor, String director, String writer, String genre, Double minPrice, Double maxPrice) {
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
        if (genre != null) {
            spec = spec.and(MovieSpecifications.genreEqualsIgnoreCase(genre));
        }
        if (minPrice != null && maxPrice != null) {
            spec = spec.and(MovieSpecifications.priceBetween(minPrice, maxPrice));
        }
        Page<Movie> moviePage = movieRepository.findAll(spec, pageable);
        return moviePage.map(movieMapper::toDto);
    }

    @Override
    public MovieDTO findMovieById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.map(movieMapper::toDto).orElse(null);
    }

    @Override
    @Transactional
    public Movie createMovie(MovieDTO movieDTO) {
        if (movieRepository.existsByMovieTitle(movieDTO.getMovieTitle())) {
            throw new DuplicateTitleException("Movie with title '" + movieDTO.getMovieTitle() + "' already exists.");
        }
        Movie movie = movieMapper.toEntity(movieDTO);
        movieRepository.save(movie);
        return movie;
    }

    @Override
    @Transactional
    public MovieDTO updateMovie(Long id, MovieDTO movieDTO) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            Movie existingMovie = optionalMovie.get();
            existingMovie = movieMapper.toEntity(movieDTO);
            existingMovie = movieRepository.save(existingMovie);
            return movieMapper.toDto(existingMovie);
        } else {
            return null;
        }
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Movie creatMovieFromOmdb(MovieOmdbDTO movieOmdbDTO, MovieCreateDTO movieCreateDTO){
        if (movieRepository.existsByMovieTitle(movieCreateDTO.getTitle())) {
            throw new DuplicateTitleException("Movie with title '" + movieCreateDTO.getTitle() + "' already exists.");
        }
        Movie movie = movieMapper.omdbToCreate(movieOmdbDTO);
        movie.setPrice(movieCreateDTO.getPrice());
        movie.setTrailer(movieCreateDTO.getTrailer());
        movie = movieRepository.save(movie);
        return movie;
    }
}