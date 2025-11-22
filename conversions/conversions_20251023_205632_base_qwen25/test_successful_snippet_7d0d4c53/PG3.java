import java.io.*;
import java.nio.file.*;
import java.text.DecimalFormat;

public class PG3 {
    private static final String ARQCAD = "CADASTR.DAT";
    private static final String CADHOMEM = "CADHOME.DAT";
    private static final String CADMULHE = "CADMULH.DAT";

    private static String stAlu;
    private static String stHom;
    private static String stMul;
    private static double mediaM;
    private static double mediaH;
    private static int totalH;
    private static int totalM;
    private static double totPrH;
    private static double totPrM;
    private static DecimalFormat edicaoM = new DecimalFormat("00,00");
    private static DecimalFormat edicaoH = new DecimalFormat("00,00");

    public static void main(String[] args) {
        try {
            abreArq();
            processa();
            finaliza();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void abreArq() throws IOException {
        if (!Files.exists(Paths.get(ARQCAD))) {
            System.out.println("ERRO DE ABERTURA - CAD ALUNO");
            System.exit(1);
        }
        if (!Files.exists(Paths.get(CADHOMEM))) {
            System.out.println("ERRO DE ABERTURA - CAD HOMEM");
        }
        if (!Files.exists(Paths.get(CADMULHE))) {
            System.out.println("ERRO DE ABERTURA - CAD MULHE");
        }
    }

    private static void processa() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQCAD));
             BufferedWriter writerHom = new BufferedWriter(new FileWriter(CADHOMEM));
             BufferedWriter writerMul = new BufferedWriter(new FileWriter(CADMULHE))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() < 41) continue;

                String coAno = line.substring(0, 2);
                String coNumero = line.substring(2, 5);
                String coDigito = line.substring(5, 6);
                String nome = line.substring(6, 36).trim();
                String sexo = line.substring(36, 37);
                String notaProvaStr = line.substring(37, 41).replace(',', '.');
                double notaProva = Double.parseDouble(notaProvaStr);

                if (sexo.equals("F")) {
                    gravaMulher(writerMul, coAno, coNumero, coDigito, nome, sexo, notaProva);
                } else {
                    gravaHomem(writerHom, coAno, coNumero, coDigito, nome, sexo, notaProva);
                }
            }

            mostraTotal();
        }
    }

    private static void gravaMulher(BufferedWriter writer, String coAno, String coNumero, String coDigito, String nome, String sexo, double notaProva) throws IOException {
        totalM++;
        totPrM += notaProva;
        writer.write(String.format("%02s%03s%1s%30s%1s%05.2f", coAno, coNumero, coDigito, nome, sexo, notaProva).replace('.', ','));
        writer.newLine();
    }

    private static void gravaHomem(BufferedWriter writer, String coAno, String coNumero, String coDigito, String nome, String sexo, double notaProva) throws IOException {
        totalH++;
        totPrH += notaProva;
        writer.write(String.format("%02s%03s%1s%30s%1s%05.2f", coAno, coNumero, coDigito, nome, sexo, notaProva).replace('.', ','));
        writer.newLine();
    }

    private static void mostraTotal() {
        if (totalM > 0) {
            mediaM = totPrM / totalM;
        }
        if (totalH > 0) {
            mediaH = totPrH / totalH;
        }

        System.out.println(" MEDIA FINAL SEM EDICAO MULHER =" + mediaM);
        System.out.println(" MEDIA FINAL SEM EDICAO HOMEM  =" + mediaH);
        System.out.println(" MEDIA FINAL MULHER =" + edicaoM.format(mediaM));
        System.out.println(" MEDIA FINAL HOMEM  =" + edicaoH.format(mediaH));
    }

    private static void finaliza() {
        // Files are automatically closed by try-with-resources
    }
}