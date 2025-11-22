import java.math.BigDecimal;
import java.math.RoundingMode;

public class Chapt09e {
    private int monthOfSale = 12;
    private String categoryOfSale = "JEWL";
    private String saleItemFlag = "Y";
    private BigDecimal fullPrice = new BigDecimal("120.00");
    private BigDecimal salePrice = BigDecimal.ZERO;
    private int discountPercent = 0;

    private boolean isSaleItem() {
        return "Y".equals(saleItemFlag);
    }

    private void firstIfLogic() {
        if (isSaleItem()) {
            if (monthOfSale == 1 || monthOfSale == 2 || monthOfSale == 3) {
                if ("ANTI".equals(categoryOfSale) || "JEWL".equals(categoryOfSale) || "MISC".equals(categoryOfSale)) {
                    discountPercent = 50;
                    salePrice = fullPrice.multiply(new BigDecimal("0.5")).setScale(2, RoundingMode.HALF_UP);
                } else {
                    if ("XMAS".equals(categoryOfSale) || "CRAF".equals(categoryOfSale)) {
                        discountPercent = 75;
                        salePrice = fullPrice.multiply(new BigDecimal("0.25")).setScale(2, RoundingMode.HALF_UP);
                    } else {
                        discountPercent = 10;
                        salePrice = fullPrice.multiply(new BigDecimal("0.90")).setScale(2, RoundingMode.HALF_UP);
                    }
                }
            } else {
                if (monthOfSale == 4 || monthOfSale == 5 || monthOfSale == 6) {
                    if ("XMAS".equals(categoryOfSale) || "CRAF".equals(categoryOfSale)) {
                        discountPercent = 50;
                        salePrice = fullPrice.multiply(new BigDecimal("0.5")).setScale(2, RoundingMode.HALF_UP);
                    } else {
                        if ("ANTI".equals(categoryOfSale) || "JEWL".equals(categoryOfSale) || "MISC".equals(categoryOfSale)) {
                            discountPercent = 25;
                            salePrice = fullPrice.multiply(new BigDecimal("0.75")).setScale(2, RoundingMode.HALF_UP);
                        } else {
                            discountPercent = 10;
                            salePrice = fullPrice.multiply(new BigDecimal("0.90")).setScale(2, RoundingMode.HALF_UP);
                        }
                    }
                } else {
                    if (monthOfSale == 7 || monthOfSale == 8 || monthOfSale == 9) {
                        discountPercent = 25;
                        salePrice = fullPrice.multiply(new BigDecimal("0.75")).setScale(2, RoundingMode.HALF_UP);
                    } else {
                        if ("ANTI".equals(categoryOfSale)) {
                            discountPercent = 50;
                            salePrice = fullPrice.multiply(new BigDecimal("0.5")).setScale(2, RoundingMode.HALF_UP);
                        } else {
                            discountPercent = 10;
                            salePrice = fullPrice.multiply(new BigDecimal("0.9")).setScale(2, RoundingMode.HALF_UP);
                        }
                    }
                }
            }
        } else {
            salePrice = fullPrice;
        }
    }

    private void firstEvaluate() {
        boolean matched = false;
        
        if (isSaleItem() && monthOfSale >= 1 && monthOfSale <= 3 && 
            ("ANTI".equals(categoryOfSale) || "JEWL".equals(categoryOfSale) || "MISC".equals(categoryOfSale))) {
            discountPercent = 50;
            salePrice = fullPrice.multiply(new BigDecimal("0.5")).setScale(2, RoundingMode.HALF_UP);
            matched = true;
        } else if (isSaleItem() && monthOfSale >= 1 && monthOfSale <= 3 && 
                   ("XMAS".equals(categoryOfSale) || "CRAF".equals(categoryOfSale))) {
            discountPercent = 75;
            salePrice = fullPrice.multiply(new BigDecimal("0.25")).setScale(2, RoundingMode.HALF_UP);
            matched = true;
        } else if (isSaleItem() && monthOfSale >= 1 && monthOfSale <= 3) {
            discountPercent = 10;
            salePrice = fullPrice.multiply(new BigDecimal("0.9")).setScale(2, RoundingMode.HALF_UP);
            matched = true;
        } else if (isSaleItem() && monthOfSale >= 4 && monthOfSale <= 6 && 
                   ("XMAS".equals(categoryOfSale) || "CRAF".equals(categoryOfSale))) {
            discountPercent = 50;
            salePrice = fullPrice.multiply(new BigDecimal("0.5")).setScale(2, RoundingMode.HALF_UP);
            matched = true;
        } else if (isSaleItem() && monthOfSale >= 4 && monthOfSale <= 6 && 
                   ("ANTI".equals(categoryOfSale) || "JEWL".equals(categoryOfSale) || "MISC".equals(categoryOfSale))) {
            discountPercent = 25;
            salePrice = fullPrice.multiply(new BigDecimal("0.75")).setScale(2, RoundingMode.HALF_UP);
            matched = true;
        } else if (isSaleItem() && monthOfSale >= 4 && monthOfSale <= 6) {
            discountPercent = 10;
            salePrice = fullPrice.multiply(new BigDecimal("0.90")).setScale(2, RoundingMode.HALF_UP);
            matched = true;
        } else if (isSaleItem() && monthOfSale >= 6 && monthOfSale <= 9) {
            discountPercent = 25;
            salePrice = fullPrice.multiply(new BigDecimal("0.75")).setScale(2, RoundingMode.HALF_UP);
            matched = true;
        } else if (isSaleItem() && monthOfSale >= 10 && monthOfSale <= 12 && "ANTI".equals(categoryOfSale)) {
            discountPercent = 50;
            salePrice = fullPrice.multiply(new BigDecimal("0.5")).setScale(2, RoundingMode.HALF_UP);
            matched = true;
        } else if (isSaleItem() && monthOfSale >= 10 && monthOfSale <= 12) {
            discountPercent = 10;
            salePrice = fullPrice.multiply(new BigDecimal("0.9")).setScale(2, RoundingMode.HALF_UP);
            matched = true;
        }
        
        if (!matched) {
            salePrice = fullPrice;
        }
    }

    private void secondEvaluate() {
        boolean matched = false;
        
        if (isSaleItem() && 
            ((monthOfSale >= 1 && monthOfSale <= 3 && ("ANTI".equals(categoryOfSale) || "JEWL".equals(categoryOfSale) || "MISC".equals(categoryOfSale))) ||
             (monthOfSale >= 4 && monthOfSale <= 6 && ("XMAS".equals(categoryOfSale) || "CRAF".equals(categoryOfSale))) ||
             (monthOfSale >= 10 && monthOfSale <= 12 && "ANTI".equals(categoryOfSale)))) {
            discountPercent = 50;
            salePrice = fullPrice.multiply