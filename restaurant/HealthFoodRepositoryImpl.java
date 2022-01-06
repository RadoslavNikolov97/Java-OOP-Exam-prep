package restaurant;

import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.repositories.interfaces.HealthFoodRepository;

import java.util.*;

public class HealthFoodRepositoryImpl implements HealthFoodRepository<HealthyFood> {
    Map<String,HealthyFood> foods;

    public HealthFoodRepositoryImpl() {
        foods =  new LinkedHashMap<>();
    }

    @Override
    public HealthyFood foodByName(String name) {
        return foods.get(name);
    }

    @Override
    public Collection<HealthyFood> getAllEntities() {
        return foods.values();
    }

    @Override
    public void add(HealthyFood entity) {
        foods.put(entity.getName(), entity);

    }
}
