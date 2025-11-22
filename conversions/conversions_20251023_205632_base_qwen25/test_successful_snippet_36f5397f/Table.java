import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Table {
    public static void main(String[] args) {
        int line3Value1 = 1234;
        int line3Value2 = 23;
        int line3Value3 = -123;
        int line4Value1 = 123;
        int line4Value2 = 12;
        int line4Value3 = -1234;
        int line5Value1 = 567;
        int line5Value2 = 6789;
        int line5Value3 = 3;

        String line1 = "<table border=1>";
        String line2 = "<th></th><th>X</th><th>Y</th><th>Z</th>";
        String line3 = String.format("<tr align=center><th>1</th><td>%+04d</td><td>%+04d</td><td>%+04d</td></tr>", line3Value1, line3Value2, line3Value3);
        String line4 = String.format("<tr align=center><th>2</th><td>%+04d</td><td>%+04d</td><td>%+04d</td></tr>", line4Value1, line4Value2, line4Value3);
        String line5 = String.format("<tr align=center><th>3</th><td>%+04d</td><td>%+04d</td><td>%+04d</td></tr>", line5Value1, line5Value2, line5Value3);
        String line6 = "</table>";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("index.html"))) {
            writer.write(line1);
            writer.newLine();
            writer.write(line2);
            writer.newLine();
            writer.write(line3);
            writer.newLine();
            writer.write(line4);
            writer.newLine();
            writer.write(line5);
            writer.newLine();
            writer.write(line6);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}