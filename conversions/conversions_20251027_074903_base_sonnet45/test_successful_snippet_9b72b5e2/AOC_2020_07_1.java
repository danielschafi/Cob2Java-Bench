import java.io.*;
import java.util.*;

public class AOC_2020_07_1 {
    static class Bag {
        String color;
        int done;
        int bagsNumber;
        String[] bags;

        Bag() {
            color = "";
            done = 0;
            bagsNumber = 0;
            bags = new String[32];
            for (int i = 0; i < 32; i++) {
                bags[i] = "";
            }
        }
    }

    public static void main(String[] args) {
        Bag[] wsBags = new Bag[594];
        for (int i = 0; i < 594; i++) {
            wsBags[i] = new Bag();
        }
        String[] wsBagsQueue = new String[9999];
        for (int i = 0; i < 9999; i++) {
            wsBagsQueue[i] = "";
        }

        int n = 0;
        int result = 0;
        int bagIdx = 0;
        int q1 = 0;
        int q2 = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("d07.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] buffer = new String[32];
                for (int i = 0; i < 32; i++) {
                    buffer[i] = "";
                }

                String[] tokens = line.split("\\s+");
                for (int j = 0; j < tokens.length && j < 32; j++) {
                    buffer[j] = tokens[j];
                }

                wsBags[n].color = buffer[0] + " " + buffer[1];

                if (!buffer[4].equals("no")) {
                    int k = 0;
                    for (int j = 4; j < 32; j += 4) {
                        if (!buffer[j].isEmpty() && !buffer[j].trim().isEmpty()) {
                            if (j + 1 < 32 && j + 2 < 32) {
                                wsBags[n].bags[k] = buffer[j + 1] + " " + buffer[j + 2];
                                k++;
                            }
                        }
                    }
                    wsBags[n].bagsNumber = k;
                }

                n++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        wsBagsQueue[0] = "shiny gold";
        q2 = 0;

        while (q1 <= q2) {
            String wsBag = wsBagsQueue[q1];
            q1++;

            bagIdx = -1;
            for (int k = 0; k < n; k++) {
                if (wsBag.equals(wsBags[k].color)) {
                    bagIdx = k;
                    break;
                }
            }

            if (bagIdx != -1) {
                wsBags[bagIdx].done = 1;
            }

            for (int i = 0; i < n; i++) {
                if (wsBags[i].done == 0) {
                    for (int j = 0; j < wsBags[i].bagsNumber; j++) {
                        if (wsBag.equals(wsBags[i].bags[j])) {
                            q2++;
                            wsBagsQueue[q2] = wsBags[i].color;
                            break;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (wsBags[i].done == 1) {
                result++;
            }
        }

        result--;

        System.out.println(q2);
        System.out.println(result);
    }
}