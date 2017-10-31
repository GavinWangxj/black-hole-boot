package cn.cincout.tech.learningjava8.stream;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhaoyu on 17-9-25.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public class ReduceSample {
    public static int sum(List<Integer> numbers) {
        // return numbers.stream().reduce(0, (a, b) -> a + b);
        return numbers.stream().reduce(0, Integer::sum);
    }

    public static int max(List<Integer> numbers) {
        return numbers.stream().reduce(Integer::max).get();
    }


    public static StatBo objectReduce(List<StatBo> statBos) {
        StatBo statBo = new StatBo(0);
        return statBos.stream().reduce(statBo, StatBo::add);
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        System.out.println(sum(numbers));

        List<StatBo> statBoList = Arrays.asList(
            new StatBo(2), new StatBo(2), new StatBo(2)
        );

        StatBo statBo = statBoList.get(0);
        statBo = statBoList.stream().reduce(statBo, StatBo::add);
        System.out.println(statBo);
        //Long count = statBoList.stream().filter(statBo -> statBo.data == 2).count();
        //System.out.println(count);
    }
}

@Data
class StatBo {
    int data;

    public StatBo(int data) {
        this.data = data;
    }

    public static StatBo add(StatBo a, StatBo b) {
        StatBo statBo = new StatBo(a.data + b.data);
        return statBo;
    }
}
