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
        String wifeName = "";
        Sack[] sacks = new Sack[7];
        
        Wife() {
            for (int i = 0; i < 7; i++) {
                sacks[i] = new Sack();
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
    
    static class Cat {
        String catName = "";
        Kitten[] kittens = new Kitten[7];
        
        Cat() {
            for (int i = 0; i < 7; i++) {
                kittens[i] = new Kitten();
            }
        }
    }
    
    static class Kitten {
        String kittenName = "";
    }
    
    static TheManOnTheRoad theManOnTheRoad = new TheManOnTheRoad();
    static String foundFlag = " ";
    static boolean kittenFound = false;
    
    public static void main(String[] args) {
        theManOnTheRoad.wives[0].wifeName = "Darlene";
        theManOnTheRoad.wives[0].sacks[2].sackColor = "Yellow";
        theManOnTheRoad.wives[0].sacks[2].cats[1].kittens[5].kittenName = "Hershey";
        
        int wifeIndex = 0;
        while (wifeIndex < 7 && !kittenFound) {
            int sackIndex = 0;
            while (sackIndex < 7 && !kittenFound) {
                int catIndex = 0;
                while (catIndex < 7 && !kittenFound) {
                    int kittenIndex = 0;
                    while (kittenIndex < 7) {
                        if (theManOnTheRoad.wives[wifeIndex].sacks[sackIndex].cats[catIndex].kittens[kittenIndex].kittenName.equals("Hershey")) {
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
            System.out.print("Hershey found in the ");
            System.out.print(theManOnTheRoad.wives[wifeIndex].sacks[sackIndex].sackColor);
            System.out.print(" Sack, Being carried by ");
            System.out.println(theManOnTheRoad.wives[wifeIndex].wifeName);
        } else {
            System.out.println("Hershey Escaped");
        }
    }
}