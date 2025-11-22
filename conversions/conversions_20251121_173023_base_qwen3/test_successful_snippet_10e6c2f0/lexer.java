import java.io.*;
import java.util.*;

public class lexer {
    static String inputName = "";
    static String inputStatus = "  ";
    static int inputLength = 0;
    static String outputName = "                                        ";
    static String outputStatus = "  ";
    static String outputRecord = "                                                                ";
    static int lineNo = 0;
    static int colNo = 0;
    static int colNoMax = 0;
    static int colIncrement = 1;
    static int startCol = 0;
    static int outx = 0;
    static int outLim = 48;
    static String outputLine = "    0       0                                                                                                                               ";
    static int outLine = 0;
    static int outColumn = 0;
    static String messageArea = "   ";
    static String token = "                ";
    static String outValue = "                                               ";
    static int outInteger = 0;
    static int outInteger1 = 0;
    static int errorLine = 0;
    static int errorCol = 0;
    static String errorMessage = "                                                                    ";
    static String scanState = "                ";
    static char currentCharacter = ' ';
    static char previousCharacter = ' ';
    static BufferedReader inputFile;
    static String inputRecord = "                                                                                              ";
    static final int EOF = -1;

    public static void main(String[] args) {
        if (args.length > 0) {
            inputName = args[0];
        }
        startLexer();
    }

    static void startLexer() {
        if (!inputName.equals(" ")) {
            try {
                inputFile = new BufferedReader(new FileReader(inputName));
                inputStatus = "00";
            } catch (FileNotFoundException e) {
                errorMessage = "in lexer " + inputName + " not found";
                reportError();
            }
        }
        readInputFile();
        while (inputStatus.equals("00")) {
            lineNo++;
            outLine = lineNo;
            colNoMax = inputRecord.trim().length();
            colNo = 1;
            previousCharacter = ' ';
            while (colNo <= colNoMax) {
                outColumn = colNo;
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
                                outInteger = Integer.parseInt(outValue);
                                token = "Integer";
                            }
                        } else if ((currentCharacter >= 'A' && currentCharacter <= 'Z') ||
                                (currentCharacter >= 'a' && currentCharacter <= 'z')) {
                            errorMessage = "in lexer invalid integer";
                            reportError();
                        } else {
                            if (outx > 5) {
                                outInteger1 = Integer.parseInt(outValue);
                            } else {
                                outInteger = Integer.parseInt(outValue);
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
                        switch (currentCharacter) {
                            case '"':
                                if (outx == 0) {
                                    errorMessage = "in lexer empty string";
                                    reportError();
                                } else {
                                    incrementOutx();
                                    outValue = outValue.substring(0, outx - 1) + currentCharacter + outValue.substring(outx);
                                    token = "String";
                                }
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
                        switch (currentCharacter) {
                            case '\'':
                                if (outx == 0) {
                                    errorMessage = "in lexer empty character constant";
                                    reportError();
                                } else if (outx == 1) {
                                    outInteger = (int) outValue.charAt(0);
                                    token = "Integer";
                                } else if (outx == 2) {
                                    if (outValue.substring(0, 2).equals("\\n")) {
                                        outInteger = 10;
                                    } else if (outValue.substring(0, 2).equals("\\\\")) {
                                        outInteger = (int)'\\';
                                    } else {
                                        errorMessage = "in lexer unknown escape sequence " + outValue.substring(0, 2);
                                        reportError();
                                    }
                                    token = "Integer";
                                } else {
                                    errorMessage = "in lexer multicharacter constant";
                                    reportError();
                                }
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
                        switch (previousCharacter) {
                            case '/':
                                if (currentCharacter == '*') {
                                    scanState = "comment";
                                    startCol = colNo - 1;
                                } else {
                                    token = "Op_divide";
                                    colIncrement = 0;
                                }
                                break;
                            case '=':
                                if (currentCharacter == '=') {
                                    token = "Op_equal";
                                } else {
                                    token = "Op_assign";
                                    colIncrement = 0;
                                }
                                break;
                            case '<':
                                if (currentCharacter == '=') {
                                    token = "Op_lessequal";
                                } else {
                                    token = "Op_less";
                                    colIncrement = 0;
                                }
                                break;
                            case '>':
                                if (currentCharacter == '=') {
                                    token = "Op_greaterequal";
                                } else {
                                    token = "Op_greater";
                                    colIncrement = 0;
                                }
                                break;
                            case '!':
                                if (currentCharacter == '=') {
                                    token = "Op_notequal";
                                } else {
                                    token = "Op_not";
                                    colIncrement = 0;
                                }
                                break;
                            default:
                                errorMessage = "in lexer " + scanState + " unknown character \"" + currentCharacter +
                                        "\" with previous character \"" + previousCharacter + "\"";
                                reportError();
                        }
                        break;
                    default:
                        startCol = colNo;
                        switch (currentCharacter) {
                            case ' ':
                                break;
                            case 'A': case 'B': case 'C': case 'D': case 'E': case 'F': case 'G': case 'H': case 'I':
                            case 'J': case 'K': case 'L': case 'M': case 'N': case 'O': case 'P': case 'Q': case 'R':
                            case 'S': case 'T': case 'U': case 'V': case 'W': case 'X': case 'Y': case 'Z':
                            case 'a': case 'b': case 'c': case 'd': case 'e': case 'f': case 'g': case 'h': case 'i':
                            case 'j': case 'k': case 'l': case 'm': case 'n': case 'o': case 'p': case 'q': case 'r':
                            case 's': case 't': case 'u': case 'v': case 'w': case 'x': case 'y': case 'z':
                                scanState = "identifier";
                                outx = 1;
                                outValue = "" + currentCharacter + outValue.substring(1);
                                break;
                            case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
                                scanState = "integer";
                                outx = 1;
                                outValue = "" + currentCharacter + outValue.substring(1);
                                break;
                            case '&':
                                scanState = "and";
                                break;
                            case '|':
                                scanState = "or";
                                break;
                            case '"':
                                scanState = "quote";
                                outx = 1;