import java.io.*;
import java.nio.file.*;

public class PG3 {
    static class Student {
        String matric;
        String nome;
        String sexo;
        double notaProva;
    }

    static String stAlu = "00";
    static Student regAluno = new Student();
    static PrintWriter writer;

    public static void main(String[] args) {
        inicio();
    }

    static void inicio() {
        abreArq();
        processo();
        finaliza();
        System.exit(0);
    }

    static void abreArq() {
        try {
            writer = new PrintWriter(new FileWriter("CADASTR.DAT"));
            stAlu = "00";
        } catch (IOException e) {
            System.out.println("ERRO DE ABERTURA - CAD ALUNO" + stAlu);
            System.exit(1);
        }

        if (!stAlu.equals("00")) {
            System.out.println("ERRO DE ABERTURA - CAD ALUNO" + stAlu);
            System.exit(1);
        }
    }

    static void processo() {
        regAluno.matric = "180019";
        regAluno.nome = "JOAO DAS NEVES                ";
        regAluno.sexo = "M";
        regAluno.notaProva = 7.50;
        writeRecord();

        regAluno.matric = "180029";
        regAluno.nome = "MARIA JOAQUINA                ";
        regAluno.sexo = "F";
        regAluno.notaProva = 5.50;
        writeRecord();

        regAluno.matric = "180039";
        regAluno.nome = "MARIA MADALENA DE JESUS       ";
        regAluno.sexo = "F";
        regAluno.notaProva = 8.00;
        writeRecord();

        regAluno.matric = "180049";
        regAluno.nome = "ALBERT EINSTEN                ";
        regAluno.sexo = "M";
        regAluno.notaProva = 9.50;
        writeRecord();

        regAluno.matric = "180059";
        regAluno.nome = "JOAOZINHO DA SILVA            ";
        regAluno.sexo = "M";
        regAluno.notaProva = 2.00;
        writeRecord();
    }

    static void writeRecord() {
        writer.println(regAluno.matric + "|" + regAluno.nome + "|" + regAluno.sexo + "|" + regAluno.notaProva);
    }

    static void finaliza() {
        if (writer != null) {
            writer.close();
        }
    }
}