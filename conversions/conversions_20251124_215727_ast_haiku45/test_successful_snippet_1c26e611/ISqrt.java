```java
public class ISqrt {
    private long x;
    private long q;
    private long z;
    private long t;
    private long r;
    
    private int isqrtN;
    private String dispLn;
    private String dispFmt;
    private int ptr;
    
    private long pow7;
    private int powN;
    private String powNOut;
    private String pow7Out;
    
    public ISqrt() {
        dispLn = "";
        ptr = 1;
        pow7 = 7;
        powN = 1;
    }
    
    public static void main(String[] args) {
        ISqrt program = new ISqrt();
        program.begin();
    }
    
    private void begin() {
        sqrtsTo65();
        bigSqrts();
    }
    
    private void sqrtsTo65() {
        for (isqrtN = 0; isqrtN <= 65; isqrtN++) {
            dispSmallSqrt();
        }
    }
    
    private void dispSmallSqrt() {
        x = isqrtN;
        isqrt();
        dispFmt = String.format("%2d", r).replace(' ', ' ');
        if (ptr > 22) {
            System.out.println(dispLn);
            ptr = 1;
            dispLn = "";
        }
        dispLn += dispFmt;
        ptr += dispFmt.length();
    }
    
    private void bigSqrts() {
        for (int i = 0; i < 10; i++) {
            bigSqrt();
        }
    }
    
    private void bigSqrt() {
        x = pow7;
        isqrt();
        powNOut = String.format("%2d", powN).replace(' ', ' ');
        pow7Out = String.format("%10d", r).replace(' ', ' ');
        System.out.println("ISQRT(7^" + powNOut + ") = " + pow7Out);
        powN += 2;
        pow7 *= 49;
    }
    
    private void isqrt() {
        q = 1;
        while (q > x) {
            mulQBy4();
        }
        z = x;
        r = 0;
        while (q > 1) {
            isqrtStep();
        }
    }
    
    private void mulQBy4() {
        q *= 4;
    }
    
    private void isqrtStep() {
        q /= 4;
        t = z - r - q;
        r /= 2;
        if (t >= 0) {
            z = t;
            r += q;
        }
    }
}