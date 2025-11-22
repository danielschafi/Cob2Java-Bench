import java.util.Scanner;

public class RSPRG002 {
    public static void main(String[] args) {
        String wsFim = "";
        int wsCtlido = 0;
        double wsMedia = 0.0;
        int wsNumeroIn = 0;
        String wsNomeIn = "";
        String wsSexoIn = "";
        int wsIdadeIn = 0;
        String wsCursoIn = "";
        double wsNota1In = 0.0;
        double wsNota2In = 0.0;

        Scanner scanner = new Scanner(System.in);

        while (!wsFim.equals("S")) {
            String input = scanner.nextLine();
            if (input.equals("99999999999999999999999999999999")) {
                wsFim = "S";
            } else {
                wsCtlido++;
                wsNumeroIn = Integer.parseInt(input.substring(0, 4).trim());
                wsNomeIn = input.substring(4, 24).trim();
                wsSexoIn = input.substring(24, 25).trim();
                wsIdadeIn = Integer.parseInt(input.substring(25, 27).trim());
                wsCursoIn = input.substring(27, 39).trim();
                wsNota1In = Double.parseDouble(input.substring(39, 44).trim().replace(',', '.'));
                wsNota2In = Double.parseDouble(input.substring(44, 49).trim().replace(',', '.'));

                wsMedia = (wsNota1In + wsNota2In) / 2;

                System.out.println(String.format("%04d%-20s%-1s%02d%-12s%05.2f%05.2f", wsNumeroIn, wsNomeIn, wsSexoIn, wsIdadeIn, wsCursoIn, wsNota1In, wsNota2In));
                System.out.printf("%05.2f%n", wsMedia);
            }
        }

        System.out.println(" *========================================*");
        System.out.println(" *   TOTAIS DE CONTROLE - RSPRG002        *");
        System.out.println(" *----------------------------------------*");
        System.out.println(" * REGISTROS LIDOS    - SYSIN  = " + wsCtlido);
        System.out.println(" *========================================*");
        System.out.println(" *----------------------------------------*");
        System.out.println(" *      TERMINO NORMAL DO RSPRG002        *");
        System.out.println(" *----------------------------------------*");

        scanner.close();
    }
}