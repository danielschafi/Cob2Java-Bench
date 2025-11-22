import java.io.*;
import java.util.Scanner;

public class Hammer {
    private static final String DEFAULT_MESSAGE = "HELLO WORLD";
    private static final String QUOTE = "\"";

    public static void main(String[] args) {
        String userMessage = "";
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a a message: ");
        userMessage = scanner.nextLine();
        scanner.close();

        if (userMessage.trim().isEmpty()) {
            userMessage = DEFAULT_MESSAGE;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("hammer.html"))) {
            writer.write("<html><head>");
            writer.newLine();
            
            writeBootstrapLink(writer);
            writeWebFonts(writer);
            writeStyle(writer);
            
            writer.write("</head>");
            writer.newLine();
            
            String bodyLine = "<body>" +
                "<div class=" + QUOTE + "container text-center" + QUOTE + ">" +
                "<h1 class=" + QUOTE + "when-your-only" + QUOTE + ">" +
                "When your only tool is COBOL, everything looks like a " +
                "mainframe" +
                "</h1>";
            writer.write(bodyLine);
            writer.newLine();
            
            String h1Line = "<h1 class=" + QUOTE + "display-1" + QUOTE + "> " +
                "<span class=" + QUOTE + "hello-world" + QUOTE + ">";
            writer.write(h1Line);
            writer.newLine();
            
            writer.write(userMessage);
            writer.newLine();
            
            String closingLine = "</span>" +
                "</h1></div></body></html>";
            writer.write(closingLine);
            writer.newLine();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeStyle(BufferedWriter writer) throws IOException {
        String styleLine = "<style>" +
            ".hello-world {" +
            "font-family: 'VT323', sans-serif;" +
            "padding: 5rem 0;" +
            "}" +
            ".when-your-only {" +
            "font-family: 'Cousine', sans-serif;" +
            "margin: 4rem 0;" +
            "}" +
            "</style>";
        writer.write(styleLine);
        writer.newLine();
    }

    private static void writeBootstrapLink(BufferedWriter writer) throws IOException {
        String bootstrapLine = "<link href=" + QUOTE +
            "https://cdn.jsdelivr.net/npm/" +
            "bootstrap@5.0.2/dist/css/bootstrap.min.css" + QUOTE +
            "rel=" + QUOTE + "stylesheet" + QUOTE + ">";
        writer.write(bootstrapLine);
        writer.newLine();
    }

    private static void writeWebFonts(BufferedWriter writer) throws IOException {
        String webFontsLine = "<link href=" + QUOTE +
            "https://fonts.googleapis.com/css?family=" +
            "VT323|Cousine" + QUOTE +
            "rel=" + QUOTE + "stylesheet" + QUOTE +
            "type=" + QUOTE + "text/css" + QUOTE + ">";
        writer.write(webFontsLine);
        writer.newLine();
    }
}