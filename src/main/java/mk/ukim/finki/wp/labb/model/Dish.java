package mk.ukim.finki.wp.labb.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dish {
    private String dishId;
    private String name;
    private String cuisine;
    private int preparationTime;

}
