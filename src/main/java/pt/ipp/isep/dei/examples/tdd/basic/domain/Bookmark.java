package pt.ipp.isep.dei.examples.tdd.basic.domain;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Bookmark {

    private LocalDateTime creationTime;

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

    public URL getUrl() {
        return url;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public List<String> getTags() {
        return this.tags;
    }

    public void deleteTag(String tag) {
        this.tags.remove(tag);
    }

    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    public void setDateTime() {
        this.creationTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }
}

