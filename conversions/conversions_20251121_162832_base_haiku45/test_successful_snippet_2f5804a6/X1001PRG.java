import java.util.Scanner;

public class X1001PRG {
    
    static class WsAreaAux {
        char wsFim = ' ';
        int wsCtlido = 0;
        double asPAcids = 0.0;
        int asMaior = 0;
        int asCidMaior = 0;
        String asQtdeMaior = "";
    }
    
    static class WsRegSysin {
        int wsCidade = 0;
        String wsEstado = "";
        int wsQtdVeiculos = 0;
        char wsBafometro = ' ';
        int wsQtdAcidentes = 0;
        int wsQtdObitos = 0;
    }
    
    static class WsRegSysout {
        int cid = 0;
        String uf = "";
        String veics = "";
        char bafo = ' ';
        String acids = "";
        String obitos = "";
        String porcAcids = "";
    }
    
    static WsAreaAux wsAreaAux = new WsAreaAux();
    static WsRegSysin wsRegSysin = new WsRegSysin();
    static WsRegSysout wsRegSysout = new WsRegSysout();
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        iniciar();
        while (wsAreaAux.wsFim != 'S') {
            processar();
        }
        terminar();
    }
    
    static void iniciar() {
        System.out.println("P1-2019-2");
        System.out.println("MIGUEL COSTA DE MORAIS");
        System.out.println("1680481721001");
        System.out.println("----------------------");
        wsAreaAux.asMaior = 0;
        lerSysin();
    }
    
    static void lerSysin() {
        String linha = "";
        if (scanner.hasNextLine()) {
            linha = scanner.nextLine();
        }
        
        if (linha.matches("9+")) {
            wsAreaAux.wsFim = 'S';
        } else {
            if (linha.length() >= 20) {
                try {
                    wsRegSysin.wsCidade = Integer.parseInt(linha.substring(0, 4).trim());
                    wsRegSysin.wsEstado = linha.substring(4, 6);
                    wsRegSysin.wsQtdVeiculos = Integer.parseInt(linha.substring(6, 13).trim());
                    wsRegSysin.wsBafometro = linha.length() > 13 ? linha.charAt(13) : ' ';
                    wsRegSysin.wsQtdAcidentes = Integer.parseInt(linha.substring(14, 18).trim());
                    wsRegSysin.wsQtdObitos = Integer.parseInt(linha.substring(18, 22).trim());
                } catch (Exception e) {
                    wsAreaAux.wsFim = 'S';
                    return;
                }
            }
            wsAreaAux.wsCtlido++;
        }
    }
    
    static void processar() {
        if (wsRegSysin.wsQtdVeiculos > 0) {
            wsAreaAux.asPAcids = ((double) wsRegSysin.wsQtdObitos / wsRegSysin.wsQtdVeiculos) * 100;
        } else {
            wsAreaAux.asPAcids = 0.0;
        }
        
        if (wsRegSysin.wsQtdAcidentes > wsAreaAux.asMaior) {
            wsAreaAux.asMaior = wsRegSysin.wsQtdAcidentes;
            wsAreaAux.asCidMaior = wsRegSysin.wsCidade;
            wsAreaAux.asQtdeMaior = formatNumber(wsRegSysin.wsQtdAcidentes, 5);
        }
        
        wsRegSysout.cid = wsRegSysin.wsCidade;
        wsRegSysout.uf = wsRegSysin.wsEstado;
        wsRegSysout.veics = formatNumberWithDecimals(wsRegSysin.wsQtdVeiculos, 9, 2);
        wsRegSysout.bafo = wsRegSysin.wsBafometro;
        wsRegSysout.acids = formatNumber(wsRegSysin.wsQtdAcidentes, 5);
        wsRegSysout.obitos = formatNumber(wsRegSysin.wsQtdObitos, 5);
        wsRegSysout.porcAcids = formatPercentage(wsAreaAux.asPAcids);
        
        String output = String.format("%04d %s %s %c %s %s %s%% ", 
            wsRegSysout.cid, 
            wsRegSysout.uf, 
            wsRegSysout.veics, 
            wsRegSysout.bafo, 
            wsRegSysout.acids, 
            wsRegSysout.obitos, 
            wsRegSysout.porcAcids);
        
        System.out.println(output);
        
        lerSysin();
    }
    
    static void terminar() {
        System.out.println("------------------------------------------*");
        System.out.println("CIDADE COM MAIOR NUMERO DE ACIDENTES: " + String.format("%04d", wsAreaAux.asCidMaior));
        System.out.println("QTDE DE ACIDENTES DA CIDADE ACIMA: " + wsAreaAux.asQtdeMaior);
        System.out.println("QTDE DE CIDADES PESQUISADAS: " + wsAreaAux.wsCtlido);
        System.out.println("------------------------------------------*");
    }
    
    static String formatNumber(int value, int width) {
        String formatted = String.valueOf(value);
        while (formatted.length() < width) {
            formatted = " " + formatted;
        }
        return formatted;
    }
    
    static String formatNumberWithDecimals(int value, int totalWidth, int decimalPlaces) {
        String formatted = String.format("%." + decimalPlaces + "f", value / Math.pow(10, decimalPlaces));
        while (formatted.length() < totalWidth) {
            formatted = " " + formatted;
        }
        return formatted;
    }
    
    static String formatPercentage(double value) {
        return String.format("%6.2f", value).replace(".", ",");
    }
}