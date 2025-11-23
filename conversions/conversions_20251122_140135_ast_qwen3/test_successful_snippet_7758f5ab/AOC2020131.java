import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOC2020131 {
    private static int[] ws_buses = new int[99];
    private static int n = 99;
    private static int ws_start;
    private static int ws_quotient;
    private static int ws_mod;
    private static int ws_time;
    private static int ws_bus_min;
    private static int ws_time_min = 99999;
    private static int result;

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("d13.input"));
            String line1 = reader.readLine();
            ws_start = Integer.parseInt(line1.trim());
            String line2 = reader.readLine();
            reader.close();

            String[] busIds = line2.split(",");
            int j = 0;
            for (String busId : busIds) {
                if (!busId.equals("x")) {
                    ws_buses[j] = Integer.parseInt(busId);
                    j++;
                }
            }
            n = j;

            findEarliestBus();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void findEarliestBus() {
        for (int i = 0; i < n; i++) {
            ws_quotient = ws_start / ws_buses[i];
            ws_mod = ws_start % ws_buses[i];
            ws_time = ws_buses[i] - ws_mod;
            if (ws_time < ws_time_min) {
                ws_time_min = ws_time;
                ws_bus_min = ws_buses[i];
            }
        }
        result = ws_time_min * ws_bus_min;
    }
}