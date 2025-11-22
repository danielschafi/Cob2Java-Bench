import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class Table {
    public static void main(String[] args) {
        PrintStream out = null;
        try {
            out = new PrintStream(new FileOutputStream("index.html"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Table-Data
        int[][] tableData = {
            {1234, 23, -123},
            {123, 12, -1234},
            {567, 6789, 3}
        };

        // Table-HTML
        String line1 = "<table border=1>";
        String line2 = "<th></th><th>X</th><th>Y</th><th>Z</th>";
        String line3 = "<tr align=center><th>1</th><td>" + 
                      String.format("%+05d", tableData[0][0]) + "</td><td>" +
                      String.format("%+05d", tableData[0][1]) + "</td><td>" +
                      String.format("%+05d", tableData[0][2]) + "</td></tr>";
        String line4 = "<tr align=center><th>2</th><td>" + 
                      String.format("%+05d", tableData[1][0]) + "</td><td>" +
                      String.format("%+05d", tableData[1][1]) + "</td><td>" +
                      String.format("%+05d", tableData[1][2]) + "</td></tr>";
        String line5 = "<tr align=center><th>3</th><td>" + 
                      String.format("%+05d", tableData[2][0]) + "</td><td>" +
                      String.format("%+05d", tableData[2][1]) + "</td><td>" +
                      String.format("%+05d", tableData[2][2]) + "</td></tr>";
        String line6 = "</table>";

        out.println(line1);
        out.println(line2);
        out.println(line3);
        out.println(line4);
        out.println(line5);
        out.println(line6);

        out.close();
    }
}