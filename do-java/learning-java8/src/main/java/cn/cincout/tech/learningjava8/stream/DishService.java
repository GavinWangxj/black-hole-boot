package cn.cincout.tech.learningjava8.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by zhaoyu on 17-9-14.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public class DishService {
    public static List<Dish> getVegetarians(Stream<Dish> dishStream) {
        /*return dishStream
                .filter(dish -> dish.isVegetarian())
                .collect(toList());*/

        return dishStream
                .filter(Dish::isVegetarian)
                .collect(toList());
    }

    public static List<Dish> topCaloriesDishes(List<Dish> dishes) {
        return dishes.stream()
                .filter(dish -> dish.getCalories() > 400)
                .sorted(Comparator.comparing(Dish::getCalories).reversed())
                .limit(3)
                .collect(toList());
    }

    public static Optional<Dish> findAny(List<Dish> dishes) {
        return dishes.stream().filter(Dish::isVegetarian).findAny();
    }

    public static void main(String[] args) {
        System.out.println(DishService.getVegetarians(Dish.createDishes().stream()));

        System.out.println(
                DishService.topCaloriesDishes(Dish.createDishes())
        );

        Optional<Dish> optional = findAny(Dish.createDishes());
        optional.ifPresent(System.out::println);
    }
}
