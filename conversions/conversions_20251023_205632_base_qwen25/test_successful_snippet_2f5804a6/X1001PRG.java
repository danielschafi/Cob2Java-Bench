import java.util.Scanner;

public class X1001PRG {
    public static void main(String[] args) {
        String wsFim = "";
        int wsCtlido = 0;
        double asPAcids = 0.0;
        int asMaior = 0;
        int asCidMaior = 0;
        int asQtdeMaior = 0;
        int wsCidade = 0;
        String wsEstado = "";
        int wsQtdVeiculos = 0;
        String wsBafometro = "";
        int wsQtdAcidentes = 0;
        int wsQtdObitos = 0;
        int cid = 0;
        String uf = "";
        int veics = 0;
        String baho = "";
        int acids = 0;
        int obitos = 0;
        double porcAcids = 0.0;

        System.out.println("P1-2019-2");
        System.out.println("MIGUEL COSTA DE MORAIS");
        System.out.println("1680481721001");
        System.out.println("----------------------");
        asMaior = 0;
        Scanner scanner = new Scanner(System.in);

        while (!wsFim.equals("S")) {
            String input = scanner.nextLine();
            if (input.equals("9999999999999999")) {
                wsFim = "S";
            } else {
                wsCtlido++;
                wsCidade = Integer.parseInt(input.substring(0, 4));
                wsEstado = input.substring(4, 6);
                wsQtdVeiculos = Integer.parseInt(input.substring(6, 13));
                wsBafometro = input.substring(13, 14);
                wsQtdAcidentes = Integer.parseInt(input.substring(14, 18));
                wsQtdObitos = Integer.parseInt(input.substring(18, 22));

                asPAcids = ((double) wsQtdObitos / wsQtdVeiculos) * 100;
                porcAcids = asPAcids;

                if (wsQtdAcidentes > asMaior) {
                    asMaior = wsQtdAcidentes;
                    asCidMaior = wsCidade;
                    asQtdeMaior = wsQtdAcidentes;
                }

                cid = wsCidade;
                uf = wsEstado;
                veics = wsQtdVeiculos;
                baho = wsBafometro;
                acids = wsQtdAcidentes;
                obitos = wsQtdObitos;

                System.out.printf("%04d %s %07d %s %04d %04d %05.2f%% %n", cid, uf, veics, baho, acids, obitos, porcAcids);
            }
        }

        System.out.println("------------------------------------------*");
        System.out.println("CIDADE COM MAIOR NUMERO DE ACIDENTES: " + String.format("%04d", asCidMaior));
        System.out.println("QTDE DE ACIDENTES DA CIDADE ACIMA: " + String.format("%04d", asQtdeMaior));
        System.out.println("QTDE DE CIDADES PESQUISADAS: " + wsCtlido);
        System.out.println("------------------------------------------*");
        scanner.close();
    }
}