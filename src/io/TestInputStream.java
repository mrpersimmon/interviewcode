package io;

import org.junit.jupiter.api.Test;

import java.io.*;

public class TestInputStream {
    @Test
    public void testFileInputStream() {
        // try() 会自动关闭输入流，FileInputStream 与 BufferedInputStream 配合使用
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("mrpersimmon.txt"))) {
            // int available() 返回输入流中可以读取的字节数。
            System.out.println("文件中可读取的字节数量：" + bufferedInputStream.available());

            // long skip(long n) 忽略输入流中的 n 个字节 ,返回实际忽略的字节数。
            long skipCounts = bufferedInputStream.skip(3); // 忽略 3 个字节
            System.out.println("忽略的字节数量：" + skipCounts);

            // read() 返回输入流中下一个字节的数据。
            System.out.print("从文件中读取的字节内容：");
            int content;
            // 返回值为 -1 时，表示读取完毕
            while ((content = bufferedInputStream.read()) != -1) {
                System.out.print((char) content); // 将读出的 int 类型数据强转成 char 类型
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFileInputStream2() {
        // try() 会自动关闭输入流，FileInputStream 与 BufferedInputStream 配合使用
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("input.txt"))) {
            // int available() 返回输入流中可以读取的字节数。
            int bufSize = bufferedInputStream.available();
            System.out.println("文件中可读取的字节数量：" + bufSize);
            byte[] buf = new byte[8]; // 一次读取 8 字节

            // long skip(long n) 忽略输入流中的 n 个字节 ,返回实际忽略的字节数。
            long skipCounts = bufferedInputStream.skip(3); // 忽略 3 个字节
            System.out.println("忽略的字节数量：" + skipCounts);

            // read(byte b[]) 从输入流中读取一些字节存储到数组 b 中。
            // 如果数组 b 的长度为零，则不读取。
            // 如果没有可用字节读取，返回 -1。
            // 如果有可用字节读取，则最多读取的字节数最多等于 b.length，返回读取的字节数。
            // 这个方法等价于 read(b, 0, b.length)。
            System.out.print("从文件中读取的字节内容：");
            int readLen;
            // 返回值为 -1 时，表示读取完毕
            while ((readLen = bufferedInputStream.read(buf)) != -1) {
                System.out.print(new String(buf, 0, readLen)); // 将字符数组 buf 转换成字符串
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test2() {
        // BufferedInputStream
        try(BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("input.txt"))) {
            byte[] b = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(b);
            String res = new String(b);
            System.out.println(res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDataInputStream() throws IOException {
        // 必须将一个 InputStream 的实现类作为构造参数才能使用
        try(DataInputStream dis = new DataInputStream(new FileInputStream("input.txt"))) {
            // 可以读取任意具体的类型数据
            System.out.println(dis.readBoolean()); // 读取一个输入字节，并返回 true如果该字节不为零， false如果该字节是零。
            System.out.println(dis.readInt()); // 读取四个输入字节并返回一个 int 值。
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testObjectInputStream() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("mrpersimmon3.txt"))) {
            System.out.println(ois.readObject());
            System.out.println("数据读取完毕(反序列化完成)");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.dat"))) {
            oos.writeObject(new Blog("mrpersimmon", "www.mrpersimmon.top"));
            System.out.println("数据保存完毕(序列化完成)");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





}

