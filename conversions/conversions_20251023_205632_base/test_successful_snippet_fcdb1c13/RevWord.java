import java.util.ArrayList;
import java.util.List;

public class RevWord {
    private static final String[] TEXT_BLOCK = {
        "---------- Ice and Fire ------------",
        "                                    ",
        "fire, in end will world the say Some",
        "ice. in say Some                    ",
        "desire of tasted I've what From     ",
        "fire. favor who those with hold I   ",
        "                                    ",
        "... elided paragraph last ...       ",
        "                                    ",
        "Frost Robert -----------------------"
    };

    private static class TextWord {
        int wkLen;
        String wkWord;
    }

    private static class WordEntry {
        int wordLen;
        String word;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            pushWords(TEXT_BLOCK[i]);
            popWords();
        }
    }

    private static List<WordEntry> wordStack = new ArrayList<>();
    private static TextWord textWord = new TextWord();

    private static void pushWords(String textLine) {
        int pos = 0;
        wordStack.clear();
        while (pos < 36) {
            int start = pos;
            while (pos < 36 && textLine.charAt(pos) != ' ') {
                pos++;
            }
            textWord.wkLen = pos - start;
            textWord.wkWord = textLine.substring(start, pos);
            if (textWord.wkLen > 0) {
                WordEntry wordEntry = new WordEntry();
                wordEntry.wordLen = textWord.wkLen;
                wordEntry.word = textWord.wkWord;
                wordStack.add(wordEntry);
            }
            pos++;
        }
    }

    private static void popWords() {
        for (int wordStackPtr = wordStack.size() - 1; wordStackPtr >= 0; wordStackPtr--) {
            WordEntry wordEntry = wordStack.get(wordStackPtr);
            System.out.print(wordEntry.word + " ");
        }
        System.out.println();
    }
}