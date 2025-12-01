```java
public class MandelbrotSet {
    private static final int RESOLUTIONX = 240;
    private static final int RESOLUTIONY = 100;
    private static final double REALPLANEMIN = -2.5;
    private static final double REALPLANEMAX = 0.8;
    private static final double IMAGINARYPLANEMIN = -1.25;
    private static final double IMAGINARYPLANEMAX = 1.25;
    private static final int ITERATIONSMAX = 60;
    private static final int THRESHOLD = 10000;

    private double proportionalx;
    private double proportionaly;
    private int screenx;
    private int screeny;
    private double mathplanex;
    private double mathplaney;
    private double pointx;
    private double pointy;
    private double xsquared;
    private double ysquared;
    private int iteration;
    private double tempvar;

    public static void main(String[] args) {
        MandelbrotSet mandelbrot = new MandelbrotSet();
        mandelbrot.run();
    }

    public void run() {
        proportionalx = (REALPLANEMAX - REALPLANEMIN) / (RESOLUTIONX - 1);
        proportionaly = (IMAGINARYPLANEMAX - IMAGINARYPLANEMIN) / (RESOLUTIONY - 1);

        for (screeny = 0; screeny < RESOLUTIONY; screeny++) {
            mathplaney = IMAGINARYPLANEMIN + (proportionaly * screeny);

            for (screenx = 0; screenx < RESOLUTIONX; screenx++) {
                mathplanex = REALPLANEMIN + (proportionalx * screenx);

                pointx = 0;
                pointy = 0;
                xsquared = pointx * pointx;
                ysquared = pointy * pointy;

                for (iteration = 0; iteration < ITERATIONSMAX && xsquared + ysquared < THRESHOLD; iteration++) {
                    tempvar = xsquared - ysquared + mathplanex;
                    pointy = 2 * pointx * pointy + mathplaney;
                    pointx = tempvar;
                    xsquared = pointx * pointx;
                    ysquared = pointy * pointy;
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