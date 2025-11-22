import java.io.*;
import java.nio.file.*;
import java.util.*;

public class lexer {
    private static String inputName;
    private static String inputStatus;
    private static BufferedReader inputFile;
    private static String inputRecord;
    private static int inputLength;
    
    private static int lineNo = 0;
    private static int colNo;
    private static int colNoMax;
    private static int colIncrement = 1;
    private static int startCol;
    private static int outx;
    private static int outLim = 48;
    
    private static int outLine;
    private static int outColumn;
    private static String token;
    private static String outValue;
    
    private static String scanState = "";
    private static char currentCharacter;
    private static char previousCharacter;
    
    public static void main(String[] args) {
        if (args.length > 0) {
            inputName = args[0];
        } else {
            inputName = "";
        }
        startLexer();
    }
    
    private static void startLexer() {
        if (!inputName.trim().isEmpty()) {
            try {
                inputFile = new BufferedReader(new FileReader(inputName));
                inputStatus = "00";
            } catch (FileNotFoundException e) {
                reportError("in lexer " + inputName.trim() + " not found");
            }
        }
        
        readInputFile();
        
        while (inputStatus.equals("00")) {
            lineNo++;
            outLine = lineNo;
            colNoMax = inputRecord.replaceAll("\\s+$", "").length();
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
                            setOutValueChar(outx, currentCharacter);
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
                            setOutValueChar(outx, currentCharacter);
                            if (colNo == colNoMax) {
                                token = "Integer";
                            }
                        } else if ((currentCharacter >= 'A' && currentCharacter <= 'Z') ||
                                   (currentCharacter >= 'a' && currentCharacter <= 'z')) {
                            reportError("in lexer invalid integer");
                        } else {
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
                        if (currentCharacter == '"' && outx == 0) {
                            reportError("in lexer empty string");
                        } else if (currentCharacter == '"') {
                            incrementOutx();
                            setOutValueChar(outx, currentCharacter);
                            token = "String";
                        } else {
                            if (colNo == colNoMax) {
                                reportError("in lexer missing close quote");
                            } else {
                                incrementOutx();
                                setOutValueChar(outx, currentCharacter);
                            }
                        }
                        break;
                        
                    case "character":
                        if (currentCharacter == '\'' && outx == 0) {
                            reportError("in lexer empty character constant");
                        } else if (currentCharacter == '\'' && outx == 1) {
                            token = "Integer";
                        } else if (currentCharacter == '\'' && outx == 2) {
                            String escSeq = outValue.substring(0, 2);
                            if (escSeq.equals("\\n")) {
                                outValue = "10";
                            } else if (escSeq.equals("\\\\")) {
                                outValue = String.valueOf((int)'\\' - 1);
                            } else {
                                reportError("in lexer unknown escape sequence " + escSeq);
                            }
                            token = "Integer";
                        } else if (currentCharacter == '\'') {
                            reportError("in lexer multicharacter constant");
                        } else {
                            if (colNo == colNoMax) {
                                reportError("in lexer missing close quote");
                            }
                            incrementOutx();
                            setOutValueChar(outx, currentCharacter);
                        }
                        break;
                        
                    case "and":
                        if (previousCharacter == '&' && currentCharacter == '&') {
                            token = "Op_and";
                        } else {
                            reportError("in lexer AND error");
                        }
                        break;
                        
                    case "or":
                        if (previousCharacter == '|' && currentCharacter == '|') {
                            token = "Op_or";
                        } else {
                            reportError("in lexer OR error");
                        }
                        break;
                        
                    case "ambiguous":
                        if (previousCharacter == '/' && currentCharacter == '*') {
                            scanState = "comment";
                            startCol = colNo - 1;
                        } else if (previousCharacter == '/') {
                            token = "Op_divide";
                            colIncrement = 0;
                        } else if (previousCharacter == '=' && currentCharacter == '=') {
                            token = "Op_equal";
                        } else if (previousCharacter == '=') {
                            token = "Op_assign";
                            colIncrement = 0;
                        } else if (previousCharacter == '<' && currentCharacter == '=') {
                            token = "Op_lessequal";
                        } else if (previousCharacter == '<') {
                            token = "Op_less";
                            colIncrement = 0;
                        } else if (previousCharacter == '>' && currentCharacter == '=') {
                            token = "Op_greaterequal";
                        } else if (previousCharacter == '>') {
                            token = "Op_greater";
                            colIncrement = 0;
                        } else if (previousCharacter == '!' && currentCharacter == '=') {
                            token = "Op_notequal";
                        } else if (previousCharacter == '!') {
                            token = "Op_not";
                            colIncrement = 0;
                        } else {
                            reportError("in lexer " + scanState.trim() + 
                                      " unknown character \"" + currentCharacter + 
                                      "\" with previous character \"" + previousCharacter + "\"");
                        }
                        break;
                        
                    default:
                        startCol = colNo;
                        if (currentCharacter == ' ') {
                            // continue
                        } else if ((currentCharacter >= 'A' && currentCharacter <= 'Z') ||
                                   (currentCharacter >= 'a' && currentCharacter <= 'z')) {
                            scanState = "identifier";
                            outx = 1;
                            outValue = String.valueOf(currentCharacter);
                        } else if (currentCharacter >= '0' && currentCharacter <= '9') {
                            scan