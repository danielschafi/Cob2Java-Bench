public class Outcomes {
    private static int[] deckValues = new int[10];
    private static int d;
    
    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            deckValues[i] = 4;
        }
        deckValues[9] = 16;
        d = 0;
        
        for (int i = 0; i < 10; i++) {
            int p = 0;
            deckValues[i]--;
            
            for (int j = 0; j < 10; j++) {
                deckValues[j]--;
                int n = partitions(deckValues, j);
                p += n;
                deckValues[j]++;
            }
            
            System.out.println("Dealer showing " + (i+1) + " partitions = " + p);
            deckValues[i]++;
            d += p;
        }
        
        System.out.println("Total partitions = " + d);
    }
    
    private static int partitions(int[] cards, int subtotal) {
        int m = 0;
        
        for (int i = 0; i < 10; i++) {
            if (cards[i] > 0) {
                int total = i + subtotal;
                
                if (total >= 1 && total <= 20) {
                    m++;
                    int m1 = m;
                    cards[i]--;
                    m1 += partitions(cards, total);
                    m = m1;
                    cards[i]++;
                } else if (total == 21) {
                    m++;
                }
            }
        }
        
        return m;
    }
}