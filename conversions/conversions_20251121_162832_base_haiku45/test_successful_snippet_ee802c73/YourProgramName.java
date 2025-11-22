import java.io.*;
import java.nio.file.*;

public class YourProgramName {
    private static final String FILENAME = "your-file.txt";
    
    private static int fsFileo = 0;
    private static int wsCount = 0;
    private static int wsI = 0;
    private static long[] wsMyVar = new long[10];
    
    public static void main(String[] args) {
        mainProcedure();
        System.exit(0);
    }
    
    private static void mainProcedure() {
        wsCount = 4;
        wsMyVar[0] = 123456789L;
        wsMyVar[1] = 123456789L;
        wsMyVar[2] = 0L;
        wsMyVar[3] = -123456789L;
        
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(FILENAME);
            fsFileo = 0;
            
            if (fsFileo != 0) {
                System.out.println("OPEN");
                System.out.println(fsFileo);
                System.exit(fsFileo);
                System.out.println("-");
            }
            
            fileWriter.write("-NXT-\n");
            
            for (wsI = 1; wsI <= wsCount; wsI++) {
                fileWriter.write(String.valueOf(wsMyVar[wsI - 1]) + "\n");
                if (fsFileo != 0) {
                    System.out.println("WRITE");
                    System.out.println(fsFileo);
                    System.exit(fsFileo);
                    System.out.println("-");
                }
            }
            
            fileWriter.write("-NXT-\n");
            
            for (wsI = 1; wsI <= wsCount; wsI++) {
                fileWriter.write(String.valueOf(wsMyVar[wsI - 1]) + "\n");
                if (fsFileo != 0) {
                    System.out.println("WRITE");
                    System.out.println(fsFileo);
                    System.exit(fsFileo);
                    System.out.println("-");
                }
            }
            
            fileWriter.write("-NXT-\n");
            
            for (wsI = 1; wsI <= wsCount; wsI++) {
                fileWriter.write(String.valueOf(Math.max(0, wsMyVar[wsI - 1])) + "\n");
                if (fsFileo != 0) {
                    System.out.println("WRITE");
                    System.out.println(fsFileo);
                    System.exit(fsFileo);
                    System.out.println("-");
                }
            }
            
            fileWriter.write("-NXT-\n");
            
            for (wsI = 1; wsI <= wsCount; wsI++) {
                fileWriter.write(String.valueOf(Math.max(0, wsMyVar[wsI - 1])) + "\n");
                if (fsFileo != 0) {
                    System.out.println("WRITE");
                    System.out.println(fsFileo);
                    System.exit(fsFileo);
                    System.out.println("-");
                }
            }
            
            fileWriter.close();
            fsFileo = 0;
            
            if (fsFileo != 0) {
                System.out.println("CLOSE");
                System.out.println(fsFileo);
                System.exit(fsFileo);
                System.out.println("-");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}