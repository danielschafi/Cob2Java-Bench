public class Chapt12g {
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
    
    public static void main(String[] args) {
        TheManOnTheRoad theManOnTheRoad = new TheManOnTheRoad();
        boolean kittenFound = false;
        
        theManOnTheRoad.wives[0].sacks[2].cats[1].kittens[5].name = "Hershey";
        theManOnTheRoad.wives[0].name = "Darlene";
        theManOnTheRoad.wives[0].sacks[2].color = "Yellow";
        
        int wifeIndex = 0;
        while (wifeIndex < 7 && !kittenFound) {
            int sackIndex = 0;
            while (sackIndex < 7 && !kittenFound) {
                int catIndex = 0;
                while (catIndex < 7 && !kittenFound) {
                    int kittenIndex = 0;
                    while (kittenIndex < 7) {
                        if (theManOnTheRoad.wives[wifeIndex].sacks[sackIndex].cats[catIndex].kittens[kittenIndex].name.equals("Hershey")) {
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
                    theManOnTheRoad.wives[wifeIndex - 1].sacks[sackIndex - 1].color +
                    " Sack, Being carried by " +
                    theManOnTheRoad.wives[wifeIndex - 1].name);
        } else {
            System.out.println("Hershey Escaped");
        }
    }
}