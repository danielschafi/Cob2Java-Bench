import java.util.Arrays;

public class Sndxtest {
    public static void main(String[] args) {
        String[] sampleWords = {
            "soundex", "example", "sownteks", "ekzampul", "Euler", "Gauss", "Hilbert", "Knuth", "Lloyd", "Lukasiewicz",
            "Ellery", "ghosh", "Heilbronn", "Kand", "Ladd", "lissajous", "Wheaton", "Burroughs", "burrows", "O'Hara",
            "Washington", "lee", "Gutierrez", "Phister", "Jackson", "tymczak", "Vandeusen", "Ashcraft"
        };

        for (String word : sampleWords) {
            String soundexCode = sndxenc(word);
            System.out.println(soundexCode + " " + word);
        }
    }

    public static String sndxenc(String str) {
        char[] sdxCode = new char[4];
        Arrays.fill(sdxCode, '0');
        sdxCode[0] = Character.toUpperCase(str.charAt(0));

        int letCode = sndxchar(sdxCode[0]);
        int prvLetCode = letCode;

        int sdxIdx = 1;
        for (int i = 1; i < str.length() && sdxIdx < 4; i++) {
            letCode = sndxchar(str.charAt(i));
            if (letCode != 7) {
                if (letCode != 0 && letCode != prvLetCode) {
                    sdxCode[sdxIdx++] = Character.forDigit(letCode, 10);
                }
                prvLetCode = letCode;
            }
        }

        return new String(sdxCode);
    }

    public static int sndxchar(char ch) {
        ch = Character.toLowerCase(ch);
        if (ch == 'b' || ch == 'f' || ch == 'p' || ch == 'v') return 1;
        if (ch == 'c' || ch == 'g' || ch == 'j' || ch == 'k' || ch == 'q' || ch == 's' || ch == 'x' || ch == 'z') return 2;
        if (ch == 'd' || ch == 't') return 3;
        if (ch == 'l') return 4;
        if (ch == 'm' || ch == 'n') return 5;
        if (ch == 'r') return 6;
        if (ch == 'h' || ch == 'w') return 7;
        return 0;
    }
}