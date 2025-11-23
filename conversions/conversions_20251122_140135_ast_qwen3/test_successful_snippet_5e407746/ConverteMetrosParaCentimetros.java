public class ConverteMetrosParaCentimetros {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        
        int wrkNum1 = 0;
        int wrkNum2 = 0;
        int wrkNum3 = 0;
        int wrkTotal = 0;
        int wrkMedia = 0;
        
        System.out.println("Digite as 3 notas do aluno referido: ");
        wrkNum1 = scanner.nextInt();
        wrkNum2 = scanner.nextInt();
        wrkNum3 = scanner.nextInt();
        
        wrkTotal = wrkNum1 + wrkNum2 + wrkNum3;
        wrkTotal = wrkTotal / 3;
        
        if (wrkTotal >= 7) {
            System.out.println("APROVADO");
        }
        
        if (wrkTotal < 7) {
            System.out.println("REPROVADO");
        }
        
        if (wrkTotal == 10) {
            System.out.println("APROVADO COM SUCESSO");
        }
        
        scanner.close();
    }
}