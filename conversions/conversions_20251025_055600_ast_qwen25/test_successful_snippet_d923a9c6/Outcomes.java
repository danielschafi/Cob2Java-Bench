import java.util.Arrays;

public class Outcomes {
    private int[] deckValues = new int[10];
    private int d;
    private int p;
    private int n;

    public static void main(String[] args) {
        Outcomes outcomes = new Outcomes();
        outcomes.run();
    }

    public void run() {
        for (int i = 0; i < 9; i++) {
            deckValues[i] = 4;
        }
        deckValues[9] = 16;
        d = 0;
        for (int i = 0; i < 10; i++) {
            p = 0;
            deckValues[i]--;
            for (int j = 0; j < 10; j++) {
                deckValues[j]--;
                n = partitions(deckValues.clone(), j + 1);
                p += n;
                deckValues[j]++;
            }
            System.out.println("Dealer showing " + (i + 1) + " partitions = " + p);
            deckValues[i]++;
            d += p;
        }
        System.out.println("Total partitions = " + d);
    }

    public int partitions(int[] cards, int subtotal) {
        int m = 0;
        for (int i = 0; i < 10; i++) {
            if (cards[i] > 0) {
                int total = i + 1 + subtotal;
                if (total >= 1 && total <= 20) {
                    m++;
                    cards[i]--;
                    m += partitions(cards.clone(), total);
                    cards[i]++;
                } else if (total == 21) {
                    m++;
                }
            }
        }
        return m;
    }
}