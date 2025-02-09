import static org.junit.Assert.*; //import the junit Assert method statically

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.*; //import the junit library

public class MarkdownParseTest { // create a class called MarkdownParseTest
    @Test // Annotation to tell java to test
    public void addition() { // test method
        assertEquals(2, 1 + 1); // the test
    }

    @Test
    public void testlab1() throws IOException {
        Path fileName = Path.of("./labtest1.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        ArrayList<String> expected = (ArrayList<String>) Arrays.asList(
                new String[] { "`google.com", "google.com", "ucsd.edu" });
        assertEquals(links.size(), 3);
        assertEquals(links.toString(), expected.toString());

    }

    @Test
    public void testGetLinks() throws IOException {
        String[] args = { "test-file.md" };
        Path fileName = Path.of(args[0]);
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(links.toString(),
                "[https://something.com, some-page.html]");
    }

    @Test
    public void testGetLinks2() throws IOException {
        String[] args = { "new-test.md" };
        Path fileName = Path.of(args[0]);
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(links.toString(),
                "[https://www.google.com, www.bing.com]");
    }

    @Test
    public void testGetLinks4() throws IOException {
        String[] args = { "test-file9.md" };
        Path fileName = Path.of(args[0]);
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        System.out.println(links);
        assertEquals("[whatever]", links.toString());
    }

    // test all 8 test files
    @Test
    public void testGetLinks3() throws IOException {
        String[] expected = { "[https://something.com, some-page.html]", "[]",
                "[]", "[]", "[page.com]", "[]", "[]" };
        for (int i = 2; i < 9; i++) {
            String[] args = { "test-file" + i + ".md" };
            Path fileName = Path.of(args[0]);
            String contents = Files.readString(fileName);
            ArrayList<String> links = MarkdownParse.getLinks(contents);
            System.out.println(links.toString());
            if (i - 2 < expected.length)
                assertEquals(links.toString(), expected[i - 2]);
            else
                assertEquals(links.toString(), "");
        }
    }
}