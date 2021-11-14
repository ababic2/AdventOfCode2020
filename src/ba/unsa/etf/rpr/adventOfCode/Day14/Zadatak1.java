package ba.unsa.etf.rpr.adventOfCode.Day14;

import java.math.BigInteger;
import java.util.*;
public class Zadatak1 {

    public static class Triple {
        int number;
        int positionBefore;
        int positionCurrent;

        public Triple(int number, int positionBefore, int positionCurrent) {
            this.number = number;
            this.positionBefore = positionBefore;
            this.positionCurrent = positionCurrent;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getPositionBefore() {
            return positionBefore;
        }

        public void setPositionBefore(int positionBefore) {
            this.positionBefore = positionBefore;
        }

        public int getPositionCurrent() {
            return positionCurrent;
        }

        public void setPositionCurrent(int positionCurrent) {
            this.positionCurrent = positionCurrent;
        }

        public void printTriplet() {
            System.out.println(number + " " + positionBefore + " " + positionCurrent);
        }

        @Override
        public boolean equals(Object obj) {
            Triple t = (Triple)(obj);
            return this.number == t.getNumber();
        }
    }

    static String input() {
        return "13,16,0,12,15,1";
    }

    public static void main(String[] args) {
        String[] arg = input().split(",");
        List<Integer> input = new LinkedList<>();
        for (String s : arg) input.add(Integer.parseInt(s));

        List<Triple> triples = new LinkedList<>();

        // add init numbers
        for(int i = 0; i < input.size(); i++) {
            triples.add(new Triple(input.get(i), 0, i + 1));
//            triples.get(i).printTriplet();
        }

        int i = input.size() + 1;
        int next = 0;
        while(i <= 2020) {
            next = update(triples, next, i);
//            System.out.println("ITERACIJA ZA i = " + i);
//            triples.forEach(Triple::printTriplet);
            i++;
        }

        triples.get(triples.size() - 1).printTriplet();

//        triples.forEach(Triple::printTriplet);
    }

    private static int update(List<Triple> triples, int next, int index) {
        boolean found = false;
        for(int i = 0; i < triples.size(); i++) {
            if(triples.get(i).getNumber() == next) {
                found = true;
                triples.get(i).setPositionBefore(triples.get(i).getPositionCurrent());
                triples.get(i).setPositionCurrent(index);
                next = index - triples.get(i).getPositionBefore();
                break;
            }
        }
        if(!found) {
            triples.add(new Triple(next, 0, index));
            next = 0;
        }
        return next;
    }
}