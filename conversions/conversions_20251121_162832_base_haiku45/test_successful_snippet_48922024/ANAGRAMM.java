public class ANAGRAMM {
    private String CURRENT_WORD = "";
    private String CURRENT_WORD_REVERSED = "";
    private String CURRENT_RESULT = "";
    private int POSITION_STRING_END = 0;
    private int COUNTER = 0;
    private String IST_ANAGRAM_KZ = "";
    private String STRING_AUS_DB = "";

    public static void main(String[] args) {
        ANAGRAMM program = new ANAGRAMM();
        program.procedureDivision();
    }

    private void procedureDivision() {
        System.out.println(CURRENT_RESULT);
    }

    private void processWord() {
        CURRENT_RESULT = "JA";
        findStringEnde();

        String substring = CURRENT_WORD.substring(0, POSITION_STRING_END);
        CURRENT_WORD_REVERSED = new StringBuilder(substring).reverse().toString();

        for (COUNTER = 1; COUNTER <= 20; COUNTER++) {
            if (COUNTER - 1 < CURRENT_WORD.length() && COUNTER - 1 < CURRENT_WORD_REVERSED.length()) {
                if (CURRENT_WORD.charAt(COUNTER - 1) != CURRENT_WORD_REVERSED.charAt(COUNTER - 1)) {
                    CURRENT_RESULT = "NEIN";
                    break;
                }
            }
        }
    }

    private void findStringEnde() {
        POSITION_STRING_END = 0;
        
        int trailingSpaces = 0;
        for (int i = CURRENT_WORD.length() - 1; i >= 0; i--) {
            if (CURRENT_WORD.charAt(i) == ' ') {
                trailingSpaces++;
            } else {
                break;
            }
        }
        
        POSITION_STRING_END = CURRENT_WORD.length() - trailingSpaces;
    }
}