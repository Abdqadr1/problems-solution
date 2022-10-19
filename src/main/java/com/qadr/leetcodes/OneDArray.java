import java.util.*;

public class OneDArray {
	public static boolean solve(int leap, int[] game, int i) {
		if(i >= game.length) return true;
		
		if(i < 0 || game[i] == 1)return false;
		
		game[i] = 1;
		
		if(solve(leap, game, i + leap)) return true;
		
		else if(solve(leap, game, i + 1)) return true;
		
		else if (solve(leap, game, i-1)) return true;
		
		return false;
	}

    public static boolean canWin(int leap, int[] game) {
        // Return true if you can win the game; otherwise, return false.
        return solve(leap, game, 0);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();
            
            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }

            System.out.println( (canWin(leap, game)) ? "YES" : "NO" );
        }
        scan.close();
    }
}