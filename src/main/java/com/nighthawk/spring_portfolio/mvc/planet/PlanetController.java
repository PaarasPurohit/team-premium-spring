package com.nighthawk.spring_portfolio.mvc.planet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/planets")
public class PlanetController {
    @Autowired
    private PlanetRepository planetRepository;

    // Get all planets
    @GetMapping
    public List<Planet> getAllPlanets() {
        return planetRepository.findAll();
    }

    // Get a single planet by ID
    @GetMapping("/{id}")
    public ResponseEntity<Planet> getPlanetById(@PathVariable Long id) {
        Optional<Planet> planet = planetRepository.findById(id);
        return planet.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new planet
    @PostMapping
    public ResponseEntity<Planet> addPlanet(@RequestBody Planet planet) {
        Planet savedPlanet = planetRepository.save(planet);
        return new ResponseEntity<>(savedPlanet, HttpStatus.CREATED);
    }

    // Update an existing planet
    @PutMapping("/{id}")
    public ResponseEntity<Planet> updatePlanet(@PathVariable Long id, @RequestBody Planet updatedPlanet) {
        Optional<Planet> existingPlanet = planetRepository.findById(id);
        if (existingPlanet.isPresent()) {
            updatedPlanet.setId(id);
            Planet savedPlanet = planetRepository.save(updatedPlanet);
            return new ResponseEntity<>(savedPlanet, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a planet by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanet(@PathVariable Long id) {
        Optional<Planet> planet = planetRepository.findById(id);
        if (planet.isPresent()) {
            planetRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
