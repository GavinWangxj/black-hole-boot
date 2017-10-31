package cn.cincout.tech.doapachecommons.domain;

/**
 * Created by zhaoyu on 17-9-25.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public class LongTest {
    public static void main(String[] args) {
        Long a = new Long(10);
        Long b = new Long(10);

        long c = 10;
        System.out.println(a.equals(b));
        System.out.println(a == c);

        a = 10L;
        b = 10L;
        System.out.println(a == b);
    }
}
