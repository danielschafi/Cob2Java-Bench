public class middle3 {
    static class Data {
        int[] items = {123, 12345, 1234567, 987654321, 10001, -10001, -123, -100, 100, -12345, 1, 2, -1, -10, 2002, -2002, 0};
        int num;
        int num2;
        int power;
        long power10;
        int threeDigits;
        String result;
        int flag;
    }

    public static void main(String[] args) {
        Data data = new Data();
        
        for (int item = 0; item < data.items.length; item++) {
            if (data.items[item] == 0) {
                break;
            }
            outer(data, item);
        }
    }

    static void outer(Data data, int item) {
        data.num = data.items[item];
        
        if (data.num < 100) {
            data.result = "too small";
        } else {
            boolean done = false;
            for (data.power = 9; data.power >= 2; data.power--) {
                inner(data);
                if (done) {
                    break;
                }
            }
        }
        
        String numDisp = String.format("%,d", data.num);
        System.out.println(numDisp + " --> " + data.result);
    }

    static void inner(Data data) {
        data.flag = 0;
        data.power10 = (long) Math.pow(10, data.power);
        
        if (data.num >= data.power10) {
            data.flag = 1;
            if (data.power % 2 == 1) {
                data.result = "even number digits";
            } else {
                data.num2 = data.num;
                int divisor = (int) Math.pow(10, (data.power / 2) - 1);
                data.num2 = data.num2 / divisor;
                data.threeDigits = data.num2 % 1000;
                data.result = String.format("%03d", data.threeDigits);
            }
        }
    }
}