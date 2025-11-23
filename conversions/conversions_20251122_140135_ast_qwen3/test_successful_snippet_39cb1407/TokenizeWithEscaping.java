public class TokenizeWithEscaping {
    private static final char ESCAPE_CHAR = '^';
    private static final char SEPARATOR_CHAR = '|';
    private static final String REFERENCE_STRING = "one^|uno||three^^^^|four^^^|^cuatro|";
    private static final int T_LIM = 32;
    private static final int L_LIM = 16;

    private static class TokenEntry {
        int tokenLen;
        String token;
        
        TokenEntry() {
            this.token = new String(new char[16]);
        }
    }

    private static TokenEntry[] tokenEntry = new TokenEntry[T_LIM];
    private static String inputString;
    private static int c;
    private static char escaped;
    private static int t;
    private static int tMax;
    private static int l;
    private static boolean errorFound;

    static {
        for (int i = 0; i < T_LIM; i++) {
            tokenEntry[i] = new TokenEntry();
        }
    }

    public static void main(String[] args) {
        inputString = REFERENCE_STRING;
        tokenize();

        inputString = "token";
        tokenize();

        inputString = "^^^^^^^^";
        tokenize();

        inputString = "||||||||";
        tokenize();

        inputString = "token".repeat(32);
        tokenize();

        inputString = "t|".repeat(32);
        tokenize();

        inputString = "";
        tokenize();

        System.out.println();
    }

    private static void tokenize() {
        System.out.println();
        System.out.println("string:");
        System.out.println(inputString);

        escaped = 'N';
        errorFound = false;
        tMax = 1;
        initializeTokenEntry(tMax);
        l = 0;

        for (c = 1; c <= inputString.length() && !inputString.substring(c - 1).equals(""); c++) {
            char currentChar = inputString.charAt(c - 1);
            
            if (escaped == 'N' && currentChar == ESCAPE_CHAR) {
                escaped = 'Y';
            } else if (escaped == 'N' && currentChar == SEPARATOR_CHAR) {
                incrementTMax();
                if (errorFound) {
                    break;
                }
            } else if (escaped == 'N') {
                moveC();
                if (errorFound) {
                    break;
                }
            } else if (escaped == 'Y') {
                moveC();
                if (errorFound) {
                    break;
                }
                escaped = 'N';
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
        if (tMax >= T_LIM) {
            System.out.println("error: at " + c + " number of tokens exceeds " + T_LIM);
            errorFound = true;
        } else {
            tokenEntry[tMax - 1].tokenLen = l;
            tMax++;
            initializeTokenEntry(tMax);
            l = 0;
            errorFound = false;
        }
    }

    private static void moveC() {
        if (l >= L_LIM) {
            System.out.println("error: at " + c + " token length exceeds " + L_LIM);
            errorFound = true;
        } else {
            l++;
            tokenEntry[tMax - 1].token = 
                tokenEntry[tMax - 1].token.substring(0, l - 1) + 
                inputString.charAt(c - 1) + 
                tokenEntry[tMax - 1].token.substring(l);
            errorFound = false;
        }
    }

    private static void initializeTokenEntry(int index) {
        tokenEntry[index - 1].tokenLen = 0;
        tokenEntry[index - 1].token = new String(new char[16]);
    }
}