import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ADDONE {
    private int pgmCount = 0;
    private String prtDate;
    private String prtTime;
    private String prtComment = "My first z/OS COBOL program";
    private String prtMyName = "JIANGWENYUAN";

    public static void main(String[] args) {
        ADDONE addOne = new ADDONE();
        addOne.execute();
    }

    public void execute() {
        try (BufferedWriter writerLine = new BufferedWriter(new FileWriter("PRTLINE"));
             BufferedWriter writerDone = new BufferedWriter(new FileWriter("PRTDONE"))) {

            for (int i = 0; i < 10; i++) {
                pgmCount++;
                writerLine.write(String.format("%05d", pgmCount));
                writerLine.newLine();
            }

            writerDone.write(String.format("%8s %4s %27s %36s", getCurrentDate(), getCurrentTime(), prtComment, prtMyName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(new Date());
    }

    private String getCurrentTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
        return timeFormat.format(new Date());
    }
}