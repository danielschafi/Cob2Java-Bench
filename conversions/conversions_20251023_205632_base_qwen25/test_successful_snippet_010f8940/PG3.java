import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PG3 {
    public static void main(String[] args) {
        String stAlu = "";
        try (BufferedWriter arqCad = new BufferedWriter(new FileWriter("CADASTR.DAT"))) {
            abreArq(arqCad, stAlu);
            processo(arqCad);
            finaliza(arqCad);
        } catch (IOException e) {
            System.out.println("ERRO DE ABERTURA - CAD ALUNO " + stAlu);
            System.exit(1);
        }
    }

    private static void abreArq(BufferedWriter arqCad, String stAlu) throws IOException {
        if (arqCad == null) {
            throw new IOException("Erro na abertura do arquivo");
        }
    }

    private static void processo(BufferedWriter arqCad) throws IOException {
        escreveRegistro(arqCad, "180019", "JOAO DAS NEVES                ", "M", 7.50);
        escreveRegistro(arqCad, "180029", "MARIA JOAQUINA                ", "F", 5.50);
        escreveRegistro(arqCad, "180039", "MARIA MADALENA DE JESUS       ", "F", 8.00);
        escreveRegistro(arqCad, "180049", "ALBERT EINSTEN                ", "M", 9.50);
        escreveRegistro(arqCad, "180059", "JOAOZINHO DA SILVA            ", "M", 2.00);
    }

    private static void escreveRegistro(BufferedWriter arqCad, String matric, String nome, String sexo, double notaProva) throws IOException {
        arqCad.write(String.format("%s%s%s%05.2f", matric, nome, sexo, notaProva));
        arqCad.newLine();
    }

    private static void finaliza(BufferedWriter arqCad) throws IOException {
        arqCad.close();
    }
}