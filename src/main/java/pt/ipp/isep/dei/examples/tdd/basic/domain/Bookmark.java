package pt.ipp.isep.dei.examples.tdd.basic.domain;

import java.awt.print.Book;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Bookmark {

    public Bookmark(URL url) {
        this.url = url;
        this.tags = new ArrayList<>();

    }

    URL url;
    List<String> tags;
    public URL getUrl() {
        return url;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    int rating = 0;



    public void addTag(String Tag) {
        this.tags.add(Tag);
    }

    public List<String> getTags() {
        return this.tags;
    }

}

