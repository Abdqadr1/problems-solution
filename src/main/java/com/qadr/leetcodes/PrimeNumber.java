package com.qadr.leetcodes;

public class PrimeNumber {

    public static boolean isPrime(int number){
        if(number == 1) return false;
        for(var i = 2; i <= number/2; i++){
            if(number % i == 0) return false;
        }
        return true;
    }

    public static void printPrimeNumbers(int number){
        int primeCount = 0, count = 3;
        while(primeCount < number){
            if(isPrime(count)){
                System.out.print(count + " ");
                primeCount++;
            }
            count++;
        }
    }


    public static void main(String[] args) {

        int n = 100000;
        printPrimeNumbers(n);

    }
}
