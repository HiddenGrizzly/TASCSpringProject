package com.example.moviemingle.services.actors;

import com.example.moviemingle.entities.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ActorService {
    Page<Actor> findAllActors(Pageable pageable);

    Actor findActorById(Long id);

    Actor saveActor(String actorName);

    Actor updateActor(Long id, String actorName);

    void deleteActor(Long id);

    Actor findOrCreateActor(String actorName);
}
