import java.io.*;
import java.util.*;

public class PG3 {
    static class RegAluno {
        String coAno;
        String coNumero;
        String coDigito;
        String nome;
        String sexo;
        String notaProva;
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

    static BufferedReader arqcad;
    static PrintWriter cadhomem;
    static PrintWriter cadmulhe;
    static RegAluno regAluno;

    public static void main(String[] args) {
        abreArq();
        processo();
        finaliza();
    }

    static void abreArq() {
        try {
            arqcad = new BufferedReader(new FileReader("CADASTR.DAT"));
            stAlu = "00";
        } catch (IOException e) {
            System.out.println("ERRO DE ABERTURA - CAD ALUNO " + stAlu);
            System.exit(1);
        }

        try {
            cadhomem = new PrintWriter(new FileWriter("CADHOME.DAT"));
            stHom = "00";
        } catch (IOException e) {
            System.out.println("ERRO DE ABERTURA - CAD HOMEM " + stHom);
        }

        try {
            cadmulhe = new PrintWriter(new FileWriter("CADMULH.DAT"));
            stMul = "00";
        } catch (IOException e) {
            System.out.println("ERRO DE ABERTURA - CAD MULHE " + stMul);
        }
    }

    static void processo() {
        lerArq();
        while (fimArq == 0) {
            lerArq();
        }
        mostraTotal();
    }

    static void lerArq() {
        try {
            String linha = arqcad.readLine();
            if (linha == null) {
                fimArq = 1;
                return;
            }

            regAluno = parseRegAluno(linha);

            if (regAluno.sexo.equals("F")) {
                gravaMulther();
            } else {
                gravaHomem();
            }
        } catch (IOException e) {
            fimArq = 1;
        }
    }

    static RegAluno parseRegAluno(String linha) {
        RegAluno reg = new RegAluno();
        if (linha.length() >= 36) {
            reg.coAno = linha.substring(0, 2);
            reg.coNumero = linha.substring(2, 5);
            reg.coDigito = linha.substring(5, 6);
            reg.nome = linha.substring(6, 36);
            reg.sexo = linha.substring(36, 37);
            if (linha.length() >= 41) {
                reg.notaProva = linha.substring(37, 41);
            } else {
                reg.notaProva = "0000";
            }
        }
        return reg;
    }

    static void gravaMulther() {
        totalM += 1;
        double nota = parseNota(regAluno.notaProva);
        totprM += nota;
        String linha = String.format("%s%s%s%-30s%s%s",
                regAluno.coAno,
                regAluno.coNumero,
                regAluno.coDigito,
                regAluno.nome,
                regAluno.sexo,
                regAluno.notaProva);
        cadmulhe.println(linha);
    }

    static void gravaHomem() {
        totalH += 1;
        double nota = parseNota(regAluno.notaProva);
        totprH += nota;
        String linha = String.format("%s%s%s%-30s%s%s",
                regAluno.coAno,
                regAluno.coNumero,
                regAluno.coDigito,
                regAluno.nome,
                regAluno.sexo,
                regAluno.notaProva);
        cadhomem.println(linha);
    }

    static double parseNota(String notaStr) {
        if (notaStr == null || notaStr.length() < 4) {
            return 0.0;
        }
        String intPart = notaStr.substring(0, 2);
        String decPart = notaStr.substring(2, 4);
        try {
            return Double.parseDouble(intPart + "." + decPart);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    static void mostraTotal() {
        if (totalM > 0) {
            mediaM = totprM / totalM;
        }
        if (totalH > 0) {
            mediaH = totprH / totalH;
        }

        edicaoM = String.format("%2.2f", mediaM).replace(".", ",");
        edicaoH = String.format("%2.2f", mediaH).replace(".", ",");

        System.out.println(" MEDIA FINAL SEM EDICAO MULHER =" + mediaM);
        System.out.println(" MEDIA FINAL SEM EDICAO HOMEM  =" + mediaH);
        System.out.println(" MEDIA FINAL MULHER =" + edicaoM);
        System.out.println(" MEDIA FINAL HOMEM  =" + edicaoH);
    }

    static void finaliza() {
        try {
            if (arqcad != null) arqcad.close();
            if (cadhomem != null) cadhomem.close();
            if (cadmulhe != null) cadmulhe.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}