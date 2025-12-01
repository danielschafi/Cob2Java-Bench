public class Anagramm {
    private String currentWord = "";
    private String currentWordReversed = "";
    private String currentResult = "";
    private int positionStringEnd = 0;
    private int counter = 0;
    private String istAnagramKz = "";
    private String stringAusDb = "";

    public static void main(String[] args) {
        Anagramm program = new Anagramm();
        program.run();
    }

    public void run() {
        System.out.println(currentResult);
    }

    public void processWord() {
        currentResult = "JA";
        findStringEnde();
        currentWordReversed = new StringBuilder(currentWord.substring(0, positionStringEnd)).reverse().toString();
        for (counter = 1; counter <= 20; counter++) {
            if (counter - 1 < currentWord.length() && counter - 1 < currentWordReversed.length()) {
                if (currentWord.charAt(counter - 1) != currentWordReversed.charAt(counter - 1)) {
                    currentResult = "NEIN";
                    break;
                }
            }
        }
    }

    public void findStringEnde() {
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
}