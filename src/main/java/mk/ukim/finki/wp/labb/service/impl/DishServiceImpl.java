package mk.ukim.finki.wp.labb.service.impl;

import mk.ukim.finki.wp.labb.model.Dish;
import mk.ukim.finki.wp.labb.repository.DishRepository;
import mk.ukim.finki.wp.labb.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Dish> listDishes() {
        return dishRepository.findAll();
    }

    @Override
    public Dish findByDishId(String dishId) {
        return dishRepository.findByDishId(dishId);
    }

    @Override
    public Dish findById(Long id) {
        return dishRepository.findById(id).orElseThrow();
    }

    @Override
    public Dish create(String dishId, String name, String cuisine, int preparationTime) {
        Dish dish =  new Dish(dishId, name, cuisine, preparationTime);
        return dishRepository.save(dish);
    }

    @Override
    public Dish update(Long id, String dishId, String name, String cuisine, int preparationTime) {
        Dish dish = findById(id);
        dish.setDishId(dishId);
        dish.setName(name);
        dish.setCuisine(cuisine);
        dish.setPreparationTime(preparationTime);
        return dishRepository.save(dish);
    }

    @Override
    public void delete(Long id) {
        dishRepository.deleteById(id);
    }
}
