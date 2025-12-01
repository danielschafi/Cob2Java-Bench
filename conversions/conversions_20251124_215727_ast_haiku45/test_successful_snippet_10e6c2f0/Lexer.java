import java.io.*;
import java.util.*;

public class Lexer {
    private String inputName;
    private String inputStatus;
    private int inputLength;
    private String outputName;
    private String outputStatus;
    private String outputRecord;
    private int lineNo;
    private int colNo;
    private int colNoMax;
    private int colIncrement;
    private int startCol;
    private int outx;
    private static final int OUT_LIM = 48;
    private int outLine;
    private int outColumn;
    private String token;
    private String outValue;
    private int outInteger;
    private int outInteger1;
    private int errorLine;
    private int errorCol;
    private String errorMessage;
    private String scanState;
    private char currentCharacter;
    private char previousCharacter;
    private BufferedReader reader;
    private String inputRecord;

    public Lexer(String inputName) {
        this.inputName = inputName != null ? inputName : "";
        this.inputStatus = "";
        this.lineNo = 0;
        this.colNo = 0;
        this.colNoMax = 0;
        this.colIncrement = 1;
        this.startCol = 0;
        this.outx = 0;
        this.token = "";
        this.outValue = "";
        this.scanState = "";
        this.currentCharacter = ' ';
        this.previousCharacter = ' ';
        this.errorMessage = "";
        this.inputRecord = "";
    }

    public static void main(String[] args) {
        String inputName = args.length > 0 ? args[0] : "";
        Lexer lexer = new Lexer(inputName);
        lexer.startLexer();
    }

    private void startLexer() {
        if (!inputName.isEmpty()) {
            try {
                reader = new BufferedReader(new FileReader(inputName));
                inputStatus = "00";
            } catch (FileNotFoundException e) {
                inputStatus = "35";
                errorMessage = "in lexer " + inputName + " not found";
                reportError();
                return;
            }
        } else {
            reader = new BufferedReader(new InputStreamReader(System.in));
            inputStatus = "00";
        }

        readInputFile();
        while (!inputStatus.equals("00")) {
            lineNo++;
            outLine = lineNo;
            colNoMax = inputRecord.trim().length();
            colNo = 1;
            previousCharacter = ' ';

            while (colNo <= colNoMax) {
                outColumn = colNo;
                currentCharacter = inputRecord.charAt(colNo - 1);
                colIncrement = 1;

                switch (scanState) {
                    case "identifier":
                        if (isIdentifierChar(currentCharacter)) {
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
                                outInteger = Integer.parseInt(outValue.trim());
                                token = "Integer";
                            }
                        } else if ((currentCharacter >= 'A' && currentCharacter <= 'Z') ||
                                   (currentCharacter >= 'a' && currentCharacter <= 'z')) {
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
                            if (colNo == colNoMax) {
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
                            outInteger = (int) outValue.charAt(0) - 1;
                            token = "Integer";
                        } else if (currentCharacter == '\'' && outx == 2) {
                            if (outValue.substring(0, 2).equals("\\n")) {
                                outInteger = 10;
                            } else if (outValue.substring(0, 2).equals("\\\\")) {
                                outInteger = (int) '\\' - 1;
                            } else {
                                errorMessage = "in lexer unknown escape sequence " + outValue.substring(0, 2);
                                reportError();
                            }
                            token = "Integer";
                        } else if (currentCharacter == '\'') {
                            errorMessage = "in lexer multicharacter constant";
                            reportError();
                        } else {
                            if (colNo == colNoMax) {
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
                        handleAmbiguous();
                        break;

                    default:
                        startCol = colNo;
                        switch (currentCharacter) {
                            case ' ':
                                break;
                            case 'A': case 'B': case 'C': case 'D': case 'E': case 'F': case 'G': case 'H':
                            case 'I': case 'J': case 'K': case 'L': case 'M': case 'N': case 'O': case 'P':
                            case 'Q': case 'R': case 'S': case 'T': case 'U': case 'V': case 'W': case 'X':
                            case 'Y': case 'Z':