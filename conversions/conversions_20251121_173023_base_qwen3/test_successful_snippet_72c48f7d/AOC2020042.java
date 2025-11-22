import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class AOC2020042 {
    private static int correctPassports = 0;
    private static int validFields = 0;
    private static String[] wsRow = new String[8];
    private static String wsChar = "";
    private static String wsField = "";
    private static String wsEyeColor = "";
    private static int year = 0;
    private static int n1 = 0;
    private static int n2 = 0;
    private static int stringPtr = 1;
    private static int i = 1;
    private static int wsNum = 0;

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
        for (i = 0; i < 8; i++) {
            int spaceIndex = inputRecord.indexOf(' ', stringPtr - 1);
            if (spaceIndex == -1) {
                wsRow[i] = inputRecord.substring(stringPtr - 1);
                stringPtr = inputRecord.length() + 1;
            } else {
                wsRow[i] = inputRecord.substring(stringPtr - 1, spaceIndex);
                stringPtr = spaceIndex + 2;
            }
        }

        for (i = 0; i < 8; i++) {
            if (wsRow[i].length() > 0) {
                wsChar = wsRow[i].substring(0, 1);
                if (!wsChar.equals("c") && !wsChar.equals(" ")) {
                    validateField(wsRow[i]);
                }
            }
        }
    }

    private static void validateField(String fieldData) {
        wsField = fieldData.substring(0, 3);
        n1 = 0;
        n2 = 0;

        // byr (Birth Year) - four digits; at least 1920 and at most 2002.
        if (wsField.equals("byr")) {
            year = Integer.parseInt(fieldData.substring(4, 8));
            if (year >= 1920 && year <= 2002) {
                validFields++;
            }
        }

        // iyr (Issue Year) - four digits; at least 2010 and at most 2020.
        if (wsField.equals("iyr")) {
            year = Integer.parseInt(fieldData.substring(4, 8));
            if (year >= 2010 && year <= 2020) {
                validFields++;
            }
        }

        // eyr (Expiration Year) - 4 digits; at least 2020 and at most 2030
        if (wsField.equals("eyr")) {
            year = Integer.parseInt(fieldData.substring(4, 8));
            if (year >= 2020 && year <= 2030) {
                validFields++;
            }
        }

        // hgt (Height) - a number followed by either cm or in
        // - If cm, the number must be at least 150 and at most 193.
        // - If in, the number must be at least 59 and at most 76
        if (wsField.equals("hgt")) {
            String numStr = fieldData.substring(4);
            int numEnd = numStr.indexOf("cm");
            if (numEnd == -1) {
                numEnd = numStr.indexOf("in");
            }
            if (numEnd != -1) {
                wsNum = Integer.parseInt(numStr.substring(0, numEnd));
                if (numStr.contains("cm") && wsNum >= 150 && wsNum <= 193) {
                    validFields++;
                } else if (numStr.contains("in") && wsNum >= 59 && wsNum <= 76) {
                    validFields++;
                }
            }
        }

        // hcl (Hair Color) - # followed by exactly 6 characters 0-9 or a-f
        if (wsField.equals("hcl")) {
            if (fieldData.charAt(4) == '#' && fieldData.charAt(11) == ' ') {
                validFields++;
            }
        }

        // ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth
        if (wsField.equals("ecl")) {
            wsEyeColor = fieldData.substring(4, 7);
            if (Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(wsEyeColor)) {
                validFields++;
            }
        }

        // pid (Passport ID) - a 9-digit number, including leading zeroes.
        if (wsField.equals("pid")) {
            String pidStr = fieldData.substring(4, 13);
            if (pidStr.matches("\\d{9}") && fieldData.charAt(13) == ' ') {
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