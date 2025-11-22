import java.io.*;
import java.util.*;

public class part1 {
    static class Instruction {
        String name;
        int value;
        int seen;
        int region;
    }

    static class Region {
        int lo;
        int hi;
        int seen;
        int fromCount;
        int[] neighbor = new int[100];
        int[] neighborBc = new int[100];
    }

    static Instruction[] instructions = new Instruction[1000];
    static Region[] regions = new Region[1000];
    static int[] frontier = new int[100];
    static int[] seen = new int[1000];
    static int rowCount = 0;
    static int regCount = 0;
    static int frontierCount = 0;
    static int pc = 0;
    static int acc = 0;
    static int change = 0;

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 1000; i++) {
            instructions[i] = new Instruction();
            regions[i] = new Region();
        }

        BufferedReader reader = new BufferedReader(new FileReader("input"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            instructions[rowCount].name = parts[0];
            instructions[rowCount].value = Integer.parseInt(parts[1]);
            instructions[rowCount].seen = 0;
            rowCount++;
        }
        reader.close();

        pc = 0;
        acc = 0;
        while (instructions[pc].seen != 1) {
            runStep();
        }

        initRegions();
        pc = 0;
        frontierCount = 1;
        frontier[0] = regCount;
        while (change == 0 && frontierCount > 0) {
            checkFrontier();
        }

        if (change != 0) {
            System.out.println("Instruction to change: " + change);
        } else {
            return;
        }

        if (instructions[change].name.equals("jmp")) {
            instructions[change].name = "nop";
        } else {
            instructions[change].name = "jmp";
        }

        pc = 0;
        acc = 0;
        for (int i = 0; i < rowCount; i++) {
            instructions[i].seen = 0;
        }
        while (pc < rowCount) {
            runStep();
        }
        System.out.println(acc);
    }

    static void runStep() {
        instructions[pc].seen = 1;
        if (instructions[pc].name.equals("acc")) {
            acc += instructions[pc].value;
        }
        if (instructions[pc].name.equals("jmp")) {
            pc = instructions[pc].value + pc;
        } else {
            pc++;
        }
    }

    static void initRegions() {
        pc = 0;
        regCount = 0;
        regions[regCount].lo = 0;

        while (pc < rowCount) {
            instructions[pc].region = regCount;
            if (instructions[pc].seen == 1) {
                regions[regCount].seen = 1;
            }
            if (instructions[pc].name.equals("jmp")) {
                regions[regCount].hi = pc;
                regCount++;
                regions[regCount].lo = pc + 1;
            }
            pc++;
        }
        regCount -= 2;

        pc = 0;
        while (pc < rowCount) {
            if (!instructions[pc].name.equals("acc")) {
                int offset = pc + instructions[pc].value;
                int target = instructions[offset].region;
                if (regions[target].fromCount == 0 || 
                    regions[target].neighbor[regions[target].fromCount - 1] != instructions[pc].region) {
                    regions[target].neighbor[regions[target].fromCount] = instructions[pc].region;
                    regions[target].neighborBc[regions[target].fromCount] = pc;
                    regions[target].fromCount++;
                }
            }
            pc++;
        }
    }

    static void checkFrontier() {
        int target = frontier[frontierCount - 1];
        frontierCount--;

        if (regions[target - 1].seen == 1) {
            change = regions[target].lo - 1;
        } else {
            pc = 0;
            while (pc < regions[target].fromCount) {
                if (instructions[regions[target].neighborBc[pc]].seen != 0) {
                    change = regions[target].neighborBc[pc];
                }
                if (seen[regions[target].neighbor[pc]] == 0) {
                    frontier[frontierCount] = regions[target].neighbor[pc];
                    frontierCount++;
                    seen[regions[target].neighbor[pc]] = 1;
                }
                pc++;
            }
        }
    }
}