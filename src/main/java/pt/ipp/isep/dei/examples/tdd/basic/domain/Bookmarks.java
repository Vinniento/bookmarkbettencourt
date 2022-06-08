package pt.ipp.isep.dei.examples.tdd.basic.domain;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Bookmarks {
    List<Bookmark> bookmarks = new ArrayList<>();

    public List<Bookmark> addBookmark(URL url) {

        if (checkIfBookmarkExists(url)) {
            for (Bookmark bookmark : bookmarks) {
                bookmark.setRating(bookmark.getRating() + 1);
            }
        } else {
            bookmarks.add(new Bookmark(url));
        }
        return bookmarks;
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

    public boolean checkIfBookmarkExists(URL url) {
        for (Bookmark bookmark : bookmarks) {
            if (bookmark.getUrl() == url) {
                return true;
            }
        }
        return false;
    }

    public List<Bookmark> getBookmarksByTag(String tag) {
        return bookmarks
                .stream()
                .filter(bookmark -> bookmark.getTags().contains(tag))
                .collect(Collectors.toList());
    }

    public List<Bookmark> getBookmarksByTags(List<String> tags) {
        List<Bookmark> result = new ArrayList<>();
        for (String tag : tags) {
            result.addAll(getBookmarksByTag(tag));
        }
        return result;
    }

    public int getSecureUrlCount() {
        return (int) bookmarks.stream()
                .filter(b -> b.getUrl().toString().contains("https://"))
                .count();
    }

    public List<Bookmark> getAssociatedBookmarksForDomain(String domain) {

        return bookmarks
                .stream()
                .filter(bookmark -> Objects.equals(bookmark.getDomain(), domain))
                .collect(Collectors.toList());
    }

    public void deleteTagFromBookmark(URL url, String tag) {

        for (Bookmark bookmark : bookmarks) {
            if (bookmark.getUrl() == url) {
               bookmark.deleteTag(tag);
            }
        }
    }

    public void removeBookmark(URL url) {
        bookmarks.removeIf(bookmark -> bookmark.getUrl() == url);
    }
}

