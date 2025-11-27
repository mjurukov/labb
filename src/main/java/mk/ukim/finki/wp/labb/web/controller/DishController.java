package mk.ukim.finki.wp.labb.web.controller;

import mk.ukim.finki.wp.labb.model.Dish;
import mk.ukim.finki.wp.labb.repository.DishRepository;
import mk.ukim.finki.wp.labb.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }


    @GetMapping("/dishes")
    public String getDishesPage(@RequestParam(required = false) String error, Model model) {
        List<Dish> dishes = dishService.listDishes();
        model.addAttribute("dishes", dishes);
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "listDishes";
    }

    @GetMapping("/dishes/dish-form")
    public String getAddDishPage(Model model) {
        model.addAttribute("dish", null);
        return "dish-form";
    }

    @GetMapping("/dishes/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model) {
        try {
            Dish dish = dishService.findById(id);
            model.addAttribute("dish", dish);
            return "dish-form";
        } catch (Exception e) {
            return "redirect:/dishes?error=DishNotFound";
        }
    }


    @PostMapping("/dishes/add")
    public String saveDish(@RequestParam String dishId, @RequestParam String name, @RequestParam String cuisine, @RequestParam int preparationTime) {
        dishService.create(dishId, name, cuisine, preparationTime);
        return "redirect:/dishes";
    }

    @PostMapping("/dishes/edit/{id}")
    public String updateDish(@PathVariable Long id, @RequestParam String dishId, @RequestParam String name, @RequestParam String cuisine, @RequestParam int preparationTime) {
        dishService.update(id, dishId, name, cuisine, preparationTime);
        return "redirect:/dishes";
    }

    @GetMapping("/dishes/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.delete(id);
        return "redirect:/dishes";
    }
}
