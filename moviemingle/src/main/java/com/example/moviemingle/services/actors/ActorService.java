package com.example.moviemingle.services.actors;

import com.example.moviemingle.entities.Actor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ActorService {
    public Page<Actor> findAllActors(int page, int size);

    public Actor findActorById(Long id);

    public Actor saveActor(String actorName);

    public Actor updateActor(Long id,String actorName);

    public void deleteActor(Long id);

    Actor findOrCreateActor(String actorName);
}
