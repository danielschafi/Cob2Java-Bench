public class OldLady {
    static class Animal {
        String name;
        String verse;
        
        Animal(String name, String verse) {
            this.name = name;
            this.verse = verse;
        }
    }
    
    public static void main(String[] args) {
        String thereWas = "There was an old lady who swallowed a ";
        String sheSwallowed = "She swallowed the ";
        String toCatch = " to catch the ";
        
        Animal[] animals = {
            new Animal("fly", "I don't know why she swallowed a fly. Perhaps she'll die."),
            new Animal("spider", "That wiggled and jiggled and tickled inside her."),
            new Animal("bird", "How absurd, to swallow a bird."),
            new Animal("cat", "Imagine that, she swallowed a cat."),
            new Animal("dog", "What a hog, to swallow a dog."),
            new Animal("goat", "She just opened her throat and swallowed that goat."),
            new Animal("cow", "I don't know how she swallowed that cow."),
            new Animal("horse", "She's dead, of course.")
        };
        
        for (int aIdx = 0; aIdx < 8; aIdx++) {
            String lineOut = thereWas + animals[aIdx].name + ",";
            System.out.println(lineOut);
            
            if (aIdx > 0) {
                System.out.println(animals[aIdx].verse);
            }
            
            if (aIdx == 7) {
                break;
            }
            
            for (int sIdx = aIdx; sIdx >= 1; sIdx--) {
                lineOut = sheSwallowed + animals[sIdx].name + toCatch + animals[sIdx - 1].name;
                System.out.println(lineOut);
            }
            
            System.out.println(animals[0].verse);
            System.out.println();
        }
    }
}