package com.qadr.leetcodes;

import java.util.*;

public class CodilityTest {
    public int solution(String[] tests, String[] reports) {
        // write your code in Java SE 11
        int passed = 0;
        Map<String, Integer> testMap = new HashMap<>();
        for (int i = 0; i < tests.length; i++) {
            String test = tests[i];
            char lastChar = test.charAt(test.length()-1);
            String report = reports[i];
            if(lastChar >= 49 && lastChar <= 57){
                if(report.equals("OK")) {
                    testMap.put(test, 1);
                } else testMap.put(test, 0);
            }else{
                test = test.substring(0, test.length()-1);
                System.out.println(test);
                if(testMap.containsKey(test) && testMap.get(test) > 0){
                    if(report.equals("OK")) {
                        testMap.put(test, 1);
                    } else testMap.put(test, 0);
                }else if(!testMap.containsKey(test)){
                    if(report.equals("OK")) {
                        testMap.put(test, 1);
                    } else testMap.put(test, 0);
                }
            }

        }
        for (String test : testMap.keySet()){
            passed += testMap.get(test);
        }
        System.out.println(testMap);
        return (int) Math.ceil((passed*100)/testMap.keySet().size());
    }
    public int travelingLocations(int[] locations) {
        // write your code in Java SE 11
        int min=Integer.MAX_VALUE;
        Set<Integer> cities = new HashSet<>();
        for (int dest : locations) cities.add(dest);
        int count = cities.size();
        Map<Integer, Integer> lTimes = new HashMap<>();
        int lastIndex = 0;
        cities = new HashSet<>();
        for (int i = 0; i < locations.length-count; i++) {
            if(i-1 >= 0){
                int in = locations[i-1];
                int freq = lTimes.getOrDefault(in, 0) - 1;
                lTimes.put(in, freq);
                if(freq < 1) cities.remove(in);
            }
            while(cities.size() < count && lastIndex < locations.length){
                int in = locations[lastIndex++];
                lTimes.put(in, lTimes.getOrDefault(in, 0) + 1);
                cities.add(in);
            }
            if(cities.size() == count && lastIndex-i < min){
//                System.out.println("new min: " + lastIndex + " and " + i);
                min = lastIndex-i;
            }
        }
        return  min;
    }
    public int solution(int[] A, int S) {
        // write your code in Java SE 11
        int ways=0;
        for (int i = A.length;i > 0;i--) {
            for(int j=0;j < A.length;j++){
                List<Integer> each = new ArrayList<>();
                int sum= 0;
                for(int x = j;x < i;x++) {
                    each.add(A[x]);
                    sum += A[x];
                }
                if( each.size() > 0 && sum%each.size()==0 &&  sum/each.size()== S) {
                    System.out.println(each);
                    ways++;
                }
            }
        }
        return ways;
    }

    public static int maxDifference(List<Integer> px) {
        int min=px.get(0);
        int max=-1;
        for (Integer integer : px) {
            if (min >= integer) min = integer;
            else {
                if (max < integer - min)
                    max = integer - min;
            }
        }
        return max;
    }
    public static String  longestPalSubstr(String str) {
        Map<Character, Integer> charactersMap = new HashMap<>();
        for(char ch : str.toCharArray()) {
            if (charactersMap.containsKey(ch)) {
                charactersMap.put(ch, charactersMap.get(ch) + 1);
            } else {
                charactersMap.put(ch, 1);
            }
        }

        int oddCount = 0;
        char oddChar = 0;

        for(Map.Entry<Character,Integer> itr : charactersMap.entrySet()){
            if (itr.getValue() % 2 != 0) {
                oddCount++;
                oddChar = itr.getKey();
            }
        }

        if (oddCount > 1 || oddCount == 1 && str.length() % 2 == 0){
            return "";
        }

        StringBuilder firstHalf = new StringBuilder();
        StringBuilder lastHalf = new StringBuilder();
        for(Map.Entry<Character, Integer> itr : charactersMap.entrySet()) {
            StringBuilder ss = new StringBuilder();
            ss.append(String.valueOf(itr.getKey()).repeat(Math.max(0, itr.getValue() / 2)));

            firstHalf.append(ss);
            lastHalf.insert(0, ss);
        }
        return (oddCount == 1) ?
                (firstHalf.toString() + oddChar + lastHalf) :
                (firstHalf + lastHalf.toString());
    }


    public static void main(String[] args) {
        CodilityTest test = new CodilityTest();
        System.out.println(test.travelingLocations(new int[]{1,2,3,7,2,7,3,1,3,4,1}));
//        System.out.println(longestPalSubstr("abcbadefqfed"));
//        System.out.println(test.solution(new int[]{0, 4, 3, -1}, 2));
//        System.out.println(test.solution
//                new String[]{"test1a", "test2", "test1b", "test3"},
//                new String[]{"OK", "OK", "OK", "OK"})
//        );
    }
}
