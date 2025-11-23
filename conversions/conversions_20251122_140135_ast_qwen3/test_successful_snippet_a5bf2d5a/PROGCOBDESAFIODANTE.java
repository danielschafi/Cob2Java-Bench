import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PROGCOBDESAFIODANTE {
    private static double ws77_aumento = 0.0;
    private static int ws77_tempocasa = 0;
    
    private static String ws77_cad = "LUCAS DA ROSA MAGALHAES       201300500000";
    private static String ws77_cad1 = "DANTE ROBERTO DE VIT LUNARDI  198500200000";
    
    private static class WS01_CAMPOS {
        private static String ws05_nome = "";
        private static int ws05_anoentrada = 0;
        private static int ws05_salario = 0;
    }
    
    private static class WS01_DATASISTEMA {
        private static int ws03_ano = 0;
        private static int ws03_mes = 0;
        private static int ws03_dia = 0;
    }
    
    public static void main(String[] args) {
        iniciar();
        processar();
        finalizar();
    }
    
    private static void iniciar() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        
        String[] parts = formattedDate.split("-");
        WS01_DATASISTEMA.ws03_ano = Integer.parseInt(parts[0]);
        WS01_DATASISTEMA.ws03_mes = Integer.parseInt(parts[1]);
        WS01_DATASISTEMA.ws03_dia = Integer.parseInt(parts[2]);
        
        System.out.println("======================================================");
        System.out.println("DATA ATUAL: " + WS01_DATASISTEMA.ws03_dia + "/" + 
                          WS01_DATASISTEMA.ws03_mes + "/" + WS01_DATASISTEMA.ws03_ano);
        System.out.println("======================================================");
        
        extrairDados(ws77_cad);
        System.out.println("CAD: " + WS01_CAMPOS.ws05_nome + " " + 
                         WS01_CAMPOS.ws05_anoentrada + " " + WS01_CAMPOS.ws05_salario);
        
        extrairDados(ws77_cad1);
        System.out.println("CAD1: " + WS01_CAMPOS.ws05_nome + " " + 
                          WS01_CAMPOS.ws05_anoentrada + " " + WS01_CAMPOS.ws05_salario);
        System.out.println("======================================================");
    }
    
    private static void extrairDados(String dados) {
        WS01_CAMPOS.ws05_nome = dados.substring(0, 30).trim();
        WS01_CAMPOS.ws05_anoentrada = Integer.parseInt(dados.substring(30, 34));
        WS01_CAMPOS.ws05_salario = Integer.parseInt(dados.substring(34, 42));
    }
    
    private static void processar() {
        extrairDados(ws77_cad);
        ws77_tempocasa = WS01_DATASISTEMA.ws03_ano - WS01_CAMPOS.ws05_anoentrada;
        
        if (ws77_tempocasa >= 0 && ws77_tempocasa <= 1) {
            ws77_aumento = 0;
        } else if (ws77_tempocasa >= 2 && ws77_tempocasa <= 5) {
            ws77_aumento = (WS01_CAMPOS.ws05_salario / 100.0) * 0.05;
        } else if (ws77_tempocasa >= 6 && ws77_tempocasa <= 15) {
            ws77_aumento = (WS01_CAMPOS.ws05_salario / 100.0) * 0.10;
        } else {
            ws77_aumento = (WS01_CAMPOS.ws05_salario / 100.0) * 0.15;
        }
        
        System.out.println("TEMPO DE CASA LUCAS: " + ws77_tempocasa + " ANO(S)");
        System.out.println("AUMENTO LUCAS: R$ " + String.format("%.2f", ws77_aumento));
        System.out.println("======================================================");
    }
    
    private static void finalizar() {
        extrairDados(ws77_cad1);
        ws77_tempocasa = WS01_DATASISTEMA.ws03_ano - WS01_CAMPOS.ws05_anoentrada;
        
        if (ws77_tempocasa >= 0 && ws77_tempocasa <= 1) {
            ws77_aumento = 0;
        } else if (ws77_tempocasa >= 2 && ws77_tempocasa <= 5) {
            ws77_aumento = (WS01_CAMPOS.ws05_salario / 100.0) * 0.05;
        } else if (ws77_tempocasa >= 6 && ws77_tempocasa <= 15) {
            ws77_aumento = (WS01_CAMPOS.ws05_salario / 100.0) * 0.10;
        } else {
            ws77_aumento = (WS01_CAMPOS.ws05_salario / 100.0) * 0.15;
        }
        
        System.out.println("TEMPO DE CASA DANTE: " + ws77_tempocasa + " ANO(S)");
        System.out.println("AUMENTO DANTE: R$ " + String.format("%.2f", ws77_aumento));
        System.out.println("======================================================");
    }
}