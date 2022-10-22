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
                if(ch != null && ch2 != null){
                    int count1 = charMap.get(ch), count2 = charMap.get(ch2), last = result.length()-1;
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
            }
            return result.toString();
        }
        public static int compareVersion(String version1, String version2) {
            String[] revisions1Array = version1.split("\\.");
            String[] revisions2Array = version2.split("\\.");
            int len = Math.max(revisions1Array.length, revisions2Array.length);
            for (int i = 0; i < len; i++) {
                String r1 = i < revisions1Array.length ? revisions1Array[i] : "0";
                String r2 = i < revisions2Array.length ? revisions2Array[i] : "0";
                int v1 = Integer.parseInt(r1);
                int v2 = Integer.parseInt(r2);
                if(v1 < v2) return -1;
                if(v2 < v1) return 1;
            }
            return 0;
        }
        public static String simplifyPath(String path) {
            Deque<String> stack = new ArrayDeque<>();
            Set<String> invalid = new HashSet<>(Arrays.asList("..",".",""));
            for (String dir : path.split("/")) {
                if (dir.equals("..") && !stack.isEmpty()) stack.pop();
                else if (!invalid.contains(dir)) stack.push(dir);
            }
            StringBuilder res = new StringBuilder();
            for (String dir : stack) res.insert(0, "/" + dir);
            return (res.length() == 0) ? "/" : res.toString();
        }
        public static List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs){
                char[] chars = str.toCharArray();
                Arrays.sort(chars);
                String key = String.valueOf(chars);
                if(!map.containsKey(key)) map.put(key, new ArrayList<>());
                map.get(key).add(str);
            }
            return new ArrayList<>(map.values());
        }
        public static long numberOfWays(String s) {
            long zero=0, one=0, zeroOne=0, oneZero=0, count=0;
            for(char ch : s.toCharArray()){
                if(ch == '1'){
                    one++;
                    zeroOne += zero;
                    count += oneZero;
                }else {
                    zero++;
                    oneZero += one;
                    count += zeroOne;
                }
            }
            return count;
        }
        public static String multiply(String num1, String num2) {
            if(num1.equals("0") || num2.equals("0")) return "0";
            int[] ans = new int[num1.length() + num2.length()];
            for (int i = num1.length()-1; i >= 0; i--) {
                char ch1 = num1.charAt(i);
                for (int j = num2.length()-1; j >= 0; j--) {
                    char ch2 = num2.charAt(j);
                    int prod = (ch1 - '0') * (ch2 - '0');
                    int p2 = i + j + 1;
                    int sum = prod + ans[p2];
                    ans[i + j] += sum / 10;
                    ans[p2] = sum % 10;
                }
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < ans.length;i++) {
                if (i==0&&ans[0]==0)continue;
                builder.append(ans[i]);
            }
            return builder.toString();
        }
        public static boolean isInterleave(String s1, String s2, String s3){
            if(s1.length() + s2.length() != s3.length()) return false;

            Boolean[][] memo = new Boolean[s1.length() + 1][s2.length() + 1];
            return isInterleave(s1, s2, s3, 0, 0, memo);
        }
        public static boolean isInterleave(String s1, String s2, String s3, int i, int j, Boolean[][] memo) {
            if((i+j) == s3.length()) return true;
            String key = i + "," + j;
            if(memo[i][j] != null) return memo[i][j];
            // for s1
            if (i < s1.length() && s3.charAt(i+j) == s1.charAt(i)){
                boolean res = isInterleave(s1, s2, s3, i+1, j, memo);
                memo[i][j] = res;
                if(res) {
                    return true;
                }
            }

            // for s2
            if (j < s2.length() && s3.charAt(i+j) == s2.charAt(j)){
//                System.out.println(newS3);
                boolean res = isInterleave(s1, s2, s3, i, j+1, memo);
                memo[i][j] = res;
                if(res) {
                    return true;
                }
            }

            memo[i][j] = false;
            return false;
        }
        public static String largestNumber(int[] nums) {
            // copy
            String[] obj = new String[nums.length];
            for (int i = 0; i < nums.length; i++) obj[i] = String.valueOf(nums[i]);
            Arrays.sort(obj, (a, b) -> {
                String as = a+b;
                String bs = b+a;
                return bs.compareTo(as);
            });
            StringBuilder ans = new StringBuilder();
            for (String i : obj) {
                if(ans.length() == 0 && Objects.equals(i, "0")) continue;
                ans.append(i);
            }
            return ans.length() == 0 ? "0" : ans.toString();

        }
        static class WordDictionary {
            private final WordDictionary[] children;
            public boolean isEndOfWord = false;
            public WordDictionary() {
                children = new WordDictionary[26];
            }
            public void addWord(String word) {
                WordDictionary current = this;
                for(char ch : word.toCharArray()){
                    if(current.children[ch - 'a'] == null) current.children[ch - 'a'] = new WordDictionary();
                    current = current.children[ch - 'a'];
                }
                current.isEndOfWord = true;
            }

            public boolean search(String word) {
                WordDictionary current = this;
                for (int i=0; i < word.length(); i++){
                    char ch = word.charAt(i);
                    if(ch == '.'){
                        for (WordDictionary dictionary : current.children) {
                            if(dictionary != null && dictionary.search(word.substring(i+1))) return true;
                        }
                        return false;
                    }
                    if(current.children[ch - 'a'] == null) return false;
                    current = current.children[ch - 'a'];
                }
                return current != null && current.isEndOfWord;
            }
        }
        static class Trie {
            private final Trie[] children;
            boolean isWord;
            public Trie() {
                children = new Trie[26];
                isWord = false;
            }

            public void insert(String word) {
                Trie current = this;
                for (char ch : word.toCharArray()){
                    if(current.children[ch - 'a'] == null) current.children[ch - 'a'] = new Trie();
                    current = current.children[ch - 'a'];
                }
                current.isWord = true;
            }

            public boolean search(String word) {
                Trie current = this;
                for (int i = 0; i < word.length(); i++) {
                    char ch = word.charAt(i);
                    if(current.children[ch - 'a'] == null) return false;
                    current = current.children[ch - 'a'];
                }
                return current != null && current.isWord;
            }

            public boolean startsWith(String prefix) {
                Trie current = this, root = this;
                for (int i = 0; i < prefix.length(); i++) {
                    char ch = prefix.charAt(i);
                    if(current.children[ch - 'a'] == null) return false;
                    current = current.children[ch - 'a'];
                }
                return current != root;
            }
        }
        public static String removeKdigits(String num, int k) {
            if(k >= num.length()) return "0";
            Deque<Character> stack = new ArrayDeque<>();
            for (char ch : num.toCharArray()){
                while(k>0 && !stack.isEmpty() && stack.peekLast() > ch){
                    stack.removeLast();
                    k--;
                }
                stack.addLast(ch);
            }
            while(k > 0){
                stack.removeLast();
                k--;
            }
            while (stack.size() > 1 && stack.peekFirst() == '0'){
                stack.removeFirst();
            }
            StringBuilder res = new StringBuilder();
            while (!stack.isEmpty())res.append(stack.removeFirst());
            return res.toString();
        }
    }

    static class Tabulation{

    }


    public static void main(String[] args) {
        System.out.println(Memoization.removeKdigits("1432219",3));

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
