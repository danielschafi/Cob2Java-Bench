public class AULA03 {
    private static String WRK_NOME = "               ";
    private static int WRK_IDADE = 0;
    private static long WRK_CPF = 0L;
    private static String WRK_ENDERECO = "                    ";

    private static int WRK_PERFORM = 0;

    private static int WRK_A = 0;
    private static int WRK_B = 0;
    private static int WRK_C = 0;
    private static int WRK_DELTA = 1;

    private static int WRK_QUADRADO = 2;

    private static int WRK_AREA_QUADRADO = 0;

    private static int WRK_RAIO = 0;

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        WRK_A = scanner.nextInt();
        WRK_B = scanner.nextInt();
        WRK_C = scanner.nextInt();

        WRK_DELTA = (WRK_B * WRK_B) - (4 * WRK_A * WRK_C);
        System.out.println("DELTA EH: " + WRK_DELTA);

        WRK_RAIO = 2;
        WRK_AREA_QUADRADO = (int)(3.14 * (WRK_RAIO * WRK_RAIO));
        System.out.println("AREA DO CIRCULO EH: " + WRK_AREA_QUADRADO);

        scanner.close();
    }
}