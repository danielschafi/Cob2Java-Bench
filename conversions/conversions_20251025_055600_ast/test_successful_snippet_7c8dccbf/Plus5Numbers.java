import java.io.*;

public class Plus5Numbers {
    public static void main(String[] args) {
        BufferedReader in = null;
        BufferedWriter out = null;
        String eofSwitch = "N";
        String line;
        int originalNumber;
        int result;
        String neResult;

        try {
            in = new BufferedReader(new FileReader("numbers.txt"));
            out = new BufferedWriter(new FileWriter("newNumbers.txt"));

            line = in.readLine();
            if (line == null) {
                eofSwitch = "Y";
            }

            while (eofSwitch.equals("N")) {
                originalNumber = Integer.parseInt(line);
                result = originalNumber + 5;
                neResult = String.format("%05d", result);

                out.write(neResult);
                out.newLine();
                System.out.println(neResult);

                line = in.readLine();
                if (line == null) {
                    eofSwitch = "Y";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}