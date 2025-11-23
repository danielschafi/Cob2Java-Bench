import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdventOfCode2019_2b {
    public static void main(String[] args) {
        String inputFile = "2.input";
        List<Long> ioList = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line = br.readLine();
            if (line != null) {
                String[] parts = line.split(",");
                for (String part : parts) {
                    ioList.add(Long.parseLong(part.trim()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long[] ioArray = ioList.stream().mapToLong(Long::longValue).toArray();
        int len = ioArray.length;
        
        for (int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {
                long[] opArray = new long[len];
                System.arraycopy(ioArray, 0, opArray, 0, len);
                
                opArray[1] = noun;
                opArray[2] = verb;
                
                int ptr = 0;
                while (ptr < len) {
                    long opcode = opArray[ptr];
                    switch ((int) opcode) {
                        case 1:
                            ptr++;
                            int addr1 = (int) opArray[ptr] + 1;
                            ptr++;
                            int addr2 = (int) opArray[ptr] + 1;
                            ptr++;
                            int addr3 = (int) opArray[ptr] + 1;
                            opArray[addr3] = opArray[addr1] + opArray[addr2];
                            break;
                        case 2:
                            ptr++;
                            addr1 = (int) opArray[ptr] + 1;
                            ptr++;
                            addr2 = (int) opArray[ptr] + 1;
                            ptr++;
                            addr3 = (int) opArray[ptr] + 1;
                            opArray[addr3] = opArray[addr1] * opArray[addr2];
                            break;
                        case 99:
                            if (opArray[0] == 19690720) {
                                System.out.println(100 * noun + verb);
                                return;
                            }
                            break;
                        default:
                            throw new RuntimeException("Unknown opcode: " + opcode);
                    }
                    ptr++;
                }
            }
        }
    }
}