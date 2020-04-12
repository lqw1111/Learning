package leetcode;

public class Tic_Tac {
    int[] rows;
    int[] cols;
    int diagonal;
    int antiDiagonal;

    /** Initialize your data structure here. */
    public Tic_Tac(int n) {
        rows = new int[n];
        cols = new int[n];
        diagonal = 0;
        antiDiagonal = 0;
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int num = player == 1 ? 1 : -1;

        rows[row] += num;
        cols[col] += num;

        if(row == col)
            diagonal += num;

        if(row == rows.length - 1 - col)
            antiDiagonal += num;

        if(Math.abs(rows[row]) == rows.length
                || Math.abs(cols[col]) == rows.length
                || Math.abs(diagonal) == rows.length
                || Math.abs(antiDiagonal) == rows.length)
            return player;

        return 0;
    }

    public static void main(String[] args) {
        Tic_Tac tic_tac = new Tic_Tac(2);
        tic_tac.move(0,1,1);
        tic_tac.move(1,1,2);
    }
}
