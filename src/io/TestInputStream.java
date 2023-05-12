package io;

import org.junit.jupiter.api.Test;

import java.io.*;

public class TestInputStream {
    @Test
    public void test() {
        // try() 会自动关闭输入流
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("input.txt"))) {
            System.out.println("文件中可读取的字节数量：" + bufferedInputStream.available());
            long skipCounts = bufferedInputStream.skip(2); // 忽略 2 个字节
            System.out.println("忽略的字节数量：" + skipCounts);
            System.out.print("从文件中读取的字节内容：");
            int content; // 保存读取的文本数据
            // 读取的内容为 -1 时结束读取
            while ((content = bufferedInputStream.read()) != -1) {
                System.out.print((char) content); // 将读出的 int 数据强转成 char 类型
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
        // 必须将一个 InputStream 类作为构造参数才能使用
        try(DataInputStream dis = new DataInputStream(new FileInputStream("input.txt"))) {
            // 可以读取任意具体的类型数据
            dis.readBoolean();
            dis.readInt();
            dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testObjectInputStream() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.data"))) {
//            MyClass object = (MyClass) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

