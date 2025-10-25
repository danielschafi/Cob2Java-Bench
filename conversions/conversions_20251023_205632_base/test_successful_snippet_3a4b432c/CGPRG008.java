import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class CGPRG008 {
    private static final String CADALUJ = "CADALUJ.txt";
    private static String wsFim = "";
    private static int wsCtlido = 0;
    private static int wsCtgrav = 0;
    private static String wsFsAlu = "";
    private static String wsMsg = "";
    private static String wsFsMsg = "";
    private static double wsMedia = 0.0;
    private static int wsNumeroIn = 0;
    private static String wsNomeIn = "";
    private static String wsSexoIn = "";
    private static int wsIdadeIn = 0;
    private static String wsCursoIn = "";
    private static double wsNota1In = 0.0;
    private static double wsNota2In = 0.0;
    private static int wsNumeroS = 0;
    private static String wsNomeS = "";
    private static String wsSexoS = "";
    private static int wsIdadeS = 0;
    private static String wsCursoS = "";
    private static double wsNota1S = 0.0;
    private static double wsNota2S = 0.0;
    private static double wsMediaS = 0.0;
    private static int wsNumeroO = 0;
    private static String wsNomeO = "";
    private static String wsSexoO = "";
    private static int wsIdadeO = 0;
    private static String wsCursoO = "";
    private static double wsNota1O = 0.0;
    private static double wsNota2O = 0.0;
    private static double wsMediaO = 0.0;
    private static final String wsHifen = "--------------------------------------------------------------------------------";

    public static void main(String[] args) {
        try (Scanner sysin = new Scanner(System.in);
             BufferedWriter cadalu = Files.newBufferedWriter(Paths.get(CADALUJ))) {

            rsprg003(cadalu, sysin);

        } catch (IOException e) {
            wsMsg = "ERRO AO ABRIR O CADALU";
            wsFsMsg = e.getMessage();
            erro();
        }
    }

    private static void rsprg003(BufferedWriter cadalu, Scanner sysin) {
        iniciar(cadalu);
        while (!wsFim.equals("S")) {
            processar(cadalu);
        }
        terminar();
    }

    private static void iniciar(BufferedWriter cadalu) {
        try {
            cadalu.write("");
        } catch (IOException e) {
            wsMsg = "ERRO AO ABRIR O CADALU";
            wsFsMsg = e.getMessage();
            erro();
        }
        lerSysin(sysin);
    }

    private static void lerSysin(Scanner sysin) {
        if (sysin.hasNext()) {
            wsNumeroIn = sysin.nextInt();
            wsNomeIn = sysin.next();
            wsSexoIn = sysin.next();
            wsIdadeIn = sysin.nextInt();
            wsCursoIn = sysin.next();
            wsNota1In = sysin.nextDouble();
            wsNota2In = sysin.nextDouble();
        } else {
            wsFim = "S";
        }
        if (wsNumeroIn == 9999) {
            wsFim = "S";
        } else {
            wsCtlido++;
        }
    }

    private static void processar(BufferedWriter cadalu) {
        wsMedia = (wsNota1In + wsNota2In) / 2;
        wsNumeroS = wsNumeroIn;
        wsNomeS = wsNomeIn;
        wsSexoS = wsSexoIn;
        wsIdadeS = wsIdadeIn;
        wsCursoS = wsCursoIn;
        wsNota1S = wsNota1In;
        wsNota2S = wsNota2In;
        wsMediaS = wsMedia;

        try {
            cadalu.write(String.format("%04d%-20s%-1s%02d%-12s%05.2f%05.2f%05.2f%n",
                    wsNumeroS, wsNomeS, wsSexoS, wsIdadeS, wsCursoS, wsNota1S, wsNota2S, wsMediaS));
            wsCtgrav++;
        } catch (IOException e) {
            wsMsg = "ERRO NA GRAVACAO DO CADALUN";
            wsFsMsg = e.getMessage();
            erro();
        }

        wsNumeroO = wsNumeroS;
        wsNomeO = wsNomeS;
        wsSexoO = wsSexoS;
        wsIdadeO = wsIdadeS;
        wsCursoO = wsCursoS;
        wsNota1O = wsNota1S;
        wsNota2O = wsNota2S;
        wsMediaO = wsMediaS;

        System.out.printf("%04d %-20s %-1s %02d %-12s %5.2f %5.2f %5.2f%n",
                wsNumeroO, wsNomeO, wsSexoO, wsIdadeO, wsCursoO, wsNota1O, wsNota2O, wsMediaO);

        lerSysin(sysin);
    }

    private static void terminar() {
        System.out.println(" *========================================*");
        System.out.println(" *   TOTAIS DE CONTROLE - RSPRG003        *");
        System.out.println(" *----------------------------------------*");
        System.out.println(" * REGISTROS LIDOS    - SYSIN  = " + wsCtlido);
        System.out.println(" * REGISTROS GRAVADOS - CADALU = " + wsCtgrav);
        System.out.println(" *========================================*");

        try (BufferedWriter cadalu = Files.newBufferedWriter(Paths.get(CADALUJ), StandardOpenOption.APPEND)) {
            cadalu.write("");
        } catch (IOException e) {
            wsMsg = "ERRO AO FECHAR O CADALU";
            wsFsMsg = e.getMessage();
            erro();
        }

        System.out.println(" *----------------------------------------*");
        System.out.println(" *      TERMINO NORMAL DO RSPRG003        *");
        System.out.println(" *----------------------------------------*");
    }

    private static void erro() {
        System.out.println(" *----------------------------------------*");
        System.out.println(" *           PROGRAMA CANCELADO           *");
        System.out.println(" *----------------------------------------*");
        System.out.println(" * MENSAGEM    = " + wsMsg);
        System.out.println(" * FILE STATUS = " + wsFsMsg);
        System.out.println(" *----------------------------------------*");
        System.out.println(" *       TERMINO ANORMAL DO RSPRG003      *");
        System.out.println(" *----------------------------------------*");
        System.exit(1);
    }
}