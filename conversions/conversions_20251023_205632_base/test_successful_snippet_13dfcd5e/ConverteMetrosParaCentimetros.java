import java.util.Scanner;

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
            System.out.println("MENOS DE 20 LITROS, DESCONTO DE 3% " + wrkTotal);
        }

        if (wrkTipo.equals("E") && wrkLitros > 20) {
            wrkTotal = (wrkLitros * wrkEtanol) * 0.95;
            System.out.println("MAIS DE 20 LITROS, DESCONTO DE 5% " + wrkTotal);
        }

        if (wrkTipo.equals("G") && wrkLitros <= 20) {
            wrkTotal = (wrkLitros * wrkGasolina) * 0.96;
            System.out.println("MENOS DE 20 LITROS, DESCONTO DE 4% " + wrkTotal);
        }

        if (wrkTipo.equals("G") && wrkLitros > 20) {
            wrkTotal = (wrkLitros * wrkGasolina) * 0.94;
            System.out.println("MAIS DE 20 LITROS, DESCONTO DE 6% " + wrkTotal);
        }

        scanner.close();
    }
}