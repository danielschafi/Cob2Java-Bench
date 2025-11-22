public class Chapt12x {
    
    static class SackTableArea {
        String[] sackColorToUse = new String[7];
        
        SackTableArea() {
            sackColorToUse[0] = "Blue     ";
            sackColorToUse[1] = "Red      ";
            sackColorToUse[2] = "Yellow   ";
            sackColorToUse[3] = "Green    ";
            sackColorToUse[4] = "Purple   ";
            sackColorToUse[5] = "Black    ";
            sackColorToUse[6] = "White    ";
        }
    }
    
    static class Kitten {
        String kittenName = "";
    }
    
    static class Cat {
        String catName = "";
        Kitten[] kittens = new Kitten[7];
        
        Cat() {
            for (int i = 0; i < 7; i++) {
                kittens[i] = new Kitten();
            }
        }
    }
    
    static class Sack {
        String sackColor = "";
        Cat[] cats = new Cat[7];
        
        Sack() {
            for (int i = 0; i < 7; i++) {
                cats[i] = new Cat();
            }
        }
    }
    
    static class Wife {
        String wifeName = "";
        Sack[] sacks = new Sack[7];
        
        Wife() {
            for (int i = 0; i < 7; i++) {
                sacks[i] = new Sack();
            }
        }
    }
    
    static class TheManOnTheRoad {
        Wife[] wives = new Wife[7];
        
        TheManOnTheRoad() {
            for (int i = 0; i < 7; i++) {
                wives[i] = new Wife();
            }
        }
    }
    
    public static void main(String[] args) {
        SackTableArea sackTable = new SackTableArea();
        TheManOnTheRoad theMan = new TheManOnTheRoad();
        
        boolean kittenFound = false;
        int wifeIndex = 0;
        int sackIndex = 0;
        int catIndex = 0;
        int kittenIndex = 0;
        
        // Color the different wives sacks
        for (wifeIndex = 0; wifeIndex < 7; wifeIndex++) {
            for (int sackColorIndex = 0; sackColorIndex < 7; sackColorIndex++) {
                sackIndex = sackColorIndex;
                theMan.wives[wifeIndex].sacks[sackIndex].sackColor = 
                    sackTable.sackColorToUse[sackColorIndex];
            }
        }
        
        // Setup the rest of the search so we can find Hershey
        theMan.wives[0].sacks[2].cats[1].kittens[5].kittenName = "Hershey";
        theMan.wives[0].wifeName = "Darlene";
        
        // Search for Hershey
        wifeIndex = 0;
        while (wifeIndex < 7 && !kittenFound) {
            sackIndex = 0;
            while (sackIndex < 7 && !kittenFound) {
                catIndex = 0;
                while (catIndex < 7 && !kittenFound) {
                    kittenIndex = 0;
                    while (kittenIndex < 7) {
                        if (theMan.wives[wifeIndex].sacks[sackIndex].cats[catIndex]
                                .kittens[kittenIndex].kittenName.equals("Hershey")) {
                            kittenFound = true;
                            break;
                        }
                        kittenIndex++;
                    }
                    catIndex++;
                }
                sackIndex++;
            }
            wifeIndex++;
        }
        
        if (kittenFound) {
            System.out.println("Hershey found in the " +
                    theMan.wives[wifeIndex - 1].sacks[sackIndex - 1].sackColor.trim() +
                    " Sack, Being carried by " +
                    theMan.wives[wifeIndex - 1].wifeName.trim());
        } else {
            System.out.println("Hershey Escaped");
        }
    }
}