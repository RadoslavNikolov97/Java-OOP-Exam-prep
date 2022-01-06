package spaceStation.core;

import spaceStation.common.ConstantMessages;
import spaceStation.common.ExceptionMessages;
import spaceStation.models.astronauts.*;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    AstronautRepository astronautRepository;
    PlanetRepository planetRepository;
    private static int exploredPlanets = 0;

    public ControllerImpl() {
        planetRepository = new PlanetRepository();
        astronautRepository = new AstronautRepository();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;
        if (type.equals("Biologist")) {
            astronaut = new Biologist(astronautName);
        } else if (type.equals("Meteorologist")) {
            astronaut = new Meteorologist(astronautName);
        } else if (type.equals("Geodesist")) {
            astronaut = new Geodesist(astronautName);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_INVALID_TYPE);
        }
        astronautRepository.add(astronaut);

        return String.format(ConstantMessages.ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);
        planet.getItems().addAll(Arrays.stream(items).collect(Collectors.toList()));

        return String.format(ConstantMessages.PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        if (!astronautRepository.remove(astronautRepository.findByName(astronautName))) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }
        return String.format(ConstantMessages.ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        MissionImpl mission = new MissionImpl();
        Collection<Astronaut> astronauts = new ArrayList<>();
        for (Astronaut astronaut : astronautRepository.getModels()) {
            if (astronaut.getOxygen() >= 60) {
                astronauts.add(astronaut);
            }
        }
        int sizeOfTeam = astronauts.size();
        if (astronauts.size() <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }

        if (planetRepository.getModels().contains(planetRepository.findByName(planetName))) {
            mission.explore(planetRepository.findByName(planetName), astronauts);

        }
        exploredPlanets++;
        astronautRepository.getModels().removeAll(astronauts);

        return String.format(ConstantMessages.PLANET_EXPLORED, planetName, sizeOfTeam - astronauts.size());
    }

    @Override
    public String report() {

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%d planets were explored!", exploredPlanets)).append(System.lineSeparator());
        sb.append("Astronauts info:").append(System.lineSeparator());

        for (Astronaut astronaut : astronautRepository.getModels()) {
            sb.append("Name: ").append(astronaut.getName());
            sb.append(System.lineSeparator());
            sb.append("Oxygen: ").append(astronaut.getOxygen());
            sb.append(System.lineSeparator());
            if (astronaut.getBag().getItems().size() <= 0) {
                sb.append("none");
            } else {
                sb.append(String.join(ConstantMessages.REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER, astronaut.getBag().getItems()));
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
