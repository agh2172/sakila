package com.example.sakila.controllers;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.PartialFilm;
import com.example.sakila.input.ActorInput;
import com.example.sakila.input.ActorUpdate;
import com.example.sakila.repositories.ActorRespository;
import com.example.sakila.repositories.FilmRepository;
import com.example.sakila.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ActorController {
    @Autowired
    ActorRespository actorRespository;
    @Autowired
    FilmRepository filmRepository;

    @Autowired
    ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService=actorService;
    }


    @GetMapping("/actors")
    public List<Actor> listActors(@RequestParam(required = false) String name){
        if(name == null) return actorRespository.findAll();
        return actorRespository.findDistinctActorsByLastNameOrFirstName(name, name);
    }

    @GetMapping("/actors/{id}")
    public Actor getActorById(@PathVariable Short id){
        return actorService.getActorById(id);
        //return actorRespository.findById(id).orElseThrow(() -> new ResourceAccessException("No Such Actor"));
    }


    @PostMapping("/actors")
    @ResponseStatus(HttpStatus.CREATED)
    public Actor createActor(@Validated @RequestBody ActorInput data){
        final var actor =  actorService.createActorService(data);
        return actorRespository.save(actor);

    }


    @PatchMapping("/actors")
    public Actor updateActor(@Validated @RequestBody ActorUpdate data){
//        short id = data.getId();
//        final var actor = actorRespository.findById(id).orElseThrow(() -> new ResourceAccessException("No Such Actor"));
//
//        String firstName = data.getFirstName();
//        String lastName = data.getLastName();
//
//        if(firstName != null)  actor.setFirstName(firstName);
//        if(lastName != null) actor.setLastName(lastName);
//        List<Short> films = data.getFilms();
//        if(!films.isEmpty()) {
//            List<PartialFilm> currentFilms = new ArrayList<PartialFilm>();
//            for (Short filmId : films) {
//                //get film to add
//                final var film = filmRepository.findById(filmId).orElseThrow(() -> new ResourceAccessException("No Such Film"));
//                //add film
//                PartialFilm partialFilm = film.toPartialFilm();
//                currentFilms.add(partialFilm);
//            }
//            actor.setFilms(currentFilms);
//        }
        final var actor = actorService.updateActorService(data);
        return actorRespository.save(actor);
    }

    @DeleteMapping("/actors/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteActor(@PathVariable Short id){
        final var actor = actorRespository.findById(id).orElseThrow(() -> new ResourceAccessException("No Such Actor"));
        actorRespository.deleteById(id);
    }

    @GetMapping("/starsIn/{id}")
    public List<PartialFilm> filmsStarredIn(@PathVariable Short id) {
//        final var actor = actorRespository.findById(id).orElseThrow(() -> new ResourceAccessException("No Such Actor"));
//        return actor.getFilms();
        return actorService.filmsStarredIn(id);
    }
}
