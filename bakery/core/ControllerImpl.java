package bakery.core;

import bakery.DrinkRepositoryImpl;
import bakery.FoodRepositoryImpl;
import bakery.TableRepositoryImpl;
import bakery.common.ExceptionMessages;
import bakery.common.OutputMessages;
import bakery.core.interfaces.Controller;
import bakery.entities.*;
import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.*;

public class ControllerImpl implements Controller {

    FoodRepository<BakedFood> foodRepository;
    DrinkRepository<Drink> drinkRepository;
    TableRepository<Table> tableRepository;
    private double allIncome = 0;

    public ControllerImpl(FoodRepository<BakedFood> foodRepository,DrinkRepository<Drink> drinkRepository,TableRepository<Table> tableRepository) {
       this.foodRepository = foodRepository;
       this.drinkRepository = drinkRepository;
       this.tableRepository = tableRepository;

    }


    @Override
    public String addFood(String type, String name, double price) {
        BakedFood food;
        if (type.equals("Bread")) {
            food = new Bread(name, price);
        } else if (type.equals("Cake")) {
            food = new Cake(name, price);
        } else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_OR_DRINK_EXIST, type, name));
        }
        foodRepository.add(food);

        return String.format(OutputMessages.FOOD_ADDED, name, type);
    }

    @Override
    public String addDrink(String type, String name, int portion, String brand) {
        Drink drink;
        if (type.equals("Tea")) {
            drink = new Tea(name, portion, brand);
        } else if (type.equals("Water")) {
            drink = new Water(name, portion, brand);
        } else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_OR_DRINK_EXIST, type, name));
        }
        drinkRepository.add(drink);
        return String.format(OutputMessages.DRINK_ADDED, name, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table;
        if (type.equals("InsideTable")) {
            table = new InsideTable(tableNumber, capacity);
        } else if (type.equals("OutsideTable")) {
            table = new OutsideTable(tableNumber, capacity);
        } else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.TABLE_EXIST, tableNumber));
        }
        tableRepository.add(table);


        return String.format(OutputMessages.TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserveTable(int numberOfPeople) {
        for (Table table :
                tableRepository.getAll()) {
            if (table.getCapacity() >= numberOfPeople) {
                if (!table.isReserved()) {
                    table.reserve(numberOfPeople);
                    return String.format(OutputMessages.TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
                }
            }
        }
        return String.format(OutputMessages.RESERVATION_NOT_POSSIBLE, numberOfPeople);

    }

    @Override
    public String orderFood(int tableNumber, String foodName) {
        Table tablePresent = tableRepository.getByNumber(tableNumber);

        if (tablePresent == null) {
            return String.format(OutputMessages.WRONG_TABLE_NUMBER, tableNumber);
        }
        BakedFood food = foodRepository.getByName(foodName);
        if (food == null) {
            return String.format(OutputMessages.NONE_EXISTENT_FOOD, foodName);
        }

        tablePresent.orderFood(food);

        return String.format(OutputMessages.FOOD_ORDER_SUCCESSFUL, tableNumber, foodName);
    }

    @Override
    public String orderDrink(int tableNumber, String drinkName, String drinkBrand) {
        Table tablePresent = tableRepository.getByNumber(tableNumber);
        if (tablePresent == null) {
            return String.format(OutputMessages.WRONG_TABLE_NUMBER, tableNumber);
        }
        Drink drink = drinkRepository.getByNameAndBrand(drinkName, drinkBrand);
        if (drink == null) {
            return String.format(OutputMessages.NON_EXISTENT_DRINK, drinkName, drinkBrand);
        }

        tablePresent.orderDrink(drink);

        return String.format(OutputMessages.DRINK_ORDER_SUCCESSFUL, tableNumber, drinkName, drinkBrand);

    }

    @Override
    public String leaveTable(int tableNumber) {
        Table table = tableRepository.getByNumber(tableNumber);
        double bill = table.getBill();
        table.clear();

        allIncome += bill;

        StringBuilder sb =new StringBuilder();
        sb.append(String.format("Table: %d", tableNumber)).append(System.lineSeparator());
        sb.append(String.format("Bill: %.2f", bill)).append(System.lineSeparator());


        return sb.toString();
    }

    @Override
    public String getFreeTablesInfo() {
        StringBuilder sb = new StringBuilder();

        for (Table table : tableRepository.getAll()) {
            if (!table.isReserved()) {
                sb.append(table.getFreeTableInfo()).append(System.lineSeparator());
            }
        }

        return sb.toString().trim();
    }

    @Override
    public String getTotalIncome() {

        return String.format("Total income: %.2flv", allIncome);
    }
}
