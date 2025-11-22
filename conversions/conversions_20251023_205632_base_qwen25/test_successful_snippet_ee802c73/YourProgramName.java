import java.io.*;

public class YourProgramName {
    public static void main(String[] args) {
        String fileName = "your-file.txt";
        RandomAccessFile fileO = null;
        String fsFileO = "00";
        int wsCount = 4;
        int wsI;
        int[] wsMyVar = new int[10];

        wsMyVar[0] = 123456789;
        wsMyVar[1] = 123456789;
        wsMyVar[2] = 0;
        wsMyVar[3] = -123456789;

        try {
            fileO = new RandomAccessFile(fileName, "rw");
        } catch (FileNotFoundException e) {
            fsFileO = "01";
            System.out.println("OPEN");
            System.out.println(fsFileO);
            System.out.println("-");
        }

        if (fsFileO.equals("00")) {
            try {
                fileO.writeBytes("-NXT-\r\n");
                for (wsI = 0; wsI < wsCount; wsI++) {
                    fileO.writeInt(wsMyVar[wsI]);
                    if (fsFileO.equals("00")) {
                        System.out.println("WRITE");
                        System.out.println(fsFileO);
                        System.out.println("-");
                    }
                }
                fileO.writeBytes("-NXT-\r\n");
                for (wsI = 0; wsI < wsCount; wsI++) {
                    fileO.writeInt(wsMyVar[wsI]);
                    if (fsFileO.equals("00")) {
                        System.out.println("WRITE");
                        System.out.println(fsFileO);
                        System.out.println("-");
                    }
                }
                fileO.writeBytes("-NXT-\r\n");
                for (wsI = 0; wsI < wsCount; wsI++) {
                    fileO.writeInt(wsMyVar[wsI]);
                    if (fsFileO.equals("00")) {
                        System.out.println("WRITE");
                        System.out.println(fsFileO);
                        System.out.println("-");
                    }
                }
                fileO.writeBytes("-NXT-\r\n");
                for (wsI = 0; wsI < wsCount; wsI++) {
                    fileO.writeInt(wsMyVar[wsI]);
                    if (fsFileO.equals("00")) {
                        System.out.println("WRITE");
                        System.out.println(fsFileO);
                        System.out.println("-");
                    }
                }
                fileO.close();
            } catch (IOException e) {
                fsFileO = "02";
                System.out.println("WRITE");
                System.out.println(fsFileO);
                System.out.println("-");
            }
        }

        if (fsFileO.equals("00")) {
            try {
                fileO.close();
            } catch (IOException e) {
                fsFileO = "03";
                System.out.println("CLOSE");
                System.out.println(fsFileO);
                System.out.println("-");
            }
        }
    }
}