package leetcode;

import java.util.*;

public class GameOfLife {
    static class Pair{
        int x;
        int y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            Pair p = (Pair)obj;
            if(this.x == p.x && this.y == p.y)
                return true;
            else
                return false;
        }
    }
    // die -> live : -1;
    // live -> die : 2;
    // -1 => 1 ; 2 => 0;
    public void gameOfLife(int[][] board) {
        if(board == null || board.length == 0)
            return;
        for(int i = 0 ; i < board.length ; i++){
            for(int j = 0 ; j < board[0].length ; j++){
                if(board[i][j] == 0){
                    int lives = calculate(i, j ,board);
                    board[i][j] =  lives == 3 ? -1 : 0;
                }else{
                    int lives = calculate(i, j ,board);
                    board[i][j] = (lives == 2 || lives == 3) ? 1 : 2;
                }
            }
        }
        // for(int i = 0 ; i < board.length ; i++){
        //     for(int j = 0 ; j < board[0].length ; j++){
        //         if(board[i][j] == 0){
        //             update(board, i, j);
        //         }else{
        //             update(board, i, j);
        //         }
        //     }
        // }
    }

    private int calculate(int row, int col, int[][] board){
        return  num(board, row + 1, col) +
                num(board, row - 1, col) +
                num(board, row, col + 1) +
                num(board, row, col - 1) +
                num(board, row + 1, col + 1) +
                num(board, row + 1, col - 1) +
                num(board, row - 1, col + 1) +
                num(board, row - 1, col - 1);
    }

    private int num(int[][] board, int row, int col){
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length)
            return 0;
        else
            return board[row][col] > 0 ? 1 : 0;
    }

    private void update(int[][] board, int i, int j){
        if(board[i][j] < 0){
            board[i][j] = board[i][j] == -3 ? 1 : 0;
        }else if(board[i][j] > 0){
            board[i][j] = (board[i][j] == 2 || board[i][j] == 3) ? 1 : 0;
        }else{
            board[i][j] = 0;
        }
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0)
            return -1;
        if (grid.length == 1 && grid[0].length == 1)
            return 0;
        Queue<int[]> q = new ArrayDeque<>();
        if(grid[0][0] == 1)
            return -1;
        int[] root = {0,0};
        q.add(root);

        int res = 0;

        while(!q.isEmpty()){
            int size = q.size();
            for (int i = 0 ; i < size ; i ++){
                int[] position = q.poll();
                int row = position[0];
                int col = position[1];
                if (row == grid.length - 1 && col == grid[0].length - 1)
                    return res;
                List<int[]> list = Arrays.asList(new int[]{row + 1, col},
                        new int[]{row - 1, col},
                        new int[]{row, col + 1},
                        new int[]{row, col - 1},
                        new int[]{row + 1, col + 1},
                        new int[]{row + 1, col - 1},
                        new int[]{row - 1, col + 1},
                        new int[]{row - 1, col - 1});

                for (int[] p : list){
                    add(q, p[0], p[1], grid);
                }

                grid[row][col] = 1;
            }
            res ++;
        }

        return -1;
    }

    private void add(Queue<int[]> q, int row, int col, int[][] grid){
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 1)
            return;
        q.add(new int[]{row, col});
    }

    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();
    }
}
