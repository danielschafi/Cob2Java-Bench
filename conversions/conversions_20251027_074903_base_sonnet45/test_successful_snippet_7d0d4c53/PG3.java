import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class PG3 {
    
    private static class RegAluno {
        int coAno;
        int coNumero;
        int coDigito;
        String nome;
        String sexo;
        double notaProva;
        
        public String toFixedString() {
            return String.format("%02d%03d%01d%-30s%-1s%04d",
                coAno, coNumero, coDigito, nome, sexo, (int)(notaProva * 100));
        }
        
        public void fromFixedString(String line) {
            coAno = Integer.parseInt(line.substring(0, 2));
            coNumero = Integer.parseInt(line.substring(2, 5));
            coDigito = Integer.parseInt(line.substring(5, 6));
            nome = line.substring(6, 36);
            sexo = line.substring(36, 37);
            notaProva = Integer.parseInt(line.substring(37, 41)) / 100.0;
        }
    }
    
    private BufferedReader arqcad;
    private BufferedWriter cadhomem;
    private BufferedWriter cadmulhe;
    
    private int fimArq;
    private String stAlu;
    private String stHom;
    private String stMul;
    private double mediaM;
    private double mediaH;
    private int totalH;
    private int totalM;
    private double totprH;
    private double totprM;
    
    private RegAluno regAluno;
    
    public static void main(String[] args) {
        PG3 program = new PG3();
        program.inicio();
    }
    
    private void inicio() {
        abreArq();
        moveZerosToWsDados();
        processo();
        finaliza();
    }
    
    private void abreArq() {
        try {
            arqcad = new BufferedReader(new FileReader("CADASTR.DAT"));
            stAlu = "00";
        } catch (IOException e) {
            stAlu = "35";
            System.out.println("ERRO DE ABERTURA - CAD ALUNO" + stAlu);
            System.exit(1);
        }
        
        try {
            cadhomem = new BufferedWriter(new FileWriter("CADHOME.DAT"));
            stHom = "00";
        } catch (IOException e) {
            stHom = "35";
            System.out.println("ERRO DE ABERTURA - CAD HOMEM" + stHom);
        }
        
        try {
            cadmulhe = new BufferedWriter(new FileWriter("CADMULH.DAT"));
            stMul = "00";
        } catch (IOException e) {
            stMul = "35";
            System.out.println("ERRO DE ABERTURA - CAD MULHE" + stMul);
        }
    }
    
    private void moveZerosToWsDados() {
        fimArq = 0;
        mediaM = 0.0;
        mediaH = 0.0;
        totalH = 0;
        totalM = 0;
        totprH = 0.0;
        totprM = 0.0;
        regAluno = new RegAluno();
    }
    
    private void processo() {
        readArqcad();
        while (fimArq != 1) {
            lerArq();
        }
        mostraTotal();
    }
    
    private void readArqcad() {
        try {
            String line = arqcad.readLine();
            if (line == null) {
                fimArq = 1;
            } else {
                regAluno.fromFixedString(line);
            }
        } catch (IOException e) {
            fimArq = 1;
        }
    }
    
    private void lerArq() {
        if (regAluno.sexo.equals("F")) {
            gravaMulher();
        } else {
            gravaHomem();
        }
        readArqcad();
    }
    
    private void gravaMulher() {
        totalM += 1;
        totprM += regAluno.notaProva;
        try {
            cadhomem.write(regAluno.toFixedString());
            cadmulhe.newLine();
        } catch (IOException e) {
            // Handle write error
        }
    }
    
    private void gravaHomem() {
        totalH += 1;
        totprH += regAluno.notaProva;
        try {
            cadhomem.write(regAluno.toFixedString());
            cadhomem.newLine();
        } catch (IOException e) {
            // Handle write error
        }
    }
    
    private void mostraTotal() {
        if (totalM > 0) {
            mediaM = totprM / totalM;
        }
        if (totalH > 0) {
            mediaH = totprH / totalH;
        }
        
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat(" #0,00", symbols);
        
        String edicaoM = df.format(mediaM);
        String edicaoH = df.format(mediaH);
        
        System.out.println(" MEDIA FINAL SEM EDICAO MULHER =" + String.format("%05.2f", mediaM).replace('.', ','));
        System.out.println(" MEDIA FINAL SEM EDICAO HOMEM  =" + String.format("%05.2f", mediaH).replace('.', ','));
        System.out.println(" MEDIA FINAL MULHER =" + edicaoM);
        System.out.println(" MEDIA FINAL HOMEM  =" + edicaoH);
    }
    
    private void finaliza() {
        try {
            if (arqcad != null) arqcad.close();
            if (cadhomem != null) cadhomem.close();
            if (cadmulhe != null) cadmulhe.close();
        } catch (IOException e) {
            // Handle close error
        }
    }
}