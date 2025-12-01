public class TokenizeWithEscaping {
    private static char escapeChar = '^';
    private static char separatorChar = '|';
    private static String referenceString = "one^|uno||three^^^^|four^^^|^cuatro|";
    
    private static String inputString = "";
    private static int c = 0;
    private static char escaped = 'N';
    
    private static int t = 0;
    private static int tMax = 0;
    private static int tLim = 32;
    
    private static class TokenEntry {
        int tokenLen = 0;
        String token = "";
    }
    
    private static TokenEntry[] tokenEntry = new TokenEntry[32];
    private static int l = 0;
    private static int lLim = 16;
    private static char errorFound = 'N';
    
    public static void main(String[] args) {
        for (int i = 0; i < tokenEntry.length; i++) {
            tokenEntry[i] = new TokenEntry();
        }
        
        inputString = referenceString;
        tokenize();
        
        inputString = "token";
        tokenize();
        
        inputString = "^^^^^^^^";
        tokenize();
        
        inputString = "||||||||";
        tokenize();
        
        inputString = "token".replaceAll(".", "token");
        tokenize();
        
        inputString = "t|".replaceAll(".", "t|");
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
        errorFound = 'N';
        tMax = 1;
        for (int i = 0; i < tokenEntry.length; i++) {
            tokenEntry[i].tokenLen = 0;
            tokenEntry[i].token = "";
        }
        l = 0;
        
        for (c = 0; c < inputString.length(); c++) {
            if (inputString.length() > c && inputString.substring(c).equals("")) {
                break;
            }
            
            char currentChar = inputString.charAt(c);
            
            if (escaped == 'N' && currentChar == escapeChar) {
                escaped = 'Y';
            } else if (escaped == 'N' && currentChar == separatorChar) {
                incrementTMax();
                if (errorFound == 'Y') {
                    break;
                }
            } else if (escaped == 'N') {
                moveC(currentChar);
                if (errorFound == 'Y') {
                    break;
                }
            } else if (escaped == 'Y') {
                moveC(currentChar);
                if (errorFound == 'Y') {
                    break;
                }
                escaped = 'N';
            }
        }
        
        if (l > 0) {
            tokenEntry[tMax - 1].tokenLen = l;
        }
        
        if (c == 0) {
            System.out.println("no tokens");
        } else {
            System.out.println("tokens:");
            for (t = 1; t <= tMax; t++) {
                if (tokenEntry[t - 1].tokenLen > 0) {
                    System.out.println(t + ": " + tokenEntry[t - 1].tokenLen + " " + tokenEntry[t - 1].token);
                } else {
                    System.out.println(t + ": " + tokenEntry[t - 1].tokenLen);
                }
            }
        }
    }
    
    private static void incrementTMax() {
        if (tMax >= tLim) {
            System.out.println("error: at " + (c + 1) + " number of tokens exceeds " + tLim);
            errorFound = 'Y';
        } else {
            tokenEntry[tMax - 1].tokenLen = l;
            tMax++;
            tokenEntry[tMax - 1].tokenLen = 0;
            tokenEntry[tMax - 1].token = "";
            l = 0;
            errorFound = 'N';
        }
    }
    
    private static void moveC(char ch) {
        if (l >= lLim) {
            System.out.println("error: at " + (c + 1) + " token length exceeds " + lLim);
            errorFound = 'Y';
        } else {
            l++;
            String currentToken = tokenEntry[tMax - 1].token;
            if (l - 1 < currentToken.length()) {
                currentToken = currentToken.substring(0, l - 1) + ch + currentToken.substring(l);
            } else {
                currentToken = currentToken + ch;
            }
            tokenEntry[tMax - 1].token = currentToken;
            errorFound = 'N';
        }
    }
}