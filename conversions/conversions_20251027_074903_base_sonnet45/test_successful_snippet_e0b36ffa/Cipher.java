public class Cipher {
    private static String str = "HAL";
    private static String og = "HAL";
    private static String encrypted = "";
    private static int offset = 3;
    private static int counter = 0;

    public static void main(String[] args) {
        og = str;
        offset = 3;

        System.out.println("Original ------> " + str);
        encrypt();
        encrypted = str;
        decrypt();

        counter = 0;
        offset = 0;
        System.out.println("Solve:");
        while (counter < 26) {
            solve();
        }
    }

    private static void encrypt() {
        og = og.toUpperCase();
        
        if (offset >= 26) {
            offset = offset % 26;
        }

        StringBuilder result = new StringBuilder(str);
        
        for (int i = 0; i < og.length(); i++) {
            char c = og.charAt(i);
            if (c != ' ') {
                int charCode = (int) c;
                if ((charCode + offset) <= (int) 'Z') {
                    result.setCharAt(i, (char) (charCode + offset));
                } else {
                    result.setCharAt(i, (char) ((int) 'A' + ((charCode + offset) - 1) - (int) 'Z'));
                }
            }
        }
        
        str = result.toString();
        System.out.println("Encrypted " + og + " -> " + str + " with Key:" + offset);
    }

    private static void decrypt() {
        str = str.toUpperCase();
        
        if (offset >= 26) {
            offset = offset % 26;
        }

        StringBuilder result = new StringBuilder(str);
        
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ') {
                int charCode = (int) c;
                if ((charCode - offset) >= (int) 'A') {
                    result.setCharAt(i, (char) (charCode - offset));
                } else {
                    result.setCharAt(i, (char) ((int) 'Z' - ((offset - 1) - (charCode - (int) 'A'))));
                }
            }
        }
        
        str = result.toString();
        System.out.println("Decrypted " + encrypted + " -> " + str + " with Key:" + offset);
    }

    private static void solve() {
        counter++;
        offset++;
        encrypt();
        System.exit(0);
    }
}