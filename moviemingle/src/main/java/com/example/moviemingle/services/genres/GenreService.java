package com.example.moviemingle.services.genres;

import com.example.moviemingle.entities.Genre;
import com.example.moviemingle.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface GenreService {
    Page<Genre> findAllGenres(Pageable pageable);

    Genre findGenreById(Long id);

    Genre saveGenre(String genreName);

    Genre updateGenre(Long id, String genreName);

    void deleteGenre(Long id);

    Genre findOrCreateGenre(String genreName);
}
