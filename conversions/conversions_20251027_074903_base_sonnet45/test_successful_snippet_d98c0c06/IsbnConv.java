import java.io.*;
import java.nio.file.*;
import java.util.*;

public class IsbnConv {
    
    public static void main(String[] args) {
        try {
            List<String> isbn10List = new ArrayList<>();
            Path isbn10Path = Paths.get("isbn10.dat");
            
            if (Files.exists(isbn10Path)) {
                isbn10List = Files.readAllLines(isbn10Path);
            }
            
            BufferedWriter writer = new BufferedWriter(new FileWriter("isbn13.dat"));
            
            for (String isbn10 : isbn10List) {
                if (isbn10.length() >= 10) {
                    isbn10 = isbn10.substring(0, 10);
                }
                
                String isbn10Prn = formatIsbn10(isbn10);
                System.out.print(isbn10Prn + " -> ");
                
                int validationResult = checkIsbn10(isbn10);
                
                if (validationResult == 1) {
                    System.out.println("not valid");
                } else {
                    String isbn13 = makeIsbn13(isbn10);
                    String isbn13Prn = formatIsbn13(isbn13);
                    System.out.println(isbn13Prn);
                    writer.write(isbn13);
                    writer.newLine();
                }
            }
            
            writer.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static String formatIsbn10(String isbn10) {
        if (isbn10.length() < 10) {
            isbn10 = String.format("%-10s", isbn10).replace(' ', '0');
        }
        String formatted = isbn10.substring(0, 1) + "/" + 
                          isbn10.substring(1, 4) + "/" + 
                          isbn10.substring(4, 9) + "/" + 
                          isbn10.substring(9, 10);
        return formatted.replace("/", "-");
    }
    
    private static String formatIsbn13(String isbn13) {
        if (isbn13.length() < 13) {
            isbn13 = String.format("%-13s", isbn13).replace(' ', '0');
        }
        String formatted = isbn13.substring(0, 3) + "/" + 
                          isbn13.substring(3, 4) + "/" + 
                          isbn13.substring(4, 7) + "/" + 
                          isbn13.substring(7, 12) + "/" + 
                          isbn13.substring(12, 13);
        return formatted.replace("/", "-");
    }
    
    private static int checkIsbn10(String isbn) {
        if (isbn.length() < 10) {
            return 1;
        }
        
        int result = 0;
        
        for (int idx = 0; idx < 9; idx++) {
            int digit = Character.getNumericValue(isbn.charAt(idx));
            result += (idx + 1) * digit;
        }
        
        result = result % 11;
        
        int checkNum;
        char isbnChk = isbn.charAt(9);
        if (isbnChk == 'X' || isbnChk == 'x') {
            checkNum = 10;
        } else {
            checkNum = Character.getNumericValue(isbnChk);
        }
        
        if (result == checkNum) {
            return 0;
        } else {
            return 1;
        }
    }
    
    private static String makeIsbn13(String isbn10) {
        int result = 38;
        
        for (int idx = 0; idx < 9; idx++) {
            int digit = Character.getNumericValue(isbn10.charAt(idx));
            if ((idx + 1) % 2 == 0) {
                result += digit;
            } else {
                result += 3 * digit;
            }
        }
        
        int checkNum = (10 - (result % 10)) % 10;
        
        String isbn13 = "978" + isbn10.substring(0, 9) + checkNum;
        
        return isbn13;
    }
}