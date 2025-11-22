public class Outcomes {
    private static int[] deck = new int[10];
    private static int d;
    private static int p;
    private static int n;
    private static int i;
    private static int j;
    
    public static void main(String[] args) {
        for (i = 0; i < 9; i++) {
            deck[i] = 4;
        }
        deck[9] = 16;
        d = 0;
        for (i = 0; i < 10; i++) {
            p = 0;
            deck[i]--;
            for (j = 0; j < 10; j++) {
                deck[j]--;
                n = partitions(deck, j);
                p += n;
                deck[j]++;
            }
            System.out.println("Dealer showing " + (i+1) + " partitions = " + p);
            deck[i]++;
            d += p;
        }
        System.out.println("Total partitions = " + d);
    }
    
    private static int partitions(int[] cards, int subtotal) {
        int[] cardsCopy = cards.clone();
        int i;
        int total;
        int m = 0;
        
        for (i = 0; i < 10; i++) {
            if (cardsCopy[i] > 0) {
                total = i + subtotal;
                switch (total) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                        m++;
                        cardsCopy[i]--;
                        m += partitions(cardsCopy, total);
                        cardsCopy[i]++;
                        break;
                    case 21:
                        m++;
                        break;
                }
            }
        }
        return m;
    }
}