package com.example.sakila.services;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.PartialFilm;
import com.example.sakila.input.ActorInput;
import com.example.sakila.input.ActorUpdate;
import com.example.sakila.repositories.ActorRespository;
import com.example.sakila.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorService {

    @Autowired
    ActorRespository actorRespository;
    @Autowired
    FilmRepository filmRepository;
    public Actor getActorById(Short id){
        return actorRespository.findById(id).orElseThrow(() -> new ResourceAccessException("No Such Actor"));
    }

    public Actor createActorService(ActorInput data){
        final var actor = new Actor();
        actor.setFirstName(data.getFirstName());
        actor.setLastName(data.getLastName());
        List<Short> films = data.getFilms();
        List<PartialFilm> currentFilms = new ArrayList<>();
        for(Short filmId : films){
            //get film to add
            final var film = filmRepository.findById(filmId).orElseThrow(() -> new ResourceAccessException("No Such Film"));
            //add film
            PartialFilm partialFilm = film.toPartialFilm();
            currentFilms.add(partialFilm);
        }
        actor.setFilms(currentFilms);
        return actor;
    }

    public Actor updateActorService(ActorUpdate data){
        short id = data.getId();
        final var actor = actorRespository.findById(id).orElseThrow(() -> new ResourceAccessException("No Such Actor"));

        String firstName = data.getFirstName();
        String lastName = data.getLastName();

        if(firstName != null)  actor.setFirstName(firstName);
        if(lastName != null) actor.setLastName(lastName);
        List<Short> films = data.getFilms();
        if(!films.isEmpty()) {
            List<PartialFilm> currentFilms = new ArrayList<>();
            for (Short filmId : films) {
                //get film to add
                final var film = filmRepository.findById(filmId).orElseThrow(() -> new ResourceAccessException("No Such Film"));
                //add film
                PartialFilm partialFilm = film.toPartialFilm();
                currentFilms.add(partialFilm);
            }
            actor.setFilms(currentFilms);
        }
        return actor;
    }

    public List<PartialFilm> filmsStarredIn(Short id){
        final var actor = actorRespository.findById(id).orElseThrow(() -> new ResourceAccessException("No Such Actor"));
        return actor.getFilms();
    }

}
