import java.util.Scanner;

public class ConverteMetrosParaCentimetros {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        char wrkTipo;
        int wrkLitros;
        double wrkTotal;
        final double wrkEtanol = 1.90;
        final double wrkGasolina = 2.50;
        
        System.out.print("Digite o tipo de combustível (E para etanol, G para gasolina): ");
        wrkTipo = scanner.next().charAt(0);
        System.out.print("Digite a quantidade de litros: ");
        wrkLitros = scanner.nextInt();
        
        if (wrkTipo == 'E' && wrkLitros <= 20) {
            wrkTotal = (wrkLitros * wrkEtanol) * 0.97;
            System.out.println("MENOS DE 20 LITROS, DESCONTO DE 3% " + String.format("%.2f", wrkTotal));
        }
        
        if (wrkTipo == 'E' && wrkLitros > 20) {
            wrkTotal = (wrkLitros * wrkEtanol) * 0.95;
            System.out.println("MAIS DE 20 LITROS, DESCONTO DE 5% " + String.format("%.2f", wrkTotal));
        }
        
        if (wrkTipo == 'G' && wrkLitros <= 20) {
            wrkTotal = (wrkLitros * wrkGasolina) * 0.94;
            System.out.println("MENOS DE 20 LITROS, DESCONTO DE 6% " + String.format("%.2f", wrkTotal));
        }
        
        if (wrkTipo == 'G' && wrkLitros > 20) {
            wrkTotal = (wrkLitros * wrkGasolina) * 0.96;
            System.out.println("MAIS DE 20 LITROS, DESCONTO DE 4% " + String.format("%.2f", wrkTotal));
        }
        
        scanner.close();
    }
}