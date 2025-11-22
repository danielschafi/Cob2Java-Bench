import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOC_2020_13_1 {
    private int wsStart;
    private List<Integer> wsBuses;
    private int n;
    private int wsQuotient;
    private int wsMod;
    private int wsTime;
    private int wsBusMin;
    private int wsTimeMin;
    private int result;

    public AOC_2020_13_1() {
        wsBuses = new ArrayList<>();
        n = 0;
        wsTimeMin = 99999;
        result = 0;
    }

    public static void main(String[] args) {
        AOC_2020_13_1 program = new AOC_2020_13_1();
        program.run();
    }

    public void run() {
        read();
        findEarliestBus();
        System.out.println(result);
    }

    private void read() {
        try (BufferedReader br = new BufferedReader(new FileReader("d13.input"))) {
            String line1 = br.readLine();
            wsStart = Integer.parseInt(line1.trim());
            
            String line2 = br.readLine();
            String[] tokens = line2.split(",");
            
            for (String token : tokens) {
                token = token.trim();
                if (!token.equals("x")) {
                    try {
                        int busId = Integer.parseInt(token);
                        if (busId != 0) {
                            wsBuses.add(busId);
                        }
                    } catch (NumberFormatException e) {
                        // Skip non-numeric values
                    }
                }
            }
            
            n = wsBuses.size();
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void findEarliestBus() {
        for (int i = 0; i < n; i++) {
            int busId = wsBuses.get(i);
            wsQuotient = wsStart / busId;
            wsMod = wsStart % busId;
            wsTime = busId - wsMod;
            
            if (wsTime < wsTimeMin) {
                wsTimeMin = wsTime;
                wsBusMin = busId;
            }
        }
        
        result = wsTimeMin * wsBusMin;
    }
}