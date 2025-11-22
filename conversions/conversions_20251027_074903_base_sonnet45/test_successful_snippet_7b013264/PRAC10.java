import java.util.Scanner;

public class PRAC10 {
    
    static class Participant implements Comparable<Participant> {
        int numLista;
        String nombre;
        String apellidos;
        int numGpo;
        
        public Participant() {
            this.numLista = 0;
            this.nombre = "";
            this.apellidos = "";
            this.numGpo = 0;
        }
        
        @Override
        public int compareTo(Participant other) {
            return Integer.compare(this.numLista, other.numLista);
        }
    }
    
    private static int wkEOA = 0;
    private static int wkEOB = 0;
    private static int wksNumLista = 0;
    private static String wksNombre = "";
    private static String wksApellidos = "";
    private static int wksNumGpo = 0;
    private static Participant[] wsLista = new Participant[3];
    private static int wkIndice = 0;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            wsLista[i] = new Participant();
        }
        
        principalSection();
    }
    
    private static void principalSection() {
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
        
        scanner.close();
    }
    
    private static void llenaTabla() {
        if (scanner.hasNextLine()) {
            String lineaCaptura = scanner.nextLine();
            
            if (lineaCaptura.length() >= 44) {
                try {
                    wksNumLista = Integer.parseInt(lineaCaptura.substring(0, 4).trim());
                    wksNombre = lineaCaptura.substring(4, 22);
                    wksApellidos = lineaCaptura.substring(22, 42);
                    wksNumGpo = Integer.parseInt(lineaCaptura.substring(42, 44).trim());
                } catch (NumberFormatException e) {
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
        } else {
            wkEOA = 1;
        }
    }
    
    private static void busca() {
        System.out.println("QUE NUMERO DE LA LISTA QUIERES VER");
        
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            try {
                wksNumLista = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                wksNumLista = 0;
            }
            
            if (wksNumLista > 0) {
                searchSection();
            } else {
                wkEOB = 1;
            }
        } else {
            wkEOB = 1;
        }
    }
    
    private static void searchSection() {
        boolean found = false;
        
        for (int i = 0; i < 3; i++) {
            if (wsLista[i].numLista == wksNumLista) {
                System.out.println("REGISTRO: " + wsLista[i].numLista + 
                                   ".-: " + wsLista[i].apellidos + 
                                   ",    " + wsLista[i].nombre);
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("NO EXISTE REGISTRO EN EL NUMERO: " + wksNumLista);
        }
    }
}