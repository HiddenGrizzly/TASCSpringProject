package com.example.moviemingle.mappers;

import com.example.moviemingle.dtos.movies.MovieInDTO;
import com.example.moviemingle.dtos.movies.MovieOutDTO;
import com.example.moviemingle.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(source = "writers", target = "writers", qualifiedByName = "stringToWriters")
    @Mapping(source = "actors", target = "actors", qualifiedByName = "stringToActors")
    @Mapping(source = "directors", target = "directors", qualifiedByName = "stringToDirectors")
    @Mapping(source = "genres", target = "genres", qualifiedByName = "stringToGenres")
    Movie toEntity(MovieInDTO dto);

    @Mapping(source = "writers", target = "writers", qualifiedByName = "writersToString")
    @Mapping(source = "actors", target = "actors", qualifiedByName = "actorsToString")
    @Mapping(source = "directors", target = "directors", qualifiedByName = "directorsToString")
    @Mapping(source = "genres", target = "genres", qualifiedByName = "genresToString")
    MovieOutDTO toOutDto(Movie entity);

    @Named("stringToWriters")
    default Set<Writer> stringToWriters(Set<String> names) {
        return names.stream().map(name -> {
            Writer writer = new Writer();
            writer.setWriterName(name);
            return writer;
        }).collect(Collectors.toSet());
    }

    @Named("stringToActors")
    default Set<Actor> stringToActors(Set<String> names) {
        return names.stream().map(name -> {
            Actor actor = new Actor();
            actor.setActorName(name);
            return actor;
        }).collect(Collectors.toSet());
    }

    @Named("stringToDirectors")
    default Set<Director> stringToDirectors(Set<String> names) {
        return names.stream().map(name -> {
            Director director = new Director();
            director.setDirectorName(name);
            return director;
        }).collect(Collectors.toSet());
    }

    @Named("stringToGenres")
    default Set<Genre> stringToGenres(Set<String> names) {
        return names.stream().map(name -> {
            Genre genre = new Genre();
            genre.setGenreName(name);
            return genre;
        }).collect(Collectors.toSet());
    }

    @Named("writersToString")
    default Set<String> writersToString(Set<Writer> writers) {
        return writers.stream().map(Writer::getWriterName).collect(Collectors.toSet());
    }

    @Named("actorsToString")
    default Set<String> actorsToString(Set<Actor> actors) {
        return actors.stream().map(Actor::getActorName).collect(Collectors.toSet());
    }

    @Named("directorsToString")
    default Set<String> directorsToString(Set<Director> directors) {
        return directors.stream().map(Director::getDirectorName).collect(Collectors.toSet());
    }

    @Named("genresToString")
    default Set<String> genresToString(Set<Genre> genres) {
        return genres.stream().map(Genre::getGenreName).collect(Collectors.toSet());
    }
}
