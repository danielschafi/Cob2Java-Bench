import java.io.*;
import java.util.*;

public class part1 {
    static class Command {
        String commandName;
        int commandValue;
    }

    static class Row {
        String name;
        int value;
        int seen;
        int region;
    }

    static class Region {
        int lo;
        int hi;
        int seen;
        int from;
        int[] neighbor = new int[100];
        int[] neighborBc = new int[100];
    }

    static class FrontierRow {
        int frontier;
    }

    static class SeenRow {
        int seen;
    }

    static final int MAX_ROWS = 1000;
    static final int MAX_REGIONS = 1000;
    static final int MAX_FRONTIER = 100;

    static Row[] rows = new Row[MAX_ROWS];
    static Region[] regions = new Region[MAX_REGIONS];
    static FrontierRow[] frontierTable = new FrontierRow[MAX_FRONTIER];
    static SeenRow[] seenTable = new SeenRow[MAX_ROWS];

    static int rowCount = 0;
    static int pc = 0;
    static int acc = 0;
    static int regCount = 0;
    static int frontierCount = 0;
    static int change = 0;
    static int target = 0;
    static int offset = 0;

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < MAX_ROWS; i++) {
            rows[i] = new Row();
        }
        for (int i = 0; i < MAX_REGIONS; i++) {
            regions[i] = new Region();
        }
        for (int i = 0; i < MAX_FRONTIER; i++) {
            frontierTable[i] = new FrontierRow();
        }
        for (int i = 0; i < MAX_ROWS; i++) {
            seenTable[i] = new SeenRow();
        }

        readInput();

        pc = 1;
        acc = 0;
        while (rows[pc - 1].seen != 1) {
            runStep();
        }

        initRegions();
        pc = 1;
        frontierCount = 1;
        regions[0].from = regCount;
        frontierTable[frontierCount - 1].frontier = regCount;
        change = 0;
        while (change != 0 || frontierCount == 0) {
            checkFrontier();
        }

        if (change != 0) {
            System.out.println("Instruction to change: " + change);
        } else {
            System.exit(0);
        }

        if (rows[change - 1].name.equals("jmp")) {
            rows[change - 1].name = "nop";
        } else {
            rows[change - 1].name = "jmp";
        }

        pc = 1;
        acc = 0;
        while (pc <= rowCount) {
            runStep();
        }
        System.out.println(acc);
    }

    static void readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input"));
        String line;
        int count = 0;
        while ((line = reader.readLine()) != null) {
            count++;
            String[] parts = line.split(" ");
            rows[count - 1].name = parts[0];
            rows[count - 1].value = Integer.parseInt(parts[1]);
            rows[count - 1].seen = 0;
        }
        rowCount = count;
        reader.close();
    }

    static void runStep() {
        rows[pc - 1].seen = 1;
        if (rows[pc - 1].name.equals("acc")) {
            acc += rows[pc - 1].value;
        }
        if (rows[pc - 1].name.equals("jmp")) {
            pc += rows[pc - 1].value;
        } else {
            pc++;
        }
    }

    static void initRegions() {
        pc = 1;
        regCount = 1;
        regions[regCount - 1].lo = 1;
        while (pc <= rowCount) {
            rows[pc - 1].region = regCount;
            if (rows[pc - 1].seen == 1) {
                regions[regCount - 1].seen = 1;
            }
            if (rows[pc - 1].name.equals("jmp")) {
                regions[regCount - 1].hi = pc;
                regCount++;
                regions[regCount - 1].lo = pc + 1;
            }
            pc++;
        }
        regCount -= 2;
        pc = 1;
        while (pc <= rowCount) {
            if (!rows[pc - 1].name.equals("acc")) {
                offset = pc + rows[pc - 1].value;
                target = rows[offset - 1].region;
                if (regions[target - 1].neighbor[regions[target - 1].from - 1] != rows[pc - 1].region) {
                    regions[target - 1].from++;
                    regions[target - 1].neighbor[regions[target - 1].from - 1] = rows[pc - 1].region;
                    regions[target - 1].neighborBc[regions[target - 1].from - 1] = pc;
                }
            }
            pc++;
        }
    }

    static void checkFrontier() {
        target = frontierTable[frontierCount - 1].frontier;
        frontierCount--;
        if (regions[target - 1].seen == 1) {
            change = regions[target - 1].lo - 1;
        } else {
            pc = 1;
            while (pc <= regions[target - 1].from) {
                if (rows[regions[target - 1].neighborBc[pc - 1] - 1].seen != 0) {
                    change = regions[target - 1].neighborBc[pc - 1];
                }
                if (seenTable[regions[target - 1].neighbor[pc - 1] - 1].seen == 0) {
                    frontierCount++;
                    frontierTable[frontierCount - 1].frontier = regions[target - 1].neighbor[pc - 1];
                    seenTable[regions[target - 1].neighbor[pc - 1] - 1].seen = 1;
                }
                pc++;
            }
        }
    }
}