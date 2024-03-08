package com.example.sakila.input;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ActorUpdate {
    private short id;

    @Size(min = 1, max = 45)
    private String firstName;

    @Size(min = 1, max = 45)
    private String lastName;

    private List<Short> films;
}
