import java.util.*;
import java.time.LocalTime;

public class Twentyfour {
    private static class ProgramEntry {
        int address;
        int definition;
        int matching;
        char symbol;
    }

    private static class TerminalSymbol {
        int len;
        String symbol;
    }

    private static class NonterminalStatement {
        int number;
        String statement;
    }

    private static class StackEntry {
        int p;
        int startControl;
        int endControl;
        int alternate;
        char result;
        int count;
        int repeat;
        int nt;
    }

    private static class ResultStack {
        int numerator;
        int denominator;
    }

    private static final int P_MAX = 38;
    private static final int T_LEN = 6;
    private static final int NT_LIM = 5;
    private static final int S_MAX = 32;
    private static final int LOOP_LIM = 1500;

    private ProgramEntry[] programSyntax = new ProgramEntry[P_MAX + 1];
    private TerminalSymbol[] terminalSymbols = new TerminalSymbol[T_LEN + 1];
    private NonterminalStatement[] nonterminalStatements = new NonterminalStatement[NT_LIM + 1];
    private StackEntry[] stack = new StackEntry[S_MAX + 1];
    private ResultStack[] resultStack = new ResultStack[9];

    private int p, p1, t, nt, r, s, l, lLim, lLen, nd, nu;
    private int[] n = new int[5];
    private char[] u = new char[5];
    private char[] statement = new char[33];
    private int osx, oqx, oqx1, rsx;
    private char[] operatorStack = new char[33];
    private char[] outputQueue = new char[33];
    private int topNumerator, topDenominator;
    private char errorFound, divideByZeroError;
    private char displayLevel = '0';
    private int loopCount;
    private Scanner scanner = new Scanner(System.in);

    public Twentyfour() {
        initializeProgramSyntax();
        initializeTerminalSymbols();
        initializeNonterminalStatements();
        for (int i = 0; i <= S_MAX; i++) {
            stack[i] = new StackEntry();
        }
        for (int i = 0; i < 9; i++) {
            resultStack[i] = new ResultStack();
        }
    }

    private void initializeProgramSyntax() {
        String syntax = "001001000n002000004=003005000n004000002;005005000n006000016=007017000n008000015{009011013(010001000t011013000|012002000t013000009)014017000n015000008}016000006;017017000n018000028=019029000n020000027{021023025(022003000t023025000|024004000t025000021)026029000n027000020}028000018;029029000n030000038=031035037(032005000t033005000n034006000t035037000|036000000n037000031)038000030;";
        for (int i = 1; i <= P_MAX; i++) {
            int pos = (i - 1) * 12;
            programSyntax[i] = new ProgramEntry();
            programSyntax[i].address = Integer.parseInt(syntax.substring(pos, pos + 3));
            programSyntax[i].definition = Integer.parseInt(syntax.substring(pos + 4, pos + 7));
            programSyntax[i].matching = Integer.parseInt(syntax.substring(pos + 8, pos + 11));
            programSyntax[i].symbol = syntax.charAt(pos + 11);
        }
    }

    private void initializeTerminalSymbols() {
        String[] symbols = {"+", "-", "*", "/", "(", ")"};
        for (int i = 1; i <= T_LEN; i++) {
            terminalSymbols[i] = new TerminalSymbol();
            terminalSymbols[i].len = 1;
            terminalSymbols[i].symbol = symbols[i - 1];
        }
    }

    private void initializeNonterminalStatements() {
        String[] statements = {
            "statement = expression;",
            "expression = term, {('+'|'-') term,};",
            "term = factor, {('*'|'/') factor,};",
            "factor = ('(' expression, ')' | digit,);",
            "digit;"
        };
        int[] numbers = {1, 5, 17, 29, 36};
        for (int i = 1; i <= NT_LIM; i++) {
            nonterminalStatements[i] = new NonterminalStatement();
            nonterminalStatements[i].number = numbers[i - 1];
            nonterminalStatements[i].statement = statements[i - 1];
        }
    }

    public void run() {
        System.out.println("start twentyfour");
        generateNumbers();
        System.out.println("type h <enter> to see instructions");
        String instruction = scanner.nextLine().trim();

        while (!instruction.isEmpty() && !instruction.equals("q")) {
            if (instruction.equals("h")) {
                displayInstructions();
            } else if (instruction.equals("n")) {
                generateNumbers();
            } else if (instruction.startsWith("m") && instruction.length() >= 5) {
                for (int i = 0; i < 4; i++) {
                    n[i + 1] = instruction.charAt(i + 1) - '0';
                }
                validateNumber();
                if (divideByZeroError == ' ' && 24 * topDenominator == topNumerator) {
                    System.out.println(numberString() + " is solved by " + new String(outputQueue, 0, oqx));
                } else {
                    System.out.println(numberString() + " is not solvable");
                }
            } else if (instruction.matches("d[0-3]")) {
                displayLevel = instruction.charAt(1);
            } else if (instruction.equals("e")) {
                runExamples();
            } else {
                Arrays.fill(statement, ' ');
                for (int i = 0; i < instruction.length() && i < 32; i++) {
                    statement[i] = instruction.charAt(i);
                }
                evaluateStatement();
                showResult();
            }
            System.out.print("instruction? ");
            instruction = scanner.nextLine().trim();
        }

        System.out.println("exit twentyfour");
    }

    private void generateNumbers() {
        Random random = new Random();
        do {
            for (nd = 1; nd <= 4; nd++) {
                do {
                    n[nd] = random.nextInt(9) + 1;
                } while (n[nd] == 0);
            }
            validateNumber();
        } while (divideByZeroError != ' ' || 24 * topDenominator != topNumerator);

        System.out.print("\nnumbers:");
        for (nd = 1; nd <= 4; nd++) {
            System.out.print(" " + n[nd]);
        }
        System.out.println();
    }

    private void validateNumber() {
        String[][] perms = generatePermutations();
        char[] ops = {'+', '-', '*', '/'};
        String[] rpnForms = {"nnonono", "nnnonoo", "nnnoono", "nnnnooo"};

        for (String perm : perms) {
            for (char op1 : ops) {
                for (char op2 : ops) {
                    for (char op3 : ops) {
                        for (String rpnForm : rpnForms) {
                            Arrays.fill(outputQueue, ' ');
                            oqx =