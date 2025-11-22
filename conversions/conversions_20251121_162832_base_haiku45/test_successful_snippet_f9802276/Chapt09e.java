```java
public class Chapt09e {
    public static void main(String[] args) {
        // Entry-Fields
        int monthOfSale = 12;
        String categoryOfSale = "JEWL";
        boolean saleItem = true;
        double fullPrice = 120.00;
        double salePrice = 0.0;
        int discountPercent = 0;

        // First IF-ELSE block
        if (saleItem) {
            if (monthOfSale == 1 || monthOfSale == 2 || monthOfSale == 3) {
                if (categoryOfSale.equals("ANTI") || categoryOfSale.equals("JEWL") || categoryOfSale.equals("MISC")) {
                    discountPercent = 50;
                    salePrice = fullPrice * 0.5;
                } else {
                    if (categoryOfSale.equals("XMAS") || categoryOfSale.equals("CRAF")) {
                        discountPercent = 75;
                        salePrice = fullPrice * 0.25;
                    } else {
                        discountPercent = 10;
                        salePrice = fullPrice * 0.90;
                    }
                }
            } else {
                if (monthOfSale == 4 || monthOfSale == 5 || monthOfSale == 6) {
                    if (categoryOfSale.equals("XMAS") || categoryOfSale.equals("CRAF")) {
                        discountPercent = 50;
                        salePrice = fullPrice * 0.5;
                    } else {
                        if (categoryOfSale.equals("ANTI") || categoryOfSale.equals("JEWL") || categoryOfSale.equals("MISC")) {
                            discountPercent = 25;
                            salePrice = fullPrice * 0.75;
                        } else {
                            discountPercent = 10;
                            salePrice = fullPrice * 0.90;
                        }
                    }
                } else {
                    if (monthOfSale == 7 || monthOfSale == 8 || monthOfSale == 9) {
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
                }
            }
        } else {
            salePrice = fullPrice;
        }

        System.out.println("Full Price " + fullPrice);
        System.out.println("Sale Price " + salePrice);

        // First EVALUATE block
        if (saleItem && ((monthOfSale >= 1 && monthOfSale <= 3) && (categoryOfSale.equals("ANTI") || categoryOfSale.equals("JEWL") || categoryOfSale.equals("MISC")))) {
            discountPercent = 50;
            salePrice = fullPrice * 0.5;
        } else if (saleItem && ((monthOfSale >= 1 && monthOfSale <= 3) && (categoryOfSale.equals("XMAS") || categoryOfSale.equals("CRAF")))) {
            discountPercent = 75;
            salePrice = fullPrice * 0.25;
        } else if (saleItem && (monthOfSale >= 1 && monthOfSale <= 3)) {
            discountPercent = 10;
            salePrice = fullPrice * 0.9;
        } else if (saleItem && ((monthOfSale >= 4 && monthOfSale <= 6) && (categoryOfSale.equals("XMAS") || categoryOfSale.equals("CRAF")))) {
            discountPercent = 50;
            salePrice = fullPrice * 0.5;
        } else if (saleItem && ((monthOfSale >= 4 && monthOfSale <= 6) && (categoryOfSale.equals("ANTI") || categoryOfSale.equals("JEWL") || categoryOfSale.equals("MISC")))) {
            discountPercent = 25;
            salePrice = fullPrice * 0.75;
        } else if (saleItem && (monthOfSale >= 4 && monthOfSale <= 6)) {
            discountPercent = 10;
            salePrice = fullPrice * 0.90;
        } else if (saleItem && (monthOfSale >= 6 && monthOfSale <= 9)) {
            discountPercent = 25;
            salePrice = fullPrice * 0.75;
        } else if (saleItem && (monthOfSale >= 10 && monthOfSale <= 12) && categoryOfSale.equals("ANTI")) {
            discountPercent = 50;
            salePrice = fullPrice * 0.5;
        } else if (saleItem && (monthOfSale >= 10 && monthOfSale <= 12)) {
            discountPercent = 10;
            salePrice = fullPrice * 0.9;
        } else {
            salePrice = fullPrice;
        }

        System.out.println("Full Price " + fullPrice);
        System.out.println("Sale Price " + salePrice);

        // Second EVALUATE block
        if (saleItem && ((monthOfSale >= 1 && monthOfSale <= 3) && (categoryOfSale.equals("ANTI") || categoryOfSale.equals("JEWL") || categoryOfSale.equals("MISC"))) ||
            (saleItem && ((monthOfSale >= 4 && monthOfSale <= 6) && (categoryOfSale.equals("XMAS") || categoryOfSale.equals("CRAF")))) ||
            (saleItem && (monthOfSale >= 10 && monthOfSale <= 12) && categoryOfSale.equals("ANTI"))) {
            discountPercent = 50;
            salePrice = fullPrice * 0.5;
        } else if (saleItem && ((monthOfSale >= 1 && monthOfSale <= 3) && (categoryOfSale.equals("XMAS") || categoryOfSale.equals("CRAF")))) {
            discountPercent = 75;
            salePrice = fullPrice * 0.25;
        } else if ((saleItem && ((monthOfSale >= 4 && monthOfSale <= 6) && (categoryOfSale.equals("ANTI") || categoryOfSale.equals("JEWL") || categoryOfSale.equals("MISC")))) ||
                   (saleItem && (monthOfSale >= 6 && monthOfSale <= 9))) {
            discountPercent = 25;
            salePrice = fullPrice * 0.75;
        } else if ((saleItem && (monthOfSale >= 1 && monthOfSale <= 3)) ||
                   (saleItem && (monthOfSale >= 4 && monthOfSale <= 6)) ||
                   (saleItem && (monthOfSale >= 10 && monthOfSale <= 12))) {
            discountPercent = 10;
            salePrice = fullPrice * 0.9;
        } else {
            salePrice = fullPrice;
        }

        System.out.println("Full Price " + fullPrice);
        System.out.println("Sale Price " + salePrice);
    }
}