package ba.unsa.etf.rpr.adventOfCode.Day11;


import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Zadatak2 {
    public static class Coordinate {
        private int x;
        private int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
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
    }
    public static class Seats {
        private List<String> seats;

        public Seats(List<String> seats) {
            this.seats = seats;
        }

        public boolean goToNextState() {
            boolean hasChanged = false;
            List<String> newSeats = new LinkedList<>();
            List<Coordinate> directions = new ArrayList<>(createListOfDirections());

            for(int i = 0; i < this.seats.size(); i++) { //prolaz kroz redove
                StringBuilder currentSeatRow = new StringBuilder(this.seats.get(i));
                /////// prolaz kroz seat line////////////////
                for(int j = 0; j < currentSeatRow.length(); j++) { //prolaz kroz kolone reda
                    int occupied = 0;

                    //count occupied around
                    for(Coordinate coordinate : directions) {
                        int posX = j + coordinate.x;
                        int posY = i + coordinate.y;
                        while(posX >= 0 && posY >= 0 && posX < this.seats.get(0).length() && posY < this.seats.size()) {
                            if(this.seats.get(posY).charAt(posX) == '#') {
                                occupied++;
                                break; //change direction
                            }
                            if(this.seats.get(posY).charAt(posX) == 'L') {
                                break;
                            }
                            posX += coordinate.x;
                            posY += coordinate.y;
                        }
                    }

                    if(currentSeatRow.charAt(j) == 'L' && occupied == 0) {
                        //If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.
                        currentSeatRow.setCharAt(j, '#');
                        hasChanged = true;
                    } else if(currentSeatRow.charAt(j) == '#' && occupied >= 5) {
                        //If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.
                        currentSeatRow.setCharAt(j, 'L');
                        hasChanged = true;
                    }
                }
                /////////////////////////////////////////
                //updatean seatLine dodaj u update seats
                newSeats.add(String.valueOf(currentSeatRow));
            }
            this.seats = new LinkedList<>(newSeats);
            return hasChanged;
        }

        private List<Coordinate> createListOfDirections() {
            return Stream.of(new Coordinate(1,0),
                    new Coordinate(-1,0),
                    new Coordinate(1,1),
                    new Coordinate(-1,-1),
                    new Coordinate(1,-1),
                    new Coordinate(-1,1),
                    new Coordinate(0,1),
                    new Coordinate(0,-1)).collect(Collectors.toList());
        }

        public Integer getOccupiedSeats() {
            int occupied = 0;
            for(int i = 0; i < this.seats.size(); i++)
                for(int j = 0; j < this.seats.get(i).length(); j++)
                    if(this.seats.get(i).charAt(j) == '#')
                        occupied++;

            return occupied;
        }

        public void printSeats() {
            for(String seat : this.seats) System.out.println(seat);
        }
    }

    static String input() {
        return "LLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLL.LLLLLLLLLL.LLLLLLLLLLL.LLLLLLLLLLLLLL.LLLL.LLLL.LLLLLL\n" +
                "LLLLL.LLLLLLLLLLLLLLLL.LLLLL.LLL.LLLLLLL.LLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLL.LLLL.LLLL.LLLL.LLLLLL\n" +
                "LLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLL.LLLLLLL.LLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLLLLLL.LLLLLLLLLLL\n" +
                "LLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLL.LLLLLLL.LLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLL.LLLL.LLLLLL\n" +
                "LLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLL.LLLLLLL.LLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLLLLLLL.LLLL.LLLL.LLLLLL\n" +
                "LLLLL.LLLL.LLLLLLLLLLL.LLLLLLLLL.LLLLLLL.LLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLL.LLLL.LLLLLL\n" +
                "LLLLLLLLLL.LLLLLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLL.LLLL.LLLL.LLLL.LLLLLL\n" +
                "LLLLLLLLLL.LLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLL.LLLL.LLLL.LLLL.LLLLLL\n" +
                "L.L...L...LL.LL.......LL...LL.L...LL..LL..L.......LLLLL.....LL..LLLL.L....L..L...L.LL....LL...L\n" +
                "LLLLL.LLLLL.LLLLLLLLLL.LLLLLLLLL.LLLLLLL.LLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLL.LLLL.LLLL.LLLL.LLLLLL\n" +
                "LLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLLLLLL.LLLLLLLLLLLLL.LL.LLLLLLLLL.LLLL.LLLL.LLLLLLLLLLL\n" +
                "LLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLL.LL.LLLLLLLLLL.LLLL.LLLLLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLLL.LLLLLL\n" +
                "LLLLLLLLLL.LLLLLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLL.LLLLLLLLL.LLL.LL.LLLLLLLLL.LLLL.LLLL.LLLL.L.LLLL\n" +
                "LLLLL.LLLL.LLLL.LLL.LLLLLLLLLLLLLLLLLLLL.LLLLLLLLLL.LLLLLLLLLLL.LLLLLLLLL.LLLL.LLLL.LLLLLLLLLLL\n" +
                "LLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLL.LLLL.LLL.LL.LLLLLLLLLLLLLLLLLLL.LLLL.LLLLLL\n" +
                ".L.....L.LL..LLL.L..L...L.LLL.L...L.L.L.L.....L..L.......L.LLL...L.......L.LLLL......L.L.L...LL\n" +
                "LLLLLLLLLL.LLLL.LLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLL..LLLLLL.LLLL.LLLL.L.LL.LLLLLLLLL.LLLLLL\n" +
                "LLLLLLLLLL.LLLL.LLLLLL.LLLLLLLLL.LLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLLLLLLLL\n" +
                "LLLLL.LLLL.LLLLLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLL.LL.LLLLLLLLLLLLL.LLLLLLLLLLLLLL.LLLL.LLLL.LLLLLL\n" +
                "LLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLLLLLLLLLL.LLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLLLLLLL.LLLL.LLLL.LLLLLL\n" +
                ".L..LLL.L...LL.....LL......LL...L...LL...L.L..L....L.L.L.LL.L........L....LL......L..LL..LL....\n" +
                "LLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLL.LLLLLLL.LLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLLLLLLL.LLLL.LLLLLLLLLLL\n" +
                "LLLLLLLLLL.LLLLLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLLLL.LLLLLLLLL.LLLL.LLLLLL\n" +
                "LLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLL.LLLLL.LLL..LLLL.LLLLLL.LLLLLLLLLLL.LL.LLLL.LLLLLLLLLLL\n" +
                "LLLLL.LLLL.LLLLLLLLLLLLLLLLLLLLL.LLLLLLL.LLLLL.LLLL.LLLL..L.LLL.LLLLLLLLL.LLLL.LLLLLLLLLLLLLLLL\n" +
                "LLLLLLLLLL.LLLL.LLLLLL.LLLLLL.LLLLLLLLLL.LLLLL.LLLL.LLLLLLLLLLL.LLLLLLLLLLLLLL.LLLL.LLLLLLLLLLL\n" +
                "L.LLL.LLLLLLLLL.LLLLLL.LLLLLLLLL.LLLLLLL.LLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLLLLL\n" +
                "LLLL....L.......L..LLL...........L..L...LL..L.L.LLL...L.....LL..LL..L....L....L..LL..LL.L....L.\n" +
                "LLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLL.LLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLL.L.LLLLLLLLLLLL.LLLLLL\n" +
                "LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLL.LLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLLLLLLLLLLLL.LLLL.LLLLLL\n" +
                "LLLLLLLLLLLLLLL.LLLLLL.LLLLLLLLL.LLLLLLL.LLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLL.LLLL.LLLLL.LLLLLLLLLL\n" +
                "LLLLLLLLLL.LLLL.LLLLLL.LLLLLLLLL.LLLLLLLLLLLLL.LLLL.LLLL.LLLLLL..LLL.LLLL.LLLL.LLLL.LLLLLLLLLLL\n" +
                "LLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLL.LLL.L.LLLL.LL.L.LL.LLLLLLLLLLLLL.LLLL.LLLL.LLLL.LLLLLL\n" +
                ".LL........L.....L.L.L.......LL.L.L.......LLL.........L....LL........L.L..L......L.LL......L..L\n" +
                "LLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLL.LLLL.LLLL.LLLL.LLLLLL\n" +
                "LLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLL.LLLLLLLLLLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLLLLLLL.L.LLLLLLLLLLLLLL\n" +
                "LLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLL.LLLLLLLLLLLL.LLLLLLLLLLLLL.LLLLLLLLLLLLLLLL\n" +
                "LLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.L.LLLL.LLLLLL.LLLLLLLLL.LLLL.LLLLLLLLL.LLLLLL\n" +
                "LLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLL..LLLLLL.LLLLLLLLL.LLLLLLLLL.LLLL.LLLLLL\n" +
                "LLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLL.LL.L.LLLLLLLLLLLLLLLL.LLLL.LLLLLLLLL.LLLLLL\n" +
                ".L..L...L........L.......L.L.LL.LLLL...LLL.L.L..L.L....L.................L.L.L.L....L...L...L..\n" +
                "LLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLL.LLLLLL.LLLLLLLLLLLLLL.LLLL.LLLL.LLLLLL\n" +
                "LLLLLLLLLL.LLLL.LLLLLL.LLLLLLLLLLLLLLLLLLLLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLLLLLLL.LLLL.LLLL.LLLLLL\n" +
                "LLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLL.LLLLLLL.LLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLLLLLLL.LLLL.LLLL.LLLLLL\n" +
                "LLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLL.LLLLLLL.LLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLL.LLLL.LLLL.LLLLLL.LLLL\n" +
                "LLLLL.LL.LLLLLLL.LLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLL.LLLLLLLLLLLLLLLL.LLLL.LLLLLLLLL.LLLLLL\n" +
                "LLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLL.LLLLLLLLLLLLL.LLLL.LLLLLLLLLLL.LLLLLLLLLLLLLL.LLLL.LLLL.LLLLLL\n" +
                "LLLL...LLL.......LL..L.L.L.L...L........LL..............L.L......L.......L..LL....L....LL...LL.\n" +
                "LLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLL.LLLLLL.L.LLLLLLL.LLLLLLLLL.LLLLLLLLLLL\n" +
                "LLLLL.LLLL.LLLL.LLLLLL..LLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLL.LLLLLL.LLLLLLLLL.LLLLLLL.L.LLLL.LLLLLL\n" +
                "LLLLL.LLLL.LLLLLLLLLLL.LLLLLLLLL.LLLLLLL.LLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLLLLLLL.LLLL.LLLLLLLLLLL\n" +
                "LLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLLLLLLLLLL.LLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLLLLLLL.LLLLLLLLL.LLLLLL\n" +
                "LLLLLLLLLL.LLLL.LLLLLL.LLLLLLLLL.LLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLLLLLLLLLLLL.LLLL.LLLLLL\n" +
                "LLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLLLLLLLLLL.LLLLL.LLLL.L.LL.LLLLLL.LLLLLLLLLLL.LL.LLLLLLLLL.LLL.LL\n" +
                "LLLLL.LLLL.LLLLLLLLLLLLLLLLLLLLL.LLLLLLL.LLLLLLLLLL.LLLLLLLLLLL.LLLLLLLLL.LLLL.LLLLLLLLLLLLLLLL\n" +
                "LLLLLLLLLL.LLLL.LLLLLL.LLLLLLLLLLLLLLLLLLLLLLL.LLLL.LLLLLLLLLLL.LLLLLLLLL.LLLL.LLLL.LLLL.LLLLLL\n" +
                "LL.LL.LLLLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLL.LLLLLLLLL.LLLL.LLLLLL\n" +
                "LLLL.L...LL.......LL..L.....L.LL..L....L.L..L.......L..L......LLLLL..L.L..L......L...L.L...L.L.\n" +
                "LLLLL.LLL..LLLL.LLLLLL.LLLLL.LLL.LLLLL.L.LLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLLLLLLL.LLLLLLLL..LLLLLL\n" +
                "LLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLL.LLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLL.LLLLLLLLL.LLLL.LLLLLL\n" +
                "LLLLL.LLLL.LLLLLLLLLLL.LLLLLLLLL.LLLLLL.LLLLLL.LLLL.LLLLLLLLLLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLLLLL\n" +
                "LLLLLLLLLL.LLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLLLLLLL.LLLL.LLLL.LLLLLL\n" +
                "LLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLL.LLLLL.LLLL.LLLLLLLLLLLL.LLLLLLLL.LLLL.LLLL.LLLL.LLLLLL\n" +
                "L.L...............L..L.LLLL...L..L...LLL.......LLL.LL........L..LL..L..L...L.L.L.LL..LLLL.L.LL.\n" +
                "LLLLLLLL.L.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLL.LLLL.LLLLLLLLL.LLLLLL\n" +
                "LLLLL.LLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLLLL.LLLLLLLLLLL\n" +
                "LLLLLLLLLLLLLLL.LLLLLL.LLLLLLLLL.LLLLLLL.LLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLL.LLLL.LLLLLLLLLLL\n" +
                "LLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLL.LLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLL.LLLLLL\n" +
                "LLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLL.LLLLLLL.LLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLL.LLLL.LLLLLLLLLLLLLLLL\n" +
                "L.LLL.LLLL.LLLLLLLLLLLLLLLLLLLLL.LLLLLLL.LLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLLLLL\n" +
                "LLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLL.LLLLLLL.LLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLL.LLLL.LLLLLL\n" +
                "LLLLL.LLLLLLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLLLLLLL.LLLLLLL.LLLL.LLLL.LLLLLL\n" +
                ".L..LL.L.L..L...L....L......LLL......L.LL..L....L.LLLL.LL.....L.L.LL.L.....L......L.LL.........\n" +
                "LLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLL.LLLL..LLLL.LLLL.LLLLLL.LLLLLLLLL.LLLL.LLLLLLLLL.LLLLLL\n" +
                "LLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLL.LLLLLLL.LLLLL.LLLL.LLLLLLLLLLL.LLLLLLLLLLLLLL.LLLLLLLLL.LLLLLL\n" +
                "LLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLL.LLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLL\n" +
                "LLLLL.LLL..LLLL.LLLLLL.LLLLLLLLL.LLLLLLL.LLLLL.LLLL.LLLLLLLLLLL.LLLLLLLLL..LLL.LLLLLLLLLLLLLLLL\n" +
                ".LLL......L.L.L......L.....LL......L.LLL.LLL..LL...L.L.......L..L.......L....L.....L.......LL..\n" +
                "LLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLL.LLLLLLL.LLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLL.LLLL.LLLLLLLLL.LLLLLL\n" +
                "LLLLL.LLLL.LLLLLLLLLLLLLLLLLLLLL.LLLLLLL.LLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLL.LLLL.LLLL.LLLL.LLLLLL\n" +
                "LLLLLLLLLL.LLLL.LLLLLL.LLLLLLLLL.LLLLLLL.LLLLLLLLLLLLLLL.LLLLLL.LLLLLLLLL.LLLL.LLLL.LLLL.LLLLLL\n" +
                "LLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLL.LLLLLLLLLLLLLL.LLLLLL\n" +
                "LLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLL.LLLL.LLLL.LLLL.LLLLLL\n" +
                "LL...L...........L...L..L......LL...........L...L.LL..LL....L....LLL.LLLL....LLL...LL..L..L...L\n" +
                "LLLLLLLLLLLLLLL.LLLLLL.LLLLLLLLL.LLLLLLLLLLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLL.LLLL.LLLL.LLLL.LLLLLL\n" +
                "LLLLL.LLLL.LLLLLLLLLLLLLLLLLLLLL.LLLLLLL.LLLLLLLLLL.LLLL.LLLLLL.LLLLLLLLL.LLLL.LLLL.LLLLLLLLLLL\n" +
                "LLLLLLLLLL.LLLL.LLLLLL.LLL.LLLLLLLLLLLLL.LLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLL.LLLL.LLLL.LLLLLLLLLLL\n" +
                ".LLLL.LLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLL.LLLLLLLLLLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLLLL.LLLL.LLLLLL\n" +
                "LLLLL.LLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLL.LLLL.LLLL.LLLL.LLLLLL\n" +
                "LLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLL\n" +
                "LLLLL.LL.LLLLLLLLLLLLL.LLLLLLLLL.LLLLLLL.LLLLLLLLLL.LLLLLLLLLLL.LLLLLLLL..LLLL.LLL..LLLLLLLLLLL\n" +
                ".......L...LL.L.....L.L.....LLL.L.......L.....LL.......L..LLL.....L.LL.L..........LL...........\n" +
                "LLLLL.LLLLLLLLL.LLLLLL.LLLLLLLLL.LLLLLLLLLLLLL.LLLLLL.LL.LL.LLL.LLLLLLLLLLLLLL.LLLL.LLLLLLLLLLL\n" +
                "LLLLLLLLLL.LLLL.LLLLLL.LLLLLLLLL.LLLLLLL.LLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLL.LLLL.LLLL.LLLL.LLLLLL\n" +
                "LLLLL.LLLL.LLLLLLLLLLL.LLLLLLLLLLLLLLLLL.LLLLLLLLLL.LLLL.LLLLLL.LLLLLLLL..LLLLLLLLL.LLLL.LLLLLL\n" +
                "LLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLL.LLLLLLLLLLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLL.LLLL.LLLLLL\n" +
                "LLLLL.LLLL.LLLL.LLLLLL.LLLLLLLLL.LLLLLLL.LLLLL.LLLL.LLLL.LLLLLLLLLLLLLLLL.LLLL.LLLL.LLLL.LLLLLL\n" +
                "LLLLL.LLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLLLL.LLLLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL.LLLLLLLLL.LLLLLL";
    }


    public static void main(String[] args) {
        String[] arg = input().split("\n");
        Seats seats = new Seats(Arrays.asList(arg));
        while(seats.goToNextState()) {
            //rearrange seats
        }
        seats.printSeats();
        System.out.println(seats.getOccupiedSeats());
    }


}