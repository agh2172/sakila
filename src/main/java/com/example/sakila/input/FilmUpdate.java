package com.example.sakila.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class FilmUpdate {
    private short id;

    @Size(min = 1, max = 128)
    private String title;

    private String description;

    private Date releaseYear;

    private byte rentalDuration;

    private BigDecimal rentalRate;

    private BigDecimal replacementCost;

    private byte language;

    private List<Short> actors;

}
