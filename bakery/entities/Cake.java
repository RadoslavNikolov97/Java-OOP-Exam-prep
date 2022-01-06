package bakery.entities;

public class Cake extends BaseFood {
    private static final double portion = 245;

    public Cake(String name, double price) {
        super(name, portion, price);
    }
}
