package com.qadr.leetcodes;

import java.util.*;

public class RomanInteger {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String roman = scanner.nextLine();
        int[] nums = {1,6,7,5,2,4,10,6,4};
        System.out.println(new Solution().maxProductDifference(nums));
    }
}
class Solution {
    private Map<String, Integer> getMap(){
        Map<String, Integer> roman = new HashMap<>();
        roman.put("I", 1);
        roman.put("V", 5);
        roman.put("X", 10);
        roman.put("L", 50);
        roman.put("C", 100);
        roman.put("D", 500);
        roman.put("M", 1000);
        return roman;
    }

    public int romanToInt(String s) {
        Map<String, Integer> map = getMap();
        String[] split = s.split("");
        int total = map.get(split[0]);
        for (int i = 1; i < split.length; i++) {
            int number = map.get(split[i]);
            int numberBefore = map.get(split[i-1]);
            if(number > numberBefore){
                total -= numberBefore;
                total += (number - numberBefore);
            }else{
                total += number;
            }
        }
        return total;
    }

    public int maxProductDifference(int[] nums) {
        int[] pickedNumbers = {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE};
        findNumbers(nums, pickedNumbers);
        printArray(pickedNumbers);
        return (pickedNumbers[3] * pickedNumbers[2]) - (pickedNumbers[0] * pickedNumbers[1]);
    }

    private void findNumbers(int[] nums, int[] pickedNumbers){
        for (int i = 0; i < nums.length; i++) {
            // find max
            if (nums[i] > pickedNumbers[2]) {
                pickedNumbers[2] = nums[i];
                if (nums[i] > pickedNumbers[3]) {
                    int oldMax = pickedNumbers[3];
                    pickedNumbers[3] = nums[i];
                    pickedNumbers[2] = oldMax;
                }
            }
            // find min
            if (nums[i] < pickedNumbers[1]) {
                pickedNumbers[1] = nums[i];
                if (nums[i] < pickedNumbers[0]) {
                    int oldMin = pickedNumbers[0];
                    pickedNumbers[0] = nums[i];
                    pickedNumbers[1] = oldMin;
                }
            }
        }
    }

    private void printArray(int[] arr) {
		for(int i : arr) {
			System.out.print(i + " ");
		}
    }
}

class Checker {
    class Player
    {
        String name;
        int score;
    }
    public Comparator<Player> desc = new Comparator<Player>() {
        @Override
        public int compare(Player player1, Player player2) {
            int score1 = player1.score, score2 = player2.score;
            if(score1 != score2) {
                if(score1 > score2) return -1;
                return 1;
            }
            int res = player1.name.compareTo(player2.name);
            if(res < 0) return 1;
            if(res > 0) return -1;
            return res;
        }
    };
}
