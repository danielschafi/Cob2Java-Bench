import java.io.*;
import java.nio.file.*;
import java.math.BigDecimal;

public class COBPACK2 {
    
    static class RecOutfile {
        String outfileText;
        long outfileUnpacked;
        long outfileUnpackedS;
        int outfileComp04;
        int outfileComp04S;
        long outfileComp09;
        long outfileComp09S;
        long outfileComp18;
        long outfileComp18S;
        int outfileComp304;
        int outfileComp304S;
        long outfileComp309;
        long outfileComp309S;
        long outfileComp318;
        long outfileComp318S;
        char[] group1_1;
        String text2;
        
        RecOutfile() {
            outfileText = "";
            outfileUnpacked = 0;
            outfileUnpackedS = 0;
            outfileComp04 = 0;
            outfileComp04S = 0;
            outfileComp09 = 0;
            outfileComp09S = 0;
            outfileComp18 = 0;
            outfileComp18S = 0;
            outfileComp304 = 0;
            outfileComp304S = 0;
            outfileComp309 = 0;
            outfileComp309S = 0;
            outfileComp318 = 0;
            outfileComp318S = 0;
            group1_1 = new char[2];
            text2 = "";
        }
    }
    
    static String wsFileStatusOutfile = "  ";
    static long wsIx = 0;
    static RecOutfile recOutfile = new RecOutfile();
    static PrintWriter outfileWriter;
    
    public static void main(String[] args) {
        try {
            System.out.println("COBPACK START...");
            
            openOutputFile();
            
            if (!wsFileStatusOutfile.equals("00")) {
                System.out.println("OPEN OUTFILE FS:  " + wsFileStatusOutfile);
                System.exit(1);
            }
            
            wsIx = -100000000;
            
            while (wsIx <= 100000000) {
                moveToFields(wsIx);
                
                recOutfile.group1_1[0] = 'A';
                recOutfile.group1_1[1] = 'A';
                
                writeRecord();
                
                if (!wsFileStatusOutfile.equals("00")) {
                    System.out.println("WRITE OUTFILE FS:  " + wsFileStatusOutfile);
                    System.exit(1);
                }
                
                wsIx += 2001;
            }
            
            closeOutputFile();
            
            if (!wsFileStatusOutfile.equals("00")) {
                System.out.println("CLOSE OUTFILE FS: " + wsFileStatusOutfile);
                System.exit(1);
            }
            
            System.out.println("COBPACK FINISH..");
            
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    static void openOutputFile() {
        try {
            outfileWriter = new PrintWriter(new FileWriter("OUTFILE"));
            wsFileStatusOutfile = "00";
        } catch (IOException e) {
            wsFileStatusOutfile = "99";
        }
    }
    
    static void closeOutputFile() {
        try {
            if (outfileWriter != null) {
                outfileWriter.close();
            }
            wsFileStatusOutfile = "00";
        } catch (Exception e) {
            wsFileStatusOutfile = "99";
        }
    }
    
    static void moveToFields(long value) {
        recOutfile.outfileText = String.format("%018d", value);
        recOutfile.outfileUnpacked = Math.abs(value);
        recOutfile.outfileUnpackedS = value;
        recOutfile.outfileComp04 = (int) value;
        recOutfile.outfileComp04S = (int) value;
        recOutfile.outfileComp09 = value;
        recOutfile.outfileComp09S = value;
        recOutfile.outfileComp18 = value;
        recOutfile.outfileComp18S = value;
        recOutfile.outfileComp304 = (int) value;
        recOutfile.outfileComp304S = (int) value;
        recOutfile.outfileComp309 = value;
        recOutfile.outfileComp309S = value;
        recOutfile.outfileComp318 = value;
        recOutfile.outfileComp318S = value;
    }
    
    static void writeRecord() {
        try {
            StringBuilder record = new StringBuilder();
            record.append(String.format("%-20s", recOutfile.outfileText));
            record.append(String.format("%018d", recOutfile.outfileUnpacked));
            record.append(String.format("%018d", recOutfile.outfileUnpackedS));
            record.append(String.format("%04d", recOutfile.outfileComp04));
            record.append(String.format("%04d", recOutfile.outfileComp04S));
            record.append(String.format("%09d", recOutfile.outfileComp09));
            record.append(String.format("%09d", recOutfile.outfileComp09S));
            record.append(String.format("%018d", recOutfile.outfileComp18));
            record.append(String.format("%018d", recOutfile.outfileComp18S));
            record.append(String.format("%04d", recOutfile.outfileComp304));
            record.append(String.format("%04d", recOutfile.outfileComp304S));
            record.append(String.format("%09d", recOutfile.outfileComp309));
            record.append(String.format("%09d", recOutfile.outfileComp309S));
            record.append(String.format("%018d", recOutfile.outfileComp318));
            record.append(String.format("%018d", recOutfile.outfileComp318S));
            record.append(recOutfile.group1_1[0]);
            record.append(recOutfile.group1_1[1]);
            record.append(recOutfile.text2);
            
            outfileWriter.println(record.toString());
            wsFileStatusOutfile = "00";
        } catch (Exception e) {
            wsFileStatusOutfile = "99";
        }
    }
}