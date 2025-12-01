import java.io.*;
import java.util.Scanner;

public class Hammer {
    private static String htmlLine = "";
    private static final String DEFAULT_MESSAGE = "HELLO WORLD";
    private static String userMessage = "";
    private static PrintWriter writer;

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Enter a a message: ");
            userMessage = scanner.nextLine();
            
            if (userMessage.trim().isEmpty()) {
                userMessage = DEFAULT_MESSAGE;
            }
            
            writer = new PrintWriter(new FileWriter("hammer.html"));
            
            writer.println("<html><head>");
            
            writeBootstrapLink();
            
            writeWebFonts();
            
            writeStyle();
            
            writer.println("</head>");
            
            htmlLine = "";
            htmlLine = "<body><div class=\"container text-center\"><h1 class=\"when-your-only\">When your only tool is COBOL, everything looks like a mainframe</h1>";
            writer.println(htmlLine);
            
            htmlLine = "";
            htmlLine = "<h1 class=\"display-1\"> <span class=\"hello-world\">";
            writer.println(htmlLine);
            
            writer.println(userMessage);
            
            htmlLine = "";
            htmlLine = "</span></h1></div></body></html>";
            writer.println(htmlLine);
            
            writer.close();
            
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void writeStyle() {
        htmlLine = "";
        htmlLine = "<style>.hello-world {font-family: 'VT323', sans-serif;padding: 5rem 0;}.when-your-only {font-family: 'Cousine', sans-serif;margin: 4rem 0;}</style>";
        writer.println(htmlLine);
    }
    
    private static void writeBootstrapLink() {
        htmlLine = "";
        htmlLine = "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\">";
        writer.println(htmlLine);
    }
    
    private static void writeWebFonts() {
        htmlLine = "";
        htmlLine = "<link href=\"https://fonts.googleapis.com/css?family=VT323|Cousine\" rel=\"stylesheet\" type=\"text/css\">";
        writer.println(htmlLine);
    }
}