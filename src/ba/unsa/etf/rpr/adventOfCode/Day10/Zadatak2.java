package ba.unsa.etf.rpr.adventOfCode.Day10;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;


public class Zadatak2 {
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
        return "49\n" +
                "89\n" +
                "70\n" +
                "56\n" +
                "34\n" +
                "14\n" +
                "102\n" +
                "148\n" +
                "143\n" +
                "71\n" +
                "15\n" +
                "107\n" +
                "127\n" +
                "165\n" +
                "135\n" +
                "26\n" +
                "119\n" +
                "46\n" +
                "53\n" +
                "69\n" +
                "134\n" +
                "1\n" +
                "40\n" +
                "81\n" +
                "140\n" +
                "160\n" +
                "33\n" +
                "117\n" +
                "82\n" +
                "55\n" +
                "25\n" +
                "11\n" +
                "128\n" +
                "159\n" +
                "61\n" +
                "105\n" +
                "112\n" +
                "99\n" +
                "93\n" +
                "151\n" +
                "20\n" +
                "108\n" +
                "168\n" +
                "2\n" +
                "109\n" +
                "75\n" +
                "139\n" +
                "170\n" +
                "65\n" +
                "114\n" +
                "21\n" +
                "92\n" +
                "106\n" +
                "162\n" +
                "124\n" +
                "158\n" +
                "38\n" +
                "136\n" +
                "95\n" +
                "161\n" +
                "146\n" +
                "129\n" +
                "154\n" +
                "121\n" +
                "86\n" +
                "118\n" +
                "88\n" +
                "50\n" +
                "48\n" +
                "62\n" +
                "155\n" +
                "28\n" +
                "120\n" +
                "78\n" +
                "60\n" +
                "147\n" +
                "87\n" +
                "27\n" +
                "7\n" +
                "54\n" +
                "39\n" +
                "113\n" +
                "5\n" +
                "74\n" +
                "169\n" +
                "6\n" +
                "43\n" +
                "8\n" +
                "29\n" +
                "18\n" +
                "68\n" +
                "32\n" +
                "19\n" +
                "133\n" +
                "22\n" +
                "94\n" +
                "47\n" +
                "132\n" +
                "59\n" +
                "83\n" +
                "12\n" +
                "13\n" +
                "96\n" +
                "35";
    }


    public static void main(String[] args) {
        String[] arg = input().split("\n");

        List<Integer> input = new LinkedList<>();
        for(int i = 0; i < arg.length; i++)
            input.add(Integer.parseInt(arg[i]));

        input.add(0);
        input.add(deviceBuiltInJoltage(input));
        input = input.stream().sorted().collect(Collectors.toList());
//        for(Integer item : input) System.out.println(item);
        ////////////
        BigInteger result = combinations(input, new HashMap<>());
        System.out.println("DONE");
        System.out.println(result);
    }
    //Memoization ensures that a method doesn't run for the same inputs more than once by keeping a record of the
    // results for the given inputs (usually in a hash map).
    private static BigInteger combinations(List<Integer> input, Map<List<Integer>, BigInteger> memo) {
        if(memo.containsKey(input))
            return memo.get(input);
        BigInteger result = BigInteger.ONE;
        for(int i = 1; i < input.size() - 1; i++) {
            if(input.get(i + 1) - input.get(i - 1) <= 3) {
                List<Integer> copyList = new LinkedList<>();
                copyList.add(input.get(i - 1));
                copyList.addAll(input.subList(i + 1, input.size() ));
//                copyList = Stream.concat(copyList.stream(), input.stream()).collect(Collectors.toList());
                result = result.add(combinations(copyList, memo));
            }
        }
        memo.put(input, result);
        return result;
    }

    private static Integer deviceBuiltInJoltage(List<Integer> input) {
        Integer max = input
                .stream()
                .mapToInt(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        return max + 3;
    }


}