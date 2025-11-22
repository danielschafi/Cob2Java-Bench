import java.util.*;

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

    private static class WordStack {
        private String[] words = new String[10];
        private int ptr = 0;

        public void push(String word) {
            if (ptr < 10) {
                words[ptr++] = word;
            }
        }

        public String pop() {
            if (ptr > 0) {
                return words[--ptr];
            }
            return null;
        }

        public int getPtr() {
            return ptr;
        }

        public void setPtr(int ptr) {
            this.ptr = ptr;
        }
    }

    public static void main(String[] args) {
        WordStack wordStack = new WordStack();
        
        for (int i = 0; i < 10; i++) {
            pushWords(i, wordStack);
            popWords(wordStack);
        }
    }

    private static void pushWords(int lineIndex, WordStack wordStack) {
        String line = TEXT_BLOCK[lineIndex];
        int pos = 0;
        wordStack.setPtr(0);

        while (pos < 36) {
            StringBuilder word = new StringBuilder();
            int start = pos;
            
            while (start < 36 && line.charAt(start) == ' ') {
                start++;
            }
            
            if (start >= 36) {
                break;
            }
            
            int end = start;
            while (end < 36 && line.charAt(end) != ' ') {
                word.append(line.charAt(end));
                end++;
            }
            
            pos = end;
            wordStack.push(word.toString());
        }
    }

    private static void popWords(WordStack wordStack) {
        for (int i = wordStack.getPtr() - 1; i >= 0; i--) {
            System.out.print(wordStack.pop() + " ");
        }
        System.out.println();
    }
}