public class mandelbrotset {
    private static final int RESOLUTIONX = 240;
    private static final int RESOLUTIONY = 100;
    private static final double REALPLANEMIN = -2.5;
    private static final double REALPLANEMAX = 0.8;
    private static final double IMAGINARYPLANEMIN = -1.25;
    private static final double IMAGINARYPLANEMAX = 1.25;
    private static final int ITERATIONSMAX = 60;
    private static final int THRESHOLD = 10000;

    public static void main(String[] args) {
        double proportionalx = (REALPLANEMAX - REALPLANEMIN) / (RESOLUTIONX - 1);
        double proportionaly = (IMAGINARYPLANEMAX - IMAGINARYPLANEMIN) / (RESOLUTIONY - 1);

        for (int screeny = 0; screeny < RESOLUTIONY; screeny++) {
            double mathplaney = IMAGINARYPLANEMIN + (proportionaly * screeny);

            for (int screenx = 0; screenx < RESOLUTIONX; screenx++) {
                double mathplanex = REALPLANEMIN + (proportionalx * screenx);

                double pointx = 0.0;
                double pointy = 0.0;
                double xsquared = pointx * pointx;
                double ysquared = pointy * pointy;

                int iteration = 0;
                do {
                    double tempvar = xsquared - ysquared + mathplanex;
                    pointy = 2 * pointx * pointy + mathplaney;
                    pointx = tempvar;
                    xsquared = pointx * pointx;
                    ysquared = pointy * pointy;
                    iteration++;
                } while (iteration < ITERATIONSMAX && xsquared + ysquared < THRESHOLD);

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