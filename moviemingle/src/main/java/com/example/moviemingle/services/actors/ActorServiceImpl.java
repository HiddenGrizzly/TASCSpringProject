package com.example.moviemingle.services.actors;

import com.example.moviemingle.entities.Actor;
import com.example.moviemingle.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {
    @Autowired
    private ActorRepository actorRepository;

    @Override
    public Page<Actor> findAllActors(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return actorRepository.findAll(pageable);
    }

    @Override
    public Actor findActorById(Long id) {
        return actorRepository.findById(id).orElse(null);
    }

    @Override
    public Actor saveActor(String actorName) {
        Actor actor = new Actor();
        actor.setActorName(actorName);
        return actorRepository.save(actor);
    }

    @Override
    public Actor updateActor(Long id, String actorName) {
        Actor actor = findActorById(id);
        if (actor == null) {
            return null;
        }
        actor.setActorName(actorName);
        return actorRepository.save(actor);
    }

    @Override
    public void deleteActor(Long id) {
        actorRepository.deleteById(id);
    }

    @Override
    public Actor findOrCreateActor(String actorName) {
        Optional<Actor> actorOptional = actorRepository.findByActorName(actorName);
        return actorOptional.orElseGet(() -> {
            Actor newActor = new Actor();
            newActor.setActorName(actorName);
            return actorRepository.save(newActor);
        });
    }
}
