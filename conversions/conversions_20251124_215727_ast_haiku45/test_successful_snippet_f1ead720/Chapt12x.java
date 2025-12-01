public class Chapt12x {
    static class TheManOnTheRoad {
        Wife[] wives = new Wife[7];
        
        TheManOnTheRoad() {
            for (int i = 0; i < 7; i++) {
                wives[i] = new Wife();
            }
        }
    }
    
    static class Wife {
        String name = "";
        Sack[] sacks = new Sack[7];
        
        Wife() {
            for (int i = 0; i < 7; i++) {
                sacks[i] = new Sack();
            }
        }
    }
    
    static class Sack {
        String color = "";
        Cat[] cats = new Cat[7];
        
        Sack() {
            for (int i = 0; i < 7; i++) {
                cats[i] = new Cat();
            }
        }
    }
    
    static class Cat {
        String name = "";
        Kitten[] kittens = new Kitten[7];
        
        Cat() {
            for (int i = 0; i < 7; i++) {
                kittens[i] = new Kitten();
            }
        }
    }
    
    static class Kitten {
        String name = "";
    }
    
    static String[] sackColorToUse = {
        "Blue     ",
        "Red      ",
        "Yellow   ",
        "Green    ",
        "Purple   ",
        "Black    ",
        "White    "
    };
    
    static boolean kittenFound = false;
    
    public static void main(String[] args) {
        TheManOnTheRoad theManOnTheRoad = new TheManOnTheRoad();
        
        for (int wifeIndex = 0; wifeIndex < 7; wifeIndex++) {
            for (int sackColorIndex = 0; sackColorIndex < 7; sackColorIndex++) {
                int sackIndex = sackColorIndex;
                theManOnTheRoad.wives[wifeIndex].sacks[sackIndex].color = sackColorToUse[sackColorIndex];
            }
        }
        
        theManOnTheRoad.wives[0].sacks[2].cats[1].kittens[5].name = "Hershey";
        theManOnTheRoad.wives[0].name = "Darlene";
        
        int wifeIndex = 0;
        int sackIndex = 0;
        int catIndex = 0;
        int kittenIndex = 0;
        
        outerLoop:
        for (wifeIndex = 0; wifeIndex < 7; wifeIndex++) {
            for (sackIndex = 0; sackIndex < 7; sackIndex++) {
                for (catIndex = 0; catIndex < 7; catIndex++) {
                    kittenIndex = 0;
                    for (kittenIndex = 0; kittenIndex < 7; kittenIndex++) {
                        if (theManOnTheRoad.wives[wifeIndex].sacks[sackIndex].cats[catIndex].kittens[kittenIndex].name.equals("Hershey")) {
                            kittenFound = true;
                            break outerLoop;
                        }
                    }
                }
            }
        }
        
        if (kittenFound) {
            System.out.print("Hershey found in the ");
            System.out.print(theManOnTheRoad.wives[wifeIndex].sacks[sackIndex].color);
            System.out.print(" Sack, Being carried by ");
            System.out.println(theManOnTheRoad.wives[wifeIndex].name);
        } else {
            System.out.println("Hershey Escaped");
        }
    }
}