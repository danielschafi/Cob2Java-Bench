import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class YourProgramName {
    private static final String FILE_NAME = "your-file.txt";
    private static final int[] WS_MY_VAR = new int[10];
    private static int FS_FILEO;
    private static final int FS_FILEO_OK = 0;
    private static int WS_COUNT;
    private static int WS_I;

    public static void main(String[] args) {
        WS_COUNT = 4;
        WS_MY_VAR[0] = 123456789;
        WS_MY_VAR[1] = 123456789;
        WS_MY_VAR[2] = 0;
        WS_MY_VAR[3] = -123456789;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            FS_FILEO = 0;

            if (FS_FILEO != FS_FILEO_OK) {
                System.out.println("OPEN");
                System.out.println(FS_FILEO);
                System.exit(FS_FILEO);
                System.out.println("-");
            }

            writer.write("-NXT-\n");

            for (WS_I = 0; WS_I < WS_COUNT; WS_I++) {
                writer.write(String.format("%010d\n", WS_MY_VAR[WS_I]));
                if (FS_FILEO != FS_FILEO_OK) {
                    System.out.println("WRITE");
                    System.out.println(FS_FILEO);
                    System.exit(FS_FILEO);
                    System.out.println("-");
                }
            }

            writer.write("-NXT-\n");

            for (WS_I = 0; WS_I < WS_COUNT; WS_I++) {
                writer.write(String.format("%09d\n", WS_MY_VAR[WS_I]));
                if (FS_FILEO != FS_FILEO_OK) {
                    System.out.println("WRITE");
                    System.out.println(FS_FILEO);
                    System.exit(FS_FILEO);
                    System.out.println("-");
                }
            }

            writer.write("-NXT-\n");

            for (WS_I = 0; WS_I < WS_COUNT; WS_I++) {
                writer.write(String.format("%09d\n", WS_MY_VAR[WS_I]));
                if (FS_FILEO != FS_FILEO_OK) {
                    System.out.println("WRITE");
                    System.out.println(FS_FILEO);
                    System.exit(FS_FILEO);
                    System.out.println("-");
                }
            }

            writer.write("-NXT-\n");

            for (WS_I = 0; WS_I < WS_COUNT; WS_I++) {
                writer.write(String.format("%010d\n", WS_MY_VAR[WS_I]));
                if (FS_FILEO != FS_FILEO_OK) {
                    System.out.println("WRITE");
                    System.out.println(FS_FILEO);
                    System.exit(FS_FILEO);
                    System.out.println("-");
                }
            }

        } catch (IOException e) {
            FS_FILEO = 1;
            System.out.println("CLOSE");
            System.out.println(FS_FILEO);
            System.exit(FS_FILEO);
            System.out.println("-");
        }
    }
}