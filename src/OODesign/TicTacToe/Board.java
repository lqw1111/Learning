package OODesign.TicTacToe;

import OODesign.TicTacToe.exception.InvalidMove;

public class Board {
    private char[][] board;

    public void initializeBoard(int row, int col){
        board = new char[row][col];
    }

    public void makeMove(int row, int col, char currentMove) throws InvalidMove {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] == 'x' || board[row][col] == 'o')
            throw new InvalidMove();
        board[row][col] = currentMove;
    }

    public boolean checkWin(int row, int col, char currentMove){
        return true;
    }

    public boolean isBoardFull(){
        return false;
    }
}
