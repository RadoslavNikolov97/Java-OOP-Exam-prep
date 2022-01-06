package bakery;

import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.repositories.interfaces.FoodRepository;

import java.util.ArrayList;
import java.util.Collection;


public class FoodRepositoryImpl implements FoodRepository<BakedFood> {

    Collection<BakedFood> bakedFoods;

    public FoodRepositoryImpl() {
        bakedFoods = new ArrayList<>();
    }

    @Override
    public BakedFood getByName(String name) {
        return bakedFoods.stream().filter(bakedFood -> bakedFood.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<BakedFood> getAll() {
        return bakedFoods;
    }

    @Override
    public void add(BakedFood bakedFood) {

        bakedFoods.add(bakedFood);

    }
}
