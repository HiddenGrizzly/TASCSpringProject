package com.example.moviemingle.controllers.movies;

import com.example.moviemingle.entities.Director;
import com.example.moviemingle.services.directors.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("directors")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @GetMapping("/")
    public ResponseEntity<Page<Director>> getAllDirectors(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<Director> directorPage = directorService.findAllDirectors(page, size);
        return ResponseEntity.ok(directorPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Director> getDirectorById(@PathVariable("id") Long id) {
        Director director = directorService.findDirectorById(id);
        if (director == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(director);
    }

    @PostMapping("/")
    public ResponseEntity<Director> createDirector(@RequestParam String directorName) {
        Director savedDirector = directorService.saveDirector(directorName);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDirector);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Director> updateDirector(
            @PathVariable("id") Long id,
            @RequestParam String directorName) {
        Director updatedDirector = directorService.updateDirector(id, directorName);
        if (updatedDirector == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedDirector);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable("id") Long id) {
        directorService.deleteDirector(id);
        return ResponseEntity.noContent().build();
    }
}

