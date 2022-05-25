package pt.ipp.isep.dei.examples.tdd.basic.domain;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookmarkTest {

    @Test
    public void ensureMultipleTagsForBookmark() throws MalformedURLException {

        URL url = new URL("http://www.google.com");
        Bookmark bookmark = new Bookmark(url);

        //arrange
        Map<String, Integer> expectedResult = new HashMap<String, Integer>();

        String firstTag = "firstTag";
        String secondTag = "secondTag";

        expectedResult.put(firstTag,0);
        expectedResult.put(secondTag,0);

        //act
        bookmark.addTag(firstTag);
        bookmark.addTag(secondTag);

        List<String> result = bookmark.getTags();

        //assert
        assertEquals(expectedResult, result);

    }
}
