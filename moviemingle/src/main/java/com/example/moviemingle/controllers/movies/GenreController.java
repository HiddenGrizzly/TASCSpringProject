package com.example.moviemingle.controllers.movies;

import com.example.moviemingle.entities.Actor;
import com.example.moviemingle.entities.Genre;
import com.example.moviemingle.models.pages.PageRes;
import com.example.moviemingle.services.genres.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/")
    public ResponseEntity<PageRes<Genre>> getAllGenres(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Genre> genrePage = genreService.findAllGenres(pageable);
        return ResponseEntity.ok(new PageRes<>(genrePage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable("id") Long id) {
        Genre genre = genreService.findGenreById(id);
        if (genre == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(genre);
    }

    @PostMapping("/")
    public ResponseEntity<Genre> createGenre(@RequestParam String genreName) {
        Genre savedGenre = genreService.saveGenre(genreName);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(
            @PathVariable("id") Long id,
            @RequestParam String genreName) {
        Genre updatedGenre = genreService.updateGenre(id, genreName);
        if (updatedGenre == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedGenre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable("id") Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}
