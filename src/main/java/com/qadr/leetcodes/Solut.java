
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.math.*;

public class Solut {
    
    static int IsAB(String a, String b){
        for (int i = 0; i < a.length() && i < b.length();i++){
        		
            if (a.charAt(i) != b.charAt(i)) {
            	return (int) a.charAt(i) - (int) b.charAt(i); 
            }
        }
        return 0;
    }
    
    static String capitalize(String a) {
    	String newa = "";
        for (int i = 0; i < a.length() ;i++){
    		
            if (i == 0) {
            	newa = String.valueOf(a.charAt(i)).toUpperCase();
            } else {
            	newa = newa + a.charAt(i);
            }
        }
        return newa;
    }
    
    static boolean isPalindrome(String A) {
    	for (int i = 0; i< A.length(); i++) {
       	 int blen = A.length() - 1;
       	 if(A.charAt(i) != A.charAt(blen - i)) {
       		 return false;
       	 }
       }
    	return true;
    }
    
    static Integer anagramCount(String str, String ch) {
    	Integer count = 0;
    	for(int i = 0; i < str.length();i++) {
    		if (String.valueOf(str.charAt(i)).equalsIgnoreCase(ch)) {
    			count++;
    		}
    	}
    	return count;
    }
    
    /**
     * IP addresses regular expression pattern
     * */
    String pattern = "[0-9]{2,3}.[0-9]{2,3}.[0-9]{2,3}.[0-9]{2,3}";


    static boolean isAnagram(String a, String b) {
        // Complete the function
        if (a.length() == b.length()){
        	String[] arr = a.split("");
            for (int i = 0; i < arr.length;i++) {
            	String ch = arr[i];
            	if (anagramCount(a, ch) != anagramCount(b, ch)) {
            		return false;
            	}
            }
            return true;
        } else return false;
    }
    /**
     * method to remove duplicate words from a sentence
     * */
    static String removeDuplicateWords(String in) {
    	// regular expression to match repeated words
    	String regex = "\\b([a-zA-Z_0-9]+)( (\\1)\\b)+";
    	Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    	Matcher m = pattern.matcher(in);
    	while (m.find()) {
    		in = in.replaceAll(m.group(),m.group(1));
    	}
    	return in;
    }
    
    static String tagContentExtractor(String line) {
    	String content = "";
    	// regular expression for extracting tag content
    	String regex = "<([\\x20-\\x7F]+)>([\\w \\p{Punct}&&[^<>]]+)</(\\1)>";
      	//Write your code here
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = pattern.matcher(line);
		while(m.find()) {
            content = m.group(2);
        }
        if(content.equals("")){
            content = "None";
        }
    	return content;
    }
    
    static String findEmails(String line){
    	String emails = "";
    	String regex  = "([a-zA-Z][\\w.]+@[a-zA-Z]+.com)";
    	Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    	Matcher matcher = pattern.matcher(line);
    	while(matcher.find()) {
    		emails = matcher.group(1);
    	}
    	return emails;
    }

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] arr = digest.digest(line.getBytes());
            BigInteger bigInt = new BigInteger(1,arr);
            String hashtext = bigInt.toString(16);
            if(hashtext.length() < 64){
                String padding = "";
                for(int i=0;i <= 32-hashtext.length();i++){
                    padding = padding + "0";
                }
                System.out.println(padding+hashtext);
            } else {
                System.out.println(hashtext);
            }
             System.out.println(hashtext.length()+"");
            
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sc.close();
    }
}



