import java.util.*;

public class TwentyFour {
    private static final int P_MAX = 38;
    private static final int T_LEN = 6;
    private static final int NT_LIM = 5;
    private static final int S_MAX = 32;
    private static final int P4_LIM = 24;
    private static final int OD_LIM = 4;
    private static final int RPX_LIM = 4;
    private static final int LOOP_LIM = 1500;
    
    // Grammar rules
    private static final String[] PROGRAM_SYNTAX = {
        "001 001 000 n",
        "002 000 004 =",
        "003 005 000 n",
        "004 000 002 ;",
        "005 005 000 n",
        "006 000 016 =",
        "007 017 000 n",
        "008 000 015 {",
        "009 011 013 (",
        "010 001 000 t",
        "011 013 000 |",
        "012 002 000 t",
        "013 000 009 )",
        "014 017 000 n",
        "015 000 008 }",
        "016 000 006 ;",
        "017 017 000 n",
        "018 000 028 =",
        "019 029 000 n",
        "020 000 027 {",
        "021 023 025 (",
        "022 003 000 t",
        "023 025 000 |",
        "024 004 000 t",
        "025 000 021 )",
        "026 029 000 n",
        "027 000 020 }",
        "028 000 018 ;",
        "029 029 000 n",
        "030 000 038 =",
        "031 035 037 (",
        "032 005 000 t",
        "033 005 000 n",
        "034 006 000 t",
        "035 037 000 |",
        "036 000 000 n",
        "037 000 031 )",
        "038 000 030 ;"
    };
    
    // Terminal symbols
    private static final String[] TERMINAL_SYMBOLS = {
        "01 +                               ",
        "01 -                               ",
        "01 *                               ",
        "01 /                               ",
        "01 (                               ",
        "01 )                               "
    };
    
    // Nonterminal statements
    private static final String[] NONTERMINAL_STATEMENTS = {
        "000 ....,....,....,....,....,....,....,....,....,",
        "001 statement = expression;                      ",
        "005 expression = term, {('+'|'-') term,};        ",
        "017 term = factor, {('*'|'/') factor,};          ",
        "029 factor = ('(' expression, ')' | digit,);     ",
        "036 digit;                                       "
    };
    
    // Permutations
    private static final String[] PERMUTATIONS_4 = {
        "1234", "1243", "1324", "1342", "1423", "1432",
        "2134", "2143", "2314", "2341", "2413", "2431",
        "3124", "3142", "3214", "3241", "3423", "3432",
        "4123", "4132", "4213", "4231", "4312", "4321"
    };
    
    // Valid RPN forms
    private static final String[] VALID_RPN_FORMS = {
        "nnonono", "nnnonoo", "nnnoono", "nnnnooo"
    };
    
    // Operators
    private static final String OPERATORS = "+-*/";
    
    // Variables
    private static int p = 1;
    private static int p1 = 0;
    private static int pMax = P_MAX;
    private static int t = 0;
    private static int tLen = T_LEN;
    private static int nt = 1;
    private static int ntLim = NT_LIM;
    private static int[] sEntry = new int[S_MAX];
    private static int s = 0;
    private static int r = 0;
    private static int sMax = S_MAX;
    private static int[] sP = new int[S_MAX];
    private static int[] sStartControl = new int[S_MAX];
    private static int[] sEndControl = new int[S_MAX];
    private static int[] sAlternate = new int[S_MAX];
    private static char[] sResult = new char[S_MAX];
    private static int[] sCount = new int[S_MAX];
    private static int[] sRepeat = new int[S_MAX];
    private static int[] sNt = new int[S_MAX];
    
    private static int l = 0;
    private static int lLim = 0;
    private static int lLen = 1;
    private static int nd = 0;
    private static int[] n = new int[5]; // 1-based indexing
    private static int nu = 0;
    private static char[] u = new char[5]; // 1-based indexing
    private static char[] c = new char[33]; // 1-based indexing
    private static int p4 = 0;
    private static int p4Lim = P4_LIM;
    private static int cpx = 0;
    private static int od1 = 0;
    private static int od2 = 0;
    private static int odx = 0;
    private static int odLim = OD_LIM;
    private static int co3 = 0;
    private static int rpx = 0;
    private static int rpxLim = RPX_LIM;
    private static int osx = 0;
    private static char[] operatorStack = new char[33]; // 1-based indexing
    private static int oqx = 0;
    private static int oqx1 = 0;
    private static char[] outputQueue = new char[33]; // 1-based indexing
    private static int workNumber = 0;
    private static int topNumerator = 0;
    private static int topDenominator = 1;
    private static int rsx = 0;
    private static int[] numerator = new int[9]; // 1-based indexing
    private static int[] denominator = new int[9]; // 1-based indexing
    private static char errorFound = ' ';
    private static char divideByZeroError = ' ';
    private static int loopCount = 0;
    private static char[] statement = new char[33]; // 1-based indexing
    private static char[] instruction = new char[33]; // 1-based indexing
    private static int tsx = 0;
    private static int tsxLim = 14;
    private static String[] testNumbers = {
        "1234", "1234", "1234", "1234", "1234", 
        "1234", "1234", "5679", "1268", "4583",
        "4583", "4583", "1223", "2468"
    };
    private static String[] testStatements = {
        "1 + 2 + 3 + 4", "1 * 2 * 3 * 4", "((1)) * (((2 * 3))) * 4",
        "((1)) * ((2 * 3))) * 4", "(1 + 2 + 3 + 4", ")1 + 2 + 3 + 4",
        "1 * * 2 * 3 * 4", "6 - (5 - 7) * 9", "((1 * (8 * 6) / 2))",
        "-5-3+(8*4)", "8 * 4 - 5 - 3", "8 * 4 -