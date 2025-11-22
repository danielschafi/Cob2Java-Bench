import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Table {
    
    private static class TableData {
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
    
    private static class TableHTML {
        String line1 = "<table border=1>";
        String line2 = "<th></th><th>X</th><th>Y</th><th>Z</th>";
        String line3Field1 = "<tr align=center><th>1</th><td>";
        int line3Value1;
        String line3Field3 = "</td><td>";
        int line3Value2;
        String line3Field5a = "</td><td>";
        int line3Value3;
        String line3Field5b = "</td></tr>";
        String line4Field1 = "<tr align=center><th>2</th><td>";
        int line4Value1;
        String line4Field3 = "</td><td>";
        int line4Value2;
        String line4Field5a = "</td><td>";
        int line4Value3;
        String line4Field5b = "</td></tr>";
        String line5Field1 = "<tr align=center><th>3</th><td>";
        int line5Value1;
        String line5Field3 = "</td><td>";
        int line5Value2;
        String line5Field5a = "</td><td>";
        int line5Value3;
        String line5Field5b = "</td></tr>";
        String line6 = "</table>";
    }
    
    private static String formatNumber(int value) {
        String formatted = String.format("%5d", value);
        if (value < 0) {
            formatted = String.format("%5d", value);
        } else {
            formatted = String.format(" %4d", value);
        }
        return formatted;
    }
    
    public static void main(String[] args) {
        TableData tableData = new TableData();
        TableHTML tableHTML = new TableHTML();
        
        tableHTML.line3Value1 = tableData.line3Value1;
        tableHTML.line3Value2 = tableData.line3Value2;
        tableHTML.line3Value3 = tableData.line3Value3;
        tableHTML.line4Value1 = tableData.line4Value1;
        tableHTML.line4Value2 = tableData.line4Value2;
        tableHTML.line4Value3 = tableData.line4Value3;
        tableHTML.line5Value1 = tableData.line5Value1;
        tableHTML.line5Value2 = tableData.line5Value2;
        tableHTML.line5Value3 = tableData.line5Value3;
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("index.html"))) {
            writer.write(tableHTML.line1);
            writer.newLine();
            
            writer.write(tableHTML.line2);
            writer.newLine();
            
            String line3 = tableHTML.line3Field1 + formatNumber(tableHTML.line3Value1) + 
                          tableHTML.line3Field3 + formatNumber(tableHTML.line3Value2) + 
                          tableHTML.line3Field5a + formatNumber(tableHTML.line3Value3) + 
                          tableHTML.line3Field5b;
            writer.write(line3);
            writer.newLine();
            
            String line4 = tableHTML.line4Field1 + formatNumber(tableHTML.line4Value1) + 
                          tableHTML.line4Field3 + formatNumber(tableHTML.line4Value2) + 
                          tableHTML.line4Field5a + formatNumber(tableHTML.line4Value3) + 
                          tableHTML.line4Field5b;
            writer.write(line4);
            writer.newLine();
            
            String line5 = tableHTML.line5Field1 + formatNumber(tableHTML.line5Value1) + 
                          tableHTML.line5Field3 + formatNumber(tableHTML.line5Value2) + 
                          tableHTML.line5Field5a + formatNumber(tableHTML.line5Value3) + 
                          tableHTML.line5Field5b;
            writer.write(line5);
            writer.newLine();
            
            writer.write(tableHTML.line6);
            writer.newLine();
            
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            System.exit(1);
        }
    }
}