import java.util.*;
import java.io.*;

public class twentyfour {
    private int p = 1, p1, pMax = 38;
    private int t, tLen = 6;
    private int nt, ntLim = 5;
    private int l, lLim, lLen = 1;
    private int nd, nu;
    private int[] n = new int[5];
    private char[] u = new char[5];
    private char[] c = new char[33];
    private char errorFound = ' ';
    private char divideByZeroError = ' ';
    
    private int p4, p4Lim = 24;
    private int osx, oqx, oqx1;
    private String operatorStack = "";
    private String outputQueue = "";
    private int workNumber;
    private long topNumerator, topDenominator;
    private int rsx;
    private long[] numerator = new long[9];
    private long[] denominator = new long[9];
    
    private int s = 0, r, sMax = 32;
    private int[] sP = new int[33];
    private int[] sStartControl = new int[33];
    private int[] sEndControl = new int[33];
    private int[] sAlternate = new int[33];
    private char[] sResult = new char[33];
    private int[] sCount = new int[33];
    private int[] sRepeat = new int[33];
    private int[] sNt = new int[33];
    
    private int loopCount = 0, loopLim = 1500;
    private char displayLevel = '0';
    private String statement = "";
    private String numberDefinitions = "";
    private String numberUse = "";
    private String instruction = "";
    
    private String[] testStatements = {
        "1234;1 + 2 + 3 + 4",
        "1234;1 * 2 * 3 * 4",
        "1234;((1)) * (((2 * 3))) * 4",
        "1234;((1)) * ((2 * 3))) * 4",
        "1234;(1 + 2 + 3 + 4",
        "1234;)1 + 2 + 3 + 4",
        "1234;1 * * 2 * 3 * 4",
        "5679;6 - (5 - 7) * 9",
        "1268;((1 * (8 * 6) / 2))",
        "4583;-5-3+(8*4)",
        "4583;8 * 4 - 5 - 3",
        "4583;8 * 4 - (5 + 3)",
        "1223;1 * 3 / (2 - 2)",
        "2468;(6 * 8) / 4 / 2"
    };
    
    private String[] permutations4 = {
        "1234", "1243", "1324", "1342", "1423", "1432",
        "2134", "2143", "2314", "2341", "2413", "2431",
        "3124", "3142", "3214", "3241", "3423", "3432",
        "4123", "4132", "4213", "4231", "4312", "4321"
    };
    
    private String[] rpnForms = {"nnonono", "nnnonoo", "nnnoono", "nnnnooo"};
    private String[] terminalSymbols = {"+", "-", "*", "/", "(", ")"};
    private int[] terminalSymbolLen = {1, 1, 1, 1, 1, 1};
    
    public static void main(String[] args) {
        new twentyfour().startTwentyfour();
    }
    
    private void startTwentyfour() {
        System.out.println("start twentyfour");
        generateNumbers();
        System.out.println("type h <enter> to see instructions");
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            instruction = scanner.nextLine().trim();
            if (instruction.isEmpty() || instruction.equals("q")) break;
            
            if (instruction.equals("h")) {
                displayInstructions();
            } else if (instruction.equals("n")) {
                generateNumbers();
            } else if (instruction.startsWith("m") && instruction.length() >= 5) {
                numberDefinitions = instruction.substring(1, 5);
                validateNumber();
                if (divideByZeroError == ' ' && 24 * topDenominator == topNumerator) {
                    System.out.println(numberDefinitions + " is solved by " + outputQueue);
                } else {
                    System.out.println(numberDefinitions + " is not solvable");
                }
            } else if (instruction.matches("d[0-3]")) {
                displayLevel = instruction.charAt(1);
            } else if (instruction.equals("e")) {
                System.out.println("examples:");
                for (String test : testStatements) {
                    String[] parts = test.split(";");
                    numberDefinitions = parts[0];
                    statement = parts[1];
                    evaluateStatement();
                    showResult();
                }
            } else {
                statement = instruction;
                evaluateStatement();
                showResult();
            }
            
            System.out.print("instruction? ");
        }
        
        System.out.println("exit twentyfour");
        scanner.close();
    }
    
    private void generateNumbers() {
        do {
            for (int i = 1; i <= 4; i++) {
                n[i] = (int)(Math.random() * 10);
                while (n[i] == 0) {
                    n[i] = (int)(Math.random() * 10);
                }
            }
            validateNumber();
        } while (divideByZeroError != ' ' || 24 * topDenominator != topNumerator);
        
        System.out.print("\nnumbers:");
        for (int i = 1; i <= 4; i++) {
            System.out.print(" " + n[i]);
        }
        System.out.println();
    }
    
    private void validateNumber() {
        divideByZeroError = ' ';
        topNumerator = 0;
        topDenominator = 1;
        
        for (int p4 = 0; p4 < 24; p4++) {
            String perm = permutations4[p4];
            for (int od1 = 0; od1 < 4; od1++) {
                for (int od2 = 0; od2 < 4; od2++) {
                    for (int od3 = 0; od3 < 4; od3++) {
                        for (int rpx = 0; rpx < 4; rpx++) {
                            outputQueue = "";
                            int cpx = 0;
                            String ops = "" + "+-*/"
                                .charAt(od1) + "+-*/".charAt(od2) + "+-*/".charAt(od3);
                            String rpnForm = rpnForms[rpx];
                            int opIdx = 0;
                            
                            for (int i = 0; i < 7; i++) {
                                if (rpnForm.charAt(i) == 'n') {
                                    outputQueue += n[Character.getNumericValue(perm.charAt(cpx))];
                                    cpx++;
                                } else {
                                    outputQueue += ops.charAt(opIdx);
                                    opIdx++;
                                }
                            }
                            
                            evaluateRpn();
                            if (divideByZeroError == ' ' && 24 * topDenominator == topNumerator) {
                                return;
                            }
                        }
                    }
                }
            }
        }
    }