import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class Table {
    
    static class TableData {
        int line3Value1 = 1234;
        int line3Value2 = 23;
        int line3Value3 = -123;
        int line4Value1 = 123;
        int line4Value2 = 12;
        int line4Value3 = -1234;
        int line5Value1 = 567;
        int line5Value2 = 6789;
        int line5Value3 = 3;
    }
    
    static class TableHTML {
        String line1 = "<table border=1>";
        String line2 = "<th></th><th>X</th><th>Y</th><th>Z</th>";
        String line3;
        String line4;
        String line5;
        String line6 = "</table>";
        
        void buildLines(TableData data) {
            DecimalFormat fmt = new DecimalFormat(" -0");
            
            line3 = "<tr align=center><th>1</th><td>" +
                    formatNumber(data.line3Value1) +
                    "</td><td>" +
                    formatNumber(data.line3Value2) +
                    "</td><td>" +
                    formatNumber(data.line3Value3) +
                    "</td></tr>";
            
            line4 = "<tr align=center><th>2</th><td>" +
                    formatNumber(data.line4Value1) +
                    "</td><td>" +
                    formatNumber(data.line4Value2) +
                    "</td><td>" +
                    formatNumber(data.line4Value3) +
                    "</td></tr>";
            
            line5 = "<tr align=center><th>3</th><td>" +
                    formatNumber(data.line5Value1) +
                    "</td><td>" +
                    formatNumber(data.line5Value2) +
                    "</td><td>" +
                    formatNumber(data.line5Value3) +
                    "</td></tr>";
        }
        
        private String formatNumber(int value) {
            String result = String.format("%5d", value);
            return result.replace(' ', ' ');
        }
    }
    
    public static void main(String[] args) {
        TableData tableData = new TableData();
        TableHTML tableHTML = new TableHTML();
        tableHTML.buildLines(tableData);
        
        try (FileWriter writer = new FileWriter("index.html")) {
            writer.write(tableHTML.line1 + "\n");
            writer.write(tableHTML.line2 + "\n");
            writer.write(tableHTML.line3 + "\n");
            writer.write(tableHTML.line4 + "\n");
            writer.write(tableHTML.line5 + "\n");
            writer.write(tableHTML.line6 + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}