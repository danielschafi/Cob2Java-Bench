public class ConverteMetrosParaCentimetros {
    private static int wrkNum = 0;
    private static int wrkRepeticao = 0;
    private static int wrkResultado = 0;
    private static int wrkResto = 0;
    private static int wrkCentena = 0;
    private static int wrkDezena = 0;
    private static int wrkUnidade = 0;

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        wrkNum = scanner.nextInt();
        scanner.close();

        while (wrkRepeticao != 1) {
            if (wrkNum > 0 && wrkNum < 1000) {
                wrkResultado = wrkNum / 100;
                wrkResto = wrkNum % 100;
                wrkCentena += wrkResultado;

                if (wrkResto > 0) {
                    wrkResultado = 0;
                    wrkResultado = wrkResto / 10;
                    wrkResto = wrkResto % 10;
                    wrkDezena += wrkResultado;
                }

                if (wrkResto > 0) {
                    wrkResultado = 0;
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
    }
}