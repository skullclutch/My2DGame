package WorkStuff;

import java.util.ArrayList;

public class Range {

    public static void main(String[] args) {

        ArrayList<Integer> nums = new ArrayList<Integer>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        nums.add(5);
        nums.add(6);
        nums.add(7);
        nums.add(8);
        nums.add(9);
        nums.add(10);
        System.out.println(removeRanges(nums,3,7));
        //should return [1,2,8,9,10]

    }

    public static ArrayList<Integer> removeRanges(ArrayList<Integer> nums, int from, int to) {

        int first = nums.indexOf(from);
        int last = nums.indexOf(to);

        for (int i = first; i <= last; i++) {

                nums.remove(first);

        }

        return nums;

    }
}
