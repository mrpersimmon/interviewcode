package io;

import org.junit.jupiter.api.Test;

import java.io.*;

public class TestReader {
    @Test
    public void testFileReader() {
        try (BufferedReader br = new BufferedReader(new FileReader("inputFileReader.txt"))) {
            long skipNum = br.skip(4);
            System.out.println("忽略的字符数量：" + skipNum);

            int content;
            System.out.print("从文件中读取的内容：");
            while ((content = br.read()) != -1) {
                System.out.print((char) content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void compareInputStream() {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("inputFileReader.txt"))) {
            long skipNum = bis.skip(4);
            System.out.println("忽略的字节数：" + skipNum);

            int content;
            System.out.print("从文件中读取的内容：");
            while ((content = bis.read()) != -1) {
                System.out.print((char) content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
