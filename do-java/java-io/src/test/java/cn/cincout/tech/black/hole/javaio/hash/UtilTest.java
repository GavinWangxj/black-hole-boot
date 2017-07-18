package cn.cincout.tech.black.hole.javaio.hash;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-11
 * @sine 1.8
 */
public class UtilTest {

    @Test
    public void testGenerator() {
        System.out.println(Long.toString(Util.hash(12)));
    }
}