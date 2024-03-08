package com.example.sakila.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "film")
@Getter
@Setter
public class Film {

    public PartialFilm toPartialFilm(){
        return new PartialFilm(id, title, description, releaseYear, language, rentalDuration, rentalRate, replacementCost);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private short id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Date releaseYear;

    @Column(name = "language_id")
    private byte language;


    @Column(name = "rental_duration")
    private byte rentalDuration;

    @Column(name = "rental_rate")
    private BigDecimal rentalRate;

    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;

    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")}
    )
    //@JsonIgnore
    //@ToString.Exclude
    //@Setter(AccessLevel.NONE)
    private List<PartialActor> cast = new ArrayList<>();

}
