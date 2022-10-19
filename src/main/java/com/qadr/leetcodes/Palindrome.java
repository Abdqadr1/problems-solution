package com.qadr.leetcodes;

import java.util.*;

import static com.qadr.leetcodes.PrimeNumber.isPrime;

public class Palindrome {
    public static boolean isPalindrome(String s){
        return new StringBuilder(s).reverse().toString().equals(s);
    }
    public static String breakPal(String palindrome){
        if(palindrome.length() < 2) return "";
        StringBuilder builder = new StringBuilder(palindrome);
        for (int i = 0; i < palindrome.length()/2; i++) {
            char ch = builder.charAt(i);
            if(ch != 'a'){
                builder.setCharAt(i, 'a');
                return builder.toString();
            }
        }
        builder.setCharAt(builder.length()-1, 'b');
        return builder.toString();
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> singles = new ArrayList<>();
        backTrack(s, singles, result);
        return result;
    }

    private static void backTrack(String s, List<String> singles, List<List<String>> result) {
        if(s.length()==0){
            System.out.println(s);
            result.add(new ArrayList<>(singles));
        }else{
            for (int i = 0; i < s.length(); i++) {
                String prefix = s.substring(0, i+1);
                String rest = s.substring(i+1);
                if(isPalindrome(prefix)){
                    singles.add(prefix);
                    backTrack(rest, singles, result);
                    singles.remove(singles.size()-1);
                }
            }
        }
    }

    public static boolean isPrime(int number){
        if( ( number & 1 ) == 0 && number != 2 ) {
            return false ;
        } else if( number % 3 == 0 && number != 3 ) {
            return false ;
        } else if( number % 11 == 0 && number != 11 ) {
            return false ;
        } else if( number % 13 == 0 && number != 13 ) {
            return false ;
        } else if( number % 17 == 0 && number != 17 ) {
            return false ;
        }else{
            for( var i = 3 ; i <= Math.sqrt(number) ; i += 2 ) {
                if( number % i == 0 ) {
                    return false ;
                }
            }
        }
        return true;
    }
    public static int primePalindrome(int number) {
        if (number == 1 || number ==2) return 2;
        if(number%2 == 0) number++;
        while(true){
             if(isPrime(number) && isPalindrome(String.valueOf(number))){
                return number;
             }
            number+=2;
             if( number > 11 && number < 100 ) {
                 number = 101 ;
             }
             if( number > 999 && number < 10000 ) {
                 number = 10001 ;
             }
             if( number > 99999 && number < 1000000 ) {
                 number = 1000001 ;
             }
             if( number > 9999999 && number < 100000000 ) {
                 number = 100000001 ;
             }
        }
    }

    public boolean canConstruct(String s, int k) {
        // get character count
        if(s.length() < k) return false;
        Map<Character, Integer> charMap = new HashMap<>();
        for(char ch : s.toCharArray()){
            charMap.put(ch, charMap.getOrDefault(ch, 0)+1);
        }
        int ans=0;
        for (Map.Entry<Character, Integer> entry : charMap.entrySet()){
            if(entry.getValue()%2 !=0)ans++;
        }
        return ans<=k;
    }

    public static boolean checkPalindromeFormation(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
            String prefixA = a.substring(0, i);
            String prefixB = a.substring(0, i);
            String suffixA = a.substring(i);
            String suffixB = b.substring(i);
            if(isPalindrome(prefixA+suffixB) || isPalindrome(prefixB+suffixA)) return true;
        }
        return false;
    }

    public static int longestPalindrome(String[] words) {
        Map<String, Integer> strMap = new HashMap<>();
        int res =0;
        for (String word : words){
            char a = word.charAt(0), b = word.charAt(1);
            String counter = b+""+a;
            if(strMap.getOrDefault(counter, 0)>0){
                res += 4;
                strMap.put(counter, strMap.get(counter)-1);
            }else {
                strMap.put(word, strMap.getOrDefault(word, 0)+1);
            }
        }
        for (String word : words){
            char a = word.charAt(0), b = word.charAt(1);
            if(strMap.getOrDefault(word,0) > 0 && a==b){
                res+=2;
            }
        }
        return res;
    }
    public int longestPalindrome(String s) {
        if(s.length()==1) return 1;
        int count=0, odd=0;
        Map<Character, Integer> countMap = new HashMap<>();
        for (char ch : s.toCharArray()){
            countMap.put(ch, countMap.getOrDefault(ch, 0)+1);
        }
        for(Integer values : countMap.values()){
            if(values % 2 == 0){
                count+= values;
            }else {
                count += values-1;
                odd = 1;
            }
        }
        return count+odd;
    }

    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            int k = queries[i][2];
            String str = s.substring(queries[i][0], queries[i][1]+1);
            result.add(i, changeString(str, k));
        }
        return result;
    }

    public static boolean changeString(String str, int k){
        int left=0, right=str.length()-1;

        return false;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome(new String[] {"ab","ty","yt","lc","cl","ab"}));
    }
}
