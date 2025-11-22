import java.util.Scanner;

public class PRAC10 {
    private int WK_EOA;
    private int WK_EOB;
    private int WK_INDICE;
    private int[] WS_NUMLISTA = new int[3];
    private String[] WS_NOMBRE = new String[3];
    private String[] WS_APELLIDOS = new String[3];
    private int[] WS_NUMGPO = new int[3];
    private String WKS_LINEA_CAPTURA;
    private int WKS_NUMLISTA;
    private String WKS_NOMBRE;
    private String WKS_APELLIDOS;
    private int WKS_NUMGPO;

    public static void main(String[] args) {
        PRAC10 program = new PRAC10();
        program.WK_EOA = 0;
        program.WK_EOB = 0;
        program.WK_INDICE = 0;

        System.out.println("INGRESE TRES REGISTROS CON FORMATO:");
        System.out.println("####______________________________________##");
        System.out.println("EJ. 0003ANA ISABEL        GARCIA VERA         02");

        while (program.WK_INDICE <= 2 && program.WK_EOA == 0) {
            program.llenaTabla();
        }

        while (program.WK_EOB == 0) {
            program.busca();
        }
    }

    private void llenaTabla() {
        Scanner scanner = new Scanner(System.in);
        WKS_LINEA_CAPTURA = scanner.nextLine();

        WKS_NUMLISTA = Integer.parseInt(WKS_LINEA_CAPTURA.substring(0, 4).trim());
        WKS_NOMBRE = WKS_LINEA_CAPTURA.substring(4, 22).trim();
        WKS_APELLIDOS = WKS_LINEA_CAPTURA.substring(22, 42).trim();
        WKS_NUMGPO = Integer.parseInt(WKS_LINEA_CAPTURA.substring(42, 44).trim());

        if (WKS_NUMLISTA > 0) {
            WS_NUMLISTA[WK_INDICE] = WKS_NUMLISTA;
            WS_NOMBRE[WK_INDICE] = WKS_NOMBRE;
            WS_APELLIDOS[WK_INDICE] = WKS_APELLIDOS;
            WS_NUMGPO[WK_INDICE] = WKS_NUMGPO;
            WK_INDICE++;
        } else {
            WK_EOA = 1;
        }
    }

    private void busca() {
        System.out.println("QUE NUMERO DE LA LISTA QUIERES VER");
        Scanner scanner = new Scanner(System.in);
        WKS_NUMLISTA = scanner.nextInt();

        if (WKS_NUMLISTA > 0) {
            search();
        } else {
            WK_EOB = 1;
        }
    }

    private void search() {
        WK_INDICE = 0;
        boolean encontrado = false;

        for (int i = 0; i < 3; i++) {
            if (WS_NUMLISTA[i] == WKS_NUMLISTA) {
                System.out.println("REGISTRO: " + WS_NUMLISTA[i] + ".-: " + WS_APELLIDOS[i] + ",    " + WS_NOMBRE[i]);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("NO EXISTE REGISTRO EN EL NUMERO: " + WKS_NUMLISTA);
        }
    }
}