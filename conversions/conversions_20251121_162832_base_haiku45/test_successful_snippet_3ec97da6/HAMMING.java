```java
import java.util.Scanner;

public class HAMMING {
    private static int wksOption = 0;
    private static String binaryWord = "1101000";
    private static int[] hammingArray = new int[11];
    private static int[] wrongHammingArray = new int[11];
    private static int[] bitBinaryWord = new int[7];
    private static int parity1 = 0;
    private static int parity2 = 0;
    private static int parity4 = 0;
    private static int parity8 = 0;
    private static char letra = ' ';
    private static int residueParity = 0;
    private static double result = 0.0;
    private static int errorPosition = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (wksOption != 2) {
            mainMenu();
        }
        System.out.println("Adios...");
    }

    private static void mainMenu() {
        System.out.println("Hamming (11,7)");
        System.out.println("1. Calcular Hamming");
        System.out.println("2. Salir");
        System.out.print("Elige una opcion: ");
        wksOption = scanner.nextInt();
        scanner.nextLine();

        if (wksOption == 1) {
            System.out.println("--------------------------------------------");
            System.out.println("Introduce letra en minuscula a evaluar");
            System.out.println("Ejemplo => 'h'");
            System.out.println("--------------------------------------------");

            String input = scanner.nextLine();
            if (input.length() > 0) {
                letra = input.charAt(0);
            }

            transLetBin();
            fillHamming();
            calcParityBits();

            System.out.println("-----------------------------------------");
            System.out.print("Su hamming es: ");
            for (int i = 0; i < 11; i++) {
                System.out.print(hammingArray[i]);
            }
            System.out.println();
            System.out.println("-----------------------------------------");
            System.out.println("Introduce el hamming a evaluar");
            System.out.println("Deben ser 11 bits !!! ");

            String hammingInput = scanner.nextLine();
            for (int i = 0; i < 11 && i < hammingInput.length(); i++) {
                wrongHammingArray[i] = Character.getNumericValue(hammingInput.charAt(i));
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
            case 'a':
                bitBinaryWord = new int[]{1, 1, 0, 0, 0, 0, 1};
                break;
            case 'b':
                bitBinaryWord = new int[]{1, 1, 0, 0, 0, 1, 0};
                break;
            case 'c':
                bitBinaryWord = new int[]{1, 1, 0, 0, 0, 1, 1};
                break;
            case 'd':
                bitBinaryWord = new int[]{1, 1, 0, 0, 1, 0, 0};
                break;
            case 'e':
                bitBinaryWord = new int[]{1, 1, 0, 0, 1, 0, 1};
                break;
            case 'f':
                bitBinaryWord = new int[]{1, 1, 0, 0, 1, 1, 0};
                break;
            case 'g':
                bitBinaryWord = new int[]{1, 1, 0, 0, 1, 1, 1};
                break;
            case 'h':
                bitBinaryWord = new int[]{1, 1, 0, 1, 0, 0, 0};
                break;
            case 'i':
                bitBinaryWord = new int[]{1, 1, 0, 1, 0, 0, 1};
                break;
            case 'j':
                bitBinaryWord = new int[]{1, 1, 0, 1, 0, 1, 0};
                break;
            case 'k':
                bitBinaryWord = new int[]{1, 1, 0, 1, 0, 1, 1};
                break;
            case 'l':
                bitBinaryWord = new int[]{1, 1, 0, 1, 1, 0, 0};
                break;
            case 'm':
                bitBinaryWord = new int[]{1, 1, 0, 1, 1, 0, 1};
                break;
            case 'n':
                bitBinaryWord = new int[]{1, 1, 0, 1, 1, 1, 0};
                break;
            case 'o':
                bitBinaryWord = new int[]{1, 1, 0, 1, 1, 1, 1};
                break;
            case 'p':
                bitBinaryWord = new int[]{1, 1, 1, 0, 0, 0, 0};
                break;
            case 'q':
                bitBinaryWord = new int[]{1, 1, 1, 0, 0, 0, 1};
                break;
            case 'r':
                bitBinaryWord = new int[]{1, 1, 1, 0, 0, 1, 0};
                break;
            case 's':
                bitBinaryWord = new int[]{1, 1, 1, 0, 0, 1, 1};
                break;
            case 't':
                bitBinaryWord = new int[]{1, 1, 1, 0, 1, 0, 0};
                break;
            case 'u':
                bitBinaryWord = new int[]{1, 1, 1, 0, 1, 0, 1};
                break;
            case 'v':
                bitBinaryWord = new int[]{1, 1, 1, 0, 1, 1, 0};
                break;
            case 'w':
                bitBinaryWord = new int[]{1, 1, 1, 0, 1, 1, 1};
                break;
            case 'x':
                bitBinaryWord = new int[]{1, 1, 1, 1, 0, 0, 0};
                break;
            case 'y':
                bitBinaryWord = new int[]{1, 1, 1, 1, 0, 0, 1};
                break;
            case 'z':
                bitBinaryWord = new int[]{1, 1, 1, 1, 0, 1, 0};
                break;
            default:
                System.out.println("Opcion no valida");
        }
    }

    private static void fillHamming() {
        hammingArray[0] = 0;
        hammingArray[1] = 0;
        hammingArray[2] = bitBinaryWord[0];
        hammingArray[3] = 0;
        hammingArray[4] = bitBinaryWord[1];
        hammingArray[5] = bitBinaryWord[2];
        hammingArray[6] = bitBinaryWord[3];
        hammingArray[7] = 0;
        hammingArray[8] = bitBinaryWord[4];
        hammingArray[9] = bitBinaryWord[5];
        hammingArray[10] = bitBinaryWord[6];
    }

    private static void calcParityBits() {
        parity1 = 0