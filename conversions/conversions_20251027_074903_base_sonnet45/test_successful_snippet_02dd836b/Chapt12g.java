import java.util.Arrays;

public class Chapt12g {
    
    static class Kitten {
        String kittenName = "";
    }
    
    static class Cat {
        String catName = "";
        Kitten[] kitten = new Kitten[7];
        
        Cat() {
            for (int i = 0; i < 7; i++) {
                kitten[i] = new Kitten();
            }
        }
    }
    
    static class Sack {
        String sackColor = "";
        Cat[] cat = new Cat[7];
        
        Sack() {
            for (int i = 0; i < 7; i++) {
                cat[i] = new Cat();
            }
        }
    }
    
    static class Wife {
        String wifeName = "";
        Sack[] sack = new Sack[7];
        
        Wife() {
            for (int i = 0; i < 7; i++) {
                sack[i] = new Sack();
            }
        }
    }
    
    static class TheManOnTheRoad {
        Wife[] wife = new Wife[7];
        
        TheManOnTheRoad() {
            for (int i = 0; i < 7; i++) {
                wife[i] = new Wife();
            }
        }
    }
    
    public static void main(String[] args) {
        TheManOnTheRoad theManOnTheRoad = new TheManOnTheRoad();
        boolean kittenFound = false;
        int wifeIndex = 0;
        int sackIndex = 0;
        int catIndex = 0;
        int kittenIndex = 0;
        
        theManOnTheRoad.wife[0].sack[2].cat[1].kitten[5].kittenName = "Hershey";
        theManOnTheRoad.wife[0].wifeName = "Darlene";
        theManOnTheRoad.wife[0].sack[2].sackColor = "Yellow";
        
        wifeIndex = 1;
        do {
            sackIndex = 1;
            do {
                catIndex = 1;
                do {
                    kittenIndex = 1;
                    while (kittenIndex <= 7 && !kittenFound) {
                        if (theManOnTheRoad.wife[wifeIndex - 1].sack[sackIndex - 1]
                                .cat[catIndex - 1].kitten[kittenIndex - 1].kittenName
                                .equals("Hershey")) {
                            kittenFound = true;
                            break;
                        }
                        kittenIndex++;
                    }
                    catIndex++;
                } while (catIndex <= 7 && !kittenFound);
                sackIndex++;
            } while (sackIndex <= 7 && !kittenFound);
            wifeIndex++;
        } while (wifeIndex <= 7 && !kittenFound);
        
        if (kittenFound) {
            System.out.println("Hershey found in the " +
                    theManOnTheRoad.wife[wifeIndex - 1].sack[sackIndex - 1].sackColor +
                    " Sack, Being carried by " +
                    theManOnTheRoad.wife[wifeIndex - 1].wifeName);
        } else {
            System.out.println("Hershey Escaped");
        }
    }
}