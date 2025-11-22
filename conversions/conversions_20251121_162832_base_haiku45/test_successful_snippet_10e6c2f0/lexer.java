import java.io.*;
import java.util.*;

public class lexer {
    private String inputName = "";
    private String inputStatus = "";
    private int inputLength = 0;
    
    private String outputName = "";
    private String outputStatus = "";
    private String outputRecord = "";
    
    private int lineNo = 0;
    private int colNo = 0;
    private int colNoMax = 0;
    private int colIncrement = 1;
    private int startCol = 0;
    private int outx = 0;
    private int outLim = 48;
    
    private int outLine = 0;
    private int outColumn = 0;
    private String token = "";
    private String outValue = "";
    private int outInteger = 0;
    private int outInteger1 = 0;
    
    private int errorLine = 0;
    private int errorCol = 0;
    private String errorMessage = "";
    
    private String scanState = "";
    private char currentCharacter = ' ';
    private char previousCharacter = ' ';
    
    private BufferedReader reader = null;
    private String inputRecord = "";
    
    public static void main(String[] args) {
        lexer lex = new lexer();
        String input = args.length > 0 ? args[0] : "";
        lex.startLexer(input);
    }
    
    public void startLexer(String input) {
        inputName = input;
        
        if (!inputName.isEmpty() && !inputName.trim().isEmpty()) {
            try {
                reader = new BufferedReader(new FileReader(inputName));
                inputStatus = "00";
            } catch (FileNotFoundException e) {
                errorMessage = "in lexer " + inputName + " not found";
                reportError();
                return;
            }
        }
        
        readInputFile();
        
        while (inputStatus.equals("00")) {
            lineNo++;
            outLine = lineNo;
            colNoMax = inputRecord.length();
            colNo = 0;
            previousCharacter = ' ';
            
            while (colNo < colNoMax) {
                outColumn = colNo + 1;
                currentCharacter = inputRecord.charAt(colNo);
                colIncrement = 1;
                
                switch (scanState) {
                    case "identifier":
                        if (isAlpha(currentCharacter) || isDigit(currentCharacter) || currentCharacter == '_') {
                            incrementOutx();
                            outValue = outValue.substring(0, outx - 1) + currentCharacter + outValue.substring(outx);
                            if (colNo == colNoMax - 1) {
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
                        if (isDigit(currentCharacter)) {
                            incrementOutx();
                            outValue = outValue.substring(0, outx - 1) + currentCharacter + outValue.substring(outx);
                            if (colNo == colNoMax - 1) {
                                outInteger = Integer.parseInt(outValue.trim());
                                token = "Integer";
                            }
                        } else if (isAlpha(currentCharacter)) {
                            errorMessage = "in lexer invalid integer";
                            reportError();
                        } else {
                            if (outx > 5) {
                                outInteger1 = Integer.parseInt(outValue.trim());
                            } else {
                                outInteger = Integer.parseInt(outValue.trim());
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
                        if (currentCharacter == '"' && outx == 0) {
                            errorMessage = "in lexer empty string";
                            reportError();
                        } else if (currentCharacter == '"') {
                            incrementOutx();
                            outValue = outValue.substring(0, outx - 1) + currentCharacter + outValue.substring(outx);
                            token = "String";
                        } else {
                            if (colNo == colNoMax - 1) {
                                errorMessage = "in lexer missing close quote";
                                reportError();
                            } else {
                                incrementOutx();
                                outValue = outValue.substring(0, outx - 1) + currentCharacter + outValue.substring(outx);
                            }
                        }
                        break;
                        
                    case "character":
                        if (currentCharacter == '\'' && outx == 0) {
                            errorMessage = "in lexer empty character constant";
                            reportError();
                        } else if (currentCharacter == '\'' && outx == 1) {
                            outInteger = (int)outValue.charAt(0);
                            token = "Integer";
                        } else if (currentCharacter == '\'' && outx == 2) {
                            if (outValue.substring(0, 2).equals("\\n")) {
                                outInteger = 10;
                            } else if (outValue.substring(0, 2).equals("\\\\")) {
                                outInteger = (int)'\\';
                            } else {
                                errorMessage = "in lexer unknown escape sequence " + outValue.substring(0, 2);
                                reportError();
                            }
                            token = "Integer";
                        } else if (currentCharacter == '\'' && outx > 2) {
                            errorMessage = "in lexer multicharacter constant";
                            reportError();
                        } else {
                            if (colNo == colNoMax - 1) {
                                errorMessage = "in lexer missing close quote";
                                reportError();
                            }
                            incrementOutx();
                            outValue = outValue.substring(0, outx - 1) + currentCharacter + outValue.substring(outx);
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
                        if (previousCharacter == '/' && currentCharacter == '*') {
                            scanState = "comment";
                            startCol = colNo;
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
                        } else if (previousCharacter == '!'