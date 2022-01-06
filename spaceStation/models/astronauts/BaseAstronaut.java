package spaceStation.models.astronauts;

import spaceStation.common.ExceptionMessages;
import spaceStation.models.bags.Backpack;
import spaceStation.models.bags.Bag;



public abstract class BaseAstronaut implements Astronaut{
    private String name;
    private double oxygen;
    private Backpack bag;
    private boolean canBreath = true;

    protected BaseAstronaut(String name, double oxygen) {
        setName(name);
        setOxygen(oxygen);
        bag = new Backpack() {};
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.ASTRONAUT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    private void setOxygen(double oxygen) {
        if (oxygen < 0){
            throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_OXYGEN_LESS_THAN_ZERO);
        }
        this.oxygen = oxygen;

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getOxygen() {
        return oxygen;
    }

    @Override
    public boolean canBreath() {
        return canBreath;
    }

    @Override
    public Bag getBag() {
        return bag;
    }

    @Override
    public void breath() {
        if (canBreath) {
            oxygen -= 10;
            if (oxygen <= 0) {
                oxygen = 0;
                canBreath = false;
            }
        }

    }
}
