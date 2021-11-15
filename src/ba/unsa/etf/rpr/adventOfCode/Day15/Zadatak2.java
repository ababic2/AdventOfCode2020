package ba.unsa.etf.rpr.adventOfCode.Day15;

import java.util.*;
public class Zadatak2 {

    public static class GameMap {
        private List<Integer> input;
        private int turn;
        private int lastNumber;
        // u mapi ce se uvijek update distinct elements i nece trebati lista
        private Map<Integer,Integer> memo;

        public int getLastNumber() {
            return lastNumber;
        }

        public GameMap(List<Integer> input) {
            memo = new HashMap<>();
            turn = 1;

            for (Integer integer : input) {
                memo.put(integer, turn);
                lastNumber = integer;
                turn++;
            }
        }

        public void letsPlay(int number) {
            while(this.turn <= number) {
                int prevoiusTurn = turn - 1;
                int newNumber;
                if(this.memo.containsKey(this.lastNumber)) {
                    newNumber = prevoiusTurn - this.memo.get(this.lastNumber);
                } else {
                    newNumber = 0;
                }
                this.memo.put(this.lastNumber, this.turn-1);
                this.lastNumber = newNumber;
                this.turn++;
            }
        }
    }

    static String input() {
        return "13,16,0,12,15,1";
    }

    public static void main(String[] args) {
        String[] arg = input().split(",");
        List<Integer> input = new LinkedList<>();
        for (String s : arg) input.add(Integer.parseInt(s));

        GameMap gameMap = new GameMap(input);
        gameMap.letsPlay(30000000);
        System.out.println(gameMap.getLastNumber());
    }
}