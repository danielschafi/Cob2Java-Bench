import java.util.Scanner;

public class PRAC10 {
    private int wkEoa;
    private int wkEob;
    private int wkIndice;
    private int[] wsNumlista = new int[3];
    private String[] wsNombre = new String[3];
    private String[] wsApellidos = new String[3];
    private int[] wsNumgpo = new int[3];
    private String wksLineaCaptura;
    private int wksNumlista;
    private String wksNombre;
    private String wksApellidos;
    private int wksNumgpo;

    public static void main(String[] args) {
        PRAC10 prac10 = new PRAC10();
        prac10.principal();
    }

    public void principal() {
        wkIndice = 0;
        System.out.println("INGRESE TRES REGISTROS CON FORMATO:");
        System.out.println("####______________________________________##");
        System.out.println("EJ. 0003ANA ISABEL        GARCIA VERA         02");
        while (wkIndice <= 2 && wkEoa == 0) {
            llenaTabla();
        }
        while (wkEob == 0) {
            busca();
        }
    }

    public void llenaTabla() {
        Scanner scanner = new Scanner(System.in);
        wksLineaCaptura = scanner.nextLine();
        wksNumlista = Integer.parseInt(wksLineaCaptura.substring(0, 4).trim());
        wksNombre = wksLineaCaptura.substring(4, 22).trim();
        wksApellidos = wksLineaCaptura.substring(22, 42).trim();
        wksNumgpo = Integer.parseInt(wksLineaCaptura.substring(42, 44).trim());
        if (wksNumlista > 0) {
            wkIndice++;
            wsNumlista[wkIndice - 1] = wksNumlista;
            wsNombre[wkIndice - 1] = wksNombre;
            wsApellidos[wkIndice - 1] = wksApellidos;
            wsNumgpo[wkIndice - 1] = wksNumgpo;
        } else {
            wkEob = 1;
        }
    }

    public void busca() {
        System.out.println("QUE NUMERO DE LA LISTA QUIERES VER");
        Scanner scanner = new Scanner(System.in);
        wksNumlista = scanner.nextInt();
        if (wksNumlista > 0) {
            search();
        } else {
            wkEob = 1;
        }
    }

    public void search() {
        wkIndice = 0;
        boolean encontrado = false;
        for (int i = 0; i < 3; i++) {
            if (wsNumlista[i] == wksNumlista) {
                encontrado = true;
                wkIndice = i + 1;
                break;
            }
        }
        if (encontrado) {
            System.out.println("REGISTRO: " + wsNumlista[wkIndice - 1] + ".-: " + wsApellidos[wkIndice - 1] + ",    " + wsNombre[wkIndice - 1]);
        } else {
            System.out.println("NO EXISTE REGISTRO EN EL NUMERO: " + wksNumlista);
        }
    }
}