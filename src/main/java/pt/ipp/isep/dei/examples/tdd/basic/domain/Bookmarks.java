package pt.ipp.isep.dei.examples.tdd.basic.domain;

import java.awt.print.Book;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Bookmarks {
    List<Bookmark> bookmarks = new ArrayList<>();

    public List<Bookmark> addBookmark(URL url) {
        if (CheckIfBookmarkExists(url)) {
            for (Bookmark bookmark: bookmarks) {
                bookmark.setRating(bookmark.getRating() + 1);
            }
        } else {
            bookmarks.add(new Bookmark(url));
        }
        return bookmarks;
    }


    public boolean CheckIfBookmarkExists(URL url) {
        for (Bookmark bookmark : bookmarks) {
            if (bookmark.getUrl() == url) {
                return true;
            }
        }
        return false;
    }

    public void addTagToBookmark(URL url, String tag) {

        bookmarks.forEach(bookmark -> {
            if (bookmark.getUrl() == url) {
                bookmark.addTag(tag);
            }
        });

    }

    public List<String> getBookmarkTags(URL url) {
        List<String> tag = new ArrayList<>();

        for (Bookmark bookmark : bookmarks) {
            if (bookmark.getUrl() == url) {
                tag = bookmark.getTags();
                break;
            }
        }
        return tag;
    }


    public int getBookmarkRating(URL url) {
        int rating = 0;
        for (Bookmark bookmark : bookmarks) {
            if (bookmark.getUrl() == url) {
                rating = bookmark.getRating();
                break;
            }
        }
        return rating;
    }
}

