package cn.cincout.tech.black.hole.javaio.stream;

import java.io.*;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-6-26
 * @sine 1.8
 */
public class BasicFileOutput {
    static String file = "ignore.txt";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new StringReader(BufferedInputFile.read(".gitignore"))
        );

        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(file))
        );

        int lineCount = 0;
        String s;
        while ((s = reader.readLine()) != null) {
            out.println(++lineCount + " : " + s);
        }
        out.close();
        reader.close();

        System.out.println(BufferedInputFile.read(file));
    }
}

class BasicFileOutputShortCut {
    static String file = "ignore.txt";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new StringReader(BufferedInputFile.read(".gitignore"))
        );

        // do more for us
        PrintWriter out = new PrintWriter(file);
        int lineCount = 0;
        String s;
        while ((s = reader.readLine()) != null) {
            out.println(++lineCount + " : " + s);
        }
        out.close();
        reader.close();

        System.out.println(BufferedInputFile.read(file));
    }
}
