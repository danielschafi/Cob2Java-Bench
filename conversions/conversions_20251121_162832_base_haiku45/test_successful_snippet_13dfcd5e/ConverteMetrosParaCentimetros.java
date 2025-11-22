import java.util.Scanner;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ConverteMetrosParaCentimetros {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String wrkTipo = scanner.nextLine().trim();
        int wrkLitros = scanner.nextInt();
        
        double wrkTotal = 0.0;
        double wrkEtanol = 1.90;
        double wrkGasolina = 2.50;
        
        if (wrkTipo.equals("E") && wrkLitros <= 20) {
            wrkTotal = (wrkLitros * wrkEtanol) * 0.97;
            System.out.println("MENOS DE 20 LITROS, DESCONTO DE 3% " + formatOutput(wrkTotal));
        }
        
        if (wrkTipo.equals("E") && wrkLitros > 20) {
            wrkTotal = (wrkLitros * wrkEtanol) * 0.95;
            System.out.println("MAIS DE 20 LITROS, DESCONTO DE 5% " + formatOutput(wrkTotal));
        }
        
        if (wrkTipo.equals("G") && wrkLitros <= 20) {
            wrkTotal = (wrkLitros * wrkGasolina) * 0.96;
            System.out.println("MENOS DE 20 LITROS, DESCONTO DE 4% " + formatOutput(wrkTotal));
        }
        
        if (wrkTipo.equals("G") && wrkLitros > 20) {
            wrkTotal = (wrkLitros * wrkGasolina) * 0.94;
            System.out.println("MAIS DE 20 LITROS, DESCONTO DE 6% " + formatOutput(wrkTotal));
        }
        
        scanner.close();
    }
    
    private static String formatOutput(double value) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("0.00", symbols);
        return df.format(value);
    }
}