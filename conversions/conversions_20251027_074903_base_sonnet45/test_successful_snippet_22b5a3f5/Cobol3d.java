import java.util.Scanner;

public class Cobol3d {
    public static void main(String[] args) {
        String cobolTextData = "________/\\\\\\\\\____" +
                "____/\\\\\\\\_______/\\\\\\\\\\\\\__________/\\\\\\_______" +
                "/\\\\______________" +
                "_____/\\\////////_______/\\\///" +
                "\\\\____\\/\\\\////////\\\\______/\\\///\\\\____\\/\\\\____" +
                "__________" +
                "___/\\\\/______________/\\\\/__\\///\\\\__" +
                "_\\/\\\\_______\\/\\\\____/\\\\/__\\///\\\\___\\/\\\\____________" +
                "__" +
                "__/\\\\_______________ /\\\\______\\//\\\\__\\/\\\\\\" +
                "\\\\\\\\\\\\____/\\\\______\\//\\\\__\\/\\\\_____________" +
                "  _\\/\\\\______________\\/\\\\_______\\/\\\\__\\/\\\\/////////\\\\" +
                "__\\/\\\\_______\\/\\\\__\\/\\\\_____________" +
                "_\\//\\\\" +
                "____________\\//\\\\______/\\\\___\\/\\\\_______\\/\\\\__\\//\\\\" +
                "______/\\\\___\\/\\\\_____________" +
                "__\\///\\\\_______" +
                "_____\\///\\\\__/\\\\_____\\/\\\\_______\\/\\\\___\\///\\\\__/\\\\" +
                "_____\\/\\\\_____________" +
                "____\\////\\\\\\\\\\\\______" +
                "\\///\\\\\\/______\\/\\\\\\\\\\\\\\\\\\/_____\\///\\\\\\/______\\/\\" +
                "\\\\\\\\\\\\\\\\\_" +
                "_______\\/////////________\\/////_" +
                "_______\\////////////__________\\/////________\\///////////" +
                "////__";

        String[] cobolText = new String[10];
        for (int idx = 0; idx < 10; idx++) {
            cobolText[idx] = cobolTextData.substring(idx * 103, (idx + 1) * 103);
        }

        for (int i = 1; i <= 10; i++) {
            int j = 1;
            while (j <= 103) {
                if (j + 3 <= 103 && cobolText[i - 1].substring(j - 1, j + 3).equals("\\\\/")) {
                    System.out.print("\033[" + i + ";" + j + "H");
                    System.out.print("\033[1;37m");
                    System.out.print(cobolText[i - 1].substring(j - 1, j + 2));

                    j += 3;
                    System.out.print("\033[" + i + ";" + j + "H");
                    System.out.print("\033[1;30m");
                    System.out.print(cobolText[i - 1].substring(j - 1, j));

                    j += 1;
                    continue;
                }

                String currentChar = cobolText[i - 1].substring(j - 1, j);
                String nextChar = (j < 103) ? cobolText[i - 1].substring(j, j + 1) : "";
                String nextTwoChars = (j + 1 <= 103) ? cobolText[i - 1].substring(j, Math.min(j + 2, 103)) : "";

                if (currentChar.equals("/") || nextChar.equals("/") || nextTwoChars.equals("\\/")) {
                    System.out.print("\033[" + i + ";" + j + "H");
                    System.out.print("\033[1;30m");
                    System.out.print(currentChar);
                } else {
                    System.out.print("\033[" + i + ";" + j + "H");
                    System.out.print("\033[1;37m");
                    System.out.print(currentChar);
                }

                j += 1;
            }
        }

        System.out.print("\033[11;1H");
        System.out.print("\033[0m");
        System.out.print("Press enter to stop appreciating COBOL in 3D.");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        scanner.close();
    }
}