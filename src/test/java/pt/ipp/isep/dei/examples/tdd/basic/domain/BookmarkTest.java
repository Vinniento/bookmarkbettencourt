package pt.ipp.isep.dei.examples.tdd.basic.domain;

import org.junit.jupiter.api.Test;
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
        List<URL> expectedResult = new ArrayList<>();
        List<URL> result;
        URL url = new URL("http://www.google.com");

        //act
        expectedResult.add(url);
        result = bookmarks.addBookmark(url);

        //assert
        assertEquals(expectedResult, result);
    }

}
