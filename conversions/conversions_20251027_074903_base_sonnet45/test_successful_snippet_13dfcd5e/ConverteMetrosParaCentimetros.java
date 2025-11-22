import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConverteMetrosParaCentimetros {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String wrkTipo = "";
        int wrkLitros = 0;
        BigDecimal wrkTotal = BigDecimal.ZERO;
        BigDecimal wrkEtanol = new BigDecimal("1.90");
        BigDecimal wrkGasolina = new BigDecimal("2.50");
        
        wrkTipo = scanner.nextLine().trim();
        wrkLitros = scanner.nextInt();
        
        if (wrkTipo.equals("E") && wrkLitros <= 20) {
            wrkTotal = new BigDecimal(wrkLitros)
                .multiply(wrkEtanol)
                .multiply(new BigDecimal("0.97"))
                .setScale(2, RoundingMode.HALF_UP);
            System.out.println("MENOS DE 20 LITROS, DESCONTO DE 3% " + 
                String.format("%.2f", wrkTotal).replace('.', ','));
        }
        
        if (wrkTipo.equals("E") && wrkLitros > 20) {
            wrkTotal = new BigDecimal(wrkLitros)
                .multiply(wrkEtanol)
                .multiply(new BigDecimal("0.95"))
                .setScale(2, RoundingMode.HALF_UP);
            System.out.println("MAIS DE 20 LITROS, DESCONTO DE 5% " + 
                String.format("%.2f", wrkTotal).replace('.', ','));
        }
        
        if (wrkTipo.equals("G") && wrkLitros <= 20) {
            wrkTotal = new BigDecimal(wrkLitros)
                .multiply(wrkGasolina)
                .multiply(new BigDecimal("0.96"))
                .setScale(2, RoundingMode.HALF_UP);
            System.out.println("MENOS DE 20 LITROS, DESCONTO DE 4% " + 
                String.format("%.2f", wrkTotal).replace('.', ','));
        }
        
        if (wrkTipo.equals("G") && wrkLitros > 20) {
            wrkTotal = new BigDecimal(wrkLitros)
                .multiply(wrkGasolina)
                .multiply(new BigDecimal("0.94"))
                .setScale(2, RoundingMode.HALF_UP);
            System.out.println("MAIS DE 20 LITROS, DESCONTO DE 6% " + 
                String.format("%.2f", wrkTotal).replace('.', ','));
        }
        
        scanner.close();
    }
}