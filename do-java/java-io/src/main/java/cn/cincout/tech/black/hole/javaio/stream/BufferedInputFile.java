package cn.cincout.tech.black.hole.javaio.stream;

import java.io.*;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-6-26
 * @sine 1.8
 */
public class BufferedInputFile {
    public static String read(String fileName) {
        StringBuilder builder = new StringBuilder();
        // try with resources
        // java will call all AutoCloseable classes
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String s;
            while (((s = reader.readLine()) != null)) {
                builder.append(s + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    public static String currentPath() {
        File file = new File("./");
        return file.getAbsolutePath();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(currentPath() + "\n");
        System.out.println(read("/home/zhaoyu/Java/projects/black-hole-boot/do-java/java-io/src/main/java/cn/cincout/tech/black/hole/javaio/stream/BufferedInputFile.java"));
    }
}
