import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class DAY09PART1 {
    private static final int LS_BUFFER_SIZE = 25;
    private static final int LS_TOTAL_NUMBER_COUNT = 1000;
    
    private static BigInteger[] ls_elements = new BigInteger[LS_BUFFER_SIZE];
    private static BigInteger[] ls_numbers = new BigInteger[LS_TOTAL_NUMBER_COUNT];
    private static int ls_total_numbers_read = 0;
    private static int ls_index = 1;
    private static BigInteger ls_number = BigInteger.ZERO;
    private static BigInteger ls_target = BigInteger.ZERO;
    private static BigInteger ls_min = BigInteger.valueOf(Long.MAX_VALUE);
    private static BigInteger ls_max = BigInteger.ZERO;
    private static BigInteger ls_sum = BigInteger.ZERO;
    
    public static void main(String[] args) {
        try {
            readNumber();
            for (int i = 0; i < LS_BUFFER_SIZE; i++) {
                move_to_buffers();
                readNumber();
            }
            
            while (ls_total_numbers_read < LS_TOTAL_NUMBER_COUNT) {
                part1();
            }
            
            part2();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void readNumber() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        if (input != null) {
            ls_number = new BigInteger(input.trim());
        } else {
            throw new IOException("No input");
        }
    }
    
    private static void move_to_buffers() {
        ls_elements[ls_index - 1] = ls_number;
        ls_numbers[ls_total_numbers_read] = ls_number;
        ls_index++;
        ls_total_numbers_read++;
        
        if (ls_index > LS_BUFFER_SIZE) {
            ls_index = 1;
        }
    }
    
    private static void part1() throws IOException {
        readNumber();
        find_match();
        move_to_buffers();
    }
    
    private static void find_match() {
        for (int ls_index_search1 = 0; ls_index_search1 < LS_BUFFER_SIZE; ls_index_search1++) {
            for (int ls_index_search2 = 0; ls_index_search2 < LS_BUFFER_SIZE; ls_index_search2++) {
                if (!ls_elements[ls_index_search1].equals(ls_elements[ls_index_search2])) {
                    ls_sum = ls_elements[ls_index_search1].add(ls_elements[ls_index_search2]);
                    
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
        for (int ls_index_search1 = 0; ls_index_search1 < LS_TOTAL_NUMBER_COUNT; ls_index_search1++) {
            ls_sum = BigInteger.ZERO;
            ls_max = BigInteger.ZERO;
            ls_min = BigInteger.valueOf(Long.MAX_VALUE);
            
            for (int ls_index_search2 = ls_index_search1; ls_index_search2 < LS_TOTAL_NUMBER_COUNT; ls_index_search2++) {
                ls_sum = ls_sum.add(ls_numbers[ls_index_search2]);
                ls_max = ls_max.max(ls_numbers[ls_index_search2]);
                ls_min = ls_min.min(ls_numbers[ls_index_search2]);
                
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