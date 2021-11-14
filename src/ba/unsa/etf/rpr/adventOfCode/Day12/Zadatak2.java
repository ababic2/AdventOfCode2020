package ba.unsa.etf.rpr.adventOfCode.Day12;

import java.util.*;
import java.lang.*;

import static java.lang.Math.abs;

public class Zadatak2 {
    //Challenge 2 -> move vector(point) around the boat position
    // add to class coordinate, another two x,y for coordinating new point
    // znaci sad updates wayPoint osim u slucaju 'F' kada pomjeras boat za number * waypoint

    public static class Side {
        char character;
        int id;

        public Side(char character, int id) {
            this.character = character;
            this.id = id;
        }

        public void setCharacter(char character) {
            this.character = character;
        }

        public void setId(int id) {
            this.id = id;
        }

        public char getCharacter() {
            return character;
        }

        public int getId() {
            return id;
        }
    }


    public static class Coordinate {
        private int x;
        private int y;
        private int wpX;
        private int wpY;

        public Coordinate(int x, int y, int pointX, int pointY) {
            this.x = x;
            this.y = y;
            this.wpX = pointX;
            this.wpY = pointY;
        }

        public int getWpX() {
            return wpX;
        }

        public void setWpX(int wpX) {
            this.wpX = wpX;
        }

        public int getWpY() {
            return wpY;
        }

        public void setWpY(int wpY) {
            this.wpY = wpY;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int manhattanDistance() {
            return abs(x) + abs(y);
        }
    }

    static String input() {
        return "W4\n" +
                "N5\n" +
                "F23\n" +
                "E1\n" +
                "L90\n" +
                "N5\n" +
                "F62\n" +
                "W2\n" +
                "L90\n" +
                "F56\n" +
                "W1\n" +
                "L90\n" +
                "W1\n" +
                "S4\n" +
                "F69\n" +
                "R90\n" +
                "F40\n" +
                "R90\n" +
                "F86\n" +
                "S4\n" +
                "F94\n" +
                "N4\n" +
                "R180\n" +
                "F2\n" +
                "W4\n" +
                "R90\n" +
                "S5\n" +
                "R180\n" +
                "W3\n" +
                "S1\n" +
                "E2\n" +
                "F79\n" +
                "R90\n" +
                "S4\n" +
                "L90\n" +
                "N5\n" +
                "F6\n" +
                "L90\n" +
                "N2\n" +
                "F92\n" +
                "R90\n" +
                "W2\n" +
                "R90\n" +
                "F99\n" +
                "E2\n" +
                "F72\n" +
                "W4\n" +
                "S3\n" +
                "R90\n" +
                "W4\n" +
                "L90\n" +
                "W2\n" +
                "S3\n" +
                "F89\n" +
                "W2\n" +
                "N1\n" +
                "F27\n" +
                "L90\n" +
                "S1\n" +
                "R90\n" +
                "F14\n" +
                "N4\n" +
                "L90\n" +
                "N2\n" +
                "F23\n" +
                "W4\n" +
                "L90\n" +
                "F18\n" +
                "N1\n" +
                "R180\n" +
                "F92\n" +
                "N1\n" +
                "L90\n" +
                "F32\n" +
                "S1\n" +
                "L180\n" +
                "N4\n" +
                "W5\n" +
                "S1\n" +
                "E4\n" +
                "N2\n" +
                "R90\n" +
                "S2\n" +
                "R90\n" +
                "E1\n" +
                "F89\n" +
                "R90\n" +
                "S5\n" +
                "F5\n" +
                "F16\n" +
                "N3\n" +
                "F68\n" +
                "R90\n" +
                "N4\n" +
                "L180\n" +
                "F22\n" +
                "N2\n" +
                "R90\n" +
                "S5\n" +
                "R180\n" +
                "E3\n" +
                "F68\n" +
                "R90\n" +
                "F55\n" +
                "L90\n" +
                "F4\n" +
                "E4\n" +
                "F87\n" +
                "E1\n" +
                "L90\n" +
                "L180\n" +
                "S1\n" +
                "W5\n" +
                "S1\n" +
                "S5\n" +
                "W3\n" +
                "F67\n" +
                "N5\n" +
                "S3\n" +
                "F37\n" +
                "W2\n" +
                "F48\n" +
                "S2\n" +
                "L180\n" +
                "S3\n" +
                "R90\n" +
                "E5\n" +
                "L90\n" +
                "F3\n" +
                "R90\n" +
                "N3\n" +
                "W5\n" +
                "L90\n" +
                "N1\n" +
                "F85\n" +
                "R180\n" +
                "N5\n" +
                "R90\n" +
                "W5\n" +
                "S3\n" +
                "L180\n" +
                "E5\n" +
                "N3\n" +
                "F35\n" +
                "R180\n" +
                "W2\n" +
                "F54\n" +
                "N4\n" +
                "L180\n" +
                "F90\n" +
                "S3\n" +
                "E5\n" +
                "F34\n" +
                "W2\n" +
                "R270\n" +
                "F24\n" +
                "E2\n" +
                "F71\n" +
                "R90\n" +
                "F66\n" +
                "W4\n" +
                "S3\n" +
                "F47\n" +
                "S2\n" +
                "L270\n" +
                "F69\n" +
                "N5\n" +
                "F91\n" +
                "W5\n" +
                "L90\n" +
                "E1\n" +
                "S1\n" +
                "L270\n" +
                "F91\n" +
                "W3\n" +
                "N4\n" +
                "E1\n" +
                "F52\n" +
                "R90\n" +
                "N4\n" +
                "F17\n" +
                "N1\n" +
                "F27\n" +
                "R90\n" +
                "N4\n" +
                "W2\n" +
                "R90\n" +
                "W2\n" +
                "F84\n" +
                "W2\n" +
                "F13\n" +
                "R90\n" +
                "W4\n" +
                "N2\n" +
                "R90\n" +
                "W5\n" +
                "F52\n" +
                "L90\n" +
                "E3\n" +
                "F49\n" +
                "R90\n" +
                "W1\n" +
                "R90\n" +
                "F93\n" +
                "R90\n" +
                "E5\n" +
                "F34\n" +
                "L90\n" +
                "F72\n" +
                "S2\n" +
                "L90\n" +
                "E1\n" +
                "R90\n" +
                "F12\n" +
                "E2\n" +
                "N2\n" +
                "L90\n" +
                "F28\n" +
                "L180\n" +
                "E5\n" +
                "R180\n" +
                "E4\n" +
                "R180\n" +
                "F43\n" +
                "N3\n" +
                "F85\n" +
                "W4\n" +
                "S3\n" +
                "E5\n" +
                "R270\n" +
                "F45\n" +
                "W4\n" +
                "L90\n" +
                "N5\n" +
                "R180\n" +
                "N2\n" +
                "F51\n" +
                "E3\n" +
                "S5\n" +
                "W3\n" +
                "N5\n" +
                "R180\n" +
                "N3\n" +
                "F99\n" +
                "W3\n" +
                "F80\n" +
                "S5\n" +
                "E5\n" +
                "F56\n" +
                "W4\n" +
                "F54\n" +
                "W4\n" +
                "F90\n" +
                "S4\n" +
                "F85\n" +
                "W4\n" +
                "F49\n" +
                "W2\n" +
                "E5\n" +
                "R180\n" +
                "F75\n" +
                "W4\n" +
                "R90\n" +
                "N2\n" +
                "L90\n" +
                "S4\n" +
                "L270\n" +
                "S5\n" +
                "F77\n" +
                "S5\n" +
                "R90\n" +
                "S4\n" +
                "R90\n" +
                "N5\n" +
                "W4\n" +
                "F47\n" +
                "E5\n" +
                "S5\n" +
                "R90\n" +
                "S5\n" +
                "F30\n" +
                "S3\n" +
                "F25\n" +
                "W1\n" +
                "N5\n" +
                "L90\n" +
                "N3\n" +
                "F15\n" +
                "W1\n" +
                "N1\n" +
                "F47\n" +
                "W3\n" +
                "N4\n" +
                "W4\n" +
                "W3\n" +
                "F4\n" +
                "W2\n" +
                "L270\n" +
                "W1\n" +
                "N2\n" +
                "F84\n" +
                "R90\n" +
                "F6\n" +
                "R180\n" +
                "W3\n" +
                "R180\n" +
                "W4\n" +
                "S1\n" +
                "F92\n" +
                "E1\n" +
                "N1\n" +
                "W2\n" +
                "R180\n" +
                "N4\n" +
                "W4\n" +
                "F38\n" +
                "L90\n" +
                "W1\n" +
                "L90\n" +
                "E3\n" +
                "R180\n" +
                "W5\n" +
                "R180\n" +
                "F45\n" +
                "R90\n" +
                "E2\n" +
                "N3\n" +
                "L90\n" +
                "F35\n" +
                "R180\n" +
                "E4\n" +
                "N5\n" +
                "L90\n" +
                "F2\n" +
                "N1\n" +
                "R90\n" +
                "F20\n" +
                "L90\n" +
                "W3\n" +
                "F64\n" +
                "L90\n" +
                "F98\n" +
                "L90\n" +
                "N2\n" +
                "F36\n" +
                "R270\n" +
                "S1\n" +
                "R90\n" +
                "F100\n" +
                "E1\n" +
                "N1\n" +
                "L90\n" +
                "F46\n" +
                "N1\n" +
                "N4\n" +
                "E2\n" +
                "L270\n" +
                "N3\n" +
                "W1\n" +
                "F84\n" +
                "S3\n" +
                "F33\n" +
                "N5\n" +
                "E1\n" +
                "F37\n" +
                "N2\n" +
                "E3\n" +
                "N4\n" +
                "L270\n" +
                "F44\n" +
                "L180\n" +
                "F57\n" +
                "E4\n" +
                "N2\n" +
                "L90\n" +
                "N5\n" +
                "W1\n" +
                "L180\n" +
                "S3\n" +
                "L90\n" +
                "E3\n" +
                "L90\n" +
                "E2\n" +
                "L270\n" +
                "S3\n" +
                "F70\n" +
                "L90\n" +
                "E4\n" +
                "R90\n" +
                "E4\n" +
                "S3\n" +
                "F16\n" +
                "E3\n" +
                "N2\n" +
                "F51\n" +
                "R180\n" +
                "E4\n" +
                "N3\n" +
                "F52\n" +
                "R90\n" +
                "N2\n" +
                "R90\n" +
                "S3\n" +
                "L90\n" +
                "E2\n" +
                "F74\n" +
                "L90\n" +
                "W4\n" +
                "R180\n" +
                "W3\n" +
                "S3\n" +
                "F2\n" +
                "N4\n" +
                "L180\n" +
                "E3\n" +
                "F38\n" +
                "E3\n" +
                "F37\n" +
                "R90\n" +
                "F68\n" +
                "R180\n" +
                "F62\n" +
                "W4\n" +
                "N3\n" +
                "F70\n" +
                "E5\n" +
                "F50\n" +
                "N3\n" +
                "F6\n" +
                "R180\n" +
                "F25\n" +
                "N4\n" +
                "R90\n" +
                "F10\n" +
                "L90\n" +
                "F53\n" +
                "S1\n" +
                "F32\n" +
                "R90\n" +
                "F69\n" +
                "S2\n" +
                "W4\n" +
                "S5\n" +
                "R90\n" +
                "F10\n" +
                "R90\n" +
                "F39\n" +
                "W3\n" +
                "F55\n" +
                "E4\n" +
                "F16\n" +
                "W1\n" +
                "L90\n" +
                "E1\n" +
                "L270\n" +
                "N1\n" +
                "E4\n" +
                "F94\n" +
                "N2\n" +
                "W2\n" +
                "F23\n" +
                "N3\n" +
                "F51\n" +
                "L180\n" +
                "S1\n" +
                "F83\n" +
                "L90\n" +
                "N3\n" +
                "R90\n" +
                "F5\n" +
                "N1\n" +
                "L90\n" +
                "F80\n" +
                "E4\n" +
                "E1\n" +
                "F51\n" +
                "R180\n" +
                "F14\n" +
                "L90\n" +
                "F28\n" +
                "L90\n" +
                "W1\n" +
                "L180\n" +
                "N3\n" +
                "R90\n" +
                "E5\n" +
                "F21\n" +
                "R90\n" +
                "E5\n" +
                "F95\n" +
                "R180\n" +
                "S2\n" +
                "E1\n" +
                "F69\n" +
                "R90\n" +
                "S3\n" +
                "F83\n" +
                "W5\n" +
                "F7\n" +
                "S4\n" +
                "R90\n" +
                "F4\n" +
                "W4\n" +
                "L90\n" +
                "S5\n" +
                "F67\n" +
                "R90\n" +
                "W2\n" +
                "F59\n" +
                "N5\n" +
                "R90\n" +
                "F63\n" +
                "E3\n" +
                "L90\n" +
                "E1\n" +
                "S3\n" +
                "L90\n" +
                "E5\n" +
                "R90\n" +
                "F40\n" +
                "W2\n" +
                "L90\n" +
                "F86\n" +
                "E1\n" +
                "N1\n" +
                "E3\n" +
                "F25\n" +
                "L180\n" +
                "F3\n" +
                "R180\n" +
                "F47\n" +
                "S5\n" +
                "F94\n" +
                "L180\n" +
                "F10\n" +
                "S3\n" +
                "W2\n" +
                "F95\n" +
                "S3\n" +
                "L90\n" +
                "F38\n" +
                "N4\n" +
                "R90\n" +
                "F51\n" +
                "L90\n" +
                "F27\n" +
                "E1\n" +
                "F93\n" +
                "N1\n" +
                "F27\n" +
                "L180\n" +
                "E5\n" +
                "S1\n" +
                "E4\n" +
                "N3\n" +
                "L90\n" +
                "N3\n" +
                "W3\n" +
                "S1\n" +
                "L180\n" +
                "E2\n" +
                "E2\n" +
                "F34\n" +
                "E1\n" +
                "S4\n" +
                "E4\n" +
                "F77\n" +
                "F49\n" +
                "W4\n" +
                "N3\n" +
                "F46\n" +
                "L90\n" +
                "E1\n" +
                "F85\n" +
                "R180\n" +
                "S4\n" +
                "E3\n" +
                "R90\n" +
                "N1\n" +
                "R90\n" +
                "F8\n" +
                "E1\n" +
                "F40\n" +
                "R180\n" +
                "E5\n" +
                "F68\n" +
                "F15\n" +
                "R180\n" +
                "W5\n" +
                "F24\n" +
                "F30\n" +
                "L90\n" +
                "L90\n" +
                "F65\n" +
                "E5\n" +
                "L180\n" +
                "F44\n" +
                "L90\n" +
                "W2\n" +
                "F28\n" +
                "E2\n" +
                "L180\n" +
                "S4\n" +
                "F91\n" +
                "L90\n" +
                "F41\n" +
                "E3\n" +
                "F100\n" +
                "R90\n" +
                "W2\n" +
                "S2\n" +
                "F87\n" +
                "R90\n" +
                "W5\n" +
                "F43\n" +
                "W3\n" +
                "S3\n" +
                "F53\n" +
                "S3\n" +
                "F29\n" +
                "E3\n" +
                "F83\n" +
                "L90\n" +
                "F85\n" +
                "N1\n" +
                "E3\n" +
                "L90\n" +
                "E2\n" +
                "L180\n" +
                "E5\n" +
                "S2\n" +
                "N1\n" +
                "R90\n" +
                "F67\n" +
                "E5\n" +
                "R180\n" +
                "F88\n" +
                "S4\n" +
                "R90\n" +
                "E5\n" +
                "F72\n" +
                "W4\n" +
                "N2\n" +
                "R90\n" +
                "N2\n" +
                "L90\n" +
                "S5\n" +
                "F2\n" +
                "R180\n" +
                "E5\n" +
                "R180\n" +
                "F92\n" +
                "E2\n" +
                "F90\n" +
                "L180\n" +
                "E2\n" +
                "L90\n" +
                "W4\n" +
                "S4\n" +
                "R90\n" +
                "S4\n" +
                "L90\n" +
                "S4\n" +
                "L180\n" +
                "F54\n" +
                "E5\n" +
                "L270\n" +
                "F80\n" +
                "E5\n" +
                "N3\n" +
                "F84\n" +
                "S4\n" +
                "F13\n" +
                "S3\n" +
                "W4\n" +
                "F90\n" +
                "W3\n" +
                "N3\n" +
                "F65\n" +
                "E4\n" +
                "F33\n" +
                "L90\n" +
                "W4\n" +
                "F97\n" +
                "N1\n" +
                "R90\n" +
                "S3\n" +
                "L90\n" +
                "F71\n" +
                "R90\n" +
                "L90\n" +
                "F99\n" +
                "E2\n" +
                "R90\n" +
                "F76\n" +
                "N3\n" +
                "R90\n" +
                "N3\n" +
                "F49\n" +
                "R180\n" +
                "N5\n" +
                "N3\n" +
                "W4\n" +
                "F24\n" +
                "W1\n" +
                "F79\n" +
                "L90\n" +
                "F59\n" +
                "R90\n" +
                "F73\n" +
                "R180\n" +
                "F53\n" +
                "S5\n" +
                "F72\n" +
                "E5\n" +
                "F40\n" +
                "E2\n" +
                "F28\n" +
                "W1\n" +
                "F96\n" +
                "N2\n" +
                "R90\n" +
                "W5\n" +
                "N3\n" +
                "E1\n" +
                "N1\n" +
                "L90\n" +
                "E1\n" +
                "F85\n" +
                "L90\n" +
                "F45\n" +
                "S4\n" +
                "W2\n" +
                "F77\n" +
                "N4\n" +
                "R270\n" +
                "E1\n" +
                "R90\n" +
                "E1\n" +
                "F32\n" +
                "S5\n" +
                "F93\n" +
                "W4\n" +
                "F38\n" +
                "N1\n" +
                "W2\n" +
                "R180\n" +
                "S2\n" +
                "F44\n" +
                "L90\n" +
                "S1\n" +
                "E1\n" +
                "S2\n" +
                "E5\n" +
                "N4\n" +
                "E2\n" +
                "S4\n" +
                "W4\n" +
                "F27";
    }

    public static void main(String[] args) {
        String[] arg = input().split("\n");
        List<String> input = (Arrays.asList(arg));

        List<Side> sides = new LinkedList<>();
        sides.add(new Side('E', 0));
        sides.add(new Side('S', 1));
        sides.add(new Side('W', 2));
        sides.add(new Side('N', 3));

        Coordinate coordinate = new Coordinate(0,0,10,1); //start at id=0 -> east

        for (int i = 0; i < input.size(); i++) {
            Integer number = parseToNumber(input.get(i));
            updateXY(coordinate, sides, number, input.get(i).charAt(0));
        }

        System.out.println(coordinate.manhattanDistance());

    }

    private static void updateXY(Coordinate coordinate, List<Side> sides, Integer number, char character) {
        switch (character) {
            case 'N':
                coordinate.setWpY(coordinate.getWpY() + number);
                break;
            case 'S':
                coordinate.setWpY(coordinate.getWpY() - number);
                break;
            case 'E':
                coordinate.setWpX(coordinate.getWpX() + number);
                break;
            case 'W':
                coordinate.setWpX(coordinate.getWpX() - number);
                break;
            case 'F':
                // move boat forwars for number * waypoint
                coordinate.setX(coordinate.getX() +  coordinate.getWpX() * number);
                coordinate.setY(coordinate.getY() +  coordinate.getWpY() * number);
                break;
            case 'L':
                //rotate waypoint
                // FORMULA: https://en.wikipedia.org/wiki/Rotation_matrix

                // cos and sin accepts angle in radian, so convert it
                double angle = Math.toRadians(number);
                int a = (int) Math.round(coordinate.getWpX()*Math.cos(angle) - coordinate.getWpY()*Math.sin(angle));
                int b = (int) Math.round(coordinate.getWpX()*Math.sin(angle) + coordinate.getWpY()*Math.cos(angle));
                coordinate.setWpX(a);
                coordinate.setWpY(b);
                break;
            case 'R':
                //rotate waypoint
                double angleR = Math.toRadians(-number);
                int aR = (int) Math.round(coordinate.getWpX()*Math.cos(angleR) - coordinate.getWpY()*Math.sin(angleR));
                int bR = (int) Math.round(coordinate.getWpX()*Math.sin(angleR) + coordinate.getWpY()*Math.cos(angleR));
                coordinate.setWpX(aR);
                coordinate.setWpY(bR);
                break;
            default:
                System.out.println("Default in updateXY");
                break;
        }

    }

    private static Integer parseToNumber(String s) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < s.length(); i++)
            result.append(s.charAt(i));
        return Integer.parseInt(result.toString());
    }
}