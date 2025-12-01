import java.util.Scanner;
import java.util.Arrays;

public class PRAC10 {
    static class Participant {
        int numLista;
        String nombre;
        String apellidos;
        int numGpo;
        
        Participant() {
            this.numLista = 0;
            this.nombre = "";
            this.apellidos = "";
            this.numGpo = 0;
        }
    }
    
    static int wkEOA = 0;
    static int wkEOB = 0;
    static int wksNumLista = 0;
    static String wksNombre = "";
    static String wksApellidos = "";
    static int wksNumGpo = 0;
    static Participant[] wsLista = new Participant[3];
    static int wkIndice = 0;
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            wsLista[i] = new Participant();
        }
        
        principal();
    }
    
    static void principal() {
        wkIndice = 0;
        System.out.println("INGRESE TRES REGISTROS CON FORMATO:");
        System.out.println("####______________________________________##");
        System.out.println("EJ. 0003ANA ISABEL        GARCIA VERA         02");
        
        while (wkIndice <= 2 && wkEOA != 1) {
            llenaTabla();
        }
        
        while (wkEOB != 1) {
            busca();
        }
    }
    
    static void llenaTabla() {
        String input = scanner.nextLine();
        
        if (input.length() >= 44) {
            try {
                wksNumLista = Integer.parseInt(input.substring(0, 4).trim());
                wksNombre = input.substring(4, 22);
                wksApellidos = input.substring(22, 42);
                wksNumGpo = Integer.parseInt(input.substring(42, 44).trim());
            } catch (Exception e) {
                wksNumLista = 0;
            }
        } else {
            wksNumLista = 0;
        }
        
        if (wksNumLista > 0) {
            wsLista[wkIndice].numLista = wksNumLista;
            wsLista[wkIndice].nombre = wksNombre;
            wsLista[wkIndice].apellidos = wksApellidos;
            wsLista[wkIndice].numGpo = wksNumGpo;
            wkIndice++;
        } else {
            wkEOB = 1;
        }
    }
    
    static void busca() {
        System.out.println("QUE NUMERO DE LA LISTA QUIERES VER");
        String input = scanner.nextLine().trim();
        
        try {
            wksNumLista = Integer.parseInt(input);
        } catch (Exception e) {
            wksNumLista = 0;
        }
        
        if (wksNumLista > 0) {
            search();
        } else {
            wkEOB = 1;
        }
    }
    
    static void search() {
        wkIndice = 0;
        boolean found = false;
        
        for (int i = 0; i < 3; i++) {
            if (wsLista[i].numLista == wksNumLista) {
                wkIndice = i;
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("NO EXISTE REGISTRO EN EL NUMERO: " + wksNumLista);
        } else {
            System.out.println("REGISTRO: " + wsLista[wkIndice].numLista + ".-: " + 
                             wsLista[wkIndice].apellidos + ",    " + wsLista[wkIndice].nombre);
        }
    }
}