import java.io.*;
import java.nio.file.*;
import java.util.*;

public class part1 {
    static class Command {
        String name;
        int value;
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
        int fromCount;
        int[] neighbor = new int[100];
        int[] neighborBc = new int[100];
    }

    static List<Row> instructions = new ArrayList<>();
    static List<Region> regions = new ArrayList<>();
    static List<Integer> frontier = new ArrayList<>();
    static Set<Integer> seenSet = new HashSet<>();
    
    static int pc;
    static int acc;
    static int rowCount;
    static int regCount;
    static int frontierCount;
    static int change;

    public static void main(String[] args) throws IOException {
        readInput();
        
        pc = 1;
        acc = 0;
        runStepUntilLoop();
        
        initRegions();
        pc = 1;
        frontierCount = 1;
        frontier.add(regCount);
        checkFrontierUntilChange();
        
        if (change != 0) {
            System.out.println("Instruction to change: " + change);
        } else {
            System.exit(0);
        }
        
        Row changeRow = instructions.get(change - 1);
        if (changeRow.name.equals("jmp")) {
            changeRow.name = "nop";
        } else {
            changeRow.name = "jmp";
        }
        
        pc = 1;
        acc = 0;
        while (pc <= rowCount) {
            runStep();
        }
        System.out.println(acc);
    }

    static void readInput() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input"));
        rowCount = 0;
        
        for (String line : lines) {
            rowCount++;
            String[] parts = line.split(" ");
            Row row = new Row();
            row.name = parts[0];
            row.value = Integer.parseInt(parts[1]);
            row.seen = 0;
            row.region = 0;
            instructions.add(row);
        }
    }

    static void runStepUntilLoop() {
        while (instructions.get(pc - 1).seen == 0) {
            runStep();
        }
    }

    static void runStep() {
        Row currentRow = instructions.get(pc - 1);
        currentRow.seen = 1;
        
        if (currentRow.name.equals("acc")) {
            acc += currentRow.value;
        }
        
        if (currentRow.name.equals("jmp")) {
            pc = pc + currentRow.value;
        } else {
            pc++;
        }
    }

    static void initRegions() {
        pc = 1;
        regCount = 1;
        Region firstRegion = new Region();
        firstRegion.lo = 1;
        firstRegion.fromCount = 0;
        regions.add(firstRegion);
        
        while (pc <= rowCount) {
            Row currentRow = instructions.get(pc - 1);
            currentRow.region = regCount;
            
            if (currentRow.seen == 1) {
                regions.get(regCount - 1).seen = 1;
            }
            
            if (currentRow.name.equals("jmp")) {
                regions.get(regCount - 1).hi = pc;
                regCount++;
                Region newRegion = new Region();
                newRegion.lo = pc + 1;
                newRegion.fromCount = 0;
                regions.add(newRegion);
            }
            
            pc++;
        }
        
        regCount -= 2;
        
        pc = 1;
        while (pc <= rowCount) {
            Row currentRow = instructions.get(pc - 1);
            if (!currentRow.name.equals("acc")) {
                int offset = pc + currentRow.value;
                if (offset >= 1 && offset <= rowCount) {
                    int target = instructions.get(offset - 1).region;
                    Region targetRegion = regions.get(target - 1);
                    int sourceRegion = currentRow.region;
                    
                    if (targetRegion.neighbor[targetRegion.fromCount] != sourceRegion) {
                        targetRegion.neighbor[targetRegion.fromCount] = sourceRegion;
                        targetRegion.neighborBc[targetRegion.fromCount] = pc;
                        targetRegion.fromCount++;
                    }
                }
            }
            pc++;
        }
    }

    static void checkFrontierUntilChange() {
        while (change == 0 && frontierCount > 0) {
            checkFrontier();
        }
    }

    static void checkFrontier() {
        int target = frontier.get(frontierCount - 1);
        frontierCount--;
        
        if (target > 1 && regions.get(target - 2).seen == 1) {
            change = regions.get(target - 1).lo - 1;
        } else {
            pc = 1;
            while (pc <= regions.get(target - 1).fromCount) {
                Region targetRegion = regions.get(target - 1);
                if (instructions.get(targetRegion.neighborBc[pc - 1] - 1).seen != 0) {
                    change = targetRegion.neighborBc[pc - 1];
                }
                
                int neighborRegion = targetRegion.neighbor[pc - 1];
                if (!seenSet.contains(neighborRegion)) {
                    frontierCount++;
                    frontier.add(neighborRegion);
                    seenSet.add(neighborRegion);
                }
                pc++;
            }
        }
    }
}