import java.text.DecimalFormat;

public class MandelbrotSet {
    private static final int RESOLUTIONX = 240;
    private static final int RESOLUTIONY = 100;
    private static final double REALPLANE_MIN = -2.5;
    private static final double REALPLANE_MAX = 0.8;
    private static final double IMAGINARYPLANE_MIN = -1.25;
    private static final double IMAGINARYPLANE_MAX = 1.25;
    private static final int ITERATIONSMAX = 60;
    private static final double THRESHOLD = 10000;

    public static void main(String[] args) {
        double proportionalX = (REALPLANE_MAX - REALPLANE_MIN) / (RESOLUTIONX - 1);
        double proportionalY = (IMAGINARYPLANE_MAX - IMAGINARYPLANE_MIN) / (RESOLUTIONY - 1);

        for (int screenY = 0; screenY < RESOLUTIONY; screenY++) {
            double mathPlaneY = IMAGINARYPLANE_MIN + (proportionalY * screenY);

            for (int screenX = 0; screenX < RESOLUTIONX; screenX++) {
                double mathPlaneX = REALPLANE_MIN + (proportionalX * screenX);

                double pointX = 0;
                double pointY = 0;
                double xSquared = 0;
                double ySquared = 0;
                int iteration = 0;

                while (iteration < ITERATIONSMAX && xSquared + ySquared < THRESHOLD) {
                    double tempVar = xSquared - ySquared + mathPlaneX;
                    pointY = 2 * pointX * pointY + mathPlaneY;
                    pointX = tempVar;
                    xSquared = pointX * pointX;
                    ySquared = pointY * pointY;
                    iteration++;
                }

                if (iteration == ITERATIONSMAX) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}