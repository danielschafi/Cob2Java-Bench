import java.io.*;
import java.util.*;

public class AutoAbbreviations {
    private String dowLine = "";
    private int endo = 0;
    private int endo2 = 0;
    private String curday = "";
    private int abptr = 0;
    private int lineNum = 1;
    private int charamt = 1;
    private int largestCharamt = 0;
    private int daynum = 1;
    private int abrestart = 0;
    private String curabbr = "";
    private int tmp1 = 0;
    private int tmp2 = 0;
    private int tindex = 1;
    private String[] abbritem = new String[7];

    public AutoAbbreviations() {
        for (int i = 0; i < 7; i++) {
            abbritem[i] = "";
        }
    }

    public static void main(String[] args) {
        AutoAbbreviations program = new AutoAbbreviations();
        program.run();
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader("days-of-week.txt"))) {
            endo = 0;
            while (endo == 0) {
                dowLine = reader.readLine();
                if (dowLine == null) {
                    endo = 1;
                } else {
                    if (dowLine.isEmpty()) {
                        System.out.println();
                    } else {
                        endo2 = 0;
                        charamt = 0;

                        while (endo2 == 0) {
                            abptr = 1;
                            daynum = 1;
                            abrestart = 0;
                            charamt++;

                            for (int i = 0; i < 7; i++) {
                                abbritem[i] = "";
                            }

                            String[] words = dowLine.split(" ");
                            for (int i = 0; i < 7 && i < words.length; i++) {
                                curday = words[i];

                                tmp1 = curday.length();
                                tmp2 = 0;
                                for (char c : curday.toCharArray()) {
                                    if (c == ' ') tmp2++;
                                }
                                tmp1 = tmp1 - tmp2;

                                if (tmp1 > largestCharamt) {
                                    largestCharamt = tmp1;
                                }

                                if (curday.isEmpty()) {
                                    endo2 = 3;
                                }

                                if (charamt <= curday.length()) {
                                    curabbr = curday.substring(0, charamt);
                                } else {
                                    curabbr = curday;
                                }

                                boolean found = false;
                                for (int j = 0; j < 7; j++) {
                                    if (abbritem[j].equals(curabbr)) {
                                        found = true;
                                        break;
                                    }
                                }
                                if (found) {
                                    abrestart = 1;
                                }

                                abbritem[daynum - 1] = curabbr;
                                daynum++;
                            }

                            if (abrestart == 0) {
                                endo2 = 1;
                            }

                            if (charamt > largestCharamt) {
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
                            System.out.print(charamt + ": ");

                            for (int i = 0; i < 7; i++) {
                                curabbr = abbritem[i];

                                tmp1 = curabbr.length();
                                tmp2 = 0;
                                for (int j = curabbr.length() - 1; j >= 0; j--) {
                                    if (curabbr.charAt(j) == ' ') {
                                        tmp2++;
                                    } else {
                                        break;
                                    }
                                }
                                tmp1 = tmp1 - tmp2;

                                System.out.print(curabbr.substring(0, tmp1) + ".");

                                if (i < 6) {
                                    System.out.print(" ");
                                } else {
                                    System.out.println();
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