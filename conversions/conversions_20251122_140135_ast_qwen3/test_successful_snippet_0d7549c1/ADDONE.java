import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ADDONE {
    private static int pgmCount = 0;
    private static String prtDate = "        ";
    private static String prtTime = "    ";
    private static String prtComment = "                           ";
    private static String prtMyName = "                                    ";
    private static final String prtRec = "                    ";
    private static final String prtRecDone = "        " + " " + "    " + "  " + "                           " + "  " + "                                    ";

    public static void main(String[] args) {
        try {
            PrintWriter prtLine = new PrintWriter(new FileWriter("PRTLINE"));
            for (int i = 0; i < 10; i++) {
                performA000Count();
                prtLine.println(formatPgmCount());
            }
            prtLine.close();
            
            PrintWriter prtDone = new PrintWriter(new FileWriter("PRTDONE"));
            performA000Done();
            prtDone.println(formatPrtRecDone());
            prtDone.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void performA000Count() {
        pgmCount++;
    }

    private static String formatPgmCount() {
        return String.format("%05d", pgmCount);
    }

    private static void performA000Done() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        
        prtDate = now.format(dateFormatter);
        prtTime = now.format(timeFormatter);
        prtComment = "My first z/OS COBOL program";
        prtMyName = "";
    }

    private static String formatPrtRecDone() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-8s", prtDate));
        sb.append(" ");
        sb.append(String.format("%-4s", prtTime));
        sb.append("  ");
        sb.append(String.format("%-27s", prtComment));
        sb.append("  ");
        sb.append(String.format("%-36s", prtMyName));
        return sb.toString();
    }
}