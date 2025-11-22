import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PROGCOBDESAFIODANTE {
    
    private double ws77Aumento = 0.0;
    private int ws77TempoCasa = 0;
    
    private String ws77Cad = "LUCAS DA ROSA MAGALHAES       201300500000";
    private String ws77Cad1 = "DANTE ROBERTO DE VIT LUNARDI  198500200000";
    
    private String ws05Nome;
    private int ws05AnoEntrada;
    private long ws05Salario;
    
    private int ws03Ano = 0;
    private int ws03Mes = 0;
    private int ws03Dia = 0;
    
    public static void main(String[] args) {
        PROGCOBDESAFIODANTE program = new PROGCOBDESAFIODANTE();
        program.principal();
    }
    
    private void principal() {
        iniciar();
        processar();
        finalizar();
    }
    
    private void iniciar() {
        LocalDate currentDate = LocalDate.now();
        ws03Ano = currentDate.getYear();
        ws03Mes = currentDate.getMonthValue();
        ws03Dia = currentDate.getDayOfMonth();
        
        System.out.println("======================================================");
        System.out.println("DATA ATUAL: " + String.format("%02d", ws03Dia) + "/" + 
                          String.format("%02d", ws03Mes) + "/" + ws03Ano);
        System.out.println("======================================================");
        
        parseInformacoes(ws77Cad);
        System.out.println("CAD: " + ws05Nome + " " + ws05AnoEntrada + " " + 
                          String.format("%08d", ws05Salario));
        
        parseInformacoes(ws77Cad1);
        System.out.println("CAD1: " + ws05Nome + " " + ws05AnoEntrada + " " + 
                          String.format("%08d", ws05Salario));
        System.out.println("======================================================");
    }
    
    private void processar() {
        parseInformacoes(ws77Cad);
        ws77TempoCasa = ws03Ano - ws05AnoEntrada;
        
        if (ws77TempoCasa >= 0 && ws77TempoCasa <= 1) {
            ws77Aumento = 0.0;
        } else if (ws77TempoCasa >= 2 && ws77TempoCasa <= 5) {
            ws77Aumento = (ws05Salario / 100.0) * 0.05;
        } else if (ws77TempoCasa >= 6 && ws77TempoCasa <= 15) {
            ws77Aumento = (ws05Salario / 100.0) * 0.10;
        } else {
            ws77Aumento = (ws05Salario / 100.0) * 0.15;
        }
        
        System.out.println("TEMPO DE CASA LUCAS: " + ws77TempoCasa + " ANO(S)");
        System.out.println("AUMENTO LUCAS: R$ " + String.format("%.2f", ws77Aumento).replace('.', ','));
        System.out.println("======================================================");
    }
    
    private void finalizar() {
        parseInformacoes(ws77Cad1);
        ws77TempoCasa = ws03Ano - ws05AnoEntrada;
        
        if (ws77TempoCasa >= 0 && ws77TempoCasa <= 1) {
            ws77Aumento = 0.0;
        } else if (ws77TempoCasa >= 2 && ws77TempoCasa <= 5) {
            ws77Aumento = (ws05Salario / 100.0) * 0.05;
        } else if (ws77TempoCasa >= 6 && ws77TempoCasa <= 15) {
            ws77Aumento = (ws05Salario / 100.0) * 0.10;
        } else {
            ws77Aumento = (ws05Salario / 100.0) * 0.15;
        }
        
        System.out.println("TEMPO DE CASA DANTE: " + ws77TempoCasa + " ANO(S)");
        System.out.println("AUMENTO DANTE: R$ " + String.format("%.2f", ws77Aumento).replace('.', ','));
        System.out.println("======================================================");
    }
    
    private void parseInformacoes(String cad) {
        ws05Nome = cad.substring(0, 30);
        ws05AnoEntrada = Integer.parseInt(cad.substring(30, 34));
        ws05Salario = Long.parseLong(cad.substring(34, 42));
    }
}