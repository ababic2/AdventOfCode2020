package ba.unsa.etf.rpr.adventOfCode.Day3;

public class Day3_zadatak1 {
    static String input(){
        String input = "";
        return input;
    }
    public static void main(String[] args) {

        int trees = getTrees(1,1);
        System.out.println(trees);
    }

    private static int getTrees(int right, int increment) {
        String ulaz = input();
        String[] strArray = ulaz.split("\n");
        int trees = 0;
        int start = right;
        String tree = "#";
        for(int i = 1; i < strArray.length; i+=increment)
        {
            while(strArray[i].length() < start)
            {
                strArray[i] = strArray[i].concat(strArray[i]);
            }
            if(strArray[i].charAt(start) == tree.charAt(0))
            {
                trees += 1;
            }
            start += right;
        }
        return trees;
    }
}
