//package mk.ukim.finki.wp.labb.repository.impl;
//
//import mk.ukim.finki.wp.labb.bootstrap.DataHolder;
//import mk.ukim.finki.wp.labb.model.Dish;
//import mk.ukim.finki.wp.labb.repository.DishRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class InMemoryDishRepository implements DishRepository {
//    @Override
//    public List<Dish> findAll() {
//        return DataHolder.dishes;
//    }
//
//    @Override
//    public Dish findByDishId(String dishId) {
//        return DataHolder.dishes.stream().
//                filter(r->r.getDishId().equals(dishId)).
//                findFirst().orElseThrow(IllegalArgumentException::new);
//    }
//
//    @Override
//    public Optional<Dish> findById(Long id) {
//        return DataHolder.dishes.stream().filter(r->r.getId().equals(id)).findFirst();
//    }
//
//    @Override
//    public Dish save(Dish dish) {
//        DataHolder.dishes.removeIf(r->r.getId().equals(dish.getId()));
//        DataHolder.dishes.add(dish);
//        return dish;
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        DataHolder.dishes.removeIf(r->r.getId().equals(id));
//    }
//}
