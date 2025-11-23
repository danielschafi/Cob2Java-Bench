public class OldLady {
    private static String[] names = {"fly", "spider", "bird", "cat", "dog", "goat", "cow", "horse"};
    private static String[] verses = {
        "I don't know why she swallowed a fly. Perhaps she'll die.",
        "That wiggled and jiggled and tickled inside her.",
        "How absurd, to swallow a bird.",
        "Imagine that, she swallowed a cat.",
        "What a hog, to swallow a dog.",
        "She just opened her throat and swallowed that goat.",
        "I don't know how she swallowed that cow.",
        "She's dead, of course."
    };
    private static String THERE_WAS = "There was an old lady who swallowed a ";
    private static String SHE_SWALLOWED = "She swallowed the ";
    private static String TO_CATCH = " to catch the ";

    public static void main(String[] args) {
        for (int aIdx = 0; aIdx < names.length; aIdx++) {
            StringBuilder lineOut = new StringBuilder();
            lineOut.append(THERE_WAS).append(names[aIdx]).append(",");
            System.out.println(lineOut.toString());
            
            if (aIdx > 0) {
                System.out.println(verses[aIdx]);
            }
            
            if (aIdx == names.length - 1) {
                break;
            }
            
            for (int sIdx = aIdx; sIdx > 0; sIdx--) {
                StringBuilder lineOut2 = new StringBuilder();
                lineOut2.append(SHE_SWALLOWED).append(names[sIdx]).append(TO_CATCH).append(names[sIdx - 1]);
                System.out.println(lineOut2.toString());
            }
            
            System.out.println(verses[0]);
            System.out.println();
        }
    }
}