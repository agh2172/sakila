package com.example.sakila.controllers;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Category;
import com.example.sakila.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public List<Category> listActors(){
        return categoryRepository.findAll();
    }

    @GetMapping("/categories/{id}")
    public Category getActorById(@PathVariable Byte id){
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceAccessException("No Such Actor"));
    }


}
