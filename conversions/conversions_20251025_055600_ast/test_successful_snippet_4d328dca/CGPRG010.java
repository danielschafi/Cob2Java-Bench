import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CGPRG010 {
    private static final int MAX_LINE_LENGTH = 80;
    private static final int MAX_PAGE_LINES = 13;

    private int wsCtlido;
    private int wsCtimpr;
    private int wsCtlin = 65;
    private String wsDtsys;
    private String wsHrsys;
    private String wsDtedi;
    private String wsHredi;
    private String wsFsAlu;
    private String wsFsRel;
    private String wsMsg;
    private String wsFsMsg;

    private int wsNumeroE;
    private String wsNomeE;
    private String wsSexoE;
    private int wsIdadeE;
    private String wsCursoE;
    private double wsNota1E;
    private double wsNota2E;
    private double wsMediaE;

    private String wsCursoCab;
    private int wsPagCab = 0;

    private String wsNumeroR;
    private String wsNomeR;
    private String wsCursoR;
    private String wsNota1R;
    private String wsNota2R;
    private String wsMediaR;
    private String wsAvaliacaoR;

    private String wsCab1;
    private String wsCab2;
    private String wsHifen;

    public static void main(String[] args) {
        CGPRG010 program = new CGPRG010();
        program.run();
    }

    private void run() {
        try {
            iniciar();
            processar();
            terminar();
        } catch (Exception e) {
            erro("ERRO INESPERADO", e.getMessage());
        }
    }

    private void iniciar() throws IOException {
        dataHora();

        System.out.println(" *----------------------------------------*");
        System.out.println(" * INICIO : " + wsDtedi + " AS " + wsHredi);
        System.out.println(" *----------------------------------------*");

        System.out.println(" *----------------------------------------*");
        System.out.println(" * PROGRAMA 10 - FATEC SCS                *");
        System.out.println(" * MIGUEL MORAIS                          *");
        System.out.println(" * JESSICA HOLANDA                        *");
        System.out.println(" *----------------------------------------*");

        abrirArquivos();

        lerCadalu();

        if ("10".equals(wsFsAlu)) {
            erro("ERRO - CADALU VAZIO", wsFsAlu);
        }

        Scanner scanner = new Scanner(System.in);
        wsCursoCab = scanner.nextLine();
        wsDtedi = wsDtedi;

        wsCab1 = String.format(" %10s RELATORIO DA AVALIACAO DOS ALUNOS DO: %15s PAG. %02d ", wsDtedi, wsCursoCab, wsPagCab);
        wsCab2 = " NUM.    NOME                 CURSO         NOTA1    NOTA2    MEDIA   AVALIACAO";
        wsHifen = "-".repeat(MAX_LINE_LENGTH);
    }

    private void dataHora() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmssSS");

        wsDtsys = dateFormat.format(new Date());
        wsDtedi = wsDtsys.substring(2, 4) + "/" + wsDtsys.substring(0, 2) + "/20" + wsDtsys.substring(4);

        wsHrsys = timeFormat.format(new Date());
        wsHredi = wsHrsys.substring(0, 2) + ":" + wsHrsys.substring(2, 4) + ":" + wsHrsys.substring(4, 6) + ":" + wsHrsys.substring(6);
    }

    private void abrirArquivos() throws FileNotFoundException {
        try {
            new BufferedReader(new FileReader("CADALUJ"));
        } catch (FileNotFoundException e) {
            erro("ERRO AO ABRIR O CADALU", e.getMessage());
        }

        try {
            new BufferedWriter(new FileWriter("RELALUJ"));
        } catch (IOException e) {
            erro("ERRO AO ABRIR O RELALU", e.getMessage());
        }
    }

    private void lerCadalu() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("CADALUJ"));
        String line = reader.readLine();
        wsFsAlu = line == null ? "10" : "00";

        if (!"00".equals(wsFsAlu) && !"10".equals(wsFsAlu)) {
            erro("ERRO NA LEITURA DO CADALU", wsFsAlu);
        } else if ("00".equals(wsFsAlu)) {
            wsCtlido++;
            String[] parts = line.split("(?<=\\G.{7})");
            wsNumeroE = Integer.parseInt(parts[0]);
            wsNomeE = parts[1].trim();
            wsSexoE = parts[2].trim();
            wsIdadeE = Integer.parseInt(parts[3]);
            wsCursoE = parts[4].trim();
            wsNota1E = Double.parseDouble(parts[5].replace(",", "."));
            wsNota2E = Double.parseDouble(parts[6].replace(",", "."));
            wsMediaE = Double.parseDouble(parts[7].replace(",", "."));
        }
    }

    private void processar() throws IOException {
        while (!"10".equals(wsFsAlu)) {
            if (wsMediaE < 7) {
                wsAvaliacaoR = "REPROVADO";
            } else {
                wsAvaliacaoR = "APROVADO ";
            }

            wsNumeroR = String.format("%04d", wsNumeroE);
            wsNomeR = String.format("%-20s", wsNomeE);
            wsCursoR = String.format("%-12s", wsCursoE);
            wsNota1R = String.format("%6.2f", wsNota1E).replace(".", ",");
            wsNota2R = String.format("%6.2f", wsNota2E).replace(".", ",");
            wsMediaR = String.format("%6.2f", wsMediaE).replace(".", ",");

            imprimirLinha();

            lerCadalu();
        }
    }

    private void imprimirLinha() throws IOException {
        if (wsCtlin > MAX_PAGE_LINES) {
            imprimirCabecalho();
        }

        String linha = String.format(" %s %s %s %s %s %s %s", wsNumeroR, wsNomeR, wsCursoR, wsNota1R, wsNota2R, wsMediaR, wsAvaliacaoR);
        BufferedWriter writer = new BufferedWriter(new FileWriter("RELALUJ", true));
        writer.write(linha);
        writer.newLine();
        writer.close();

        wsCtimpr++;
        wsCtlin++;
    }

    private void imprimirCabecalho() throws IOException {
        wsPagCab++;

        BufferedWriter writer = new BufferedWriter(new FileWriter("RELALUJ", true));
        writer.write(wsCab1);
        writer.newLine();
        writer.write(wsHifen);
        writer.newLine();
        writer.write(wsCab2);
        writer.newLine();
        writer.write(wsHifen);
        writer.newLine();
        writer.close();

        wsCtlin = 4;
    }

    private void terminar() throws IOException {
        dataHora();

        System.out.println(" *----------------------------------------*");
        System.out.println(" * TERMINO: " + wsDtedi + " AS " + wsHredi);
        System.out.println(" *----------------------------------------*");
        System.out.println(" *========================================*");
        System.out.println(" *   TOTAIS DE CONTROLE - CGPRG010        *");
        System.out.println(" *----------------------------------------*");
        System.out.println(" * REGISTROS LIDOS     - CADALU = " + wsCtlido);
        System.out.println(" * REGISTROS IMPRESSOS - RELALU = " + wsCtimpr);
        System.out.println(" *========================================*");

        fecharArquivos();

        System.out.println(" *----------------------------------------*");
        System.out.println(" *      TERMINO NORMAL DO CGPRG010        *");
        System.out.println(" *----------------------------------------*");
    }

    private void fecharArquivos() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("CADALUJ"));
            reader.close();
        } catch (IOException e) {
            erro("ERRO AO FECHAR O CADALU", e.getMessage());
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("RELALUJ", true));
            writer.close();
        } catch (IOException e) {
            erro("ERRO AO FECHAR O RELALU", e.getMessage());
        }
    }

    private void erro(String mensagem, String fileStatus) {
        System.out.println(" *----------------------------------------*");
        System.out.println(" *           PROGRAMA CANCELADO           *");
        System.out.println(" *----------------------------------------*");
        System.out.println(" * MENSAGEM    = " + mensagem);
        System.out.println(" * FILE STATUS = " + fileStatus);
        System.out.println(" *----------------------------------------*");
        System.out.println(" *       TERMINO ANORMAL DO CGPRG010      *");
        System.out.println(" *----------------------------------------*");
        System.exit(1);
    }
}