import java.util.Scanner;

public class Cobol3d {
    public static void main(String[] args) {
        String cobolTextData = "________/\\\\\\\\\\\\\\\\____" +
                "____/\\\\\\\\________/\\\\\\\\\\\\\\\\\\\\\\\\__________/\\\\\\\\________" +
                "/\\\\_____________         _____/\\\\\\////////_______/\\\\\\//" +
                "/\\\\\\_____\\/\\\\\\/////////\\\\\\______/\\\\\\///\\\\\\_____\\/\\\\\\____" +
                "_________         ___/\\\\\\/______________/\\\\\\/__\\///\\\\\\__" +
                "_\\/\\\\\\_______\\/\\\\\\____/\\\\\\/__\\///\\\\\\___\\/\\\\\\____________" +
                "_         __/\\\\\\_______________/\\\\\\______\\//\\\\\\__\\/\\\\\\\\" +
                "\\\\\\\\\\\\\\\\____/\\\\\\______\\//\\\\\\__\\/\\\\\\_____________       " +
                "  _\\/\\\\\\______________\\/\\\\\\_______\\/\\\\\\__\\/\\\\\\/////////\\\\\\" +
                "__\\/\\\\\\_______\\/\\\\\\__\\/\\\\\\_____________         _\\//\\\\\\_" +
                "____________\\//\\\\\\______/\\\\\\___\\/\\\\\\_______\\/\\\\\\__\\//\\\\\\" +
                "______/\\\\\\___\\/\\\\\\_____________         __\\///\\\\\\_______" +
                "_____\\///\\\\\\__/\\\\\\_____\\/\\\\\\_______\\/\\\\\\___\\///\\\\\\__/\\\\\\" +
                "_____\\/\\\\\\_____________         ____\\////\\\\\\\\\\\\\\\\\\_____\\" +
                "///\\\\\\\\\\/______\\/\\\\\\\\\\\\\\\\\\\\\\\\\\/______\\///\\\\\\\\\\/______\\/\\" +
                "\\\\\\\\\\\\\\\\\\\\\\\\\\\_         _______\\/////////________\\/////_" +
                "_______\\/////////////__________\\/////________\\//////////" +
                "/////__";

        String[] cobolText = new String[10];
        for (int idx = 0; idx < 10; idx++) {
            cobolText[idx] = cobolTextData.substring(idx * 103, (idx + 1) * 103);
        }

        for (int i = 0; i < 10; i++) {
            int j = 0;
            while (j < 103) {
                if (j + 3 < 103 && cobolText[i].substring(j, j + 4).equals("\\\\\\\/")) {
                    printAt(i + 1, j + 1, cobolText[i].substring(j, j + 3), 7, true);
                    j += 3;
                    printAt(i + 1, j + 1, cobolText[i].substring(j, j + 1), 0, true);
                    j += 1;
                    continue;
                }

                String currentChar = cobolText[i].substring(j, j + 1);
                String nextChar = (j + 1 < 103) ? cobolText[i].substring(j + 1, j + 2) : "";
                String nextTwoChars = (j + 2 < 103) ? cobolText[i].substring(j + 1, j + 3) : "";

                if (currentChar.equals("/") || nextChar.equals("/") || nextTwoChars.equals("\\/")) {
                    printAt(i + 1, j + 1, currentChar, 0, true);
                } else {
                    printAt(i + 1, j + 1, currentChar, 7, true);
                }

                j += 1;
            }
        }

        System.out.print("\nPress enter to stop appreciating COBOL in 3D.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    private static void printAt(int line, int col, String text, int color, boolean highlight) {
        System.out.print(text);
    }
}