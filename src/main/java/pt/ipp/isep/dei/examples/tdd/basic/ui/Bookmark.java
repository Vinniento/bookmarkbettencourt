package pt.ipp.isep.dei.examples.tdd.basic.ui;

import java.awt.print.Book;
import java.net.URL;

public class Bookmark {
    public URL getUrl() {
        return url;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    URL url;
    String tag;

    public Bookmark(URL url) {
        this.url = url;
    }


}

