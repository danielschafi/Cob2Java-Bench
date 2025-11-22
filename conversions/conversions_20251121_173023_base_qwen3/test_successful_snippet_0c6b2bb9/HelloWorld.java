public class HelloWorld {
    private static int x;
    private static int y;
    private static int z;
    private static String aDatum;
    private static String aNamn;
    private static int aAlder;
    private static String datumRed;
    private static String namn;
    private static boolean bilNej;
    private static boolean bilJa;
    private static boolean sysNej;
    private static boolean sysJa;
    private static String resultat;

    public static void main(String[] args) {
        x = 10;
        x = x + 1;
        x = x - 1;
        x = x * 6;
        y = x / 3;
        z = x % 3;
        System.out.println("X=" + x + " Y=" + y + " Z=" + z);

        x = 10;
        y = (((x + 1) - 1) * 6) / 3;
        System.out.println("Y=" + y);

        java.time.LocalDate date = java.time.LocalDate.now();
        aDatum = String.format("%04d%02d%02d", date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        System.out.println("Dagens datum: " + aDatum);

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Enter name: ");
        aNamn = scanner.nextLine();
        System.out.print("Enter age: ");
        aAlder = scanner.nextInt();

        sysNej = true;
        if (aAlder >= 18) {
            bilJa = true;
            if (aAlder >= 20) {
                sysJa = true;
            }
        } else {
            bilNej = true;
        }

        switch (aAlder) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                bilNej = true;
                sysNej = true;
                break;
            case 18:
            case 19:
                bilJa = true;
                sysNej = true;
                break;
            default:
                bilJa = true;
                sysJa = true;
                break;
        }

        if (aNamn.equals("Pink Panther")) {
            resultat = "Du är ju Rosa Pantern! Du står över lagen!";
        } else if (aAlder < 18) {
            bilNej = true;
            sysNej = true;
        } else if (aAlder >= 18 && aAlder < 20) {
            bilJa = true;
            sysNej = true;
        } else {
            bilJa = true;
            sysJa = true;
        }

        datumRed = aDatum;
        namn = aNamn;
        System.out.println("RAPPORT " + datumRed + " " + namn);
        System.out.println(resultat);

        System.out.println(namn.length());

        for (int i = 0; i < namn.length(); i++) {
            if (namn.charAt(i) == ' ') {
                break;
            }
            System.out.print(namn.charAt(i));
        }
        System.out.println();

        scanner.close();
    }
}