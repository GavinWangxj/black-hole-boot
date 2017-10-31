package cn.cincout.tech.learningjava8.lambda;

import java.util.function.DoubleFunction;

/**
 * Created by zhaoyu on 17-9-14.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public class FunctionSample {
    public static double integrate(DoubleFunction<Double> doubleFunction, double a, double b) {
        return (doubleFunction.apply(a) + doubleFunction.apply(b)) * (b - a) / 2.0;
    }

    public static void main(String[] args) {
        System.out.println(FunctionSample.integrate(x -> x + 10, 10, 20));
    }
}
