import java.io.*;
import java.util.*;

public class AOC2020101 {
    private static final String INPUT_FILE = "d10.input";
    
    public static void main(String[] args) {
        try {
            List<Integer> wsArray = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
            String line;
            int result = 0;
            int i = 1;
            
            // Read input file
            while ((line = reader.readLine()) != null) {
                result++;
                wsArray.add(Integer.parseInt(line.trim()));
                i++;
            }
            reader.close();
            
            // Sort the array
            Collections.sort(wsArray);
            
            // Shift array (equivalent to 004-SHIFT-ARRAY)
            for (int j = 0; j < wsArray.size() - 1; j++) {
                wsArray.set(j, wsArray.get(j + 1));
            }
            
            // Add the last element (equivalent to COMPUTE WS-ARR-I(WS-ARR-LEN) = WS-ARR-I(WS-ARR-LEN - 1) + 3)
            if (!wsArray.isEmpty()) {
                int lastElement = wsArray.get(wsArray.size() - 1);
                wsArray.add(lastElement + 3);
            }
            
            // Count differences (equivalent to 005-USE-ADAPTERS)
            int diff1 = 0;
            int diff3 = 0;
            
            for (int j = 0; j < wsArray.size() - 1; j++) {
                int diff = wsArray.get(j + 1) - wsArray.get(j);
                if (diff == 1) {
                    diff1++;
                } else if (diff == 3) {
                    diff3++;
                }
            }
            
            int finalResult = diff1 * diff3;
            System.out.println(finalResult);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}