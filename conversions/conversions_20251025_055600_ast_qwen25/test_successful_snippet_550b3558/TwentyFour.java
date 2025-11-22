import java.util.Random;
import java.util.Arrays;

public class TwentyFour {
    private static final int P_MAX = 38;
    private static final int NT_LIM = 5;
    private static final int O_MAX = 32;
    private static final int R_MAX = 8;
    private static final int T_LEN = 6;
    private static final int TSX_LIM = 14;
    private static final int LOOP_LIM = 1500;

    private static final String[] PROGRAM_SYNTAX = {
        "001 001 000 n", "002 000 004 =", "003 005 000 n", "004 000 002 ;",
        "005 005 000 n", "006 000 016 =", "007 017 000 n", "008 000 015 {",
        "009 011 013 (", "010 001 000 t", "011 013 000 |", "012 002 000 t",
        "013 000 009 )", "014 017 000 n", "015 000 008 }", "016 000 006 ;",
        "017 017 000 n", "018 000 028 =", "019 029 000 n", "020 000 027 {",
        "021 023 025 (", "022 003 000 t", "023 025 000 |", "024 004 000 t",
        "025 000 021 )", "026 029 000 n", "027 000 020 }", "028 000 018 ;",
        "029 029 000 n", "030 000 038 =", "031 035 037 (", "032 005 000 t",
        "033 005 000 n", "034 006 000 t", "035 037 000 |", "036 000 000 n",
        "037 000 031 )", "038 000 030 ;"
    };

    private static final String[] TERMINAL_SYMBOLS = {
        "01 +                               ",
        "01 -                               ",
        "01 *                               ",
        "01 /                               ",
        "01 (                               ",
        "01 )                               "
    };

    private static final String[] NONTERMINAL_STATEMENTS = {
        "000 ....,....,....,....,....,....,....,....,....,",
        "001 statement = expression;                      ",
        "005 expression = term, {('+ '|'-' ) term,};        ",
        "017 term = factor, {('*'|'/') factor,};          ",
        "029 factor = ('(' expression, ')' | digit,);     ",
        "036 digit;                                       "
    };

    private static final String[] PERMUTATIONS_4 = {
        "1234", "1243", "1324", "1342", "1423", "1432", "2134", "2143",
        "2314", "2341", "2413", "2431", "3124", "3142", "3214", "3241",
        "3423", "3432", "4123", "4132", "4213", "4231", "4312", "4321"
    };

    private static final String[] OPERATOR_DEFINITIONS = "+-*/";
    private static final String[] VALID_RPN_FORMS = {
        "nnonono", "nnnonoo", "nnnoono", "nnnnooo"
    };

    private static final String[] TEST_STATEMENTS = {
        "1234;1 + 2 + 3 + 4", "1234;1 * 2 * 3 * 4", "1234;((1)) * (((2 * 3))) * 4",
        "1234;((1)) * ((2 * 3))) * 4", "1234;(1 + 2 + 3 + 4", "1234;)1 + 2 + 3 + 4",
        "1234;1 * * 2 * 3 * 4", "5679;6 - (5 - 7) * 9", "1268;((1 * (8 * 6) / 2))",
        "4583;-5-3+(8*4)", "4583;8 * 4 - 5 - 3", "4583;8 * 4 - (5 + 3)",
        "1223;1 * 3 / (2 - 2)", "2468;(6 * 8) / 4 / 2"
    };

    private static final String INDENT = "      ";

    private static int p, p1, t, l, l_len, nd, nu, s, r, osx, oqx, oqx1, rsx, tsx, loop_count;
    private static int[] n = new int[4];
    private static char[] u = new char[4];
    private static char[] c = new char[32];
    private static char[] p_entry = new int[P_MAX * 6];
    private static char[] terminal_symbol_entry = new int[6 * 34];
    private static char[] nonterminal_statement_entry = new int[NT_LIM * 48];
    private static char[] s_entry = new int[O_MAX * 24];
    private static char[] operator_stack = new char[O_MAX];
    private static char[] output_queue = new char[O_MAX];
    private static int[] result_stack_numerator = new int[R_MAX];
    private static int[] result_stack_denominator = new int[R_MAX];
    private static char[] current_permutation_4 = new char[4];
    private static char[] current_operators = new char[3];
    private static char[] current_rpn_form = new char[7];
    private static char[] instruction = new char[32];
    private static char[] statement = new char[32];
    private static char[] message_area = new char[128];
    private static char error_found, divide_by_zero_error, nl_flag, display_level;
    private static String nl = "\n";

    public static void main(String[] args) {
        System.out.println("start twentyfour");
        generateNumbers();
        System.out.println("type h <enter> to see instructions");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.isEmpty() && !input.equals("q")) {
            switch (input) {
                case "h":
                    displayInstructions();
                    break;
                case "n":
                    generateNumbers();
                    break;
                case "e":
                    displayExamples();
                    break;
                default:
                    if (input.charAt(0) == 'm') {
                        for (int i = 0; i < 4; i++) {
                            n[i] = input.charAt(i + 1) - '0';
                        }
                        validateNumber();
                        if (divide_by_zero_error == ' ' && 24 * result_stack_denominator[0] == result_stack_numerator[0]) {
                            System.out.println(input.substring(1, 5) + " is solved by " + new String(output_queue, 0, oqx));
                        } else {
                            System.out.println(input.substring(1, 5) + " is not solvable");
                        }
                    } else if (input.charAt(0) == 'd' && input.length() == 2) {
                        display_level = input.charAt(1);
                    } else {
                        statement = input.toCharArray();
                        evaluateStatement();
                        showResult();
                    }
                    break;
            }
            System.out.print("instruction? ");
            input = scanner.nextLine();
        }
        System.out.println("exit twentyfour");
    }

    private static void generateNumbers() {
        Random random = new Random();
        do {
            n[0] = random.nextInt(10);
            for (int i = 1; i < 4; i++) {
                do {
                    n[i] = random.nextInt(10);
                } while (n[i