import java.io.*;

public class Table {
    public static void main(String[] args) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("index.html"));
            
            // Initialize data values
            int[][] tableData = {
                {1234, 23, -123},
                {123, 12, -1234},
                {567, 6789, 3}
            };
            
            // HTML template parts
            String line1 = "<table border=1>";
            String line2 = "<th></th><th>X</th><th>Y</th><th>Z</th>";
            String line6 = "</table>";
            
            // Process each row
            for (int i = 0; i < tableData.length; i++) {
                int[] row = tableData[i];
                String line3 = "<tr align=center><th>" + (i+1) + "</th><td>" + 
                              String.format("%4d", row[0]) + "</td><td>" +
                              String.format("%4d", row[1]) + "</td><td>" +
                              String.format("%4d", row[2]) + "</td></tr>";
                
                if (i == 0) {
                    writer.println(line1);
                    writer.println(line2);
                    writer.println(line3);
                } else if (i == 1) {
                    writer.println(line3);
                } else if (i == 2) {
                    writer.println(line3);
                    writer.println(line6);
                }
            }
            
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}