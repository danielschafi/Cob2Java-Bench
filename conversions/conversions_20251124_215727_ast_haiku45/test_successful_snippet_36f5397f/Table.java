import java.io.*;
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
        String line3Field1 = "<tr align=center><th>1</th><td>";
        String line3Value1 = "";
        String line3Field3 = "</td><td>";
        String line3Value2 = "";
        String line3Field5 = "</td><td>";
        String line3Value3 = "";
        String line3Field5b = "</td></tr>";
        
        String line4Field1 = "<tr align=center><th>2</th><td>";
        String line4Value1 = "";
        String line4Field3 = "</td><td>";
        String line4Value2 = "";
        String line4Field5 = "</td><td>";
        String line4Value3 = "";
        String line4Field5b = "</td></tr>";
        
        String line5Field1 = "<tr align=center><th>3</th><td>";
        String line5Value1 = "";
        String line5Field3 = "</td><td>";
        String line5Value2 = "";
        String line5Field5 = "</td><td>";
        String line5Value3 = "";
        String line5Field5b = "</td></tr>";
        
        String line6 = "</table>";
    }

    public static void main(String[] args) {
        try {
            TableData tableData = new TableData();
            TableHTML tableHTML = new TableHTML();
            
            moveCorresponding(tableData, tableHTML);
            writeTable(tableHTML);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void moveCorresponding(TableData source, TableHTML target) {
        DecimalFormat df = new DecimalFormat("-ZZZ9");
        
        target.line3Value1 = formatNumber(source.line3Value1);
        target.line3Value2 = formatNumber(source.line3Value2);
        target.line3Value3 = formatNumber(source.line3Value3);
        
        target.line4Value1 = formatNumber(source.line4Value1);
        target.line4Value2 = formatNumber(source.line4Value2);
        target.line4Value3 = formatNumber(source.line4Value3);
        
        target.line5Value1 = formatNumber(source.line5Value1);
        target.line5Value2 = formatNumber(source.line5Value2);
        target.line5Value3 = formatNumber(source.line5Value3);
    }

    static String formatNumber(int value) {
        String result = String.valueOf(value);
        if (value >= 0) {
            result = String.format("%5d", value).replace(' ', ' ');
        } else {
            result = String.format("%5d", value);
        }
        return result;
    }

    static void writeTable(TableHTML tableHTML) throws IOException {
        FileWriter fw = new FileWriter("index.html");
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write(tableHTML.line1);
        bw.newLine();
        bw.write(tableHTML.line2);
        bw.newLine();
        bw.write(tableHTML.line3Field1 + tableHTML.line3Value1 + tableHTML.line3Field3 + 
                 tableHTML.line3Value2 + tableHTML.line3Field5 + tableHTML.line3Value3 + 
                 tableHTML.line3Field5b);
        bw.newLine();
        bw.write(tableHTML.line4Field1 + tableHTML.line4Value1 + tableHTML.line4Field3 + 
                 tableHTML.line4Value2 + tableHTML.line4Field5 + tableHTML.line4Value3 + 
                 tableHTML.line4Field5b);
        bw.newLine();
        bw.write(tableHTML.line5Field1 + tableHTML.line5Value1 + tableHTML.line5Field3 + 
                 tableHTML.line5Value2 + tableHTML.line5Field5 + tableHTML.line5Value3 + 
                 tableHTML.line5Field5b);
        bw.newLine();
        bw.write(tableHTML.line6);
        bw.newLine();
        
        bw.close();
        fw.close();
    }
}