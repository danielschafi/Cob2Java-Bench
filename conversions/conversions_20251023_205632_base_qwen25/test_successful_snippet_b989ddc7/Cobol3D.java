import java.util.Scanner;

public class Cobol3D {
    public static void main(String[] args) {
        String[] cobolText = {
            "________/\\\\\\\\\\____",
            "____/\\\\\\________/\\\\\\\\\\\\\\__________/\\\\\\________",
            "/\\\_____________         _____/\\\////////_______/\\\//",
            "/\\\_____\/\\\/////////\\\______/\\\///\\\_____\/\\\____",
            "_________         ___/\\\/______________/\\\/__\\///\\\__",
            "_\\/\\\_______\\/\\\____/\\\/__\\///\\\___\\/\\\____________",
            "_         __/\\\_______________/\\\______\\//\\\__\\/\\\\\\",
            "\\\\\\\\\____/\\\______\\//\\\__\\/\\\_____________       ",
            "  _\\/\\\______________\\/\\\_______\\/\\\__\\/\\\/////////\\\\",
            "__\\/\\\_______\\/\\\__\\/\\\_____________         _\\//\\\_"
        };

        for (int i = 0; i < 10; i++) {
            int j = 0;
            while (j < 103) {
                if (j + 3 < 103 && cobolText[i].substring(j, j + 4).equals("\\\\/")) {
                    System.out.print("\u001B[37m\u001B[1m" + cobolText[i].substring(j, j + 3) + "\u001B[0m");
                    j += 3;
                    System.out.print("\u001B[30m\u001B[1m" + cobolText[i].substring(j, j + 1) + "\u001B[0m");
                    j++;
                    continue;
                }

                if (j < 103 && cobolText[i].substring(j, j + 1).equals("/") ||
                    j + 1 < 103 && cobolText[i].substring(j + 1, j + 2).equals("/") ||
                    j + 1 < 103 && j + 2 < 103 && cobolText[i].substring(j + 1, j + 3).equals("\\/")) {
                    System.out.print("\u001B[30m\u001B[1m" + cobolText[i].substring(j, j + 1) + "\u001B[0m");
                } else {
                    System.out.print("\u001B[37m\u001B[1m" + cobolText[i].substring(j, j + 1) + "\u001B[0m");
                }

                j++;
            }
            System.out.println();
        }

        System.out.print("Press enter to stop appreciating COBOL in 3D.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        scanner.close();
    }
}