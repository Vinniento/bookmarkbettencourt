package pt.ipp.isep.dei.examples.tdd.basic.domain;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.examples.tdd.basic.ui.Bookmark;
import pt.ipp.isep.dei.examples.tdd.basic.ui.Bookmarks;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookmarkTest {
    /*
     * Brauchen eine bookmark liste, um urls hinein zu speichern
     *
     * */
    @Test
    public void addBookmarksTest() throws MalformedURLException {

        Bookmarks bookmarks = new Bookmarks();

        //arrange
        List<Bookmark> expectedResult = new ArrayList<>();
        List<Bookmark> result;
        URL url = new URL("http://www.google.com");

        //act
        expectedResult.add(new Bookmark(url));
        result = bookmarks.addBookmark(url);

        //assert
        assertEquals(expectedResult.get(0).getUrl(), result.get(0).getUrl());
    }

    /*@Test
    //["https://www.google.com", "https://www.youtube.com", ""]
    public void ensureThatValidURLsAreParsedCorrectly(String url) throws MalformedURLException {
        Bookmarks bookmarks = new Bookmarks();

        URL u = new URL("ww.google.com");

        // Act
        Exception ex = assertThrows(MalformedURLException.class, () -> bookmarks.checkValidURL());

        // Assert
        assertEquals("/ by zero", ex.getMessage());
        assertTrue(ex.getMessage().contains("zero"));

    }*/

    @Test
    public void ensureTaggingOfURL() throws MalformedURLException {
        Bookmarks bookmarks = new Bookmarks();

        String tag = "katzenvideos";

        String expectedResult = tag;
        URL url = new URL("https://www.google.com");
        bookmarks.addBookmark(url);
        bookmarks.setBookmarkTag(url, tag);

        String result = bookmarks.getBookmarkTag(url);

        assertEquals(expectedResult, result);

    }

}
