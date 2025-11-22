import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOC2021072 {
    private static final String INPUT_FILE = "d07.input";
    
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                numbers.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        int n = numbers.size();
        long sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        
        double mean = Math.round((double) sum / n);
        
        long ans1 = 0;
        long ans2 = 0;
        long ans3 = 0;
        
        for (int num : numbers) {
            long x1 = Math.abs((long) mean - 1 - num);
            ans1 += (x1 * (x1 + 1)) / 2;
            
            long x2 = Math.abs((long) mean - num);
            ans2 += (x2 * (x2 + 1)) / 2;
            
            long x3 = Math.abs((long) mean + 1 - num);
            ans3 += (x3 * (x3 + 1)) / 2;
        }
        
        long result = Math.min(Math.min(ans1, ans2), ans3);
        System.out.println(result);
    }
}