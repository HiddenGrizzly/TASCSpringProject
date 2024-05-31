package com.example.moviemingle.controllers.movies;

import com.example.moviemingle.entities.Actor;
import com.example.moviemingle.models.pages.PageRes;
import com.example.moviemingle.services.actors.ActorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Actor", description = "Actor controller")
@RestController("actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/")
    public ResponseEntity<PageRes<Actor>> getAllActors(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Actor> actorPage = actorService.findAllActors(pageable);
        return ResponseEntity.ok(new PageRes<>(actorPage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable("id") Long id) {
        Actor actor = actorService.findActorById(id);
        if (actor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(actor);
    }

    @PostMapping("/")
    public ResponseEntity<Actor> createActor(@RequestParam String actorName) {
        Actor savedActor = actorService.saveActor(actorName);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedActor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable("id") Long id, @RequestParam String actorName) {
        Actor updatedActor = actorService.updateActor(id, actorName);
        if (updatedActor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedActor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable("id") Long id) {
        actorService.deleteActor(id);
        return ResponseEntity.noContent().build();
    }
}
