public class RevWord {
    
    private static class TextWord {
        int wkLen;
        String wkWord;
    }
    
    private static class WordEntry {
        int wordLen;
        String word;
    }
    
    private static final String[] TEXT_LINES = {
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
    
    private static TextWord textWord = new TextWord();
    private static WordEntry[] wordStack = new WordEntry[10];
    private static int i;
    private static int pos;
    private static int wordStackPtr;
    
    static {
        for (int idx = 0; idx < 10; idx++) {
            wordStack[idx] = new WordEntry();
        }
    }
    
    public static void main(String[] args) {
        for (i = 0; i < 10; i++) {
            pushWords();
            popWords();
        }
    }
    
    private static void pushWords() {
        pos = 0;
        wordStackPtr = 0;
        
        while (pos < 36) {
            unstringTextLine();
            if (textWord.wkLen > 0) {
                wordStack[wordStackPtr].wordLen = textWord.wkLen;
                wordStack[wordStackPtr].word = textWord.wkWord;
                wordStackPtr++;
            }
        }
    }
    
    private static void unstringTextLine() {
        String line = TEXT_LINES[i];
        
        while (pos < line.length() && line.charAt(pos) == ' ') {
            pos++;
        }
        
        if (pos >= line.length()) {
            textWord.wkLen = 0;
            textWord.wkWord = "";
            return;
        }
        
        int start = pos;
        while (pos < line.length() && line.charAt(pos) != ' ') {
            pos++;
        }
        
        textWord.wkWord = line.substring(start, pos);
        textWord.wkLen = pos - start;
    }
    
    private static void popWords() {
        for (int ptr = wordStackPtr - 1; ptr >= 0; ptr--) {
            textWord.wordLen = wordStack[ptr].wordLen;
            textWord.wkWord = wordStack[ptr].word;
            System.out.print(textWord.wkWord.substring(0, textWord.wordLen) + " ");
        }
        System.out.println();
    }
}