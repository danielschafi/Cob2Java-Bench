import java.util.Scanner;
import java.text.DecimalFormat;

public class X1001PRG {
    
    private static class WorkingStorage {
        String wsFim = "N";
        int wsCtlido = 0;
        double asPAcids = 0.0;
        int asMaior = 0;
        int asCidMaior = 0;
        int asQtdeMaior = 0;
        
        int wsCidade = 0;
        String wsEstado = "";
        int wsQtdVeiculos = 0;
        String wsBafometro = "";
        int wsQtdAcidentes = 0;
        int wsQtdObitos = 0;
    }
    
    private static WorkingStorage ws = new WorkingStorage();
    private static Scanner scanner = new Scanner(System.in);
    private static DecimalFormat dfInteger = new DecimalFormat("0");
    private static DecimalFormat dfVeiculos = new DecimalFormat("#,##0");
    private static DecimalFormat dfAcids = new DecimalFormat("#,##0");
    private static DecimalFormat dfObitos = new DecimalFormat("#,##0");
    private static DecimalFormat dfPercent = new DecimalFormat("##0.00");
    private static DecimalFormat dfQtdeMaior = new DecimalFormat("#,##0");
    
    public static void main(String[] args) {
        perform000X1001PRG();
    }
    
    private static void perform000X1001PRG() {
        perform010Iniciar();
        while (!ws.wsFim.equals("S")) {
            perform030Processar();
        }
        perform090Terminar();
    }
    
    private static void perform010Iniciar() {
        System.out.println("P1-2019-2");
        System.out.println("MIGUEL COSTA DE MORAIS");
        System.out.println("1680481721001");
        System.out.println("----------------------");
        ws.asMaior = 0;
        perform025LerSysin();
    }
    
    private static void perform025LerSysin() {
        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            
            if (line.matches("9+")) {
                ws.wsFim = "S";
            } else {
                if (line.length() >= 22) {
                    ws.wsCidade = Integer.parseInt(line.substring(0, 4).trim());
                    ws.wsEstado = line.substring(4, 6);
                    ws.wsQtdVeiculos = Integer.parseInt(line.substring(6, 13).trim());
                    ws.wsBafometro = line.substring(13, 14);
                    ws.wsQtdAcidentes = Integer.parseInt(line.substring(14, 18).trim());
                    ws.wsQtdObitos = Integer.parseInt(line.substring(18, 22).trim());
                    ws.wsCtlido++;
                } else {
                    ws.wsFim = "S";
                }
            }
        } else {
            ws.wsFim = "S";
        }
    }
    
    private static void perform030Processar() {
        ws.asPAcids = ((double) ws.wsQtdObitos / ws.wsQtdVeiculos) * 100;
        
        if (ws.wsQtdAcidentes > ws.asMaior) {
            ws.asMaior = ws.wsQtdAcidentes;
            ws.asCidMaior = ws.wsCidade;
            ws.asQtdeMaior = ws.wsQtdAcidentes;
        }
        
        String cid = String.format("%04d", ws.wsCidade);
        String uf = ws.wsEstado;
        String veics = String.format("%8s", formatNumber(ws.wsQtdVeiculos, dfVeiculos));
        String bafo = ws.wsBafometro;
        String acids = String.format("%5s", formatNumber(ws.wsQtdAcidentes, dfAcids));
        String obitos = String.format("%5s", formatNumber(ws.wsQtdObitos, dfObitos));
        String porcAcids = String.format("%6s", dfPercent.format(ws.asPAcids).replace('.', ','));
        
        System.out.println(cid + " " + uf + " " + veics + " " + bafo + " " + acids + " " + obitos + " " + porcAcids + "% ");
        
        perform025LerSysin();
    }
    
    private static void perform090Terminar() {
        System.out.println("------------------------------------------*");
        System.out.println("CIDADE COM MAIOR NUMERO DE ACIDENTES: " + ws.asCidMaior);
        System.out.println("QTDE DE ACIDENTES DA CIDADE ACIMA: " + formatNumber(ws.asQtdeMaior, dfQtdeMaior));
        System.out.println("QTDE DE CIDADES PESQUISADAS: " + ws.wsCtlido);
        System.out.println("------------------------------------------*");
        scanner.close();
    }
    
    private static String formatNumber(int number, DecimalFormat df) {
        String formatted = df.format(number);
        return formatted.replace(',', '.');
    }
}