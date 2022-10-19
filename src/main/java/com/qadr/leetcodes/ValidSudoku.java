package com.qadr.leetcodes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ValidSudoku {
    private final int GRID_SIZE = 9;
    private Map<Character, Integer> map;
    private boolean isRowSafe(char[][] board, int row, char ch){
        for (int i = 0; i < GRID_SIZE; i++) {
            char num = board[row][i];
            if(num == ch) return false;
        }
        return true;
    }
    private boolean isColSafe(char[][] board, int col, char ch){
        for (int i = 0; i < GRID_SIZE; i++) {
            char num = board[i][col];
            if(num == ch) return false;
        }
        return true;
    }
    private boolean isSubGridSafe(char[][] board, int row, int col, char chara){
        int c = col - (col % 3);
        int r = row - (row % 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char ch = board[r+i][c+j];
                if(ch == chara) return false;
            }
        }
        return true;
    }
    private boolean isSafeMove(char[][] board, int row, int col, char ch){
        return isRowSafe(board, row, ch) && isColSafe(board, col, ch) && isSubGridSafe(board, row, col, ch);
    }
//    public boolean isValidSudoku(char[][] board) {
//        for (int i = 0; i < GRID_SIZE; i++) {
//            for (int j = 0; j < GRID_SIZE; j++) {
//                char ch = board[i][j];
//                if(ch=='.')continue;
//                if(!isRowSafe(board, i) || !isColSafe(board, j) || !isSubGridSafe(board, i, j)){
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
    public boolean solveSudoku(char[][] board) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                char ch = board[i][j];
                if(ch=='.'){
                    for (int k = 49; k <= 57; k++) {
                        if(isSafeMove(board, i, j, (char) k)){
                            board[i][j] = (char) k;
                            if(solveSudoku(board)){
                                return true;
                            }else {
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }


    private static void printBoard(char[][] board){
        for (var i = 0; i < 9; i++){

            for (var j = 0; j < 9; j++){
                char element = board[i][j];
                if(j > 0 && (j+1) % 3 == 0)
                    System.out.print(element + " | ");
                else System.out.print(element + " ");
                if(j == (9 - 1)) System.out.println("");
            }
            if(i > 0 && (i+1) % 3 == 0)
                System.out.println("-----------------------");
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                ,{'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(new ValidSudoku().solveSudoku(board));
        printBoard(board);
    }

}
