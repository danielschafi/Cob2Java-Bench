import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class part1 {
    static class Command {
        String commandName;
        int commandValue;
    }

    static class Row {
        String rowName;
        int rowValue;
        int rowSeen;
        int rowRegion;
    }

    static class Region {
        int regLo;
        int regHi;
        int regSeen;
        int regFrom;
        int[] neighbor = new int[100];
        int[] neighborBc = new int[100];
    }

    static class FrontierRow {
        int frontier;
    }

    static class SeenRow {
        int seen;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input"));
        List<Command> commands = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            Command cmd = new Command();
            cmd.commandName = line.substring(0, 3);
            cmd.commandValue = Integer.parseInt(line.substring(4));
            commands.add(cmd);
        }
        reader.close();

        int rowCount = commands.size();
        Row[] rows = new Row[rowCount];
        for (int i = 0; i < rowCount; i++) {
            rows[i] = new Row();
            rows[i].rowName = commands.get(i).commandName;
            rows[i].rowValue = commands.get(i).commandValue;
            rows[i].rowSeen = 0;
            rows[i].rowRegion = 0;
        }

        int pc = 1;
        int acc = 0;

        while (rows[pc - 1].rowSeen == 0) {
            rows[pc - 1].rowSeen = 1;
            if (rows[pc - 1].rowName.equals("acc")) {
                acc += rows[pc - 1].rowValue;
            }
            if (rows[pc - 1].rowName.equals("jmp")) {
                pc += rows[pc - 1].rowValue;
            } else {
                pc++;
            }
        }

        // Initialize regions
        int regCount = 1;
        Region[] regions = new Region[1000];
        for (int i = 0; i < 1000; i++) {
            regions[i] = new Region();
        }
        regions[regCount - 1].regLo = 1;
        int currentPc = 1;

        while (currentPc <= rowCount) {
            rows[currentPc - 1].rowRegion = regCount;
            if (rows[currentPc - 1].rowSeen == 1) {
                regions[regCount - 1].regSeen = 1;
            }
            if (rows[currentPc - 1].rowName.equals("jmp")) {
                regions[regCount - 1].regHi = currentPc;
                regCount++;
                regions[regCount - 1].regLo = currentPc + 1;
            }
            currentPc++;
        }
        regCount -= 2;

        // Reset pc
        currentPc = 1;
        while (currentPc <= rowCount) {
            if (!rows[currentPc - 1].rowName.equals("acc")) {
                int offset = currentPc + rows[currentPc - 1].rowValue;
                int target = rows[offset - 1].rowRegion;
                if (regions[target - 1].neighbor[regions[target - 1].regFrom - 1] != rows[currentPc - 1].rowRegion) {
                    regions[target - 1].regFrom++;
                    regions[target - 1].neighbor[regions[target - 1].regFrom - 1] = rows[currentPc - 1].rowRegion;
                    regions[target - 1].neighborBc[regions[target - 1].regFrom - 1] = currentPc;
                }
            }
            currentPc++;
        }

        // Check frontier
        int frontierCount = 1;
        FrontierRow[] frontierTable = new FrontierRow[100];
        for (int i = 0; i < 100; i++) {
            frontierTable[i] = new FrontierRow();
        }
        frontierTable[frontierCount - 1].frontier = regCount;

        int change = 0;
        while (change != 0 && frontierCount != 0) {
            int target = frontierTable[frontierCount - 1].frontier;
            frontierCount--;
            if (regions[target - 1].regSeen == 1) {
                change = regions[target - 1].regLo - 1;
            } else {
                currentPc = 1;
                while (currentPc <= regions[target - 1].regFrom) {
                    if (rows[regions[target - 1].neighborBc[currentPc - 1] - 1].rowSeen != 0) {
                        change = regions[target - 1].neighborBc[currentPc - 1];
                    }
                    if (regions[target - 1].neighbor[currentPc - 1] != 0) {
                        int seenValue = 0;
                        for (int i = 0; i < 1000; i++) {
                            if (i == regions[target - 1].neighbor[currentPc - 1] - 1) {
                                seenValue = 1;
                                break;
                            }
                        }
                        if (seenValue == 0) {
                            frontierCount++;
                            frontierTable[frontierCount - 1].frontier = regions[target - 1].neighbor[currentPc - 1];
                            for (int i = 0; i < 1000; i++) {
                                if (i == regions[target - 1].neighbor[currentPc - 1] - 1) {
                                    // Set seen
                                }
                            }
                        }
                    }
                    currentPc++;
                }
            }
        }

        if (change != 0) {
            System.out.println("Instruction to change: " + change);
            if (rows[change - 1].rowName.equals("jmp")) {
                rows[change - 1].rowName = "nop";
            } else {
                rows[change - 1].rowName = "jmp";
            }
        } else {
            return;
        }

        pc = 1;
        acc = 0;
        while (pc <= rowCount) {
            rows[pc - 1].rowSeen = 1;
            if (rows[pc - 1].rowName.equals("acc")) {
                acc += rows[pc - 1].rowValue;
            }
            if (rows[pc - 1].rowName.equals("jmp")) {
                pc += rows[pc - 1].rowValue;
            } else {
                pc++;
            }
        }
        System.out.println(acc);
    }
}