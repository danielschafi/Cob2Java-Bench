import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020121 {
    public static void main(String[] args) {
        String inputFile = "d12.input";
        int fileStatus = 0;
        int recLen;
        char currDir = 'E';
        char dir = 'E';
        int dx = 1;
        int dy = 0;
        int x = 0;
        int y = 0;
        int n = 0;
        int arg = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String inputRecord;
            while ((inputRecord = br.readLine()) != null) {
                recLen = inputRecord.length();
                if (recLen >= 2) {
                    char inputAction = inputRecord.charAt(0);
                    arg = Integer.parseInt(inputRecord.substring(1));

                    if (inputAction == 'N' || inputAction == 'S' || inputAction == 'E' || inputAction == 'W') {
                        dir = inputAction;
                    } else if (inputAction == 'F') {
                        dir = currDir;
                    } else {
                        n = arg / 90;
                        if (inputAction == 'R') {
                            for (int i = 0; i < n; i++) {
                                switch (currDir) {
                                    case 'N':
                                        currDir = 'E';
                                        break;
                                    case 'E':
                                        currDir = 'S';
                                        break;
                                    case 'S':
                                        currDir = 'W';
                                        break;
                                    case 'W':
                                        currDir = 'N';
                                        break;
                                }
                            }
                        } else {
                            for (int i = 0; i < n; i++) {
                                switch (currDir) {
                                    case 'N':
                                        currDir = 'W';
                                        break;
                                    case 'W':
                                        currDir = 'S';
                                        break;
                                    case 'S':
                                        currDir = 'E';
                                        break;
                                    case 'E':
                                        currDir = 'N';
                                        break;
                                }
                            }
                        }
                        dir = currDir;
                    }

                    switch (dir) {
                        case 'N':
                            dx = -1;
                            dy = 0;
                            break;
                        case 'W':
                            dx = 0;
                            dy = -1;
                            break;
                        case 'S':
                            dx = 1;
                            dy = 0;
                            break;
                        case 'E':
                            dx = 0;
                            dy = 1;
                            break;
                    }

                    if (inputAction != 'L' && inputAction != 'R') {
                        x += dx * arg;
                        y += dy * arg;
                    }
                }
            }
        } catch (IOException e) {
            fileStatus = 1;
        }

        n = Math.abs(x) + Math.abs(y);
        System.out.println(n);
    }
}