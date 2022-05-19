package pt.ipp.isep.dei.examples.tdd.basic.ui;

import java.awt.print.Book;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


}

