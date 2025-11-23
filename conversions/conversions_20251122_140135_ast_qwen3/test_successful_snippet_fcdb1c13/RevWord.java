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
        private int wkLen;
        private String wkWord;
        
        public TextWord() {
            this.wkWord = "";
        }
    }
    
    private static class WordEntry {
        private int wordLen;
        private String word;
        
        public WordEntry() {
            this.word = "";
        }
    }
    
    private static void pushWords(int i) {
        int pos = 1;
        int wordStackPtr = 0;
        TextWord textWord = new TextWord();
        WordEntry[] wordStack = new WordEntry[10];
        
        for (int j = 0; j < 10; j++) {
            wordStack[j] = new WordEntry();
        }
        
        while (pos <= 36) {
            StringBuilder tempWord = new StringBuilder();
            int len = 0;
            
            while (pos <= 36 && TEXT_BLOCK[i-1].charAt(pos-1) != ' ') {
                tempWord.append(TEXT_BLOCK[i-1].charAt(pos-1));
                len++;
                pos++;
            }
            
            if (len > 0) {
                wordStackPtr++;
                wordStack[wordStackPtr-1].wordLen = len;
                wordStack[wordStackPtr-1].word = tempWord.toString();
            }
            
            while (pos <= 36 && TEXT_BLOCK[i-1].charAt(pos-1) == ' ') {
                pos++;
            }
        }
    }
    
    private static void popWords() {
        WordEntry[] wordStack = new WordEntry[10];
        
        for (int j = 0; j < 10; j++) {
            wordStack[j] = new WordEntry();
        }
        
        int wordStackPtr = 10;
        
        while (wordStackPtr >= 1) {
            System.out.print(wordStack[wordStackPtr-1].word);
            System.out.print(" ");
            wordStackPtr--;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            pushWords(i);
            popWords();
        }
    }
}