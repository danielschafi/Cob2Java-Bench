import java.util.Scanner;

public class PRAC10 {
    static class Participante {
        int numLista;
        String nombre;
        String apellidos;
        int numGpo;
    }

    static int wkEOA = 0;
    static int wkEOB = 0;
    static int wkIndice = 0;
    static Participante[] wsLista = new Participante[3];
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        principal();
    }

    static void principal() {
        wkIndice = 0;
        System.out.println("INGRESE TRES REGISTROS CON FORMATO:");
        System.out.println("####______________________________________##");
        System.out.println("EJ. 0003ANA ISABEL        GARCIA VERA         02");

        while (wkIndice < 3 && wkEOA != 1) {
            llenaTabla();
        }

        while (wkEOB != 1) {
            busca();
        }
    }

    static void llenaTabla() {
        String linea = scanner.nextLine();

        if (linea.length() < 44) {
            linea = String.format("%-44s", linea);
        }

        int numLista = Integer.parseInt(linea.substring(0, 4).trim());

        if (numLista > 0) {
            Participante p = new Participante();
            p.numLista = numLista;
            p.nombre = linea.substring(4, 22).trim();
            p.apellidos = linea.substring(22, 42).trim();
            p.numGpo = Integer.parseInt(linea.substring(42, 44).trim());

            wsLista[wkIndice] = p;
            wkIndice++;
        } else {
            wkEOB = 1;
        }
    }

    static void busca() {
        System.out.println("QUE NUMERO DE LA LISTA QUIERES VER");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            wkEOB = 1;
            return;
        }

        int numLista = Integer.parseInt(input);

        if (numLista > 0) {
            search(numLista);
        } else {
            wkEOB = 1;
        }
    }

    static void search(int numLista) {
        boolean encontrado = false;

        for (int i = 0; i < wkIndice; i++) {
            if (wsLista[i] != null && wsLista[i].numLista == numLista) {
                System.out.println("REGISTRO: " + wsLista[i].numLista + ".-: " +
                        wsLista[i].apellidos + ",    " + wsLista[i].nombre);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("NO EXISTE REGISTRO EN EL NUMERO: " + numLista);
        }
    }
}