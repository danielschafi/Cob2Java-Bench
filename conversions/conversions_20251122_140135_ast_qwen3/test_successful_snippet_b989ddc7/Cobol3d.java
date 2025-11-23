public class Cobol3d {
    private static final String COBOL_TEXT_DATA = "________/\\\\\\\\\\____" +
            "____/\\\\\\________/\\\\\\\\\\\\\\__________/\\\\\\________" +
            "/\\\\" + "_____________         _____/\\\\\\\\\\\\\\_______/\\\\\\//" +
            "/\\\\" + "_____\\/\\\\/////////\\\\\\______/\\\\\\///\\\\\\_____\\/\\\\____" +
            "_________         ___/\\\\\\/______________/\\\\\\/__\\///\\\\\\" +
            "_\\/\\\\_______\\/\\\\____/\\\\\\/__\\///\\\\\\___\\/\\\\____________" +
            "_         __/\\\\\\_______________/\\\\\\______\\//\\\\\\__\\/\\\\\\\\" +
            "\\\\\\\\\\\\\\\\____/\\\\\\______\\//\\\\\\__\\/\\\\\\_____________" +
            "  _\\/\\\\\\______________\\/\\\\\\_______\\/\\\\\\__\\/\\\\/////////\\\\\\" +
            "__\\/\\\\_______\\/\\\\__\\/\\\\\\_____________         _\\//\\\\\\_" +
            "____________\\//\\\\\\______/\\\\\\___\\/\\\\\\_______\\/\\\\\\__\\//\\\\\\" +
            "______/\\\\\\___\\/\\\\\\_____________         __\\///\\\\\\_______" +
            "_____/\\///\\\\\\__/\\\\\\_____\\/\\\\\\_______\\/\\\\\\___\\///\\\\\\__/\\\\\\" +
            "_____\\/\\\\\\_____________         ____\\////\\\\\\\\\\\\\\_____\\\\" +
            "///\\\\\\/______\\/\\\\\\\\\\\\\\\\\\/______\\///\\\\\\/______\\/\\\\" +
            "\\\\\\\\\\\\\\\\\\_         _______\\/////////________\\/////_" +
            "_______\\/////////////__________\\/////________\\//////////" +
            "/////__";

    private static final String[] COBOL_TEXT = new String[10];

    public static void main(String[] args) {
        // Initialize the cobol-text array from cobol-text-data
        for (int i = 0; i < 10; i++) {
            COBOL_TEXT[i] = COBOL_TEXT_DATA.substring(i * 103, (i + 1) * 103);
        }

        int i;
        int j;

        for (i = 1; i <= 10; i++) {
            j = 1;
            while (j <= 103) {
                if (COBOL_TEXT[i - 1].substring(j - 1, Math.min(j + 3, COBOL_TEXT[i - 1].length())).equals("\\\\\\/")) {
                    System.out.print(COBOL_TEXT[i - 1].substring(j - 1, Math.min(j + 2, COBOL_TEXT[i - 1].length())));
                    j += 3;
                    System.out.print(COBOL_TEXT[i - 1].charAt(j - 1));
                    j += 1;
                    continue;
                }

                char currentChar = COBOL_TEXT[i - 1].charAt(j - 1);
                if (currentChar == '/' || 
                    (j + 1 <= COBOL_TEXT[i - 1].length() && COBOL_TEXT[i - 1].charAt(j) == '/') ||
                    (j + 2 <= COBOL_TEXT[i - 1].length() && 
                     COBOL_TEXT[i - 1].substring(j - 1, Math.min(j + 1, COBOL_TEXT[i - 1].length())).equals("\\/"))) {
                    System.out.print(currentChar);
                } else {
                    System.out.print(currentChar);
                }
                j += 1;
            }
        }

        System.out.println("Press enter to stop appreciating COBOL in 3D.");
        try {
            System.in.read();
        } catch (Exception e) {
            // Ignore exception
        }
    }
}