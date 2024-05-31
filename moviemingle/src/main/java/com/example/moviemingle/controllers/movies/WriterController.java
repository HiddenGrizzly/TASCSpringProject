package com.example.moviemingle.controllers.movies;

import com.example.moviemingle.entities.Actor;
import com.example.moviemingle.entities.Writer;
import com.example.moviemingle.models.pages.PageRes;
import com.example.moviemingle.services.writer.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/writers")
public class WriterController {

    @Autowired
    private WriterService writerService;

    @GetMapping("/")
    public ResponseEntity<PageRes<Writer>> getAllWriters(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Writer> writerPage = writerService.findAllWriters(pageable);
        return ResponseEntity.ok(new PageRes<>(writerPage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Writer> getWriterById(@PathVariable("id") Long id) {
        Writer writer = writerService.findWriterById(id);
        if (writer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(writer);
    }

    @PostMapping("/")
    public ResponseEntity<Writer> createWriter(@RequestParam String writerName) {
        Writer savedWriter = writerService.saveWriter(writerName);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWriter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Writer> updateWriter(
            @PathVariable("id") Long id,
            @RequestParam String writerName) {
        Writer updatedWriter = writerService.updateWriter(id, writerName);
        if (updatedWriter == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedWriter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWriter(@PathVariable("id") Long id) {
        writerService.deleteWriter(id);
        return ResponseEntity.noContent().build();
    }
}

