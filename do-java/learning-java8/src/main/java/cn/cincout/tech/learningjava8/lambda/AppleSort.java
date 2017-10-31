package cn.cincout.tech.learningjava8.lambda;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

/**
 * Created by zhaoyu on 17-9-13.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public class AppleSort {
    public static void main(String[] args) {
        List<Apple> apples = createData();

        apples.sort(comparing(Apple::getWeight));
        System.out.println(apples);
        Collections.reverse(apples);
        System.out.println(apples);

        apples.sort(comparing(Apple::getColor));
        System.out.println(apples);
        apples.sort(comparing(Apple::getColor).reversed());
        System.out.println(apples);

        Predicate<Apple> redApple = (Apple apple) -> apple.getColor().equals("red");
        // not red apple
        Predicate<Apple> notRedApple = redApple.negate();

    }

    public static List<Apple> createData() {
        return Arrays.asList(
                new Apple(10, "green"),
                new Apple(100, "red"),
                new Apple(200, "blue")
        );
    }
}
