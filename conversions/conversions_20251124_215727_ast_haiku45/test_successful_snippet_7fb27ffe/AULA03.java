import java.util.Scanner;

public class AULA03 {
    static class WrkCadastro {
        String wrkNome = "               ";
        int wrkIdade = 0;
        long wrkCpf = 0;
        String wrkEndereco = "                    ";
    }
    
    static int wrkPerform = 0;
    static int wrkA = 0;
    static int wrkB = 0;
    static int wrkC = 0;
    static int wrkDelta = 1;
    static int wrkQuadrado = 2;
    static int wrkAreaQuadrado = 0;
    static int wrkRaio = 0;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        wrkA = scanner.nextInt();
        wrkB = scanner.nextInt();
        wrkC = scanner.nextInt();
        
        wrkDelta = (wrkB * wrkB) - (4 * wrkA * wrkC);
        System.out.println("DELTA EH: " + wrkDelta);
        
        wrkRaio = 2;
        wrkAreaQuadrado = (int)(3.14 * (wrkRaio * wrkRaio));
        System.out.println("AREA DO CIRCULO EH: " + wrkAreaQuadrado);
        
        scanner.close();
    }
}