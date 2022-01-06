package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;

import java.util.Collection;

public class MissionImpl implements Mission{
    private PlanetImpl planet;
    private AstronautRepository astronauts;


    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        for (Astronaut astronaut : astronauts ) {
            if (astronaut.canBreath()) {

                for (String item : planet.getItems()) {
                    astronaut.getBag().getItems().add(item);
                    astronaut.breath();
                    if (!astronaut.canBreath()){
                        for (String itemInAstronaut :
                                astronaut.getBag().getItems()) {
                            planet.getItems().remove(itemInAstronaut);
                       }
                        astronauts.remove(astronaut);
                    }
                }
            }
        }
    }
}
