package com.santos.texoit.controllers;

import java.util.List;
import java.util.Set;
import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.santos.texoit.entities.Producer;
import com.santos.texoit.representations.TopTailWinnersInterval;
import com.santos.texoit.representations.WinnerInterval;
import com.santos.texoit.services.impl.ProducerServiceImpl;

@RestController
@RequestMapping("/api/producers")
public class ProducerController {

	@Autowired
	private ProducerServiceImpl service;

	@GetMapping
	public ResponseEntity<List<Producer>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<Producer> find(@PathVariable("id") Long id) throws ValidationException {
		return ResponseEntity.ok(service.findById(id));
	}

	@PostMapping
	public ResponseEntity<Producer> create(@RequestBody Producer entity) {
		return ResponseEntity.ok(service.save(entity));
	}

	@PutMapping("{id}")
	public ResponseEntity<Producer> update(@RequestBody Producer entity, @PathVariable("id") Long id) throws ValidationException {
	    Producer producer = service.findById(id);
	    entity.setId(producer.getId());
	    entity.setMovies(producer.getMovies());
		return ResponseEntity.ok(service.save(entity));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Producer> delete(@PathVariable("id") Long id) throws ValidationException {
		service.removeById(id);
		return ResponseEntity.accepted().build();
	}

	@GetMapping("winners")
	public List<Producer> findWinners() {
		return service.getWinners();
	}

	@GetMapping("winners/intervals")
	public Set<WinnerInterval> findWinnersIntervals() {
		return service.getWinnersInterval();
	}

	@GetMapping("winners/intervals/top-tail-awards")
	public TopTailWinnersInterval findTopTailWinners() {
		return TopTailWinnersInterval.fromWinnersInterval(service.getWinnersInterval());
	}

}