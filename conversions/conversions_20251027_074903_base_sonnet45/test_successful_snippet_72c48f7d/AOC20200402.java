import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC20200402 {
    private int correctPassports = 0;
    private int validFields = 0;

    public static void main(String[] args) {
        AOC20200402 program = new AOC20200402();
        program.run();
    }

    public void run() {
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

    private void processRecord(String inputRecord) {
        if (inputRecord.trim().isEmpty()) {
            nextPassport();
        } else {
            processRow(inputRecord);
        }
    }

    private void nextPassport() {
        if (validFields == 7) {
            correctPassports++;
        }
        validFields = 0;
    }

    private void processRow(String inputRecord) {
        String[] tokens = inputRecord.split("\\s+");
        
        for (String token : tokens) {
            if (token.length() > 0 && token.charAt(0) != 'c' && token.charAt(0) != ' ') {
                validateField(token);
            }
        }
    }

    private void validateField(String wsRow) {
        if (wsRow.length() < 4) return;
        
        String wsField = wsRow.substring(0, Math.min(3, wsRow.length()));
        
        if (wsField.equals("byr")) {
            if (wsRow.length() >= 8) {
                try {
                    int year = Integer.parseInt(wsRow.substring(4, 8));
                    if (year >= 1920 && year <= 2002) {
                        validFields++;
                    }
                } catch (NumberFormatException e) {
                }
            }
        }

        if (wsField.equals("iyr")) {
            if (wsRow.length() >= 8) {
                try {
                    int year = Integer.parseInt(wsRow.substring(4, 8));
                    if (year >= 2010 && year <= 2020) {
                        validFields++;
                    }
                } catch (NumberFormatException e) {
                }
            }
        }

        if (wsField.equals("eyr")) {
            if (wsRow.length() >= 8) {
                try {
                    int year = Integer.parseInt(wsRow.substring(4, 8));
                    if (year >= 2020 && year <= 2030) {
                        validFields++;
                    }
                } catch (NumberFormatException e) {
                }
            }
        }

        if (wsField.equals("hgt")) {
            if (wsRow.length() >= 7) {
                String heightStr = wsRow.substring(4);
                if (heightStr.contains("cm")) {
                    try {
                        int height = Integer.parseInt(heightStr.replace("cm", ""));
                        if (height >= 150 && height <= 193) {
                            validFields++;
                        }
                    } catch (NumberFormatException e) {
                    }
                } else if (heightStr.contains("in")) {
                    try {
                        int height = Integer.parseInt(heightStr.replace("in", ""));
                        if (height >= 59 && height <= 76) {
                            validFields++;
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            }
        }

        if (wsField.equals("hcl")) {
            if (wsRow.length() >= 11) {
                if (wsRow.charAt(4) == '#' && (wsRow.length() == 11 || wsRow.charAt(11) == ' ')) {
                    validFields++;
                }
            }
        }

        if (wsField.equals("ecl")) {
            if (wsRow.length() >= 7) {
                String eyeColor = wsRow.substring(4, 7);
                if (eyeColor.equals("amb") || eyeColor.equals("blu") || 
                    eyeColor.equals("brn") || eyeColor.equals("gry") || 
                    eyeColor.equals("grn") || eyeColor.equals("hzl") || 
                    eyeColor.equals("oth")) {
                    validFields++;
                }
            }
        }

        if (wsField.equals("pid")) {
            if (wsRow.length() >= 13) {
                try {
                    String pidStr = wsRow.substring(4, 13);
                    int pid = Integer.parseInt(pidStr);
                    if ((wsRow.length() == 13 || wsRow.charAt(13) == ' ') && 
                        pidStr.matches("\\d{9}")) {
                        validFields++;
                    }
                } catch (NumberFormatException e) {
                }
            }
        }
    }
}