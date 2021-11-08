package ba.unsa.etf.rpr.adventOfCode.Day10;

import java.util.*;
import java.util.stream.Collectors;


public class Zadatak1 {
    public static class Pair<L,R> {
        private L element1;
        private R element2;

        public Pair() {
        }

        public Pair(L type, R number) {
            this.element1 = type;
            this.element2 = number;
        }

        public L getElement1() {
            return element1;
        }

        public void setElement1(L element1) {
            this.element1 = element1;
        }

        public R getElement2() {
            return element2;
        }

        public void setElement2(R element2) {
            this.element2 = element2;
        }
    }

    static String input() {
        return "16\n" +
                "10\n" +
                "15\n" +
                "5\n" +
                "1\n" +
                "11\n" +
                "7\n" +
                "19\n" +
                "6\n" +
                "12\n" +
                "4";
    }

    public static void main(String[] args) {
        String ulaz = input();
        String[] arg = input().split("\n");

        List<Integer> input = new LinkedList<>();
        for(int i = 0; i < arg.length; i++)
            input.add(Integer.parseInt(arg[i]));

        input = input.stream().sorted().collect(Collectors.toList());

        LinkedList<Integer> joltage = new LinkedList<Integer>();

        Integer next = 0;
        for(int i = 0; i < input.size(); i++) {
            System.out.println("Next " + next);
            Pair<Integer, Integer> pair = new Pair<>();
            pair = findNext(next, input);
            System.out.println(pair.getElement1() + " " + pair.getElement2());
            joltage.add(pair.getElement2());
            next = pair.getElement1();
        }

//        your device's built-in joltage adapter would be rated for 19 + 3 = 22 jolts, 3 higher than the highest-rated adapter.
        deviceBuiltInJoltage(input, joltage);
        for (Integer integer : joltage) System.out.println(integer);
        mapJoltageAndRepeats(joltage);
        System.out.println("DONE");

    }

    private static void mapJoltageAndRepeats(LinkedList<Integer> joltage) {
        Pair<Integer, Integer> pair1 = new Pair<>(1, 0);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 0);
        Pair<Integer, Integer> pair3 = new Pair<>(3, 0);
        for(int i = 0; i < joltage.size(); i++) {
            if(joltage.get(i) == 1) {
                int count = pair1.getElement2();
                count++;
                pair1.setElement2(count);
            }
            else if(joltage.get(i) == 2) {
                int count = pair2.getElement2();
                count++;
                pair2.setElement2(count);
            }
            else if(joltage.get(i) == 3) {
                int count = pair3.getElement2();
                count++;
                pair3.setElement2(count);
            }
        }
        System.out.println("1: " + pair1.getElement2());
        System.out.println("2: " + pair2.getElement2());
        System.out.println("3: " + pair3.getElement2());
    }

    private static Integer deviceBuiltInJoltage(List<Integer> input, LinkedList<Integer> joltage) {
        Integer max = input
                .stream()
                .mapToInt(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        joltage.add(3);
        return max + 3;
    }

    private static Pair<Integer, Integer> findNext(Integer next, List<Integer> input) {
        if(input.contains(next + 1)) {
            System.out.println("In 1 " + next);
//            input.remove(next + 1);
            return new Pair<Integer,Integer>(next + 1, 1);
        } else if(input.contains(next + 2)) {
            System.out.println("In 2 " + next);
//            input.remove(next + 2);
            return new Pair<Integer,Integer>(next + 2, 2);
        } else if(input.contains(next + 3)) {
            System.out.println("In 3 " + next);
//            input.remove(next + 3);
            return new Pair<Integer,Integer>(next + 3, 3);
        }
        return new Pair<Integer,Integer>();
    }

}