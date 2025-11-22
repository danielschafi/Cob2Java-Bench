public class OLDLADY {
    private static final String THERE_WAS = "There was an old lady who swallowed a ";
    private static final String SHE_SWALLOWED = "She swallowed the ";
    private static final String TO_CATCH = " to catch the ";
    
    private static class Animal {
        String name;
        String verse;
        
        Animal(String name, String verse) {
            this.name = name;
            this.verse = verse;
        }
    }
    
    private static final Animal[] ANIMALS = {
        new Animal("fly", "I don't know why she swallowed a fly. Perhaps she'll die."),
        new Animal("spider", "That wiggled and jiggled and tickled inside her."),
        new Animal("bird", "How absurd, to swallow a bird."),
        new Animal("cat", "Imagine that, she swallowed a cat."),
        new Animal("dog", "What a hog, to swallow a dog."),
        new Animal("goat", "She just opened her throat and swallowed that goat."),
        new Animal("cow", "I don't know how she swallowed that cow."),
        new Animal("horse", "She's dead, of course.")
    };
    
    public static void main(String[] args) {
        for (int aIdx = 0; aIdx < ANIMALS.length; aIdx++) {
            doAnimal(aIdx);
        }
    }
    
    private static void doAnimal(int aIdx) {
        StringBuilder lineOut = new StringBuilder();
        lineOut.append(THERE_WAS).append(ANIMALS[aIdx].name).append(",");
        System.out.println(lineOut.toString());
        
        if (aIdx > 0) {
            System.out.println(ANIMALS[aIdx].verse);
        }
        
        if (aIdx == ANIMALS.length - 1) {
            return;
        }
        
        for (int sIdx = aIdx; sIdx >= 1; sIdx--) {
            doSwallow(sIdx);
        }
        
        System.out.println(ANIMALS[0].verse);
        System.out.println();
    }
    
    private static void doSwallow(int sIdx) {
        StringBuilder lineOut = new StringBuilder();
        lineOut.append(SHE_SWALLOWED)
               .append(ANIMALS[sIdx].name)
               .append(TO_CATCH)
               .append(ANIMALS[sIdx - 1].name);
        System.out.println(lineOut.toString());
    }
}