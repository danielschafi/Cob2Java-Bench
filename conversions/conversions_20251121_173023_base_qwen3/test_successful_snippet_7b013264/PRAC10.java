import java.util.Scanner;

public class PRAC10 {
    private static int wkEoa = 0;
    private static int wkEob = 0;
    private static int wkIndice = 0;
    
    private static class ListaParticipantes {
        int wsNumlista;
        String wsNombre;
        String wsApellidos;
        int wsNumgpo;
    }
    
    private static ListaParticipantes[] wsLista = new ListaParticipantes[3];
    
    static {
        for (int i = 0; i < 3; i++) {
            wsLista[i] = new ListaParticipantes();
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("INGRESE TRES REGISTROS CON FORMATO:");
        System.out.println("####______________________________________##");
        System.out.println("EJ. 0003ANA ISABEL        GARCIA VERA         02");
        
        while (wkIndice < 3 && wkEoa == 0) {
            llenarTabla(scanner);
        }
        
        while (wkEob == 0) {
            buscar(scanner);
        }
        
        scanner.close();
    }
    
    private static void llenarTabla(Scanner scanner) {
        System.out.print("Ingrese registro: ");
        String lineaCaptura = scanner.nextLine();
        
        if (lineaCaptura.length() >= 4) {
            try {
                int numlista = Integer.parseInt(lineaCaptura.substring(0, 4));
                if (numlista > 0) {
                    wkIndice++;
                    wsLista[wkIndice - 1].wsNumlista = numlista;
                    
                    if (lineaCaptura.length() >= 22) {
                        wsLista[wkIndice - 1].wsNombre = lineaCaptura.substring(4, 22).trim();
                    } else {
                        wsLista[wkIndice - 1].wsNombre = lineaCaptura.substring(4).trim();
                    }
                    
                    if (lineaCaptura.length() >= 42) {
                        wsLista[wkIndice - 1].wsApellidos = lineaCaptura.substring(22, 42).trim();
                    } else {
                        wsLista[wkIndice - 1].wsApellidos = lineaCaptura.substring(22).trim();
                    }
                    
                    if (lineaCaptura.length() >= 44) {
                        wsLista[wkIndice - 1].wsNumgpo = Integer.parseInt(lineaCaptura.substring(42, 44));
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
    
    private static void buscar(Scanner scanner) {
        System.out.print("QUE NUMERO DE LA LISTA QUIERES VER: ");
        String entrada = scanner.nextLine();
        
        try {
            int numlista = Integer.parseInt(entrada);
            if (numlista > 0) {
                buscarRegistro(numlista);
            } else {
                wkEob = 1;
            }
        } catch (NumberFormatException e) {
            wkEob = 1;
        }
    }
    
    private static void buscarRegistro(int numlista) {
        int indice = 0;
        boolean encontrado = false;
        
        while (indice < wkIndice && !encontrado) {
            if (wsLista[indice].wsNumlista == numlista) {
                System.out.println("REGISTRO: " + wsLista[indice].wsNumlista + 
                                 ".-: " + wsLista[indice].wsApellidos + 
                                 ",    " + wsLista[indice].wsNombre);
                encontrado = true;
            }
            indice++;
        }
        
        if (!encontrado) {
            System.out.println("NO EXISTE REGISTRO EN EL NUMERO: " + numlista);
        }
    }
}