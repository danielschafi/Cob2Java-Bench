public class Chapt12c {
    private static final String[] STATE_TABLE = {
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
        for (int i = 0; i < STATE_TABLE.length; i++) {
            String entry = STATE_TABLE[i];
            String abbrev = entry.substring(0, 2);
            if (abbrev.equals("TX")) {
                System.out.println("TX = " + entry.substring(2));
                return;
            }
        }
        System.out.println("State Not Found");
    }
}