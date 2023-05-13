package io;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class TestFile {
    @Test
    public void createFile() {
        // 更换成你想要存放的文件路径
        String path = "/Users/sunnywinter/projects/interviewcode/testFile.txt";
        File file = new File(path); // 此时只是程序中的一个对象
        try {
            file.createNewFile(); // 执行该方法才会真正地在磁盘中创建文件
            System.out.println("文件创建成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createFile2() {
        // 更换成你想要存放的文件路径
        File parentFile = new File("/Users/sunnywinter/projects/interviewcode/");
        String fileName = "testFile2.txt";
        File file = new File(parentFile, fileName);
        try {
            file.createNewFile();
            System.out.println("文件创建成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createFile3() {
        // 更换成你想要存放的文件路径
        String parentFile = "/Users/sunnywinter/projects/interviewcode/";
        String fileName = "testFile3.txt";
        File file = new File(parentFile, fileName);
        try {
            file.createNewFile();
            System.out.println("文件创建成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFileInfo() {
        File file = new File("/Users/sunnywinter/projects/interviewcode/testFile.txt");
        System.out.println("文件名：" + file.getName());
        System.out.println("文件绝对路径：" + file.getAbsolutePath());
        System.out.println("文件父级目录：" + file.getParent());
        System.out.println("文件大小：" + file.length());
        System.out.println("文件是否存在：" + file.exists());
        System.out.println("是否是一个文件：" + file.isFile());
        System.out.println("是否是一个目录：" + file.isDirectory());
    }


    @Test
    public void test() {
        String parentPath = "/Users/sunnywinter/projects/interviewcode/";
        String fileName = "testFile.txt";
        String directoryName = "a";
        String mulDirectoryName = "b/c/d";
        // 删除文件
        File file = new File(parentPath, fileName);
        file.delete();
        // 创建一级目录
        File directory = new File(parentPath, directoryName);
        directory.mkdir();
        // 创建多级目录
        File mulDirectory = new File(parentPath, mulDirectoryName);
        mulDirectory.mkdirs();
        // 删除目录
        directory.delete();
    }
}
