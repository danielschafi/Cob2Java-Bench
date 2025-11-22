import java.io.*;
import java.time.*;
import java.time.format.*;

public class ADDONE {
    private static int PGM_COUNT = 0;
    private static PrintWriter prtLineWriter;
    private static PrintWriter prtDoneWriter;

    public static void main(String[] args) {
        try {
            A000_START();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void A000_START() throws IOException {
        prtLineWriter = new PrintWriter(new FileWriter("PRTLINE"));
        A000_COUNT_LOOP();
        A000_DONE();
        prtLineWriter.close();
    }

    private static void A000_COUNT_LOOP() {
        for (int i = 0; i < 10; i++) {
            A000_COUNT();
        }
    }

    private static void A000_COUNT() {
        PGM_COUNT++;
        String countStr = String.format("%05d", PGM_COUNT);
        prtLineWriter.println(countStr);
    }

    private static void A000_DONE() throws IOException {
        prtDoneWriter = new PrintWriter(new FileWriter("PRTDONE"));
        
        LocalDateTime now = LocalDateTime.now();
        LocalDate today = now.toLocalDate();
        LocalTime time = now.toLocalTime();
        
        String dateStr = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String timeStr = time.format(DateTimeFormatter.ofPattern("HHmm"));
        
        StringBuilder prtRecDone = new StringBuilder();
        prtRecDone.append(String.format("%-8s", dateStr));
        prtRecDone.append(" ");
        prtRecDone.append(String.format("%-4s", timeStr));
        prtRecDone.append("  ");
        prtRecDone.append(String.format("%-27s", "My first z/OS COBOL program"));
        prtRecDone.append("  ");
        prtRecDone.append(String.format("%-36s", ""));
        
        prtDoneWriter.println(prtRecDone.toString());
        prtDoneWriter.close();
    }
}