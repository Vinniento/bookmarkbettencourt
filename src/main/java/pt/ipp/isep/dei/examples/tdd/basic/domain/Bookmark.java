package pt.ipp.isep.dei.examples.tdd.basic.domain;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Bookmark {

    public Bookmark(URL url) {
        this.url = url;
        this.tags = new ArrayList<>();
        this.domain = url.getHost();
    }

    URL url;
    List<String> tags;
    String domain;
    int rating = 0;


    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public URL getUrl() {
        return url;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    public void addTag(String Tag) {
        this.tags.add(Tag);
    }

    public List<String> getTags() {
        return this.tags;
    }

}

