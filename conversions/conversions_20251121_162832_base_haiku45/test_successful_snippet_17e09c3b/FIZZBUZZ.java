```java
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FIZZBUZZ {
    public static void main(String[] args) {
        final int CNST_MAX = 200;
        int WS_COUNTER = 0;
        int WS_FIZZ_AUX = 0;
        int WS_BUZZ_AUX = 0;
        
        System.out.println("+----------------------------------------------+");
        System.out.println("|                FIZZ BUZZ                     |");
        System.out.println("+----------------------------------------------+");
        
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeString = currentTime.format(formatter);
        System.out.println("[Current Time] - " + timeString);
        
        for (WS_COUNTER = 1; WS_COUNTER <= CNST_MAX; WS_COUNTER++) {
            WS_FIZZ_AUX = WS_COUNTER % 3;
            WS_BUZZ_AUX = WS_COUNTER % 5;
            
            if (WS_FIZZ_AUX == 0 && WS_BUZZ_AUX == 0) {
                System.out.println("Fizz Buzz");
            } else if (WS_FIZZ_AUX == 0) {
                System.out.println("Fizz");
            } else if (WS_BUZZ_AUX == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(WS_COUNTER);
            }
        }
        
        System.out.println("+----------------------------------------------+");
        System.out.println("|        FIZZ BUZZ - END BATCH                 |");
        System.out.println("+----------------------------------------------+");
        
        System.exit(0);
    }
}