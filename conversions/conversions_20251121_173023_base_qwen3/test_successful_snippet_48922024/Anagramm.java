public class Anagramm {
    private static String currentWord = "";
    private static String currentWordReversed = "";
    private static String currentResult = "";
    private static int positionStringEnd = 0;
    private static int counter = 0;
    private static boolean istAnagram = false;
    private static boolean istKeinAnagram = true;
    private static String stringAusDb = "";

    public static void main(String[] args) {
        processWord();
        System.out.println(currentResult);
    }

    public static void processWord() {
        currentResult = "JA";
        findStringEnd();
        currentWordReversed = new StringBuilder(currentWord.substring(0, positionStringEnd)).reverse().toString();
        for (counter = 1; counter <= 20; counter++) {
            if (counter > positionStringEnd) {
                break;
            }
            if (currentWord.charAt(counter - 1) != currentWordReversed.charAt(counter - 1)) {
                currentResult = "NEIN";
                break;
            }
        }
    }

    public static void findStringEnd() {
        positionStringEnd = 0;
        int length = currentWord.length();
        while (positionStringEnd < length && currentWord.charAt(length - 1 - positionStringEnd) == ' ') {
            positionStringEnd++;
        }
        positionStringEnd = length - positionStringEnd;
    }
}