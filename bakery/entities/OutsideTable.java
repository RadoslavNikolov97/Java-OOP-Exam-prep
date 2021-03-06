package bakery.entities;

public class OutsideTable extends BaseTable{
    private static final double pricePerPerson = 3.50;
    public OutsideTable(int tableNumber, int capacity) {
        super(tableNumber, capacity, pricePerPerson);
    }

    @Override
    protected void setPrice(int numberOfPeople) {
        double v = numberOfPeople * pricePerPerson;
        setPrice(v);
    }
}
