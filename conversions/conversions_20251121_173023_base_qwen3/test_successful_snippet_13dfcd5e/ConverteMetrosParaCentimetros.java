public class ConverteMetrosParaCentimetros {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        
        char wrkTipo;
        int wrkLitros;
        double wrkTotal;
        double wrkEtanol = 1.90;
        double wrkGasolina = 2.50;
        
        wrkTipo = scanner.next().charAt(0);
        wrkLitros = scanner.nextInt();
        
        if (wrkTipo == 'E' && wrkLitros <= 20) {
            wrkTotal = (wrkLitros * wrkEtanol) * 0.97;
            System.out.println("MENOS DE 20 LITROS, DESCONTO DE 3% " + wrkTotal);
        }
        
        if (wrkTipo == 'E' && wrkLitros > 20) {
            wrkTotal = (wrkLitros * wrkEtanol) * 0.95;
            System.out.println("MAIS DE 20 LITROS, DESCONTO DE 5% " + wrkTotal);
        }
        
        if (wrkTipo == 'G' && wrkLitros <= 20) {
            wrkTotal = (wrkLitros * wrkGasolina) * 0.90;
            System.out.println("MENOS DE 20 LITROS, DESCONTO DE 4% " + wrkTotal);
        }
        
        if (wrkTipo == 'G' && wrkLitros > 20) {
            wrkTotal = (wrkLitros * wrkGasolina) * 0.94;
            System.out.println("MAIS DE 20 LITROS, DESCONTO DE 6% " + wrkTotal);
        }
        
        scanner.close();
    }
}