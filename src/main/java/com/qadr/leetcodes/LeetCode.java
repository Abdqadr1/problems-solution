package com.qadr.leetcodes;

import java.math.BigInteger;
import java.util.*;
import java.util.function.IntConsumer;

public class LeetCode {
    static class MyHashSet {
        boolean[] elements;

        public MyHashSet() {
            elements = new boolean[10000];
        }

        public void add(int key) {
            elements[key] = true;
        }

        public void remove(int key) {
            elements[key] = false;
        }

        public boolean contains(int key) {
            return elements[key];
        }
    }
    static class Training {
        public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
            int trainingHours = 0;
            for (int i = 0; i < energy.length; i++){
                int opponentEnergy = energy[i], opponentExperience = experience[i];
                if(initialEnergy <= opponentEnergy || initialExperience <= opponentExperience){
                    if(initialEnergy <= opponentEnergy){
                        int hoursNeeded = opponentEnergy - initialEnergy;
                        hoursNeeded++;
                        initialEnergy += hoursNeeded;
                        trainingHours += hoursNeeded;
                    }
                    if (initialExperience <= opponentExperience){
                        int hoursNeeded = opponentExperience - initialExperience;
                        hoursNeeded++;
                        initialExperience += hoursNeeded;
                        trainingHours += hoursNeeded;
                    }
                }

                initialEnergy -= opponentEnergy;
                initialExperience += opponentExperience;
            }
            return trainingHours;
        }
        public char nextGreatestLetter(char[] letters, char target) {
            for (char c : letters){
                if(c > target) return c;
            }

            return letters[0];
        }

        public int countElements(int[] nums) {
            int number = 0;
            Arrays.sort(nums);
            for (int i = 1; i < nums.length-1; i++) {
                if(nums[0] < nums[i] && nums[nums.length-1] > nums[i]) number++;
            }
            return number;
        }
    }
    static class PeekingIterator implements Iterator<Integer> {
        int pointerIndex = 0;
        int[] nums = new int[1000];
        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            while (iterator.hasNext()){
                nums[pointerIndex++] = iterator.next();
            }
            pointerIndex =0;
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return nums[pointerIndex];
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            Integer peek = peek();
            pointerIndex++;
            return peek;
        }

        @Override
        public boolean hasNext() {
            return nums[pointerIndex] > 0;
        }
    }
    static class Refill {
        public int minimumRefill(int[] plants, int capacityA, int capacityB) {
            int leftPointer = 0, rightPointer = plants.length - 1;
            int canA = capacityA, canB = capacityB;
            int[] refill = new int[1];
            while (rightPointer >= leftPointer){
                if(leftPointer == rightPointer){
                    if(plants[leftPointer] >canA && plants[leftPointer] > canB)
                        refill[0]++;
                    break;
                }
                int plantA = plants[leftPointer], plantB = plants[rightPointer];
                if(plantA > canA){
                    canA = capacityA;
                    refill[0]++;
                }
                if(plantB > canB){
                    canB = capacityB;
                    refill[0]++;
                }
                canA -= plantA;
                canB -= plantB;
                rightPointer--;
                leftPointer++;
            }
            return refill[0];
        }
        public int wateringPlants(int[] plants, int capacity) {
            int steps = 0;
            int can = capacity;
            for (int i = 0; i < plants.length; i++) {
                steps++;
                int plant = plants[i];
                if(plant > can){
                    can = capacity;
                    steps += i + i ;
                }
                can -= plant;
            }
            return steps;
        }

    }
    static class ZeroEvenOdd {
        private int n, count;
        private boolean printZeros;

        public ZeroEvenOdd(int n) {
            this.n = n;
            this.printZeros = true;
            this.count = 1;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public synchronized void zero(IntConsumer printNumber) throws InterruptedException {
            while (count <= n){
                if(!printZeros){
                    wait();
                    continue;
                }

                printNumber.accept(0);
                this.printZeros = false;
                notifyAll();
            }
        }

        public synchronized void even(IntConsumer printNumber) throws InterruptedException {
            while (count <= n){
                if (printZeros || count % 2 == 1){
                    wait();
                    continue;
                }

                if(count % 2 == 0) printNumber.accept(count);
                this.printZeros = true;
                count++;
                notifyAll();
            }
        }

        public synchronized void odd(IntConsumer printNumber) throws InterruptedException {
            while (count <= n){
                if (printZeros || count % 2 == 0){
                    wait();
                    continue;
                }

                if(count % 2 == 1) printNumber.accept(count);

                this.printZeros = true;
                count++;
                notifyAll();
            }
        }
    }

    public static String largestOddNumber(String str){
        String[] split = str.split("");
        for (int i = split.length - 1; i >= 0; i--) {
            int num = Integer.parseInt(split[i]);
            if(num  % 2 == 1){
                return str.substring(0, i+1);
            }
        }
        return "";
    }

    private static BigInteger arrayProduct(int[] nums){
        BigInteger product = BigInteger.valueOf(1);
        for (int num : nums) {
            if(num == 0) return BigInteger.valueOf(0);
            product = product.multiply(BigInteger.valueOf(num));
        }
        return product;
    }
    public static int getMaxLen(int[] nums){
        Map<String, Integer> memo = new HashMap<>();
        return getMaxLen(nums, memo);
    }
    public static int getMaxLen(int[] nums, Map<String, Integer> memo) {
        if(memo.containsKey(Arrays.toString(nums))) return memo.get(Arrays.toString(nums));
        if(nums.length == 1) return nums[0] > 0 ? 1 : 0;

        if(arrayProduct(nums).compareTo(BigInteger.ZERO) > 0) return nums.length;

        int[] left = new int[nums.length - 1], right = new int[nums.length - 1];
        System.arraycopy(nums, 0, left, 0, nums.length - 2 + 1);
        System.arraycopy(nums, 1, right, 0, nums.length - 1);

        int maxLenRight = getMaxLen(left, memo);
        int maxLenLeft = getMaxLen(right, memo);


        int max = Math.max(maxLenLeft, maxLenRight);
        memo.put(Arrays.toString(nums), max);
        return max;
    }
    public static int getMaxSubArrayLen(int[] nums){
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = nums.length; j >= 1 ; j--) {
                BigInteger total = BigInteger.ONE;
                for (int k = i; k < j; k++) {
                    int num = nums[k];
                    if(num == 0) {
                        total = BigInteger.ZERO;
                        break;
                    }
                    total = total.multiply(BigInteger.valueOf(num));
                }
                int len = j - i;
                if(total.compareTo(BigInteger.ZERO) > 0 && len > max) max = len;
            }
        }
        return max;
    }
    public static int maxLength(int[] nums){
        int res=0 , pos=0, neg=0;
        for (int num : nums) {
            if(num < 0) {
                int temp = pos;
                pos = neg == 0 ? 0 : neg + 1;
                neg = ++temp;
            } else if (num > 0){
                pos++;
                neg = neg == 0 ? 0 : neg + 1;
            } else {
                pos = 0;
                neg = 0;
            }
            res = Math.max(res, pos);
        }
        return res;
    }

    public static int videoStitching(int[][] clips, int time) {
        Map<String, Integer> memo = new HashMap<>();
        return videoStitching(clips, time, 0, memo);
    }
    public static int videoStitching(int[][] clips, int time, int startTime, Map<String, Integer> memo) {
        if(memo.containsKey(startTime+"")) return memo.get(startTime+"");
        if(startTime >= time) {
            memo.put(startTime+"", 0);
            return 0;
        }
        int min = -1;
        for (int[] clip : clips){
            int start = clip[0], end = clip[1], res;
            if(start <= startTime && end > startTime){
                res = videoStitching(clips, time, end, memo);
                if(res > -1){
                    res++;
                    if(min == -1 || res < min){
                        min = res;
                    }
                }
            }
        }
        memo.put(startTime+"", min);
        return min;
    }

    public static int lenLongestFibSubseq(int[] arr) {
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) map.put(arr[i], i);

        for (int i = 0; i < arr.length; i++) {
            int start = arr[i], target = 0, len = 1;
            for (int j = i + 1; j < arr.length; j++) {
                len++;
                int next = arr[j];
                target =  next + start;
                while(map.containsKey(target)){
                    len++;
                    int temp = target;
                    target = target + next;
                    next = temp;
                }
                if(len >= 3){
                    if(max == 0 || len > max) max = len;
                }
                len = 1;
            }

        }
        return max;
    }
    public static Map<String, String> getCode(){
        Map<String, String> code = new HashMap<>();
        String[] codes = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",
                ".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        int codeStart = 97;
        for (String s : codes) {
            String ch = String.valueOf(Character.toChars(codeStart));
            code.put(ch, s);
            codeStart++;
        }
        return code;
    }
    public static int uniqueMorseRepresentations(String[] words) {
        Map<String, String> rep = new HashMap<>();
        String[] codes = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",
                ".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        for (String word : words){
            StringBuilder code = new StringBuilder();
            for (Character c : word.toCharArray()){
                code.append(codes[c - 'a']);
            }
            System.out.println(word + ": "+ code);
            rep.put(code.toString(), word);
        }
        return rep.keySet().size();
    }

    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int rightPointer=0, leftPointer=0;
        int res = 0;
        Map<Character, Integer> seen = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            Integer pos = seen.get(ch);

            if (pos != null && pos >= rightPointer) rightPointer = pos + 1;

            seen.put(ch, i);
            leftPointer++;
            res = Math.max(leftPointer - rightPointer, res);
        }
        return res;
    }

    static class FizzBuzz {
        private int n, count;

        public FizzBuzz(int n) {
            this.n = n;
            this.count = 1;
        }

        // printFizz.run() outputs "fizz".
        public synchronized void fizz(Runnable printFizz) throws InterruptedException {
            while (count <= n){
                if(!(count % 3 == 0 && count % 5 != 0)){
                    wait();
                    continue;
                }

                printFizz.run();
                count++;
                notifyAll();
            }
        }

        // printBuzz.run() outputs "buzz".
        public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
            while (count <= n){
                if(!(count % 3 != 0 && count % 5 == 0)){
                    wait();
                    continue;
                }

                printBuzz.run();
                count++;
                notifyAll();
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            while (count <= n){
                if(!(count % 3 == 0 && count % 5 == 0)){
                    wait();
                    continue;
                }

                printFizzBuzz.run();
                count++;
                notifyAll();
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public synchronized void number(IntConsumer printNumber) throws InterruptedException {
            while (count <= n){
                if(!(count % 3 != 0 && count % 5 != 0)){
                    wait();
                    continue;
                }

                printNumber.accept(count);
                count++;
                notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("bbtablud"));
//        FizzBuzz fizzBuzz = new FizzBuzz(15);
//        Thread thread1 = new Thread(() -> {
//            try {
//                fizzBuzz.fizz(()-> System.out.println("fizz"));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        Thread thread2 = new Thread(() -> {
//            try {
//                fizzBuzz.buzz(()-> System.out.println("buzz"));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        Thread thread3 = new Thread(() -> {
//            try {
//                fizzBuzz.fizzbuzz(()-> System.out.println("fizzbuzz"));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        Thread thread4 = new Thread(() -> {
//            try {
//                fizzBuzz.number(System.out::println);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//
//        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();

//
//        System.out.println(LeetCode.lenLongestFibSubseq(new int[]{3, 4, 5, 6, 7, 8}));
////        int initialEnergy = 5, initialExperience = 3;
////        int[] energy = {1, 4, 3, 2}, experience = {2, 6, 3, 1};
////        int initialEnergy = 2, initialExperience = 4; int[] energy = {1}, experience = {3};
////        System.out.println(new Training().minNumberOfHours(initialEnergy, initialExperience, energy, experience));
////        System.out.println(new Training().countElements(new int[]{11, 7, 2, 15}));
//        System.out.println(new Refill().wateringPlants(new int[]{7,7,7,7,7,7,7}, 8));
    }
}
