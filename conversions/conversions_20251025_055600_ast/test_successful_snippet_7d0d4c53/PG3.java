import java.io.*;
import java.text.DecimalFormat;

public class PG3 {
    private static final String ARQCAD = "CADASTR.DAT";
    private static final String CADHOMEM = "CADHOME.DAT";
    private static final String CADMULHE = "CADMULH.DAT";

    private static int fimArq;
    private static String stAlu;
    private static String stHom;
    private static String stMul;
    private static double mediaM;
    private static double mediaH;
    private static int totalH;
    private static int totalM;
    private static double totPrH;
    private static double totPrM;
    private static DecimalFormat edicaoM;
    private static DecimalFormat edicaoH;

    public static void main(String[] args) {
        abreArq();
        inicializaDados();
        processo();
        finaliza();
    }

    private static void abreArq() {
        try (RandomAccessFile arqCad = new RandomAccessFile(ARQCAD, "r");
             RandomAccessFile cadHom = new RandomAccessFile(CADHOMEM, "rw");
             RandomAccessFile cadMul = new RandomAccessFile(CADMULHE, "rw")) {

            stAlu = "";
            stHom = "";
            stMul = "";

        } catch (FileNotFoundException e) {
            if (!stAlu.equals("00")) {
                System.out.println("ERRO DE ABERTURA - CAD ALUNO " + stAlu);
                System.exit(1);
            }
            if (!stHom.equals("00")) {
                System.out.println("ERRO DE ABERTURA - CAD HOMEM " + stHom);
            }
            if (!stMul.equals("00")) {
                System.out.println("ERRO DE ABERTURA - CAD MULHE " + stMul);
            }
        }
    }

    private static void inicializaDados() {
        fimArq = 0;
        stAlu = "00";
        stHom = "00";
        stMul = "00";
        mediaM = 0.0;
        mediaH = 0.0;
        totalH = 0;
        totalM = 0;
        totPrH = 0.0;
        totPrM = 0.0;
        edicaoM = new DecimalFormat("0,00");
        edicaoH = new DecimalFormat("0,00");
    }

    private static void processo() {
        try (RandomAccessFile arqCad = new RandomAccessFile(ARQCAD, "r");
             RandomAccessFile cadHom = new RandomAccessFile(CADHOMEM, "rw");
             RandomAccessFile cadMul = new RandomAccessFile(CADMULHE, "rw")) {

            lerArq(arqCad, cadHom, cadMul);
            mostraTotal();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void lerArq(RandomAccessFile arqCad, RandomAccessFile cadHom, RandomAccessFile cadMul) throws IOException {
        while (fimArq == 0) {
            String linha = arqCad.readLine();
            if (linha == null) {
                fimArq = 1;
                break;
            }

            String sexo = linha.substring(33, 34);
            if (sexo.equals("F")) {
                gravaMulher(linha, cadMul);
            } else {
                gravaHomem(linha, cadHom);
            }
        }
    }

    private static void gravaMulher(String linha, RandomAccessFile cadMul) throws IOException {
        totalM++;
        double notaProva = Double.parseDouble(linha.substring(34, 38).replace(',', '.'));
        totPrM += notaProva;
        cadMul.writeBytes(linha + "\n");
    }

    private static void gravaHomem(String linha, RandomAccessFile cadHom) throws IOException {
        totalH++;
        double notaProva = Double.parseDouble(linha.substring(34, 38).replace(',', '.'));
        totPrH += notaProva;
        cadHom.writeBytes(linha + "\n");
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
        try (RandomAccessFile arqCad = new RandomAccessFile(ARQCAD, "r");
             RandomAccessFile cadHom = new RandomAccessFile(CADHOMEM, "rw");
             RandomAccessFile cadMul = new RandomAccessFile(CADMULHE, "rw")) {

            arqCad.close();
            cadHom.close();
            cadMul.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}