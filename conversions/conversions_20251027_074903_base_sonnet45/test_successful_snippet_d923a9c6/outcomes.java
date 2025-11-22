import java.util.Arrays;

public class outcomes {
    
    public static void main(String[] args) {
        int i, j, d, p, n;
        int[] deckValues = new int[10];
        
        for (i = 0; i < 9; i++) {
            deckValues[i] = 4;
        }
        deckValues[9] = 16;
        
        d = 0;
        for (i = 0; i < 10; i++) {
            p = 0;
            deckValues[i]--;
            for (j = 0; j < 10; j++) {
                deckValues[j]--;
                n = partitions(deckValues, j);
                p += n;
                deckValues[j]++;
            }
            System.out.println("Dealer showing " + (i + 1) + " partitions = " + p);
            deckValues[i]++;
            d += p;
        }
        System.out.println("Total partitions = " + d);
    }
    
    public static int partitions(int[] cards, int subtotal) {
        int i, m, m1, v, total;
        int[] localCards = Arrays.copyOf(cards, cards.length);
        
        m = 0;
        for (i = 0; i < 10; i++) {
            if (localCards[i] > 0) {
                total = (i + 1) + subtotal;
                if (total >= 1 && total <= 20) {
                    m++;
                    m1 = m;
                    localCards[i]--;
                    v = partitions(localCards, total);
                    m1 += v;
                    m = m1;
                    localCards[i]++;
                } else if (total == 21) {
                    m++;
                }
            }
        }
        return m;
    }
}