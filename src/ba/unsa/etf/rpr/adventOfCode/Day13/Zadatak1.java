package ba.unsa.etf.rpr.adventOfCode.Day13;

import java.math.BigInteger;
import java.util.*;
public class Zadatak1 {

    public static class BusLine {
        int id;
        int previous;
        int next;

        public BusLine(int id, int previous, int next) {
            this.id = id;
            this.previous = previous;
            this.next = next;
        }

        public int getId() {
            return id;
        }

        public int getPrevious() {
            return previous;
        }

        public int getNext() {
            return next;
        }

        public void printBusLine() {
            System.out.println("id:" + id + " previous: " + previous + " next: " + next);
        }
    }

    static String input() {
        return "1006605\n" +
                "19,x,x,x,x,x,x,x,x,x,x,x,x,37,x,x,x,x,x,883,x,x,x,x,x,x,x,23,x,x,x,x,13,x,x,x,17,x,x,x,x,x,x,x,x,x,x,x,x,x,797,x,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,29";
    }

    public static void main(String[] args) {
        String[] arg = input().split("\n");
        String[] IDs = arg[1].split(",");
        Integer timestamp = Integer.parseInt(arg[0]);
        List<BusLine> busLines = new LinkedList<>();
        for (int i = 0; i < IDs.length; i++) {
            if (!IDs[i].equals("x")) {
                Integer id = Integer.parseInt(IDs[i]);
                busLines.add(new BusLine(id, (timestamp%id), (id - timestamp%id)));
            }
        }
        busLines.sort(Comparator.comparing(BusLine::getNext));
        busLines.get(0).printBusLine();
        System.out.println(busLines.get(0).getNext() * busLines.get(0).getId());
    }
}