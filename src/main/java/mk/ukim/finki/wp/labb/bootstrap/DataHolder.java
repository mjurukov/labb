package mk.ukim.finki.wp.labb.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.labb.model.Chef;
import mk.ukim.finki.wp.labb.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();

    @PostConstruct
    public void init() {
        dishes = new ArrayList<>();
        dishes.add(new Dish("D1", "Beef Wellington", "British", 120));
        dishes.add(new Dish("D2", "Pasta Carbonara", "Italian", 30));
        dishes.add(new Dish("D3", "Tavche Gravche", "Macedonian", 60));
        dishes.add(new Dish("D4", "Risotto al Tartufo", "Italian", 45));
        dishes.add(new Dish("D5", "Fish and Chips", "British", 25));

        chefs = new ArrayList<>();
        chefs.add(new Chef(1L, "Gordon", "Ramsay", "World-famous British chef known for his fiery personality.", new ArrayList<>()));
        chefs.add(new Chef(2L, "Massimo", "Bottura", "Italian chef specializing in modern Italian cuisine.", new ArrayList<>()));
        chefs.add(new Chef(3L, "Nobu", "Matsuhisa", "Japanese chef and restaurateur known for fusion cuisine.", new ArrayList<>()));
        chefs.add(new Chef(4L, "Enrique", "Olvera", "Mexican chef celebrated for modern takes on traditional dishes.", new ArrayList<>()));
        chefs.add(new Chef(5L, "Argyro", "Barbarigou", "Greek chef famous for Mediterranean flavors and TV shows.", new ArrayList<>()));
    }
}
