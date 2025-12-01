public class EthiopianMultiplication {
    
    static class Container {
        long value;
        Container(long value) {
            this.value = value;
        }
    }
    
    public static void main(String[] args) {
        long l = 17;
        long r = 34;
        Container ethiopianMultiply = new Container(0);
        
        ethiopianMultiply(l, r, ethiopianMultiply);
        System.out.println(ethiopianMultiply.value);
        
        long product = l * r;
        System.out.println(product);
    }
    
    static void ethiopianMultiply(long l, long r, Container product) {
        product.value = 0;
        
        while (l != 0) {
            Container evenp = new Container(0);
            evenp(l, evenp);
            
            if (evenp.value == 0) {
                product.value = product.value + r;
            }
            
            Container lContainer = new Container(l);
            halve(l, lContainer);
            l = lContainer.value;
            
            Container rContainer = new Container(r);
            twice(r, rContainer);
            r = rContainer.value;
        }
    }
    
    static void halve(long n, Container m) {
        m.value = n / 2;
    }
    
    static void twice(long n, Container m) {
        m.value = n * 2;
    }
    
    static void evenp(long n, Container m) {
        long q = n / 2;
        m.value = 1 - (n % 2);
    }
}