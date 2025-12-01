import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AOC202004_2 {
    private static int fileStatus = 0;
    private static int recLen = 0;
    private static String[] wsRow = new String[8];
    private static String wsChar = "";
    private static String wsField = "";
    private static String wsEyeColor = "";
    private static long wsNum = 0;
    
    private static long correctPassports = 0;
    private static long validFields = 0;
    private static int stringPtr = 1;
    private static int i = 1;
    private static long year = 0;
    private static long n1 = 0;
    private static long n2 = 0;
    
    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("d4.input"));
            for (String line : lines) {
                processRecord(line);
            }
            nextPassport();
            System.out.println(correctPassports);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void processRecord(String inputRecord) {
        recLen = inputRecord.length();
        if (recLen == 0) {
            nextPassport();
        } else {
            processRow(inputRecord);
        }
    }
    
    private static void nextPassport() {
        if (validFields == 7) {
            correctPassports++;
        }
        validFields = 0;
    }
    
    private static void processRow(String inputRecord) {
        stringPtr = 0;
        wsRow = inputRecord.split(" ");
        
        for (i = 0; i < wsRow.length && i < 8; i++) {
            if (wsRow[i].length() > 0) {
                wsChar = wsRow[i].substring(0, 1);
                if (!wsChar.equals("c") && !wsChar.equals(" ")) {
                    validateField(wsRow[i]);
                }
            }
        }
    }
    
    private static void validateField(String field) {
        if (field.length() < 3) return;
        
        wsField = field.substring(0, 3);
        n1 = 0;
        n2 = 0;
        
        if (wsField.equals("byr")) {
            if (field.length() >= 5) {
                try {
                    year = Long.parseLong(field.substring(4, 8));
                    if (year >= 1920 && year <= 2002) {
                        validFields++;
                    }
                } catch (Exception e) {
                }
            }
        }
        
        if (wsField.equals("iyr")) {
            if (field.length() >= 5) {
                try {
                    year = Long.parseLong(field.substring(4, 8));
                    if (year >= 2010 && year <= 2020) {
                        validFields++;
                    }
                } catch (Exception e) {
                }
            }
        }
        
        if (wsField.equals("eyr")) {
            if (field.length() >= 5) {
                try {
                    year = Long.parseLong(field.substring(4, 8));
                    if (year >= 2020 && year <= 2030) {
                        validFields++;
                    }
                } catch (Exception e) {
                }
            }
        }
        
        if (wsField.equals("hgt")) {
            if (field.length() >= 5) {
                String heightPart = field.substring(4);
                try {
                    String numStr = heightPart.replaceAll("[^0-9]", "");
                    wsNum = Long.parseLong(numStr);
                    n1 = countOccurrences(heightPart, "cm");
                    n2 = countOccurrences(heightPart, "in");
                    
                    if (n1 == 1 && wsNum >= 150 && wsNum <= 193) {
                        validFields++;
                    }
                    if (n2 == 1 && wsNum >= 59 && wsNum <= 76) {
                        validFields++;
                    }
                } catch (Exception e) {
                }
            }
        }
        
        if (wsField.equals("hcl")) {
            if (field.length() >= 12) {
                if (field.substring(4, 5).equals("#") && field.substring(11, 12).equals(" ")) {
                    validFields++;
                }
            }
        }
        
        if (wsField.equals("ecl")) {
            if (field.length() >= 7) {
                wsEyeColor = field.substring(4, 7);
                if (wsEyeColor.equals("amb") || wsEyeColor.equals("blu") ||
                    wsEyeColor.equals("brn") || wsEyeColor.equals("gry") ||
                    wsEyeColor.equals("grn") || wsEyeColor.equals("hzl") ||
                    wsEyeColor.equals("oth")) {
                    validFields++;
                }
            }
        }
        
        if (wsField.equals("pid")) {
            if (field.length() >= 13) {
                try {
                    String pidStr = field.substring(4, 13);
                    wsNum = Long.parseLong(pidStr);
                    n1 = Long.parseLong(pidStr);
                    
                    if (field.length() > 13 && field.substring(13, 14).equals(" ") &&
                        field.substring(4, 13).equals(String.valueOf(wsNum)) &&
                        wsNum == n1) {
                        validFields++;
                    } else if (field.length() == 13 &&
                               field.substring(4, 13).equals(String.valueOf(wsNum)) &&
                               wsNum == n1) {
                        validFields++;
                    }
                } catch (Exception e) {
                }
            }
        }
    }
    
    private static long countOccurrences(String str, String substr) {
        long count = 0;
        int index = 0;
        while ((index = str.indexOf(substr, index)) != -1) {
            count++;
            index += substr.length();
        }
        return count;
    }
}