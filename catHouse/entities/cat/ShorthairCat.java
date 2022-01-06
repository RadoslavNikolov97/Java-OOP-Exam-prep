package catHouse.entities.cat;

public class ShorthairCat extends BaseCat{
    private static final int kilograms = 7;
    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, price, kilograms);
    }

    @Override
    public void eating() {
        int kilogramsAfterEating = getKilograms() + 1;
        setKilograms(kilogramsAfterEating);
    }

}
