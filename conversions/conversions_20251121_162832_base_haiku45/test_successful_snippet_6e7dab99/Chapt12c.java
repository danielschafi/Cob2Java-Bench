public class Chapt12c {
    public static void main(String[] args) {
        String[] stateTableData = {
            "ALAlabama              ",
            "AKAlaska               ",
            "AZArizona              ",
            "ARArkansas             ",
            "CACalifornia           ",
            "COColorado             ",
            "CTConnecticut          ",
            "DCDistrict of Columbia ",
            "DEDelaware             ",
            "FLFlorida              ",
            "GAGeorgia              ",
            "HIHawaii               ",
            "IDIdaho                ",
            "ILIllinois             ",
            "INIndiana              ",
            "IAIowa                 ",
            "KSKansas               ",
            "KYKentucky             ",
            "LALouisiana            ",
            "MEMaine                ",
            "MDMaryland             ",
            "MAMassachusetts        ",
            "MIMichigan             ",
            "MNMinnesota            ",
            "MSMississipi           ",
            "MOMissouri             ",
            "MTMontana              ",
            "NENebraska             ",
            "NVNevada               ",
            "NHNew Hampshire        ",
            "NJNew Jersey           ",
            "NMNew Mexico           ",
            "NYNew York             ",
            "NCNorth Carolina       ",
            "NDNorth Dakota         ",
            "OHOhio                 ",
            "OKOklahoma             ",
            "OROregon               ",
            "PAPennsylvania         ",
            "RIRhode Island         ",
            "SCSouth Carolina       ",
            "SDSouth Dakota         ",
            "TNTennessee            ",
            "TXTexas                ",
            "UTUtah                 ",
            "VTVermont              ",
            "VAVirginia             ",
            "WAWashington           ",
            "WVWest Virginia        ",
            "WIWisconsin            ",
            "WYWyoming              "
        };
        
        boolean found = false;
        String stateAbbrev;
        String stateName;
        
        for (int tableIndex = 0; tableIndex < stateTableData.length; tableIndex++) {
            stateAbbrev = stateTableData[tableIndex].substring(0, 2);
            stateName = stateTableData[tableIndex].substring(2, 22).trim();
            
            if (stateAbbrev.equals("TX")) {
                System.out.println("TX = " + stateName);
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("State Not Found");
        }
    }
}