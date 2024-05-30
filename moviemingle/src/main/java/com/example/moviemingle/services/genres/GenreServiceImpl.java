package com.example.moviemingle.services.genres;

import com.example.moviemingle.entities.Genre;
import com.example.moviemingle.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Page<Genre> findAllGenres(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return genreRepository.findAll(pageable);
    }

    @Override
    public Genre findGenreById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }

    @Override
    public Genre saveGenre(String genreName) {
        Genre genre = new Genre();
        genre.setGenreName(genreName);
        return genreRepository.save(genre);
    }

    @Override
    public Genre updateGenre(Long id, String genreName) {
        Genre genre = findGenreById(id);
        if (genre == null) {
            return null;
        }
        genre.setGenreName(genreName);
        return genreRepository.save(genre);
    }

    @Override
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public Genre findOrCreateGenre(String genreName) {
        Optional<Genre> genreOptional = genreRepository.findByGenreName(genreName);
        return genreOptional.orElseGet(() -> {
            Genre newGenre = new Genre();
            newGenre.setGenreName(genreName);
            return genreRepository.save(newGenre);
        });
    }
}
