public class Outcomes {
    static class Deck {
        int[] values = new int[10];
    }

    static int i;
    static int j;
    static long d;
    static long p;
    static long n;
    static Deck deck;

    public static void main(String[] args) {
        deck = new Deck();
        
        for (i = 1; i <= 9; i++) {
            deck.values[i - 1] = 4;
        }
        deck.values[9] = 16;
        
        d = 0;
        
        for (i = 1; i <= 10; i++) {
            p = 0;
            deck.values[i - 1]--;
            
            for (j = 1; j <= 10; j++) {
                deck.values[j - 1]--;
                n = partitions(deck, j);
                p += n;
                deck.values[j - 1]++;
            }
            
            System.out.println("Dealer showing " + i + " partitions = " + p);
            deck.values[i - 1]++;
            d += p;
        }
        
        System.out.println("Total partitions = " + d);
    }

    static long partitions(Deck cards, int subtotal) {
        long m = 0;
        
        for (int i = 1; i <= 10; i++) {
            if (cards.values[i - 1] > 0) {
                int total = i + subtotal;
                
                if (total >= 1 && total <= 20) {
                    m++;
                    long m1 = m;
                    cards.values[i - 1]--;
                    long v = partitions(cards, total);
                    m1 += v;
                    m = m1;
                    cards.values[i - 1]++;
                } else if (total == 21) {
                    m++;
                }
            }
        }
        
        return m;
    }
}