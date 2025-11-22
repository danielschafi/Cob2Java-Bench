import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Hammer {
    public static void main(String[] args) {
        String userMessage = "";
        String defaultMessage = "HELLO WORLD";
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a a message: ");
        userMessage = scanner.nextLine();
        scanner.close();
        
        if (userMessage.trim().isEmpty()) {
            userMessage = defaultMessage;
        }
        
        try (FileWriter writer = new FileWriter("hammer.html")) {
            writer.write("<html><head>\n");
            
            writeBootstrapLink(writer);
            writeWebFonts(writer);
            writeStyle(writer);
            
            writer.write("</head>\n");
            
            String bodyStart = "<body>" +
                    "<div class=\"container text-center\">" +
                    "<h1 class=\"when-your-only\">" +
                    "When your only tool is COBOL, everything looks like a " +
                    "mainframe" +
                    "</h1>\n";
            writer.write(bodyStart);
            
            String h1Start = "<h1 class=\"display-1\"> " +
                    "<span class=\"hello-world\">\n";
            writer.write(h1Start);
            
            writer.write(userMessage + "\n");
            
            String h1End = "</span>" +
                    "</h1></div></body></html>\n";
            writer.write(h1End);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void writeStyle(FileWriter writer) throws IOException {
        String style = "<style>\n" +
                ".hello-world {\n" +
                "font-family: 'VT323', sans-serif;\n" +
                "padding: 5rem 0;\n" +
                "}\n" +
                ".when-your-only {\n" +
                "font-family: 'Cousine', sans-serif;\n" +
                "margin: 4rem 0;\n" +
                "}\n" +
                "</style>\n";
        writer.write(style);
    }
    
    private static void writeBootstrapLink(FileWriter writer) throws IOException {
        String bootstrapLink = "<link href=\"" +
                "https://cdn.jsdelivr.net/npm/" +
                "bootstrap@5.0.2/dist/css/bootstrap.min.css\" " +
                "rel=\"stylesheet\">\n";
        writer.write(bootstrapLink);
    }
    
    private static void writeWebFonts(FileWriter writer) throws IOException {
        String webFonts = "<link href=\"" +
                "https://fonts.googleapis.com/css?family=" +
                "VT323|Cousine\" " +
                "rel=\"stylesheet\" " +
                "type=\"text/css\">\n";
        writer.write(webFonts);
    }
}