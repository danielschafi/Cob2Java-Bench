import java.util.Scanner;

public class MediaAritmetica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int wrkIdade = 0;
        int wrkSoma = 0;
        int wrkRepeticao = 0;
        int wrkContador = 0;
        int wrkMedia = 0;

        while (wrkRepeticao == 0) {
            System.out.print("Digite N idades, e 0 para calcular: ");
            wrkIdade = scanner.nextInt();

            if (wrkIdade != 0) {
                wrkSoma += wrkIdade;
                wrkContador++;
            }

            if (wrkIdade == 0) {
                wrkRepeticao++;
                wrkMedia = wrkSoma / wrkContador;

                if (wrkMedia > 0 && wrkMedia <= 25) {
                    System.out.println("Turma jovem!");
                }

                if (wrkMedia > 25 && wrkMedia <= 60) {
                    System.out.println("Turma adulta");
                }

                if (wrkMedia > 60) {
                    System.out.println("Turma idosa");
                }
            }
        }

        scanner.close();
    }
}