public class OldLady {
    static class Animal {
        String name;
        String verse;
        
        Animal(String name, String verse) {
            this.name = name;
            this.verse = verse;
        }
    }
    
    static Animal[] animals = {
        new Animal("fly", "I don't know why she swallowed a fly. Perhaps she'll die."),
        new Animal("spider", "That wiggled and jiggled and tickled inside her."),
        new Animal("bird", "How absurd, to swallow a bird."),
        new Animal("cat", "Imagine that, she swallowed a cat."),
        new Animal("dog", "What a hog, to swallow a dog."),
        new Animal("goat", "She just opened her throat and swallowed that goat."),
        new Animal("cow", "I don't know how she swallowed that cow."),
        new Animal("horse", "She's dead, of course.")
    };
    
    static String thereWas = "There was an old lady who swallowed a ";
    static String sheSwallowed = "She swallowed the ";
    static String toCatch = " to catch the ";
    
    public static void main(String[] args) {
        for (int aIdx = 0; aIdx < 8; aIdx++) {
            doAnimal(aIdx);
        }
    }
    
    static void doAnimal(int aIdx) {
        String lineOut = thereWas + animals[aIdx].name.trim() + ",";
        System.out.println(lineOut);
        
        if (aIdx > 0) {
            System.out.println(animals[aIdx].verse);
        }
        
        if (aIdx == 7) {
            return;
        }
        
        for (int sIdx = aIdx; sIdx >= 1; sIdx--) {
            doSwallow(sIdx);
        }
        
        System.out.println(animals[0].verse);
        System.out.println();
    }
    
    static void doSwallow(int sIdx) {
        String lineOut = sheSwallowed + animals[sIdx].name.trim() + toCatch + animals[sIdx - 1].name.trim();
        System.out.println(lineOut);
    }
}