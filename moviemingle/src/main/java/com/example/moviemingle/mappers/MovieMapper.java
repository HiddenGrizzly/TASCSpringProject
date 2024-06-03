package com.example.moviemingle.mappers;

import com.example.moviemingle.dtos.movies.MovieDTO;
import com.example.moviemingle.dtos.movies.MovieOmdbDTO;
import com.example.moviemingle.entities.Actor;
import com.example.moviemingle.entities.Director;
import com.example.moviemingle.entities.Genre;
import com.example.moviemingle.entities.Movie;
import com.example.moviemingle.entities.Writer;
import com.example.moviemingle.services.actors.ActorService;
import com.example.moviemingle.services.directors.DirectorService;
import com.example.moviemingle.services.genres.GenreService;
import com.example.moviemingle.services.writer.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MovieMapper {

    private final ActorService actorService;
    private final DirectorService directorService;
    private final WriterService writerService;
    private final GenreService genreService;

    @Autowired
    public MovieMapper(ActorService actorService, DirectorService directorService, WriterService writerService, GenreService genreService) {
        this.actorService = actorService;
        this.directorService = directorService;
        this.writerService = writerService;
        this.genreService = genreService;
    }

    public MovieDTO toDto(Movie movie) {
        if (movie == null) {
            return null;
        }

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setMovieTitle(movie.getMovieTitle());
        movieDTO.setYear(movie.getYear());
        movieDTO.setImdbId(movie.getImdbId());
        movieDTO.setReleased(movie.getReleased());
        movieDTO.setRuntime(movie.getRuntime());
        movieDTO.setPlot(movie.getPlot());
        movieDTO.setAwards(movie.getAwards());
        movieDTO.setPoster(movie.getPoster());
        movieDTO.setTrailer(movie.getTrailer());
        movieDTO.setPrice(movie.getPrice());
        movieDTO.setActors(movie.getActors().stream().map(Actor::getActorName).collect(Collectors.toSet()));
        movieDTO.setDirectors(movie.getDirectors().stream().map(Director::getDirectorName).collect(Collectors.toSet()));
        movieDTO.setWriters(movie.getWriters().stream().map(Writer::getWriterName).collect(Collectors.toSet()));
        movieDTO.setGenres(movie.getGenres().stream().map(Genre::getGenreName).collect(Collectors.toSet()));

        return movieDTO;
    }

    public Movie toEntity(MovieDTO movieDTO) {
        if (movieDTO == null) {
            return null;
        }
        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setMovieTitle(movieDTO.getMovieTitle());
        movie.setYear(movie.getYear());
        movie.setImdbId(movieDTO.getImdbId());
        movie.setReleased(movieDTO.getReleased());
        movie.setRuntime(movieDTO.getRuntime());
        movie.setPlot(movieDTO.getPlot());
        movie.setAwards(movieDTO.getAwards());
        movie.setPoster(movieDTO.getPoster());
        movie.setTrailer(movieDTO.getTrailer());
        movie.setPrice(movieDTO.getPrice());
        movie.setActors(convertStringSetToActorSet(movieDTO.getActors()));
        movie.setDirectors(convertStringSetToDirectorSet(movieDTO.getDirectors()));
        movie.setWriters(convertStringSetToWriterSet(movieDTO.getWriters()));
        movie.setGenres(convertStringSetToGenreSet(movieDTO.getGenres()));
        return movie;
    }

    private Set<Actor> convertStringSetToActorSet(Set<String> actorNames) {
        return actorNames.stream()
                .map(actorService::findOrCreateActor)
                .collect(Collectors.toSet());
    }

    private Set<Director> convertStringSetToDirectorSet(Set<String> directorNames) {
        return directorNames.stream()
                .map(directorService::findOrCreateDirector)
                .collect(Collectors.toSet());
    }

    private Set<Writer> convertStringSetToWriterSet(Set<String> writerNames) {
        return writerNames.stream()
                .map(writerService::findOrCreateWriter)
                .collect(Collectors.toSet());
    }

    private Set<Genre> convertStringSetToGenreSet(Set<String> genreNames) {
        return genreNames.stream()
                .map(genreService::findOrCreateGenre)
                .collect(Collectors.toSet());
    }

    public Movie omdbToCreate(MovieOmdbDTO movieOmdbDTO) {
        if (movieOmdbDTO == null) {
            return null;
        }

        Movie movie = new Movie();
        movie.setMovieTitle(movieOmdbDTO.getTitle());
        movie.setYear(movieOmdbDTO.getYear());
        movie.setImdbId(movieOmdbDTO.getImdbId());
        movie.setRated(movieOmdbDTO.getRated());
        movie.setReleased(movieOmdbDTO.getReleased());
        movie.setRuntime(movieOmdbDTO.getRuntime());
        movie.setPlot(movieOmdbDTO.getPlot());
        movie.setAwards(movieOmdbDTO.getAwards());
        movie.setPoster(movieOmdbDTO.getPoster());
        movie.setTrailer(null);
        movie.setPrice(null);

        // Set genres
        movie.setGenres(convertToGenreEntities(movieOmdbDTO.getGenres()));

        // Set directors
        movie.setDirectors(convertToDirectorEntities(movieOmdbDTO.getDirectors()));

        // Set actors
        movie.setActors(convertToActorEntities(movieOmdbDTO.getActors()));

        // Set writers
        movie.setWriters(convertToWriterEntities(movieOmdbDTO.getWriters()));

        return movie;
    }

    private Set<Genre> convertToGenreEntities(Set<String> genreNames) {
        Set<Genre> genres = new HashSet<>();
        if (genreNames != null) {
            for (String genreName : genreNames) {
                Genre genre = genreService.findOrCreateGenre(genreName);
                genres.add(genre);
            }
        }
        return genres;
    }

    private Set<Director> convertToDirectorEntities(Set<String> directorNames) {
        Set<Director> directors = new HashSet<>();
        if (directorNames != null) {
            for (String directorName : directorNames) {
                Director director = directorService.findOrCreateDirector(directorName);
                directors.add(director);
            }
        }
        return directors;
    }

    private Set<Actor> convertToActorEntities(Set<String> actorNames) {
        Set<Actor> actors = new HashSet<>();
        if (actorNames != null) {
            for (String actorName : actorNames) {
                Actor actor = actorService.findOrCreateActor(actorName);
                actors.add(actor);
            }
        }
        return actors;
    }

    private Set<Writer> convertToWriterEntities(Set<String> writerNames) {
        Set<Writer> writers = new HashSet<>();
        if (writerNames != null) {
            for (String writerName : writerNames) {
                Writer writer = writerService.findOrCreateWriter(writerName);
                writers.add(writer);
            }
        }
        return writers;
    }
}

