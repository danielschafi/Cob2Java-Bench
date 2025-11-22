import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AdventOfCode2019_2b {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("2.input"));
            String inputLine = reader.readLine();
            reader.close();

            String[] parts = inputLine.split(",");
            List<Long> ioOp = new ArrayList<>();
            for (String part : parts) {
                ioOp.add(Long.valueOf(part.trim()));
            }

            long len = ioOp.size();

            for (long noun = 0; noun <= 99; noun++) {
                for (long verb = 0; verb <= 99; verb++) {
                    List<Long> op = new ArrayList<>(ioOp);
                    op.set(1, noun);
                    op.set(2, verb);

                    long ptr = 0;
                    while (ptr < len) {
                        long opcode = op.get((int) ptr);
                        if (opcode == 1) {
                            ptr++;
                            long addr1 = op.get((int) ptr) + 1;
                            ptr++;
                            long addr2 = op.get((int) ptr) + 1;
                            ptr++;
                            long addr3 = op.get((int) ptr) + 1;
                            long res = op.get((int) addr1) + op.get((int) addr2);
                            op.set((int) addr3, res);
                        } else if (opcode == 2) {
                            ptr++;
                            long addr1 = op.get((int) ptr) + 1;
                            ptr++;
                            long addr2 = op.get((int) ptr) + 1;
                            ptr++;
                            long addr3 = op.get((int) ptr) + 1;
                            long res = op.get((int) addr1) * op.get((int) addr2);
                            op.set((int) addr3, res);
                        } else if (opcode == 99) {
                            break;
                        }
                        ptr++;
                    }

                    if (op.get(0) == 19690720) {
                        System.out.println(100 * noun + verb);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}