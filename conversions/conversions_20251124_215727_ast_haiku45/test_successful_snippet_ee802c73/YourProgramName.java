import java.io.*;
import java.nio.file.*;

public class YourProgramName {
    private static int fsFileo = 0;
    private static int wsCount = 0;
    private static int wsI = 0;
    private static long[] wsMyVar = new long[10];
    private static PrintWriter writer;
    private static int returnCode = 0;

    public static void main(String[] args) {
        try {
            wsCount = 4;
            wsMyVar[0] = 123456789L;
            wsMyVar[1] = 123456789L;
            wsMyVar[2] = 0L;
            wsMyVar[3] = -123456789L;

            writer = new PrintWriter(new FileWriter("your-file.txt"));

            fsFileo = 0;
            if (fsFileo != 0) {
                System.out.println("OPEN");
                System.out.println(fsFileo);
                returnCode = fsFileo;
                System.out.println("-");
            }

            writer.println("-NXT-");

            for (wsI = 1; wsI <= wsCount; wsI++) {
                writer.println(wsMyVar[wsI - 1]);
                if (fsFileo != 0) {
                    System.out.println("WRITE");
                    System.out.println(fsFileo);
                    returnCode = fsFileo;
                    System.out.println("-");
                }
            }

            writer.println("-NXT-");

            for (wsI = 1; wsI <= wsCount; wsI++) {
                writer.println(wsMyVar[wsI - 1]);
                if (fsFileo != 0) {
                    System.out.println("WRITE");
                    System.out.println(fsFileo);
                    returnCode = fsFileo;
                    System.out.println("-");
                }
            }

            writer.println("-NXT-");

            for (wsI = 1; wsI <= wsCount; wsI++) {
                writer.println(wsMyVar[wsI - 1]);
                if (fsFileo != 0) {
                    System.out.println("WRITE");
                    System.out.println(fsFileo);
                    returnCode = fsFileo;
                    System.out.println("-");
                }
            }

            writer.println("-NXT-");

            for (wsI = 1; wsI <= wsCount; wsI++) {
                writer.println(wsMyVar[wsI - 1]);
                if (fsFileo != 0) {
                    System.out.println("WRITE");
                    System.out.println(fsFileo);
                    returnCode = fsFileo;
                    System.out.println("-");
                }
            }

            writer.close();
            fsFileo = 0;

            if (fsFileo != 0) {
                System.out.println("CLOSE");
                System.out.println(fsFileo);
                returnCode = fsFileo;
                System.out.println("-");
            }

            System.exit(returnCode);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}