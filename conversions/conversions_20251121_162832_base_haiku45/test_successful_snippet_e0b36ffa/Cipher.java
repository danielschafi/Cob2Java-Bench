public class Cipher {
    private static String str = "";
    private static String og = "";
    private static String encrypted = "";
    private static int offset = 0;
    private static int counter = 0;
    private static int i = 0;
    private static char c = ' ';

    public static void main(String[] args) {
        begin();
    }

    public static void begin() {
        str = "HAL";
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

    public static void encrypt() {
        og = og.toUpperCase();

        if (offset >= 26) {
            offset = offset % 26;
        }

        StringBuilder result = new StringBuilder(str);
        for (i = 0; i < og.length(); i++) {
            if (og.charAt(i) != ' ') {
                c = og.charAt(i);
                if ((int) c + offset <= (int) 'Z') {
                    result.setCharAt(i, (char) ((int) c + offset));
                } else {
                    result.setCharAt(i, (char) ((int) 'A' + ((int) c + offset - 1) - (int) 'Z'));
                }
            }
        }
        str = result.toString();
        System.out.println("Encrypted " + og + " -> " + str + " with Key:" + offset);
    }

    public static void decrypt() {
        str = str.toUpperCase();

        if (offset >= 26) {
            offset = offset % 26;
        }

        StringBuilder result = new StringBuilder(str);
        for (i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                c = str.charAt(i);
                if ((int) c - offset >= (int) 'A') {
                    result.setCharAt(i, (char) ((int) c - offset));
                } else {
                    result.setCharAt(i, (char) ((int) 'Z' - ((offset - 1) - ((int) c - (int) 'A'))));
                }
            }
        }
        str = result.toString();
        System.out.println("Decrypted " + encrypted + " -> " + str + " with Key:" + offset);
    }

    public static void solve() {
        counter++;
        offset++;
        encrypt();
        System.exit(0);
    }
}