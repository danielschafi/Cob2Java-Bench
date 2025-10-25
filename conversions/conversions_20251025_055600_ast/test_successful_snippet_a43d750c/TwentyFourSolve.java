import java.io.*;
import java.util.*;

public class TwentyFourSolve {
    private static final String COUNT_FILE_NAME = "solutioncounts";
    private static final int S_LIM = 600;
    private static final int NS_LIM = 6561;
    private static final String PERMUTATIONS = "123412431324134214231432213421432314234124132431312431423214323143214312432134214323142341241324313124314232143231432143124321";
    private static final String OPERATOR_DEFINITIONS = "+-*/";
    private static final String RPN_FORMS = "nnonononnonnoonnnnonoonnnnooonnnnnooo";
    private static final String SPACES = "                ";

    private String countFileName = COUNT_FILE_NAME;
    private String countFileStatus = "00";
    private char[] countRecord = new char[7];
    private int[] n = new int[4];
    private int nd;
    private char[] numberDefinition = new char[4];
    private char[] numberDefinition9 = new char[4];
    private char[] commandInput = new char[16];
    private char[] command = new char[5];
    private int numberCount;
    private int l1;
    private int l2;
    private int expressions;
    private int px;
    private char[] currentPermutation = new char[4];
    private int cpx;
    private int od1;
    private int od2;
    private int od3;
    private int cox;
    private char[] currentOperators = new char[3];
    private int rpx;
    private char[] currentRpnForm = new char[7];
    private int oqx;
    private char[] outputQueue = new char[7];
    private int workNumber;
    private int topNumerator;
    private int topDenominator;
    private int rsx;
    private ResultStack[] resultStack = new ResultStack[8];
    private char divideByZeroError;
    private int s;
    private int sMax;
    private int sLim = S_LIM;
    private int sMaxValue = 0;
    private char[][] solution = new char[S_LIM][7];
    private int sc;
    private int sc1;
    private int sc2;
    private int scMax;
    private int scLim = S_LIM;
    private int[] solutionCount = new int[S_LIM];
    private int ns;
    private int nsMax;
    private int nsLim = NS_LIM;
    private NumberSolution[] numberSolutions = new NumberSolution[NS_LIM];
    private int recordCounts;
    private int totalSolutions;

    public TwentyFourSolve() {
        for (int i = 0; i < 8; i++) {
            resultStack[i] = new ResultStack();
        }
        for (int i = 0; i < NS_LIM; i++) {
            numberSolutions[i] = new NumberSolution();
        }
    }

    public static void main(String[] args) {
        TwentyFourSolve twentyFourSolve = new TwentyFourSolve();
        twentyFourSolve.startTwentyFourSolve();
    }

    private void startTwentyFourSolve() {
        System.out.println("start twentyfoursolve");
        displayInstructions();
        getCommand();
        while (!isSpaces(commandInput)) {
            System.out.println();
            initialize(command, numberCount);
            unstring(commandInput, command, numberCount);
            System.arraycopy(commandInput, 0, numberDefinition, 0, 4);
            Arrays.fill(commandInput, ' ');
            switch (new String(command).trim()) {
                case "h":
                case "help":
                    displayInstructions();
                    break;
                case "list":
                    if (nsMax == 0) {
                        loadSolutionCounts();
                    }
                    listCounts();
                    break;
                case "show":
                    if (nsMax == 0) {
                        loadSolutionCounts();
                    }
                    showNumbers();
                    break;
                default:
                    if (!isNumeric(numberDefinition9)) {
                        System.out.println("invalid number");
                    } else {
                        getSolutions();
                        displaySolutions();
                    }
                    break;
            }
            if (isSpaces(commandInput)) {
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
        System.out.print("(h for help)? ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.arraycopy(input.toCharArray(), 0, commandInput, 0, Math.min(input.length(), 16));
    }

    private void askForMore() {
        System.out.println();
        l1 = 0;
        l2++;
        if (l2 == 10) {
            System.out.print("more (<enter>)? ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            System.arraycopy(input.toCharArray(), 0, commandInput, 0, Math.min(input.length(), 16));
            l2 = 0;
        }
    }

    private void listCounts() {
        scMax++;
        sc = scMax;
        System.out.println("there are " + sc + " solution counts");
        System.out.println();
        System.out.println("solutions/numbers");
        l1 = 0;
        l2 = 0;
        for (sc = 1; sc <= scMax; sc++) {
            if (solutionCount[sc] > 0) {
                sc1 = sc - 1;
                System.out.print(sc1 + "/" + solutionCount[sc] + " ");
                l1++;
                if (l1 == 8) {
                    askForMore();
                }
            }
        }
        if (l1 > 0) {
            System.out.println();
        }
    }

    private void showNumbers() {
        scMax++;
        sc1 = scMax;
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
        for (ns = 1; ns <= nsMax; ns++) {
            if (numberSolutions[ns].nsCount == numberCount) {
                System.out.print(new String(numberSolutions[ns].nsNumber) + " ");
                l1++;
                if (l1 == 14) {
                    askForMore();
                }
            }
        }
        if (l1 > 0) {
            System.out.println();
        }
    }

    private void displaySolutions() {
        switch (sMax) {
            case 0:
                System.out.println(new String(numberDefinition) + " has no solutions");
                break;
            case 1:
                System.out.println(new String(numberDefinition) + " has 1 solution");
                break;
            default:
                System.out.println(new String(numberDefinition) + " has " + sMax + " solutions");
                break;
        }
        System.out.println();
        l1 = 0;
        l2 = 0;
        for (s = 1; s <= sMax; s++) {
            iS = 0;
            for (int i = 1; i <= 7; i++) {
                if (solution[s][i - 1] >= '1' && solution[s][i - 1] <= '9') {
                    iS++;
                    iStack[iS - 1] = solution[s][i - 1];
                } else {
                    iS1 = iS - 1;
                    StringBuilder iWork = new StringBuilder();
                    iWork.append('(').append(iStack[iS1]).append(solution[s][i - 1]).append(iStack[iS]).append(')');
                    iStack[iS1] = iWork.toString().charAt(0);
                    iS--;
                }
            }
            System.out.print(new String(solution[s]) + " " + new String(iStack, 0, iS + 1) + "  ");
            l1++;
            if (l1 == 3) {
                askForMore();
            }
        }
        if (l1 >