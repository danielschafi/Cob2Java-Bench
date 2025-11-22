public class ANAGRAMM {
    private String currentWord = "                    ";
    private String currentWordReversed = "                    ";
    private String currentResult = "            ";
    private int positionStringEnd = 0;
    private int counter = 0;
    private char istAnagramKz = ' ';
    private String stringAusDb = "  ";

    public static void main(String[] args) {
        ANAGRAMM program = new ANAGRAMM();
        program.procedureDivision();
    }

    private void procedureDivision() {
        System.out.println(currentResult);
    }

    private void process1000Word() {
        currentResult = "JA          ";
        
        find1001StringEnde();
        
        String substringToReverse = currentWord.substring(0, positionStringEnd);
        currentWordReversed = reverseString(substringToReverse) + 
                             currentWord.substring(positionStringEnd);
        
        for (counter = 0; counter < 20; counter++) {
            if (currentWord.charAt(counter) != currentWordReversed.charAt(counter)) {
                currentResult = "NEIN        ";
                break;
            }
        }
    }

    private void find1001StringEnde() {
        positionStringEnd = 0;
        int trailingSpaces = 0;
        
        for (int i = currentWord.length() - 1; i >= 0; i--) {
            if (currentWord.charAt(i) == ' ') {
                trailingSpaces++;
            } else {
                break;
            }
        }
        
        positionStringEnd = currentWord.length() - trailingSpaces;
    }

    private String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}