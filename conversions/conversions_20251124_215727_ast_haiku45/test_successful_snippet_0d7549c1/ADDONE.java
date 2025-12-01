import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ADDONE {
    private int pgmCount = 0;
    private long yyyymmdd = 0;
    private int integerForm = 0;
    private String refmodTimeItem = "        ";
    
    private PrintWriter prtLineWriter;
    private PrintWriter prtDoneWriter;
    
    private String prtRec = "";
    private String prtDate = "        ";
    private String prtTime = "    ";
    private String prtComment = "                           ";
    private String prtMyName = "                                    ";
    
    public static void main(String[] args) {
        ADDONE program = new ADDONE();
        program.run();
    }
    
    public void run() {
        try {
            a000Start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void a000Start() throws IOException {
        openOutputPrtLine();
        for (int i = 0; i < 10; i++) {
            a000Count();
        }
        a000Done();
        closePrtLine();
    }
    
    private void a000Count() throws IOException {
        pgmCount++;
        writePrtRec(String.valueOf(pgmCount));
    }
    
    private void a000Done() throws IOException {
        openOutputPrtDone();
        prtDate = "        ";
        prtTime = "    ";
        prtComment = "                           ";
        prtMyName = "                                    ";
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        refmodTimeItem = now.format(timeFormatter);
        if (refmodTimeItem.length() < 8) {
            refmodTimeItem = refmodTimeItem + "    ".substring(refmodTimeItem.length());
        }
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String currentDate = now.format(dateFormatter);
        yyyymmdd = Long.parseLong(currentDate);
        
        prtDate = currentDate.substring(0, 8);
        prtTime = refmodTimeItem.substring(0, 4);
        prtComment = "My first z/OS COBOL program";
        
        writePrtRecDone();
        closePrtDone();
    }
    
    private void openOutputPrtLine() throws IOException {
        prtLineWriter = new PrintWriter(new FileWriter("PRTLINE"));
    }
    
    private void openOutputPrtDone() throws IOException {
        prtDoneWriter = new PrintWriter(new FileWriter("PRTDONE"));
    }
    
    private void closePrtLine() throws IOException {
        if (prtLineWriter != null) {
            prtLineWriter.close();
        }
    }
    
    private void closePrtDone() throws IOException {
        if (prtDoneWriter != null) {
            prtDoneWriter.close();
        }
    }
    
    private void writePrtRec(String value) throws IOException {
        String record = String.format("%-80s", value);
        if (record.length() > 80) {
            record = record.substring(0, 80);
        }
        prtLineWriter.println(record);
        prtLineWriter.flush();
    }
    
    private void writePrtRecDone() throws IOException {
        StringBuilder record = new StringBuilder();
        record.append(padRight(prtDate, 8));
        record.append(" ");
        record.append(padRight(prtTime, 4));
        record.append("  ");
        record.append(padRight(prtComment, 27));
        record.append("  ");
        record.append(padRight(prtMyName, 36));
        
        String output = record.toString();
        if (output.length() > 80) {
            output = output.substring(0, 80);
        } else if (output.length() < 80) {
            output = String.format("%-80s", output);
        }
        prtDoneWriter.println(output);
        prtDoneWriter.flush();
    }
    
    private String padRight(String str, int length) {
        if (str == null) {
            str = "";
        }
        if (str.length() >= length) {
            return str.substring(0, length);
        }
        return String.format("%-" + length + "s", str);
    }
}