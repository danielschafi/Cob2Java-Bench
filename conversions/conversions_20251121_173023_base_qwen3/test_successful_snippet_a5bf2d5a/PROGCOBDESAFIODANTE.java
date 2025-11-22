import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PROGCOBDESAFIODANTE {
    private static double ws77_aumento = 0;
    private static int ws77_tempocasa = 0;
    private static String ws77_cad = "LUCAS DA ROSA MAGALHAES       201300500000";
    private static String ws77_cad1 = "DANTE ROBERTO DE VIT LUNARDI  198500200000";
    
    private static class WS01_Campos {
        private String ws05_nome;
        private int ws05_anoentrada;
        private int ws05_salario;
    }
    
    private static class WS01_Datasistema {
        private int ws03_ano;
        private int ws03_mes;
        private int ws03_dia;
    }
    
    public static void main(String[] args) {
        WS01_Campos ws03_informacoes = new WS01_Campos();
        WS01_Datasistema ws01_datasistema = new WS01_Datasistema();
        
        iniciar(ws01_datasistema);
        processar(ws03_informacoes, ws01_datasistema);
        finalizar(ws03_informacoes, ws01_datasistema);
    }
    
    private static void iniciar(WS01_Datasistema ws01_datasistema) {
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dataFormatada = dataAtual.format(formatter);
        
        ws01_datasistema.ws03_ano = Integer.parseInt(dataFormatada.substring(0, 4));
        ws01_datasistema.ws03_mes = Integer.parseInt(dataFormatada.substring(4, 6));
        ws01_datasistema.ws03_dia = Integer.parseInt(dataFormatada.substring(6, 8));
        
        System.out.println("======================================================");
        System.out.println("DATA ATUAL: " + ws01_datasistema.ws03_dia + "/" + 
                          ws01_datasistema.ws03_mes + "/" + ws01_datasistema.ws03_ano);
        System.out.println("======================================================");
        
        extrairDados(ws77_cad, ws03_informacoes);
        System.out.println("CAD: " + ws03_informacoes.ws05_nome + " " + 
                          ws03_informacoes.ws05_anoentrada + " " + 
                          ws03_informacoes.ws05_salario);
        
        extrairDados(ws77_cad1, ws03_informacoes);
        System.out.println("CAD1: " + ws03_informacoes.ws05_nome + " " + 
                           ws03_informacoes.ws05_anoentrada + " " + 
                           ws03_informacoes.ws05_salario);
        System.out.println("======================================================");
    }
    
    private static void extrairDados(String cadastro, WS01_Campos campos) {
        campos.ws05_nome = cadastro.substring(0, 30).trim();
        campos.ws05_anoentrada = Integer.parseInt(cadastro.substring(30, 34));
        campos.ws05_salario = Integer.parseInt(cadastro.substring(34, 42));
    }
    
    private static void processar(WS01_Campos ws03_informacoes, WS01_Datasistema ws01_datasistema) {
        extrairDados(ws77_cad, ws03_informacoes);
        ws77_tempocasa = ws01_datasistema.ws03_ano - ws03_informacoes.ws05_anoentrada;
        
        if (ws77_tempocasa >= 0 && ws77_tempocasa <= 1) {
            ws77_aumento = 0;
        } else if (ws77_tempocasa >= 2 && ws77_tempocasa <= 5) {
            ws77_aumento = (ws03_informacoes.ws05_salario / 100.0) * 0.05;
        } else if (ws77_tempocasa >= 6 && ws77_tempocasa <= 15) {
            ws77_aumento = (ws03_informacoes.ws05_salario / 100.0) * 0.10;
        } else {
            ws77_aumento = (ws03_informacoes.ws05_salario / 100.0) * 0.15;
        }
        
        System.out.println("TEMPO DE CASA LUCAS: " + ws77_tempocasa + " ANO(S)");
        System.out.println("AUMENTO LUCAS: R$ " + String.format("%.2f", ws77_aumento));
        System.out.println("======================================================");
    }
    
    private static void finalizar(WS01_Campos ws03_informacoes, WS01_Datasistema ws01_datasistema) {
        extrairDados(ws77_cad1, ws03_informacoes);
        ws77_tempocasa = ws01_datasistema.ws03_ano - ws03_informacoes.ws05_anoentrada;
        
        if (ws77_tempocasa >= 0 && ws77_tempocasa <= 1) {
            ws77_aumento = 0;
        } else if (ws77_tempocasa >= 2 && ws77_tempocasa <= 5) {
            ws77_aumento = (ws03_informacoes.ws05_salario / 100.0) * 0.05;
        } else if (ws77_tempocasa >= 6 && ws77_tempocasa <= 15) {
            ws77_aumento = (ws03_informacoes.ws05_salario / 100.0) * 0.10;
        } else {
            ws77_aumento = (ws03_informacoes.ws05_salario / 100.0) * 0.15;
        }
        
        System.out.println("TEMPO DE CASA DANTE: " + ws77_tempocasa + " ANO(S)");
        System.out.println("AUMENTO DANTE: R$ " + String.format("%.2f", ws77_aumento));
        System.out.println("======================================================");
    }
}