package catHouse.entities.cat;

public class LonghairCat extends BaseCat{
    private static final int kilograms = 9;
    public LonghairCat(String name, String breed, double price) {
        super(name, breed, price, kilograms);
    }
    @Override
    public void eating() {
        int kilogramsAfterEating = getKilograms() + 3;
        setKilograms(kilogramsAfterEating);
    }
}
