import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AOC2020042 {
    private static int correctPassports = 0;
    private static int validFields = 0;
    private static int stringPtr = 1;
    private static int i = 1;
    private static int year = 0;
    private static int n1 = 0;
    private static int n2 = 0;
    private static String[] wsRow = new String[8];
    private static String wsChar;
    private static String wsField;
    private static String wsEyeColor;
    private static int wsNum;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d4.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    nextPassport();
                } else {
                    processRow(line);
                }
            }
            nextPassport();
            System.out.println(correctPassports);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processRow(String inputRecord) {
        stringPtr = 1;
        String[] rows = inputRecord.trim().split("\\s+");
        for (int j = 0; j < Math.min(rows.length, 8); j++) {
            wsRow[j] = rows[j];
        }

        for (int j = 0; j < 8; j++) {
            if (wsRow[j] != null && !wsRow[j].isEmpty()) {
                wsChar = wsRow[j].substring(0, 1);
                if (!wsChar.equals("c") && !wsChar.equals(" ")) {
                    validateField(j);
                }
            }
        }
    }

    private static void validateField(int index) {
        wsField = wsRow[index].substring(0, 3);
        n1 = 0;
        n2 = 0;

        // byr (Birth Year) - four digits; at least 1920 and at most 2002.
        if (wsField.equals("byr")) {
            year = Integer.parseInt(wsRow[index].substring(4, 8));
            if (year >= 1920 && year <= 2002) {
                validFields++;
            }
        }

        // iyr (Issue Year) - four digits; at least 2010 and at most 2020.
        if (wsField.equals("iyr")) {
            year = Integer.parseInt(wsRow[index].substring(4, 8));
            if (year >= 2010 && year <= 2020) {
                validFields++;
            }
        }

        // eyr (Expiration Year) - 4 digits; at least 2020 and at most 2030
        if (wsField.equals("eyr")) {
            year = Integer.parseInt(wsRow[index].substring(4, 8));
            if (year >= 2020 && year <= 2030) {
                validFields++;
            }
        }

        // hgt (Height) - a number followed by either cm or in
        // - If cm, the number must be at least 150 and at most 193.
        // - If in, the number must be at least 59 and at most 76
        if (wsField.equals("hgt")) {
            String numStr = wsRow[index].substring(4, 14).trim();
            wsNum = Integer.parseInt(numStr);
            if (numStr.endsWith("cm") && wsNum >= 150 && wsNum <= 193) {
                validFields++;
            }
            if (numStr.endsWith("in") && wsNum >= 59 && wsNum <= 76) {
                validFields++;
            }
        }

        // hcl (Hair Color) - # followed by exactly 6 characters 0-9 or a-f
        if (wsField.equals("hcl")) {
            if (wsRow[index].charAt(4) == '#' && wsRow[index].charAt(11) == ' ') {
                validFields++;
            }
        }

        // ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth
        if (wsField.equals("ecl")) {
            wsEyeColor = wsRow[index].substring(4, 7);
            if (wsEyeColor.equals("amb") || wsEyeColor.equals("blu") ||
                wsEyeColor.equals("brn") || wsEyeColor.equals("gry") ||
                wsEyeColor.equals("grn") || wsEyeColor.equals("hzl") ||
                wsEyeColor.equals("oth")) {
                validFields++;
            }
        }

        // pid (Passport ID) - a 9-digit number, including leading zeroes.
        if (wsField.equals("pid")) {
            String pidStr = wsRow[index].substring(4, 13);
            wsNum = Integer.parseInt(pidStr);
            if (wsRow[index].charAt(13) == ' ' && wsRow[index].substring(4, 13).equals(pidStr) && wsNum == Integer.parseInt(pidStr)) {
                validFields++;
            }
        }
    }

    private static void nextPassport() {
        if (validFields == 7) {
            correctPassports++;
        }
        validFields = 0;
    }
}