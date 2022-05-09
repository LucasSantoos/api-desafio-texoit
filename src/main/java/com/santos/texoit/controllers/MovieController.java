package com.santos.texoit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.santos.texoit.entities.Movie;
import com.santos.texoit.services.impl.MovieServiceImpl;

@RestController
@RequestMapping(value = "/api/movies")
public class MovieController {

    @Autowired
    private MovieServiceImpl service;

    @GetMapping
    public ResponseEntity<List<Movie>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Movie> find(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Movie> create(@RequestBody Movie entity) {
        return ResponseEntity.ok(service.save(entity));
    }

    @PutMapping("{id}")
    public ResponseEntity<Movie> update(@RequestBody Movie entity, @PathVariable("id") Long id) {
        Movie producer = service.findById(id);
        entity.setId(producer.getId());
        return ResponseEntity.ok(service.save(entity));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Movie> delete(@PathVariable("id") Long id) {
        service.removeById(id);
        return ResponseEntity.accepted().build();
    }

}
