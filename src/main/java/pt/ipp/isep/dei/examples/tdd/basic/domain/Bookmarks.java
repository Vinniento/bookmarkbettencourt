package pt.ipp.isep.dei.examples.tdd.basic.domain;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Bookmarks {
    List<Bookmark> bookmarks = new ArrayList<>();

    public List<Bookmark> addBookmark(URL url) {
        bookmarks.add(new Bookmark(url));
        return bookmarks;
    }

    public void setBookmarkTag(URL url, String tag) {

        bookmarks.forEach(bookmark -> {
            if (bookmark.getUrl() == url) {
                bookmark.setTag(tag);
            }
        });

    }

    public String getBookmarkTag(URL url) {
        String tag = "";
        for (Bookmark bookmark : bookmarks) {
            if (bookmark.getUrl() == url) {
                tag = bookmark.getTag();
                break;
            }
        }
        return tag;
    }


    public int getBookmarkRating(URL url) {
        int rating=0;
        for (Bookmark bookmark : bookmarks) {
            if (bookmark.getUrl() == url) {
                rating = bookmark.getRating();
                break;
            }
        }
        return rating;
    }
}
