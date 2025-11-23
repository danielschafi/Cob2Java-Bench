public class Anagramm {
    private static String currentWord = "";
    private static String currentWordReversed = "";
    private static String currentResult = "";
    private static int positionStringEnd = 0;
    private static int counter = 0;
    private static String istAnagramKz = "";

    public static void main(String[] args) {
        System.out.println(currentResult);
        System.exit(0);
    }

    public static void processWord() {
        currentResult = "JA";
        findStringEnd();
        currentWordReversed = reverseString(currentWord.substring(0, positionStringEnd));
        
        for (counter = 1; counter <= 20; counter++) {
            if (currentWord.charAt(counter - 1) != currentWordReversed.charAt(counter - 1)) {
                currentResult = "NEIN";
                break;
            }
        }
    }

    public static void findStringEnd() {
        positionStringEnd = 0;
        int length = currentWord.length();
        for (int i = length - 1; i >= 0; i--) {
            if (currentWord.charAt(i) == ' ') {
                positionStringEnd++;
            } else {
                break;
            }
        }
        positionStringEnd = length - positionStringEnd;
    }

    public static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}