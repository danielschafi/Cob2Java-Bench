import java.util.Scanner;

public class ConverteMetrosParaCentimetros {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int wrkNum = scanner.nextInt();
        int wrkRepeticao = 0;
        int wrkResultado = 0;
        int wrkResto = 0;
        int wrkCentena = 0;
        int wrkDezena = 0;
        int wrkUnidade = 0;

        while (wrkRepeticao == 0) {
            if (wrkNum > 0 && wrkNum < 1000) {
                wrkResultado = wrkNum / 100;
                wrkResto = wrkNum % 100;
                wrkCentena += wrkResultado;

                if (wrkResto > 0) {
                    wrkResultado = wrkResto / 10;
                    wrkResto = wrkResto % 10;
                    wrkDezena += wrkResultado;
                }

                if (wrkResto > 0) {
                    wrkResultado = wrkResto / 1;
                    wrkResto = wrkResto % 1;
                    wrkUnidade += wrkResultado;
                }

                System.out.println("CENTENA: " + wrkCentena);
                System.out.println("DEZENA: " + wrkDezena);
                System.out.println("UNIDADE: " + wrkUnidade);

                wrkRepeticao++;
            }
        }
        scanner.close();
    }
}