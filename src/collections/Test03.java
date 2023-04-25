package collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Test03 {
    public static void main(String[] args) {
        Collection<Book> col = new ArrayList<>();
        col.add(new Book("三国演义", "罗贯中", 10.1));
        col.add(new Book("小李飞刀", "古龙", 5.1));
        col.add(new Book("红楼梦", "曹雪芹", 34.6));

        // 1、使用迭代器遍历集合元素，快捷键 itit
        Iterator it = col.iterator(); // 1. 得到 col 的迭代器
        while (it.hasNext()) { // 2. 使用 while 循环，利用 hasNext() 判断集合是否还有其他元素
            System.out.println(it.next()); // 3. 返回下一个元素
        }

        // 4. 当退出 while 循环后，这时 iterator 迭代器，指向最后的元素
        // it.next(); // 会报错，NoSuchElementException

        // 5. 如果希望再次遍历，需要重置迭代器
        it = col.iterator();
        System.out.println("===== 第二次遍历 =====");
        while (it.hasNext()) {
            Object obj = it.next();
            System.out.println("obj=" + obj);
        }
    }
}

class Book {
    private String name;
    private String author;
    private double price;

    public Book(String name, String author, double price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
