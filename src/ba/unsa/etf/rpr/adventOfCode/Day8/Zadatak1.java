package ba.unsa.etf.rpr.adventOfCode.Day8;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//DAY 2 ZADATAK 1
public class Zadatak1 {
    public static class Triple<L,R, K> {
        private L type;
        private R number;
        private K visited;

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

    static String input(){
        String input = "nop +0\n" +
                "acc +1\n" +
                "jmp +4\n" +
                "acc +3\n" +
                "jmp -3\n" +
                "acc -99\n" +
                "acc +1\n" +
                "jmp -4\n" +
                "acc +6";
        return input;
    }

    public static void main(String[] args) {
        String ulaz = input();
        String[] strArray = ulaz.split("\n");
        List<String> instructions = Arrays.asList(strArray);
        int accCounter = 0;
        Pattern p = Pattern.compile("^(\\w+) ([+\\-])([0-9]*)");

        List<Triple<String, Integer, Boolean>> triples = new LinkedList<Triple<String, Integer, Boolean>>();

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

        for (int i = 0; i < triples.size(); i++) {
            if(triples.get(i).getVisited()) break;
            triples.get(i).setVisited(true);
            if (triples.get(i).getType().equals("acc")) {
                accCounter += triples.get(i).getNumber();
            } else if (triples.get(i).getType().equals("jmp")) {
                i += triples.get(i).getNumber() - 1;
            }
        }
        System.out.println(accCounter);
    }
}