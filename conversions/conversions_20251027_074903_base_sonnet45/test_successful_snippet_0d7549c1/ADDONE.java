import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ADDONE {
    
    private static final int RECORD_SIZE = 80;
    private int pgmCount = 0;
    
    private static class PrtRecDone {
        String prtDate = "";
        String prtTime = "";
        String prtComment = "";
        String prtMyName = "";
        
        public String format() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-8s", prtDate));
            sb.append(" ");
            sb.append(String.format("%-4s", prtTime));
            sb.append("  ");
            sb.append(String.format("%-27s", prtComment));
            sb.append("  ");
            sb.append(String.format("%-36s", prtMyName));
            
            String result = sb.toString();
            if (result.length() > RECORD_SIZE) {
                return result.substring(0, RECORD_SIZE);
            } else {
                return String.format("%-80s", result);
            }
        }
    }
    
    public void run() {
        try (BufferedWriter prtLine = new BufferedWriter(new FileWriter("PRTLINE"))) {
            for (int i = 0; i < 10; i++) {
                a000Count(prtLine);
            }
            a000Done();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private void a000Count(BufferedWriter prtLine) throws IOException {
        pgmCount++;
        String record = String.format("%-80s", String.format("%05d", pgmCount));
        prtLine.write(record);
        prtLine.newLine();
    }
    
    private void a000Done() throws IOException {
        try (BufferedWriter prtDone = new BufferedWriter(new FileWriter("PRTDONE"))) {
            PrtRecDone prtRecDone = new PrtRecDone();
            
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
            
            prtRecDone.prtDate = now.format(dateFormatter);
            prtRecDone.prtTime = now.format(timeFormatter);
            prtRecDone.prtComment = "My first z/OS COBOL program";
            prtRecDone.prtMyName = "";
            
            prtDone.write(prtRecDone.format());
            prtDone.newLine();
        }
    }
    
    public static void main(String[] args) {
        ADDONE program = new ADDONE();
        program.run();
    }
}