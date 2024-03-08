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
public class PartialFilm {

    public PartialFilm(){

    }
    public PartialFilm(short id, String title, String description, Date releaseYear,
                       byte language, byte rentalDuration, BigDecimal rentalRate,
                       BigDecimal replacementCost){
        this.id = id;
        this.title = title;
         this.description = description;
         this.releaseYear = releaseYear;
         this.language = language;
         this.rentalDuration = rentalDuration;
         this.rentalRate = rentalRate;
         this.replacementCost = replacementCost;
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

}
