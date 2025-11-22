```java
import java.util.*;

public class outcomes {
    static class Deck {
        int[] values = new int[10];
    }
    
    static int partitions(Deck cards, int subtotal) {
        int m = 0;
        
        for (int i = 0; i < 10; i++) {
            if (cards.values[i] > 0) {
                int total = i + 1 + subtotal;
                
                if (total >= 1 && total <= 20) {
                    m += 1;
                    int m1 = m;
                    cards.values[i] -= 1;
                    int v = partitions(cards, total);
                    m1 += v;
                    m = m1;
                    cards.values[i] += 1;
                } else if (total == 21) {
                    m += 1;
                }
            }
        }
        
        return m;
    }
    
    public static void main(String[] args) {
        Deck deck = new Deck();
        
        for (int i = 0; i < 9; i++) {
            deck.values[i] = 4;
        }
        deck.values[9] = 16;
        
        int d = 0;
        
        for (int i = 0; i < 10; i++) {
            int p = 0;
            deck.values[i] -= 1;
            
            for (int j = 0; j < 10; j++) {
                deck.values[j] -= 1;
                int n = partitions(deck, j + 1);
                p += n;
                deck.values[j] += 1;
            }
            
            System.out.println("Dealer showing " + (i + 1) + " partitions = " + p);
            deck.values[i] += 1;
            d += p;
        }
        
        System.out.println("Total partitions = " + d);
    }
}