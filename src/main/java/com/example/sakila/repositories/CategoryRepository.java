package com.example.sakila.repositories;

import com.example.sakila.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Byte> {

    //Optional<Category> findTopNameIgnoringCase(String name);

}
