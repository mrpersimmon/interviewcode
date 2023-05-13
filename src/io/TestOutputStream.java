package io;

import org.junit.jupiter.api.Test;

import java.io.*;

public class TestOutputStream {
    @Test
    public void testFileOutputStream() {
        // FileOutputStream(String name, boolean append) 追加写入
        // FileOutputStream(String name) 覆盖写入
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("mrpersimmon.txt"))) {
            String str = "Hi,Mrpersimmon!";
            // write(byte b[ ]) : 将字节数组 b 写入到输出流，等价于 write(b, 0, b.length)
            bos.write(str.getBytes("UTF-8")); // str.getBytes() 字符串 -> 字节数组
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDataOutputStream() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("mrpersimmon2.txt"))) {
            // 输出任意输入类型
            dos.writeUTF("Hi,Mrpersimmon!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testObjectOutputStream() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("mrpersimmon3.txt"))) {
            Blog blog = new Blog("mrpersimmon", "https://www.mrpersimmon.top");
            oos.writeObject(blog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


