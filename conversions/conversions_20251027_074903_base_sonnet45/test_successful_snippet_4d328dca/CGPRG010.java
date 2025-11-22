import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CGPRG010 {

    private static final int RECORD_LENGTH = 70;
    private static final int REPORT_WIDTH = 80;
    private static final int MAX_LINES_PER_PAGE = 13;

    private int wsCtlido = 0;
    private int wsCtimpr = 0;
    private int wsCtlin = 65;
    private String wsDtedi = "";
    private String wsHredi = "";
    private String wsFsAlu = "00";
    private String wsFsRel = "00";
    private String wsMsg = "";
    private String wsFsMsg = "";
    private String wsCursoCab = "";
    private int wsPagCab = 0;

    private BufferedReader cadaluReader;
    private PrintWriter relaluWriter;
    private StudentRecord currentRecord;

    private DecimalFormat gradeFormat = new DecimalFormat("00.00");

    static class StudentRecord {
        int numero;
        String nome;
        String sexo;
        int idade;
        String curso;
        double nota1;
        double nota2;
        double media;

        StudentRecord() {
            numero = 0;
            nome = "";
            sexo = "";
            idade = 0;
            curso = "";
            nota1 = 0.0;
            nota2 = 0.0;
            media = 0.0;
        }
    }

    public static void main(String[] args) {
        CGPRG010 program = new CGPRG010();
        program.run();
    }

    public void run() {
        iniciar();
        while (!wsFsAlu.equals("10")) {
            processar();
        }
        terminar();
    }

    private void iniciar() {
        dataHora();

        System.out.println(" *----------------------------------------*");
        System.out.println(" * INICIO : " + wsDtedi + " AS " + wsHredi);
        System.out.println(" *----------------------------------------*");
        System.out.println(" *----------------------------------------*");
        System.out.println(" * PROGRAMA 10 - FATEC SCS                *");
        System.out.println(" * MIGUEL MORAIS                          *");
        System.out.println(" * JESSICA HOLANDA                        *");
        System.out.println(" *----------------------------------------*");

        abrirArq();
        lerCadalu();

        if (wsFsAlu.equals("10")) {
            wsMsg = "ERRO - CADALU VAZIO";
            wsFsMsg = wsFsAlu;
            erro();
        }

        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            wsCursoCab = scanner.nextLine().trim();
            if (wsCursoCab.length() > 15) {
                wsCursoCab = wsCursoCab.substring(0, 15);
            }
        }
    }

    private void dataHora() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss:SS");
        Date now = new Date();
        wsDtedi = dateFormat.format(now);
        wsHredi = timeFormat.format(now);
    }

    private void abrirArq() {
        try {
            String inputFile = System.getenv("CADALUJ");
            if (inputFile == null || inputFile.isEmpty()) {
                inputFile = "CADALU.txt";
            }
            cadaluReader = new BufferedReader(new FileReader(inputFile));
            wsFsAlu = "00";
        } catch (FileNotFoundException e) {
            wsMsg = "ERRO AO ABRIR O CADALU";
            wsFsAlu = "35";
            wsFsMsg = wsFsAlu;
            erro();
        }

        try {
            String outputFile = System.getenv("RELALUJ");
            if (outputFile == null || outputFile.isEmpty()) {
                outputFile = "RELALU.txt";
            }
            relaluWriter = new PrintWriter(new FileWriter(outputFile));
            wsFsRel = "00";
        } catch (IOException e) {
            wsMsg = "ERRO AO ABRIR O RELALU";
            wsFsRel = "35";
            wsFsMsg = wsFsRel;
            erro();
        }
    }

    private void lerCadalu() {
        try {
            String line = cadaluReader.readLine();
            if (line == null) {
                wsFsAlu = "10";
            } else {
                wsFsAlu = "00";
                currentRecord = parseRecord(line);
                wsCtlido++;
            }
        } catch (IOException e) {
            wsMsg = "ERRO NA LEITURA DO CADALU";
            wsFsAlu = "90";
            wsFsMsg = wsFsAlu;
            erro();
        }
    }

    private StudentRecord parseRecord(String line) {
        StudentRecord record = new StudentRecord();
        
        if (line.length() < 70) {
            line = String.format("%-70s", line);
        }

        try {
            record.numero = Integer.parseInt(line.substring(0, 4).trim());
            record.nome = line.substring(4, 24);
            record.sexo = line.substring(24, 25);
            record.idade = Integer.parseInt(line.substring(25, 27).trim());
            record.curso = line.substring(27, 39);
            record.nota1 = Double.parseDouble(line.substring(39, 43).trim()) / 100.0;
            record.nota2 = Double.parseDouble(line.substring(43, 47).trim()) / 100.0;
            record.media = Double.parseDouble(line.substring(47, 51).trim()) / 100.0;
        } catch (Exception e) {
            record.numero = 0;
            record.nome = "";
            record.curso = "";
            record.nota1 = 0.0;
            record.nota2 = 0.0;
            record.media = 0.0;
        }

        return record;
    }

    private void processar() {
        String avaliacao;
        if (currentRecord.media < 7.0) {
            avaliacao = "REPROVADO";
        } else {
            avaliacao = "APROVADO ";
        }

        imprel(currentRecord, avaliacao);
        lerCadalu();
    }

    private void imprel(StudentRecord record, String avaliacao) {
        if (wsCtlin > MAX_LINES_PER_PAGE) {
            impcab();
        }

        String linha = formatDetailLine(record, avaliacao);
        relaluWriter.println(linha);
        
        if (relaluWriter.checkError()) {
            wsMsg = "ERRO NA GRAVACAO DO RELALU";
            wsFsRel = "90";
            wsFsMsg = wsFsRel;
            erro();
        }

        wsCtimpr++;
        wsCtlin++;
    }

    private String formatDetailLine(StudentRecord record, String avaliacao) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(String.format("%04d", record.numero));
        sb.append("   ");
        sb.append(String.format("%-20s", record.nome));
        sb.append("   ");
        sb.append(String.format("%-12s", record.curso));
        sb.append("   ");
        sb.append(formatGrade(record.nota1));
        sb.append("   ");
        sb.append(formatGrade(record.nota2));
        sb.append("   ");
        sb.append(formatGrade(record.media));
        sb.append("   ");
        sb.append(avaliacao);
        sb.append(" ");
        return sb.toString();
    }