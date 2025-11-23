import java.io.*;
import java.nio.file.*;

public class YourProgramName {
    private static final String FILE_NAME = "your-file.txt";
    
    public static void main(String[] args) {
        int fsFileo = 0;
        int wsCount = 4;
        int[] wsMyVar = new int[10];
        int wsI = 0;
        
        // Initialize wsMyVar array
        wsMyVar[0] = 123456789;
        wsMyVar[1] = 123456789;
        wsMyVar[2] = 0;
        wsMyVar[3] = -123456789;
        
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_NAME))) {
            // Open output file
            if (fsFileo != 0) {
                System.out.println("OPEN");
                System.out.println(fsFileo);
                System.exit(fsFileo);
            }
            
            // Write first string
            String fileoString = "-NXT-";
            writer.write(fileoString);
            writer.newLine();
            
            // First loop with FILEO-REC
            for (wsI = 0; wsI < wsCount; wsI++) {
                int fileoRec = wsMyVar[wsI];
                writer.write(String.valueOf(fileoRec));
                writer.newLine();
                
                if (fsFileo != 0) {
                    System.out.println("WRITE");
                    System.out.println(fsFileo);
                    System.exit(fsFileo);
                }
            }
            
            // Write second string
            fileoString = "-NXT-";
            writer.write(fileoString);
            writer.newLine();
            
            // Second loop with FILEO-REC1
            for (wsI = 0; wsI < wsCount; wsI++) {
                int fileoRec1 = wsMyVar[wsI];
                writer.write(String.valueOf(fileoRec1));
                writer.newLine();
                
                if (fsFileo != 0) {
                    System.out.println("WRITE");
                    System.out.println(fsFileo);
                    System.exit(fsFileo);
                }
            }
            
            // Write third string
            fileoString = "-NXT-";
            writer.write(fileoString);
            writer.newLine();
            
            // Third loop with FILEO-REC2
            for (wsI = 0; wsI < wsCount; wsI++) {
                int fileoRec2 = wsMyVar[wsI];
                writer.write(String.valueOf(fileoRec2));
                writer.newLine();
                
                if (fsFileo != 0) {
                    System.out.println("WRITE");
                    System.out.println(fsFileo);
                    System.exit(fsFileo);
                }
            }
            
            // Write fourth string
            fileoString = "-NXT-";
            writer.write(fileoString);
            writer.newLine();
            
            // Fourth loop with FILEO-REC3
            for (wsI = 0; wsI < wsCount; wsI++) {
                int fileoRec3 = wsMyVar[wsI];
                writer.write(String.valueOf(fileoRec3));
                writer.newLine();
                
                if (fsFileo != 0) {
                    System.out.println("WRITE");
                    System.out.println(fsFileo);
                    System.exit(fsFileo);
                }
            }
            
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            System.exit(1);
        }
    }
}