public class _010___Media_aritmetica {
    private static int wrkIdade = 0;
    private static int wrkSoma = 0;
    private static int wrkRepeticao = 0;
    private static int wrkContador = 0;
    private static int wrkMedia = 0;

    public static void main(String[] args) {
        while (wrkRepeticao != 1) {
            System.out.print("Digite N idades, e 0 para calcular: ");
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            wrkIdade = scanner.nextInt();

            if (wrkIdade != 0) {
                wrkSoma += wrkIdade;
                wrkContador += 1;
            }

            if (wrkIdade == 0) {
                wrkRepeticao += 1;
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
    }
}