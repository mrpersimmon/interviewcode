package io;

import java.io.Serializable;

public class Blog implements Serializable {
    String name;
    String url;
    public Blog(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "name='" + name + '\'' +
                ", url=" + url +
                '}';
    }
}
