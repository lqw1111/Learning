package OODesign.ChineseChess;

public class Game {
    private Player redPlayer;
    private Player balckPlayer;
    private Player currentPlayer;
    private Piece[][] board;
    private int step;
    private ChineseChess chess;

    public void joinGame(Player p){}

    public void initializeBoard(){}

    public boolean move(Piece piece, int row, int col){
        return true;
    }

    private void changePlayer(){}

    private boolean ifCUrrentPlayerWin(){
        return true;
    }

    private Boolean gameDraw(){
        return true;
    }
}
