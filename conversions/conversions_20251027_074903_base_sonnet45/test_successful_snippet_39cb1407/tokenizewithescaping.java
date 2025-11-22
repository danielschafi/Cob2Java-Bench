public class tokenizewithescaping {
    private static final char ESCAPE_CHAR = '^';
    private static final char SEPARATOR_CHAR = '|';
    private static final String REFERENCE_STRING = "one^|uno||three^^^^|four^^^|^cuatro|";
    private static final int T_LIM = 32;
    private static final int L_LIM = 16;

    private String inputString;
    private boolean escaped;
    private int tMax;
    private TokenEntry[] tokenEntry;
    private int l;
    private boolean errorFound;

    private static class TokenEntry {
        int tokenLen;
        StringBuilder token;

        TokenEntry() {
            tokenLen = 0;
            token = new StringBuilder();
        }

        void initialize() {
            tokenLen = 0;
            token.setLength(0);
        }
    }

    public tokenizewithescaping() {
        tokenEntry = new TokenEntry[T_LIM];
        for (int i = 0; i < T_LIM; i++) {
            tokenEntry[i] = new TokenEntry();
        }
    }

    public static void main(String[] args) {
        tokenizewithescaping program = new tokenizewithescaping();
        program.startTokenizeWithEscaping();
    }

    private void startTokenizeWithEscaping() {
        inputString = REFERENCE_STRING;
        tokenize();

        inputString = "token";
        tokenize();

        inputString = "^^^^^^^^";
        tokenize();

        inputString = "||||||||";
        tokenize();

        inputString = repeatString("token", 64);
        tokenize();

        inputString = repeatString("t|", 64);
        tokenize();

        inputString = "";
        tokenize();

        System.out.println();
    }

    private String repeatString(String str, int maxLen) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() + str.length() <= maxLen) {
            sb.append(str);
        }
        return sb.toString();
    }

    private void tokenize() {
        System.out.println();
        System.out.println("string:");
        System.out.println(inputString);

        escaped = false;
        errorFound = false;
        tMax = 1;
        tokenEntry[tMax - 1].initialize();
        l = 0;

        String trimmedInput = inputString.trim();
        int length = trimmedInput.length();

        for (int c = 0; c < length; c++) {
            char currentChar = trimmedInput.charAt(c);

            if (!escaped && currentChar == ESCAPE_CHAR) {
                escaped = true;
            } else if (!escaped && currentChar == SEPARATOR_CHAR) {
                incrementTMax();
                if (errorFound) {
                    return;
                }
            } else if (!escaped) {
                moveC(currentChar, c + 1);
                if (errorFound) {
                    return;
                }
            } else {
                moveC(currentChar, c + 1);
                if (errorFound) {
                    return;
                }
                escaped = false;
            }
        }

        if (l > 0) {
            tokenEntry[tMax - 1].tokenLen = l;
        }

        if (length == 0) {
            System.out.println("no tokens");
        } else {
            System.out.println("tokens:");
            for (int t = 1; t <= tMax; t++) {
                if (tokenEntry[t - 1].tokenLen > 0) {
                    System.out.println(t + ": " + tokenEntry[t - 1].tokenLen + " " + tokenEntry[t - 1].token.toString());
                } else {
                    System.out.println(t + ": " + tokenEntry[t - 1].tokenLen);
                }
            }
        }
    }

    private void incrementTMax() {
        if (tMax >= T_LIM) {
            System.out.println("error: at " + (l + 1) + " number of tokens exceeds " + T_LIM);
            errorFound = true;
        } else {
            tokenEntry[tMax - 1].tokenLen = l;
            tMax++;
            tokenEntry[tMax - 1].initialize();
            l = 0;
            errorFound = false;
        }
    }

    private void moveC(char ch, int position) {
        if (l >= L_LIM) {
            System.out.println("error: at " + position + " token length exceeds " + L_LIM);
            errorFound = true;
        } else {
            l++;
            tokenEntry[tMax - 1].token.append(ch);
            errorFound = false;
        }
    }
}