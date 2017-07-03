package cn.cincout.tech.black.hole.javaio.stream;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-6-26
 * @sine 1.8
 */
public class MemoryInput {
    public static void main(String[] args) throws IOException {
        StringReader stringReader = new StringReader(BufferedInputFile.read("pom.xml"));

        int c;
        while ((c = stringReader.read()) != -1) {
            System.out.print((char) c);
        }
    }
}

class FormattedMemoryInput {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(
                new ByteArrayInputStream(BufferedInputFile.read("pom.xml").getBytes())
        );

        while (in.available() != 0) {
            System.out.print((char) in.readByte());
        }
    }
}
