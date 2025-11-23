import java.io.*;
import java.util.*;

public class PG3 {
    static class RegistroAluno {
        String matric;
        String nome;
        String sexo;
        double notaProva;
    }

    static class RegistroHomem {
        String matricH;
        String nomeH;
        String sexoH;
        double notaProvaH;
    }

    static class RegistroMulher {
        String matricM;
        String nomeM;
        String sexoM;
        double notaProvaM;
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("CADASTR.DAT"));
            BufferedWriter writerHomem = new BufferedWriter(new FileWriter("CADHOME.DAT"));
            BufferedWriter writerMulher = new BufferedWriter(new FileWriter("CADMULH.DAT"));

            String line;
            int fimArq = 0;
            int totalH = 0;
            int totalM = 0;
            double totPrH = 0.0;
            double totPrM = 0.0;

            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) continue;
                
                RegistroAluno regAluno = new RegistroAluno();
                regAluno.matric = line.substring(0, 6);
                regAluno.nome = line.substring(6, 36).trim();
                regAluno.sexo = line.substring(36, 37);
                regAluno.notaProva = Double.parseDouble(line.substring(37, 41) + "." + line.substring(41, 43));

                if (regAluno.sexo.equals("F")) {
                    totalM++;
                    totPrM += regAluno.notaProva;
                    
                    RegistroMulher regMulher = new RegistroMulher();
                    regMulher.matricM = regAluno.matric;
                    regMulher.nomeM = regAluno.nome;
                    regMulher.sexoM = regAluno.sexo;
                    regMulher.notaProvaM = regAluno.notaProva;
                    
                    writerMulher.write(regMulher.matricM + 
                                      String.format("%-30s", regMulher.nomeM) + 
                                      regMulher.sexoM + 
                                      String.format("%.2f", regMulher.notaProvaM));
                    writerMulher.newLine();
                } else {
                    totalH++;
                    totPrH += regAluno.notaProva;
                    
                    RegistroHomem regHomem = new RegistroHomem();
                    regHomem.matricH = regAluno.matric;
                    regHomem.nomeH = regAluno.nome;
                    regHomem.sexoH = regAluno.sexo;
                    regHomem.notaProvaH = regAluno.notaProva;
                    
                    writerHomem.write(regHomem.matricH + 
                                     String.format("%-30s", regHomem.nomeH) + 
                                     regHomem.sexoH + 
                                     String.format("%.2f", regHomem.notaProvaH));
                    writerHomem.newLine();
                }
            }

            reader.close();

            double mediaM = (totalM > 0) ? totPrM / totalM : 0.0;
            double mediaH = (totalH > 0) ? totPrH / totalH : 0.0;

            System.out.println(" MEDIA FINAL SEM EDICAO MULHER =" + String.format("%.2f", mediaM));
            System.out.println(" MEDIA FINAL SEM EDICAO HOMEM  =" + String.format("%.2f", mediaH));
            System.out.println(" MEDIA FINAL MULHER =" + String.format("%.2f", mediaM));
            System.out.println(" MEDIA FINAL HOMEM  =" + String.format("%.2f", mediaH));

            writerHomem.close();
            writerMulher.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}