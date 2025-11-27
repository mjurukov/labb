package mk.ukim.finki.wp.labb.service.impl;

import mk.ukim.finki.wp.labb.model.Chef;
import mk.ukim.finki.wp.labb.model.Dish;
import mk.ukim.finki.wp.labb.repository.ChefRepository;
import mk.ukim.finki.wp.labb.repository.DishRepository;
import mk.ukim.finki.wp.labb.service.ChefService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {

    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    public ChefServiceImpl(ChefRepository chefRepository, DishRepository dishRepository) {
        this.chefRepository = chefRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Chef> listChefs() {
        return chefRepository.findAll();
    }

    @Override
    public Chef findById(Long id) {
        return chefRepository.findById(id).orElseThrow();
    }

    @Override
    public Chef addDishToChef(Long chefId, String dishId) {
        Chef chef = findById(chefId);
        List<Dish> dishes = chef.getDishes();
        Dish dish = dishRepository.findByDishId(dishId);
        if (chef != null && dish != null) {
            dishes.add(dish);
            chef.setDishes(dishes);
            chefRepository.save(chef);
        }
        return chef;
    }
}
