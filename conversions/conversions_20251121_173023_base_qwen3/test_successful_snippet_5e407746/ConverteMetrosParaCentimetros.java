import java.util.Scanner;

public class ConverteMetrosParaCentimetros {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int wrkNum1 = 0;
        int wrkNum2 = 0;
        int wrkNum3 = 0;
        int wrkTotal = 0;
        int wrkMedia = 0;
        
        System.out.print("Digite as 3 notas do aluno referido: ");
        wrkNum1 = scanner.nextInt();
        wrkNum2 = scanner.nextInt();
        wrkNum3 = scanner.nextInt();
        
        wrkTotal = wrkNum1 + wrkNum2 + wrkNum3;
        wrkMedia = wrkTotal / 3;
        
        if (wrkMedia >= 7) {
            System.out.println("APROVADO");
        }
        
        if (wrkMedia < 7) {
            System.out.println("REPROVADO");
        }
        
        if (wrkMedia == 10) {
            System.out.println("APROVADO COM SUCESSO");
        }
        
        scanner.close();
    }
}