import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FIZZBUZZ {
    private static final int CNST_MAX = 200;
    private static int ws_counter = 0;
    private static int ws_fizz_aux = 0;
    private static int ws_buzz_aux = 0;
    private static int ws_hh = 0;
    private static int ws_mm = 0;
    private static int ws_ss = 0;

    public static void main(String[] args) {
        System.out.println("+----------------------------------------------+");
        System.out.println("|                FIZZ BUZZ                     |");
        System.out.println("+----------------------------------------------+");
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeStr = now.format(formatter);
        String[] timeParts = timeStr.split(":");
        ws_hh = Integer.parseInt(timeParts[0]);
        ws_mm = Integer.parseInt(timeParts[1]);
        ws_ss = Integer.parseInt(timeParts[2]);
        
        System.out.println("[Current Time] - " + ws_hh + ":" + ws_mm + ":" + ws_ss);
        
        for (ws_counter = 1; ws_counter <= CNST_MAX; ws_counter++) {
            ws_fizz_aux = ws_counter % 3;
            ws_buzz_aux = ws_counter % 5;
            
            if (ws_fizz_aux == 0 && ws_buzz_aux == 0) {
                System.out.println("Fizz Buzz");
            } else if (ws_fizz_aux == 0) {
                System.out.println("Fizz");
            } else if (ws_buzz_aux == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(ws_counter);
            }
        }
        
        System.out.println("+----------------------------------------------+");
        System.out.println("|        FIZZ BUZZ - END BATCH                 |");
        System.out.println("+----------------------------------------------+");
    }
}