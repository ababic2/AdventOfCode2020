package ba.unsa.etf.rpr.adventOfCode.Day16;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Zadatak2_trebaUpdate {
//    https://github.com/tpatel/advent-of-code-2020/blob/main/day16.js

    public static class Pair {
        private Field field;
        private int index;

        public Pair(Field field, int index) {
            this.field = field;
            this.index = index;
        }

        public Field getField() {
            return field;
        }

        public void setField(Field field) {
            this.field = field;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public void printPair() {
            this.field.printField();
            System.out.println(index);
        }
    }

    public static class Field {
        private String type;
        private int from_1;
        private int to_1;
        private int from_2;
        private int to_2;

        public Field(String type, int from_1, int to_1, int from_2, int to_2) {
            this.type = type;
            this.from_1 = from_1;
            this.to_1 = to_1;
            this.from_2 = from_2;
            this.to_2 = to_2;
        }

        public int getTo_2() {
            return to_2;
        }

        public void setTo_2(int to_2) {
            this.to_2 = to_2;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getFrom_1() {
            return from_1;
        }

        public void setFrom_1(int from_1) {
            this.from_1 = from_1;
        }

        public int getTo_1() {
            return to_1;
        }

        public void setTo_1(int to_1) {
            this.to_1 = to_1;
        }

        public int getFrom_2() {
            return from_2;
        }

        public void setFrom_2(int from_2) {
            this.from_2 = from_2;
        }

        public void printField() {
            System.out.println(type + " " + from_1 + "  " + to_1 + " || " + from_2 + "  " + to_2 );
        }

    }

    public static class Ticket {
        private String ticketType;
        private List<Integer> numbersOnTicket;

        public Ticket(String ticketType, List<Integer> numbersOnTicket) {
            this.ticketType = ticketType;
            this.numbersOnTicket = numbersOnTicket;
        }

        public String getTicketType() {
            return ticketType;
        }

        public void setTicketType(String ticketType) {
            this.ticketType = ticketType;
        }

        public List<Integer> getNumbersOnTicket() {
            return numbersOnTicket;
        }

        public void setNumbersOnTicket(List<Integer> numbersOnTicket) {
            this.numbersOnTicket = numbersOnTicket;
        }

        public void printTicket() {
            System.out.println(ticketType);
            numbersOnTicket.forEach(System.out::println);
        }
    }

    static String input() {
        return "class: 0-1 or 4-19\n" +
                "row: 0-5 or 8-19\n" +
                "seat: 0-13 or 16-19\n" +
                "\n" +
                "your ticket:\n" +
                "11,12,13\n" +
                "\n" +
                "nearby tickets:\n" +
                "3,9,18\n" +
                "15,1,5\n" +
                "5,14,9";
    }

    public static void main(String[] args) {
        // input as rules, ticket numbers and some scanned tickets
        // what numbers in tickets match no rules at all -> find invalid ones

        String[] arg = input().split("\n\n");
        String[] fields = arg[0].split("\n"); // regex -> departure location: 45-535 or 550-961

        // Reading rules
        List<Field> fieldList = new LinkedList<>();
        extractInfoWithPattern(fields, fieldList);
//        fieldList.forEach(Field::printField);

        // Reading my ticket
        String[] myTicketRead = arg[1].split("\n");
        String[] numbersOnTicket = myTicketRead[1].split(",");
        Ticket myTicket = new Ticket(myTicketRead[0], extractNumbersOnTicket(numbersOnTicket));
//        myTicket.printTicket();

        // Reading other tickets
        String[] nearbyTicketRead = arg[2].split("\n");
        List<Ticket> nearbyTickets = new LinkedList<>();
        for(int i = 1; i < nearbyTicketRead.length; i++) {
//            System.out.println("Iteration " + i);
            String[] numbersOnNearbyTicket = nearbyTicketRead[i].split(",");
            Ticket nearbyTicket = new Ticket(nearbyTicketRead[0], extractNumbersOnTicket(numbersOnNearbyTicket));
            nearbyTickets.add(nearbyTicket);
        }

        // Looking for numbers that dont match any rule at all
        List<Ticket> validTickets = getListOfValidTickets(fieldList, nearbyTickets, myTicket);
//        validTickets.forEach(Ticket::printTicket);
        List<Pair> matches = new LinkedList();

        for (int i = 0; i < myTicket.getNumbersOnTicket().size(); i++) {

            for (Field rule : fieldList) {
                boolean valid = true;
                for (Ticket ticket : validTickets) {
                    if(!matchRule(rule, ticket.getNumbersOnTicket().get(i))) {
                        valid = false;
                        break;
                    }
                }
                if(valid) {
                    Pair container = new Pair(rule, i);
                    matches.add(container);
                }
            }
        }
//        for (Pair match : matches) match.printPair();
        System.out.println("ULAZIM");
        while(matches.size() > fieldList.size()) {
            System.out.println("LOOP");
            // da matches size bude kao field jer je tako svakom rule dodjeljen number
            for (int i = 0; i < myTicket.getNumbersOnTicket().size(); i++) {
                int ind = i;
                List<Pair> rulesMatchingCurrentIndex = matches.stream().filter(m -> m.index == ind).collect(Collectors.toList());
                if(rulesMatchingCurrentIndex.size() == 1) {
                    //znaci za neko pravilo je jedno poklapanje
                    // uklanjam ostala ponavljanja tog pravila
//                    for (Pair match : matches) match.printPair();
                    Pair currentRule = rulesMatchingCurrentIndex.get(0);
                    matches = matches.stream().filter(m -> {
                        if(m.getField() == currentRule.getField()) {
                            //pronadji gdje se ponavlja pravilo, ako to nijr taj indeks
                            // vratit ce false i to vise nece biti u matches, za ostale field nece biti isti, pa se nece izbaciti
                            return m.getIndex() == currentRule.getIndex();
                        }
                        return true;
                    }).collect(Collectors.toList());
                }
            }
        }

        // Print result
        List<Pair> departureFields = matches.stream().filter(m -> m.getField().getType().startsWith("departure")).collect(Collectors.toList());

        int result = 1;
        for (Pair field : departureFields) {
            result *= myTicket.getNumbersOnTicket().get(field.getIndex());
        }
        System.out.println(result);

    }

    private static List<Ticket> getListOfValidTickets(List<Field> fieldList, List<Ticket> nearbyTickets, Ticket myTicket) {
        boolean valid = true;
        List<Ticket> validTickets = new LinkedList<>();
        validTickets.add(myTicket);
        for (Ticket nearbyTicket : nearbyTickets) {
            for (Integer number : nearbyTicket.getNumbersOnTicket()) {
                if(!matchAtLeastOneRule(number, fieldList)) {
                    valid = false;
                    break;
                }
            }
            if(valid)
                validTickets.add(nearbyTicket);
        }
        return validTickets;
    }

    private static List<Integer> extractNumbersOnTicket(String[] numbersOnTicket) {
        List<Integer> numbersOnTicketList = new LinkedList<>();
        for(int i = 0; i < numbersOnTicket.length; i++)
            numbersOnTicketList.add(Integer.parseInt(numbersOnTicket[i]));
        return numbersOnTicketList;
    }

    private static void extractInfoWithPattern(String[] fields, List<Field> fieldList) {
        Pattern p = Pattern.compile("([^:]+): (\\d+)-(\\d+) or (\\d+)-(\\d+)");
        for(int i = 0; i < fields.length; i++) {
            Matcher m = p.matcher(fields[i]);
            if(m.find()) {
                Field field = new Field(m.group(1), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4)), Integer.parseInt(m.group(5)));
                fieldList.add(field);
            }
        }
    }

    private static boolean matchRule(Field rule, int value) {
        return (value >= rule.from_1 && value <= rule.to_1) || (value >= rule.from_2 && value <= rule.to_2);
    }

    private static boolean matchAtLeastOneRule(int value, List<Field> rules) {
        for (Field rule : rules) {
            if(matchRule(rule, value)) {
                // we need numbers that dont match any rule at all
                return true;
            }
        }
        return false;
    }
}