import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
        try (BufferedReader br = new BufferedReader(new FileReader("d4.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                processRecord(line);
            }
            nextPassport();
            System.out.println(correctPassports);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processRecord(String line) {
        if (line.isEmpty()) {
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
        stringPtr = 1;
        String[] parts = line.split(" ");
        for (i = 0; i < parts.length && i < 8; i++) {
            wsRow[i] = parts[i];
        }

        for (i = 0; i < parts.length && i < 8; i++) {
            wsChar = wsRow[i].substring(0, 1);
            if (!wsChar.equals("c") && !wsChar.equals(" ")) {
                validateField(wsRow[i]);
            }
        }
    }

    private static void validateField(String field) {
        wsField = field.substring(0, 3);
        n1 = 0;
        n2 = 0;

        if (wsField.equals("byr")) {
            year = Integer.parseInt(field.substring(4, 8));
            if (year >= 1920 && year <= 2002) {
                validFields++;
            }
        }

        if (wsField.equals("iyr")) {
            year = Integer.parseInt(field.substring(4, 8));
            if (year >= 2010 && year <= 2020) {
                validFields++;
            }
        }

        if (wsField.equals("eyr")) {
            year = Integer.parseInt(field.substring(4, 8));
            if (year >= 2020 && year <= 2030) {
                validFields++;
            }
        }

        if (wsField.equals("hgt")) {
            wsNum = Integer.parseInt(field.substring(5, field.length() - 2));
            if (field.endsWith("cm") && wsNum >= 150 && wsNum <= 193) {
                validFields++;
            }
            if (field.endsWith("in") && wsNum >= 59 && wsNum <= 76) {
                validFields++;
            }
        }

        if (wsField.equals("hcl")) {
            if (field.charAt(4) == '#' && field.length() == 11) {
                validFields++;
            }
        }

        if (wsField.equals("ecl")) {
            wsEyeColor = field.substring(4, 7);
            if (wsEyeColor.equals("amb") || wsEyeColor.equals("blu") || wsEyeColor.equals("brn") || wsEyeColor.equals("gry") || wsEyeColor.equals("grn") || wsEyeColor.equals("hzl") || wsEyeColor.equals("oth")) {
                validFields++;
            }
        }

        if (wsField.equals("pid")) {
            wsNum = Integer.parseInt(field.substring(4, 13));
            if (field.length() == 13 && field.substring(4, 13).equals(String.valueOf(wsNum))) {
                validFields++;
            }
        }
    }
}