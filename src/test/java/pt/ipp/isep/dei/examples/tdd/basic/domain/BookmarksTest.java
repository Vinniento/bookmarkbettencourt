package pt.ipp.isep.dei.examples.tdd.basic.domain;

import org.junit.jupiter.api.Test;

import java.awt.print.Book;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookmarksTest {

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

    //CheckSecureUrlCount
    @Test
    public void EnsureNoSecureUrlsInBookmarksReturnsZero() throws MalformedURLException {

        //arrange
        Bookmarks bookmarks = new Bookmarks();

        bookmarks.addBookmark(new URL("http://www.google.at"));
        bookmarks.addBookmark(new URL("http://www.orf.at"));

        //act
        int result = bookmarks.getSecureUrlCount();
        int expectedresult = 0;


        //assert
        assertEquals(expectedresult, result);

    }

    @Test
    public void EnsureOneSecureUrlInBookmarksReturnsOne() throws MalformedURLException {

        //arrange
        Bookmarks bookmarks = new Bookmarks();

        bookmarks.addBookmark(new URL("https://www.google.at"));
        bookmarks.addBookmark(new URL("http://www.orf.at"));

        //act
        int result = bookmarks.getSecureUrlCount();
        int expectedresult = 1;


        //assert
        assertEquals(expectedresult, result);
    }
    @Test
    public void EnsureTwoSecureUrlInBookmarksReturnsTwo() throws MalformedURLException {
        //arrange
        Bookmarks bookmarks = new Bookmarks();

        bookmarks.addBookmark(new URL("https://www.google.at"));
        bookmarks.addBookmark(new URL("https://www.orf.at"));

        //act
        int result = bookmarks.getSecureUrlCount();
        int expectedresult = 2;


        //assert
        assertEquals(expectedresult, result);
    }

}
