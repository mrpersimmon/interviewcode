package collections;

import java.util.ArrayList;
import java.util.Collection;

public class Test04 {
    public static void main(String[] args) {
        Collection<Book> col = new ArrayList<>();
        col.add(new Book("三国演义", "罗贯中", 10.1));
        col.add(new Book("小李飞刀", "古龙", 5.1));
        col.add(new Book("红楼梦", "曹雪芹", 34.6));

        for (Book b : col) {
            System.out.println(b);
        }
    }
}
