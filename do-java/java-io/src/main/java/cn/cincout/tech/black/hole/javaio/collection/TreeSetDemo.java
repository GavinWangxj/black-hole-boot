package cn.cincout.tech.black.hole.javaio.collection;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-19
 * @sine 1.8
 */
public class TreeSetDemo {
    public static void main(String[] args) {
        SortedSet<String> sortedSet = new TreeSet<>();
        sortedSet.add("112");
        sortedSet.add("113");
        sortedSet.add("1120");

        System.out.println(sortedSet);
    }
}
