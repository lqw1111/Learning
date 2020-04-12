package pack1;

import java.util.Scanner;
import java.util.regex.Matcher;

public class Solution {
    public static void main(String[] args) {

    }

    public static int method1(String str1, String str2){


        str1 = str1.replaceAll("\\s", "").toLowerCase();
        str1 = str1.replaceAll("\\t", "").toLowerCase();
        int count = 0;
        for(int i = 0 ; i < str1.length() ; i ++){
            if (i + str2.length() > str1.length())
                break;
            if(str1.substring(i, i + str2.length()).equals(str2)){
                count ++;
            }
        }

        return count;
    }

    public static String validTicTacToe(String[] board) {
        char[][] gameBoard = new char[3][3];

        for (int i = 0; i < 3; i++) {
            gameBoard[i] = board[i].toCharArray();
        }

        int numOfX = 0;
        int numOfO = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = gameBoard[i][j];

                if (c == '1') {
                    numOfX++;
                } else if (c == '2') {
                    numOfO++;
                }
            }
        }

        if (numOfX < numOfO || numOfX - numOfO > 1) {
            return "-1";
        }

        if (numOfX - numOfO == 1) {
            if (hasWon(gameBoard, 'O')) {
                return "-1";
            } else{
                return "2";
            }
        }

        if (numOfX == numOfO) {
            if (hasWon(gameBoard, 'X')) {
                return "-1";
            }else{
                return "1";
            }
        }

        return "0";
    }

    private static boolean hasWon(char[][] gameBoard, char player) {
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][0] == player &&
                    gameBoard[i][1] == gameBoard[i][0] &&
                    gameBoard[i][2] == gameBoard[i][1]) {
                return true;
            }

            if (gameBoard[0][i] == player &&
                    gameBoard[1][i] == gameBoard[0][i] &&
                    gameBoard[2][i] == gameBoard[1][i]) {
                return true;
            }

            if (player == gameBoard[0][0] &&
                    gameBoard[0][0] == gameBoard[1][1] &&
                    gameBoard[1][1] == gameBoard[2][2]) {
                return true;
            }

            if (player == gameBoard[0][2] &&
                    gameBoard[1][1] == gameBoard[0][2] &&
                    gameBoard[1][1] == gameBoard[2][0]) {
                return true;
            }
        }

        return false;
    }
}
