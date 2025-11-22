import java.util.Arrays;

public class SndxTest {
    private static final String[] SAMPLE_WORDS = {
        "soundex", "example", "sownteks", "ekzampul", "Euler", "Gauss", "Hilbert", "Knuth", "Lloyd", "Lukasiewicz",
        "Ellery", "ghosh", "Heilbronn", "Kand", "Ladd", "lissajous", "Wheaton", "Burroughs", "burrows", "O'Hara",
        "Washington", "lee", "Gutierrez", "Phister", "Jackson", "tymczak", "Vandeusen", "Ashcraft"
    };

    public static void main(String[] args) {
        StringBuilder wrdCode = new StringBuilder(999);
        for (String sampleWord : SAMPLE_WORDS) {
            sndxEnc(sampleWord, wrdCode);
            System.out.println(wrdCode.toString().trim() + " " + sampleWord);
        }
    }

    private static void sndxEnc(String strToEncode, StringBuilder sdxCode) {
        sdxCode.setLength(0);
        sdxCode.append(Character.toUpperCase(strToEncode.charAt(0)));
        int letCode = sndxChar(Character.toLowerCase(strToEncode.charAt(0)));
        int prvLetCode = letCode;

        for (int strIdx = 1; strIdx < Math.min(strToEncode.length(), 15); strIdx++) {
            letCode = sndxChar(Character.toLowerCase(strToEncode.charAt(strIdx)));
            if (letCode != 7) {
                if (letCode != 0 && letCode != prvLetCode) {
                    sdxCode.append(letCode);
                    if (sdxCode.length() >= 4) break;
                }
                prvLetCode = letCode;
            }
        }

        while (sdxCode.length() < 4) {
            sdxCode.append('0');
        }
    }

    private static int sndxChar(char charToEncode) {
        switch (charToEncode) {
            case 'b': case 'f': case 'p': case 'v': return 1;
            case 'c': case 'g': case 'j': case 'k': case 'q': case 's': case 'x': case 'z': return 2;
            case 'd': case 't': return 3;
            case 'l': return 4;
            case 'm': case 'n': return 5;
            case 'r': return 6;
            case 'h': case 'w': return 7;
            default: return 0;
        }
    }
}