import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TicTacToe {
	
	static void render(char[] arr) {
		System.out.println();
		for(int i=1;i<=arr.length; i++) {
			System.out.print("  " + arr[i-1] + "  ");
			if((i % 3) == 0) System.out.println();
		}
	}
	
	static boolean isComplete(char[] ch) {
		for(char a : ch) {
			if((int)a == 0) return false;
		}
		return true;
	}

	static boolean hasWon(char[] board) {
		boolean hasWon = false;
		Map<Integer, List<Integer>> lines = new HashMap<>();
		lines.put(0, List.of(0,1,2));
		lines.put(1, List.of(3,4,5));
		lines.put(2, List.of(6,7,8));
		lines.put(3, List.of(0,3,6));
		lines.put(4, List.of(1,4,7));
		lines.put(5, List.of(2,5,8));
		lines.put(6, List.of(0,4,8));
		lines.put(7, List.of(2,4,6));
		for(List<Integer> set : lines.values()) {
//			System.out.println(board[set.get(0)]);
			char first = board[set.get(0)];
			char second = board[set.get(1)];
			char third = board[set.get(2)];
			if((int)first != 0 && (int)second != 0 && (int)third != 0) {
				if(first == second && second == third) {
					hasWon = true;
				}
			}
			
		}
		
		return hasWon;
	}
	public static void main(String[] args) {
		Scanner sc = null;
		char[] board = new char[9];
		boolean isNext = false;
		while(!isComplete(board)) {
			char symbol = isNext ? 'O' :'X';
			char player = isNext ? '2' : '1';
			System.out.println(String.format("Player %c turn", player));
			sc = new Scanner(System.in);
			int point = sc.nextInt();
			if((int)(board[point-1]) != 0) {
				System.out.println("Invalid move, Try again!");
				continue;
			}
			board[point-1] = symbol;
			if(hasWon(board)) {
				System.out.println(String.format("Player %c won", player));
				break;
			}
			isNext = !isNext;
			render(board);
		}
		render(board);
		sc.close();
	}

}
