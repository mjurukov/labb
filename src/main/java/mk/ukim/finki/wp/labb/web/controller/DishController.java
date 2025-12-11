package mk.ukim.finki.wp.labb.web.controller;

import mk.ukim.finki.wp.labb.model.Chef;
import mk.ukim.finki.wp.labb.model.Dish;
import mk.ukim.finki.wp.labb.service.ChefService;
import mk.ukim.finki.wp.labb.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DishController {

    private final DishService dishService;
    private final ChefService chefService;

    public DishController(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @GetMapping("/dishes")
    public String getDishesPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("dishes", dishService.listDishes());
        if (error != null) model.addAttribute("error", error);
        return "listDishes";
    }

    @GetMapping("/dishes/dish-form")
    public String getAddDishPage(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("chefs", chefService.listChefs());
        return "dish-form";
    }


    @GetMapping("/dishes/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("dish", dishService.findById(id));
            model.addAttribute("chefs", chefService.listChefs());
            return "dish-form";
        } catch (Exception e) {
            return "redirect:/dishes?error=DishNotFound";
        }
    }

    @PostMapping("/dishes/add")
    public String saveDish(@RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime,
                           @RequestParam Long chefId) {

        dishService.create(dishId, name, cuisine, preparationTime, chefId);
        return "redirect:/dishes";
    }

    @PostMapping("/dishes/edit/{id}")
    public String updateDish(@PathVariable Long id,
                             @RequestParam String dishId,
                             @RequestParam String name,
                             @RequestParam String cuisine,
                             @RequestParam int preparationTime,
                             @RequestParam Long chefId) {

        dishService.update(id, dishId, name, cuisine, preparationTime, chefId);
        return "redirect:/dishes";
    }

    @GetMapping("/dishes/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.delete(id);
        return "redirect:/dishes";
    }
}
