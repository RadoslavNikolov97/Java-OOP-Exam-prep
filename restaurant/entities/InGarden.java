package restaurant.entities;

public class InGarden extends BaseTable{
    private static final double pricePerPerson = 4.50;

    public InGarden(int number, int size) {
        super(number, size, pricePerPerson);
    }

    @Override
    public double bill() {
        return super.bill() + (numberOfPeople() * pricePerPerson);
    }
}
