package cn.cincout.tech.learningjava8.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by zhaoyu on 17-9-14.
 *
 * @author zhaoyu
 * @sine 1.8
 */

class Dog {
    private int id;
    private String name;

    public Dog(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

public class StreamSample {
    public static List<Integer> distinct(List<Integer> numbers) {
        return numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .collect(toList());
    }

    public static List<Integer> sort(List<Integer> numbers) {
        return numbers.stream().sorted().collect(toList());
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 1, 2, 44, 44, 55, 5, 89);
        System.out.println(distinct(numbers));

        System.out.println(sort(numbers));

        List<Dog> dogs = Arrays.asList(
                new Dog(1, "x"),
                new Dog(2, "ss"),
                new Dog(3, "mm")
        );

        System.out.println(dogs.stream().sorted(Comparator.comparing(Dog::getId).reversed()).collect(toList()));

    }
}
