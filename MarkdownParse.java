
// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while (currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            if (nextOpenBracket == -1)
                break;
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            if (nextCloseBracket == -1)
                break;
            String betweenBracket = markdown.substring(nextOpenBracket + 1,
                    nextCloseBracket);
            if (betweenBracket.equals("!Image")
                    || betweenBracket.equals("!File")) {
                break;
            }
            currentIndex = nextCloseBracket + 1;
            if (nextCloseBracket < markdown.length() + 1
                    && markdown.charAt(nextCloseBracket + 1) == '(') {
                int nextCloseParen = markdown.indexOf(')',
                        nextCloseBracket + 1);
                if (nextCloseParen == -1)
                    break;
                if (nextCloseParen - nextCloseBracket + 2 > 0) {
                    String possURL = markdown.substring(nextCloseBracket + 2,
                            nextCloseParen);
                    if (Pattern.matches(
                            "[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]",
                            possURL))
                        toReturn.add(possURL);
                }
                currentIndex = nextCloseParen + 1;
            }
        }
        return toReturn;
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0 || args == null) {
            System.out.println("Please provide a file to parse");
            return;
        }
        Path fileName = Path.of(args[0]);
        String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}