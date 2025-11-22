public class Chapt12c {
    
    static class StateTableOccurrence {
        String stateAbbrev;
        String stateName;
        
        StateTableOccurrence(String abbrev, String name) {
            this.stateAbbrev = abbrev;
            this.stateName = name;
        }
    }
    
    static StateTableOccurrence[] stateTable = new StateTableOccurrence[51];
    static int tableIndex;
    
    static {
        stateTable[0] = new StateTableOccurrence("AL", "Alabama");
        stateTable[1] = new StateTableOccurrence("AK", "Alaska");
        stateTable[2] = new StateTableOccurrence("AZ", "Arizona");
        stateTable[3] = new StateTableOccurrence("AR", "Arkansas");
        stateTable[4] = new StateTableOccurrence("CA", "California");
        stateTable[5] = new StateTableOccurrence("CO", "Colorado");
        stateTable[6] = new StateTableOccurrence("CT", "Connecticut");
        stateTable[7] = new StateTableOccurrence("DC", "District of Columbia");
        stateTable[8] = new StateTableOccurrence("DE", "Delaware");
        stateTable[9] = new StateTableOccurrence("FL", "Florida");
        stateTable[10] = new StateTableOccurrence("GA", "Georgia");
        stateTable[11] = new StateTableOccurrence("HI", "Hawaii");
        stateTable[12] = new StateTableOccurrence("ID", "Idaho");
        stateTable[13] = new StateTableOccurrence("IL", "Illinois");
        stateTable[14] = new StateTableOccurrence("IN", "Indiana");
        stateTable[15] = new StateTableOccurrence("IA", "Iowa");
        stateTable[16] = new StateTableOccurrence("KS", "Kansas");
        stateTable[17] = new StateTableOccurrence("KY", "Kentucky");
        stateTable[18] = new StateTableOccurrence("LA", "Louisiana");
        stateTable[19] = new StateTableOccurrence("ME", "Maine");
        stateTable[20] = new StateTableOccurrence("MD", "Maryland");
        stateTable[21] = new StateTableOccurrence("MA", "Massachusetts");
        stateTable[22] = new StateTableOccurrence("MI", "Michigan");
        stateTable[23] = new StateTableOccurrence("MN", "Minnesota");
        stateTable[24] = new StateTableOccurrence("MS", "Mississipi");
        stateTable[25] = new StateTableOccurrence("MO", "Missouri");
        stateTable[26] = new StateTableOccurrence("MT", "Montana");
        stateTable[27] = new StateTableOccurrence("NE", "Nebraska");
        stateTable[28] = new StateTableOccurrence("NV", "Nevada");
        stateTable[29] = new StateTableOccurrence("NH", "New Hampshire");
        stateTable[30] = new StateTableOccurrence("NJ", "New Jersey");
        stateTable[31] = new StateTableOccurrence("NM", "New Mexico");
        stateTable[32] = new StateTableOccurrence("NY", "New York");
        stateTable[33] = new StateTableOccurrence("NC", "North Carolina");
        stateTable[34] = new StateTableOccurrence("ND", "North Dakota");
        stateTable[35] = new StateTableOccurrence("OH", "Ohio");
        stateTable[36] = new StateTableOccurrence("OK", "Oklahoma");
        stateTable[37] = new StateTableOccurrence("OR", "Oregon");
        stateTable[38] = new StateTableOccurrence("PA", "Pennsylvania");
        stateTable[39] = new StateTableOccurrence("RI", "Rhode Island");
        stateTable[40] = new StateTableOccurrence("SC", "South Carolina");
        stateTable[41] = new StateTableOccurrence("SD", "South Dakota");
        stateTable[42] = new StateTableOccurrence("TN", "Tennessee");
        stateTable[43] = new StateTableOccurrence("TX", "Texas");
        stateTable[44] = new StateTableOccurrence("UT", "Utah");
        stateTable[45] = new StateTableOccurrence("VT", "Vermont");
        stateTable[46] = new StateTableOccurrence("VA", "Virginia");
        stateTable[47] = new StateTableOccurrence("WA", "Washington");
        stateTable[48] = new StateTableOccurrence("WV", "West Virginia");
        stateTable[49] = new StateTableOccurrence("WI", "Wisconsin");
        stateTable[50] = new StateTableOccurrence("WY", "Wyoming");
    }
    
    public static void main(String[] args) {
        boolean found = false;
        
        for (tableIndex = 0; tableIndex < stateTable.length; tableIndex++) {
            if (stateTable[tableIndex].stateAbbrev.equals("TX")) {
                System.out.println("TX = " + stateTable[tableIndex].stateName);
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("State Not Found");
        }
    }
}