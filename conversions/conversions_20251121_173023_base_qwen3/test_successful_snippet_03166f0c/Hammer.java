import java.io.*;
import java.nio.file.*;

public class Hammer {
    private static final String DEFAULT_MESSAGE = "HELLO WORLD";
    private static final String HTML_FILE_PATH = "hammer.html";

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter a a message: ");
            String userMessage = reader.readLine();
            
            if (userMessage == null || userMessage.trim().isEmpty()) {
                userMessage = DEFAULT_MESSAGE;
            }

            BufferedWriter writer = Files.newBufferedWriter(Paths.get(HTML_FILE_PATH));
            
            writer.write("<html><head>");
            writer.newLine();
            
            writeBootstrapLink(writer);
            writeWebFonts(writer);
            writeStyle(writer);
            
            writer.write("</head>");
            writer.newLine();
            
            StringBuilder htmlLine = new StringBuilder();
            htmlLine.append("<body>");
            htmlLine.append("<div class=\"container text-center\">");
            htmlLine.append("<h1 class=\"when-your-only\">");
            htmlLine.append("When your only tool is COBOL, everything looks like a ");
            htmlLine.append("mainframe");
            htmlLine.append("</h1>");
            htmlLine.append("</div>");
            writer.write(htmlLine.toString());
            writer.newLine();
            
            htmlLine.setLength(0);
            htmlLine.append("<h1 class=\"display-1\"> <span class=\"hello-world\">");
            writer.write(htmlLine.toString());
            writer.newLine();
            
            writer.write(userMessage);
            writer.newLine();
            
            htmlLine.setLength(0);
            htmlLine.append("</span>");
            htmlLine.append("</h1></div></body></html>");
            writer.write(htmlLine.toString());
            writer.newLine();
            
            writer.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void writeBootstrapLink(BufferedWriter writer) throws IOException {
        StringBuilder htmlLine = new StringBuilder();
        htmlLine.append("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
        writer.write(htmlLine.toString());
        writer.newLine();
    }
    
    private static void writeWebFonts(BufferedWriter writer) throws IOException {
        StringBuilder htmlLine = new StringBuilder();
        htmlLine.append("<link href=\"https://fonts.googleapis.com/css?family=VT323|Cousine\" rel=\"stylesheet\" type=\"text/css\">");
        writer.write(htmlLine.toString());
        writer.newLine();
    }
    
    private static void writeStyle(BufferedWriter writer) throws IOException {
        StringBuilder htmlLine = new StringBuilder();
        htmlLine.append("<style>");
        htmlLine.append(".hello-world {");
        htmlLine.append("font-family: 'VT323', sans-serif;");
        htmlLine.append("padding: 5rem 0;");
        htmlLine.append("}");
        htmlLine.append(".when-your-only {");
        htmlLine.append("font-family: 'Cousine', sans-serif;");
        htmlLine.append("margin: 4rem 0;");
        htmlLine.append("}");
        htmlLine.append("</style>");
        writer.write(htmlLine.toString());
        writer.newLine();
    }
}