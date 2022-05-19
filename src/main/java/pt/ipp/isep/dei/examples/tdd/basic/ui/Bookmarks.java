package pt.ipp.isep.dei.examples.tdd.basic.ui;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Bookmarks {
    List<URL> bookmarks = new ArrayList<>();

    public List<URL> addBookmark(URL url) {
        bookmarks.add(url);
        return bookmarks;
    }
}
