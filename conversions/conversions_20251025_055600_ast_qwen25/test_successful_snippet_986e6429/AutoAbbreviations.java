import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AutoAbbreviations {
    public static void main(String[] args) {
        String dowFileName = "days-of-week.txt";
        String dowLine = new String(new char[200]);
        int endo = 0;
        int endo2 = 0;
        String curDay = new String(new char[50]);
        int abPtr = 0;
        int lineNum = 1;
        int charAmt = 1;
        int largestCharAmt = 0;
        int dayNum = 1;
        int abRestart = 0;
        String curAbbr = new String(new char[50]);
        int tmp1 = 0;
        int tmp2 = 0;
        int tIndex = 1;
        String[] abbrList = new String[7];
        for (int i = 0; i < 7; i++) {
            abbrList[i] = new String(new char[50]);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(dowFileName))) {
            while (endo == 0) {
                dowLine = br.readLine();
                if (dowLine == null) {
                    endo = 1;
                } else {
                    if (dowLine.trim().isEmpty()) {
                        System.out.println();
                    } else {
                        endo2 = 0;
                        charAmt = 0;

                        while (endo2 == 0) {
                            abPtr = 1;
                            dayNum = 1;
                            abRestart = 0;

                            charAmt++;
                            for (tIndex = 0; tIndex < 7; tIndex++) {
                                abbrList[tIndex] = "";
                            }

                            for (tIndex = 0; tIndex < 7; tIndex++) {
                                int spaceIndex = dowLine.indexOf(' ', abPtr);
                                if (spaceIndex == -1) {
                                    curDay = dowLine.substring(abPtr - 1).trim();
                                    abPtr = dowLine.length() + 1;
                                } else {
                                    curDay = dowLine.substring(abPtr - 1, spaceIndex).trim();
                                    abPtr = spaceIndex + 1;
                                }

                                tmp1 = curDay.length();
                                tmp2 = curDay.replaceAll("\\S", "").length();
                                tmp1 -= tmp2;
                                if (tmp1 > largestCharAmt) {
                                    largestCharAmt = tmp1;
                                }

                                if (curDay.isEmpty()) {
                                    endo2 = 3;
                                }

                                curAbbr = curDay.substring(0, charAmt);

                                for (int i = 0; i < 7; i++) {
                                    if (abbrList[i].equals(curAbbr)) {
                                        abRestart = 1;
                                    }
                                }

                                abbrList[dayNum - 1] = curAbbr;

                                dayNum++;
                            }

                            if (abRestart == 0) {
                                endo2 = 1;
                            }

                            if (charAmt > largestCharAmt) {
                                endo2 = 2;
                            }
                        }

                        System.out.print("Line " + lineNum + ": ");

                        if (endo2 == 3) {
                            System.out.print("Error: not enough days");
                        } else if (endo2 == 2) {
                            System.out.print("Error: identical days");
                        } else {
                            System.out.print(charAmt + ": ");

                            for (tIndex = 0; tIndex < 7; tIndex++) {
                                curAbbr = abbrList[tIndex];

                                tmp1 = curAbbr.length();
                                tmp2 = curAbbr.replaceAll("\\S", "").length();
                                tmp1 -= tmp2;

                                System.out.print(curAbbr.substring(0, tmp1) + ".");
                                if (tIndex < 6) {
                                    System.out.print(" ");
                                } else {
                                    System.out.print("\n");
                                }
                            }
                        }
                    }
                }
                lineNum++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}