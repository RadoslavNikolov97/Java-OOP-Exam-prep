package restaurant.entities;

public class Fresh extends BaseBeverage {

    private static final double freshPrice = 3.50;

    public Fresh(String name, int count, String brand) {
        super(name, count, freshPrice, brand);
    }
}
