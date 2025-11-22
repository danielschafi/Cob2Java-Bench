import java.util.Arrays;

public class XFN {
    private static final int[] BIT_REC_KEY = new int[256];
    private static final String[] BIT_REC_VAL = new String[256];
    private static final String[] BYTE_REC_KEY = new String[256];
    private static final int[] BYTE_REC_VAL = new int[256];
    private static final String BIT_STRING = new String(new char[8]);
    private static int SHORT_INT;
    private static long I;
    private static long J;
    private static long I_MAX;
    private static long J_MAX;
    private static String ROUTINE;
    private static String TIME1;
    private static String TIME2;
    private static long CNT;

    public static void main(String[] args) {
        fillBitTable();
        fillByteTable();

        System.out.println("Testing the XF5 procedure:");
        SHORT_INT = 42;
        xf5();
        System.out.println("  byte: " + SHORT_INT);
        System.out.println("  bits: " + BIT_STRING);

        System.out.println("Testing CALL X\"F5\":");
        SHORT_INT = 42;
        byte aByte = (byte) SHORT_INT;
        byte[] byteArray = new byte[8];
        xf5(aByte, byteArray);
        System.out.println("  byte: " + aByte);
        System.out.println("  bits: " + Arrays.toString(byteArray));

        System.out.println("Testing the XF4 procedure:");
        SHORT_INT = 0;
        xf4();
        System.out.println("  bits: " + BIT_STRING);
        System.out.println("  byte: " + SHORT_INT);

        System.out.println("Testing CALL X\"F4\":");
        aByte = (byte) SHORT_INT;
        byteArray = new byte[8];
        xf4(aByte, byteArray);
        System.out.println("  bits: " + Arrays.toString(byteArray));
        System.out.println("  byte: " + aByte);

        ROUTINE = "XFN";
        bigLoop();
        ROUTINE = "CALL X\"FN\"";
        bigLoop();

        System.out.println("Press ENTER to exit ...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fillBitTable() {
        for (I = 1; I <= 256; I++) {
            BIT_REC_KEY[(int) I - 1] = (int) I - 1;
            switch (String.format("%02X", BIT_REC_KEY[(int) I - 1])) {
                case "00": BIT_REC_VAL[(int) I - 1] = "00000000"; break;
                case "01": BIT_REC_VAL[(int) I - 1] = "00000001"; break;
                case "02": BIT_REC_VAL[(int) I - 1] = "00000010"; break;
                case "03": BIT_REC_VAL[(int) I - 1] = "00000011"; break;
                case "04": BIT_REC_VAL[(int) I - 1] = "00000100"; break;
                case "05": BIT_REC_VAL[(int) I - 1] = "00000101"; break;
                case "06": BIT_REC_VAL[(int) I - 1] = "00000110"; break;
                case "07": BIT_REC_VAL[(int) I - 1] = "00000111"; break;
                case "08": BIT_REC_VAL[(int) I - 1] = "00001000"; break;
                case "09": BIT_REC_VAL[(int) I - 1] = "00001001"; break;
                case "0A": BIT_REC_VAL[(int) I - 1] = "00001010"; break;
                case "0B": BIT_REC_VAL[(int) I - 1] = "00001011"; break;
                case "0C": BIT_REC_VAL[(int) I - 1] = "00001100"; break;
                case "0D": BIT_REC_VAL[(int) I - 1] = "00001101"; break;
                case "0E": BIT_REC_VAL[(int) I - 1] = "00001110"; break;
                case "0F": BIT_REC_VAL[(int) I - 1] = "00001111"; break;
                case "10": BIT_REC_VAL[(int) I - 1] = "00010000"; break;
                case "11": BIT_REC_VAL[(int) I - 1] = "00010001"; break;
                case "12": BIT_REC_VAL[(int) I - 1] = "00010010"; break;
                case "13": BIT_REC_VAL[(int) I - 1] = "00010011"; break;
                case "14": BIT_REC_VAL[(int) I - 1] = "00010100"; break;
                case "15": BIT_REC_VAL[(int) I - 1] = "00010101"; break;
                case "16": BIT_REC_VAL[(int) I - 1] = "00010110"; break;
                case "17": BIT_REC_VAL[(int) I - 1] = "00010111"; break;
                case "18": BIT_REC_VAL[(int) I - 1] = "00011000"; break;
                case "19": BIT_REC_VAL[(int) I - 1] = "00011001"; break;
                case "1A": BIT_REC_VAL[(int) I - 1] = "00011010"; break;
                case "1B": BIT_REC_VAL[(int) I - 1] = "00011011"; break;
                case "1C": BIT_REC_VAL[(int) I - 1] = "00011100"; break;
                case "1D": BIT_REC_VAL[(int) I - 1] = "00011101"; break;
                case "1E": BIT_REC_VAL[(int) I - 1] = "00011110"; break;
                case "1F": BIT_REC_VAL[(int) I - 1] = "00011111"; break;
                case "20": BIT_REC_VAL[(int) I - 1] = "00100000"; break;
                case "21": BIT_REC_VAL[(int) I - 1] = "00100001"; break;
                case "22": BIT_REC_VAL[(int) I - 1] = "00100010"; break;
                case "23": BIT_REC_VAL[(int) I - 1] = "00100011"; break;
                case "24": BIT_REC_VAL[(int) I - 1] = "00100100"; break;
                case "25": BIT_REC_VAL[(int) I - 1] = "00100101"; break;
                case "26": BIT_REC_VAL[(int) I - 1] = "00100110"; break;
                case "27": BIT_REC_VAL[(int) I - 1] = "00100111"; break;
                case "28": BIT_REC_VAL[(int) I - 1] = "00101000"; break;
                case "29": BIT_REC_VAL[(int) I - 1] = "00101001"; break;
                case "2A": BIT_REC_VAL[(int) I - 1] = "00101010"; break;
                case "2B": BIT_REC_VAL[(int) I - 1] = "00101011"; break;
                case "2C": BIT_REC_VAL[(int) I - 1] = "00101100"; break;
                case "2D": BIT_REC_VAL[(int) I - 1] = "00101101"; break;
                case "2E": BIT_REC_VAL[(int) I - 1] = "00101110"; break;
                case "2F": BIT_REC_VAL[(int) I - 1] = "00101111"; break;
                case "30": BIT_REC_VAL[(int) I - 1] = "00110000"; break;
                case "31": BIT_REC_VAL[(int) I - 1] = "00110001"; break;