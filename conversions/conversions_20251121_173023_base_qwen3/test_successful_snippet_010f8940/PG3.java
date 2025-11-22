import java.io.*;
import java.nio.file.*;

public class PG3 {
    private static final String ARQCAD_PATH = "CADASTR.DAT";
    
    public static void main(String[] args) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(ARQCAD_PATH));
            
            writeStudent(writer, "180019", "JOAO DAS NEVES                ", "M", 7.50);
            writeStudent(writer, "180029", "MARIA JOAQUINA                ", "F", 5.50);
            writeStudent(writer, "180039", "MARIA MADALENA DE JESUS       ", "F", 8.00);
            writeStudent(writer, "180049", "ALBERT EINSTEN                ", "M", 9.50);
            writeStudent(writer, "180059", "JOAOZINHO DA SILVA            ", "M", 2.00);
            
            writer.close();
        } catch (IOException e) {
            System.err.println("ERRO DE ABERTURA - CAD ALUNO");
            System.exit(1);
        }
    }
    
    private static void writeStudent(PrintWriter writer, String matric, String nome, String sexo, double notaProva) {
        StringBuilder record = new StringBuilder();
        
        // MATRIC
        record.append(String.format("%-6s", matric));
        
        // NOME (30 caracteres)
        record.append(String.format("%-30s", nome));
        
        // SEXO (1 caractere)
        record.append(sexo);
        
        // NOTA-PROVA (formatada como 9(02)V99)
        record.append(String.format("%05.2f", notaProva));
        
        writer.println(record.toString());
    }
}