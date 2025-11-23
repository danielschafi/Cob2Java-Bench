public class Chapt12c {
    private static String[] stateTableOccurrences = {
        "ALAlabama", "AKAlaska", "AZArizona", "ARArkansas", "CACalifornia",
        "COColorado", "CTConnecticut", "DCDistrict of Columbia", "DEDelaware",
        "FLFlorida", "GAGeorgia", "HIHawaii", "IDIdaho", "ILIllinois",
        "INIndiana", "IAIowa", "KSKansas", "KYKentucky", "LALouisiana",
        "MEMaine", "MDMaryland", "MAMassachusetts", "MIMichigan", "MNMinnesota",
        "MSMississipi", "MOMissouri", "MTMontana", "NENebraska", "NVNevada",
        "NHNew Hampshire", "NJNew Jersey", "NMNew Mexico", "NYNew York",
        "NCNorth Carolina", "NDNorth Dakota", "OHOhio", "OKOklahoma", "OROregon",
        "PAPennsylvania", "RIRhode Island", "SCSouth Carolina", "SDSouth Dakota",
        "TNTennessee", "TXTexas", "UTUtah", "VTVermont", "VAVirginia",
        "WAWashington", "WVWest Virginia", "WIWisconsin", "WYWyoming"
    };

    public static void main(String[] args) {
        searchForTexas();
    }

    private static void searchForTexas() {
        boolean found = false;
        for (int i = 0; i < stateTableOccurrences.length; i++) {
            String entry = stateTableOccurrences[i];
            String stateAbbrev = entry.substring(0, 2);
            String stateName = entry.substring(2);
            
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