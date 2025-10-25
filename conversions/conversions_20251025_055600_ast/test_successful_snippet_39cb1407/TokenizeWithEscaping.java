import java.util.Arrays;

public class TokenizeWithEscaping {
    private static final char ESCAPE_CHAR = '^';
    private static final char SEPARATOR_CHAR = '|';
    private static final String REFERENCE_STRING = "one^|uno||three^^^^|four^^^|^cuatro|";
    private static final int TOKEN_LIMIT = 32;
    private static final int TOKEN_LENGTH_LIMIT = 16;

    public static void main(String[] args) {
        String[] inputStrings = {
            REFERENCE_STRING,
            "token",
            "^^^^^^^^",
            "||||||||",
            "token".repeat(64),
            "t|".repeat(32),
            " ".repeat(64)
        };

        for (String inputString : inputStrings) {
            tokenize(inputString);
        }
        System.out.println();
    }

    private static void tokenize(String inputString) {
        System.out.println();
        System.out.println("string:");
        System.out.println(inputString);

        char escaped = 'N';
        char errorFound = 'N';
        int tMax = 1;
        int[][] tokenEntry = new int[TOKEN_LIMIT][TOKEN_LENGTH_LIMIT + 1];
        int l = 0;

        for (int c = 0; c < inputString.length(); c++) {
            char currentChar = inputString.charAt(c);
            if (currentChar == ' ') {
                break;
            }

            switch (escaped + "" + currentChar) {
                case "N^":
                    escaped = 'Y';
                    break;
                case "N|":
                    incrementTMax(tokenEntry, tMax, l);
                    if (tokenEntry[tMax][0] == 1) {
                        errorFound = 'Y';
                    } else {
                        tMax++;
                        l = 0;
                    }
                    break;
                case "N":
                    moveC(tokenEntry, tMax, l, currentChar);
                    if (tokenEntry[tMax][0] == 1) {
                        errorFound = 'Y';
                    } else {
                        l++;
                    }
                    break;
                case "Y":
                    moveC(tokenEntry, tMax, l, currentChar);
                    if (tokenEntry[tMax][0] == 1) {
                        errorFound = 'Y';
                    } else {
                        l++;
                        escaped = 'N';
                    }
                    break;
            }

            if (errorFound == 'Y') {
                break;
            }
        }

        if (l > 0) {
            tokenEntry[tMax][0] = l;
        }

        if (tMax == 1 && l == 0) {
            System.out.println("no tokens");
        } else {
            System.out.println("tokens:");
            for (int t = 1; t <= tMax; t++) {
                int tokenLength = tokenEntry[t][0];
                if (tokenLength > 0) {
                    System.out.print(t + ": " + tokenLength + " ");
                    System.out.println(new String(Arrays.copyOfRange(tokenEntry[t], 1, tokenLength + 1)).trim());
                } else {
                    System.out.println(t + ": " + tokenLength);
                }
            }
        }
    }

    private static void incrementTMax(int[][] tokenEntry, int tMax, int l) {
        if (tMax >= TOKEN_LIMIT) {
            System.out.println("error: at " + (tMax + 1) + " number of tokens exceeds " + TOKEN_LIMIT);
            tokenEntry[tMax][0] = 1;
        } else {
            tokenEntry[tMax][0] = l;
        }
    }

    private static void moveC(int[][] tokenEntry, int tMax, int l, char currentChar) {
        if (l >= TOKEN_LENGTH_LIMIT) {
            System.out.println("error: at " + (l + 1) + " token length exceeds " + TOKEN_LENGTH_LIMIT);
            tokenEntry[tMax][0] = 1;
        } else {
            tokenEntry[tMax][l + 1] = currentChar;
        }
    }
}