import java.io.*;
import java.nio.file.*;
import java.math.BigDecimal;

public class COBPACK2 {
    
    static class RecOutfile {
        byte[] data = new byte[150];
        
        void setOutfileText(long value) {
            String formatted = String.format("%019d", Math.abs(value));
            if (value < 0) {
                formatted = "-" + formatted.substring(1);
            }
            byte[] bytes = formatted.getBytes();
            System.arraycopy(bytes, 0, data, 0, Math.min(bytes.length, 19));
        }
        
        void setOutfileUnpacked(long value) {
            String formatted = String.format("%018d", value);
            byte[] bytes = formatted.getBytes();
            System.arraycopy(bytes, 0, data, 19, Math.min(bytes.length, 18));
        }
        
        void setOutfileUnpackedS(long value) {
            String formatted = String.format("%019d", value);
            byte[] bytes = formatted.getBytes();
            System.arraycopy(bytes, 0, data, 37, Math.min(bytes.length, 19));
        }
        
        void setOutfileComp04(long value) {
            int intVal = (int) value;
            data[56] = (byte) ((intVal >> 8) & 0xFF);
            data[57] = (byte) (intVal & 0xFF);
        }
        
        void setOutfileComp04S(long value) {
            int intVal = (int) value;
            data[58] = (byte) ((intVal >> 8) & 0xFF);
            data[59] = (byte) (intVal & 0xFF);
        }
        
        void setOutfileComp09(long value) {
            int intVal = (int) value;
            data[60] = (byte) ((intVal >> 24) & 0xFF);
            data[61] = (byte) ((intVal >> 16) & 0xFF);
            data[62] = (byte) ((intVal >> 8) & 0xFF);
            data[63] = (byte) (intVal & 0xFF);
        }
        
        void setOutfileComp09S(long value) {
            int intVal = (int) value;
            data[64] = (byte) ((intVal >> 24) & 0xFF);
            data[65] = (byte) ((intVal >> 16) & 0xFF);
            data[66] = (byte) ((intVal >> 8) & 0xFF);
            data[67] = (byte) (intVal & 0xFF);
        }
        
        void setOutfileComp18(long value) {
            data[68] = (byte) ((value >> 56) & 0xFF);
            data[69] = (byte) ((value >> 48) & 0xFF);
            data[70] = (byte) ((value >> 40) & 0xFF);
            data[71] = (byte) ((value >> 32) & 0xFF);
            data[72] = (byte) ((value >> 24) & 0xFF);
            data[73] = (byte) ((value >> 16) & 0xFF);
            data[74] = (byte) ((value >> 8) & 0xFF);
            data[75] = (byte) (value & 0xFF);
        }
        
        void setOutfileComp18S(long value) {
            data[76] = (byte) ((value >> 56) & 0xFF);
            data[77] = (byte) ((value >> 48) & 0xFF);
            data[78] = (byte) ((value >> 40) & 0xFF);
            data[79] = (byte) ((value >> 32) & 0xFF);
            data[80] = (byte) ((value >> 24) & 0xFF);
            data[81] = (byte) ((value >> 16) & 0xFF);
            data[82] = (byte) ((value >> 8) & 0xFF);
            data[83] = (byte) (value & 0xFF);
        }
        
        void setOutfileComp304(long value) {
            packDecimal(value, 84, 2);
        }
        
        void setOutfileComp304S(long value) {
            packDecimalSigned(value, 86, 2);
        }
        
        void setOutfileComp309(long value) {
            packDecimal(value, 88, 5);
        }
        
        void setOutfileComp309S(long value) {
            packDecimalSigned(value, 93, 5);
        }
        
        void setOutfileComp318(long value) {
            packDecimal(value, 98, 9);
        }
        
        void setOutfileComp318S(long value) {
            packDecimalSigned(value, 107, 9);
        }
        
        void setText1(int index, byte value) {
            if (index == 1) {
                data[116] = value;
            } else if (index == 2) {
                data[117] = value;
            }
        }
        
        void setText2(byte[] value) {
            System.arraycopy(value, 0, data, 116, 2);
        }
        
        private void packDecimal(long value, int offset, int length) {
            String str = String.format("%0" + (length * 2) + "d", value);
            for (int i = 0; i < length; i++) {
                int high = Character.getNumericValue(str.charAt(i * 2));
                int low = Character.getNumericValue(str.charAt(i * 2 + 1));
                data[offset + i] = (byte) ((high << 4) | low);
            }
        }
        
        private void packDecimalSigned(long value, int offset, int length) {
            String str = String.format("%0" + (length * 2 - 1) + "d", Math.abs(value));
            for (int i = 0; i < length - 1; i++) {
                int high = Character.getNumericValue(str.charAt(i * 2));
                int low = Character.getNumericValue(str.charAt(i * 2 + 1));
                data[offset + i] = (byte) ((high << 4) | low);
            }
            int sign = value < 0 ? 0x0D : 0x0C;
            int lastDigit = Character.getNumericValue(str.charAt(str.length() - 1));
            data[offset + length - 1] = (byte) ((lastDigit << 4) | sign);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("COBPACK START...");
        
        String filename = "OUTFILE";
        FileOutputStream fos = null;
        String fileStatus = "00";
        
        try {
            fos = new FileOutputStream(filename);
            
            long wsIx = -100000000L;
            
            while (wsIx <= 100000000L) {
                RecOutfile recOutfile = new RecOutfile();
                
                recOutfile.setOutfileText(wsIx);
                recOutfile.setOutfileUnpacked(wsIx);
                recOutfile.setOutfileUnpackedS(wsIx);
                recOutfile.setOutfileComp04(wsIx);
                recOutfile.setOutfileComp04S(wsIx);
                recOutfile.setOutfileComp09(wsIx);
                recOutfile.setOutfileComp09S(wsIx);
                recOutfile.setOutfileComp18(wsIx);
                recOutfile.setOutfileComp18S(wsIx);
                recOutfile.setOutfileComp304(wsIx);
                recOutfile.setOutfileComp304S(wsIx);
                recOutfile.setOutfileComp309(wsIx);
                recOutfile.setOutfileComp309S(wsI