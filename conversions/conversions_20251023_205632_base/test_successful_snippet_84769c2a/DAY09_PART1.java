import java.util.Scanner;

public class DAY09_PART1 {
    private static final int LS_BUFFER_SIZE = 25;
    private static final int LS_TOTAL_NUMBER_COUNT = 1000;
    private int LS_TOTAL_NUMBERS_READ = 0;
    private long LS_NUMBER;
    private int LS_INDEX = 1;
    private int LS_INDEX_SEARCH1;
    private int LS_INDEX_SEARCH2;
    private long LS_SUM;
    private long[] LS_BUFFER = new long[LS_BUFFER_SIZE];
    private long LS_TARGET = 0;
    private long LS_MIN;
    private long LS_MAX;
    private long[] LS_ALL_NUMBERS = new long[LS_TOTAL_NUMBER_COUNT];

    public static void main(String[] args) {
        DAY09_PART1 program = new DAY09_PART1();
        program.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < LS_BUFFER_SIZE; i++) {
            readNumber(scanner);
            moveToBuffers();
        }
        while (LS_TOTAL_NUMBERS_READ < LS_TOTAL_NUMBER_COUNT) {
            part1(scanner);
        }
        part2();
        scanner.close();
    }

    private void readNumber(Scanner scanner) {
        if (scanner.hasNextLong()) {
            LS_NUMBER = scanner.nextLong();
        } else {
            System.exit(0);
        }
    }

    private void moveToBuffers() {
        LS_BUFFER[LS_INDEX - 1] = LS_NUMBER;
        LS_ALL_NUMBERS[LS_TOTAL_NUMBERS_READ] = LS_NUMBER;
        LS_INDEX++;
        LS_TOTAL_NUMBERS_READ++;
        if (LS_INDEX > LS_BUFFER_SIZE) {
            LS_INDEX = 1;
        }
    }

    private void part1(Scanner scanner) {
        readNumber(scanner);
        findMatch();
        moveToBuffers();
    }

    private void findMatch() {
        for (LS_INDEX_SEARCH1 = 1; LS_INDEX_SEARCH1 <= LS_BUFFER_SIZE; LS_INDEX_SEARCH1++) {
            for (LS_INDEX_SEARCH2 = 1; LS_INDEX_SEARCH2 <= LS_BUFFER_SIZE; LS_INDEX_SEARCH2++) {
                if (LS_BUFFER[LS_INDEX_SEARCH1 - 1] != LS_BUFFER[LS_INDEX_SEARCH2 - 1]) {
                    LS_SUM = LS_BUFFER[LS_INDEX_SEARCH1 - 1] + LS_BUFFER[LS_INDEX_SEARCH2 - 1];
                    if (LS_SUM == LS_NUMBER) {
                        return;
                    }
                }
            }
        }
        if (LS_SUM != LS_NUMBER) {
            System.out.println("NOT FOUND " + LS_NUMBER);
            LS_TARGET = LS_NUMBER;
        }
    }

    private void part2() {
        for (LS_INDEX_SEARCH1 = 1; LS_INDEX_SEARCH1 <= LS_TOTAL_NUMBER_COUNT; LS_INDEX_SEARCH1++) {
            LS_SUM = 0;
            LS_MAX = 0;
            LS_MIN = 9999999999999999999L;
            for (LS_INDEX_SEARCH2 = LS_INDEX_SEARCH1; LS_INDEX_SEARCH2 <= LS_TOTAL_NUMBER_COUNT; LS_INDEX_SEARCH2++) {
                LS_SUM += LS_ALL_NUMBERS[LS_INDEX_SEARCH2 - 1];
                LS_MAX = Math.max(LS_ALL_NUMBERS[LS_INDEX_SEARCH2 - 1], LS_MAX);
                LS_MIN = Math.min(LS_ALL_NUMBERS[LS_INDEX_SEARCH2 - 1], LS_MIN);
                if (LS_SUM >= LS_TARGET) {
                    break;
                }
            }
            if (LS_SUM == LS_TARGET) {
                LS_SUM = LS_MIN + LS_MAX;
                System.out.println("MIN " + LS_MIN + " MAX " + LS_MAX + " SUM " + LS_SUM);
                return;
            }
        }
    }
}