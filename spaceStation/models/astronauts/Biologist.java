package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut{
    private static double oxygen = 70;

    public Biologist(String name) {
        super(name, oxygen);
    }

    @Override
    public void breath() {
        oxygen -= 5;
    }
}
