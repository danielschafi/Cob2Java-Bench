public class Chapt12c {
    static class StateEntry {
        String abbrev;
        String name;
        
        StateEntry(String abbrev, String name) {
            this.abbrev = abbrev;
            this.name = name;
        }
    }
    
    static StateEntry[] stateTable = {
        new StateEntry("AL", "Alabama"),
        new StateEntry("AK", "Alaska"),
        new StateEntry("AZ", "Arizona"),
        new StateEntry("AR", "Arkansas"),
        new StateEntry("CA", "California"),
        new StateEntry("CO", "Colorado"),
        new StateEntry("CT", "Connecticut"),
        new StateEntry("DC", "District of Columbia"),
        new StateEntry("DE", "Delaware"),
        new StateEntry("FL", "Florida"),
        new StateEntry("GA", "Georgia"),
        new StateEntry("HI", "Hawaii"),
        new StateEntry("ID", "Idaho"),
        new StateEntry("IL", "Illinois"),
        new StateEntry("IN", "Indiana"),
        new StateEntry("IA", "Iowa"),
        new StateEntry("KS", "Kansas"),
        new StateEntry("KY", "Kentucky"),
        new StateEntry("LA", "Louisiana"),
        new StateEntry("ME", "Maine"),
        new StateEntry("MD", "Maryland"),
        new StateEntry("MA", "Massachusetts"),
        new StateEntry("MI", "Michigan"),
        new StateEntry("MN", "Minnesota"),
        new StateEntry("MS", "Mississipi"),
        new StateEntry("MO", "Missouri"),
        new StateEntry("MT", "Montana"),
        new StateEntry("NE", "Nebraska"),
        new StateEntry("NV", "Nevada"),
        new StateEntry("NH", "New Hampshire"),
        new StateEntry("NJ", "New Jersey"),
        new StateEntry("NM", "New Mexico"),
        new StateEntry("NY", "New York"),
        new StateEntry("NC", "North Carolina"),
        new StateEntry("ND", "North Dakota"),
        new StateEntry("OH", "Ohio"),
        new StateEntry("OK", "Oklahoma"),
        new StateEntry("OR", "Oregon"),
        new StateEntry("PA", "Pennsylvania"),
        new StateEntry("RI", "Rhode Island"),
        new StateEntry("SC", "South Carolina"),
        new StateEntry("SD", "South Dakota"),
        new StateEntry("TN", "Tennessee"),
        new StateEntry("TX", "Texas"),
        new StateEntry("UT", "Utah"),
        new StateEntry("VT", "Vermont"),
        new StateEntry("VA", "Virginia"),
        new StateEntry("WA", "Washington"),
        new StateEntry("WV", "West Virginia"),
        new StateEntry("WI", "Wisconsin"),
        new StateEntry("WY", "Wyoming")
    };
    
    public static void main(String[] args) {
        boolean found = false;
        
        for (int i = 0; i < stateTable.length; i++) {
            if (stateTable[i].abbrev.equals("TX")) {
                System.out.println("TX = " + stateTable[i].name);
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("State Not Found");
        }
    }
}