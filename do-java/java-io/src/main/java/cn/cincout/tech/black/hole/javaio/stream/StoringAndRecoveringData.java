package cn.cincout.tech.black.hole.javaio.stream;

import java.io.*;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-6-27
 * @sine 1.8
 */
public class StoringAndRecoveringData {
    public static void main(String[] args) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream("Data.txt"))
        );

        dataOutputStream.writeDouble(3.14159);
        dataOutputStream.writeUTF("hello world");
        dataOutputStream.close();

        DataInputStream dataInputStream = new DataInputStream(
                new BufferedInputStream(new FileInputStream("Data.txt"))
        );

        System.out.println(dataInputStream.readDouble());
        System.out.println(dataInputStream.readUTF());
        dataInputStream.close();
    }
}
