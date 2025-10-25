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
                System.out.print("\033[" + (30 + foreColour) + "m\033[" + (40 + backColour) + "m" + exampleStr + "\033[0m");
                colNum += 6;
                if (colNum > 72) {
                    System.out.println();
                    lineNum++;
                    colNum = 1;
                }
            }
            System.out.println();
            lineNum++;
            colNum = 1;
        }

        System.out.println("With HIGHLIGHT:");
        lineNum++;

        for (foreColour = 0; foreColour <= 7; foreColour++) {
            for (backColour = 0; backColour <= 7; backColour++) {
                System.out.print("\033[" + (30 + foreColour) + "m\033[" + (40 + backColour) + "m\033[1m" + exampleStr + "\033[0m");
                colNum += 6;
                if (colNum > 72) {
                    System.out.println();
                    lineNum++;
                    colNum = 1;
                }
            }
            System.out.println();
            lineNum++;
            colNum = 1;
        }

        System.out.println("With LOWLIGHT: (has no effect on many terminals)");
        lineNum++;

        for (foreColour = 0; foreColour <= 7; foreColour++) {
            for (backColour = 0; backColour <= 7; backColour++) {
                System.out.print("\033[" + (30 + foreColour) + "m\033[" + (40 + backColour) + "m\033[2m" + exampleStr + "\033[0m");
                colNum += 6;
                if (colNum > 72) {
                    System.out.println();
                    lineNum++;
                    colNum = 1;
                }
            }
            System.out.println();
            lineNum++;
            colNum = 1;
        }

        System.out.println("With BLINK:");
        lineNum++;

        for (foreColour = 0; foreColour <= 7; foreColour++) {
            for (backColour = 0; backColour <= 7; backColour++) {
                System.out.print("\033[" + (30 + foreColour) + "m\033[" + (40 + backColour) + "m\033[5m" + exampleStr + "\033[0m");
                colNum += 6;
                if (colNum > 72) {
                    System.out.println();
                    lineNum++;
                    colNum = 1;
                }
            }
            System.out.println();
            lineNum++;
            colNum = 1;
        }

        System.out.print("Press enter to continue.");
        scanner.nextLine();
        scanner.close();
    }
}