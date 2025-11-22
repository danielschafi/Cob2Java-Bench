import java.io.*;
import java.util.*;

public class AOC202004_2 {
    private static int correctPassports = 0;
    private static int validFields = 0;
    private static List<String> wsRow = new ArrayList<>();
    
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d4.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line);
            }
            nextPassport();
            System.out.println(correctPassports);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void processRecord(String line) {
        if (line.trim().isEmpty()) {
            nextPassport();
        } else {
            processRow(line);
        }
    }
    
    private static void nextPassport() {
        if (validFields == 7) {
            correctPassports++;
        }
        validFields = 0;
    }
    
    private static void processRow(String line) {
        wsRow.clear();
        String[] fields = line.split(" ");
        for (String field : fields) {
            wsRow.add(field);
        }
        
        for (String field : wsRow) {
            if (!field.isEmpty() && !field.startsWith("c")) {
                validateField(field);
            }
        }
    }
    
    private static void validateField(String field) {
        String fieldType = field.substring(0, 3);
        String fieldValue = field.substring(4);
        
        // byr (Birth Year) - four digits; at least 1920 and at most 2002.
        if (fieldType.equals("byr")) {
            try {
                int year = Integer.parseInt(fieldValue);
                if (year >= 1920 && year <= 2002) {
                    validFields++;
                }
            } catch (NumberFormatException e) {
                // Invalid format
            }
        }
        
        // iyr (Issue Year) - four digits; at least 2010 and at most 2020.
        if (fieldType.equals("iyr")) {
            try {
                int year = Integer.parseInt(fieldValue);
                if (year >= 2010 && year <= 2020) {
                    validFields++;
                }
            } catch (NumberFormatException e) {
                // Invalid format
            }
        }
        
        // eyr (Expiration Year) - 4 digits; at least 2020 and at most 2030
        if (fieldType.equals("eyr")) {
            try {
                int year = Integer.parseInt(fieldValue);
                if (year >= 2020 && year <= 2030) {
                    validFields++;
                }
            } catch (NumberFormatException e) {
                // Invalid format
            }
        }
        
        // hgt (Height) - a number followed by either cm or in
        if (fieldType.equals("hgt")) {
            if (fieldValue.endsWith("cm")) {
                try {
                    int height = Integer.parseInt(fieldValue.substring(0, fieldValue.length() - 2));
                    if (height >= 150 && height <= 193) {
                        validFields++;
                    }
                } catch (NumberFormatException e) {
                    // Invalid format
                }
            } else if (fieldValue.endsWith("in")) {
                try {
                    int height = Integer.parseInt(fieldValue.substring(0, fieldValue.length() - 2));
                    if (height >= 59 && height <= 76) {
                        validFields++;
                    }
                } catch (NumberFormatException e) {
                    // Invalid format
                }
            }
        }
        
        // hcl (Hair Color) - # followed by exactly 6 characters 0-9 or a-f
        if (fieldType.equals("hcl")) {
            if (fieldValue.length() == 7 && fieldValue.charAt(0) == '#') {
                String hexPart = fieldValue.substring(1);
                if (isValidHex(hexPart)) {
                    validFields++;
                }
            }
        }
        
        // ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth
        if (fieldType.equals("ecl")) {
            if (fieldValue.equals("amb") || fieldValue.equals("blu") ||
                fieldValue.equals("brn") || fieldValue.equals("gry") ||
                fieldValue.equals("grn") || fieldValue.equals("hzl") ||
                fieldValue.equals("oth")) {
                validFields++;
            }
        }
        
        // pid (Passport ID) - a 9-digit number, including leading zeroes.
        if (fieldType.equals("pid")) {
            if (fieldValue.length() == 9 && fieldValue.matches("\\d{9}")) {
                validFields++;
            }
        }
    }
    
    private static boolean isValidHex(String str) {
        if (str.length() != 6) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f'))) {
                return false;
            }
        }
        return true;
    }
}