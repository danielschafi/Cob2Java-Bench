import java.io.*;
import java.util.*;

public class Lexer {
    private static final String INPUT_NAME = System.getProperty("input.name", "");
    private static final int OUT_LIM = 48;
    
    private static String inputRecord = "";
    private static String inputStatus = "";
    private static String outputName = "";
    private static String outputStatus = "";
    private static String outputRecord = "";
    private static int lineNo = 0;
    private static int colNo = 0;
    private static int colNoMax = 0;
    private static int colIncrement = 1;
    private static int startCol = 0;
    private static int outx = 0;
    private static String outputLine = "";
    private static String outLine = "";
    private static String outColumn = "";
    private static String token = "";
    private static String outValue = "";
    private static int outInteger = 0;
    private static int outInteger1 = 0;
    private static String errorMessage = "";
    private static String scanState = "";
    private static char currentCharacter = '\0';
    private static char previousCharacter = '\0';
    private static String errorLine = "";
    private static String errorCol = "";
    
    private static BufferedReader reader;
    
    public static void main(String[] args) {
        try {
            startLexer();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(-1);
        }
    }
    
    private static void startLexer() throws IOException {
        if (!INPUT_NAME.isEmpty()) {
            try {
                reader = new BufferedReader(new FileReader(INPUT_NAME));
                inputStatus = "00";
            } catch (FileNotFoundException e) {
                errorMessage = "in lexer " + INPUT_NAME + " not found";
                reportError();
            }
        }
        
        readInputFile();
        
        while (!inputStatus.equals("00")) {
            lineNo++;
            outLine = String.format("%5d", lineNo);
            colNoMax = trim(inputRecord).length();
            colNo = 1;
            previousCharacter = '\0';
            
            while (colNo <= colNoMax) {
                outColumn = String.format("%6d", colNo);
                currentCharacter = inputRecord.charAt(colNo - 1);
                
                switch (scanState) {
                    case "identifier":
                        if ((currentCharacter >= 'A' && currentCharacter <= 'Z') ||
                            (currentCharacter >= 'a' && currentCharacter <= 'z') ||
                            (currentCharacter >= '0' && currentCharacter <= '9') ||
                            currentCharacter == '_') {
                            incrementOutx();
                            outValue = outValue.substring(0, outx - 1) + currentCharacter + outValue.substring(outx);
                            if (colNo == colNoMax) {
                                processIdentifier();
                            }
                        } else {
                            processIdentifier();
                            if (currentCharacter != ' ') {
                                colIncrement = 0;
                            }
                        }
                        break;
                        
                    case "integer":
                        if (currentCharacter >= '0' && currentCharacter <= '9') {
                            incrementOutx();
                            outValue = outValue.substring(0, outx - 1) + currentCharacter + outValue.substring(outx);
                            if (colNo == colNoMax) {
                                try {
                                    outInteger = Integer.parseInt(outValue);
                                } catch (NumberFormatException e) {
                                    // Handle parsing error
                                }
                                token = "Integer";
                            }
                        } else if ((currentCharacter >= 'A' && currentCharacter <= 'Z') ||
                                   (currentCharacter >= 'a' && currentCharacter <= 'z')) {
                            errorMessage = "in lexer invalid integer";
                            reportError();
                        } else {
                            if (outx > 5) {
                                try {
                                    outInteger1 = Integer.parseInt(outValue);
                                } catch (NumberFormatException e) {
                                    // Handle parsing error
                                }
                            } else {
                                try {
                                    outInteger = Integer.parseInt(outValue);
                                } catch (NumberFormatException e) {
                                    // Handle parsing error
                                }
                            }
                            token = "Integer";
                            if (currentCharacter != ' ') {
                                colIncrement = 0;
                            }
                        }
                        break;
                        
                    case "comment":
                        if (previousCharacter == '*' && currentCharacter == '/') {
                            token = "comment";
                        }
                        break;
                        
                    case "quote":
                        switch (currentCharacter + "," + outx) {
                            case "\"\",0":
                                errorMessage = "in lexer empty string";
                                reportError();
                                break;
                            case "\"\",any":
                                incrementOutx();
                                outValue = outValue.substring(0, outx - 1) + currentCharacter + outValue.substring(outx);
                                token = "String";
                                break;
                            default:
                                if (colNo == colNoMax) {
                                    errorMessage = "in lexer missing close quote";
                                    reportError();
                                } else {
                                    incrementOutx();
                                    outValue = outValue.substring(0, outx - 1) + currentCharacter + outValue.substring(outx);
                                }
                                break;
                        }
                        break;
                        
                    case "character":
                        switch (currentCharacter + "," + outx) {
                            case "'\",0":
                                errorMessage = "in lexer empty character constant";
                                reportError();
                                break;
                            case "'\",1":
                                outInteger = (int)outValue.charAt(0) - 1;
                                token = "Integer";
                                break;
                            case "'\",2":
                                if (outValue.substring(0, 2).equals("\\n")) {
                                    outInteger = 10;
                                } else if (outValue.substring(0, 2).equals("\\\\")) {
                                    outInteger = (int)'\\' - 1;
                                } else {
                                    errorMessage = "in lexer unknown escape sequence " + outValue.substring(0, 2);
                                    reportError();
                                }
                                token = "Integer";
                                break;
                            case "'\",any":
                                errorMessage = "in lexer multicharacter constant";
                                reportError();
                                break;
                            default:
                                if (colNo == colNoMax) {
                                    errorMessage = "in lexer missing close quote";
                                    reportError();
                                } else {
                                    incrementOutx();
                                    outValue = outValue.substring(0, outx - 1) + currentCharacter + outValue.substring(outx);
                                }
                                break;
                        }
                        break;
                        
                    case "and":
                        if (previousCharacter == '&' && currentCharacter == '&') {
                            token = "Op_and";
                        } else {
                            errorMessage = "in lexer AND error";
                            reportError();
                        }
                        break;
                        
                    case "or":
                        if (previousCharacter == '|' && currentCharacter == '|') {
                            token = "Op_or";
                        } else {
                            errorMessage = "in lexer OR error";
                            reportError();
                        }
                        break;
                        
                    case "ambiguous":
                        switch (previousCharacter + "," + currentCharacter) {
                            case "/*":
                                scanState = "comment";
                                startCol = colNo - 1;
                                break;
                            case "/*":
                                token = "Op_divide";
                                colIncrement = 0;
                                break;
                            case "==":
                                token = "Op_equal";
                                break;
                            case "=,":
                                token = "Op_assign";
                                colIncrement = 0;
                                break;
                            case "<=":
                                token = "Op_lessequal";
                                break;
                            case "<,":
                                token = "Op_less";
                                colIncrement = 0;
                                break;
                            case ">=":
                                token = "Op_greaterequal";
                                break;
                            case ">,":
                                token = "Op_greater";
                                colIncrement = 0;
                                break;
                            case "!=":
                                token = "Op_notequal";
                                break;
                            case "!,":
                                token = "Op_not";
                                colIncrement = 0;
                                break;
                            default:
                                errorMessage = "in lexer " + scanState + " unknown character \"" + currentCharacter + "\" with previous character \"" + previousCharacter + "\"";
                                reportError();
                                break;
                        }
                        break;
                        
                    default:
                        startCol = colNo;
                        switch (currentCharacter) {
                            case ' ':
                                break;
                            case 'A': case 'B': case 'C': case 'D': case 'E': case 'F': case 'G': case 'H': case 'I': case 'J': case 'K': case 'L': case 'M': 
                            case 'N': case 'O': case 'P': case 'Q': case 'R': case 'S': case 'T': case 'U': case 'V': case 'W': case 'X': case 'Y': case 'Z':
                            case 'a': case 'b': case 'c': case 'd': case 'e': case 'f': case 'g': case 'h': case 'i': case 'j': case 'k': case 'l': case 'm': 
                            case 'n': case 'o': case 'p': case 'q': case 'r': case 's': case 't': case 'u': case 'v': case 'w': case 'x': case 'y': case 'z':
                                scanState = "identifier";
                                outx = 1;
                                outValue = "" + currentCharacter;
                                break;
                            case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
                                scanState = "integer";
                                outx = 1;
                                outValue = "" + currentCharacter;
                                break;
                            case '&':
                                scanState