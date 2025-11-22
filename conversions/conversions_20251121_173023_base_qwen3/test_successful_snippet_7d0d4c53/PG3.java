import java.io.*;
import java.nio.file.*;
import java.text.DecimalFormat;

public class PG3 {
    private static final String CADASTR_DAT = "CADASTR.DAT";
    private static final String CADHOME_DAT = "CADHOME.DAT";
    private static final String CADMULH_DAT = "CADMULH.DAT";
    
    private static class RegAluno {
        private int coAno;
        private int coNumero;
        private int coDigito;
        private String nome;
        private char sexo;
        private double notaProva;
        
        public RegAluno() {
            this.nome = new String(new char[30]);
        }
        
        public void setMatric(int ano, int numero, int digito) {
            this.coAno = ano;
            this.coNumero = numero;
            this.coDigito = digito;
        }
        
        public void setNome(String nome) {
            this.nome = String.format("%-30s", nome).substring(0, 30);
        }
        
        public void setSexo(char sexo) {
            this.sexo = sexo;
        }
        
        public void setNotaProva(double nota) {
            this.notaProva = nota;
        }
        
        public int getCoAno() { return coAno; }
        public int getCoNumero() { return coNumero; }
        public int getCoDigito() { return coDigito; }
        public String getNome() { return nome; }
        public char getSexo() { return sexo; }
        public double getNotaProva() { return notaProva; }
    }
    
    private static class RegCadH {
        private int coAnoH;
        private int coNumeroH;
        private int coDigitoH;
        private String nomeH;
        private char sexoH;
        private double notaProvaH;
        
        public RegCadH() {
            this.nomeH = new String(new char[30]);
        }
        
        public void setMatricH(int ano, int numero, int digito) {
            this.coAnoH = ano;
            this.coNumeroH = numero;
            this.coDigitoH = digito;
        }
        
        public void setNomeH(String nome) {
            this.nomeH = String.format("%-30s", nome).substring(0, 30);
        }
        
        public void setSexoH(char sexo) {
            this.sexoH = sexo;
        }
        
        public void setNotaProvaH(double nota) {
            this.notaProvaH = nota;
        }
        
        public int getCoAnoH() { return coAnoH; }
        public int getCoNumeroH() { return coNumeroH; }
        public int getCoDigitoH() { return coDigitoH; }
        public String getNomeH() { return nomeH; }
        public char getSexoH() { return sexoH; }
        public double getNotaProvaH() { return notaProvaH; }
    }
    
    private static class RegCadM {
        private int coAnom;
        private int coNumerom;
        private int coDigitom;
        private String nomem;
        private char sexom;
        private double notaProvam;
        
        public RegCadM() {
            this.nomem = new String(new char[30]);
        }
        
        public void setMatricM(int ano, int numero, int digito) {
            this.coAnom = ano;
            this.coNumerom = numero;
            this.coDigitom = digito;
        }
        
        public void setNomem(String nome) {
            this.nomem = String.format("%-30s", nome).substring(0, 30);
        }
        
        public void setSexom(char sexo) {
            this.sexom = sexo;
        }
        
        public void setNotaProvam(double nota) {
            this.notaProvam = nota;
        }
        
        public int getCoAnom() { return coAnom; }
        public int getCoNumerom() { return coNumerom; }
        public int getCoDigitom() { return coDigitom; }
        public String getNomem() { return nomem; }
        public char getSexom() { return sexom; }
        public double getNotaProvam() { return notaProvam; }
    }
    
    private static BufferedReader arqcad;
    private static BufferedWriter cadhomem;
    private static BufferedWriter cadmulhe;
    
    private static int fimArq;
    private static int totalH;
    private static int totalM;
    private static double totprh;
    private static double totprm;
    private static double mediaM;
    private static double mediaH;
    private static DecimalFormat df = new DecimalFormat("00.00");
    private static DecimalFormat df2 = new DecimalFormat("00,00");
    
    public static void main(String[] args) {
        abreArq();
        inicializaDados();
        processo();
        finaliza();
    }
    
    private static void abreArq() {
        try {
            arqcad = Files.newBufferedReader(Paths.get(CADASTR_DAT));
            cadhomem = Files.newBufferedWriter(Paths.get(CADHOME_DAT));
            cadmulhe = Files.newBufferedWriter(Paths.get(CADMULH_DAT));
        } catch (IOException e) {
            System.err.println("ERRO DE ABERTURA - CAD ALUNO");
            System.exit(1);
        }
    }
    
    private static void inicializaDados() {
        fimArq = 0;
        totalH = 0;
        totalM = 0;
        totprh = 0.0;
        totprm = 0.0;
    }
    
    private static void processo() {
        try {
            String linha = arqcad.readLine();
            while (linha != null && fimArq == 0) {
                leArq(linha);
                linha = arqcad.readLine();
            }
            mostraTotal();
        } catch (IOException e) {
            System.err.println("Erro na leitura do arquivo");
        }
    }
    
    private static void leArq(String linha) {
        if (linha.length() >= 37) {
            RegAluno regAluno = new RegAluno();
            
            // Extrair dados do aluno
            int coAno = Integer.parseInt(linha.substring(0, 2));
            int coNumero = Integer.parseInt(linha.substring(2, 5));
            int coDigito = Integer.parseInt(linha.substring(5, 6));
            String nome = linha.substring(6, 36).trim();
            char sexo = linha.charAt(36);
            double notaProva = Double.parseDouble(linha.substring(37).replace(',', '.'));
            
            regAluno.setMatric(coAno, coNumero, coDigito);
            regAluno.setNome(nome);
            regAluno.setSexo(sexo);
            regAluno.setNotaProva(notaProva);
            
            if (sexo == 'F') {
                gravaMulher(regAluno);
            } else {
                gravaHomem(regAluno);
            }
        }
    }
    
    private static void gravaMulher(RegAluno regAluno) {
        totalM++;
        totprm += regAluno.getNotaProva();
        
        RegCadM regCadM = new RegCadM();
        regCadM.setMatricM(regAluno.getCoAno(), regAluno.getCoNumero(), regAluno.getCoDigito());
        regCadM.setNomem(regAluno.getNome());
        regCadM.setSexom(regAluno.getSexo());
        regCadM.setNotaProvam(regAluno.getNotaProva());
        
        try {
            cadmulhe.write(
                String.format("%02d%03d%1d%-30s%c%02.2f",
                    regCadM.getCoAnom(),
                    regCadM.getCoNumerom(),
                    regCadM.getCoDigitom(),
                    regCadM.getNomem(),
                    regCadM.getSexom(),
                    regCadM.getNotaProvam()
                ).replace('.', ',')
            );
            cadmulhe.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao gravar no arquivo CADMULHE");
        }
    }
    
    private static void gravaHomem(RegAluno regAluno) {
        totalH++;
        totprh += regAluno.getNotaProva();
        
        RegCadH regCadH = new RegCadH();
        regCadH.setMatricH(regAluno.getCoAno(), regAluno.getCoNumero(), regAluno.getCoDigito());
        regCadH.setNomeH(regAluno.getNome());
        regCadH.setSexoH(regAluno.getSexo());
        regCadH.setNotaProvaH(regAluno.getNotaProva());
        
        try {
            cadhomem.write(
                String.format("%02d%03d%1d%-30s%c%02.2f",
                    regCadH.getCoAnoH(),
                    regCadH.getCoNumeroH(),
                    regCadH.getCoDigitoH(),
                    regCadH.getNomeH(),
                    regCadH.getSexoH(),
                    regCadH.getNotaProvaH()
                ).replace('.', ',')
            );
            cadhomem.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao gravar no arquivo CADHOMEM");
        }
    }