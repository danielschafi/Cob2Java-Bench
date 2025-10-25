import java.util.Scanner;

public class Aula03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int wrkA = 0;
        int wrkB = 0;
        int wrkC = 0;
        int wrkDelta = 1;
        int wrkRaio = 2;
        double wrkAreaQuadrado = 0;

        wrkA = scanner.nextInt();
        wrkB = scanner.nextInt();
        wrkC = scanner.nextInt();

        wrkDelta = (wrkB * wrkB) - (4 * wrkA * wrkC);
        System.out.println("DELTA EH: " + wrkDelta);

        wrkRaio = 2;
        wrkAreaQuadrado = 3.14 * (wrkRaio * wrkRaio);
        System.out.println("AREA DO CIRCULO EH: " + wrkAreaQuadrado);

        scanner.close();
    }
}