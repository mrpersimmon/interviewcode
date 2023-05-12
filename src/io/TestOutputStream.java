package io;

import org.junit.jupiter.api.Test;

import java.io.*;

public class TestOutputStream {
    @Test
    public void testFileOutputStream() {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("output.txt"))) {
            byte[] arr = "mrpersimmon".getBytes();
            bos.write(arr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDataOutputStream() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("out.txt"))) {
            dos.writeBoolean(true);
            dos.writeByte(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testObjectOutputStream() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("file.txt"))) {
            Blog blog = new Blog("mrpersimmon", "www.mrpersimmon.top");
            oos.writeObject(blog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Blog implements Serializable{
    String name;
    String url;
    public Blog(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
