import java.io.*;
import java.nio.file.*;

public class PG3 {
    static class RegAluno {
        String coAno;
        String coNumero;
        String coDigito;
        String nome;
        String sexo;
        double notaProva;
        
        public RegAluno() {
            this.coAno = "00";
            this.coNumero = "000";
            this.coDigito = "0";
            this.nome = "";
            this.sexo = "";
            this.notaProva = 0.0;
        }
        
        public void setMatric(String matric) {
            if (matric.length() >= 6) {
                this.coAno = matric.substring(0, 2);
                this.coNumero = matric.substring(2, 5);
                this.coDigito = matric.substring(5, 6);
            }
        }
        
        public String getMatric() {
            return coAno + coNumero + coDigito;
        }
        
        public byte[] toBytes() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-6s", getMatric()));
            sb.append(String.format("%-30s", nome));
            sb.append(sexo);
            sb.append(String.format("%04.2f", notaProva));
            return sb.toString().getBytes();
        }
    }
    
    static String stAlu = "00";
    static FileOutputStream arqcad;
    
    public static void main(String[] args) {
        inicio();
    }
    
    static void inicio() {
        abreArq();
        processo();
        finaliza();
    }
    
    static void abreArq() {
        try {
            arqcad = new FileOutputStream("CADASTR.DAT");
            stAlu = "00";
        } catch (IOException e) {
            System.out.println("ERRO DE ABERTURA - CAD ALUNO " + stAlu);
            System.exit(1);
        }
    }
    
    static void processo() {
        try {
            RegAluno reg = new RegAluno();
            
            reg.setMatric("180019");
            reg.nome = "JOAO DAS NEVES                ";
            reg.sexo = "M";
            reg.notaProva = 7.50;
            arqcad.write(reg.toBytes());
            arqcad.write('\n');
            
            reg.setMatric("180029");
            reg.nome = "MARIA JOAQUINA                ";
            reg.sexo = "F";
            reg.notaProva = 5.50;
            arqcad.write(reg.toBytes());
            arqcad.write('\n');
            
            reg.setMatric("180039");
            reg.nome = "MARIA MADALENA DE JESUS       ";
            reg.sexo = "F";
            reg.notaProva = 8.00;
            arqcad.write(reg.toBytes());
            arqcad.write('\n');
            
            reg.setMatric("180049");
            reg.nome = "ALBERT EINSTEN                ";
            reg.sexo = "M";
            reg.notaProva = 9.50;
            arqcad.write(reg.toBytes());
            arqcad.write('\n');
            
            reg.setMatric("180059");
            reg.nome = "JOAOZINHO DA SILVA            ";
            reg.sexo = "M";
            reg.notaProva = 2.00;
            arqcad.write(reg.toBytes());
            arqcad.write('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static void finaliza() {
        try {
            if (arqcad != null) {
                arqcad.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}