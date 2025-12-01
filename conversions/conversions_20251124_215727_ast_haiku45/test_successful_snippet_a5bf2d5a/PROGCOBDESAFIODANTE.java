import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class PROGCOBDESAFIODANTE {
    private static double ws77Aumento = 0.0;
    private static int ws77TempoCase = 0;
    private static String ws77Cad = "LUCAS DA ROSA MAGALHAES       201300500000";
    private static String ws77Cad1 = "DANTE ROBERTO DE VIT LUNARDI  198500200000";
    
    private static String ws05Nome = "";
    private static int ws05AnoEntrada = 0;
    private static long ws05Salario = 0;
    
    private static int ws03Ano = 0;
    private static int ws03Mes = 0;
    private static int ws03Dia = 0;
    
    public static void main(String[] args) {
        iniciar();
        processar();
        finalizar();
    }
    
    private static void iniciar() {
        LocalDate today = LocalDate.now();
        ws03Dia = today.getDayOfMonth();
        ws03Mes = today.getMonthValue();
        ws03Ano = today.getYear();
        
        System.out.println("======================================================");
        System.out.println("DATA ATUAL: " + String.format("%02d", ws03Dia) + "/" + 
                          String.format("%02d", ws03Mes) + "/" + ws03Ano);
        System.out.println("======================================================");
        
        parseInformacoes(ws77Cad);
        System.out.println("CAD: " + ws05Nome + " " + ws05AnoEntrada + " " + ws05Salario);
        
        parseInformacoes(ws77Cad1);
        System.out.println("CAD1: " + ws05Nome + " " + ws05AnoEntrada + " " + ws05Salario);
        System.out.println("======================================================");
    }
    
    private static void processar() {
        parseInformacoes(ws77Cad);
        ws77TempoCase = ws03Ano - ws05AnoEntrada;
        
        if (ws77TempoCase >= 0 && ws77TempoCase <= 1) {
            ws77Aumento = 0;
        } else if (ws77TempoCase >= 2 && ws77TempoCase <= 5) {
            ws77Aumento = (ws05Salario / 100.0) * 0.05;
        } else if (ws77TempoCase >= 6 && ws77TempoCase <= 15) {
            ws77Aumento = (ws05Salario / 100.0) * 0.10;
        } else {
            ws77Aumento = (ws05Salario / 100.0) * 0.15;
        }
        
        System.out.println("TEMPO DE CASA LUCAS: " + ws77TempoCase + " ANO(S)");
        System.out.println("AUMENTO LUCAS: R$ " + formatDecimal(ws77Aumento));
        System.out.println("======================================================");
    }
    
    private static void finalizar() {
        parseInformacoes(ws77Cad1);
        ws77TempoCase = ws03Ano - ws05AnoEntrada;
        
        if (ws77TempoCase >= 0 && ws77TempoCase <= 1) {
            ws77Aumento = 0;
        } else if (ws77TempoCase >= 2 && ws77TempoCase <= 5) {
            ws77Aumento = (ws05Salario / 100.0) * 0.05;
        } else if (ws77TempoCase >= 6 && ws77TempoCase <= 15) {
            ws77Aumento = (ws05Salario / 100.0) * 0.10;
        } else {
            ws77Aumento = (ws05Salario / 100.0) * 0.15;
        }
        
        System.out.println("TEMPO DE CASA DANTE: " + ws77TempoCase + " ANO(S)");
        System.out.println("AUMENTO DANTE: R$ " + formatDecimal(ws77Aumento));
        System.out.println("======================================================");
    }
    
    private static void parseInformacoes(String cad) {
        ws05Nome = cad.substring(0, 30).trim();
        ws05AnoEntrada = Integer.parseInt(cad.substring(30, 34).trim());
        ws05Salario = Long.parseLong(cad.substring(34, 42).trim());
    }
    
    private static String formatDecimal(double value) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("0.00", symbols);
        return df.format(value);
    }
}