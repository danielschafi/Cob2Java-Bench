import java.io.*;
import java.util.*;

public class AutoAbbreviations {
    static final int MAX_DAYS = 7;
    static final int MAX_CHARS = 50;
    static final String[] abbrList = new String[MAX_DAYS];
    static int endo = 0;
    static int endo2 = 0;
    static String curDay = "";
    static int abptr = 0;
    static int lineNum = 1;
    static int charAmt = 1;
    static int largestCharAmt = 0;
    static int dayNum = 1;
    static int abRestart = 0;
    static String curAbbr = "";
    static int tmp1 = 0;
    static int tmp2 = 0;
    static int tIndex = 1;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("days-of-week.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    System.out.println();
                } else {
                    endo2 = 0;
                    charAmt = 0;
                    largestCharAmt = 0;

                    while (endo2 == 0) {
                        abptr = 1;
                        dayNum = 1;
                        abRestart = 0;

                        charAmt++;

                        Arrays.fill(abbrList, "");

                        int i = 0;
                        for (i = 0; i < MAX_DAYS; i++) {
                            abbrList[i] = "";
                        }

                        i = 0;
                        while (i < MAX_DAYS) {
                            int start = abptr - 1;
                            int end = line.indexOf(' ', start);
                            if (end == -1) {
                                end = line.length();
                            }
                            curDay = line.substring(start, end);
                            abptr = end + 1;

                            tmp1 = curDay.length();
                            tmp2 = 0;
                            for (int j = 0; j < curDay.length(); j++) {
                                if (curDay.charAt(j) == ' ') {
                                    tmp2++;
                                }
                            }
                            tmp1 -= tmp2;
                            if (tmp1 > largestCharAmt) {
                                largestCharAmt = tmp1;
                            }

                            if (curDay.isEmpty()) {
                                endo2 = 3;
                                break;
                            }

                            if (charAmt <= curDay.length()) {
                                curAbbr = curDay.substring(0, charAmt);
                            } else {
                                curAbbr = curDay;
                            }

                            tIndex = 0;
                            while (tIndex < MAX_DAYS) {
                                if (abbrList[tIndex].equals(curAbbr)) {
                                    abRestart = 1;
                                    break;
                                }
                                tIndex++;
                            }

                            if (dayNum <= MAX_DAYS) {
                                abbrList[dayNum - 1] = curAbbr;
                            }

                            dayNum++;
                            i++;
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
                        System.out.print("Error: not enough ");
                        System.out.println("days");
                    } else if (endo2 == 2) {
                        System.out.print("Error: identical ");
                        System.out.println("days");
                    } else {
                        System.out.print(charAmt + ": ");
                        tIndex = 0;
                        while (tIndex < MAX_DAYS) {
                            curAbbr = abbrList[tIndex];

                            tmp1 = curAbbr.length();
                            tmp2 = 0;
                            for (int j = curAbbr.length() - 1; j >= 0; j--) {
                                if (curAbbr.charAt(j) == ' ') {
                                    tmp2++;
                                } else {
                                    break;
                                }
                            }
                            tmp1 -= tmp2;

                            System.out.print(curAbbr.substring(0, tmp1));
                            System.out.print(".");

                            if (tIndex < MAX_DAYS - 1) {
                                System.out.print(" ");
                            } else {
                                System.out.println();
                            }

                            tIndex++;
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