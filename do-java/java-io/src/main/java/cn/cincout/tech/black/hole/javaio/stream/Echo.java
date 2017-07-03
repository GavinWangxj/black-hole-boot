package cn.cincout.tech.black.hole.javaio.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-6-29
 * @sine 1.8
 */
public class Echo {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in)
        );

        String str = reader.readLine();
        reader.close();
        //System.out.println(str);

        PrintWriter writer = new PrintWriter(System.out, true);
        writer.println(str);
        writer.close();
    }
}
