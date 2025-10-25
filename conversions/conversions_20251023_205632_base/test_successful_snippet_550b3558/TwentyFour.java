import java.util.Random;
import java.util.Scanner;

public class TwentyFour {
    private static final int P_MAX = 38;
    private static final int T_LEN = 6;
    private static final int NT_LIM = 5;
    private static final int S_MAX = 32;
    private static final int P4_LIM = 24;
    private static final int OD_LIM = 4;
    private static final int RPX_LIM = 4;
    private static final int TSX_LIM = 14;
    private static final int LOOP_LIM = 1500;

    private static int p, p1, pMax = P_MAX;
    private static String programSyntax = "001 001 000 n 002 000 004 = 003 005 000 n 004 000 002 ; 005 005 000 n 006 000 016 = 007 017 000 n 008 000 015 { 009 011 013 ( 010 001 000 t 011 013 000 | 012 002 000 t 013 000 009 ) 014 017 000 n 015 000 008 } 016 000 006 ; 017 017 000 n 018 000 028 = 019 029 000 n 020 000 027 { 021 023 025 ( 022 003 000 t 023 025 000 | 024 004 000 t 025 000 021 ) 026 029 000 n 027 000 020 } 028 000 018 ; 029 029 000 n 030 000 038 = 031 035 037 ( 032 005 000 t 033 005 000 n 034 006 000 t 035 037 000 | 036 000 000 n 037 000 031 ) 038 000 030 ;";
    private static int t, tLen = T_LEN;
    private static String terminalSymbols = "01 +                               01 -                               01 *                               01 /                               01 (                               01 )                               ";
    private static int nt, ntLim = NT_LIM;
    private static String nonterminalStatements = "000 ....,....,....,....,....,....,....,....,....,001 statement = expression;                      005 expression = term, {('+'|'-') term,};        017 term = factor, {('*'|'/') factor,};          029 factor = ('(' expression, ')' | digit,);     036 digit;";
    private static String indent = "                                                                ";
    private static int r, s, sMax = S_MAX;
    private static String interpreterStack = "";
    private static int l, lLim, lLen = 1;
    private static int nd;
    private static int[] numberDefinitions = new int[4];
    private static int nu;
    private static char[] numberUse = new char[4];
    private static char[] statement = new char[32];
    private static int p4, p4Lim = P4_LIM;
    private static String permutations4 = "1234124313241342142314322134214323142341241324313124314232143241334233432412413414242134231431243214324143124321";
    private static String currentPermutation4;
    private static int cpx, od1, od2, odx, odLim = OD_LIM;
    private static String operatorDefinitions = "+-*/";
    private static String currentOperators;
    private static int co3, rpx, rpxLim = RPX_LIM;
    private static String validRpnForms = "nnonono nnnonoo nnnoono nnnnooo";
    private static String currentRpnForm;
    private static int osx, oqx, oqx1;
    private static String outputQueue;
    private static int workNumber, topNumerator, topDenominator;
    private static int rsx;
    private static int[] numerator = new int[8];
    private static int[] denominator = new int[8];
    private static char errorFound, divideByZeroError;
    private static char NL = '\n', NLFlag = ' ';
    private static char displayLevel = '0';
    private static int loopCount = 0;
    private static String messageArea = "                                                                                                                                ";
    private static char messageLevel;
    private static String messageValue;
    private static String instruction = "                                ";
    private static int tsx, tsxLim = TSX_LIM;
    private static String testStatements = "1234;1 + 2 + 3 + 41234;1 * 2 * 3 * 41234;((1)) * (((2 * 3))) * 41234;((1)) * ((2 * 3))) * 41234;(1 + 2 + 3 + 41234;)1 + 2 + 3 + 41234;1 * * 2 * 3 * 45679;6 - (5 - 7) * 91268;((1 * (8 * 6) / 2))4583;-5-3+(8*4)4583;8 * 4 - 5 - 34583;8 * 4 - (5 + 3)4583;1 * 3 / (2 - 2)2468;(6 * 8) / 4 / 2";

    public static void main(String[] args) {
        System.out.println("start twentyfour");
        generateNumbers();
        System.out.println("type h <enter> to see instructions");
        Scanner scanner = new Scanner(System.in);
        instruction = scanner.nextLine();
        while (!instruction.equals(" ") && !instruction.equals("q")) {
            switch (instruction) {
                case "h":
                    displayInstructions();
                    break;
                case "n":
                    generateNumbers();
                    break;
                case "m":
                    for (int i = 0; i < 4; i++) {
                        numberDefinitions[i] = instruction.charAt(i + 1) - '0';
                    }
                    validateNumber();
                    if (divideByZeroError == ' ' && 24 * topDenominator == topNumerator) {
                        System.out.println(numberDefinitions[0] + "" + numberDefinitions[1] + "" + numberDefinitions[2] + "" + numberDefinitions[3] + " is solved by " + outputQueue.substring(0, oqx));
                    } else {
                        System.out.println(numberDefinitions[0] + "" + numberDefinitions[1] + "" + numberDefinitions[2] + "" + numberDefinitions[3] + " is not solvable");
                    }
                    break;
                case "d0":
                case "d1":
                case "d2":
                case "d3":
                    displayLevel = instruction.charAt(1);
                    break;
                case "e":
                    System.out.println("examples:");
                    for (tsx = 0; tsx < tsxLim; tsx++) {
                        for (int i = 0; i < 4; i++) {
                            numberDefinitions[i] = testStatements.charAt(tsx * 32 + i) - '0';
                        }
                        for (int i = 0; i < 27; i++) {
                            statement[i] = testStatements.charAt(tsx * 32 + i + 5);
                        }
                        evaluateStatement();
                        showResult();
                    }
                    break;
                default:
                    for (int i = 0; i < instruction.length(); i++) {
                        statement[i] = instruction.charAt(i);
                    }
                    evaluateStatement();
                    showResult();
                    break;
            }
            instruction = scanner.nextLine();
        }
        System.out.println("exit twentyfour");
    }

    private static void generateNumbers() {
        Random random = new Random(System.currentTimeMillis());
        do {
            n(1,