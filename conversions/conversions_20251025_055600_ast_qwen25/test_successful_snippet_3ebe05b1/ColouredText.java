import java.util.Scanner;

public class ColouredText {
    public static void main(String[] args) {
        String exampleStr = "COBOL";
        int foreColour;
        int backColour;
        int lineNum = 1;
        int colNum = 1;
        Scanner scanner = new Scanner(System.in);

        for (foreColour = 0; foreColour <= 7; foreColour++) {
            for (backColour = 0; backColour <= 7; backColour++) {
                System.out.printf("\033[%d;%dm%s\033[0m", 30 + foreColour, 40 + backColour, exampleStr);
                colNum += 6;
            }
            lineNum++;
            colNum = 1;
            System.out.println();
        }

        System.out.printf("\033[%d;%dmWith HIGHLIGHT:\033[0m\n", 30, 40);
        lineNum++;

        for (foreColour = 0; foreColour <= 7; foreColour++) {
            for (backColour = 0; backColour <= 7; backColour++) {
                System.out.printf("\033[%d;%dm%s\033[0m", 30 + foreColour, 40 + backColour, exampleStr);
                colNum += 6;
            }
            lineNum++;
            colNum = 1;
            System.out.println();
        }

        System.out.printf("\033[%d;%dmWith LOWLIGHT: (has no effect on many terminals)\033[0m\n", 30, 40);
        lineNum++;

        for (foreColour = 0; foreColour <= 7; foreColour++) {
            for (backColour = 0; backColour <= 7; backColour++) {
                System.out.printf("\033[%d;%dm%s\033[0m", 30 + foreColour, 40 + backColour, exampleStr);
                colNum += 6;
            }
            lineNum++;
            colNum = 1;
            System.out.println();
        }

        System.out.printf("\033[%d;%dmWith BLINK:\033[0m\n", 30, 40);
        lineNum++;

        for (foreColour = 0; foreColour <= 7; foreColour++) {
            for (backColour = 0; backColour <= 7; backColour++) {
                System.out.printf("\033[%d;%dm%s\033[0m", 30 + foreColour, 40 + backColour, exampleStr);
                colNum += 6;
            }
            lineNum++;
            colNum = 1;
            System.out.println();
        }

        System.out.printf("\033[%d;%dmPress enter to continue.\033[0m", 30, 40);
        scanner.nextLine();
        scanner.close();
    }
}