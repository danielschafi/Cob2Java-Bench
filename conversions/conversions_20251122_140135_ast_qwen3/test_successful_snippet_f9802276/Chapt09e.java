public class Chapt09e {
    private static int monthOfSale = 12;
    private static String categoryOfSale = "JEWL";
    private static char saleItemFlag = 'Y';
    private static double fullPrice = 120.00;
    private static double salePrice = 0.0;
    private static int discountPercent = 0;

    public static void main(String[] args) {
        if (saleItemFlag == 'Y') {
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

        // First evaluate block
        switch (saleItemFlag) {
            case 'Y':
                switch (monthOfSale) {
                    case 1:
                    case 2:
                    case 3:
                        switch (categoryOfSale) {
                            case "ANTI":
                            case "JEWL":
                            case "MISC":
                                discountPercent = 50;
                                salePrice = fullPrice * 0.5;
                                break;
                            case "XMAS":
                            case "CRAF":
                                discountPercent = 75;
                                salePrice = fullPrice * 0.25;
                                break;
                            default:
                                discountPercent = 10;
                                salePrice = fullPrice * 0.9;
                                break;
                        }
                        break;
                    case 4:
                    case 5:
                    case 6:
                        switch (categoryOfSale) {
                            case "XMAS":
                            case "CRAF":
                                discountPercent = 50;
                                salePrice = fullPrice * 0.5;
                                break;
                            case "ANTI":
                            case "JEWL":
                            case "MISC":
                                discountPercent = 25;
                                salePrice = fullPrice * 0.75;
                                break;
                            default:
                                discountPercent = 10;
                                salePrice = fullPrice * 0.90;
                                break;
                        }
                        break;
                    case 7:
                    case 8:
                    case 9:
                        discountPercent = 25;
                        salePrice = fullPrice * 0.75;
                        break;
                    case 10:
                    case 11:
                    case 12:
                        if (categoryOfSale.equals("ANTI")) {
                            discountPercent = 50;
                            salePrice = fullPrice * 0.5;
                        } else {
                            discountPercent = 10;
                            salePrice = fullPrice * 0.9;
                        }
                        break;
                    default:
                        salePrice = fullPrice;
                        break;
                }
                break;
            default:
                salePrice = fullPrice;
                break;
        }

        System.out.println("Full Price " + fullPrice);
        System.out.println("Sale Price " + salePrice);

        // Second evaluate block
        switch (saleItemFlag) {
            case 'Y':
                switch (monthOfSale) {
                    case 1:
                    case 2:
                    case 3:
                        switch (categoryOfSale) {
                            case "ANTI":
                            case "JEWL":
                            case "MISC":
                            case "XMAS":
                            case "CRAF":
                                discountPercent = 50;
                                salePrice = fullPrice * 0.5;
                                break;
                            default:
                                discountPercent = 10;
                                salePrice = fullPrice * 0.9;
                                break;
                        }
                        break;
                    case 4:
                    case 5:
                    case 6:
                        switch (categoryOfSale) {
                            case "XMAS":
                            case "CRAF":
                                discountPercent = 50;
                                salePrice = fullPrice * 0.5;
                                break;
                            case "ANTI":
                            case "JEWL":
                            case "MISC":
                                discountPercent = 25;
                                salePrice = fullPrice * 0.75;
                                break;
                            default:
                                discountPercent = 10;
                                salePrice = fullPrice * 0.9;
                                break;
                        }
                        break;
                    case 7:
                    case 8:
                    case 9:
                        discountPercent = 25;
                        salePrice = fullPrice * 0.75;
                        break;
                    case 10:
                    case 11:
                    case 12:
                        if (categoryOfSale.equals("ANTI")) {
                            discountPercent = 50;
                            salePrice = fullPrice * 0.5;
                        } else {
                            discountPercent = 10;
                            salePrice = fullPrice * 0.9;
                        }
                        break;
                    default:
                        salePrice = fullPrice;
                        break;
                }
                break;
            default:
                salePrice = fullPrice;
                break;
        }

        System.out.println("Full Price " + fullPrice);
        System.out.println("Sale Price " + salePrice);
    }
}