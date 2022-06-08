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
    public void ensureRatingGetsIncreasedWhenBookmarkIsAgainAdded() throws MalformedURLException {
        Bookmarks bookmarks = new Bookmarks();

        URL url = new URL("https://www.google.com");
        // arrange
        bookmarks.addBookmark(url);
        bookmarks.addBookmark(url);

        int expectedResult = 1;
        int result;

        // act
        result = bookmarks.bookmarks
                .stream().filter(bookmark -> bookmark.getUrl() == url)
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
    public void ensureBookmarkOfTheSameDomainAreAssociated() throws MalformedURLException {

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

    @Test
    public void ensureTaggingIsOnlyValidOnUrlsInList() throws MalformedURLException {
        URL url = new URL("http://www.google.com");
        URL url1 = new URL("http://orf.at");
        Bookmarks bookmarks = new Bookmarks();
        String tag = "Tag";
        //arrange
        bookmarks.addBookmark(url);
        bookmarks.addTagToBookmark(url1, tag);

        //act
        List<String> result = bookmarks.getBookmarkTags(url1);
        List<String> expectedResult = new ArrayList<>();
        //assert
        assertEquals(expectedResult, result);

    }

    @Test
    public void ensureFilteringByKeywordThatExistsReturnsResult() throws MalformedURLException {

        URL url = new URL("http://www.google.com");
        URL url1 = new URL("http://www.face.com/bla");
        URL url2 = new URL("http://www.google.at/extra");

        Bookmarks bookmarks = new Bookmarks();
        //arrange
        String firstTag = "firstTag";
        String secondTag = "secondTag";
        bookmarks.addBookmark(url);
        bookmarks.addBookmark(url1);
        bookmarks.addBookmark(url2);

        bookmarks.addTagToBookmark(url, firstTag);
        bookmarks.addTagToBookmark(url, secondTag);
        bookmarks.addTagToBookmark(url2, firstTag);

        //act

        int result = bookmarks.getBookmarksByTag(firstTag).size();

        //result

        int expectedResult = 2;

        assertEquals(expectedResult, result);


    }

    @Test
    public void ensureFilteringByKeywordThatDoesNotExistsReturnsZero() throws MalformedURLException {

        URL url = new URL("http://www.google.com");
        URL url1 = new URL("http://www.face.com/bla");
        URL url2 = new URL("http://www.google.at/extra");

        Bookmarks bookmarks = new Bookmarks();
        //arrange
        String firstTag = "firstTag";
        bookmarks.addBookmark(url);
        bookmarks.addBookmark(url1);
        bookmarks.addBookmark(url2);


        //act

        int result = bookmarks.getBookmarksByTag(firstTag).size();


        //result

        int expectedResult = 0;

        assertEquals(expectedResult, result);
    }

    @Test
    public void ensureFilteringByMultipleKeywordsThatExistReturnTotal() throws MalformedURLException {
        URL url = new URL("http://www.google.com");
        URL url1 = new URL("http://www.face.com/bla");
        URL url2 = new URL("http://www.google.at/extra");

        Bookmarks bookmarks = new Bookmarks();
        //arrange
        String firstTag = "firstTag";
        String secondTag = "secondTag";
        String thirdTag = "thirdTag";
        bookmarks.addBookmark(url);
        bookmarks.addBookmark(url1);
        bookmarks.addBookmark(url2);

        bookmarks.addTagToBookmark(url, firstTag);
        bookmarks.addTagToBookmark(url, secondTag);
        bookmarks.addTagToBookmark(url2, thirdTag);

        //act
        int result = bookmarks.getBookmarksByTags(Arrays.asList(secondTag, firstTag)).size();

        //result

        int expectedResult = 2;

        assertEquals(expectedResult, result);
    }

    @Test
    public void ensureFilteringByMultipleKeywordsThatDoNotExistReturnTotal() throws MalformedURLException {
        URL url = new URL("http://www.google.com");
        URL url1 = new URL("http://www.face.com/bla");
        URL url2 = new URL("http://www.google.at/extra");

        Bookmarks bookmarks = new Bookmarks();
        //arrange
        String firstTag = "firstTag";
        String secondTag = "secondTag";
        bookmarks.addBookmark(url);
        bookmarks.addBookmark(url1);
        bookmarks.addBookmark(url2);

        //act
        int result = bookmarks.getBookmarksByTags(Arrays.asList(secondTag, firstTag)).size();

        //result
        int expectedResult = 0;

        assertEquals(expectedResult, result);
    }

    @Test
    public void ensureTagIsDeletedInBookmark() throws MalformedURLException {
        URL url = new URL("http://www.google.com");
        URL url1 = new URL("http://www.facebook.com");
        Bookmarks bookmarks = new Bookmarks();
        //arrange
        String firstTag = "firstTag";
        String secondTag = "secondTag";

        bookmarks.addBookmark(url);
        bookmarks.addBookmark(url1);

        bookmarks.addTagToBookmark(url, firstTag);
        bookmarks.addTagToBookmark(url1, firstTag);
        bookmarks.addTagToBookmark(url, secondTag);
        bookmarks.addTagToBookmark(url1, secondTag);


        //act
        bookmarks.deleteTagFromBookmark(url, firstTag);

        //result
        List<String> result = bookmarks.getBookmarkTags(url);
        List<String> expectedResult = Arrays.asList(secondTag);

        assertEquals(expectedResult, result);


    }

    @Test
    public void deleteTagOfNotExistendBookmark() throws MalformedURLException {
        URL url = new URL("http://www.google.com");
        URL url1 = new URL("http://www.facebook.com");
        Bookmarks bookmarks = new Bookmarks();
        //arrange
        String firstTag = "firstTag";
        String secondTag = "secondTag";

        bookmarks.addBookmark(url);


        bookmarks.addTagToBookmark(url, firstTag);
        bookmarks.addTagToBookmark(url, secondTag);

        //act
        bookmarks.deleteTagFromBookmark(url1, firstTag);

        //result
        List<String> result = bookmarks.getBookmarkTags(url1);
        List<String> expectedResult = Arrays.asList();

        assertEquals(expectedResult, result);


    }

    @Test
    public void ensureRemovingExistingBookmarkResultsInSmallerList() throws MalformedURLException {
        URL url = new URL("http://www.google.com");
        URL url1 = new URL("http://www.facebook.com");
        Bookmarks bookmarks = new Bookmarks();
        //arramge
        bookmarks.addBookmark(url);
        bookmarks.addBookmark(url1);

        //List<Bookmark> expectedResult = Arrays.asList(new Bookmark(url1));
        int expectedResult = 1;
        //act
        bookmarks.removeBookmark(url);

        //assert

        assertEquals(expectedResult, bookmarks.bookmarks.size());

    }

    @Test
    public void ensureRemovingNonExistingBookmarkResultsInSameSizedList() throws MalformedURLException {
        URL url = new URL("http://www.google.com");
        URL url1 = new URL("http://www.facebook.com");
        Bookmarks bookmarks = new Bookmarks();
        //arramge
        bookmarks.addBookmark(url);
        bookmarks.addBookmark(url1);

        //List<Bookmark> expectedResult = Arrays.asList(new Bookmark(url1));
        int expectedResult = 2;
        //act
        bookmarks.removeBookmark(new URL("http://www.goog.com"));

        //assert

        assertEquals(expectedResult, bookmarks.bookmarks.size());

    }

}
