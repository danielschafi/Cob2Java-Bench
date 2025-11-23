import java.io.*;
import java.util.*;

public class IsbnConv {
    
    static class Isbn10File {
        private BufferedReader reader;
        private boolean endOfFile = false;
        
        public Isbn10File(String filename) throws IOException {
            reader = new BufferedReader(new FileReader(filename));
        }
        
        public String readRecord() throws IOException {
            String line = reader.readLine();
            if (line == null) {
                endOfFile = true;
                return null;
            }
            return line;
        }
        
        public boolean isEndOfFile() {
            return endOfFile;
        }
        
        public void close() throws IOException {
            reader.close();
        }
    }
    
    static class Isbn13File {
        private PrintWriter writer;
        
        public Isbn13File(String filename) throws IOException {
            writer = new PrintWriter(new FileWriter(filename));
        }
        
        public void writeRecord(String record) {
            writer.println(record);
        }
        
        public void close() {
            writer.close();
        }
    }
    
    static class CheckIsbn10 {
        public static boolean validate(String isbn10) {
            int result = 0;
            for (int i = 0; i < 9; i++) {
                result += (i + 1) * Character.getNumericValue(isbn10.charAt(i));
            }
            int checkDigit = result % 11;
            
            char lastChar = isbn10.charAt(9);
            int expectedCheckDigit;
            if (lastChar == 'X') {
                expectedCheckDigit = 10;
            } else {
                expectedCheckDigit = Character.getNumericValue(lastChar);
            }
            
            return checkDigit == expectedCheckDigit;
        }
    }
    
    static class MakeIsbn13 {
        public static String convert(String isbn10) {
            int result = 38; // 9 + 3 * 7 + 8 = 38
            
            for (int i = 0; i < 9; i++) {
                if ((i + 1) % 2 == 0) {
                    result += Character.getNumericValue(isbn10.charAt(i));
                } else {
                    result += 3 * Character.getNumericValue(isbn10.charAt(i));
                }
            }
            
            int checkDigit = (10 - (result % 10)) % 10;
            
            StringBuilder sb = new StringBuilder();
            sb.append("978");
            sb.append(isbn10.substring(0, 9));
            sb.append(checkDigit);
            
            return sb.toString();
        }
    }
    
    public static void main(String[] args) {
        try {
            Isbn10File isbn10File = new Isbn10File("isbn10.dat");
            Isbn13File isbn13File = new Isbn13File("isbn13.dat");
            
            String isbn10;
            while ((isbn10 = isbn10File.readRecord()) != null) {
                if (isbn10.length() != 10) continue;
                
                String formattedIsbn10 = isbn10.replaceAll("-", "/").replaceAll("(\\d{1})(\\d{3})(\\d{5})(\\d)", "$1/$2/$3/$4");
                System.out.print(formattedIsbn10 + " -> ");
                
                if (!CheckIsbn10.validate(isbn10)) {
                    System.out.println("not valid");
                } else {
                    String isbn13 = MakeIsbn13.convert(isbn10);
                    String formattedIsbn13 = isbn13.replaceAll("(\\d{3})(\\d)(\\d{3})(\\d{5})(\\d)", "$1/$2/$3/$4/$5");
                    System.out.println(formattedIsbn13);
                    isbn13File.writeRecord(isbn13);
                }
            }
            
            isbn10File.close();
            isbn13File.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}