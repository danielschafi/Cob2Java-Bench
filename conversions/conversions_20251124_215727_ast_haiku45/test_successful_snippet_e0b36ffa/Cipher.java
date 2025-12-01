public class Cipher {
    private String str = "   ";
    private String og = "   ";
    private String encrypted = "   ";
    private int offset = 0;
    private int Counter = 0;
    private int i = 0;
    private String c = " ";

    public static void main(String[] args) {
        Cipher cipher = new Cipher();
        cipher.begin();
    }

    private void begin() {
        str = "HAL";
        og = str;
        offset = 3;

        System.out.println("Original ------> " + str);
        encrypt();
        encrypted = str;
        decrypt();

        Counter = 0;
        offset = 0;
        System.out.println("Solve:");
        while (Counter != 26) {
            solve();
        }
    }

    private void encrypt() {
        og = og.toUpperCase();

        if (offset >= 26) {
            offset = offset % 26;
        }

        for (i = 0; i < og.length(); i++) {
            if (og.charAt(i) != ' ') {
                c = String.valueOf(og.charAt(i));
                int charCode = (int) c.charAt(0);
                int zCode = (int) 'Z';

                if ((charCode + offset) <= zCode) {
                    str = setCharAt(str, i, (char) (charCode + offset));
                } else {
                    int aCode = (int) 'A';
                    str = setCharAt(str, i, (char) (aCode + ((charCode + offset) - 1) - zCode));
                }
            }
        }

        System.out.println("Encrypted " + og + " -> " + str + " with Key:" + offset);
    }

    private void decrypt() {
        str = str.toUpperCase();

        if (offset >= 26) {
            offset = offset % 26;
        }

        for (i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                c = String.valueOf(str.charAt(i));
                int charCode = (int) c.charAt(0);
                int aCode = (int) 'A';

                if ((charCode - offset) >= aCode) {
                    str = setCharAt(str, i, (char) (charCode - offset));
                } else {
                    int zCode = (int) 'Z';
                    str = setCharAt(str, i, (char) (zCode - ((offset - 1) - (charCode - aCode))));
                }
            }
        }

        System.out.println("Decrypted " + encrypted + " -> " + str + " with Key:" + offset);
    }

    private void solve() {
        Counter++;
        offset++;
        encrypt();
    }

    private String setCharAt(String s, int index, char c) {
        if (index < 0 || index >= s.length()) {
            return s;
        }
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }
}