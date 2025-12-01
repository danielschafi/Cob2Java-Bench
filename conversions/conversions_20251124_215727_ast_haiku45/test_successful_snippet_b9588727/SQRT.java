import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class SQRT {
    private static double DIFF = 0.0;
    private static double Z = 0.0;
    private static int K = 0;
    private static double X = 0.0;
    private static double Y = 0.0;
    private static double TEMP = 0.0;
    
    private static double IN_Z = 0.0;
    private static double IN_DIFF = 0.0;
    
    private static double OUT_Z = 0.0;
    private static double OUT_Y = 0.0;
    private static double OT_Z = 0.0;
    private static double OUTP_Z = 0.0;
    
    private static BufferedReader reader;
    private static PrintWriter writer;
    
    public static void main(String[] args) {
        try {
            reader = new BufferedReader(new FileReader("sqrtFIXED.dat"));
            writer = new PrintWriter(System.out, true);
            
            printTitleLine();
            printUnderLine();
            printColHeads();
            printUnderline2();
            
            s1();
            
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void printTitleLine() {
        writer.println("         SQUARE ROOT APPROXIMATIONS");
    }
    
    private static void printUnderLine() {
        writer.println("--------------------------------------------");
    }
    
    private static void printColHeads() {
        writer.println("        NUMBER               SQUARE ROOT");
    }
    
    private static void printUnderline2() {
        writer.println(" -------------------     ------------------");
    }
    
    private static void s1() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            finish();
            return;
        }
        
        try {
            parseInputCard(line);
        } catch (Exception e) {
            finish();
            return;
        }
        
        if (IN_Z <= 0) {
            printErrorMessage();
            s1();
            return;
        }
        
        b1();
    }
    
    private static void parseInputCard(String line) {
        if (line.length() < 17) {
            throw new NumberFormatException("Invalid input format");
        }
        String zStr = line.substring(0, 17).trim();
        String diffStr = line.substring(17, 22).trim();
        
        IN_Z = Double.parseDouble(zStr);
        IN_DIFF = Double.parseDouble(diffStr);
    }
    
    private static void b1() throws IOException {
        DIFF = IN_DIFF;
        Z = IN_Z;
        X = Math.round(Z / 2.0 * 1000000.0) / 1000000.0;
        
        for (K = 1; K <= 1000; K++) {
            s2();
            if (K > 1000) break;
        }
        
        OUTP_Z = IN_Z;
        printAbortMessage();
        s1();
    }
    
    private static void s2() throws IOException {
        Y = Math.round(0.5 * (X + Z / X) * 1000000.0) / 1000000.0;
        TEMP = Y - X;
        
        if (TEMP < 0) {
            TEMP = -TEMP;
        }
        
        if (TEMP / (Y + X) > DIFF) {
            e2();
            return;
        }
        
        OUT_Z = IN_Z;
        OUT_Y = Y;
        printLine();
        s1();
    }
    
    private static void e2() {
        X = Y;
    }
    
    private static void finish() {
        System.exit(0);
    }
    
    private static void printErrorMessage() {
        DecimalFormat df = new DecimalFormat("-(11)9.9(6)");
        writer.println(" " + formatNumber(OT_Z, 18) + "        INVALID INPUT");
    }
    
    private static void printAbortMessage() {
        writer.println(" " + formatNumber(OUTP_Z, 18) + "  ATTEMPT ABORTED,TOO MANY ITERATIONS");
    }
    
    private static void printLine() {
        writer.println(" " + formatNumber(OUT_Z, 18) + "     " + formatNumber(OUT_Y, 18));
    }
    
    private static String formatNumber(double num, int width) {
        DecimalFormat df = new DecimalFormat("0.000000");
        String formatted = df.format(num);
        return String.format("%" + width + "s", formatted);
    }
}