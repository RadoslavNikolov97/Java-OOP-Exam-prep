package restaurant.entities;

public class Smoothie extends BaseBeverage {
    private static final double smoothiePrice = 4.50;
    public Smoothie(String name, int count, String brand) {
        super(name, count, smoothiePrice, brand);
    }
}
