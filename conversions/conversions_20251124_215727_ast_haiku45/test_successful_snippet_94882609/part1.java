import java.io.*;
import java.util.*;

public class part1 {
    static class Instruction {
        String name;
        double value;
        int seen;
        int region;
    }

    static class Region {
        int lo;
        int hi;
        int seen;
        int from;
        int[][] neighbor;
        int[][] neighborBc;

        Region() {
            neighbor = new int[100][1];
            neighborBc = new int[100][1];
        }
    }

    static Instruction[] instructions = new Instruction[1000];
    static Region[] regions = new Region[1000];
    static int[] frontier = new int[100];
    static int[] seen = new int[1000];

    static int rowCount = 0;
    static int pc = 0;
    static int acc = 0;
    static int regCount = 0;
    static int frontierCount = 0;
    static int offset = 0;
    static int target = 0;
    static int change = 0;

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 1000; i++) {
            instructions[i] = new Instruction();
            regions[i] = new Region();
        }

        readInput();

        pc = 1;
        acc = 0;
        runStep();

        initRegions();
        pc = 1;
        frontierCount = 1;
        frontier[frontierCount] = regCount;
        checkFrontier();

        if (change != 0) {
            System.out.println("Instruction to change: " + change);
        } else {
            System.exit(0);
        }

        if (instructions[change].name.equals("jmp")) {
            instructions[change].name = "nop";
        } else {
            instructions[change].name = "jmp";
        }

        pc = 1;
        acc = 0;
        runStep();
        System.out.println(acc);
    }

    static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input"));
        String line;
        rowCount = 0;

        while ((line = br.readLine()) != null) {
            rowCount++;
            String[] parts = line.split(" ");
            instructions[rowCount].name = parts[0];
            instructions[rowCount].value = Double.parseDouble(parts[1]);
            instructions[rowCount].seen = 0;
        }
        br.close();
    }

    static void runStep() {
        while (instructions[pc].seen != 1) {
            instructions[pc].seen = 1;
            if (instructions[pc].name.equals("acc")) {
                acc += (int) instructions[pc].value;
            }
            if (instructions[pc].name.equals("jmp")) {
                pc = (int) (instructions[pc].value + pc);
            } else {
                pc++;
            }
        }
    }

    static void initRegions() {
        pc = 1;
        regCount = 1;
        regions[regCount].lo = 1;

        while (pc <= rowCount) {
            regions[regCount].seen = instructions[pc].seen;
            instructions[pc].region = regCount;
            if (instructions[pc].name.equals("jmp")) {
                regions[regCount].hi = pc;
                regCount++;
                regions[regCount].lo = pc + 1;
            }
            pc++;
        }
        regCount -= 2;

        pc = 1;
        while (pc <= rowCount) {
            if (!instructions[pc].name.equals("acc")) {
                offset = pc + (int) instructions[pc].value;
                target = instructions[offset].region;
                if (regions[target].neighbor[regions[target].from][0] != instructions[pc].region) {
                    regions[target].from++;
                    regions[target].neighbor[regions[target].from][0] = instructions[pc].region;
                    regions[target].neighborBc[regions[target].from][0] = pc;
                }
            }
            pc++;
        }
    }

    static void checkFrontier() {
        target = frontier[frontierCount];
        frontierCount--;

        if (regions[target - 1].seen == 1) {
            change = regions[target].lo - 1;
        } else {
            pc = 1;
            while (pc <= regions[target].from) {
                if (instructions[regions[target].neighborBc[pc][0]].seen != 0) {
                    change = regions[target].neighborBc[pc][0];
                }
                if (seen[regions[target].neighbor[pc][0]] == 0) {
                    frontierCount++;
                    frontier[frontierCount] = regions[target].neighbor[pc][0];
                    seen[regions[target].neighbor[pc][0]] = 1;
                }
                pc++;
            }
        }
    }
}