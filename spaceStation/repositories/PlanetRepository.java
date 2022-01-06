package spaceStation.repositories;

import spaceStation.models.planets.Planet;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlanetRepository implements Repository<Planet>{
    Map<String,Planet> planets;

    public PlanetRepository() {
        planets = new LinkedHashMap<>();
    }

    @Override
    public Collection<Planet> getModels() {
        return planets.values();
    }

    @Override
    public void add(Planet model) {
        planets.put(model.getName(),model);
    }

    @Override
    public boolean remove(Planet model) {
        return planets.remove(model.getName(),model);
    }

    @Override
    public Planet findByName(String name) {
        return planets.get(name);
    }
}
