import java.util.*;

public class TwentyFour {
    private static final int MAX_P = 38;
    private static final int MAX_S = 32;
    private static final int MAX_OPS = 4;
    private static final int MAX_RPN = 7;
    private static final int MAX_NUMBERS = 4;
    private static final int MAX_TOKENS = 32;
    private static final int MAX_LENGTH = 128;

    // Working storage variables
    private static int p = 1;
    private static int p1 = 0;
    private static int pMax = 38;
    private static String programSyntax = 
        "001 001 000 n" +
        "002 000 004 =" +
        "003 005 000 n" +
        "004 000 002 ;" +
        "005 005 000 n" +
        "006 000 016 =" +
        "007 017 000 n" +
        "008 000 015 {" +
        "009 011 013 (" +
        "010 001 000 t" +
        "011 013 000 |" +
        "012 002 000 t" +
        "013 000 009 )" +
        "014 017 000 n" +
        "015 000 008 }" +
        "016 000 006 ;" +
        "017 017 000 n" +
        "018 000 028 =" +
        "019 029 000 n" +
        "020 000 027 {" +
        "021 023 025 (" +
        "022 003 000 t" +
        "023 025 000 |" +
        "024 004 000 t" +
        "025 000 021 )" +
        "026 029 000 n" +
        "027 000 020 }" +
        "028 000 018 ;" +
        "029 029 000 n" +
        "030 000 038 =" +
        "031 035 037 (" +
        "032 005 000 t" +
        "033 005 000 n" +
        "034 006 000 t" +
        "035 037 000 |" +
        "036 000 000 n" +
        "037 000 031 )" +
        "038 000 030 ;";

    private static int[] pAddress = new int[39];
    private static int[] pDefinition = new int[39];
    private static int[] pAlternate = new int[39];
    private static int[] pMatching = new int[39];
    private static char[] pSymbol = new char[39];

    private static int t = 0;
    private static int tLen = 6;
    private static String terminalSymbols = 
        "01 +                               " +
        "01 -                               " +
        "01 *                               " +
        "01 /                               " +
        "01 (                               " +
        "01 )                               ";

    private static int[] terminalSymbolLen = new int[7];
    private static String[] terminalSymbol = new String[7];

    private static int nt = 1;
    private static int ntLim = 5;
    private static String nonterminalStatements = 
        "000 ....,....,....,....,....,....,....,....,....," +
        "001 statement = expression;                      " +
        "005 expression = term, {('+''-') term,};        " +
        "017 term = factor, {('*''/') factor,};          " +
        "029 factor = ('(' expression, ')' | digit,);     " +
        "036 digit;                                       ";

    private static int[] nonterminalStatementNumber = new int[6];
    private static String[] nonterminalStatement = new String[6];

    private static String indent = "|  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |