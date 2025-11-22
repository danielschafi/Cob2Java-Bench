import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOC2020161 {
    private static int result = 0;
    private static int section = 1;
    private static List<Range> ranges = new ArrayList<>();
    private static int currentRow = 0;
    private static int[] rowValues = new int[20];
    private static int stringPtr = 1;

    static class Range {
        int min1, max1, min2, max2;
        
        Range(int min1, int max1, int min2, int max2) {
            this.min1 = min1;
            this.max1 = max1;
            this.min2 = min2;
            this.max2 = max2;
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d16.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    private static void processRecord(String record) {
        if (record.isEmpty()) {
            section++;
        } else {
            switch (section) {
                case 1:
                    processRecordType1(record);
                    break;
                case 2:
                    // Type 2 record - skip
                    break;
                case 3:
                    processRecordType3(record);
                    break;
            }
        }
    }

    private static void processRecordType1(String record) {
        String[] parts = record.split("[-:or ]+");
        int min1 = Integer.parseInt(parts[1]);
        int max1 = Integer.parseInt(parts[2]);
        int min2 = Integer.parseInt(parts[3]);
        int max2 = Integer.parseInt(parts[4]);
        ranges.add(new Range(min1, max1, min2, max2));
    }

    private static void processRecordType3(String record) {
        currentRow++;
        if (currentRow == 1) return;
        
        stringPtr = 1;
        String[] values = record.split(",");
        for (int j = 0; j < values.length && j < 20; j++) {
            rowValues[j] = Integer.parseInt(values[j].trim());
            checkValue(rowValues[j]);
        }
    }

    private static void checkValue(int value) {
        boolean correct = false;
        for (Range range : ranges) {
            if ((value >= range.min1 && value <= range.max1) || 
                (value >= range.min2 && value <= range.max2)) {
                correct = true;
                break;
            }
        }
        if (!correct) {
            result += value;
        }
    }
}