import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class CGPRG010 {
    // File status variables
    private static String wsFsAlu = "";
    private static String wsFsRel = "";
    
    // Control variables
    private static int wsCtlido = 0;
    private static int wsCtimpr = 0;
    private static int wsCtlin = 65;
    
    // Date and time variables
    private static String wsDtsys = "";
    private static String wsHrsys = "";
    private static String wsDtedi = "";
    private static String wsHredi = "";
    
    // Message variables
    private static String wsMsg = "";
    private static String wsFsMsg = "";
    
    // Input record variables
    private static int wsNumeroE = 0;
    private static String wsNomeE = "";
    private static String wsSexoE = "";
    private static int wsIdadeE = 0;
    private static String wsCursoE = "";
    private static double wsNota1E = 0.0;
    private static double wsNota2E = 0.0;
    private static double wsMediaE = 0.0;
    
    // Output record variables
    private static String wsDataCab = "";
    private static String wsCursoCab = "";
    private static int wsPagCab = 0;
    private static int wsNumeroR = 0;
    private static String wsNomeR = "";
    private static String wsCursoR = "";
    private static String wsNota1R = "";
    private static String wsNota2R = "";
    private static String wsMediaR = "";
    private static String wsAvaliacaoR = "";
    
    // File handles
    private static BufferedReader cadaluReader = null;
    private static PrintWriter relaluWriter = null;
    
    public static void main(String[] args) {
        try {
            perform000Rspg004();
        } catch (Exception e) {
            System.err.println("Program terminated with error: " + e.getMessage());
            System.exit(1);
        }
    }
    
    private static void perform000Rspg004() throws IOException {
        perform010Iniciar();
        while (!wsFsAlu.equals("10")) {
            perform030Processar();
        }
        perform090Terminar();
    }
    
    private static void perform010Iniciar() throws IOException {
        perform015DataHora();
        
        System.out.println(" *----------------------------------------*");
        System.out.println(" * INICIO : " + wsDtedi + " AS " + wsHredi);
        System.out.println(" *----------------------------------------*");
        
        System.out.println(" *----------------------------------------*");
        System.out.println(" * PROGRAMA 10 - FATEC SCS                *");
        System.out.println(" * MIGUEL MORAIS                          *");
        System.out.println(" * JESSICA HOLANDA                        *");
        System.out.println(" *----------------------------------------*");
        
        perform020AbrirArq();
        perform025LerCadalu();
        
        if (wsFsAlu.equals("10")) {
            wsMsg = "ERRO - CADALU VAZIO";
            wsFsMsg = wsFsAlu;
            perform999Erro();
        }
        
        Scanner scanner = new Scanner(System.in);
        wsCursoCab = scanner.nextLine().trim();
        wsDataCab = wsDtedi;
    }
    
    private static void perform015DataHora() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmssSS");
        
        wsDtsys = dateFormat.format(calendar.getTime());
        wsHrsys = timeFormat.format(calendar.getTime());
        
        wsDtedi = wsDtsys.substring(4, 6) + "/" + wsDtsys.substring(2, 4) + "/20" + wsDtsys.substring(0, 2);
        wsHredi = wsHrsys.substring(0, 2) + ":" + wsHrsys.substring(2, 4) + ":" + wsHrsys.substring(4, 6) + ":" + wsHrsys.substring(6, 8);
    }
    
    private static void perform020AbrirArq() throws IOException {
        try {
            // In a real implementation, these would be actual file readers/writers
            // For now, we'll simulate the file operations
            wsFsAlu = "00"; // Simulate successful open
            wsFsRel = "00"; // Simulate successful open
            
            if (!wsFsAlu.equals("00")) {
                wsMsg = "ERRO AO ABRIR O CADALU";
                wsFsMsg = wsFsAlu;
                perform999Erro();
            }
            
            if (!wsFsRel.equals("00")) {
                wsMsg = "ERRO AO ABRIR O RELALU";
                wsFsMsg = wsFsRel;
                perform999Erro();
            }
        } catch (Exception e) {
            wsMsg = "ERRO AO ABRIR ARQUIVOS";
            wsFsMsg = "99";
            perform999Erro();
        }
    }
    
    private static void perform025LerCadalu() throws IOException {
        // In a real implementation, this would read from the actual file
        // For simulation purposes, we'll just set some dummy data
        wsFsAlu = "00"; // Simulate successful read
        
        if (!wsFsAlu.equals("00") && !wsFsAlu.equals("10")) {
            wsMsg = "ERRO NA LEITURA DO CADALU";
            wsFsMsg = wsFsAlu;
            perform999Erro();
        } else if (wsFsAlu.equals("00")) {
            wsCtlido++;
        }
    }
    
    private static void perform030Processar() throws IOException {
        if (wsMediaE < 7) {
            wsAvaliacaoR = "REPROVADO";
        } else {
            wsAvaliacaoR = "APROVADO ";
        }
        
        wsNumeroR = wsNumeroE;
        wsNomeR = wsNomeE;
        wsCursoR = wsCursoE;
        wsNota1R = String.format("%,.2f", wsNota1E).replace(',', '.');
        wsNota2R = String.format("%,.2f", wsNota2E).replace(',', '.');
        wsMediaR = String.format("%,.2f", wsMediaE).replace(',', '.');
        
        perform035Imprel();
        perform025LerCadalu();
    }
    
    private static void perform035Imprel() throws IOException {
        if (wsCtlin > 13) {
            perform040Impcab();
        }
        
        // Write the output record
        String outputLine = " " +
                           String.format("%4d", wsNumeroR) + "   " +
                           wsNomeR + "   " +
                           wsCursoR + "   " +
                           String.format("%6s", wsNota1R) + "   " +
                           String.format("%6s", wsNota2R) + "   " +
                           String.format("%6s", wsMediaR) + "   " +
                           wsAvaliacaoR + " ";
        
        // In a real implementation, we'd write to the output file
        System.out.println(outputLine);
        wsFsRel = "00"; // Simulate successful write
        
        if (!wsFsRel.equals("00")) {
            wsMsg = "ERRO NA GRAVACAO DO RELALU";
            wsFsMsg = wsFsAlu;
            perform999Erro();
        } else {
            wsCtimpr++;
            wsCtlin++;
        }
    }
    
    private static void perform040Impcab() throws IOException {
        wsPagCab++;
        
        // Print header
        System.out.println(wsDataCab + "                    RELATORIO DA AVALIACAO DOS ALUNOS DO: " + 
                          wsCursoCab + "    PAG. " + String.format("%02d", wsPagCab));
        
        // Print separator line
        System.out.println("----------------------------------------" +
                          "----------------------------------------" +
                          "----------------------------------------" +
                          "----------------------------------------");
        
        // Print column headers
        System.out.println(" NUM.     NOME                      CURSO           NOTA1   NOTA2   MEDIA   AVALIACAO");
        
        // Print separator line
        System.out.println("----------------------------------------" +
                          "----------------------------------------" +
                          "----------------------------------------" +
                          "----------------------------------------");
        
        wsCtlin = 4;
    }
    
    private static void perform090Terminar() throws IOException {
        perform015DataHora();
        
        System.out.println(" *----------------------------------------*");
        System.out.println(" * TERMINO: " + wsDtedi + " AS " + wsHredi);
        System.out.println(" *----------------------------------------*");
        System.out.println(" *========================================*");
        System.out.println(" *   TOTAIS DE CONTROLE - CGPRG010        *");
        System.out.println(" *----------------------------------------*");
        System.out.println(" * REGISTROS LIDOS     - CADALU = " + wsCtlido);
        System.out.println(" * REGISTROS IMPRESSOS - REL