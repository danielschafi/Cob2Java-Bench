public class Cobol3d {
    private static final String COBOL_TEXT_DATA = "________/\\\\\\\\\\____"
            + "____/\\\\\\________/\\\\\\\\\\\\\\__________/\\\\\\________"
            + "/\\\\"_____________         _____/\\\\\\\\\\\\\\_______/\\\\\\//"
            + "/\\\\"_____\\/\\\\/////////\\\\\\______/\\\\\\///\\\\\\_____\\/\\\\____"
            + "_________         ___/\\\\\\/______________/\\\\\\/__\\///\\\\\\__"
            + "_\\/\\\\_______\\/\\\\____/\\\\\\/__\\///\\\\\\___\\/\\\\____________"
            + "_         __/\\\\\\_______________/\\\\\\______\\//\\\\\\__\\/\\\\\\\\\\"
            + "\\\\\\\\\\\\\\____/\\\\\\______\\//\\\\\\__\\/\\\\\\_____________"
            + "  _\\/\\\\______________\\/\\\\_______\\/\\\\__\\/\\\\/////////\\\\\\"
            + "__\\/\\\\_______\\/\\\\__\\/\\\\_____________         _\\//\\\\_"
            + "____________\\//\\\\______/\\\\\\___\\/\\\\_______\\/\\\\__\\//\\\\"
            + "______/\\\\\\___\\/\\\\_____________         __\\///\\\\_______"
            + "_____/\\///\\\\__/\\\\\\_____\\/\\\\_______\\/\\\\___\\///\\\\__/\\\\\\"
            + "_____\\/\\\\_____________         ____\\////\\\\\\\\\\\\\\_____"
            + "///\\\\\\/______\\/\\\\\\\\\\\\\\\\\\/______\\///\\\\\\/______\\/\\"
            + "\\\\\\\\\\\\\\\\\\_         _______\\/////////________\\/////_" 
            + "_______\\/////////////__________\\/////________\\//////////"
            + "/////__";
    
    private static final String[] COBOL_TEXT = new String[10];
    
    static {
        for (int i = 0; i < 10; i++) {
            COBOL_TEXT[i] = COBOL_TEXT_DATA.substring(i * 103, (i + 1) * 103);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int j = 0;
            while (j < 103) {
                if (COBOL_TEXT[i].substring(j, Math.min(j + 4, COBOL_TEXT[i].length())).equals("\\\\\\/")) {
                    System.out.print(COBOL_TEXT[i].substring(j, Math.min(j + 3, COBOL_TEXT[i].length())));
                    j += 3;
                    System.out.print(COBOL_TEXT[i].charAt(j));
                    j += 1;
                    break;
                } else if (COBOL_TEXT[i].charAt(j) == '/' || 
                        (j + 1 < COBOL_TEXT[i].length() && COBOL_TEXT[i].charAt(j + 1) == '/') ||
                        (j + 2 < COBOL_TEXT[i].length() && COBOL_TEXT[i].substring(j + 1, Math.min(j + 3, COBOL_TEXT[i].length())).equals("\\/"))) {
                    System.out.print(COBOL_TEXT[i].charAt(j));
                    j += 1;
                } else {
                    System.out.print(COBOL_TEXT[i].charAt(j));
                    j += 1;
                }
            }
            System.out.println();
        }
        
        System.out.println("Press enter to stop appreciating COBOL in 3D.");
        try {
            System.in.read();
        } catch (Exception e) {
            // Ignore exception
        }
    }
}