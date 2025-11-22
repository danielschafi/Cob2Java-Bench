import java.util.Scanner;

public class HAMMING {
    private int wksOption = 0;
    private int[] hammingArray = new int[11];
    private int[] wrongHammingArray = new int[11];
    private int[] binaryWordArray = new int[7];
    private int parity1;
    private int parity2;
    private int parity4;
    private int parity8;
    private char letra;
    private int errorPosition = 0;

    public static void main(String[] args) {
        HAMMING program = new HAMMING();
        program.mainProcedure();
    }

    private void mainProcedure() {
        while (wksOption != 2) {
            mainMenu();
        }
    }

    private void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hamming (11,7)");
        System.out.println("1. Calcular Hamming");
        System.out.println("2. Salir");
        System.out.println("Elige una opcion: ");
        wksOption = scanner.nextInt();
        scanner.nextLine();

        if (wksOption == 1) {
            System.out.println("--------------------------------------------");
            System.out.println("Introduce letra en minuscula a evaluar");
            System.out.println("Ejemplo => 'h'");
            System.out.println("--------------------------------------------");

            letra = scanner.nextLine().charAt(0);
            transLetBin();
            fillHamming();
            calcParityBits();

            System.out.println("-----------------------------------------");
            System.out.println("Su hamming es: ");
            printArray(hammingArray);
            System.out.println("-----------------------------------------");
            System.out.println("Introduce el hamming a evaluar");
            System.out.println("Deben ser 11 bits !!! ");
            String wrongInput = scanner.nextLine();
            for (int i = 0; i < 11 && i < wrongInput.length(); i++) {
                wrongHammingArray[i] = Character.getNumericValue(wrongInput.charAt(i));
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
        String binary = "";
        switch (letra) {
            case 'a': binary = "1100001"; break;
            case 'b': binary = "1100010"; break;
            case 'c': binary = "1100011"; break;
            case 'd': binary = "1100100"; break;
            case 'e': binary = "1100101"; break;
            case 'f': binary = "1100110"; break;
            case 'g': binary = "1100111"; break;
            case 'h': binary = "1101000"; break;
            case 'i': binary = "1101001"; break;
            case 'j': binary = "1101010"; break;
            case 'k': binary = "1101011"; break;
            case 'l': binary = "1101100"; break;
            case 'm': binary = "1101101"; break;
            case 'n': binary = "1101110"; break;
            case 'o': binary = "1101111"; break;
            case 'p': binary = "1110000"; break;
            case 'q': binary = "1110001"; break;
            case 'r': binary = "1110010"; break;
            case 's': binary = "1110011"; break;
            case 't': binary = "1110100"; break;
            case 'u': binary = "1110101"; break;
            case 'v': binary = "1110110"; break;
            case 'w': binary = "1110111"; break;
            case 'x': binary = "1111000"; break;
            case 'y': binary = "1111001"; break;
            case 'z': binary = "1111010"; break;
            default: System.out.println("Opcion no valida"); return;
        }
        for (int i = 0; i < 7; i++) {
            binaryWordArray[i] = Character.getNumericValue(binary.charAt(i));
        }
    }

    private void fillHamming() {
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

    private void calcParityBits() {
        parity1 = hammingArray[2] + hammingArray[4] + hammingArray[6] + hammingArray[8] + hammingArray[10];
        parity2 = hammingArray[2] + hammingArray[5] + hammingArray[6] + hammingArray[9] + hammingArray[10];
        parity4 = hammingArray[4] + hammingArray[5] + hammingArray[6];
        parity8 = hammingArray[8] + hammingArray[9] + hammingArray[10];

        hammingArray[0] = (parity1 % 2 == 1) ? 1 : 0;
        hammingArray[1] = (parity2 % 2 == 1) ? 1 : 0;
        hammingArray[3] = (parity4 % 2 == 1) ? 1 : 0;
        hammingArray[7] = (parity8 % 2 == 1) ? 1 : 0;
    }

    private void calcParityBitsWrong() {
        System.out.print("Hamming a evaluar: ");
        printArray(wrongHammingArray);

        parity1 = wrongHammingArray[2] + wrongHammingArray[4] + wrongHammingArray[6] + wrongHammingArray[8] + wrongHammingArray[10];
        parity2 = wrongHammingArray[2] + wrongHammingArray[5] + wrongHammingArray[6] + wrongHammingArray[9] + wrongHammingArray[10];
        parity4 = wrongHammingArray[4] + wrongHammingArray[5] + wrongHammingArray[6];
        parity8 = wrongHammingArray[8] + wrongHammingArray[9] + wrongHammingArray[10];

        parity1 = (parity1 % 2 == 1) ? 1 : 0;
        parity2 = (parity2 % 2 == 1) ? 1 : 0;
        parity4 = (parity4 % 2 == 1) ? 1 : 0;
        parity8 = (parity8 % 2 == 1) ? 1 : 0;
    }

    private void findErrorPosition() {
        errorPosition = 0;

        if (parity1 != wrongHammingArray[0]) {
            errorPosition += 1;
        }
        if (parity2 != wrongHammingArray[1]) {
            errorPosition += 2;
        }
        if (parity4 != wrongHammingArray[3]) {
            errorPosition += 4;
        }
        if (parity8 != wrongHammingArray[7]) {
            errorPosition += 8;
        }

        if (errorPosition == 0) {
            System.out.println("*****************************************");
            System.out.println("La