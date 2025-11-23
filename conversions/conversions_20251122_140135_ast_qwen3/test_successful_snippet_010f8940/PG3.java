import java.io.*;

public class PG3 {
    private static class RegAluno {
        private String matric;
        private String nome;
        private String sexo;
        private double notaProva;
        
        public RegAluno() {
            this.matric = "";
            this.nome = "";
            this.sexo = "";
            this.notaProva = 0.0;
        }
        
        public String getMatric() {
            return matric;
        }
        
        public void setMatric(String matric) {
            this.matric = matric;
        }
        
        public String getNome() {
            return nome;
        }
        
        public void setNome(String nome) {
            this.nome = nome;
        }
        
        public String getSexo() {
            return sexo;
        }
        
        public void setSexo(String sexo) {
            this.sexo = sexo;
        }
        
        public double getNotaProva() {
            return notaProva;
        }
        
        public void setNotaProva(double notaProva) {
            this.notaProva = notaProva;
        }
    }
    
    private static RegAluno regAluno = new RegAluno();
    private static FileOutputStream fileOutputStream;
    private static DataOutputStream dataOutputStream;
    
    public static void main(String[] args) {
        inicio();
    }
    
    public static void inicio() {
        abreArq();
        processo();
        finaliza();
    }
    
    public static void abreArq() {
        try {
            fileOutputStream = new FileOutputStream("CADASTR.DAT");
            dataOutputStream = new DataOutputStream(fileOutputStream);
            
            // In COBOL, ST-ALU would contain file status, but we're not checking it in this simple implementation
        } catch (IOException e) {
            System.err.println("ERRO DE ABERTURA - CAD ALUNO");
            System.exit(1);
        }
    }
    
    public static void processo() {
        // First record
        regAluno.setMatric("180019");
        regAluno.setNome("JOAO DAS NEVES                ");
        regAluno.setSexo("M");
        regAluno.setNotaProva(7.50);
        writeRegAluno();
        
        // Second record
        regAluno.setMatric("180029");
        regAluno.setNome("MARIA JOAQUINA                ");
        regAluno.setSexo("F");
        regAluno.setNotaProva(5.50);
        writeRegAluno();
        
        // Third record
        regAluno.setMatric("180039");
        regAluno.setNome("MARIA MADALENA DE JESUS       ");
        regAluno.setSexo("F");
        regAluno.setNotaProva(8.00);
        writeRegAluno();
        
        // Fourth record
        regAluno.setMatric("180049");
        regAluno.setNome("ALBERT EINSTEN                ");
        regAluno.setSexo("M");
        regAluno.setNotaProva(9.50);
        writeRegAluno();
        
        // Fifth record
        regAluno.setMatric("180059");
        regAluno.setNome("JOAOZINHO DA SILVA            ");
        regAluno.setSexo("M");
        regAluno.setNotaProva(2.00);
        writeRegAluno();
    }
    
    public static void finaliza() {
        try {
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (IOException e) {
            System.err.println("Erro ao fechar arquivo: " + e.getMessage());
        }
    }
    
    private static void writeRegAluno() {
        try {
            // Write matric (6 characters)
            String matric = regAluno.getMatric();
            if (matric.length() < 6) {
                matric = String.format("%-6s", matric);
            }
            dataOutputStream.writeBytes(matric.substring(0, Math.min(6, matric.length())));
            
            // Write nome (30 characters)
            String nome = regAluno.getNome();
            if (nome.length() < 30) {
                nome = String.format("%-30s", nome);
            }
            dataOutputStream.writeBytes(nome.substring(0, Math.min(30, nome.length())));
            
            // Write sexo (1 character)
            String sexo = regAluno.getSexo();
            if (sexo.length() < 1) {
                sexo = " ";
            }
            dataOutputStream.writeBytes(sexo.substring(0, Math.min(1, sexo.length())));
            
            // Write notaProva (formatted as 9(02)V99)
            double nota = regAluno.getNotaProva();
            int intPart = (int) nota;
            int decPart = (int) ((nota - intPart) * 100);
            String notaStr = String.format("%02d%02d", intPart, decPart);
            dataOutputStream.writeBytes(notaStr);
            
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}