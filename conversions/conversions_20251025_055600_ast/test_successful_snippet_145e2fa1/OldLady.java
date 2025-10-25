import java.util.Arrays;

public class OldLady {
    private static final String[] ANIMAL_NAMES = {"fly", "spider", "bird", "cat", "dog", "goat", "cow", "horse"};
    private static final String[] ANIMAL_VERSES = {
        "I don't know why she swallowed a fly. Perhaps she'll die.",
        "That wiggled and jiggled and tickled inside her.",
        "How absurd, to swallow a bird.",
        "Imagine that, she swallowed a cat.",
        "What a hog, to swallow a dog.",
        "She just opened her throat and swallowed that goat.",
        "I don't know how she swallowed that cow.",
        "She's dead, of course."
    };
    private static final String THERE_WAS = "There was an old lady who swallowed a ";
    private static final String SHE_SWALLOWED = "She swallowed the ";
    private static final String TO_CATCH = " to catch the ";

    public static void main(String[] args) {
        for (int aIdx = 0; aIdx < 8; aIdx++) {
            doAnimal(aIdx);
        }
    }

    private static void doAnimal(int aIdx) {
        StringBuilder lineOut = new StringBuilder();
        lineOut.append(THERE_WAS).append(ANIMAL_NAMES[aIdx]).append(",");
        System.out.println(lineOut.toString());

        if (aIdx > 0) {
            System.out.println(ANIMAL_VERSES[aIdx]);
        }

        if (aIdx == 7) {
            return;
        }

        for (int sIdx = aIdx; sIdx > 0; sIdx--) {
            doSwallow(sIdx);
        }

        System.out.println(ANIMAL_VERSES[0]);
        System.out.println();
    }

    private static void doSwallow(int sIdx) {
        StringBuilder lineOut = new StringBuilder();
        lineOut.append(SHE_SWALLOWED).append(ANIMAL_NAMES[sIdx]).append(TO_CATCH).append(ANIMAL_NAMES[sIdx - 1]);
        System.out.println(lineOut.toString());
    }
}