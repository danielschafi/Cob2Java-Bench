import java.util.Scanner;

public class X1001PRG {
    private String wsFim = "N";
    private int wsCtlido = 0;
    private double asPAcids;
    private int asMaior = 0;
    private int asCidMaior;
    private int asQtdeMaior;
    private int wsCidade;
    private String wsEstado;
    private int wsQtdVeiculos;
    private String wsBafometro;
    private int wsQtdAcidentes;
    private int wsQtdObitos;
    private int cid;
    private String uf;
    private int veics;
    private String bafo;
    private int acids;
    private int obitos;
    private double porcAcids;

    public static void main(String[] args) {
        X1001PRG program = new X1001PRG();
        program.run();
    }

    public void run() {
        iniciar();
        while (!wsFim.equals("S")) {
            processar();
        }
        terminar();
    }

    private void iniciar() {
        System.out.println("P1-2019-2");
        System.out.println("MIGUEL COSTA DE MORAIS");
        System.out.println("1680481721001");
        System.out.println("----------------------");
        asMaior = 0;
        lerSysin();
    }

    private void lerSysin() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("99999999")) {
            wsFim = "S";
        } else {
            wsCtlido++;
            wsCidade = Integer.parseInt(input.substring(0, 4));
            wsEstado = input.substring(4, 6);
            wsQtdVeiculos = Integer.parseInt(input.substring(6, 13));
            wsBafometro = input.substring(13, 14);
            wsQtdAcidentes = Integer.parseInt(input.substring(14, 18));
            wsQtdObitos = Integer.parseInt(input.substring(18, 22));
        }
    }

    private void processar() {
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
        bafo = wsBafometro;
        acids = wsQtdAcidentes;
        obitos = wsQtdObitos;

        System.out.printf("%04d %s %07d %s %04d %04d %05.2f%% %n", cid, uf, veics, bafo, acids, obitos, porcAcids);

        lerSysin();
    }

    private void terminar() {
        System.out.println("------------------------------------------*");
        System.out.println("CIDADE COM MAIOR NUMERO DE ACIDENTES: " + String.format("%04d", asCidMaior));
        System.out.println("QTDE DE ACIDENTES DA CIDADE ACIMA: " + asQtdeMaior);
        System.out.println("QTDE DE CIDADES PESQUISADAS: " + wsCtlido);
        System.out.println("------------------------------------------*");
    }
}