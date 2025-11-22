public class RevWord {
    static class TextBlock {
        String[] lines = {
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
    }

    static class TextWord {
        int wkLen;
        String wkWord;
    }

    static class WordEntry {
        int wordLen;
        String word;
    }

    static class WordStack {
        WordEntry[] entries = new WordEntry[10];
        
        WordStack() {
            for (int i = 0; i < 10; i++) {
                entries[i] = new WordEntry();
            }
        }
    }

    public static void main(String[] args) {
        TextBlock textBlock = new TextBlock();
        WordStack wordStack = new WordStack();
        
        for (int i = 0; i < 10; i++) {
            pushWords(textBlock.lines[i], wordStack);
            popWords(wordStack);
        }
    }

    static void pushWords(String line, WordStack wordStack) {
        int pos = 0;
        int wordStackPtr = 0;
        
        String[] words = line.trim().split("\\s+");
        
        for (String word : words) {
            if (word.length() > 0) {
                wordStack.entries[wordStackPtr].wordLen = word.length();
                wordStack.entries[wordStackPtr].word = word;
                wordStackPtr++;
            }
        }
        
        for (int i = wordStackPtr - 1; i >= 0; i--) {
            String word = wordStack.entries[i].word;
            int len = wordStack.entries[i].wordLen;
            System.out.print(word.substring(0, len) + " ");
        }
        System.out.println();
    }

    static void popWords(WordStack wordStack) {
    }
}