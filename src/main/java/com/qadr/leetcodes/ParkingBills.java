import java.util.Scanner;

public class ParkingBills {
	public static int parking(String E, String L) {
        String[] EArr = E.split(":");
        String[] LArr = L.split(":");
        int EH = Integer.valueOf(EArr[0]) * 60;
        int EM = Integer.valueOf(EArr[1]);
        int LH = Integer.valueOf(LArr[0]) * 60;
        int LM = Integer.valueOf(LArr[1]);
        int ETime = EH + EM;
        int LTime = LH + LM;
        int cost = 2;
        for (int i = ETime,count=1; i < LTime; i += 60,count++) {
        	cost = (count!=1)? cost+4:cost+3;
        }
        return cost;
    }
	
	public static int pow(int N) {
        // write your code in Java SE 8
		int ct = 0;
		if(N == 1) return 0;
		for (int i=2;i <= N; i *= 2) {
			if(N % i == 0) {
				ct++;
			}
		}
		return ct;
    }
	public static String solution(int A, int B) {
        // write your code in Java SE 8
		String result = "", aa = "a", bb= "b";
		int max = Math.max(A, B);
		int aCount = 0, bCount = 0, a=0, b=0;
		int c = A + B;
		if(max == B) {
			int C = A;
			A = B;
			B = C;
			aa = "b";
			bb = "a";
		}
		while (c-- > 0) {
			if(a < A && aCount != 2) {
				result += aa;
				a++; aCount++; bCount = 0;
			} 
			
			double remA = A - a;
			double remB = B - b;
			double diff = remA / remB;
//			System.out.println(diff);
			
			if(b < B && bCount != 2 && (remA / remB) <= 2) {
				result += bb;
				b++; bCount++; aCount = 0;
			} 

			if(result.length() == A+B) break;
		}
		return result;
    }
	 public static void main(String[] args) {
	    	Scanner sc = new Scanner (System.in);
	    	int a = sc.nextInt();
	    	int b = sc.nextInt();
	    	System.out.println(solution(a, b));
	    	sc.close();
	    }
}
