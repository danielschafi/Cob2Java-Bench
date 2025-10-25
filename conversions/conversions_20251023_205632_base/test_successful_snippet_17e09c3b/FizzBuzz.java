import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FizzBuzz {
    public static void main(String[] args) {
        final int CNST_MAX = 200;
        int wsCounter;
        int wsFizzAux;
        int wsBuzzAux;

        System.out.println("+----------------------------------------------+");
        System.out.println("|                FIZZ BUZZ                     |");
        System.out.println("+----------------------------------------------+");

        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("[Current Time] - " + currentTime.format(formatter));

        for (wsCounter = 1; wsCounter <= CNST_MAX; wsCounter++) {
            wsFizzAux = wsCounter % 3;
            wsBuzzAux = wsCounter % 5;

            if (wsFizzAux == 0 && wsBuzzAux == 0) {
                System.out.println("Fizz Buzz");
            } else if (wsFizzAux == 0) {
                System.out.println("Fizz");
            } else if (wsBuzzAux == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(wsCounter);
            }
        }

        System.out.println("+----------------------------------------------+");
        System.out.println("|        FIZZ BUZZ - END BATCH                 |");
        System.out.println("+----------------------------------------------+");
    }
}