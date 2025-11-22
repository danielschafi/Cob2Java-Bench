import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CGPRG010 {
    
    private static class WorkingStorage {
        int wsCtlido = 0;
        int wsCtimpr = 0;
        int wsCtlin = 65;
        String wsDtsys = "";
        String wsHrsys = "";
        String wsDtedi = "";
        String wsHredi = "";
        String wsFsAlu = "00";
        String wsFsRel = "00";
        String wsMsg = "";
        String wsFsMsg = "";
        
        int wsNumeroE = 0;
        String wsNomeE = "";
        String wsSexoE = "";
        int wsIdadeE = 0;
        String wsCursoE = "";
        double wsNota1E = 0.0;
        double wsNota2E = 0.0;
        double wsMediaE = 0.0;
        
        String wsCursoCab = "";
        int wsPageCab = 0;
        
        int wsNumeroR = 0;
        String wsNomeR = "";
        String wsCursoR = "";
        double wsNota1R = 0.0;
        double wsNota2R = 0.0;
        double wsMediaR = 0.0;
        String wsAvaliacaoR = "";
    }
    
    private static WorkingStorage ws = new WorkingStorage();
    private static BufferedReader cadAluReader = null;
    private static BufferedWriter relAluWriter = null;
    private static Scanner sysinScanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        procedimento000RsPrg004();
    }
    
    private static void procedimento000RsPrg004() {
        procedimento010Iniciar();
        while (!ws.wsFsAlu.equals("10")) {
            procedimento030Processar();
        }
        procedimento090Terminar();
        System.exit(0);
    }
    
    private static void procedimento010Iniciar() {
        procedimento015DataHora();
        
        System.out.println(" *----------------------------------------*");
        System.out.println(" * INICIO : " + ws.wsDtedi + " AS " + ws.wsHredi);
        System.out.println(" *----------------------------------------*");
        
        System.out.println(" *----------------------------------------*");
        System.out.println(" * PROGRAMA 10 - FATEC SCS                *");
        System.out.println(" * MIGUEL MORAIS                          *");
        System.out.println(" * JESSICA HOLANDA                        *");
        System.out.println(" *----------------------------------------*");
        
        procedimento020AbrirArq();
        procedimento025LerCadalu();
        
        if (ws.wsFsAlu.equals("10")) {
            ws.wsMsg = "ERRO - CADALU VAZIO";
            ws.wsFsMsg = ws.wsFsAlu;
            procedimento999Erro();
        }
        
        if (sysinScanner.hasNextLine()) {
            ws.wsCursoCab = sysinScanner.nextLine();
        }
    }
    
    private static void procedimento015DataHora() {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ws.wsDtedi = today.format(dateFormatter);
        
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss:SS");
        ws.wsHredi = now.format(timeFormatter);
    }
    
    private static void procedimento020AbrirArq() {
        try {
            cadAluReader = new BufferedReader(new FileReader("CADALUJ"));
            ws.wsFsAlu = "00";
        } catch (IOException e) {
            ws.wsMsg = "ERRO AO ABRIR O CADALU";
            ws.wsFsMsg = "01";
            procedimento999Erro();
        }
        
        try {
            relAluWriter = new BufferedWriter(new FileWriter("RELALUJ"));
            ws.wsFsRel = "00";
        } catch (IOException e) {
            ws.wsMsg = "ERRO AO ABRIR O RELALU";
            ws.wsFsMsg = "01";
            procedimento999Erro();
        }
    }
    
    private static void procedimento025LerCadalu() {
        try {
            String line = cadAluReader.readLine();
            if (line == null) {
                ws.wsFsAlu = "10";
            } else {
                ws.wsFsAlu = "00";
                parseRegCadalu(line);
                ws.wsCtlido++;
            }
        } catch (IOException e) {
            ws.wsMsg = "ERRO NA LEITURA DO CADALU";
            ws.wsFsMsg = "01";
            procedimento999Erro();
        }
    }
    
    private static void parseRegCadalu(String line) {
        if (line.length() >= 70) {
            ws.wsNumeroE = Integer.parseInt(line.substring(0, 4).trim());
            ws.wsNomeE = line.substring(4, 24).trim();
            ws.wsSexoE = line.substring(24, 25);
            ws.wsIdadeE = Integer.parseInt(line.substring(25, 27).trim());
            ws.wsCursoE = line.substring(27, 39).trim();
            ws.wsNota1E = Double.parseDouble(line.substring(39, 44).replace(',', '.').trim());
            ws.wsNota2E = Double.parseDouble(line.substring(44, 49).replace(',', '.').trim());
            ws.wsMediaE = Double.parseDouble(line.substring(49, 54).replace(',', '.').trim());
        }
    }
    
    private static void procedimento030Processar() {
        if (ws.wsMediaE < 7) {
            ws.wsAvaliacaoR = "REPROVADO";
        } else {
            ws.wsAvaliacaoR = "APROVADO ";
        }
        
        ws.wsNumeroR = ws.wsNumeroE;
        ws.wsNomeR = ws.wsNomeE;
        ws.wsCursoR = ws.wsCursoE;
        ws.wsNota1R = ws.wsNota1E;
        ws.wsNota2R = ws.wsNota2E;
        ws.wsMediaR = ws.wsMediaE;
        
        procedimento035Imprel();
        procedimento025LerCadalu();
    }
    
    private static void procedimento035Imprel() {
        if (ws.wsCtlin > 13) {
            procedimento040Impcab();
        }
        
        try {
            String line = formatRegRelalu();
            relAluWriter.write(line);
            relAluWriter.newLine();
            ws.wsFsRel = "00";
            ws.wsCtimpr++;
            ws.wsCtlin++;
        } catch (IOException e) {
            ws.wsMsg = "ERRO NA GRAVACAO DO RELALU";
            ws.wsFsMsg = "01";
            procedimento999Erro();
        }
    }
    
    private static String formatRegRelalu() {
        String nota1Str = String.format("%2.2f", ws.wsNota1R).replace('.', ',');
        String nota2Str = String.format("%2.2f", ws.wsNota2R).replace('.', ',');
        String mediaStr = String.format("%2.2f", ws.wsMediaR).replace('.', ',');
        
        return String.format(" %-4d   %-20s   %-12s   %6s   %6s   %6s   %-9s ",