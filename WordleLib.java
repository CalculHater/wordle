import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class WordleLib {
    /** Creates a Scanner that reads lines from the plaintext file filename. */
    public static Scanner createFileScanner(String filename) {
        try {
            return new Scanner(Paths.get(filename));
        } catch(IOException oops) {
            return null;
        }
    }

    /** Creates a string that will display with a green background when printed. */
    public static String highlightGreen(String text) {
        return "\u001b[42m\u001b[35m" + text + "\u001b[39m\u001b[49m";
    }

    /** Creates a string that will display with a yellow background when printed. */
    public static String highlightYellow(String text) {
        return "\u001b[43m\u001b[34m" + text + "\u001b[39m\u001b[49m";
    }
}
