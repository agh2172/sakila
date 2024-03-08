package com.example.sakila.repositories;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActorRespository extends JpaRepository<Actor, Short> {

    List<Actor> findByLastNameIgnoreCase(String lastName);

    List<Actor >findDistinctActorsByLastNameOrFirstName(String lastName, String firstName);

}
