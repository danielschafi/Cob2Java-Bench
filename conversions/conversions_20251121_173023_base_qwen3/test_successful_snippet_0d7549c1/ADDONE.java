import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ADDONE {
    private static int pgmCount = 0;
    private static PrintWriter prtLineWriter;
    private static PrintWriter prtDoneWriter;

    public static void main(String[] args) {
        try {
            prtLineWriter = new PrintWriter(new FileWriter("PRTLINE"));
            prtDoneWriter = new PrintWriter(new FileWriter("PRTDONE"));

            A000_START();

            prtLineWriter.close();
            prtDoneWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void A000_START() {
        for (int i = 0; i < 10; i++) {
            A000_COUNT();
        }
        A000_DONE();
    }

    private static void A000_COUNT() {
        pgmCount++;
        String record = String.format("%05d", pgmCount);
        prtLineWriter.println(record);
        prtLineWriter.flush();
    }

    private static void A000_DONE() {
        try {
            StringBuilder record = new StringBuilder();
            
            // Get current date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String currentDate = dateFormat.format(new Date());
            record.append(currentDate);

            // Add space
            record.append(" ");

            // Get current time (HHMM format)
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
            String currentTime = timeFormat.format(new Date());
            record.append(currentTime);

            // Add spaces
            record.append("  ");

            // Add comment
            record.append("My first z/OS COBOL program");

            // Add spaces
            record.append("  ");

            // Add name (empty as per original)
            record.append("                                    ");

            prtDoneWriter.println(record.toString());
            prtDoneWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}