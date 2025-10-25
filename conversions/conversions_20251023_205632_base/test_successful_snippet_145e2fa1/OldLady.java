import java.util.Arrays;

public class OldLady {
    private static final String THERE_WAS = "There was an old lady who swallowed a ";
    private static final String SHE_SAWALLOWED = "She swallowed the ";
    private static final String TO_CATCH = " to catch the ";

    private static final String[][] ANIMALS = {
        {"fly", "I don't know why she swallowed a fly. Perhaps she'll die."},
        {"spider", "That wiggled and jiggled and tickled inside her."},
        {"bird", "How absurd, to swallow a bird."},
        {"cat", "Imagine that, she swallowed a cat."},
        {"dog", "What a hog, to swallow a dog."},
        {"goat", "She just opened her throat and swallowed that goat."},
        {"cow", "I don't know how she swallowed that cow."},
        {"horse", "She's dead, of course."}
    };

    public static void main(String[] args) {
        for (int aIdx = 0; aIdx < ANIMALS.length; aIdx++) {
            doAnimal(aIdx);
        }
    }

    private static void doAnimal(int aIdx) {
        StringBuilder lineOut = new StringBuilder();
        lineOut.append(THERE_WAS).append(ANIMALS[aIdx][0]).append(",");
        System.out.println(lineOut.toString());

        if (aIdx > 0) {
            System.out.println(ANIMALS[aIdx][1]);
        }

        if (aIdx == 7) {
            return;
        }

        for (int sIdx = aIdx; sIdx > 0; sIdx--) {
            doSwallow(sIdx);
        }

        System.out.println(ANIMALS[0][1]);
        System.out.println();
    }

    private static void doSwallow(int sIdx) {
        StringBuilder lineOut = new StringBuilder();
        lineOut.append(SHE_SAWALLOWED).append(ANIMALS[sIdx][0]).append(TO_CATCH).append(ANIMALS[sIdx - 1][0]);
        System.out.println(lineOut.toString());
    }
}