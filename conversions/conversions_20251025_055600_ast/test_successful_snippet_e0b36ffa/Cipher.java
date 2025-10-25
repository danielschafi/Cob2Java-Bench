import java.util.Arrays;

public class Cipher {
    private char[] str = new char[3];
    private char[] og = new char[3];
    private char[] encrypted = new char[3];
    private int offset;
    private int counter;
    private int i;
    private char c;

    public static void main(String[] args) {
        Cipher cipher = new Cipher();
        cipher.begin();
    }

    public void begin() {
        Arrays.fill(str, ' ');
        Arrays.fill(og, ' ');
        Arrays.fill(encrypted, ' ');

        String original = "HAL";
        System.arraycopy(original.toCharArray(), 0, str, 0, original.length());
        System.arraycopy(original.toCharArray(), 0, og, 0, original.length());
        offset = 3;

        System.out.println("Original ------> " + new String(str));
        encrypt();
        System.arraycopy(str, 0, encrypted, 0, str.length);
        decrypt();

        counter = 0;
        offset = 0;
        System.out.println("Solve:");
        while (counter < 26) {
            solve();
        }
    }

    public void encrypt() {
        for (int j = 0; j < og.length; j++) {
            og[j] = Character.toUpperCase(og[j]);
        }

        if (offset >= 26) {
            offset = offset % 26;
        }

        for (i = 0; i < og.length; i++) {
            if (og[i] != ' ') {
                c = og[i];
                if ((c + offset) <= 'Z') {
                    str[i] = (char) (c + offset);
                } else {
                    str[i] = (char) ('A' + ((c + offset) - 1) - 'Z');
                }
            }
        }
        System.out.println("Encrypted " + new String(og) + " -> " + new String(str) + " with Key:" + offset);
    }

    public void decrypt() {
        for (int j = 0; j < str.length; j++) {
            str[j] = Character.toUpperCase(str[j]);
        }

        if (offset >= 26) {
            offset = offset % 26;
        }

        for (i = 0; i < str.length; i++) {
            if (str[i] != ' ') {
                c = str[i];
                if ((c - offset) >= 'A') {
                    str[i] = (char) (c - offset);
                } else {
                    str[i] = (char) ('Z' - ((offset - 1) - (c - 'A')));
                }
            }
        }
        System.out.println("Decrypted " + new String(encrypted) + " -> " + new String(str) + " with Key:" + offset);
    }

    public void solve() {
        counter++;
        offset++;
        encrypt();
    }
}