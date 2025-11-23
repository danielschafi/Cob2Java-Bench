import java.io.*;
import java.util.Scanner;

public class Hammer {
    private static final String DEFAULT_MESSAGE = "HELLO WORLD";
    private static final String HTML_FILE_NAME = "hammer.html";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Get user input
        System.out.print("Enter a a message: ");
        String userMessage = scanner.nextLine();
        
        if (userMessage == null || userMessage.trim().isEmpty()) {
            userMessage = DEFAULT_MESSAGE;
        }
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(HTML_FILE_NAME))) {
            
            // Write some html
            writer.println("<html><head>");
            
            // Gotta have some bootstrap
            writeBootstrapLink(writer);
            
            // Load Google web fonts
            writeWebFonts(writer);
            
            // Write style
            writeStyle(writer);
            
            writer.println("</head>");
            
            // Write body content
            writer.println("<body>");
            writer.println("<div class=\"container text-center\">");
            writer.println("<h1 class=\"when-your-only\">When your only tool is COBOL, everything looks like a mainframe</h1>");
            writer.println("</div>");
            
            writer.println("<h1 class=\"display-1\"> <span class=\"hello-world\">");
            writer.println(userMessage);
            writer.println("</span>");
            writer.println("</h1>");
            writer.println("</body>");
            writer.println("</html>");
            
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    private static void writeStyle(PrintWriter writer) {
        writer.println("<style>");
        writer.println(".hello-world {");
        writer.println("font-family: 'VT323', sans-serif;");
        writer.println("padding: 5rem 0;");
        writer.println("}");
        writer.println(".when-your-only {");
        writer.println("font-family: 'Cousine', sans-serif;");
        writer.println("margin: 4rem 0;");
        writer.println("}");
        writer.println("</style>");
    }
    
    private static void writeBootstrapLink(PrintWriter writer) {
        writer.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
    }
    
    private static void writeWebFonts(PrintWriter writer) {
        writer.println("<link href=\"https://fonts.googleapis.com/css?family=VT323|Cousine\" rel=\"stylesheet\" type=\"text/css\">");
    }
}