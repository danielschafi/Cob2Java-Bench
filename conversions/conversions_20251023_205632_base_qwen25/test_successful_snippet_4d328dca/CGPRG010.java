import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CGPRG010 {
    private static final int MAX_LINE_LENGTH = 70;
    private static final int REL_LINE_LENGTH = 80;
    private static final int MAX_LINES_PER_PAGE = 13;

    private static int wsCtlido;
    private static int wsCtimpr;
    private static int wsCtlin = 65;
    private static String wsDtsys;
    private static String wsHrsys;
    private static String wsDtedi;
    private static String wsHredi;
    private static String wsFsAlu;
    private static String wsFsRel;
    private static String wsMsg;
    private static String wsFsMsg;

    private static int wsNumeroE;
    private static String wsNomeE;
    private static String wsSexoE;
    private static int wsIdadeE;
    private static String wsCursoE;
    private static double wsNota1E;
    private static double wsNota2E;
    private static double wsMediaE;

    private static String wsDataCab;
    private static String wsCursoCab;
    private static int wsPagCab = 0;

    private static int wsNumeroR;
    private static String wsNomeR;
    private static String wsCursoR;
    private static double wsNota1R;
    private static double wsNota2R;
    private static double wsMediaR;
    private static String wsAvaliacaoR;

    private static final String wsHifen = "--------------------------------------------------------------------------------";

    public static void main(String[] args) {
        wsCtlido = 0;
        wsCtimpr = 0;
        wsCtlin = 65;
        wsDtedi = "";
        wsHredi = "";
        wsFsAlu = "";
        wsFsRel = "";
        wsMsg = "";
        wsFsMsg = "";

        wsNumeroE = 0;
        wsNomeE = "";
        wsSexoE = "";
        wsIdadeE = 0;
        wsCursoE = "";
        wsNota1E = 0.0;
        wsNota2E = 0.0;
        wsMediaE = 0.0;

        wsDataCab = "";
        wsCursoCab = "";
        wsPagCab = 0;

        wsNumeroR = 0;
        wsNomeR = "";
        wsCursoR = "";
        wsNota1R = 0.0;
        wsNota2R = 0.0;
        wsMediaR = 0.0;
        wsAvaliacaoR = "";

        try {
            dataHora();
            System.out.println(" *----------------------------------------*");
            System.out.println(" * INICIO : " + wsDtedi + " AS " + wsHredi);
            System.out.println(" *----------------------------------------*");

            System.out.println(" *----------------------------------------*");
            System.out.println(" * PROGRAMA 10 - FATEC SCS                *");
            System.out.println(" * MIGUEL MORAIS                          *");
            System.out.println(" * JESSICA HOLANDA                        *");
            System.out.println(" *----------------------------------------*");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o curso: ");
            wsCursoCab = scanner.nextLine();
            wsDataCab = wsDtedi;

            BufferedReader reader = new BufferedReader(new FileReader("CADALUJ"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("RELALUJ"));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() > MAX_LINE_LENGTH) {
                    wsFsAlu = "10";
                    wsMsg = "ERRO - CADALU VAZIO";
                    wsFsMsg = wsFsAlu;
                    erro();
                    return;
                }
                wsNumeroE = Integer.parseInt(line.substring(0, 4).trim());
                wsNomeE = line.substring(4, 24).trim();
                wsSexoE = line.substring(24, 25).trim();
                wsIdadeE = Integer.parseInt(line.substring(25, 27).trim());
                wsCursoE = line.substring(27, 39).trim();
                wsNota1E = Double.parseDouble(line.substring(39, 44).trim().replace(',', '.'));
                wsNota2E = Double.parseDouble(line.substring(44, 49).trim().replace(',', '.'));
                wsMediaE = Double.parseDouble(line.substring(49, 54).trim().replace(',', '.'));

                wsCtlido++;

                if (wsMediaE < 7) {
                    wsAvaliacaoR = "REPROVADO";
                } else {
                    wsAvaliacaoR = "APROVADO ";
                }

                wsNumeroR = wsNumeroE;
                wsNomeR = wsNomeE;
                wsCursoR = wsCursoE;
                wsNota1R = wsNota1E;
                wsNota2R = wsNota2E;
                wsMediaR = wsMediaE;

                imprer();

                wsCtlin++;
            }

            reader.close();
            writer.close();

            terminar();

        } catch (IOException e) {
            wsMsg = "ERRO AO ABRIR OS ARQUIVOS";
            wsFsMsg = "IO";
            erro();
        }
    }

    private static void dataHora() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmssSS");
        wsDtsys = dateFormat.format(new Date());
        wsHrsys = timeFormat.format(new Date());
        wsDtedi = wsDtsys.substring(2, 4) + "/" + wsDtsys.substring(0, 2) + "/20" + wsDtsys.substring(4, 6);
        wsHredi = wsHrsys.substring(0, 2) + ":" + wsHrsys.substring(2, 4) + ":" + wsHrsys.substring(4, 6) + ":" + wsHrsys.substring(6, 8);
    }

    private static void imprer() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("RELALUJ", true));

        if (wsCtlin > MAX_LINES_PER_PAGE) {
            impcab(writer);
        }

        String regRelalu = String.format("%-1s%04d%-3s%-20s%-3s%-12s%-3s%,5.2f%-3s%,5.2f%-3s%,5.2f%-3s%-9s%-1s",
                " ", wsNumeroR, " ", wsNomeR, " ", wsCursoR, " ", wsNota1R, " ", wsNota2R, " ", wsMediaR, " ", wsAvaliacaoR, " ");
        writer.write(regRelalu);
        writer.newLine();

        wsCtimpr++;
        wsCtlin++;

        writer.close();
    }

    private static void impcab(BufferedWriter writer) throws IOException {
        wsPagCab++;

        String wsCab1 = String.format("%-1s%-10s%-4s%-38s%-15s%-4s%-5s%02d%-1s",
                " ", wsDataCab, " ", "RELATORIO DA AVALIACAO DOS ALUNOS DO: ", wsCursoCab, " ", "PAG. ", wsPagCab, " ");
        String wsCab2 = " NUM.    NOME                 CURSO         NOTA1    NOTA2    MEDIA    AVALIACAO ";

        writer.write(wsCab1);
        writer.newLine();
        writer.write(wsHifen);
        writer.newLine();
        writer.write(wsCab2);
        writer.newLine();
        writer.write(wsHifen);
        writer.newLine();

        wsCtlin = 4;
    }

    private static void terminar() {
        System.out.println(" *----------------------------------------*");
        System.out.println(" * TERMINO: " + wsDtedi + " AS " + wsHredi);
        System.out.println(" *----------------------------------------*");
        System.out.println(" *========================================*");
        System.out.println(" *   TOTAIS DE CONTROLE - CGPRG010        *");
        System.out.println(" *----------------------------------------*");
        System.out.println(" * REGISTROS LIDOS     - CADALU = " + wsCtlido);
        System.out.println(" * REGISTROS IMPRESSOS - RELALU = " + wsCtimpr);
        System.out.println(" *========================================*");

        System.out.println(" *----------------------------------------*");
        System.out.println(" *      TERMINO NORMAL DO CGPRG010        *");
        System.out.println(" *----------------------------------------*");
    }

    private static void erro() {
        System.out.println(" *----------------------------------------*");
        System.out.println(" *           PROGRAMA CANCELADO           *");
        System.out.println(" *----------------------------------------*");
        System.out.println(" * MENSAGEM    = " + wsMsg);
        System.out.println(" * FILE STATUS = " + wsFsMsg);
        System.out.println(" *----------------------------------------*");
        System.out.println(" *       TERMINO ANORMAL DO CGPRG010      *");
        System.out.println(" *----------------------------------------*");
        System.exit(1);
    }
}