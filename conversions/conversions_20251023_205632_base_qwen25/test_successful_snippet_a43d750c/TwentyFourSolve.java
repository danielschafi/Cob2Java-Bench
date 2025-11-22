import java.io.*;
import java.util.*;

public class TwentyFourSolve {
    private static final String COUNT_FILE_NAME = "solutioncounts";
    private static final int S_LIM = 600;
    private static final int SC_LIM = 600;
    private static final int NS_LIM = 6561;
    private static final String[] PERMUTATIONS = {
        "1234", "1243", "1324", "1342", "1423", "1432",
        "2134", "2143", "2314", "2341", "2413", "2431",
        "3124", "3142", "3214", "3241", "3423", "3432",
        "4123", "4132", "4213", "4231", "4312", "4321"
    };
    private static final String OPERATOR_DEFINITIONS = "+-*/";
    private static final String[] RPN_FORMS = {
        "nnonono", "nnonnoo", "nnnonoo", "nnnoono", "nnnnooo"
    };

    private String commandInput = new String(new char[16]);
    private String command = new String(new char[5]);
    private int numberCount;
    private int l1;
    private int l2;
    private String expressions = new String(new char[12]);
    private int px;
    private String currentPermutation = new String(new char[4]);
    private int cpx;
    private int od1;
    private int od2;
    private int od3;
    private int cox;
    private String currentOperators = new String(new char[3]);
    private int rpx;
    private String currentRpnForm = new String(new char[7]);
    private int oqx;
    private String outputQueue = new String(new char[7]);
    private int workNumber;
    private int topNumerator;
    private int topDenominator;
    private int rsx;
    private int[] resultStackNumerator = new int[8];
    private int[] resultStackDenominator = new int[8];
    private String divideByZeroError = new String(new char[1]);
    private int s;
    private int sMax;
    private String[] solution = new String[S_LIM];
    private int sc;
    private int sc1;
    private int sc2;
    private int scMax;
    private int[] solutionCount = new int[SC_LIM];
    private int ns;
    private int nsMax;
    private int[] nsCount = new int[NS_LIM];
    private String[] nsNumber = new String[NS_LIM];
    private int recordCounts;
    private int totalSolutions;
    private int[] n = new int[4];

    public static void main(String[] args) {
        TwentyFourSolve twentyFourSolve = new TwentyFourSolve();
        twentyFourSolve.startTwentyFourSolve();
    }

    public void startTwentyFourSolve() {
        System.out.println("start twentyfoursolve");
        displayInstructions();
        getCommand();
        while (!commandInput.trim().isEmpty()) {
            System.out.println();
            initializeCommandAndNumberCount();
            unstringCommandInput();
            moveCommandInputToNumberDefinition();
            evaluateCommand();
            if (commandInput.trim().isEmpty()) {
                getCommand();
            }
        }
        System.out.println("exit twentyfoursolve");
    }

    private void displayInstructions() {
        System.out.println();
        System.out.println("enter a number <n> as four integers from 1-9 to see its solutions");
        System.out.println("enter list to see counts of solutions for all numbers");
        System.out.println("enter show <n> to see numbers having <n> solutions");
        System.out.println("<enter> ends the program");
    }

    private void getCommand() {
        System.out.println();
        Arrays.fill(commandInput, ' ');
        System.out.print("(h for help)?");
        Scanner scanner = new Scanner(System.in);
        commandInput = scanner.nextLine();
    }

    private void askForMore() {
        System.out.println();
        l1 = 0;
        l2++;
        if (l2 == 10) {
            System.out.print("more (<enter>)?");
            Scanner scanner = new Scanner(System.in);
            commandInput = scanner.nextLine();
            l2 = 0;
        }
    }

    private void listCounts() {
        scMax++;
        sc = 1;
        System.out.println("there are " + sc + " solution counts");
        System.out.println();
        System.out.println("solutions/numbers");
        l1 = 0;
        l2 = 0;
        while (sc <= scMax && commandInput.trim().isEmpty()) {
            if (solutionCount[sc] > 0) {
                sc1 = sc - 1;
                System.out.print(sc1 + "/" + solutionCount[sc] + " ");
                l1++;
                if (l1 == 8) {
                    askForMore();
                }
            }
            sc++;
        }
        if (l1 > 0) {
            System.out.println();
        }
    }

    private void showNumbers() {
        sc1 = numberCount + 1;
        if (numberCount >= scMax) {
            System.out.println("no number has " + numberCount + " solutions");
            return;
        }
        if (solutionCount[sc1] == 1 && numberCount == 1) {
            System.out.println("1 number has 1 solution");
        } else if (solutionCount[sc1] == 1) {
            System.out.println("1 number has " + numberCount + " solutions");
        } else if (numberCount == 1) {
            System.out.println(solutionCount[sc1] + " numbers have 1 solution");
        } else {
            System.out.println(solutionCount[sc1] + " numbers have " + numberCount + " solutions");
        }
        System.out.println();
        l1 = 0;
        l2 = 0;
        ns = 1;
        while (ns <= nsMax && commandInput.trim().isEmpty()) {
            if (nsCount[ns] == numberCount) {
                System.out.print(nsNumber[ns] + " ");
                l1++;
                if (l1 == 14) {
                    askForMore();
                }
            }
            ns++;
        }
        if (l1 > 0) {
            System.out.println();
        }
    }

    private void displaySolutions() {
        switch (sMax) {
            case 0:
                System.out.println(numberDefinition() + " has no solutions");
                break;
            case 1:
                System.out.println(numberDefinition() + " has 1 solution");
                break;
            default:
                System.out.println(numberDefinition() + " has " + sMax + " solutions");
                break;
        }
        System.out.println();
        l1 = 0;
        l2 = 0;
        s = 1;
        while (s <= sMax && commandInput.trim().isEmpty()) {
            iS = 0;
            i = 1;
            while (i <= 7) {
                if (solution[s].charAt(i - 1) >= '1' && solution[s].charAt(i - 1) <= '9') {
                    iS++;
                    iStack[iS] = String.valueOf(solution[s].charAt(i - 1));
                } else {
                    iS1 = iS - 1;
                    iWork = "(" + iStack[iS1] + solution[s].charAt(i - 1) + iStack[iS] + ")";
                    iWork = iWork.trim();
                    iStack[iS1] = iWork;
                    iS--;
                }
                i++;
            }
            System.out.print(solution[s] + " " + iStack[1] + "   ");
            l1++;
            if (l1 == 3) {
                askForMore();
            }
            s++;
        }
        if (l1 > 0) {
            System.out.println();
        }
    }

    private void loadSolutionCounts() {
        nsMax = 0;
        scMax = 0;
        recordCounts = 0;
        Arrays.fill(solutionCount, 0);
        try (BufferedReader reader = new BufferedReader(new FileReader(COUNT_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recordCounts++;
                incrementNsMax();
                numberSolutions[nsMax] = line.substring(0, 4);
                nsCount[nsMax] = Integer.parseInt(line.substring(4, 7));
                sc = nsCount[nsMax];
                if (sc > scLim) {
                    System.out.println("sc " + sc + " exceeds sc-lim " + scLim);
                    System.exit(1);
                }
                if (sc > scMax) {
                    scMax = sc;
                }
                solutionCount[sc]++;
            }
        } catch (IOException e) {
            createCountFile();
            nsMax = 0;
            scMax = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(COUNT_FILE_NAME))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    recordCounts++;
                    incrementNsMax();
                    numberSolutions[nsMax] = line.substring(0, 4);
                    nsCount[nsMax] = Integer