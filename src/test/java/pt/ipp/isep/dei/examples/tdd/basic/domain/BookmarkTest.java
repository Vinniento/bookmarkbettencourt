package pt.ipp.isep.dei.examples.tdd.basic.domain;

import org.junit.jupiter.api.Test;

import java.awt.print.Book;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookmarkTest {

    @Test
    public void ensureGetUrlReturnsUrl() throws MalformedURLException {

        //arrange
        URL url = new URL("http://www.google.com");
        Bookmark bookmark = new Bookmark(url);

        //act
        URL result = bookmark.getUrl();
        URL expectedResult = url;
        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void ensureSetRating() throws MalformedURLException {

        //arrange
        URL url = new URL("http://www.google.com");
        Bookmark bookmark = new Bookmark(url);
        bookmark.setRating(2);

        //act
        int result = bookmark.getRating();
        int expectedResult = 2;

        //assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void ensureSingleTaggingOfBookmark() throws MalformedURLException {

        URL url = new URL("http://www.google.com");
        Bookmark bookmark = new Bookmark(url);

        //arrange
        String firstTag = "firstTag";
        List<String> expectedResult = Arrays.asList(firstTag);

        //act
        bookmark.addTag(firstTag);

        List<String> result = bookmark.getTags();

        //assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void ensureMultipleTagsForBookmark() throws MalformedURLException {

        URL url = new URL("http://www.google.com");
        Bookmark bookmark = new Bookmark(url);

        //arrange
        String firstTag = "firstTag";
        String secondTag = "secondTag";
        List<String> expectedResult = Arrays.asList(firstTag, secondTag);

        //act
        bookmark.addTag(firstTag);
        bookmark.addTag(secondTag);

        List<String> result = bookmark.getTags();

        //assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void ensureDeletionOfTag() throws MalformedURLException{
        URL url = new URL("http://www.google.com");
        Bookmark bookmark = new Bookmark(url);

        //arrange
        String firstTag = "firstTag";
        String secondTag = "secondTag";
        bookmark.addTag(firstTag);
        bookmark.addTag(secondTag);

        List<String> expectedResult = Arrays.asList(secondTag);
        List<String> result = bookmark.getTags();
        //act
        bookmark.deleteTag(firstTag);

        //assert
        assertEquals(expectedResult, result);
    }
}
