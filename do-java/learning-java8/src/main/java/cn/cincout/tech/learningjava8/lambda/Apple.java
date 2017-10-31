package cn.cincout.tech.learningjava8.lambda;

import java.util.*;
import java.util.function.Predicate;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.toList;

/**
 * Created by zhaoyu on 17-9-11.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public class Apple {
    int weight;
    String color;

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return this.weight;
    }

    public String getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filter(List<Apple> inventory, Predicate<Apple> applePredicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (applePredicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> appleList = Arrays.asList(
                new Apple(10, "green"),
                new Apple(100, "red"),
                new Apple(200, "blue")
        );

        System.out.println(Apple.filter(appleList, Apple::isGreenApple));
        System.out.println(Apple.filter(appleList, Apple::isHeavyApple));

        List<Apple> apples = appleList
                .parallelStream()
                .filter((Apple apple ) -> apple.getWeight() > 150)
                .collect(toList());
        System.out.println(apples);
        List<Object> objects = Arrays.asList(
                null,
                null,
                null
        );
        System.out.println(objects.size());
        Set<Object> objectSet = new HashSet<>();
        objectSet.add(null);
        System.out.println(objectSet.size());

        int[][] array = new int[][] {};
        System.out.println(array.getClass());
        System.out.println(Apple.class.getName());
        System.out.println(Apple.class.getCanonicalName());
    }
}
