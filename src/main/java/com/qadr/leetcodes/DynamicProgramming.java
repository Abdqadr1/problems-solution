package com.qadr.leetcodes;

import java.util.*;

public class DynamicProgramming {
    static class Memoization{
        public static long gridTraveler(int row, int col){
            Map<String, Long> memo = new HashMap<>();
            return gridTraveler(row, col,memo);
        }
        public static long gridTraveler(int row, int col, Map<String, Long> memo){
            if(memo.containsKey(row+","+col)) return memo.get(row+","+col);
            if(row == 0 || col == 0) return 0;
            if(row == 1 || col == 1) return 1;

            long res = gridTraveler(row-1, col, memo) + gridTraveler(row, col - 1, memo);
            memo.put(row+","+col, res);
            return res;
        }
        public static long fibonacci(int n){
            Map<Integer, Long> memo = new HashMap<>();
            return fibonacci(n, memo);
        }
        public static long fibonacci(int n, Map<Integer, Long> memo){
            if(memo.containsKey(n)) return memo.get(n);
            if(n < 2) return n;

            long result = fibonacci(n - 1, memo) + fibonacci(n - 2, memo);
            memo.put(n, result);
            return result;
        }
        public static boolean canConstruct(String word, String[] array){
            Map<String, Boolean> memo = new HashMap<>();
            return canConstruct(word, array, memo);
        }
        public static boolean canConstruct(String word, String[] array, Map<String, Boolean> memo){
            if(memo.containsKey(word)) return memo.get(word);
            if (Objects.equals(word, "")) return true;
            for (String w : array){
                if(word.startsWith(w)){
                    String substring = word.substring(word.indexOf(w) + w.length());
                    if(canConstruct(substring, array,memo)) {
                        memo.put(word, true);
                        return true;
                    }
                }
            }
            memo.put(word, false);
            return false;
        }
        public static List<List<String>> allConstruct(String word, String[] wordBank){
            List<List<String>> result = new ArrayList<>();
            allConstruct(word, wordBank, result, new ArrayList<String>());
            return result;
        }
        private static void allConstruct(String word, String[] wordBank, List<List<String>> result, List<String> list) {
            if(Objects.equals(word, "")) result.add(new ArrayList<>(list));

            for (String w : wordBank){
                if(word.startsWith(w)){
                    String substring = word.substring(word.indexOf(w) + w.length());
                    list.add(w);
                    allConstruct(substring, wordBank, result, list);
                    list.remove(list.size()-1);
                }
            }
        }
        public static List<String> bestConstruct(String word, String[] wordBank){
            Map<String, List<String>> memo = new HashMap<>();
            return bestConstruct(word, wordBank, memo);
        }
        private static List<String> bestConstruct(String word, String[] wordBank, Map<String, List<String>> memo) {
            if(memo.containsKey(word)) return memo.get(word);
            if(word.equals("")) return new ArrayList<>();

            List<String> result = null;

            for (String w : wordBank){
                if(word.startsWith(w)){
                    String substring = word.substring(word.indexOf(w) + w.length());
                    List<String> lists = bestConstruct(substring, wordBank, memo);
                    if(lists != null){
                        List<String> map = new ArrayList<>(lists);
                        map.add(0, w);
                        if(result == null || map.size() < result.size()){
                            result = map;
                        }
                    }
                }
            }

            memo.put(word, result);
            return result;
        }
        public static boolean canSum (int sum, int[] numbers){
            Map<Integer, Boolean> memo = new HashMap<>();
            return canSum(sum, numbers, memo);
        }
        public static boolean canSum(int sum, int[] numbers, Map<Integer, Boolean> memo){
            if(memo.containsKey(sum)) return memo.get(sum);
            if(sum == 0) return true;

            for (int number : numbers){
                if(number <= sum && canSum(sum - number, numbers)){
                    memo.put(sum, true);
                    return true;
                }
            }

            memo.put(sum, false);
            return false;
        }
        public static List<List<Integer>> allSum(int sum, int[] numbers){
            List<List<Integer>> result = new ArrayList<>();
            allSum(sum, numbers, result, new ArrayList<>());
            return result;
        }
        public static void allSum(int sum, int[] numbers, List<List<Integer>> result, List<Integer> list){
            if(sum == 0) result.add(new ArrayList<>(list));

            for (int number : numbers){
                if(number <= sum){
                    list.add(number);
                    allSum(sum-number, numbers, result,list);
                    list.remove(list.size()-1);
                }
            }
        }
        public static List<Integer> bestSum(int sum, int[] numbers){
            Map<Integer, List<Integer>> memo = new HashMap<>();
            return bestSum(sum, numbers, memo);
        }
        public static List<Integer> bestSum(int sum, int[] numbers, Map<Integer, List<Integer>> memo){
            if(memo.containsKey(sum)) return memo.get(sum);
            if(sum == 0) return new ArrayList<>();

            List<Integer> result = null;

            for(int number : numbers){
                if(number <= sum){
                    List<Integer> integers = bestSum(sum - number, numbers, memo);
                    if(integers != null){
                        List<Integer> collect = new ArrayList<>(integers);
                        collect.add(number);
                        if(result == null || collect.size() < result.size()){
                            result = collect;
                        }
                    }

                }
            }

            memo.put(sum, result);
            return result;
        }

        public static int[][] insert(int[][] intervals, int[] newInterval) {
            if(intervals.length == 0) return new int[][]{{newInterval[0], newInterval[1]}};
            int minStart = newInterval[0], maxEnd = newInterval[1], count = 0, endIndex=-1;
            for (int i=0;i < intervals.length;i++){
                int start = intervals[i][0];
                int end = intervals[i][1];
                if((start <= newInterval[0] && end >= newInterval[0]) || (start <= newInterval[1] && end >= newInterval[1]) ||
                        (newInterval[0] <= start && newInterval[1]>= start) || (newInterval[1] <= start && newInterval[1]>=end)){
                    minStart = Math.min(start, minStart);
                    maxEnd = Math.max(end, maxEnd);
                    count++;
                    endIndex = i;
                }
            }
            int len, startIndex;
            int[][] newInsert;
            if(count > 0){
                startIndex = endIndex - count + 1;
                len = intervals.length - (count-1);
                newInsert = new int[len][2];
                if (startIndex >= 0) System.arraycopy(intervals, 0, newInsert, 0, startIndex);
                newInsert[startIndex] = new int[]{minStart, maxEnd};
                if (newInsert.length - (startIndex + 1) >= 0)
                    System.arraycopy(intervals, endIndex + 1, newInsert, startIndex + 1,
                            newInsert.length - (startIndex + 1));
            }else{
                len = intervals.length + 1;
                newInsert = new int[len][2];
                boolean added = false;
                for (int i = 0, c=0; i < intervals.length; i++) {
                    if(!added && minStart < intervals[i][0]){
                        newInsert[c] = new int[]{minStart, maxEnd};
                        added = true;
                        i--;
                    }else {
                        newInsert[c] = intervals[i];
                    }
                    c++;
                }
                if(!added) newInsert[newInsert.length-1] = new int[]{minStart, maxEnd};
            }
            return newInsert;
        }
        static class NumberFreq{
            int number; int freq;
            public NumberFreq(int n, int f){
                this.number =n;
                this.freq=f;
            }

            @Override
            public String toString() {
                return "NumberFreq{" +
                        "number=" + number +
                        ", freq=" + freq +
                        '}';
            }
        }
        public static int minSetSize(int[] arr) {
            int min=0, len=arr.length, half=len/2;
            Map<Integer, Integer> freqMap = new HashMap<>();
            for (int number : arr){
                freqMap.put(number, freqMap.getOrDefault(number, 0) + 1);
            }
            Comparator<Integer> comparator = (f1, f2) -> {
                int res = Integer.compare(f1, f2);
                if(res ==1) return -1;
                if(res ==-1) return 1;
                return res;
            };
            Queue<Integer> queue = new PriorityQueue<>(comparator);
            queue.addAll(freqMap.values());
            while (!queue.isEmpty() && len > half){
                int current = queue.poll();
                len -= current;
                min++;
            }
            return min;
        }
        public static int findMinArrowShots(int[][] points) {
            Arrays.sort(points, Comparator.comparingInt(p -> p[1]));
            int count = 0, current = 0;
            for (int[] point : points) {
                int start = point[0];
                int end = point[1];
                if(count == 0 || start > current){
                    count++;
                    current = end;
                }
            }

            return count;
        }
        public static int[] expand(String s, int startIndex, int endIndex){
            while(startIndex >= 0 && endIndex < s.length() && s.charAt(startIndex) == s.charAt(endIndex)){
                startIndex--;
                endIndex++;
            }
            return new int[]{++startIndex, --endIndex};
        }
        public static String longestPalindrome(String s) {
            int start=0, end=0;
            for (int i = 0; i < s.length(); i++) {
                int[] expand1 = expand(s, i, i);
                int[] expand2 = expand(s, i, i+1);
                if(expand2[1]-expand2[0] > expand1[1]-expand1[0]){
                    if(expand2[1]-expand2[0] > end - start){
                        start = expand2[0];
                        end = expand2[1];
                    }
                }else{
                    if(expand1[1]-expand1[0] > end - start){
                        start = expand1[0];
                        end = expand1[1];
                    }
                }
            }
            return s.substring(start, end+1);
        }
        public static int strStr(String haystack, String needle) {
            int len = haystack.length() - needle.length() + 1;
            for (int i = 0; i < len; i++) {
                if(haystack.startsWith(needle, i)) return i;
            }
            return -1;
        }
        public static String convertZigzag(String s, int numRows) {
            if(numRows == 1|| s.length() ==1 ) return s;
            StringBuilder[] stringBuilders = new StringBuilder[numRows];
            for (int i = 0; i < stringBuilders.length; i++) {
                stringBuilders[i] = new StringBuilder();
            }
            int i=0;
            while (i < s.length()){
                for (int j = 0; j < numRows && i < s.length(); j++) {
                    stringBuilders[j].append(s.charAt(i++));
                }
                for (int j = numRows-2; j > 0 && i < s.length(); j--) {
                    stringBuilders[j].append(s.charAt(i++));
                }
            }
            for (int j = 1; j < stringBuilders.length; j++) stringBuilders[0].append(stringBuilders[j]);
            return stringBuilders[0].toString();
        }
        public static List<String> restoreIpAddresses(String s) {
            List<String> result = new ArrayList<>();
            if(s.length() < 4) return result;
            backTrackIp(result, "", s, 1);
            return result;
        }
        public static boolean isIp(String ipNumber){
            if(ipNumber.length()<1)return false;
            if(ipNumber.length() > 1 && ipNumber.startsWith("0")) return false;
            try{
                if(Integer.parseInt(ipNumber) > 255) return false;
            }catch (NumberFormatException e) {
                return false;
            }
            return true;
        }
        public static void backTrackIp(List<String> result, String pre, String rem, int time){
            if(time == 5 && rem.isEmpty()){result.add(pre);}
            if (time < 5 && rem.length() > 0) {
                for (int i = 1; i <= 3; i++) {
                    int index = Math.min(i, rem.length());
                    String prefix = rem.substring(0, index);
                    if(!isIp(prefix))continue;
                    prefix = pre + prefix + (index < rem.length() ? "." : "");
                    String suffix = i < rem.length() ? rem.substring(i) : "";
                    System.out.println(prefix + " " + suffix);
                    backTrackIp(result, prefix, suffix, time+1);
                    if(i >= rem.length()) break;
                }
            }
        }
        public static String reorganizeString(String s) {
            if(s.length() == 1) return s;
            Map<Character, Integer> charMap = new HashMap<>();
            for(char ch : s.toCharArray()) charMap.put(ch, charMap.getOrDefault(ch, 0) + 1);
            StringBuilder result = new StringBuilder();
            Queue<Character> queue = new PriorityQueue((c1, c2) -> charMap.get(c2) - charMap.get(c1));
            queue.addAll(charMap.keySet());
            while(!queue.isEmpty()){
                Character ch = queue.poll();
                Character ch2 = queue.poll();
                int last = result.length()-1;
                if(ch != null && ch2 != null){
                    int count1 = charMap.get(ch), count2 = charMap.get(ch2);
                    if(count1 > 1 && last == -1){
                        result.append(ch).append(ch2).append(ch);
                        count1 -= 2;
                    }else{
                        if(last > -1 && result.charAt(last) != ch)result.append(ch).append(ch2);
                        else result.append(ch2).append(ch);
                        count1--;
                    }
                    if(count1 > 0) {
                        charMap.put(ch, count1);
                        queue.add(ch);
                    }
                    if(count2-1 > 0) {
                        charMap.put(ch2, count2-1);
                        queue.add(ch2);
                    }
                }else {
                    Character curr = ch == null ? ch2 : ch;
                    int count = charMap.get(curr);
                    if(count > 1 || result.charAt(result.length()-1) == curr) return "";
                    result.append(curr.toString().repeat(count));
                }
                System.out.println(result);
                System.out.println(charMap);
            }
            return result.toString();
        }
        public int compareVersion(String version1, String version2) {
            return 0;
        }
    }

    static class Tabulation{

    }


    public static void main(String[] args) {
        System.out.println(Memoization.reorganizeString("zhmyo"));

//        System.out.println(DynamicProgramming.Memoization.videoStitching(new int[][]{{0,1},{6,8},{0,2},{5,6},{0,4},{0,3},{6,7},{1,3},{4,7},{1,4},{2,5},{2,6},{3,4},{4,5}
//                ,{5,7},{6,9}}, 9));

//        System.out.println(DynamicProgramming.Memoization.maxLength(new int[]{-1, 2, 3, 0}));

//        System.out.println(DynamicProgramming.Memoization.maxLength(new int[]{70,-18,75,-72,-69,-84,64,-65,0,-82,62,54,-63,-85,53,-60,-59,29,32,59,-54,-29, -45,
//                0,-10,22,42,-37,-16,0,-7,-76,-34,37,-10,2,-59,-24,85,45,-81,56,86}));

//        System.out.println(DynamicProgramming.Memoization.bestConstruct("eeeeeeeeeeeeeeeeeeeeeeeeed",
//                new String[]{"eeee", "e", "eeeeeeeee", "ee", "d"}));

//        System.out.println(DynamicProgramming.Memoization.bestSum(210,
//                new int[]{25, 15, 70, 45, 11, 50}));
//        System.out.println(DynamicProgramming.Memoization.allConstruct("eeeeeeeeeeeeeeeeeeeeeeeed",
//                new String[]{"eeee", "e", "eeeeeeeee", "ee", "d"}));

//        System.out.println(DynamicProgramming.Memoization.allSum(209,
//                new int[]{25, 15, 70, 45, 11, 50}));


//        System.out.println(DynamicProgramming.Memoization.largestOddNumber("3691669784801845146"));
    }

}
