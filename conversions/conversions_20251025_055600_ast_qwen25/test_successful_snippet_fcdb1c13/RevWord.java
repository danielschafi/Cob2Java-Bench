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

    private static class WordEntry {
        int wordLen;
        String word;
    }

    private static final int MAX_LINES = 10;
    private static final int MAX_LINE_LENGTH = 36;
    private static final WordEntry[] WORD_STACK = new WordEntry[MAX_LINES];
    private static final String[] TEXT_LINE = new String[MAX_LINES];
    private static int wordStackPtr;

    static {
        for (int i = 0; i < MAX_LINES; i++) {
            WORD_STACK[i] = new WordEntry();
            TEXT_LINE[i] = TEXT_BLOCK[i];
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < MAX_LINES; i++) {
            pushWords(i);
            popWords();
        }
    }

    private static void pushWords(int lineIndex) {
        int pos = 0;
        wordStackPtr = 0;
        while (pos < MAX_LINE_LENGTH) {
            int start = pos;
            while (pos < MAX_LINE_LENGTH && TEXT_LINE[lineIndex].charAt(pos) != ' ') {
                pos++;
            }
            int wkLen = pos - start;
            String wkWord = TEXT_LINE[lineIndex].substring(start, pos).trim();
            wordStackPtr++;
            WORD_STACK[wordStackPtr - 1].wordLen = wkLen;
            WORD_STACK[wordStackPtr - 1].word = wkWord;
            while (pos < MAX_LINE_LENGTH && TEXT_LINE[lineIndex].charAt(pos) == ' ') {
                pos++;
            }
        }
    }

    private static void popWords() {
        for (int i = wordStackPtr - 1; i >= 0; i--) {
            System.out.print(WORD_STACK[i].word + " ");
        }
        System.out.println();
    }
}