package ba.unsa.etf.rpr.adventOfCode.Day8;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//DAY 2 ZADATAK 1
public class Zadatak2 {
    public static class Triple<L,R, K> {
        private L type;
        private R number;
        private K visited;

        public Triple() {
        }

        public Triple(L type, R number, K visited) {
            this.type = type;
            this.number = number;
            this.visited = visited;
        }

        public L getType() {
            return type;
        }

        public void setType(L type) {
            this.type = type;
        }

        public R getNumber() {
            return number;
        }

        public void setNumber(R number) {
            this.number = number;
        }

        public K getVisited() {
            return visited;
        }

        public void setVisited(K visited) {
            this.visited = visited;
        }

        public void printTriplet() {
            System.out.println(getType() + ", " + getNumber() + ", " + getVisited());
        }
    }

    public static class HaltedChecker<L,R, K> {
        private L halted;
        private R index;
        private K accCounter;

        public HaltedChecker(L halted, R index, K accCounter) {
            this.halted = halted;
            this.index = index;
            this.accCounter = accCounter;
        }

        public L getHalted() {
            return halted;
        }

        public void setHalted(L halted) {
            this.halted = halted;
        }

        public R getIndex() {
            return index;
        }

        public void setIndex(R index) {
            this.index = index;
        }

        public K getAccCounter() {
            return accCounter;
        }

        public void setAccCounter(K accCounter) {
            this.accCounter = accCounter;
        }
    }

    static String input(){
        String input = "acc +22\n" +
                "acc +42\n" +
                "nop +456\n" +
                "jmp +5\n" +
                "acc +31\n" +
                "acc +49\n" +
                "acc +10\n" +
                "jmp +519\n" +
                "nop +390\n" +
                "jmp +418\n" +
                "nop +29\n" +
                "acc -4\n" +
                "jmp +156\n" +
                "jmp +85\n" +
                "acc +5\n" +
                "acc +26\n" +
                "jmp +497\n" +
                "acc -6\n" +
                "acc -18\n" +
                "acc +20\n" +
                "acc +4\n" +
                "jmp -8\n" +
                "jmp +372\n" +
                "jmp +371\n" +
                "jmp -1\n" +
                "jmp +1\n" +
                "nop +378\n" +
                "acc +18\n" +
                "jmp +388\n" +
                "jmp +1\n" +
                "acc +29\n" +
                "acc +37\n" +
                "jmp +1\n" +
                "jmp +425\n" +
                "acc +19\n" +
                "acc +13\n" +
                "jmp +477\n" +
                "acc +7\n" +
                "jmp +469\n" +
                "nop +495\n" +
                "nop +141\n" +
                "acc +22\n" +
                "jmp +517\n" +
                "jmp +125\n" +
                "nop +30\n" +
                "acc +37\n" +
                "acc +23\n" +
                "nop +238\n" +
                "jmp +110\n" +
                "jmp +411\n" +
                "acc +2\n" +
                "acc -19\n" +
                "acc -19\n" +
                "jmp +296\n" +
                "acc +0\n" +
                "acc +14\n" +
                "acc +20\n" +
                "jmp +75\n" +
                "nop +88\n" +
                "acc -16\n" +
                "acc +40\n" +
                "acc +27\n" +
                "jmp +131\n" +
                "acc +33\n" +
                "nop +252\n" +
                "acc +5\n" +
                "acc +0\n" +
                "jmp +101\n" +
                "nop +219\n" +
                "acc +50\n" +
                "acc +40\n" +
                "jmp +49\n" +
                "nop +74\n" +
                "jmp +327\n" +
                "acc +47\n" +
                "jmp +206\n" +
                "acc -15\n" +
                "jmp +449\n" +
                "acc -17\n" +
                "acc -13\n" +
                "acc +46\n" +
                "jmp +417\n" +
                "jmp +160\n" +
                "acc -7\n" +
                "acc -11\n" +
                "acc +16\n" +
                "acc +14\n" +
                "jmp -37\n" +
                "acc -12\n" +
                "acc +15\n" +
                "acc -14\n" +
                "nop +110\n" +
                "jmp +1\n" +
                "acc -4\n" +
                "nop +287\n" +
                "nop -82\n" +
                "jmp +30\n" +
                "jmp +490\n" +
                "acc +34\n" +
                "jmp +305\n" +
                "nop +90\n" +
                "jmp +1\n" +
                "nop -4\n" +
                "nop -95\n" +
                "jmp -46\n" +
                "acc +26\n" +
                "acc +13\n" +
                "acc +47\n" +
                "jmp +350\n" +
                "acc +11\n" +
                "jmp -102\n" +
                "acc -2\n" +
                "jmp +489\n" +
                "acc +28\n" +
                "acc +24\n" +
                "nop +486\n" +
                "jmp +485\n" +
                "nop +170\n" +
                "jmp +66\n" +
                "jmp +411\n" +
                "acc +30\n" +
                "acc +48\n" +
                "acc +48\n" +
                "jmp -6\n" +
                "acc +11\n" +
                "jmp -51\n" +
                "jmp +1\n" +
                "jmp -10\n" +
                "nop +411\n" +
                "acc -17\n" +
                "acc +32\n" +
                "jmp +9\n" +
                "jmp +398\n" +
                "nop +82\n" +
                "jmp +6\n" +
                "acc +45\n" +
                "acc +34\n" +
                "jmp -44\n" +
                "acc -13\n" +
                "jmp -122\n" +
                "acc +25\n" +
                "nop +286\n" +
                "acc +5\n" +
                "jmp +144\n" +
                "acc +0\n" +
                "jmp -122\n" +
                "acc -11\n" +
                "acc -6\n" +
                "jmp -123\n" +
                "acc +16\n" +
                "acc +1\n" +
                "jmp -58\n" +
                "nop +242\n" +
                "acc -11\n" +
                "jmp +257\n" +
                "nop +231\n" +
                "acc +46\n" +
                "jmp +301\n" +
                "acc -6\n" +
                "acc +20\n" +
                "acc -7\n" +
                "jmp +365\n" +
                "acc +32\n" +
                "acc +0\n" +
                "jmp -66\n" +
                "jmp +110\n" +
                "acc -18\n" +
                "jmp +118\n" +
                "acc +33\n" +
                "nop -125\n" +
                "acc +49\n" +
                "acc +36\n" +
                "jmp +188\n" +
                "acc +9\n" +
                "acc -11\n" +
                "jmp +100\n" +
                "acc +35\n" +
                "jmp +55\n" +
                "acc +38\n" +
                "acc -1\n" +
                "jmp +312\n" +
                "jmp +157\n" +
                "acc +17\n" +
                "jmp +177\n" +
                "nop -126\n" +
                "acc +30\n" +
                "acc -3\n" +
                "jmp +211\n" +
                "acc -3\n" +
                "jmp -164\n" +
                "jmp -112\n" +
                "acc +50\n" +
                "jmp +268\n" +
                "nop +290\n" +
                "acc -8\n" +
                "acc +35\n" +
                "jmp -44\n" +
                "acc -6\n" +
                "acc +11\n" +
                "nop +327\n" +
                "jmp +155\n" +
                "acc +10\n" +
                "acc +35\n" +
                "nop +233\n" +
                "jmp +330\n" +
                "acc +31\n" +
                "acc +8\n" +
                "jmp +124\n" +
                "acc -5\n" +
                "jmp +300\n" +
                "nop +171\n" +
                "nop +4\n" +
                "acc +19\n" +
                "acc +41\n" +
                "jmp -156\n" +
                "nop +179\n" +
                "acc +12\n" +
                "jmp +160\n" +
                "jmp -92\n" +
                "acc -11\n" +
                "acc -10\n" +
                "jmp +95\n" +
                "nop +94\n" +
                "acc -8\n" +
                "jmp -199\n" +
                "acc +16\n" +
                "acc +30\n" +
                "nop +73\n" +
                "acc +36\n" +
                "jmp -53\n" +
                "jmp +1\n" +
                "jmp -6\n" +
                "nop +369\n" +
                "acc +29\n" +
                "acc +47\n" +
                "jmp +32\n" +
                "acc +35\n" +
                "jmp -61\n" +
                "acc +41\n" +
                "jmp +352\n" +
                "acc -1\n" +
                "jmp +75\n" +
                "acc -10\n" +
                "acc +28\n" +
                "acc -15\n" +
                "jmp -187\n" +
                "acc +6\n" +
                "jmp +1\n" +
                "nop +112\n" +
                "jmp +273\n" +
                "nop +186\n" +
                "acc +11\n" +
                "acc +40\n" +
                "jmp +128\n" +
                "acc +17\n" +
                "acc +23\n" +
                "acc -8\n" +
                "nop +277\n" +
                "jmp +42\n" +
                "acc +11\n" +
                "nop -237\n" +
                "acc +36\n" +
                "acc +32\n" +
                "jmp +287\n" +
                "acc +16\n" +
                "acc -19\n" +
                "jmp +115\n" +
                "acc -6\n" +
                "acc +16\n" +
                "nop -2\n" +
                "acc +23\n" +
                "jmp -160\n" +
                "acc -10\n" +
                "acc -10\n" +
                "jmp +26\n" +
                "acc -7\n" +
                "jmp -95\n" +
                "nop -160\n" +
                "acc -2\n" +
                "acc +44\n" +
                "jmp -236\n" +
                "jmp -198\n" +
                "jmp +1\n" +
                "acc +1\n" +
                "jmp -9\n" +
                "jmp -95\n" +
                "jmp +273\n" +
                "acc -19\n" +
                "jmp -46\n" +
                "acc +12\n" +
                "acc +2\n" +
                "jmp -145\n" +
                "acc -14\n" +
                "acc +3\n" +
                "acc +3\n" +
                "jmp +250\n" +
                "acc +4\n" +
                "acc +40\n" +
                "jmp +1\n" +
                "jmp +17\n" +
                "acc +6\n" +
                "acc +47\n" +
                "jmp -77\n" +
                "nop -192\n" +
                "acc +11\n" +
                "jmp +296\n" +
                "acc -14\n" +
                "jmp +64\n" +
                "acc +35\n" +
                "jmp +134\n" +
                "acc -8\n" +
                "nop +228\n" +
                "acc +24\n" +
                "acc +15\n" +
                "jmp -64\n" +
                "jmp -241\n" +
                "acc +19\n" +
                "acc +22\n" +
                "acc +49\n" +
                "nop -193\n" +
                "jmp +219\n" +
                "acc -1\n" +
                "acc -11\n" +
                "nop +211\n" +
                "acc +0\n" +
                "jmp -106\n" +
                "nop +101\n" +
                "jmp -222\n" +
                "acc +20\n" +
                "acc +45\n" +
                "jmp +70\n" +
                "acc +19\n" +
                "acc +21\n" +
                "jmp -23\n" +
                "acc +8\n" +
                "nop +92\n" +
                "acc +47\n" +
                "jmp -144\n" +
                "acc +0\n" +
                "acc -1\n" +
                "jmp -81\n" +
                "acc +23\n" +
                "jmp -274\n" +
                "acc +14\n" +
                "acc +26\n" +
                "acc +9\n" +
                "jmp +79\n" +
                "acc +22\n" +
                "jmp -331\n" +
                "acc -10\n" +
                "jmp -311\n" +
                "acc +16\n" +
                "acc +30\n" +
                "acc -8\n" +
                "jmp +176\n" +
                "acc -19\n" +
                "acc +43\n" +
                "jmp -222\n" +
                "nop -116\n" +
                "jmp +18\n" +
                "acc +26\n" +
                "acc +23\n" +
                "acc +6\n" +
                "jmp -162\n" +
                "acc +34\n" +
                "jmp +95\n" +
                "acc +27\n" +
                "acc +40\n" +
                "acc +9\n" +
                "jmp -77\n" +
                "jmp +137\n" +
                "acc -13\n" +
                "acc +21\n" +
                "acc +17\n" +
                "acc -5\n" +
                "jmp +91\n" +
                "jmp -95\n" +
                "acc +18\n" +
                "acc -1\n" +
                "jmp +70\n" +
                "jmp -355\n" +
                "nop -166\n" +
                "acc -19\n" +
                "acc +16\n" +
                "jmp -146\n" +
                "jmp -135\n" +
                "jmp +57\n" +
                "acc +45\n" +
                "jmp -62\n" +
                "acc -14\n" +
                "jmp -382\n" +
                "nop -172\n" +
                "acc +45\n" +
                "jmp -77\n" +
                "acc +13\n" +
                "jmp +65\n" +
                "acc -4\n" +
                "jmp +112\n" +
                "jmp +107\n" +
                "jmp +26\n" +
                "jmp -326\n" +
                "acc +25\n" +
                "jmp +1\n" +
                "jmp +179\n" +
                "acc +33\n" +
                "acc +2\n" +
                "jmp -222\n" +
                "nop +36\n" +
                "acc +25\n" +
                "nop -244\n" +
                "jmp -376\n" +
                "jmp -203\n" +
                "acc +26\n" +
                "nop +109\n" +
                "acc +38\n" +
                "jmp +135\n" +
                "acc +7\n" +
                "acc +40\n" +
                "acc -18\n" +
                "jmp -113\n" +
                "nop -294\n" +
                "acc +0\n" +
                "acc +40\n" +
                "nop -265\n" +
                "jmp +81\n" +
                "jmp -99\n" +
                "jmp +32\n" +
                "acc -17\n" +
                "acc +25\n" +
                "acc -12\n" +
                "acc +26\n" +
                "jmp -125\n" +
                "acc -3\n" +
                "acc -7\n" +
                "acc +25\n" +
                "jmp -410\n" +
                "acc +47\n" +
                "acc +36\n" +
                "jmp +35\n" +
                "acc +2\n" +
                "acc +18\n" +
                "acc -3\n" +
                "jmp -38\n" +
                "acc +29\n" +
                "acc +49\n" +
                "jmp -299\n" +
                "acc -4\n" +
                "nop -422\n" +
                "jmp +50\n" +
                "acc +11\n" +
                "acc +2\n" +
                "acc +49\n" +
                "jmp -233\n" +
                "acc +12\n" +
                "acc +43\n" +
                "acc -19\n" +
                "acc +11\n" +
                "jmp -264\n" +
                "jmp +124\n" +
                "jmp -361\n" +
                "acc +35\n" +
                "jmp -118\n" +
                "acc +23\n" +
                "acc -16\n" +
                "acc -14\n" +
                "jmp -22\n" +
                "jmp -135\n" +
                "jmp -309\n" +
                "acc +6\n" +
                "jmp -44\n" +
                "acc -12\n" +
                "acc +0\n" +
                "jmp -23\n" +
                "acc +29\n" +
                "acc -8\n" +
                "acc +18\n" +
                "acc +35\n" +
                "jmp -111\n" +
                "acc +22\n" +
                "acc +23\n" +
                "acc +0\n" +
                "acc -8\n" +
                "jmp -55\n" +
                "acc +14\n" +
                "jmp +1\n" +
                "acc +44\n" +
                "acc +17\n" +
                "jmp -272\n" +
                "acc +39\n" +
                "nop +37\n" +
                "acc -19\n" +
                "jmp -323\n" +
                "acc +24\n" +
                "acc +28\n" +
                "acc +29\n" +
                "acc +37\n" +
                "jmp +110\n" +
                "jmp -386\n" +
                "nop -352\n" +
                "acc +23\n" +
                "acc +38\n" +
                "jmp -369\n" +
                "acc -5\n" +
                "acc -14\n" +
                "jmp +83\n" +
                "jmp +17\n" +
                "jmp -151\n" +
                "jmp -118\n" +
                "jmp -104\n" +
                "jmp -341\n" +
                "acc +32\n" +
                "acc +43\n" +
                "jmp -52\n" +
                "acc -4\n" +
                "acc +42\n" +
                "acc +5\n" +
                "jmp -116\n" +
                "acc +13\n" +
                "jmp +1\n" +
                "nop -361\n" +
                "acc +41\n" +
                "jmp -386\n" +
                "jmp -241\n" +
                "nop -449\n" +
                "acc +46\n" +
                "jmp -176\n" +
                "acc +6\n" +
                "jmp +60\n" +
                "jmp +1\n" +
                "jmp -3\n" +
                "jmp -62\n" +
                "acc -14\n" +
                "acc +17\n" +
                "jmp -340\n" +
                "acc +31\n" +
                "acc -13\n" +
                "acc +7\n" +
                "jmp -54\n" +
                "jmp -80\n" +
                "acc +14\n" +
                "acc +49\n" +
                "acc +34\n" +
                "jmp +24\n" +
                "acc +11\n" +
                "jmp -158\n" +
                "acc -13\n" +
                "jmp -261\n" +
                "acc +33\n" +
                "nop -171\n" +
                "jmp -106\n" +
                "acc +0\n" +
                "acc +9\n" +
                "acc +16\n" +
                "acc +34\n" +
                "jmp +18\n" +
                "acc -2\n" +
                "acc +47\n" +
                "acc +39\n" +
                "jmp -232\n" +
                "acc +23\n" +
                "nop -229\n" +
                "acc +30\n" +
                "acc +32\n" +
                "jmp -147\n" +
                "acc -8\n" +
                "jmp -460\n" +
                "jmp -498\n" +
                "nop -218\n" +
                "acc +31\n" +
                "acc +44\n" +
                "acc +30\n" +
                "jmp -105\n" +
                "acc +8\n" +
                "acc -19\n" +
                "acc +45\n" +
                "nop -49\n" +
                "jmp -140\n" +
                "nop -43\n" +
                "acc +42\n" +
                "jmp +1\n" +
                "acc -14\n" +
                "jmp -42\n" +
                "jmp -389\n" +
                "acc +39\n" +
                "acc +26\n" +
                "acc +38\n" +
                "jmp -77\n" +
                "acc +48\n" +
                "jmp -83\n" +
                "acc +5\n" +
                "jmp -81\n" +
                "nop -242\n" +
                "acc +35\n" +
                "acc +0\n" +
                "acc +19\n" +
                "jmp -430\n" +
                "acc +11\n" +
                "nop -226\n" +
                "acc +13\n" +
                "acc +23\n" +
                "jmp -575\n" +
                "acc +44\n" +
                "acc +50\n" +
                "nop -303\n" +
                "jmp -112\n" +
                "jmp -305\n" +
                "acc +23\n" +
                "acc -11\n" +
                "nop -376\n" +
                "acc +50\n" +
                "jmp +1";
        return input;
    }

    public static void main(String[] args) {

        List<Triple<String, Integer, Boolean>> triples = createListOfTriples();
        runInstructions(triples);
    }

    private static List<Triple<String, Integer, Boolean>> createListOfTriples() {
        String ulaz = input();
        String[] strArray = ulaz.split("\n");
        List<String> instructions = Arrays.asList(strArray);
        int accCounter = 0;
        Pattern p = Pattern.compile("^(\\w+) ([+\\-])([0-9]*)");
        List<Triple<String, Integer, Boolean>> triples = new LinkedList<Triple<String, Integer, Boolean>>();

        //Create list of triplets
        for (int i = 0; i < instructions.size(); i++) {
            Matcher match = p.matcher(instructions.get(i));
            // pattern.matcher(input) always creates a new matcher, so you'd need to call matches() again.
            match.matches();
            String instructionType = match.group(1);
            String sign = match.group(2);
            String number = match.group(3);
            Integer fullNumber = Integer.parseInt(sign + number);

            Triple<String, Integer, Boolean> triple = new Triple<String, Integer, Boolean>(instructionType, fullNumber, false);
            //triple.printTriplet();
            triples.add(triple);
        }
        return triples;
    }

    private static void runInstructions(List<Triple<String, Integer, Boolean>> triples) {
//        System.out.println(triples.size());
        for(int i = 0; i < triples.size(); i++) {
            if(!triples.get(i).getType().equals("acc")) {
                System.out.println("Found jmp/nop " + i);
                //u pozivu ispod trebala duboka kopija!!!
                HaltedChecker<Boolean, Integer, Integer> result = checkIfHalted(createListOfTriples(), i);
                if(result.getHalted()) {

                    System.out.println("Found it " + result.getAccCounter());
                    break;
                }
                else System.out.println("Next");
            }
        }
    }

    private static HaltedChecker<Boolean, Integer, Integer> checkIfHalted(List<Triple<String, Integer, Boolean>> triples, int index) {
//        int len = triples.size();
        int i = 0;
        int acc = 0;
        for(i = 0; i < triples.size(); i++) {
//            Triple<String, Integer, Boolean> currentInstruction = new Triple<String, Integer, Boolean>(triples.get(i).getType(), triples.get(i).getNumber(), triples.get(i).getVisited());
            triples.get(i).printTriplet();
            if(triples.get(i).getVisited()) {
                System.out.println("visited");
                return new HaltedChecker<Boolean, Integer, Integer>(false, i, acc);

            } else {
                triples.get(i).setVisited(true);
            }
            if( i == index ) {
                System.out.println("i == index");
                if(triples.get(i).getType().equals("jmp")) {
                    System.out.println("Changing jmp to nop");
                    triples.get(i).setType("nop");
                } else if(triples.get(i).getType().equals("nop")) {
                    System.out.println("Changing nop to jmp");
                    triples.get(i).setType("jmp");
                }
                System.out.println(triples.get(i).getType());
            }

            if(triples.get(i).getType().equals("jmp")) {
                i += triples.get(i).getNumber() - 1;
                continue;
            } else if(triples.get(i).getType().equals("acc")) {
                acc += triples.get(i).getNumber();
            }
        }
        return new HaltedChecker<Boolean, Integer, Integer>(true, i, acc);
    }
}