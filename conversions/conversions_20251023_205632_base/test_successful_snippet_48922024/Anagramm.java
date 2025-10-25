import java.util.Scanner;

public class Anagramm {
    private String currentWord = new String(new char[20]);
    private String currentWordReversed = new String(new char[20]);
    private String currentResult = new String(new char[12]);
    private int positionStringEnd;
    private int counter;
    private boolean istAnagramKz;

    public static void main(String[] args) {
        Anagramm anagramm = new Anagramm();
        anagramm.processWord();
        System.out.println(anagramm.currentResult);
    }

    public void processWord() {
        currentResult = "JA";
        findStringEnd();
        currentWordReversed = new StringBuilder(currentWord.substring(0, positionStringEnd)).reverse().toString();

        for (counter = 0; counter < 20; counter++) {
            if (counter < currentWord.length() && counter < currentWordReversed.length() &&
                currentWord.charAt(counter) != currentWordReversed.charAt(counter)) {
                currentResult = "NEIN";
                break;
            }
        }
    }

    public void findStringEnd() {
        positionStringEnd = currentWord.length();
        while (positionStringEnd > 0 && currentWord.charAt(positionStringEnd - 1) == ' ') {
            positionStringEnd--;
        }
    }
}