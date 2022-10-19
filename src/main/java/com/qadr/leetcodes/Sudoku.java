package com.qadr.leetcodes;

public class Sudoku {

    private static final int GRID_SIZE = 9;

    private static void printBoard(int[][] board){
        for (var i = 0; i < GRID_SIZE; i++){

            for (var j = 0; j < GRID_SIZE; j++){
                int element = board[i][j];
                if(j > 0 && (j+1) % 3 == 0)
                    System.out.print(element + " | ");
                else System.out.print(element + " ");
                if(j == (GRID_SIZE - 1)) System.out.println("");
            }
            if(i > 0 && (i+1) % 3 == 0)
                System.out.println("-----------------------");
        }
    }


    private static boolean isRowClear(int[][] board, int rowIndex, int number){
        for(int column = 0; column < GRID_SIZE; column++){
            if(board[rowIndex][column] == number) return false;
        }
        return true;
    }

    private static boolean isColumnClear(int[][] board, int columnIndex, int number){
        for(int row = 0; row < GRID_SIZE; row++){
            if(board[row][columnIndex] == number) return false;
        }
        return true;
    }

    private static boolean isBoxClear(int[][] board, int rowIndex, int columnIndex, int number){
        int innerSize = 3;
        int boxRowStart = rowIndex - (rowIndex % innerSize);
        int boxColumnStart = columnIndex - (columnIndex % innerSize);
        for (var j = boxRowStart; j < (boxRowStart + innerSize); j++){
            for (var i = boxColumnStart; i < (boxColumnStart + innerSize); i++){
                if(board[j][i] == number) return false;
            }
        }
        return true;
    }
    private static boolean isValidPlacement(int[][] board, int rowIndex,int columnIndex, int number){
        return isRowClear(board, rowIndex, number) &&
                isColumnClear(board, columnIndex, number) &&
                isBoxClear(board, rowIndex, columnIndex, number);
    }
    private static boolean solve(int[][] board){
        for (var i = 0; i < GRID_SIZE; i++){
            for (var j = 0; j < GRID_SIZE; j++){
                if(board[i][j] == 0) {
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++){
                        if (isValidPlacement(board, i, j, numberToTry)){
                            board[i][j] = numberToTry;
                           if(solve(board)) return true;
                           else board[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {

        int[][] board = {
                {9,0,0,  0,0,3,  0,0,8},
                {8,2,3,  9,4,7,  1,0,0},
                {1,0,0,  0,0,0,  0,3,4},

                {0,0,5,  7,8,1,  4,2,3},
                {0,7,2,  6,9,0,  5,8,0},
                {4,8,1,  0,0,0,  0,0,7},

                {7,6,4,  0,0,0,  0,0,9},
                {2,1,0,  4,7,0,  3,6,0},
                {5,0,9,  0,0,0,  7,0,2}
        };

        System.out.println("Before: ");
        printBoard(board);

        if(solve(board)){
            System.out.println("Solved board successfully");
        } else
            System.out.println("Board is not solvable ");

        System.out.println("After: ");
        printBoard(board);
    }
}
