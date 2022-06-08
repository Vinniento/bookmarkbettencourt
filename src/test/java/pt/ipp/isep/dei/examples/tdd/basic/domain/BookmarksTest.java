package pt.ipp.isep.dei.examples.tdd.basic.domain;

import org.junit.jupiter.api.Test;

import java.awt.print.Book;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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

    @Test
    public void ensureRatingGetsIncreasedWhenBookmarkIsAgainAdded() throws MalformedURLException{
        Bookmarks bookmarks = new Bookmarks();

        URL url  = new URL("https://www.google.com");
        // arrange
        bookmarks.addBookmark(url);
        bookmarks.addBookmark(url);

        int expectedResult = 1;
        int result;

        // act
        result = bookmarks.bookmarks
                .stream().filter(bookmark -> bookmark.getUrl()==url)
                .findFirst().orElse(null).getRating();

        // assert
        assertEquals(expectedResult, result);
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


    @Test
    public  void ensureBookmarkOfTheSameDomainAreAssociated() throws MalformedURLException {

        //arrange
        Bookmarks bookmarks = new Bookmarks();
        URL url = new URL("https://www.google.at/dadu");
        URL url1 = new URL("https://www.google.at/bla");
        URL url2 = new URL("https://www.google.com/blasdf");

        bookmarks.addBookmark(url);
        bookmarks.addBookmark(url1);
        bookmarks.addBookmark(url2);

        //act
        int result = bookmarks.getAssociatedBookmarksForDomain("www.google.at").size();

        int expectedResult = 2;

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void ensureSingleTaggingOfBookmark() throws MalformedURLException {


        //arrange
        Bookmarks bookmarks = new Bookmarks();
        String tag = "katzenvideos";
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(tag);
        URL url = new URL("https://www.google.com");

        //act
        bookmarks.addBookmark(url);
        bookmarks.addTagToBookmark(url, tag);

        List<String> result = bookmarks.getBookmarkTags(url);

        //result
        assertEquals(expectedResult, result);

    }


    @Test
    public void ensureMultipleTagsForBookmark() throws MalformedURLException {

        URL url = new URL("http://www.google.com");
        Bookmarks bookmarks = new Bookmarks();
        //arrange
        String firstTag = "firstTag";
        String secondTag = "secondTag";
        List<String> expectedResult = Arrays.asList(firstTag, secondTag);

        //act
        bookmarks.addBookmark(url);
        bookmarks.addTagToBookmark(url, firstTag);
        bookmarks.addTagToBookmark(url, secondTag);

        List<String> result = bookmarks.getBookmarkTags(url);

        //assert
        assertEquals(expectedResult, result);

    }

}
