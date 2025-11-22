import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Hammer {
    public static void main(String[] args) {
        String userMessage = " ";
        String defaultMessage = "HELLO WORLD";

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a a message: ");
        userMessage = scanner.nextLine();

        if (userMessage.trim().isEmpty()) {
            userMessage = defaultMessage;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("hammer.html"))) {
            writer.write("<html><head>");
            writer.newLine();

            writeBootstrapLink(writer);
            writeWebFonts(writer);
            writeStyle(writer);

            writer.write("</head>");
            writer.newLine();

            writer.write("<body>");
            writer.newLine();
            writer.write("<div class=\"container text-center\">");
            writer.newLine();
            writer.write("<h1 class=\"when-your-only\">");
            writer.newLine();
            writer.write("When your only tool is COBOL, everything looks like a mainframe");
            writer.newLine();
            writer.write("</h1>");
            writer.newLine();

            writer.write("<h1 class=\"display-1\">");
            writer.newLine();
            writer.write("<span class=\"hello-world\">");
            writer.newLine();
            writer.write(userMessage);
            writer.newLine();
            writer.write("</span>");
            writer.newLine();
            writer.write("</h1></div></body></html>");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeStyle(BufferedWriter writer) throws IOException {
        writer.write("<style>");
        writer.newLine();
        writer.write(".hello-world {");
        writer.newLine();
        writer.write("font-family: 'VT323', sans-serif;");
        writer.newLine();
        writer.write("padding: 5rem 0;");
        writer.newLine();
        writer.write("}");
        writer.newLine();
        writer.write(".when-your-only {");
        writer.newLine();
        writer.write("font-family: 'Cousine', sans-serif;");
        writer.newLine();
        writer.write("margin: 4rem 0;");
        writer.newLine();
        writer.write("}");
        writer.newLine();
        writer.write("</style>");
        writer.newLine();
    }

    private static void writeBootstrapLink(BufferedWriter writer) throws IOException {
        writer.write("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
        writer.newLine();
    }

    private static void writeWebFonts(BufferedWriter writer) throws IOException {
        writer.write("<link href=\"https://fonts.googleapis.com/css?family=VT323|Cousine\" rel=\"stylesheet\" type=\"text/css\">");
        writer.newLine();
    }
}