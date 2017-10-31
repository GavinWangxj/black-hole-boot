package cn.cincout.tech.learningjava8.stream;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by zhaoyu on 17-9-25.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public class MapSample {

    public static void flatMap() {
        List<Integer> number1 = Arrays.asList(1, 2, 3);
        List<Integer> number2 = Arrays.asList(3, 4);

        List<int[]> pairs = number1.stream()
                // merge into one stream
                .flatMap(i -> number2.stream().map(j -> new int[]{i, j}))
                .collect(toList());

        pairs.stream().forEach(MapSample::print);
    }

    public static void print(int[] pair) {
        System.out.println(Arrays.toString(pair));
    }

    public static List<String> createData() {
        return Arrays.asList("Java8", "zhang", "yu");
    }

    public static void main(String[] args) {
        List<Integer> lengths = createData().stream().map(String::length).collect(toList());
        System.out.println(lengths);

        flatMap();
    }
}
