import java.util.Arrays;

public class Outcomes {
    public static void main(String[] args) {
        int[] deckValues = new int[10];
        Arrays.fill(deckValues, 4);
        deckValues[9] = 16;
        int d = 0;

        for (int i = 1; i <= 9; i++) {
            deckValues[i - 1]--;
            int p = 0;
            for (int j = 1; j <= 10; j++) {
                deckValues[j - 1]--;
                int n = partitions(deckValues.clone(), 0);
                p += n;
                deckValues[j - 1]++;
            }
            System.out.println("Dealer showing " + i + " partitions = " + p);
            deckValues[i - 1]++;
            d += p;
        }
        System.out.println("Total partitions = " + d);
    }

    public static int partitions(int[] cards, int subtotal) {
        int m = 0;
        for (int i = 1; i <= 10; i++) {
            if (cards[i - 1] > 0) {
                int total = i + subtotal;
                if (total >= 1 && total <= 20) {
                    m++;
                    cards[i - 1]--;
                    m += partitions(cards.clone(), total);
                    cards[i - 1]++;
                } else if (total == 21) {
                    m++;
                }
            }
        }
        return m;
    }
}