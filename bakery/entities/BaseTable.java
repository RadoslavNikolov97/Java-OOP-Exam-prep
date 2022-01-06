package bakery.entities;

import bakery.common.ExceptionMessages;
import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseTable implements Table {
    private int tableNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;
    private Collection<BakedFood> foodOrders;
    private Collection<Drink> drinkOrders;

    public BaseTable(int tableNumber, int capacity, double pricePerPerson) {
        this.tableNumber = tableNumber;
        setCapacity(capacity);
        setPricePerPerson(pricePerPerson);
        foodOrders = new ArrayList<>();
        drinkOrders = new ArrayList<>();
    }




    public void setCapacity(int capacity) {
        if (capacity <= 0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TABLE_CAPACITY);
        }
        this.capacity = capacity;
    }

    public void setPricePerPerson(double pricePerPerson) {
        if (pricePerPerson <= 0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }
        this.pricePerPerson = pricePerPerson;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public int getTableNumber() {
        return tableNumber;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    @Override
    public double getPricePerPerson() {
        return pricePerPerson;
    }

    @Override
    public boolean isReserved() {
        return isReserved;
    }

    @Override
    public double getPrice() {
        return price;
    }

    protected void setPrice(double price){
        this.price = price;
    }

    @Override
    public void reserve(int numberOfPeople) {
        setNumberOfPeople(numberOfPeople);
        isReserved = true;
    }

    @Override
    public void orderFood(BakedFood food) {
        foodOrders.add(food);
    }

    @Override
    public void orderDrink(Drink drink) {
        drinkOrders.add(drink);
    }

    @Override
    public double getBill() {

        setPrice(numberOfPeople);

        return  foodOrders.stream().mapToDouble(BakedFood::getPrice).sum() +
                drinkOrders.stream().mapToDouble(Drink::getPrice).sum() + getPrice();
    }

    @Override
    public void clear() {
        foodOrders.clear();
        drinkOrders.clear();
        setNumberOfPeople(0);
        isReserved = false;
        setPrice(0);


    }

    @Override
    public String getFreeTableInfo() {

        StringBuilder sb = new StringBuilder();
        sb.append("Table: ").append(getTableNumber()).append(System.lineSeparator());
        sb.append("Type: ").append(getClass().getSimpleName()).append(System.lineSeparator());
        sb.append("Capacity: ").append(getCapacity()).append(System.lineSeparator());
        sb.append(String.format("Price per Person: %.2f",getPricePerPerson())).append(System.lineSeparator());
        return sb.toString().trim();
    }

    protected abstract void setPrice(int numberOfPeople);
}
