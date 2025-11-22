import java.util.*;
import java.io.*;

public class twentyfour {
    static class ProgramEntry {
        int address;
        int definition;
        int alternate;
        int matching;
        char symbol;
    }

    static class TerminalSymbolEntry {
        int len;
        String symbol;
    }

    static class NonterminalStatementEntry {
        int number;
        String statement;
    }

    static class StackEntry {
        int p;
        int startControl;
        int endControl;
        int alternate;
        char result;
        int count;
        int repeat;
        int nt;
    }

    static class ResultStack {
        long numerator;
        long denominator;
    }

    static int p, p1, pMax = 38;
    static ProgramEntry[] pEntry = new ProgramEntry[39];
    static int t, tLen = 6;
    static TerminalSymbolEntry[] terminalSymbol = new TerminalSymbolEntry[7];
    static int nt, ntLim = 5;
    static NonterminalStatementEntry[] nonterminalStatement = new NonterminalStatementEntry[6];
    static int r, s, sMax = 32;
    static StackEntry[] sEntry = new StackEntry[33];
    static int l, lLim, lLen = 1;
    static int nd;
    static int[] n = new int[5];
    static int nu;
    static char[] u = new char[5];
    static char[] c = new char[33];
    static int[] c9 = new int[33];
    static int p4, p4Lim = 24;
    static String[] permutation4 = {
        "1234", "1243", "1324", "1342", "1423", "1432",
        "2134", "2143", "2314", "2341", "2413", "2431",
        "3124", "3142", "3214", "3241", "3423", "3432",
        "4123", "4132", "4213", "4231", "4312", "4321"
    };
    static String currentPermutation4;
    static int cpx, od1, od2, odx, odLim = 4;
    static String operatorDefinitions = "+-*/";
    static String currentOperators;
    static int rpx, rpxLim = 4;
    static String[] rpnForm = {
        "nnonono", "nnnonoo", "nnnoono", "nnnnooo"
    };
    static String currentRpnForm;
    static int osx, oqx, oqx1;
    static String operatorStack = "";
    static String outputQueue = "";
    static long workNumber;
    static long topNumerator, topDenominator;
    static int rsx;
    static ResultStack[] resultStack = new ResultStack[9];
    static char errorFound = ' ';
    static char divideByZeroError = ' ';
    static char displayLevel = '0';
    static int loopLim = 1500, loopCount = 0;
    static String statement = "";
    static int tsx, tsxLim = 14;
    static String[][] testStatements = {
        {"1234", "1 + 2 + 3 + 4"},
        {"1234", "1 * 2 * 3 * 4"},
        {"1234", "((1)) * (((2 * 3))) * 4"},
        {"1234", "((1)) * ((2 * 3))) * 4"},
        {"1234", "(1 + 2 + 3 + 4"},
        {"1234", ")1 + 2 + 3 + 4"},
        {"1234", "1 * * 2 * 3 * 4"},
        {"5679", "6 - (5 - 7) * 9"},
        {"1268", "((1 * (8 * 6) / 2))"},
        {"4583", "-5-3+(8*4)"},
        {"4583", "8 * 4 - 5 - 3"},
        {"4583", "8 * 4 - (5 + 3)"},
        {"1223", "1 * 3 / (2 - 2)"},
        {"2468", "(6 * 8) / 4 / 2"}
    };

    static {
        initializeProgramSyntax();
        initializeTerminalSymbols();
        initializeNonterminalStatements();
        for (int i = 0; i < 33; i++) {
            sEntry[i] = new StackEntry();
        }
        for (int i = 0; i < 9; i++) {
            resultStack[i] = new ResultStack();
        }
    }

    static void initializeProgramSyntax() {
        String syntax = "001001000n002000004=003005000n004000002;005005000n006000016=007017000n008000015{009011013(010001000t011013000|012002000t013000009)014017000n015000008}016000006;017017000n018000028=019029000n020000027{021023025(022003000t023025000|024004000t025000021)026029000n027000020}028000018;029029000n030000038=031035037(032005000t033005000n034006000t035037000|036000000n037000031)038000030;";
        
        for (int i = 1; i <= 38; i++) {
            pEntry[i] = new ProgramEntry();
            int idx = (i - 1) * 13;
            pEntry[i].address = Integer.parseInt(syntax.substring(idx, idx + 3));
            pEntry[i].definition = Integer.parseInt(syntax.substring(idx + 4, idx + 7));
            pEntry[i].alternate = Integer.parseInt(syntax.substring(idx + 8, idx + 11));
            pEntry[i].symbol = syntax.charAt(idx + 12);
        }
    }

    static void initializeTerminalSymbols() {
        String[] symbols = {"+", "-", "*", "/", "(", ")"};
        for (int i = 0; i < 6; i++) {
            terminalSymbol[i] = new TerminalSymbolEntry();
            terminalSymbol[i].len = 1;
            terminalSymbol[i].symbol = symbols[i];
        }
    }

    static void initializeNonterminalStatements() {
        String[] statements = {
            "000 ....",
            "001 statement = expression;",
            "005 expression = term, {('+'|'-') term,};",
            "017 term = factor, {('*'|'/') factor,};",
            "029 factor = ('(' expression, ')' | digit,);",
            "036 digit;"
        };
        for (int i = 0; i < 6; i++) {
            nonterminalStatement[i] = new NonterminalStatementEntry();
            nonterminalStatement[i].number = Integer.parseInt(statements[i].substring(0, 3));
            nonterminalStatement[i].statement = statements[i].substring(4);
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("start twentyfour");
        generateNumbers();
        System.out.println("type h <enter> to see instructions");
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String instruction;
        
        while ((instruction = br.readLine()) != null) {
            if (instruction.isEmpty() || instruction.equals("q")) break;
            
            if (instruction.equals("h")) {
                displayInstructions();
            } else if (instruction.equals("n")) {
                generateNumbers();
            } else if (instruction.startsWith("m") && instruction.length() >= 5) {
                String nums = instruction.substring(1, 5);
                for (int i = 0; i < 4; i++) {
                    n[i