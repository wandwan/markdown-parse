import static org.junit.Assert.*; //import the junit Assert method statically

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.*; //import the junit library

public class MarkdownParseTest { // create a class called MarkdownParseTest
    @Test // Annotation to tell java to test
    public void addition() { // test method
        assertEquals(2, 1 + 1); // the test
    }

    @Test
    public void testGetLinks() throws IOException {
        String[] args = { "/home/camel/markdown-parse/test-file.md" };
        Path fileName = Path.of(args[0]);
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(links.toString(),
                "[https://something.com, some-page.html]");
    }

    @Test
    public void testGetLinks2() throws IOException {
        String[] args = { "/home/camel/markdown-parse/new-test.md" };
        Path fileName = Path.of(args[0]);
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(links.toString(), "[https://www.google.com, www.bing.com]");
    }
    //test all 8 test files
    @Test
    public void testGetLinks3() throws IOException {
        String[] expected = {"[https://something.com, some-page.html]","[]","[]","[]","[page.com]"};
        for(int i = 2; i < 10; i++) {
            String[] args = { "/home/camel/markdown-parse/test-file" + i + ".md" };
            Path fileName = Path.of(args[0]);
            String contents = Files.readString(fileName);
            ArrayList<String> links = MarkdownParse.getLinks(contents);
            System.out.println(links.toString());
            if(i - 2 < expected.length)
                assertEquals(links.toString(), expected[i - 2]);
            else
                assertEquals(links.toString(), "");
        }
    }
}