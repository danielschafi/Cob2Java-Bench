import java.util.Scanner;

public class PRAC10 {
    static class Participant {
        int numLista;
        String nombre;
        String apellidos;
        int numGpo;
    }
    
    static Participant[] wsLista = new Participant[3];
    static int wkIndice = 0;
    static int wkEoa = 0;
    static int wkEob = 0;
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            wsLista[i] = new Participant();
        }
        
        System.out.println("INGRESE TRES REGISTROS CON FORMATO:");
        System.out.println("####______________________________________##");
        System.out.println("EJ. 0003ANA ISABEL        GARCIA VERA         02");
        
        while (wkIndice <= 2 && wkEoa == 0) {
            llenaTabla();
        }
        
        while (wkEob == 0) {
            busca();
        }
        
        scanner.close();
    }
    
    static void llenaTabla() {
        System.out.print("Ingrese registro: ");
        String lineaCaptura = scanner.nextLine();
        
        if (lineaCaptura.length() >= 4) {
            try {
                int numLista = Integer.parseInt(lineaCaptura.substring(0, 4));
                if (numLista > 0) {
                    wkIndice++;
                    wsLista[wkIndice - 1].numLista = numLista;
                    
                    if (lineaCaptura.length() >= 22) {
                        wsLista[wkIndice - 1].nombre = lineaCaptura.substring(4, 22).trim();
                    } else {
                        wsLista[wkIndice - 1].nombre = lineaCaptura.substring(4).trim();
                    }
                    
                    if (lineaCaptura.length() >= 42) {
                        wsLista[wkIndice - 1].apellidos = lineaCaptura.substring(22, 42).trim();
                    } else {
                        wsLista[wkIndice - 1].apellidos = lineaCaptura.substring(22).trim();
                    }
                    
                    if (lineaCaptura.length() >= 44) {
                        wsLista[wkIndice - 1].numGpo = Integer.parseInt(lineaCaptura.substring(42, 44));
                    }
                } else {
                    wkEoa = 1;
                }
            } catch (NumberFormatException e) {
                wkEoa = 1;
            }
        } else {
            wkEoa = 1;
        }
    }
    
    static void busca() {
        System.out.print("QUE NUMERO DE LA LISTA QUIERES VER: ");
        String input = scanner.nextLine();
        try {
            int numLista = Integer.parseInt(input);
            if (numLista > 0) {
                search(numLista);
            } else {
                wkEob = 1;
            }
        } catch (NumberFormatException e) {
            wkEob = 1;
        }
    }
    
    static void search(int numLista) {
        for (int i = 0; i < wkIndice; i++) {
            if (wsLista[i].numLista == numLista) {
                System.out.println("REGISTRO: " + wsLista[i].numLista + 
                                 ".-: " + wsLista[i].apellidos + 
                                 ",    " + wsLista[i].nombre);
                return;
            }
        }
        System.out.println("NO EXISTE REGISTRO EN EL NUMERO: " + numLista);
    }
}