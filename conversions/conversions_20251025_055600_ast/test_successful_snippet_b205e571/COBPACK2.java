import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class COBPACK2 {
    public static void main(String[] args) {
        String wsFsOutfile = "  ";
        long wsIx = -100000000;

        System.out.println("COBPACK START...");

        try (BufferedWriter outfile = new BufferedWriter(new FileWriter("OUTFILE"))) {
            if (!wsFsOutfile.equals("00")) {
                System.out.println("OPEN OUTFILE FS:  " + wsFsOutfile);
                return;
            }

            while (wsIx <= 100000000) {
                String outfileText = String.format("%018d", wsIx);
                String outfileUnpacked = String.format("%018d", wsIx);
                String outfileUnpackedS = String.format("%018d", wsIx);
                String outfileComp04 = String.format("%04d", wsIx % 10000);
                String outfileComp04S = String.format("%04d", wsIx % 10000);
                String outfileComp09 = String.format("%09d", wsIx % 1000000000);
                String outfileComp09S = String.format("%09d", wsIx % 1000000000);
                String outfileComp18 = String.format("%018d", wsIx);
                String outfileComp18S = String.format("%018d", wsIx);
                String outfileComp304 = String.format("%04d", wsIx % 10000);
                String outfileComp304S = String.format("%04d", wsIx % 10000);
                String outfileComp309 = String.format("%09d", wsIx % 1000000000);
                String outfileComp309S = String.format("%09d", wsIx % 1000000000);
                String outfileComp318 = String.format("%018d", wsIx);
                String outfileComp318S = String.format("%018d", wsIx);
                String text1Group11 = "AA";

                String record = String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s",
                        outfileText, outfileUnpacked, outfileUnpackedS, outfileComp04, outfileComp04S,
                        outfileComp09, outfileComp09S, outfileComp18, outfileComp18S, outfileComp304,
                        outfileComp304S, outfileComp309, outfileComp309S, outfileComp318, outfileComp318S, text1Group11);

                outfile.write(record);
                outfile.newLine();

                if (!wsFsOutfile.equals("00")) {
                    System.out.println("WRITE OUTFILE FS:  " + wsFsOutfile);
                    return;
                }

                wsIx += 2001;
            }

            if (!wsFsOutfile.equals("00")) {
                System.out.println("CLOSE OUTFILE FS: " + wsFsOutfile);
                return;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("COBPACK FINISH..");
    }
}