package com.example.moviemingle.services.actors;

import com.example.moviemingle.entities.Actor;
import com.example.moviemingle.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl implements ActorService {
    @Autowired
    private ActorRepository actorRepository;

    public Page<Actor> findAllActors(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return actorRepository.findAll(pageable);
    }

    public Actor findActorById(Long id) {
        return actorRepository.findById(id).orElse(null);
    }

    public Actor saveActor(String actorName) {
        Actor actor = new Actor();
        actor.setActorName(actorName);
        return actorRepository.save(actor);
    }

    public void deleteActor(Long id) {
        actorRepository.deleteById(id);
    }
}
