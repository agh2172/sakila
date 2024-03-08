package com.example.sakila.controllers;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import com.example.sakila.entities.PartialActor;
import com.example.sakila.input.FilmInput;
import com.example.sakila.input.FilmUpdate;
import com.example.sakila.repositories.ActorRespository;
import com.example.sakila.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class FilmController {
    @Autowired
    FilmRepository filmRepository;
    @Autowired
    ActorRespository actorRespository;

    @GetMapping("/films")
    public List<Film> listFilms(){
        return filmRepository.findAll();
    }

    @GetMapping("/films/{id}")
    public Film getFilmById(@PathVariable Short id){
        return filmRepository.findById(id).orElseThrow(() -> new ResourceAccessException("No Such Film"));
    }


    @PostMapping("/films")
    public Film createFilm(@Validated @RequestBody FilmInput data){
        final var film = new Film();
        film.setTitle(data.getTitle());
        film.setDescription(data.getDescription());
        film.setRentalRate(data.getRentalRate());
        film.setRentalDuration(data.getRentalDuration());
        film.setReplacementCost(data.getReplacementCost());
        film.setReleaseYear(data.getReleaseYear());
        film.setLanguage(data.getLanguage());
        List<Short> actors = data.getActors();
        List<PartialActor> currentActors = new ArrayList<PartialActor>();
        for(Short actorId : actors){
            final var actor = actorRespository.findById(actorId).orElseThrow(() -> new ResourceAccessException("No Such Actor"));
            PartialActor partialActor = actor.toPartialActor();
            currentActors.add(partialActor);
        }
        film.setCast(currentActors);
        return filmRepository.save(film);
    }

    @PatchMapping("/films")
    public Film updateFilm(@Validated @RequestBody FilmUpdate data){
        short id = data.getId();
        final var film = filmRepository.findById(id).orElseThrow(() -> new ResourceAccessException("No Such Film"));

        String title = data.getTitle();
        String description = data.getDescription();
        Date releaseYear = data.getReleaseYear();
        byte language = data.getLanguage();
        byte rentalDuration = data.getRentalDuration();
        BigDecimal rentalRate = data.getRentalRate();
        BigDecimal replacementCost = data.getReplacementCost();

        if(title != null) film.setTitle(title);
        if(description != null) film.setDescription(description);
        if(releaseYear != null) film.setReleaseYear(releaseYear);
        if(language != 0) film.setLanguage(language);
        if(rentalDuration != 0) film.setRentalDuration(rentalDuration);
        if(rentalRate != null) film.setRentalRate(rentalRate);
        if(replacementCost != null) film.setReplacementCost(replacementCost);

        List<Short> actors = data.getActors();
        if(!actors.isEmpty()) {
            List<PartialActor> currentActors = new ArrayList<PartialActor>();
            for (Short actorId : actors) {
                final var actor = actorRespository.findById(actorId).orElseThrow(() -> new ResourceAccessException("No Such Actor"));
                PartialActor partialActor = actor.toPartialActor();
                currentActors.add(partialActor);
            }
            film.setCast(currentActors);
        }
        return filmRepository.save(film);

    }

    @DeleteMapping("/films/{id}")
    public Film deleteFilm(@PathVariable Short id){
        final var film = filmRepository.findById(id).orElseThrow(() -> new ResourceAccessException("No Such Film"));
        filmRepository.deleteById(id);
        return film;
    }

    @GetMapping("/cast/{id}")
    public List<PartialActor> filmStars(@PathVariable Short id){
        final var film = filmRepository.findById(id).orElseThrow(() -> new ResourceAccessException("No Such Film"));
        return film.getCast();
    }

}
