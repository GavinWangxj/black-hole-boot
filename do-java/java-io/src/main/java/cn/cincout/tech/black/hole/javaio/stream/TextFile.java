package cn.cincout.tech.black.hole.javaio.stream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-6-27
 * @sine 1.8
 */
public class TextFile extends ArrayList<String> {

    public static String read(String fileName) {
        StringBuffer stringBuffer = new StringBuffer();

        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(new File(fileName).getAbsoluteFile())
            );

            try {
                String s;
                while ((s = reader.readLine()) != null) {
                    stringBuffer.append(s);
                    stringBuffer.append("\n");
                }
            } finally {
                reader.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }



}
