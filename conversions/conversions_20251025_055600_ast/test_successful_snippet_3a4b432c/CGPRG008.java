import java.io.*;
import java.util.Scanner;

public class CGPRG008 {
    private static final int MAX_RECORDS = 1000;
    private static final String OUTPUT_FILE = "CADALUJ.txt";

    private String wsFim = "N";
    private int wsCtlido = 0;
    private int wsCtgrav = 0;
    private String wsFsAlu = "00";
    private String wsMsg = "";
    private String wsFsMsg = "00";
    private double wsMedia = 0.0;

    private int wsNumeroIn = 0;
    private String wsNomeIn = new String(new char[20]);
    private String wsSexoIn = new String(new char[1]);
    private int wsIdadeIn = 0;
    private String wsCursoIn = new String(new char[12]);
    private double wsNota1In = 0.0;
    private double wsNota2In = 0.0;

    private int wsNumeroS = 0;
    private String wsNomeS = new String(new char[20]);
    private String wsSexoS = new String(new char[1]);
    private int wsIdadeS = 0;
    private String wsCursoS = new String(new char[12]);
    private double wsNota1S = 0.0;
    private double wsNota2S = 0.0;
    private double wsMediaS = 0.0;

    private int wsNumeroO = 0;
    private String wsNomeO = new String(new char[20]);
    private String wsSexoO = new String(new char[1]);
    private int wsIdadeO = 0;
    private String wsCursoO = new String(new char[12]);
    private double wsNota1O = 0.0;
    private double wsNota2O = 0.0;
    private double wsMediaO = 0.0;

    public static void main(String[] args) {
        CGPRG008 program = new CGPRG008();
        program.run();
    }

    public void run() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
            iniciar();
            while (!wsFim.equals("S")) {
                processar(writer);
            }
            terminar();
        } catch (IOException e) {
            erro("ERRO AO GRAVAR O CADALU", e.getMessage());
        }
    }

    private void iniciar() {
        try (Scanner scanner = new Scanner(System.in)) {
            lerSysin(scanner);
        } catch (Exception e) {
            erro("ERRO AO ABRIR O CADALU", e.getMessage());
        }
    }

    private void lerSysin(Scanner scanner) {
        if (scanner.hasNext()) {
            wsNumeroIn = scanner.nextInt();
            wsNomeIn = scanner.next();
            wsSexoIn = scanner.next();
            wsIdadeIn = scanner.nextInt();
            wsCursoIn = scanner.next();
            wsNota1In = scanner.nextDouble();
            wsNota2In = scanner.nextDouble();
            wsCtlido++;
        } else {
            wsFim = "S";
        }
    }

    private void processar(BufferedWriter writer) throws IOException {
        wsMedia = (wsNota1In + wsNota2In) / 2.0;

        wsNumeroS = wsNumeroIn;
        wsNomeS = wsNomeIn;
        wsSexoS = wsSexoIn;
        wsIdadeS = wsIdadeIn;
        wsCursoS = wsCursoIn;
        wsNota1S = wsNota1In;
        wsNota2S = wsNota2In;
        wsMediaS = wsMedia;

        writer.write(String.format("%04d%-20s%-1s%02d%-12s%05.2f%05.2f%05.2f\n",
                wsNumeroS, wsNomeS, wsSexoS, wsIdadeS, wsCursoS, wsNota1S, wsNota2S, wsMediaS));
        wsCtgrav++;

        wsNumeroO = wsNumeroS;
        wsNomeO = wsNomeS;
        wsSexoO = wsSexoS;
        wsIdadeO = wsIdadeS;
        wsCursoO = wsCursoS;
        wsNota1O = wsNota1S;
        wsNota2O = wsNota2S;
        wsMediaO = wsMediaS;

        System.out.printf("%04d %-20s %-1s %-2d %-12s %5.2f %5.2f %5.2f\n",
                wsNumeroO, wsNomeO, wsSexoO, wsIdadeO, wsCursoO, wsNota1O, wsNota2O, wsMediaO);

        try (Scanner scanner = new Scanner(System.in)) {
            lerSysin(scanner);
        } catch (Exception e) {
            erro("ERRO AO LER O SYSIN", e.getMessage());
        }
    }

    private void terminar() {
        System.out.println(" *========================================*");
        System.out.println(" *   TOTAIS DE CONTROLE - RSPRG003        *");
        System.out.println(" *----------------------------------------*");
        System.out.println(" * REGISTROS LIDOS    - SYSIN  = " + wsCtlido);
        System.out.println(" * REGISTROS GRAVADOS - CADALU = " + wsCtgrav);
        System.out.println(" *========================================*");
        System.out.println(" *----------------------------------------*");
        System.out.println(" *      TERMINO NORMAL DO RSPRG003        *");
        System.out.println(" *----------------------------------------*");
    }

    private void erro(String msg, String fsMsg) {
        System.out.println(" *----------------------------------------*");
        System.out.println(" *           PROGRAMA CANCELADO           *");
        System.out.println(" *----------------------------------------*");
        System.out.println(" * MENSAGEM    = " + msg);
        System.out.println(" * FILE STATUS = " + fsMsg);
        System.out.println(" *----------------------------------------*");
        System.out.println(" *       TERMINO ANORMAL DO RSPRG003      *");
        System.out.println(" *----------------------------------------*");
        System.exit(1);
    }
}