import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PG3 {
    private static String stAlu = "00";

    public static void main(String[] args) {
        abreArq();
        processo();
        finaliza();
    }

    private static void abreArq() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("CADASTR.DAT"))) {
            // No need to check file status in Java as exceptions are used
        } catch (IOException e) {
            System.out.println("ERRO DE ABERTURA - CAD ALUNO " + stAlu);
            System.exit(1);
        }
    }

    private static void processo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("CADASTR.DAT", true))) {
            writer.write(formatRecord("180019", "JOAO DAS NEVES                ", "M", 7.50));
            writer.write(formatRecord("180029", "MARIA JOAQUINA                ", "F", 5.50));
            writer.write(formatRecord("180039", "MARIA MADALENA DE JESUS       ", "F", 8.00));
            writer.write(formatRecord("180049", "ALBERT EINSTEN                ", "M", 9.50));
            writer.write(formatRecord("180059", "JOAOZINHO DA SILVA            ", "M", 2.00));
        } catch (IOException e) {
            System.out.println("ERRO DE ESCRITA - CAD ALUNO");
            System.exit(1);
        }
    }

    private static void finaliza() {
        // In Java, closing the file is handled by the try-with-resources statement
    }

    private static String formatRecord(String matric, String nome, String sexo, double notaProva) {
        return String.format("%s%s%s%s%.2f%n", matric, nome, sexo, " ", notaProva);
    }
}