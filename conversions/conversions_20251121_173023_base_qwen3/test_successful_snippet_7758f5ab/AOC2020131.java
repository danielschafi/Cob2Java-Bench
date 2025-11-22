import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AOC2020131 {
    private static final String INPUT_FILE = "d13.input";
    
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
            String line1 = reader.readLine();
            int startTime = Integer.parseInt(line1);
            
            String line2 = reader.readLine();
            String[] busStrings = line2.split(",");
            List<Integer> buses = new ArrayList<>();
            
            for (String busStr : busStrings) {
                if (!busStr.equals("x")) {
                    buses.add(Integer.parseInt(busStr));
                }
            }
            
            reader.close();
            
            int minWaitTime = Integer.MAX_VALUE;
            int bestBus = 0;
            
            for (int bus : buses) {
                int quotient = startTime / bus;
                int remainder = startTime % bus;
                int waitTime = bus - remainder;
                
                if (waitTime < minWaitTime) {
                    minWaitTime = waitTime;
                    bestBus = bus;
                }
            }
            
            int result = minWaitTime * bestBus;
            System.out.println(result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}