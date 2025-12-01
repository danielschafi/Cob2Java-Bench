import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CGPRG010 {
    private static String wsCtlido = "00";
    private static String wsCtimpr = "00";
    private static String wsCtlin = "65";
    private static String wsDtsys = "";
    private static String wsHrsys = "";
    private static String wsDtedi = "";
    private static String wsHredi = "";
    private static String wsFsAlu = "";
    private static String wsFsRel = "";
    private static String wsMsg = "";
    private static String wsFsMsg = "";
    
    private static String wsNumeroE = "";
    private static String wsNomeE = "";
    private static String wsSexoE = "";
    private static String wsIdadeE = "";
    private static String wsCursoE = "";
    private static double wsNota1E = 0.0;
    private static double wsNota2E = 0.0;
    private static double wsMediaE = 0.0;
    
    private static String wsDataCab = "";
    private static String wsCursoCab = "";
    private static int wsPagCab = 0;
    
    private static String wsNumeroR = "";
    private static String wsNomeR = "";
    private static String wsCursoR = "";
    private static double wsNota1R = 0.0;
    private static double wsNota2R = 0.0;
    private static double wsMediaR = 0.0;
    private static String wsAvaliacaoR = "";
    
    private static BufferedReader inputFile;
    private static PrintWriter outputFile;
    private static Scanner scanner;

    public static void main(String[] args) {
        try {
            iniciar();
            while (!wsFsAlu.equals("10")) {
                processar();
            }
            terminar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void iniciar() throws IOException {
        obterDataHora();
        
        System.out.println(" *----------------------------------------*");
        System.out.println(" * INICIO : " + wsDtedi + " AS " + wsHredi);
        System.out.println(" *----------------------------------------*");
        System.out.println(" *----------------------------------------*");
        System.out.println(" * PROGRAMA 10 - FATEC SCS                *");
        System.out.println(" * MIGUEL MORAIS                          *");
        System.out.println(" * JESSICA HOLANDA                        *");
        System.out.println(" *----------------------------------------*");
        
        abrirArquivos();
        lerCadalu();
        
        if (wsFsAlu.equals("10")) {
            wsMsg = "ERRO - CADALU VAZIO";
            wsFsMsg = wsFsAlu;
            erro();
        }
        
        scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            wsCursoCab = scanner.nextLine().trim();
        }
        wsDataCab = wsDtedi;
    }

    private static void obterDataHora() {
        LocalDate hoje = LocalDate.now();
        LocalTime agora = LocalTime.now();
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        wsDtedi = hoje.format(dateFormatter);
        
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss:SS");
        wsHredi = agora.format(timeFormatter);
    }

    private static void abrirArquivos() throws IOException {
        try {
            inputFile = new BufferedReader(new FileReader("CADALUJ"));
            wsFsAlu = "00";
        } catch (IOException e) {
            wsMsg = "ERRO AO ABRIR O CADALU";
            wsFsMsg = "01";
            erro();
        }
        
        try {
            outputFile = new PrintWriter(new FileWriter("RELALUJ"));
            wsFsRel = "00";
        } catch (IOException e) {
            wsMsg = "ERRO AO ABRIR O RELALU";
            wsFsMsg = "01";
            erro();
        }
    }

    private static void lerCadalu() throws IOException {
        String linha = inputFile.readLine();
        if (linha == null) {
            wsFsAlu = "10";
        } else {
            wsFsAlu = "00";
            parseRegistroCadalu(linha);
            wsCtlido = String.format("%02d", Integer.parseInt(wsCtlido) + 1);
        }
    }

    private static void parseRegistroCadalu(String linha) {
        if (linha.length() >= 70) {
            wsNumeroE = linha.substring(0, 4).trim();
            wsNomeE = linha.substring(4, 24).trim();
            wsSexoE = linha.substring(24, 25).trim();
            wsIdadeE = linha.substring(25, 27).trim();
            wsCursoE = linha.substring(27, 39).trim();
            try {
                wsNota1E = Double.parseDouble(linha.substring(39, 44).replace(",", "."));
                wsNota2E = Double.parseDouble(linha.substring(44, 49).replace(",", "."));
                wsMediaE = Double.parseDouble(linha.substring(49, 54).replace(",", "."));
            } catch (NumberFormatException e) {
                wsNota1E = 0.0;
                wsNota2E = 0.0;
                wsMediaE = 0.0;
            }
        }
    }

    private static void processar() throws IOException {
        if (wsMediaE < 7) {
            wsAvaliacaoR = "REPROVADO";
        } else {
            wsAvaliacaoR = "APROVADO ";
        }
        
        wsNumeroR = wsNumeroE;
        wsNomeR = wsNomeE;
        wsCursoR = wsCursoE;
        wsNota1R = wsNota1E;
        wsNota2R = wsNota2E;
        wsMediaR = wsMediaE;
        
        imprel();
        lerCadalu();
    }

    private static void imprel() throws IOException {
        int ctlin = Integer.parseInt(wsCtlin);
        if (ctlin > 13) {
            impcab();
        }
        
        String linha = formatarLinhaRelatorio();
        outputFile.println(linha);
        
        if (wsFsRel.equals("00")) {
            wsCtimpr = String.format("%02d", Integer.parseInt(wsCtimpr) + 1);
            wsCtlin = String.format("%02d", Integer.parseInt(wsCtlin) + 1);
        } else {
            wsMsg = "ERRO NA GRAVACAO DO RELALU";
            wsFsMsg = wsFsAlu;
            erro();
        }
    }

    private static void impcab() throws IOException {
        wsPagCab++;
        
        String cab1 = formatarCabecalho1();
        outputFile.println(cab1);
        
        String hifen = String.format("%-80s", "").replace(" ", "-");
        outputFile.println(hifen);
        
        String cab2 = formatarCabecalho2();
        outputFile.println(cab2);
        
        outputFile.println(hifen);
        
        wsCtlin = "04";
    }

    private static String formatarCabecalho1() {
        return String.format(" %-10s    RELATORIO DA AVALIACAO DOS ALUNOS DO: %-15s    PAG. %02d ", 
            wsDataCab, wsCursoCab, wsPagCab);
    }

    private static String formatarCabecalho2() {
        return " NUM.       NOME                 CURSO        NOTA1    NOTA2    MEDIA    AV