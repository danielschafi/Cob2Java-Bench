import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Table {
    public static void main(String[] args) {
        TableData tableData = new TableData();
        TableHTML tableHTML = new TableHTML();

        tableHTML.setLine3Values(tableData.getLine3Values());
        tableHTML.setLine4Values(tableData.getLine4Values());
        tableHTML.setLine5Values(tableData.getLine5Values());

        writeTable("index.html", tableHTML);
    }

    private static void writeTable(String fileName, TableHTML tableHTML) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(tableHTML.getLine1());
            writer.newLine();
            writer.write(tableHTML.getLine2());
            writer.newLine();
            writer.write(tableHTML.getLine3());
            writer.newLine();
            writer.write(tableHTML.getLine4());
            writer.newLine();
            writer.write(tableHTML.getLine5());
            writer.newLine();
            writer.write(tableHTML.getLine6());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class TableData {
    private int[] line3Values = {1234, 23, -123};
    private int[] line4Values = {123, 12, -1234};
    private int[] line5Values = {567, 6789, 3};

    public int[] getLine3Values() {
        return line3Values;
    }

    public int[] getLine4Values() {
        return line4Values;
    }

    public int[] getLine5Values() {
        return line5Values;
    }
}

class TableHTML {
    private String line1 = "<table border=1>";
    private String line2 = "<th></th><th>X</th><th>Y</th><th>Z</th>";
    private String line3;
    private String line4;
    private String line5;
    private String line6 = "</table>";

    public TableHTML() {
        line3 = String.format("<tr align=center><th>1</th><td>%+04d</td><td>%+04d</td><td>%+04d</td></tr>", 0, 0, 0);
        line4 = String.format("<tr align=center><th>2</th><td>%+04d</td><td>%+04d</td><td>%+04d</td></tr>", 0, 0, 0);
        line5 = String.format("<tr align=center><th>3</th><td>%+04d</td><td>%+04d</td><td>%+04d</td></tr>", 0, 0, 0);
    }

    public void setLine3Values(int[] values) {
        line3 = String.format("<tr align=center><th>1</th><td>%+04d</td><td>%+04d</td><td>%+04d</td></tr>", values[0], values[1], values[2]);
    }

    public void setLine4Values(int[] values) {
        line4 = String.format("<tr align=center><th>2</th><td>%+04d</td><td>%+04d</td><td>%+04d</td></tr>", values[0], values[1], values[2]);
    }

    public void setLine5Values(int[] values) {
        line5 = String.format("<tr align=center><th>3</th><td>%+04d</td><td>%+04d</td><td>%+04d</td></tr>", values[0], values[1], values[2]);
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getLine3() {
        return line3;
    }

    public String getLine4() {
        return line4;
    }

    public String getLine5() {
        return line5;
    }

    public String getLine6() {
        return line6;
    }
}