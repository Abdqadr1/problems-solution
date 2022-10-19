import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;



public class Java2DArray {
     static int findSumHourGlass(List<List<Integer>> arr, int row, int col){
        int sum = arr.get(row).get(col) + arr.get(row).get(col + 1) + arr.get(row).get(col + 2)
        			+ arr.get(row + 1).get(col + 1) +
        			arr.get(row + 2).get(col) + arr.get(row + 2).get(col + 1) + arr.get(row + 2).get(col + 2);	
        return sum;
     }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int max = Integer.MIN_VALUE;

        List<List<Integer>> arr = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            String[] arrRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> arrRowItems = new ArrayList<>();

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowTempItems[j]);
                arrRowItems.add(arrItem);
            }

            arr.add(arrRowItems);
        }
        
        for (int row = 0;row < 4;row++) {
            for(int col=0;col<4;col++) {
            	int sum = findSumHourGlass(arr, row, col);
            	if(sum > max) max = sum;
            }
        }
        
        System.out.println(max);

        bufferedReader.close();
    }
}
