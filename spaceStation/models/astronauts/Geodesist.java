package spaceStation.models.astronauts;

public class Geodesist extends BaseAstronaut{
    private static double oxygen = 50;

    public Geodesist(String name) {
        super(name, oxygen);
    }
}
