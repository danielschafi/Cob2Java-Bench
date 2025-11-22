public class Chapt12x {
    
    private static class Kitten {
        String kittenName = "";
    }
    
    private static class Cat {
        String catName = "";
        Kitten[] kittens = new Kitten[7];
        
        Cat() {
            for (int i = 0; i < 7; i++) {
                kittens[i] = new Kitten();
            }
        }
    }
    
    private static class Sack {
        String sackColor = "";
        Cat[] cats = new Cat[7];
        
        Sack() {
            for (int i = 0; i < 7; i++) {
                cats[i] = new Cat();
            }
        }
    }
    
    private static class Wife {
        String wifeName = "";
        Sack[] sacks = new Sack[7];
        
        Wife() {
            for (int i = 0; i < 7; i++) {
                sacks[i] = new Sack();
            }
        }
    }
    
    private static String[] sackColorToUse = {
        "Blue      ",
        "Red       ",
        "Yellow    ",
        "Green     ",
        "Purple    ",
        "Black     ",
        "White     "
    };
    
    private static Wife[] theManOnTheRoad = new Wife[7];
    private static boolean kittenFound = false;
    
    private static int wifeIndex;
    private static int sackIndex;
    private static int catIndex;
    private static int kittenIndex;
    
    public static void main(String[] args) {
        for (int i = 0; i < 7; i++) {
            theManOnTheRoad[i] = new Wife();
        }
        
        for (wifeIndex = 0; wifeIndex < 7; wifeIndex++) {
            for (int sackColorIndex = 0; sackColorIndex < 7; sackColorIndex++) {
                sackIndex = sackColorIndex;
                theManOnTheRoad[wifeIndex].sacks[sackIndex].sackColor = 
                    sackColorToUse[sackColorIndex];
            }
        }
        
        theManOnTheRoad[0].sacks[2].cats[1].kittens[5].kittenName = "Hershey";
        theManOnTheRoad[0].wifeName = "Darlene";
        
        kittenFound = false;
        wifeIndex = 0;
        
        do {
            sackIndex = 0;
            do {
                catIndex = 0;
                do {
                    kittenIndex = 0;
                    while (kittenIndex < 7 && !kittenFound) {
                        if (theManOnTheRoad[wifeIndex].sacks[sackIndex]
                                .cats[catIndex].kittens[kittenIndex].kittenName
                                .equals("Hershey")) {
                            kittenFound = true;
                            break;
                        }
                        kittenIndex++;
                    }
                    catIndex++;
                } while (catIndex < 7 && !kittenFound);
                sackIndex++;
            } while (sackIndex < 7 && !kittenFound);
            wifeIndex++;
        } while (wifeIndex < 7 && !kittenFound);
        
        if (kittenFound) {
            System.out.println("Hershey found in the " +
                theManOnTheRoad[wifeIndex - 1].sacks[sackIndex - 1].sackColor.trim() +
                " Sack, Being carried by " +
                theManOnTheRoad[wifeIndex - 1].wifeName);
        } else {
            System.out.println("Hershey Escaped");
        }
    }
}