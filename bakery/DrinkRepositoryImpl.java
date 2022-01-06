package bakery;

import bakery.entities.drinks.interfaces.Drink;
import bakery.repositories.interfaces.DrinkRepository;

import java.util.*;

public class DrinkRepositoryImpl implements DrinkRepository<Drink> {
    Collection<Drink> drinks;


    public DrinkRepositoryImpl() {
        drinks = new ArrayList<>();

    }

    @Override
    public Drink getByNameAndBrand(String drinkName, String drinkBrand) {
        return drinks.stream().filter(drink1 -> drink1.getName().equals(drinkName))
                .filter(drink1 -> drink1.getBrand().equals(drinkBrand)).findFirst().orElse(null);
    }

    @Override
    public Collection<Drink> getAll() {
        return drinks;
    }

    @Override
    public void add(Drink drink) {
        drinks.add(drink);
    }
}
