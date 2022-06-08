package pt.ipp.isep.dei.examples.tdd.basic.domain;

import java.awt.print.Book;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Bookmarks {
    List<Bookmark> bookmarks = new ArrayList<>();

    public List<Bookmark> addBookmark(URL url){

        if (checkIfBookmarkExists(url)) {
            for (Bookmark bookmark: bookmarks) {
                bookmark.setRating(bookmark.getRating() + 1);
            }
        } else {
            bookmarks.add(new Bookmark(url));
        }
        return bookmarks;
    }



    public boolean checkIfBookmarkExists(URL url) {
        for (Bookmark bookmark : bookmarks) {
            if (bookmark.getUrl() == url) {
                return true;
            }
        }
        return false;
    }


    public int getSecureUrlCount() {
        return  (int)bookmarks.stream()
                .filter(b -> b.getUrl().toString().contains("https://"))
                .count();
    }

}

