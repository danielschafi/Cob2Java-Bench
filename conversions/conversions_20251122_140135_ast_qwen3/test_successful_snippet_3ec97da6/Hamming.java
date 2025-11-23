import java.util.Scanner;

public class Hamming {
    private static int[] wksOption = new int[1];
    private static char[] binaryWord = new char[7];
    private static int[] hammingArray = new int[11];
    private static int[] wrongHammingArray = new int[11];
    private static int[] binaryWordArray = new int[7];
    private static int parity1;
    private static int parity2;
    private static int parity4;
    private static int parity8;
    private static char[] letra = new char[1];
    private static int residueParity;
    private static double result;
    private static int errorPosition = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Initialize binaryWord with default value
        binaryWord[0] = '1';
        binaryWord[1] = '1';
        binaryWord[2] = '0';
        binaryWord[3] = '1';
        binaryWord[4] = '0';
        binaryWord[5] = '0';
        binaryWord[6] = '0';

        while (true) {
            mainMenu(scanner);
            if (wksOption[0] == 2) {
                System.out.println("Adios...");
                break;
            }
        }
        scanner.close();
    }

    private static void mainMenu(Scanner scanner) {
        System.out.println("Hamming (11,7)");
        System.out.println("1. Calcular Hamming");
        System.out.println("2. Salir");
        System.out.println("Elige una opcion: ");
        wksOption[0] = scanner.nextInt();
        
        if (wksOption[0] == 1) {
            System.out.println("--------------------------------------------");
            System.out.println("Introduce letra en minuscula a evaluar");
            System.out.println("Ejemplo => 'h'");
            System.out.println("--------------------------------------------");
            
            scanner.nextLine(); // Consume newline
            letra[0] = scanner.nextLine().charAt(0);
            
            transLetBin();
            
            fillHamming();
            
            calcParityBits();
            
            System.out.println("-----------------------------------------");
            System.out.println("Su hamming es: ");
            displayArray(hammingArray);
            System.out.println("-----------------------------------------");
            
            System.out.println("Introduce el hamming a evaluar");
            System.out.println("Deben ser 11 bits !!! ");
            
            scanner.nextLine(); // Consume newline
            String input = scanner.nextLine();
            for (int i = 0; i < 11 && i < input.length(); i++) {
                wrongHammingArray[i] = Character.getNumericValue(input.charAt(i));
            }
            
            calcParityBitsWrong();
            
            findErrorPosition();
        } else if (wksOption[0] == 2) {
            System.out.println("Adios...");
        } else {
            System.out.println("Opcion no valida");
        }
    }

    private static void transLetBin() {
        switch (letra[0]) {
            case 'a':
                binaryWordArray[0] = 1; binaryWordArray[1] = 1; binaryWordArray[2] = 0; 
                binaryWordArray[3] = 0; binaryWordArray[4] = 0; binaryWordArray[5] = 0; 
                binaryWordArray[6] = 1;
                break;
            case 'b':
                binaryWordArray[0] = 1; binaryWordArray[1] = 1; binaryWordArray[2] = 0; 
                binaryWordArray[3] = 0; binaryWordArray[4] = 0; binaryWordArray[5] = 1; 
                binaryWordArray[6] = 0;
                break;
            case 'c':
                binaryWordArray[0] = 1; binaryWordArray[1] = 1; binaryWordArray[2] = 0; 
                binaryWordArray[3] = 0; binaryWordArray[4] = 0; binaryWordArray[5] = 1; 
                binaryWordArray[6] = 1;
                break;
            case 'd':
                binaryWordArray[0] = 1; binaryWordArray[1] = 1; binaryWordArray[2] = 0; 
                binaryWordArray[3] = 0; binaryWordArray[4] = 1; binaryWordArray[5] = 0; 
                binaryWordArray[6] = 0;
                break;
            case 'e':
                binaryWordArray[0] = 1; binaryWordArray[1] = 1; binaryWordArray[2] = 0; 
                binaryWordArray[3] = 0; binaryWordArray[4] = 1; binaryWordArray[5] = 0; 
                binaryWordArray[6] = 1;
                break;
            case 'f':
                binaryWordArray[0] = 1; binaryWordArray[1] = 1; binaryWordArray[2] = 0; 
                binaryWordArray[3] = 0; binaryWordArray[4] = 1; binaryWordArray[5] = 1; 
                binaryWordArray[6] = 0;
                break;
            case 'g':
                binaryWordArray[0] = 1; binaryWordArray[1] = 1; binaryWordArray[2] = 0; 
                binaryWordArray[3] = 0; binaryWordArray[4] = 1; binaryWordArray[5] = 1; 
                binaryWordArray[6] = 1;
                break;
            case 'h':
                binaryWordArray[0] = 1; binaryWordArray[1] = 1; binaryWordArray[2] = 0; 
                binaryWordArray[3] = 1; binaryWordArray[4] = 0; binaryWordArray[5] = 0; 
                binaryWordArray[6] = 0;
                break;
            case 'i':
                binaryWordArray[0] = 1; binaryWordArray[1] = 1; binaryWordArray[2] = 0; 
                binaryWordArray[3] = 1; binaryWordArray[4] = 0; binaryWordArray[5] = 0; 
                binaryWordArray[6] = 1;
                break;
            case 'j':
                binaryWordArray[0] = 1; binaryWordArray[1] = 1; binaryWordArray[2] = 0; 
                binaryWordArray[3] = 1; binaryWordArray[4] = 0; binaryWordArray[5] = 1; 
                binaryWordArray[6] = 0;
                break;
            case 'k':
                binaryWordArray[0] = 1; binaryWordArray[1] = 1; binaryWordArray[2] = 0; 
                binaryWordArray[3] = 1; binaryWordArray[4] = 0; binaryWordArray[5] = 1; 
                binaryWordArray[6] = 1;
                break;
            case 'l':
                binaryWordArray[0] = 1; binaryWordArray[1] = 1; binaryWordArray[2] = 0; 
                binaryWordArray[3] = 1; binaryWordArray[4] = 1; binaryWordArray[5] = 0; 
                binaryWordArray[6] = 0;
                break;
            case 'm':
                binaryWordArray[0] = 1; binaryWordArray[1] = 1; binaryWordArray[2] = 0; 
                binaryWordArray[3] = 1; binaryWordArray[4] = 1; binaryWordArray[5] = 0; 
                binaryWordArray[6] = 1;
                break;
            case 'n':
                binaryWordArray[0] = 1; binaryWordArray[1] = 1; binaryWordArray[2] = 0; 
                binaryWordArray[3] = 1; binaryWordArray[4] = 1; binaryWordArray[5] = 1; 
                binaryWordArray[6] = 0;
                break;
            case 'o':
                binaryWordArray[0] = 1; binaryWordArray[1] = 1; binaryWordArray[2] = 0; 
                binaryWordArray[3] = 1; binaryWordArray[4] = 1; binaryWordArray[5] = 1; 
                binaryWordArray[6] = 1;
                break;
            case 'p':
                binaryWordArray[0] = 1; binaryWordArray[1] = 1; binaryWordArray[2] = 1; 
                binaryWordArray[3] = 0; binaryWordArray[4] = 0; binaryWordArray[5] = 0; 
                binaryWordArray[6] = 0;
                break;
            case 'q':
                binaryWordArray[0] = 1; binaryWordArray[1] = 1; binaryWordArray[2] = 1; 
                binaryWordArray[3] = 0; binaryWordArray[4] = 0; binaryWordArray[5]