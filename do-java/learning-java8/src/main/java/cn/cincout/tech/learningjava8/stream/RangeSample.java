package cn.cincout.tech.learningjava8.stream;

import java.util.stream.IntStream;

/**
 * Created by zhaoyu on 17-9-25.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public class RangeSample {
    public static void main(String[] args) {
        IntStream intStream = IntStream.range(1, 100);
        intStream.forEach(System.out::println);
    }
}
