package restaurant.entities;

public class Salad extends Food{

    private static final int InitialSaladPortion = 150;

    public Salad(String name, double price) {
        super(name,InitialSaladPortion, price);
    }
}
