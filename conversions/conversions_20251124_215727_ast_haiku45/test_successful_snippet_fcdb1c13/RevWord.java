import java.util.Scanner;

public class RevWord {
    static class WordEntry {
        int wordLen;
        String word;
        
        WordEntry() {
            this.wordLen = 0;
            this.word = "";
        }
        
        WordEntry(int len, String w) {
            this.wordLen = len;
            this.word = w;
        }
    }
    
    static String[] textBlock = {
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
    
    static int wkLen;
    static String wkWord;
    static WordEntry[] wordStack = new WordEntry[10];
    static int i;
    static int pos;
    static int wordStackPtr;
    
    public static void main(String[] args) {
        for (i = 0; i < 10; i++) {
            pushWords();
            popWords();
        }
    }
    
    static void pushWords() {
        pos = 0;
        wordStackPtr = 0;
        String textLine = textBlock[i];
        
        while (pos < textLine.length()) {
            int startPos = pos;
            while (startPos < textLine.length() && textLine.charAt(startPos) == ' ') {
                startPos++;
            }
            
            if (startPos >= textLine.length()) {
                break;
            }
            
            int endPos = startPos;
            while (endPos < textLine.length() && textLine.charAt(endPos) != ' ') {
                endPos++;
            }
            
            wkWord = textLine.substring(startPos, endPos);
            wkLen = wkWord.length();
            
            wordStackPtr++;
            wordStack[wordStackPtr - 1] = new WordEntry(wkLen, wkWord);
            
            pos = endPos;
        }
    }
    
    static void popWords() {
        for (int j = wordStackPtr - 1; j >= 0; j--) {
            WordEntry entry = wordStack[j];
            wkWord = entry.word;
            wkLen = entry.wordLen;
            System.out.print(wkWord.substring(0, wkLen) + " ");
        }
        System.out.println();
    }
}