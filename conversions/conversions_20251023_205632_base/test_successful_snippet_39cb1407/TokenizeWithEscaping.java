import java.util.Arrays;

public class TokenizeWithEscaping {
    private static final char ESCAPE_CHAR = '^';
    private static final char SEPARATOR_CHAR = '|';
    private static final String REFERENCE_STRING = "one^|uno||three^^^^|four^^^|^cuatro|";
    private static final int T_LIM = 32;
    private static final int L_LIM = 16;

    private static String inputString;
    private static int c;
    private static char escaped;
    private static int t;
    private static int tMax;
    private static int tLim = T_LIM;
    private static int[][] tokenEntry = new int[T_LIM][L_LIM + 1];
    private static int l;
    private static int lLim = L_LIM;
    private static char errorFound;

    public static void main(String[] args) {
        inputString = REFERENCE_STRING;
        tokenize();

        inputString = "token";
        tokenize();

        inputString = "^^^^^^^^";
        tokenize();

        inputString = "||||||||";
        tokenize();

        inputString = String.format("%" + 64 + "s", " ").replace(' ', 't');
        tokenize();

        inputString = String.format("%" + 64 + "s", " ").replace(' ', 't').replace('t', 'o').substring(0, 2) + "|";
        tokenize();

        inputString = String.format("%" + 64 + "s", " ");
        tokenize();
    }

    private static void tokenize() {
        System.out.println();
        System.out.println("string:");
        System.out.println(inputString);

        errorFound = 'N';
        tMax = 1;
        Arrays.fill(tokenEntry[tMax - 1], 0);
        l = 0;

        for (c = 1; c <= inputString.length(); c++) {
            if (c > inputString.length() || inputString.charAt(c - 1) == ' ') {
                break;
            }

            switch (escaped) {
                case 'N':
                    if (inputString.charAt(c - 1) == ESCAPE_CHAR) {
                        escaped = 'Y';
                    } else if (inputString.charAt(c - 1) == SEPARATOR_CHAR) {
                        incrementTMax();
                        if (errorFound == 'Y') {
                            return;
                        }
                    } else {
                        moveC();
                        if (errorFound == 'Y') {
                            return;
                        }
                    }
                    break;
                case 'Y':
                    moveC();
                    if (errorFound == 'Y') {
                        return;
                    }
                    escaped = 'N';
                    break;
            }
        }

        if (l > 0) {
            tokenEntry[tMax - 1][0] = l;
        }

        if (c == 1) {
            System.out.println("no tokens");
        } else {
            System.out.println("tokens:");
            for (t = 1; t <= tMax; t++) {
                if (tokenEntry[t - 1][0] > 0) {
                    System.out.println(t + ": " + tokenEntry[t - 1][0] + " " + new String(tokenEntry[t - 1], 1, tokenEntry[t - 1][0]));
                } else {
                    System.out.println(t + ": " + tokenEntry[t - 1][0]);
                }
            }
        }
    }

    private static void incrementTMax() {
        if (tMax >= tLim) {
            System.out.println("error: at " + c + " number of tokens exceeds " + tLim);
            errorFound = 'Y';
        } else {
            tokenEntry[tMax - 1][0] = l;
            tMax++;
            Arrays.fill(tokenEntry[tMax - 1], 0);
            l = 0;
            errorFound = 'N';
        }
    }

    private static void moveC() {
        if (l >= lLim) {
            System.out.println("error: at " + c + " token length exceeds " + lLim);
            errorFound = 'Y';
        } else {
            l++;
            tokenEntry[tMax - 1][l] = inputString.charAt(c - 1);
            errorFound = 'N';
        }
    }
}