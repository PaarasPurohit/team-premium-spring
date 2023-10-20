package com.nighthawk.spring_portfolio.mvc.astronomy;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class AstronomyFavoritesController {

    private List<CelestialObject> favorites = new ArrayList<>();

    // Endpoint to add a celestial object to favorites
    @PostMapping("/add")
    public String addFavorite(@RequestBody CelestialObject celestialObject) {
        favorites.add(celestialObject);
        return "Added to favorites: " + celestialObject.getName();
    }

    // Endpoint to remove a celestial object from favorites by ID
    @DeleteMapping("/remove/{id}")
    public String removeFavorite(@PathVariable String id) {
        for (CelestialObject celestialObject : favorites) {
            if (celestialObject.getId().equals(id)) {
                favorites.remove(celestialObject);
                return "Removed from favorites: " + celestialObject.getName();
            }
        }
        return "Object with ID " + id + " not found in favorites.";
    }

    // Endpoint to get all favorite celestial objects
    @GetMapping("/all")
    public List<CelestialObject> getAllFavorites() {
        return favorites;
    }
}


//{
//  "name": "Favorite Celestial Object",
//  "id": "unique_id_for_fav"
//}
