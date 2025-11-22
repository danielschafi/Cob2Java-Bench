import java.util.Scanner;

public class Cobol3D {
    public static void main(String[] args) {
        String cobolTextData = "________/\\\\\\\\\\____"
                + "____/\\\\\\________/\\\\\\\\\\\\\\__________/\\\\\\________"
                + "/\\\_____________         _____/\\\////////_______/\\\//"
                + "/\\\_____\/\\\/////////\\\______/\\\///\\\_____\/\\\____"
                + "_________         ___/\\\/______________/\\\/__\\///\\\__"
                + "_\\/\\\_______\\/\\\____/\\\/__\\///\\\___\\/\\\____________"
                + "_         __/\\\_______________/\\\______\\//\\\__\\/\\\\\\"
                + "\\\\\\\\\____/\\\______\\//\\\__\\/\\\_____________       "
                + "  _\\/\\\______________\\/\\\_______\\/\\\__\\/\\\/////////\\\\"
                + "__\\/\\\_______\\/\\\__\\/\\\_____________         _\\//\\\_"
                "____________\\//\\\______/\\\___\\/\\\_______\\/\\\__\\//\\"
                "______/\\\___\\/\\\_____________         __\\///\\\_______"
                "_____\///\\\__/\\\_____\\/\\\_______\\/\\\___\\///\\\__/\\"
                "_____\\/\\\_____________         ____\\////\\\\\\\\\\_____\\"
                + "///\\\\\\/______\\/\\\\\\\\\\\\\\/______\\///\\\\\\/______\\/\\"
                + "\\\\\\\\\\\\\\_         _______\\/////////________\\/////\\"
                + "_______\\/////////////__________\\/////________\\//////////"
                + "/////__";

        String[] cobolText = new String[10];
        for (int k = 0; k < 10; k++) {
            cobolText[k] = cobolTextData.substring(k * 103, (k + 1) * 103);
        }

        int i, j;

        for (i = 1; i <= 10; i++) {
            j = 1;
            while (j <= 103) {
                if (cobolText[i - 1].substring(j - 1, j + 3).equals("\\\\/")) {
                    System.out.print("\u001B[37m" + cobolText[i - 1].substring(j - 1, j + 2));
                    j += 3;
                    System.out.print("\u001B[30m" + cobolText[i - 1].substring(j - 1, j));
                    j++;
                    continue;
                }

                if (cobolText[i - 1].substring(j - 1, j).equals("/") ||
                    (j < 103 && cobolText[i - 1].substring(j, j + 1).equals("/")) ||
                    (j < 102 && cobolText[i - 1].substring(j, j + 2).equals("\\/"))) {
                    System.out.print("\u001B[30m" + cobolText[i - 1].substring(j - 1, j));
                } else {
                    System.out.print("\u001B[37m" + cobolText[i - 1].substring(j - 1, j));
                }
                j++;
            }
            System.out.println();
        }

        System.out.print("\u001B[30mPress enter to stop appreciating COBOL in 3D.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}