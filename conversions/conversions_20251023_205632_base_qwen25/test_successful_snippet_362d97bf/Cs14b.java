import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

class ElementTable {
    static class Element {
        char value;
        long count;
    }

    Element[] elements = new Element[26];

    ElementTable() {
        for (int i = 0; i < 26; i++) {
            elements[i] = new Element();
        }
    }

    int lmntidx(char lsLmnt) {
        for (int lmntIdx = 0; lmntIdx < 26; lmntIdx++) {
            if (elements[lmntIdx].value == lsLmnt || elements[lmntIdx].value == ' ') {
                if (elements[lmntIdx].value == ' ') {
                    elements[lmntIdx].value = lsLmnt;
                    if ("TEST".equals(ProcessSwitch.processSw)) {
                        System.out.println("lmntidx adding " + lsLmnt + " @ " + (lmntIdx + 1));
                    }
                }
                return lmntIdx + 1;
            }
        }
        return 0;
    }

    void lmntdump() {
        for (int lmntIdx = 0; lmntIdx < 26; lmntIdx++) {
            if (elements[lmntIdx].value == ' ') {
                break;
            }
            if ("TEST".equals(ProcessSwitch.processSw)) {
                System.out.println("lmntdump element " + elements[lmntIdx].value + " " + elements[lmntIdx].count);
            }
        }
    }
}

class RuleTable {
    static class Rule {
        String pair;
        char element;
    }

    Rule[] rules = new Rule[100];

    RuleTable() {
        for (int i = 0; i < 100; i++) {
            rules[i] = new Rule();
        }
    }

    void sort() {
        Arrays.sort(rules, (r1, r2) -> r1.pair.compareTo(r2.pair));
    }

    int searchRules(String polyPair) {
        for (int ruleIdx = 0; ruleIdx < 100; ruleIdx++) {
            if (rules[ruleIdx].pair.equals(polyPair)) {
                return ruleIdx;
            }
        }
        return -1;
    }
}

class PolymerTable {
    static class Polymer {
        String pair;
        long count;
        boolean active;
    }

    Polymer[] polymers = new Polymer[676];

    PolymerTable() {
        for (int i = 0; i < 676; i++) {
            polymers[i] = new Polymer();
        }
    }

    void initPolymerTable(String initialPolymer) {
        for (int polyIdx = 0; polyIdx < initialPolymer.length() - 1; polyIdx++) {
            polymers[polyIdx].pair = initialPolymer.substring(polyIdx, polyIdx + 2);
            polymers[polyIdx].count = 1;
            polymers[polyIdx].active = true;
        }
    }

    void polydump() {
        for (int polyIdx = 0; polyIdx < 676; polyIdx++) {
            if (polymers[polyIdx].pair == null) {
                break;
            }
            if ("TEST".equals(ProcessSwitch.processSw)) {
                System.out.println("polydump " + (polyIdx + 1) + " pair " + polymers[polyIdx].pair + " " + polymers[polyIdx].count + " " + polymers[polyIdx].active);
            }
        }
    }

    void constructPolymer(ElementTable elementTable, RuleTable ruleTable, int nbSteps) {
        for (int stepCount = 0; stepCount < nbSteps; stepCount++) {
            PolymerTable newPolymerTable = new PolymerTable();
            System.arraycopy(polymers, 0, newPolymerTable.polymers, 0, 676);
            for (int polyIdx = 0; polyIdx < 676; polyIdx++) {
                if (polymers[polyIdx].pair == null || polymers[polyIdx].count == 0) {
                    break;
                }
                int ruleIdx = ruleTable.searchRules(polymers[polyIdx].pair);
                if (ruleIdx != -1) {
                    int lmntIdx = elementTable.lmntidx(ruleTable.rules[ruleIdx].element);
                    elementTable.elements[lmntIdx].count += polymers[polyIdx].count;
                    newPolymerTable.polymers[polyIdx].count -= polymers[polyIdx].count;
                    String newPair1 = polymers[polyIdx].pair.charAt(0) + "" + ruleTable.rules[ruleIdx].element;
                    String newPair2 = ruleTable.rules[ruleIdx].element + "" + polymers[polyIdx].pair.charAt(1);
                    newPolymerTable.setNewPairActive(newPair1, polymers[polyIdx].count);
                    newPolymerTable.setNewPairActive(newPair2, polymers[polyIdx].count);
                }
            }
            System.arraycopy(newPolymerTable.polymers, 0, polymers, 0, 676);
        }
    }

    void setNewPairActive(String newPair, long count) {
        for (int polyIdx = 0; polyIdx < 676; polyIdx++) {
            if (polymers[polyIdx].pair == null) {
                polymers[polyIdx].pair = newPair;
                polymers[polyIdx].count = count;
                polymers[polyIdx].active = true;
                if ("TEST".equals(ProcessSwitch.processSw)) {
                    System.out.println("pairidx adding " + newPair + " @ " + (polyIdx + 1));
                }
                break;
            } else if (polymers[polyIdx].pair.equals(newPair)) {
                polymers[polyIdx].count += count;
                break;
            }
        }
    }
}

class ProcessSwitch {
    static String processSw;
}

public class Cs14b {
    public static void main(String[] args) throws IOException {
        String myName = "cs14b";
        int wsRecCount = 0;
        int ruleCount = 0;
        int nbSteps = 0;
        int stepCount = 0;
        long lmntMax = 0;
        long lmntMin = 9999999999L;
        long lmntDif = 0;
        String nbStepsX = " ";
        String holdLmnt = " ";
        String processType = " ";
        String initialPolymer = " ";
        String newPair = " ";
        String wsInpt = " ";
        boolean inptDataEof = false;
        boolean rulesNow = false;

        ProcessSwitch.processSw = " ";

        ElementTable elementTable = new ElementTable();
        RuleTable ruleTable = new RuleTable();
        PolymerTable polymerTable = new PolymerTable();

        System.out.println(myName + " " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        if (args.length > 0) {
            processType = args[0].toUpperCase();
            ProcessSwitch.processSw = processType;
        }
        if (args.length > 1) {
            nbStepsX = args[1];
            nbSteps = Integer.parseInt(nbStepsX);
        }

        System.out.println(myName + " nb steps  " + nbSteps);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while ((wsInpt = reader.readLine()) != null) {
            wsRecCount++;
            if (wsRecCount == 1) {
                polymerTable.initPolymerTable(wsInpt);
                for (int polyIdx = 0; polyIdx < 676; polyIdx++) {
                    if (polymerTable.polymers[polyIdx].pair == null) {
                        break;
                    }
                    int lmntIdx = elementTable.lmntidx(polymerTable.polymers[polyIdx].pair.charAt(0));
                    elementTable.elements[lmntIdx].count += polymerTable.polymers[polyIdx].count;
                }
            } else if (wsInpt.isEmpty()) {
                rulesNow = true;
            } else if (rulesNow) {
                ruleCount++;
                String[] parts = wsInpt.split(" -> ");
                ruleTable.rules[ruleCount - 1].pair = parts[0];
                ruleTable.rules[ruleCount - 1].element = parts[1].charAt(0);
            }
        }

        System.out.println(myName + " number of rules " + ruleCount);

        ruleTable.sort();

        polymerTable.constructPolymer(elementTable, ruleTable, nbSteps);

        if ("TEST".equals(ProcessSwitch.processSw)) {
            polymerTable.polydump();
        }

        for (int lmntIdx = 0; lmntIdx < 26; lmntIdx++) {
            if (elementTable.elements[lmntIdx].value == ' ') {
                break;
            }
            if (elementTable.elements[lmntIdx].count < lmntMin) {
                lmntMin = elementTable.elements[lmntIdx].count;
            }
            if (elementTable.elements[lmntIdx].count > lmntMax) {
                lmntMax = elementTable.elements[lmntIdx].count;
            }
        }

        lmntDif = lmntMax - lmntMin;

        System.out.println(myName + " most common     " + lmntMax);
        System.out.println(myName + " least common    " + lmntMin);
        System