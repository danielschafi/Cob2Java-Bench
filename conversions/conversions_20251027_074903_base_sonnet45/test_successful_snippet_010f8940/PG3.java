import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class PG3 {
    private static final String FILENAME = "CADASTR.DAT";
    private FileOutputStream fileOutputStream;
    private String stAlu;
    
    private static class RegAluno {
        String coAno;
        String coNumero;
        String coDigito;
        String nome;
        String sexo;
        String notaProva;
        
        public RegAluno() {
            this.coAno = "00";
            this.coNumero = "000";
            this.coDigito = "0";
            this.nome = "                              ";
            this.sexo = " ";
            this.notaProva = "0000";
        }
        
        public void setMatric(String matric) {
            if (matric.length() >= 6) {
                this.coAno = matric.substring(0, 2);
                this.coNumero = matric.substring(2, 5);
                this.coDigito = matric.substring(5, 6);
            }
        }
        
        public void setNome(String nome) {
            this.nome = String.format("%-30s", nome).substring(0, 30);
        }
        
        public void setSexo(String sexo) {
            this.sexo = sexo.substring(0, 1);
        }
        
        public void setNotaProva(double nota) {
            int notaInt = (int) Math.round(nota * 100);
            this.notaProva = String.format("%04d", notaInt);
        }
        
        public byte[] toBytes() {
            StringBuilder sb = new StringBuilder();
            sb.append(coAno);
            sb.append(coNumero);
            sb.append(coDigito);
            sb.append(nome);
            sb.append(sexo);
            sb.append(notaProva);
            return sb.toString().getBytes(StandardCharsets.ISO_8859_1);
        }
    }
    
    private RegAluno regAluno;
    
    public PG3() {
        this.regAluno = new RegAluno();
        this.stAlu = "00";
    }
    
    public void inicio() {
        abreArq();
        processo();
        finaliza();
    }
    
    private void abreArq() {
        try {
            fileOutputStream = new FileOutputStream(FILENAME);
            stAlu = "00";
        } catch (IOException e) {
            stAlu = "35";
            System.out.println("ERRO DE ABERTURA - CAD ALUNO" + stAlu);
            System.exit(1);
        }
        
        if (!stAlu.equals("00")) {
            System.out.println("ERRO DE ABERTURA - CAD ALUNO" + stAlu);
            System.exit(1);
        }
    }
    
    private void processo() {
        regAluno.setMatric("180019");
        regAluno.setNome("JOAO DAS NEVES                ");
        regAluno.setSexo("M");
        regAluno.setNotaProva(7.50);
        writeRegAluno();
        
        regAluno.setMatric("180029");
        regAluno.setNome("MARIA JOAQUINA                ");
        regAluno.setSexo("F");
        regAluno.setNotaProva(5.50);
        writeRegAluno();
        
        regAluno.setMatric("180039");
        regAluno.setNome("MARIA MADALENA DE JESUS       ");
        regAluno.setSexo("F");
        regAluno.setNotaProva(8.00);
        writeRegAluno();
        
        regAluno.setMatric("180049");
        regAluno.setNome("ALBERT EINSTEN                ");
        regAluno.setSexo("M");
        regAluno.setNotaProva(9.50);
        writeRegAluno();
        
        regAluno.setMatric("180059");
        regAluno.setNome("JOAOZINHO DA SILVA            ");
        regAluno.setSexo("M");
        regAluno.setNotaProva(2.00);
        writeRegAluno();
    }
    
    private void writeRegAluno() {
        try {
            fileOutputStream.write(regAluno.toBytes());
        } catch (IOException e) {
            System.out.println("ERRO DE ESCRITA");
            System.exit(1);
        }
    }
    
    private void finaliza() {
        try {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (IOException e) {
            System.out.println("ERRO AO FECHAR ARQUIVO");
        }
    }
    
    public static void main(String[] args) {
        PG3 program = new PG3();
        program.inicio();
    }
}