package bakery.entities;

public class Tea extends BaseDrink {

    private static final double price = 2.50;

    public Tea(String name, int portion, String brand) {
        super(name, portion, price, brand);
    }
}
