import java.util.Scanner;

public class Tabuada {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int wrkUsuario = 0;
        int wrkNum1 = 0;
        int wrkResultado = 0;
        int wrkRepeticao = 0;
        int wrkNum2 = 0;
        int wrkInc = 0;
        
        System.out.print("Digite o numero desejado: ");
        wrkNum1 = scanner.nextInt();
        
        while (wrkRepeticao != 1) {
            if (wrkNum1 > 0 && wrkNum1 < 11) {
                wrkResultado = wrkNum1 * wrkNum2;
                System.out.println(wrkNum1 + " * " + wrkNum2 + " = " + wrkResultado);
                wrkNum2 = wrkNum2 + 1;
                
                if (wrkNum2 == 11) {
                    wrkRepeticao = wrkRepeticao + 1;
                }
                
                if (wrkNum1 < 0 || wrkNum1 > 10) {
                    System.out.println("Erro, tente novamente!");
                    wrkRepeticao = wrkRepeticao + 1;
                }
            } else {
                wrkRepeticao = 1;
            }
        }
        
        scanner.close();
    }
}