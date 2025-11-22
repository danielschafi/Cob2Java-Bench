public class TokenizeWithEscaping {
    private static final char ESCAPE_CHAR = '^';
    private static final char SEPARATOR_CHAR = '|';
    private static final int T_LIMIT = 32;
    private static final int L_LIMIT = 16;

    private static class TokenEntry {
        int tokenLen;
        String token;
        
        TokenEntry() {
            this.token = new String(new char[16]);
        }
    }

    private static TokenEntry[] tokenEntry = new TokenEntry[T_LIMIT];
    private static String inputString;
    private static int c;
    private static char escaped;
    private static int t;
    private static int tMax;
    private static int tLim = T_LIMIT;
    private static int l;
    private static int lLim = L_LIMIT;
    private static char errorFound;
    private static String referenceString = "one^|uno||three^^^^|four^^^|^cuatro|";

    static {
        for (int i = 0; i < T_LIMIT; i++) {
            tokenEntry[i] = new TokenEntry();
        }
    }

    public static void main(String[] args) {
        inputString = referenceString;
        tokenize();

        inputString = "token";
        tokenize();

        inputString = "^^^^^^^^";
        tokenize();

        inputString = "||||||||";
        tokenize();

        inputString = "token".repeat(64);
        tokenize();

        inputString = "t|".repeat(32);
        tokenize();

        inputString = "";
        tokenize();
    }

    private static void tokenize() {
        System.out.println();
        System.out.println("string:");
        System.out.println(inputString);

        escaped = 'N';
        errorFound = 'N';
        tMax = 1;
        resetTokenEntry(tMax);
        l = 0;

        for (c = 1; c <= inputString.length(); c++) {
            char currentChar = inputString.charAt(c - 1);
            
            if (escaped == 'N' && currentChar == ESCAPE_CHAR) {
                escaped = 'Y';
            } else if (escaped == 'N' && currentChar == SEPARATOR_CHAR) {
                incrementTMax();
                if (errorFound == 'Y') break;
            } else if (escaped == 'N' || escaped == 'Y') {
                moveC();
                if (errorFound == 'Y') break;
                if (escaped == 'Y') escaped = 'N';
            }
        }

        if (l > 0) {
            tokenEntry[tMax - 1].tokenLen = l;
        }

        if (c == 1) {
            System.out.println("no tokens");
        } else {
            System.out.println("tokens:");
            for (t = 1; t <= tMax; t++) {
                if (tokenEntry[t - 1].tokenLen > 0) {
                    System.out.println(t + ": " + tokenEntry[t - 1].tokenLen + " " + 
                        tokenEntry[t - 1].token.substring(0, tokenEntry[t - 1].tokenLen));
                } else {
                    System.out.println(t + ": " + tokenEntry[t - 1].tokenLen);
                }
            }
        }
    }

    private static void incrementTMax() {
        if (tMax >= tLim) {
            System.out.println("error: at " + c + " number of tokens exceeds " + tLim);
            errorFound = 'Y';
        } else {
            tokenEntry[tMax - 1].tokenLen = l;
            tMax++;
            resetTokenEntry(tMax);
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
            tokenEntry[tMax - 1].token = 
                tokenEntry[tMax - 1].token.substring(0, l - 1) + 
                inputString.charAt(c - 1) + 
                tokenEntry[tMax - 1].token.substring(l);
            errorFound = 'N';
        }
    }

    private static void resetTokenEntry(int index) {
        tokenEntry[index - 1].tokenLen = 0;
        tokenEntry[index - 1].token = new String(new char[16]);
    }
}