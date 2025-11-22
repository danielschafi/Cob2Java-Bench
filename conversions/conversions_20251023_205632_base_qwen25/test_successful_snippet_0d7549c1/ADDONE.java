import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ADDONE {
    private static String prtRec = "                                                                                ";
    private static String prtDate = "        ";
    private static String prtTime = "    ";
    private static String prtComment = "                           ";
    private static String prtMyName = "                                    ";
    private static int pgmCount = 0;
    private static String yyyyMmDd;
    private static String refmodTimeItem;

    public static void main(String[] args) {
        try (BufferedWriter prtLine = new BufferedWriter(new FileWriter("PRTLINE"))) {
            for (int i = 0; i < 10; i++) {
                A000_COUNT(prtLine);
            }
            A000_DONE();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void A000_COUNT(BufferedWriter prtLine) throws IOException {
        pgmCount++;
        prtRec = String.format("%-80d", pgmCount);
        prtLine.write(prtRec);
        prtLine.newLine();
    }

    private static void A000_DONE() {
        try (BufferedWriter prtDone = new BufferedWriter(new FileWriter("PRTDONE"))) {
            prtDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
            refmodTimeItem = new SimpleDateFormat("HHmm").format(new Date());
            prtRec = String.format("%-8s %-1s %-4s %-2s %-27s %-2s %-36s", prtDate, " ", prtTime, " ", prtComment, " ", prtMyName);
            prtDone.write(prtRec);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}