package cn.cincout.tech.learningjava8.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhaoyu on 17-9-21.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Data
@AllArgsConstructor
class Dot {
    private int time;
}
public class SetSample {
    public static void main(String[] args) {
        Set<Dot> dots = new HashSet<>();
        dots.add(new Dot(10));
        dots.add(new Dot(20));
        dots.add(new Dot(30));

        long total = dots.stream().mapToLong(dot -> dot.getTime()).summaryStatistics().getSum();
        System.out.println(total);
    }
}
