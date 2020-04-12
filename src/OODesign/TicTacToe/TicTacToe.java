package OODesign.TicTacToe;

import OODesign.TicTacToe.exception.InvalidMove;

public class TicTacToe {
    private Board board;
    private char currentMove;

    public TicTacToe(){
        board = new Board();
        currentMove = 'x';
    }

    public void makeMove(int row, int col) throws InvalidMove {
        board.makeMove(row, col, currentMove);

        if (board.checkWin(row, col, currentMove)){
            System.out.println(currentMove + "win");
        } else if (board.isBoardFull()){
            System.out.println("it is tie");
        }
        changePlayer();
    }

    private void changePlayer(){
        currentMove = currentMove == 'x' ? 'o' : 'x';
    }
}
