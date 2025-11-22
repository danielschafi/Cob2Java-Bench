import java.util.Scanner;

public class CaixaEletronico {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int wrkValor = 0;
        int wrkResultado = 0;
        int wrkResto = 0;
        int wrk100 = 0;
        int wrk50 = 0;
        int wrk10 = 0;
        int wrk5 = 0;
        int wrk2 = 0;
        int wrkRepeticao = 0;

        while (wrkRepeticao == 0) {
            System.out.print("Digite um valor entre 10 e 1000: ");
            wrkValor = scanner.nextInt();

            if (wrkValor >= 10 && wrkValor <= 1000) {
                wrkResultado = wrkValor / 100;
                wrkResto = wrkValor % 100;
                wrk100 += wrkResultado;

                if (wrkResto > 0) {
                    wrkResultado = wrkResto / 50;
                    wrkResto %= 50;
                    wrk50 += wrkResultado;
                }

                if (wrkResto > 0) {
                    wrkResultado = wrkResto / 10;
                    wrkResto %= 10;
                    wrk10 += wrkResultado;
                }

                if (wrkResto > 0) {
                    wrkResultado = wrkResto / 5;
                    wrkResto %= 5;
                    wrk5 += wrkResultado;
                }

                if (wrkResto > 0) {
                    wrkResultado = wrkResto / 2;
                    wrkResto %= 2;
                    wrk2 += wrkResultado;
                }

                System.out.println("NOTAS DE 100: " + wrk100);
                System.out.println("NOTAS DE 50: " + wrk50);
                System.out.println("NOTAS DE 10: " + wrk10);
                System.out.println("NOTAS DE 5: " + wrk5);
                System.out.println("NOTAS DE 2: " + wrk2);

                wrkRepeticao = 1;
            } else {
                System.out.println("VALOR INVALIDO, DIGITE NOVAMENTE");
            }
        }
        scanner.close();
    }
}