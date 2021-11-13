package ba.unsa.etf.rpr.adventOfCode.Day12;

import java.util.*;
import java.lang.*;

import static java.lang.Math.abs;

public class Zadatak1_first_approach {

    public static class Side {
        private int id;
        private Character character;
        private int counter;
        private Character complement;

        public Side(int id, java.lang.Character character, int counter, Character complement) {
            this.id = id;
            this.character = character;
            this.counter = counter;
            this.complement = complement;
        }

        public int getId() {
            return id;
        }


        public java.lang.Character getCharacter() {
            return character;
        }

        public int getCounter() {
            return counter;
        }

        public void setCounter(int counter) {
            this.counter = counter;
        }

        public Character getComplement() {
            return complement;
        }

        public void printSide() {
            System.out.println(id + " " + character + " "+ counter);
        }
    }

    static String input() {
        return "F10\n" +
                "N3\n" +
                "F7\n" +
                "R90\n" +
                "F11";
    }

    public static void main(String[] args) {
        String[] arg = input().split("\n");
        List<String> input = (Arrays.asList(arg));

        Side east =  new Side(0,'E', 0, 'W');
        Side south =  new Side(1,'S', 0, 'N');
        Side west =  new Side(2,'W', 0, 'E');
        Side north =  new Side(3,'N', 0, 'S');
        List<Side> sides = new LinkedList<>();
        sides.add(east); //0
        sides.add(south); //1
        sides.add(west);  //2
        sides.add(north); //3
        Side facing = east;

        for(int i = 0; i < input.size(); i++) {
            Integer number = parseToNumber(input.get(i));
            //just move forward
            if(input.get(i).charAt(0) == 'F') {
                for(int p = 0; p < sides.size(); p++)
                    if(facing.getComplement() == sides.get(p).getCharacter()) {
                        update(facing, sides.get(p), number);
                    }
            }
            //update counters
            if(charIsWorldSide(input.get(i).charAt(0))) {
                char character = input.get(i).charAt(0);
                if(character == 'E') {
                    update(east, west, number);
                } else if(character == 'W') {
                    update(west, east, number);
                } else if(character == 'S') {
                    update(south, north, number);
                } else {
                    update(north, south, number);
                }
            }
            //update current facing
            if(input.get(i).charAt(0) == 'R') {
                int newId = ((facing.getId() + (number / 90)) + 4)%4;
                facing = sides.get(newId);
            } else if(input.get(i).charAt(0) == 'L') {
                int newId = ((facing.getId() - (number / 90)) + 4)%4;
                facing = sides.get(newId);
            }
        }

        int manhattanDistance = abs(east.getCounter() - west.getCounter()) + abs(south.getCounter() - north.getCounter());
        System.out.println(manhattanDistance);
    }

    private static void update(Side current, Side complement, Integer number) {
        if(complement.getCounter() != 0) {
            if (complement.getCounter() < number) {
                int difference = number - complement.getCounter();
                current.setCounter(current.getCounter() + difference);
                complement.setCounter(0);
            } else if (complement.getCounter() > number) {
                complement.setCounter(complement.getCounter() - number);
            }
        }else {
            current.setCounter(current.getCounter() + number);
        }
    }


    private static boolean charIsWorldSide(char character) {
        if(character == 'N' || character == 'S' || character == 'W' || character == 'E')
            return true;
        return false;
    }

    private static Integer parseToNumber(String s) {
        StringBuilder result = new StringBuilder();
        for(int i = 1; i < s.length(); i++)
            result.append(s.charAt(i));
        return Integer.parseInt(result.toString());
    }
}