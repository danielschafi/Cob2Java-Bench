import java.text.DecimalFormat;

public class Chapt09e {
    public static void main(String[] args) {
        int monthOfSale = 12;
        String categoryOfSale = "JEWL";
        boolean saleItem = true;
        double fullPrice = 120.00;
        double salePrice = 0.00;
        int discountPercent = 0;

        if (saleItem) {
            if (monthOfSale >= 1 && monthOfSale <= 3) {
                if (categoryOfSale.equals("ANTI") || categoryOfSale.equals("JEWL") || categoryOfSale.equals("MISC")) {
                    discountPercent = 50;
                    salePrice = fullPrice * 0.5;
                } else if (categoryOfSale.equals("XMAS") || categoryOfSale.equals("CRAF")) {
                    discountPercent = 75;
                    salePrice = fullPrice * 0.25;
                } else {
                    discountPercent = 10;
                    salePrice = fullPrice * 0.90;
                }
            } else if (monthOfSale >= 4 && monthOfSale <= 6) {
                if (categoryOfSale.equals("XMAS") || categoryOfSale.equals("CRAF")) {
                    discountPercent = 50;
                    salePrice = fullPrice * 0.5;
                } else if (categoryOfSale.equals("ANTI") || categoryOfSale.equals("JEWL") || categoryOfSale.equals("MISC")) {
                    discountPercent = 25;
                    salePrice = fullPrice * 0.75;
                } else {
                    discountPercent = 10;
                    salePrice = fullPrice * 0.90;
                }
            } else if (monthOfSale >= 7 && monthOfSale <= 9) {
                discountPercent = 25;
                salePrice = fullPrice * 0.75;
            } else {
                if (categoryOfSale.equals("ANTI")) {
                    discountPercent = 50;
                    salePrice = fullPrice * 0.5;
                } else {
                    discountPercent = 10;
                    salePrice = fullPrice * 0.9;
                }
            }
        } else {
            salePrice = fullPrice;
        }

        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("Full Price " + df.format(fullPrice));
        System.out.println("Sale Price " + df.format(salePrice));

        if (saleItem) {
            if ((monthOfSale >= 1 && monthOfSale <= 3) && (categoryOfSale.equals("ANTI") || categoryOfSale.equals("JEWL") || categoryOfSale.equals("MISC"))) {
                discountPercent = 50;
                salePrice = fullPrice * 0.5;
            } else if ((monthOfSale >= 1 && monthOfSale <= 3) && (categoryOfSale.equals("XMAS") || categoryOfSale.equals("CRAF"))) {
                discountPercent = 75;
                salePrice = fullPrice * 0.25;
            } else if ((monthOfSale >= 1 && monthOfSale <= 3)) {
                discountPercent = 10;
                salePrice = fullPrice * 0.9;
            } else if ((monthOfSale >= 4 && monthOfSale <= 6) && (categoryOfSale.equals("XMAS") || categoryOfSale.equals("CRAF"))) {
                discountPercent = 50;
                salePrice = fullPrice * 0.5;
            } else if ((monthOfSale >= 4 && monthOfSale <= 6) && (categoryOfSale.equals("ANTI") || categoryOfSale.equals("JEWL") || categoryOfSale.equals("MISC"))) {
                discountPercent = 25;
                salePrice = fullPrice * 0.75;
            } else if ((monthOfSale >= 4 && monthOfSale <= 6)) {
                discountPercent = 10;
                salePrice = fullPrice * 0.90;
            } else if ((monthOfSale >= 6 && monthOfSale <= 9)) {
                discountPercent = 25;
                salePrice = fullPrice * 0.75;
            } else if ((monthOfSale >= 10 && monthOfSale <= 12) && (categoryOfSale.equals("ANTI"))) {
                discountPercent = 50;
                salePrice = fullPrice * 0.5;
            } else if ((monthOfSale >= 10 && monthOfSale <= 12)) {
                discountPercent = 10;
                salePrice = fullPrice * 0.9;
            } else {
                salePrice = fullPrice;
            }
        } else {
            salePrice = fullPrice;
        }

        System.out.println("Full Price " + df.format(fullPrice));
        System.out.println("Sale Price " + df.format(salePrice));

        if (saleItem) {
            if ((monthOfSale >= 1 && monthOfSale <= 3) && (categoryOfSale.equals("ANTI") || categoryOfSale.equals("JEWL") || categoryOfSale.equals("MISC") || categoryOfSale.equals("XMAS") || categoryOfSale.equals("CRAF"))) {
                discountPercent = 50;
                salePrice = fullPrice * 0.5;
            } else if ((monthOfSale >= 1 && monthOfSale <= 3) && (categoryOfSale.equals("XMAS") || categoryOfSale.equals("CRAF"))) {
                discountPercent = 75;
                salePrice = fullPrice * 0.25;
            } else if ((monthOfSale >= 1 && monthOfSale <= 3)) {
                discountPercent = 10;
                salePrice = fullPrice * 0.9;
            } else if ((monthOfSale >= 4 && monthOfSale <= 6) && (categoryOfSale.equals("ANTI") || categoryOfSale.equals("JEWL") || categoryOfSale.equals("MISC") || categoryOfSale.equals("XMAS") || categoryOfSale.equals("CRAF"))) {
                discountPercent = 50;
                salePrice = fullPrice * 0.5;
            } else if ((monthOfSale >= 4 && monthOfSale <= 6)) {
                discountPercent = 10;
                salePrice = fullPrice * 0.90;
            } else if ((monthOfSale >= 6 && monthOfSale <= 9)) {
                discountPercent = 25;
                salePrice = fullPrice * 0.75;
            } else if ((monthOfSale >= 10 && monthOfSale <= 12) && (categoryOfSale.equals("ANTI"))) {
                discountPercent = 50;
                salePrice = fullPrice * 0.5;
            } else if ((monthOfSale >= 10 && monthOfSale <= 12)) {
                discountPercent = 10;
                salePrice = fullPrice * 0.9;
            } else {
                salePrice = fullPrice;
            }
        } else {
            salePrice = fullPrice;
        }

        System.out.println("Full Price " + df.format(fullPrice));
        System.out.println("Sale Price " + df.format(salePrice));
    }
}