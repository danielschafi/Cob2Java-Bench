import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class DAY09_PART1 {
    private static final int LS_BUFFER_SIZE = 25;
    private static final int LS_TOTAL_NUMBER_COUNT = 1000;
    
    private static BigInteger[] ls_element = new BigInteger[LS_BUFFER_SIZE];
    private static BigInteger[] ls_numbers = new BigInteger[LS_TOTAL_NUMBER_COUNT];
    private static int ls_total_numbers_read = 0;
    private static BigInteger ls_number = BigInteger.ZERO;
    private static int ls_index = 1;
    private static int ls_index_search1 = 0;
    private static int ls_index_search2 = 0;
    private static BigInteger ls_sum = BigInteger.ZERO;
    private static BigInteger ls_target = BigInteger.ZERO;
    private static BigInteger ls_min = BigInteger.valueOf(Long.MAX_VALUE);
    private static BigInteger ls_max = BigInteger.ZERO;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        // Initialize arrays
        for (int i = 0; i < LS_BUFFER_SIZE; i++) {
            ls_element[i] = BigInteger.ZERO;
        }
        for (int i = 0; i < LS_TOTAL_NUMBER_COUNT; i++) {
            ls_numbers[i] = BigInteger.ZERO;
        }
        
        // Read initial numbers
        for (int i = 0; i < LS_BUFFER_SIZE; i++) {
            readNumber(reader);
            move_to_buffers();
        }
        
        // Part 1
        while (ls_total_numbers_read < LS_TOTAL_NUMBER_COUNT) {
            part1(reader);
        }
        
        // Part 2
        part2();
    }
    
    private static void readNumber(BufferedReader reader) throws IOException {
        try {
            String input = reader.readLine();
            if (input != null) {
                ls_number = new BigInteger(input.trim());
            } else {
                ls_number = BigInteger.ZERO;
            }
        } catch (NumberFormatException e) {
            ls_number = BigInteger.ZERO;
        }
    }
    
    private static void move_to_buffers() {
        ls_element[ls_index - 1] = ls_number;
        ls_numbers[ls_total_numbers_read] = ls_number;
        ls_index++;
        ls_total_numbers_read++;
        
        if (ls_index > LS_BUFFER_SIZE) {
            ls_index = 1;
        }
    }
    
    private static void part1(BufferedReader reader) throws IOException {
        readNumber(reader);
        find_match();
        move_to_buffers();
    }
    
    private static void find_match() {
        for (ls_index_search1 = 1; ls_index_search1 <= LS_BUFFER_SIZE; ls_index_search1++) {
            for (ls_index_search2 = 1; ls_index_search2 <= LS_BUFFER_SIZE; ls_index_search2++) {
                if (!ls_element[ls_index_search1 - 1].equals(ls_element[ls_index_search2 - 1])) {
                    ls_sum = ls_element[ls_index_search1 - 1].add(ls_element[ls_index_search2 - 1]);
                    
                    if (ls_sum.equals(ls_number)) {
                        return;
                    }
                }
            }
        }
        
        if (!ls_sum.equals(ls_number)) {
            System.out.println("NOT FOUND " + ls_number);
            ls_target = ls_number;
        }
    }
    
    private static void part2() {
        for (ls_index_search1 = 1; ls_index_search1 <= LS_TOTAL_NUMBER_COUNT; ls_index_search1++) {
            ls_sum = BigInteger.ZERO;
            ls_max = BigInteger.ZERO;
            ls_min = BigInteger.valueOf(Long.MAX_VALUE);
            
            for (ls_index_search2 = ls_index_search1; ls_index_search2 <= LS_TOTAL_NUMBER_COUNT; ls_index_search2++) {
                ls_sum = ls_sum.add(ls_numbers[ls_index_search2 - 1]);
                
                if (ls_numbers[ls_index_search2 - 1].compareTo(ls_max) > 0) {
                    ls_max = ls_numbers[ls_index_search2 - 1];
                }
                
                if (ls_numbers[ls_index_search2 - 1].compareTo(ls_min) < 0) {
                    ls_min = ls_numbers[ls_index_search2 - 1];
                }
                
                if (ls_sum.compareTo(ls_target) >= 0) {
                    break;
                }
            }
            
            if (ls_sum.equals(ls_target)) {
                break;
            }
        }
        
        if (ls_sum.equals(ls_target)) {
            ls_sum = ls_min.add(ls_max);
            System.out.println("MIN " + ls_min + " MAX " + ls_max + " SUM " + ls_sum);
        }
    }
}