import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FIZZBUZZ {
    
    private static final int CNST_MAX = 200;
    private int wsCounter = 0;
    private int wsFizzAux;
    private int wsBuzzAux;
    private String wsTime;
    private String wsHH;
    private String wsMM;
    private String wsSS;
    
    public static void main(String[] args) {
        FIZZBUZZ program = new FIZZBUZZ();
        program.mainline();
    }
    
    private void mainline() {
        System.out.println("+----------------------------------------------+");
        System.out.println("|                FIZZ BUZZ                     |");
        System.out.println("+----------------------------------------------+");
        
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        wsTime = currentTime.format(formatter);
        String[] timeParts = wsTime.split(":");
        wsHH = timeParts[0];
        wsMM = timeParts[1];
        wsSS = timeParts[2];
        
        System.out.println("[Current Time] - " + wsHH + ":" + wsMM + ":" + wsSS);
        
        for (wsCounter = 1; wsCounter <= CNST_MAX; wsCounter++) {
            wsFizzAux = wsCounter % 3;
            wsBuzzAux = wsCounter % 5;
            
            if (wsFizzAux == 0 && wsBuzzAux == 0) {
                System.out.println("Fizz Buzz");
            } else {
                if (wsFizzAux == 0) {
                    System.out.println("Fizz");
                } else {
                    if (wsBuzzAux == 0) {
                        System.out.println("Buzz");
                    } else {
                        System.out.println(wsCounter);
                    }
                }
            }
        }
        
        System.out.println("+----------------------------------------------+");
        System.out.println("|        FIZZ BUZZ - END BATCH                 |");
        System.out.println("+----------------------------------------------+");
        
        System.exit(0);
    }
}