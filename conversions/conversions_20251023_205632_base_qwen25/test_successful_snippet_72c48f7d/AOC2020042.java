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

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d4.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    nextPassport();
                } else {
                    processRecord(line);
                }
            }
            nextPassport();
            System.out.println(correctPassports);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processRecord(String inputRecord) {
        if (inputRecord.length() == 0) {
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
        stringPtr = 1;
        String[] wsRow = new String[8];
        for (i = 0; i < 8; i++) {
            int spaceIndex = inputRecord.indexOf(' ', stringPtr);
            if (spaceIndex == -1) {
                wsRow[i] = inputRecord.substring(stringPtr);
                break;
            }
            wsRow[i] = inputRecord.substring(stringPtr, spaceIndex);
            stringPtr = spaceIndex + 1;
        }

        for (i = 0; i < 8 && wsRow[i] != null; i++) {
            String wsChar = wsRow[i].substring(0, 1);
            if (!wsChar.equals("c") && !wsChar.equals(" ")) {
                validateField(wsRow[i]);
            }
        }
    }

    private static void validateField(String field) {
        String wsField = field.substring(0, 3);
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
            String wsNum = field.substring(4, 10);
            n1 = countOccurrences(wsNum, "cm");
            n2 = countOccurrences(wsNum, "in");
            if (n1 == 1) {
                int height = Integer.parseInt(wsNum.substring(0, wsNum.indexOf("cm")));
                if (height >= 150 && height <= 193) {
                    validFields++;
                }
            }
            if (n2 == 1) {
                int height = Integer.parseInt(wsNum.substring(0, wsNum.indexOf("in")));
                if (height >= 59 && height <= 76) {
                    validFields++;
                }
            }
        }

        if (wsField.equals("hcl")) {
            if (field.charAt(4) == '#' && field.charAt(10) == ' ') {
                validFields++;
            }
        }

        if (wsField.equals("ecl")) {
            String wsEyeColor = field.substring(4, 7);
            if (wsEyeColor.equals("amb") || wsEyeColor.equals("blu") || wsEyeColor.equals("brn") || wsEyeColor.equals("gry") || wsEyeColor.equals("grn") || wsEyeColor.equals("hzl") || wsEyeColor.equals("oth")) {
                validFields++;
            }
        }

        if (wsField.equals("pid")) {
            String wsNum = field.substring(4, 13);
            int num = Integer.parseInt(wsNum);
            if (field.charAt(13) == ' ' && wsNum.equals(String.valueOf(num)) && num == Integer.parseInt(wsNum)) {
                validFields++;
            }
        }
    }

    private static int countOccurrences(String str, String sub) {
        int count = 0;
        int index = 0;
        while ((index = str.indexOf(sub, index)) != -1) {
            count++;
            index += sub.length();
        }
        return count;
    }
}