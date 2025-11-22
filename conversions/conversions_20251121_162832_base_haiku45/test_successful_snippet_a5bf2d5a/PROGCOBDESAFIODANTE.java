import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class PROGCOBDESAFIODANTE {
    
    private static double ws77Aumento = 0;
    private static int ws77TempoCase = 0;
    
    private static final String WS77_CAD = "LUCAS DA ROSA MAGALHAES       201300500000";
    private static final String WS77_CAD1 = "DANTE ROBERTO DE VIT LUNARDI  198500200000";
    
    private static int ws03Ano = 0;
    private static int ws03Mes = 0;
    private static int ws03Dia = 0;
    
    private static String ws05Nome = "";
    private static int ws05AnoEntrada = 0;
    private static long ws05Salario = 0;
    
    public static void main(String[] args) {
        iniciar();
        processar();
        finalizar();
    }
    
    private static void iniciar() {
        LocalDate hoje = LocalDate.now();
        ws03Ano = hoje.getYear();
        ws03Mes = hoje.getMonthValue();
        ws03Dia = hoje.getDayOfMonth();
        
        System.out.println("======================================================");
        System.out.println("DATA ATUAL: " + String.format("%02d", ws03Dia) + "/" + 
                          String.format("%02d", ws03Mes) + "/" + ws03Ano);
        System.out.println("======================================================");
        
        parseInformacoes(WS77_CAD);
        System.out.println("CAD: " + ws05Nome + " " + ws05AnoEntrada + " " + ws05Salario);
        
        parseInformacoes(WS77_CAD1);
        System.out.println("CAD1: " + ws05Nome + " " + ws05AnoEntrada + " " + ws05Salario);
        
        System.out.println("======================================================");
    }
    
    private static void processar() {
        parseInformacoes(WS77_CAD);
        ws77TempoCase = ws03Ano - ws05AnoEntrada;
        
        if (ws77TempoCase >= 0 && ws77TempoCase <= 1) {
            ws77Aumento = 0;
        } else if (ws77TempoCase >= 2 && ws77TempoCase <= 5) {
            ws77Aumento = (ws05Salario / 100.0) * 5;
        } else if (ws77TempoCase >= 6 && ws77TempoCase <= 15) {
            ws77Aumento = (ws05Salario / 100.0) * 10;
        } else {
            ws77Aumento = (ws05Salario / 100.0) * 15;
        }
        
        System.out.println("TEMPO DE CASA LUCAS: " + ws77TempoCase + " ANO(S)");
        System.out.println("AUMENTO LUCAS: R$ " + formatDecimal(ws77Aumento));
        System.out.println("======================================================");
    }
    
    private static void finalizar() {
        parseInformacoes(WS77_CAD1);
        ws77TempoCase = ws03Ano - ws05AnoEntrada;
        
        if (ws77TempoCase >= 0 && ws77TempoCase <= 1) {
            ws77Aumento = 0;
        } else if (ws77TempoCase >= 2 && ws77TempoCase <= 5) {
            ws77Aumento = (ws05Salario / 100.0) * 5;
        } else if (ws77TempoCase >= 6 && ws77TempoCase <= 15) {
            ws77Aumento = (ws05Salario / 100.0) * 10;
        } else {
            ws77Aumento = (ws05Salario / 100.0) * 15;
        }
        
        System.out.println("TEMPO DE CASA DANTE: " + ws77TempoCase + " ANO(S)");
        System.out.println("AUMENTO DANTE: R$ " + formatDecimal(ws77Aumento));
        System.out.println("======================================================");
    }
    
    private static void parseInformacoes(String cad) {
        ws05Nome = cad.substring(0, 30).trim();
        ws05AnoEntrada = Integer.parseInt(cad.substring(30, 34));
        ws05Salario = Long.parseLong(cad.substring(34, 42));
    }
    
    private static String formatDecimal(double value) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("0.00", symbols);
        return df.format(value);
    }
}