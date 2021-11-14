package ba.unsa.etf.rpr.adventOfCode.Day13;

import java.math.BigInteger;
import java.util.*;
public class Zadatak2 {

    public static class ChineseRemainderTheorem {
        BigInteger N;
        // x congruent reminder(mod n_i)
        List<Integer> n;
        List<Integer> reminders;
        List<BigInteger> partialProducts;
        List<Integer> subsolutionsX;
        BigInteger result;

        public ChineseRemainderTheorem() {
            N = BigInteger.ONE;
            n = new LinkedList<>();
            reminders = new LinkedList<>();
            partialProducts = new LinkedList<>();
            subsolutionsX = new LinkedList<>();
            result = BigInteger.ZERO;
        }

        public void setRemindersAndN(String[] IDs) {
            int j = 0;
            for (int i = 0; i < IDs.length; i++) {
                if (!IDs[i].equals("x")) {
                    this.n.add(Integer.parseInt(IDs[i]));
                    //Let's consider bus number 13, which has an offset of 1. That means at T+1 you will be able to get on that bus 13.
                    // Thus, T+1 % 13 is zero. Which also means T % 13 is 12.
                    this.reminders.add(Math.abs(((this.n.get(j))-i % (this.n.get(j))) % (this.n.get(j)) ));
                    j++;
                }
            }
            System.out.println("Numbers: ");
            this.n.forEach(System.out::println);
            System.out.println("Remainders: ");
            this.reminders.forEach(System.out::println);
        }

        public void findTotalProduct() {
            // Find N as total product and then partial products
            for(Integer n : this.n ) {
                this.N = this.N.multiply(BigInteger.valueOf(n));
            }
        }

        public void setPartialProducts() {
            for(Integer n : this.n) {
                this.partialProducts.add(this.N.divide(BigInteger.valueOf(n)));
            }
            partialProducts.forEach(System.out::println);
        }

        public void findPartialSolutions() {
            // solving partial congruent equations
            // partialProduct * x_i congruent reminder_i (mod n_i)
            for(int i = 0; i < partialProducts.size(); i++) {
                int reminder = partialProducts.get(i).mod(BigInteger.valueOf(n.get(i))).intValue();
                boolean found = false;
                int x = 1; //start solution
                while(found == false) {
                    if(partialProducts.get(i).multiply(BigInteger.valueOf(x)).mod(BigInteger.valueOf(n.get(i))).equals(BigInteger.valueOf(reminders.get(i)))) {
                        found = true;
                        break;
                    } else
                        x += 1;
                }
                subsolutionsX.add(x);
            }
            subsolutionsX.forEach(System.out::println);
        }

        public void findFinalSolution() {
            // sum(x_1*partial)
            for (int i = 0; i < subsolutionsX.size(); i++)
                this.result = this.result.add(partialProducts.get(i).multiply(BigInteger.valueOf(subsolutionsX.get(i))));
            System.out.println(result.mod(this.N));
        }
    }
    static String input() {
        return "19,x,x,x,x,x,x,x,x,x,x,x,x,37,x,x,x,x,x,883,x,x,x,x,x,x,x,23,x,x,x,x,13,x,x,x,17,x,x,x,x,x,x,x,x,x,x,x,x,x,797,x,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,29";
    }
    public static void main(String[] args) {
        // Chinese theroem of reminders
        String[] IDs = input().split(",");
        List<Integer> num = new LinkedList<>();
        List<Integer> reminders = new LinkedList<>();
        ChineseRemainderTheorem crt = new ChineseRemainderTheorem();

        crt.setRemindersAndN(IDs);
        crt.findTotalProduct();
        crt.setPartialProducts();
        crt.findPartialSolutions();
        crt.findFinalSolution();
    }
}