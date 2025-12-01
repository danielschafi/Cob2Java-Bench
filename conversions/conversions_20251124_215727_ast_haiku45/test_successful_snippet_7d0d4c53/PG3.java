import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class PG3 {
    static class StudentRecord {
        int coAno;
        int coNumero;
        int coDigito;
        String nome;
        String sexo;
        double notaProva;
    }

    static String stAlu = "00";
    static String stHom = "00";
    static String stMul = "00";
    static int fimArq = 0;
    static double mediaM = 0;
    static double mediaH = 0;
    static int totalH = 0;
    static int totalM = 0;
    static double totprH = 0;
    static double totprM = 0;
    static String edicaoM = "";
    static String edicaoH = "";

    static StudentRecord regAluno = new StudentRecord();
    static StudentRecord regCadH = new StudentRecord();
    static StudentRecord regCadM = new StudentRecord();

    static BufferedReader arqcadReader;
    static PrintWriter cadhomemWriter;
    static PrintWriter cadmulheWriter;

    public static void main(String[] args) {
        abreArq();
        fimArq = 0;
        totalH = 0;
        totalM = 0;
        totprH = 0;
        totprM = 0;
        processo();
        finaliza();
    }

    static void abreArq() {
        try {
            arqcadReader = new BufferedReader(new FileReader("CADASTR.DAT"));
            stAlu = "00";
        } catch (IOException e) {
            System.out.println("ERRO DE ABERTURA - CAD ALUNO " + stAlu);
            System.exit(1);
        }

        try {
            cadhomemWriter = new PrintWriter(new FileWriter("CADHOME.DAT"));
            stHom = "00";
        } catch (IOException e) {
            System.out.println("ERRO DE ABERTURA - CAD HOMEM " + stHom);
        }

        try {
            cadmulheWriter = new PrintWriter(new FileWriter("CADMULH.DAT"));
            stMul = "00";
        } catch (IOException e) {
            System.out.println("ERRO DE ABERTURA - CAD MULHE " + stMul);
        }
    }

    static void processo() {
        lerArqRecord();
        while (fimArq == 0) {
            lerArq();
        }
        mostraTotal();
    }

    static void lerArqRecord() {
        try {
            String line = arqcadReader.readLine();
            if (line == null) {
                fimArq = 1;
            } else {
                parseRecord(line, regAluno);
            }
        } catch (IOException e) {
            fimArq = 1;
        }
    }

    static void lerArq() {
        if (regAluno.sexo.equals("F")) {
            gravaMulher();
        } else {
            gravaHomem();
        }
        lerArqRecord();
    }

    static void gravaMulher() {
        totalM++;
        totprM += regAluno.notaProva;
        regCadM = copyRecord(regAluno);
        try {
            cadmulheWriter.println(formatRecord(regCadM));
        } catch (Exception e) {
        }
    }

    static void gravaHomem() {
        totalH++;
        totprH += regAluno.notaProva;
        regCadH = copyRecord(regAluno);
        try {
            cadhomemWriter.println(formatRecord(regCadH));
        } catch (Exception e) {
        }
    }

    static void mostraTotal() {
        if (totalM > 0) {
            mediaM = totprM / totalM;
        }
        if (totalH > 0) {
            mediaH = totprH / totalH;
        }

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("0.00", symbols);

        edicaoM = df.format(mediaM);
        edicaoH = df.format(mediaH);

        System.out.println(" MEDIA FINAL SEM EDICAO MULHER =" + mediaM);
        System.out.println(" MEDIA FINAL SEM EDICAO HOMEM  =" + mediaH);
        System.out.println(" MEDIA FINAL MULHER =" + edicaoM);
        System.out.println(" MEDIA FINAL HOMEM  =" + edicaoH);
    }

    static void finaliza() {
        try {
            if (arqcadReader != null) arqcadReader.close();
            if (cadhomemWriter != null) cadhomemWriter.close();
            if (cadmulheWriter != null) cadmulheWriter.close();
        } catch (IOException e) {
        }
    }

    static void parseRecord(String line, StudentRecord record) {
        try {
            if (line.length() >= 36) {
                record.coAno = Integer.parseInt(line.substring(0, 2));
                record.coNumero = Integer.parseInt(line.substring(2, 5));
                record.coDigito = Integer.parseInt(line.substring(5, 6));
                record.nome = line.substring(6, 36).trim();
                record.sexo = line.substring(36, 37);
                String notaStr = line.substring(37);
                if (notaStr.length() >= 4) {
                    int intPart = Integer.parseInt(notaStr.substring(0, 2));
                    int decPart = Integer.parseInt(notaStr.substring(2, 4));
                    record.notaProva = intPart + decPart / 100.0;
                }
            }
        } catch (Exception e) {
        }
    }

    static StudentRecord copyRecord(StudentRecord source) {
        StudentRecord dest = new StudentRecord();
        dest.coAno = source.coAno;
        dest.coNumero = source.coNumero;
        dest.coDigito = source.coDigito;
        dest.nome = source.nome;
        dest.sexo = source.sexo;
        dest.notaProva = source.notaProva;
        return dest;
    }

    static String formatRecord(StudentRecord record) {
        String ano = String.format("%02d", record.coAno);
        String numero = String.format("%03d", record.coNumero);
        String digito = String.format("%01d", record.coDigito);
        String nome = String.format("%-30s", record.nome);
        int intPart = (int) record.notaProva;
        int decPart = (int) ((record.notaProva - intPart) * 100);
        String nota = String.format("%02d%02d", intPart, decPart);
        return ano + numero + digito + nome + record.sexo + nota;
    }
}