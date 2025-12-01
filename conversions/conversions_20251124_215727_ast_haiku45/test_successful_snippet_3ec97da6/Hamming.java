```java
import java.util.Scanner;

public class Hamming {
    private int wksOption = 0;
    private String binaryWord = "1101000";
    private int[] hammingArray = new int[11];
    private int[] wrongHammingArray = new int[11];
    private int[] bitBinaryWord = new int[7];
    private int parity1;
    private int parity2;
    private int parity4;
    private int parity8;
    private String letra;
    private int residueParity;
    private double result;
    private int errorPosition = 0;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Hamming hamming = new Hamming();
        hamming.mainProcedure();
    }

    private void mainProcedure() {
        while (wksOption != 2) {
            mainMenu();
        }
    }

    private void mainMenu() {
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

            letra = scanner.nextLine().trim();
            transLetBin();
            fillHamming();
            calcParityBits();

            System.out.println("-----------------------------------------");
            System.out.println("Su hamming es: ");
            printHammingArray();
            System.out.println("-----------------------------------------");
            System.out.println("Introduce el hamming a evaluar");
            System.out.println("Deben ser 11 bits !!! ");

            String input = scanner.nextLine().trim();
            for (int i = 0; i < 11 && i < input.length(); i++) {
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

    private void transLetBin() {
        switch (letra) {
            case "a":
                bitBinaryWord = new int[]{1, 1, 0, 0, 0, 0, 1};
                break;
            case "b":
                bitBinaryWord = new int[]{1, 1, 0, 0, 0, 1, 0};
                break;
            case "c":
                bitBinaryWord = new int[]{1, 1, 0, 0, 0, 1, 1};
                break;
            case "d":
                bitBinaryWord = new int[]{1, 1, 0, 0, 1, 0, 0};
                break;
            case "e":
                bitBinaryWord = new int[]{1, 1, 0, 0, 1, 0, 1};
                break;
            case "f":
                bitBinaryWord = new int[]{1, 1, 0, 0, 1, 1, 0};
                break;
            case "g":
                bitBinaryWord = new int[]{1, 1, 0, 0, 1, 1, 1};
                break;
            case "h":
                bitBinaryWord = new int[]{1, 1, 0, 1, 0, 0, 0};
                break;
            case "i":
                bitBinaryWord = new int[]{1, 1, 0, 1, 0, 0, 1};
                break;
            case "j":
                bitBinaryWord = new int[]{1, 1, 0, 1, 0, 1, 0};
                break;
            case "k":
                bitBinaryWord = new int[]{1, 1, 0, 1, 0, 1, 1};
                break;
            case "l":
                bitBinaryWord = new int[]{1, 1, 0, 1, 1, 0, 0};
                break;
            case "m":
                bitBinaryWord = new int[]{1, 1, 0, 1, 1, 0, 1};
                break;
            case "n":
                bitBinaryWord = new int[]{1, 1, 0, 1, 1, 1, 0};
                break;
            case "o":
                bitBinaryWord = new int[]{1, 1, 0, 1, 1, 1, 1};
                break;
            case "p":
                bitBinaryWord = new int[]{1, 1, 1, 0, 0, 0, 0};
                break;
            case "q":
                bitBinaryWord = new int[]{1, 1, 1, 0, 0, 0, 1};
                break;
            case "r":
                bitBinaryWord = new int[]{1, 1, 1, 0, 0, 1, 0};
                break;
            case "s":
                bitBinaryWord = new int[]{1, 1, 1, 0, 0, 1, 1};
                break;
            case "t":
                bitBinaryWord = new int[]{1, 1, 1, 0, 1, 0, 0};
                break;
            case "u":
                bitBinaryWord = new int[]{1, 1, 1, 0, 1, 0, 1};
                break;
            case "v":
                bitBinaryWord = new int[]{1, 1, 1, 0, 1, 1, 0};
                break;
            case "w":
                bitBinaryWord = new int[]{1, 1, 1, 0, 1, 1, 1};
                break;
            case "x":
                bitBinaryWord = new int[]{1, 1, 1, 1, 0, 0, 0};
                break;
            case "y":
                bitBinaryWord = new int[]{1, 1, 1, 1, 0, 0, 1};
                break;
            case "z":
                bitBinaryWord = new int[]{1, 1, 1, 1, 0, 1, 0};
                break;
            default:
                System.out.println("Opcion no valida");
        }
    }

    private void fillHamming() {
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

    private void calcParityBits() {
        parity1 = hammingArray[2] + hammingArray[4] + hammingArray[6] + hammingArray[8] + hammingArray[10];
        parity2 = hammingArray[2] + hammingArray[5] + hammingArray[6] + hammingArray[9] + hammingArray[10];
        parity4 = hammingArray[4] + hammingArray[5] + hammingArray[6];
        parity8