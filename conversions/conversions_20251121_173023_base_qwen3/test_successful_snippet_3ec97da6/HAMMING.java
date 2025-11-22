import java.util.Scanner;

public class HAMMING {
    private static int wksOption = 0;
    private static String binaryWord = "1101000";
    private static int[] hammingArray = new int[11];
    private static int[] wrongHammingArray = new int[11];
    private static int[] binaryWordArray = new int[7];
    private static int parity1;
    private static int parity2;
    private static int parity4;
    private static int parity8;
    private static char letra;
    private static int residueParity;
    private static double result;
    private static int errorPosition = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (wksOption != 2) {
            mainMenu(scanner);
        }
        
        scanner.close();
    }
    
    private static void mainMenu(Scanner scanner) {
        System.out.println("Hamming (11,7)");
        System.out.println("1. Calcular Hamming");
        System.out.println("2. Salir");
        System.out.println("Elige una opcion: ");
        wksOption = scanner.nextInt();
        
        if (wksOption == 1) {
            System.out.println("--------------------------------------------");
            System.out.println("Introduce letra en minuscula a evaluar");
            System.out.println("Ejemplo => 'h'");
            System.out.println("--------------------------------------------");
            
            scanner.nextLine(); // consume newline
            letra = scanner.nextLine().charAt(0);
            
            transLetBin();
            
            fillHamming();
            
            calcParityBits();
            System.out.println("-----------------------------------------");
            System.out.println("Su hamming es: ");
            for (int i = 0; i < 11; i++) {
                System.out.print(hammingArray[i]);
            }
            System.out.println();
            System.out.println("-----------------------------------------");
            System.out.println("Introduce el hamming a evaluar");
            System.out.println("Deben ser 11 bits !!! ");
            
            scanner.nextLine(); // consume newline
            String input = scanner.nextLine();
            for (int i = 0; i < 11; i++) {
                wrongHammingArray[i] = Character.getNumericValue(input.charAt(i));
            }
            
            calcParityBitsWrong();
            findErrorPosition();
            
        } else if (wksOption == 2) {
            System.out.println("Adios...");
        } else {
            System.out.println("Opcion no valida");
        }
    }
    
    private static void transLetBin() {
        switch (letra) {
            case 'a': binaryWord = "1100001"; break;
            case 'b': binaryWord = "1100010"; break;
            case 'c': binaryWord = "1100011"; break;
            case 'd': binaryWord = "1100100"; break;
            case 'e': binaryWord = "1100101"; break;
            case 'f': binaryWord = "1100110"; break;
            case 'g': binaryWord = "1100111"; break;
            case 'h': binaryWord = "1101000"; break;
            case 'i': binaryWord = "1101001"; break;
            case 'j': binaryWord = "1101010"; break;
            case 'k': binaryWord = "1101011"; break;
            case 'l': binaryWord = "1101100"; break;
            case 'm': binaryWord = "1101101"; break;
            case 'n': binaryWord = "1101110"; break;
            case 'o': binaryWord = "1101111"; break;
            case 'p': binaryWord = "1110000"; break;
            case 'q': binaryWord = "1110001"; break;
            case 'r': binaryWord = "1110010"; break;
            case 's': binaryWord = "1110011"; break;
            case 't': binaryWord = "1110100"; break;
            case 'u': binaryWord = "1110101"; break;
            case 'v': binaryWord = "1110110"; break;
            case 'w': binaryWord = "1110111"; break;
            case 'x': binaryWord = "1111000"; break;
            case 'y': binaryWord = "1111001"; break;
            case 'z': binaryWord = "1111010"; break;
            default: 
                System.out.println("Opcion no valida");
                return;
        }
        
        for (int i = 0; i < 7; i++) {
            binaryWordArray[i] = Character.getNumericValue(binaryWord.charAt(i));
        }
    }
    
    private static void fillHamming() {
        for (int i = 0; i < 11; i++) {
            hammingArray[i] = 0;
        }
        hammingArray[0] = 0;
        hammingArray[1] = 0;
        hammingArray[2] = binaryWordArray[0];
        hammingArray[3] = 0;
        hammingArray[4] = binaryWordArray[1];
        hammingArray[5] = binaryWordArray[2];
        hammingArray[6] = binaryWordArray[3];
        hammingArray[7] = 0;
        hammingArray[8] = binaryWordArray[4];
        hammingArray[9] = binaryWordArray[5];
        hammingArray[10] = binaryWordArray[6];
    }
    
    private static void calcParityBits() {
        parity1 = 0;
        parity1 = hammingArray[2] + hammingArray[4] + hammingArray[6] + hammingArray[8] + hammingArray[10];
        
        parity2 = 0;
        parity2 = hammingArray[2] + hammingArray[5] + hammingArray[6] + hammingArray[9] + hammingArray[10];
        
        parity4 = 0;
        parity4 = hammingArray[4] + hammingArray[5] + hammingArray[6];
        
        parity8 = 0;
        parity8 = hammingArray[8] + hammingArray[9] + hammingArray[10];
        
        if (parity1 == 1 || parity1 == 3 || parity1 == 5 || parity1 == 7) {
            hammingArray[0] = 1;
        } else {
            hammingArray[0] = 0;
        }
        
        if (parity2 == 1 || parity2 == 3 || parity2 == 5 || parity2 == 7) {
            hammingArray[1] = 1;
        } else {
            hammingArray[1] = 0;
        }
        
        if (parity4 == 1 || parity4 == 3 || parity4 == 5 || parity4 == 7) {
            hammingArray[3] = 1;
        } else {
            hammingArray[3] = 0;
        }
        
        if (parity8 == 1 || parity8 == 3 || parity8 == 5 || parity8 == 7) {
            hammingArray[7] = 1;
        } else {
            hammingArray[7] = 0;
        }
    }
    
    private static void calcParityBitsWrong() {
        System.out.println("Hamming a evaluar: ");
        for (int i = 0; i < 11; i++) {
            System.out.print(wrongHammingArray[i]);
        }
        System.out.println();
        
        parity1 = 0;
        parity1 = wrongHammingArray[2] + wrongHammingArray[4] + wrongHammingArray[6] + wrongHammingArray[8] + wrongHammingArray[10];
        
        parity2 = 0;
        parity2 = wrongHammingArray[2] + wrongHammingArray[5] + wrongHammingArray[6] + wrongHammingArray[9] + wrongHammingArray[10];
        
        parity4 = 0;
        parity4 = wrongHammingArray[4] + wrongHammingArray[5] + wrongHammingArray[6];
        
        parity8 = 0;
        parity8 = wrongHammingArray[8] + wrongHammingArray[9] + wrongHammingArray[10];
        
        if (parity1 == 1 || parity1 == 3 || parity1 == 5 || parity1 == 7) {
            parity1 = 1;
        } else {
            parity1 = 0;
        }
        
        if (parity2 == 1 || parity2 == 3 || parity2 == 5 || parity2 == 7) {
            parity2 = 1;
        } else {
            parity2 = 0;
        }
        
        if (parity4 == 1 || parity4 == 3 || parity4 == 5 || parity