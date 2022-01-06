package bakery.entities;

public class InsideTable extends BaseTable{
    private static final double pricePerPerson = 2.50;

    public InsideTable(int tableNumber, int capacity) {
        super(tableNumber, capacity, pricePerPerson);
    }

    @Override
    protected void setPrice(int numberOfPeople) {
        double v = numberOfPeople * pricePerPerson;
        setPrice(v);
    }
}
