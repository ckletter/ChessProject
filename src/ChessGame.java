import java.util.Scanner;
public class ChessGame {
    private Board board;
    private boolean isWin;
    private ChessGameView window;
    public ChessGame()
    {
        board = new Board();
        isWin = false;
        window = new ChessGameView(board);
    }
    public void playGame()
    {
        board.printBoard();
        while (!isWin)
        {
            turn();
            board.printBoard();
        }
    }
    public void turn()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("What col would you like to move your piece from? (a-h)");
        int colFrom = s.next().charAt(0) - 97;
        s.nextLine();
        System.out.println("What row would you like to move your piece from? (1-8)");
        int rowFrom = s.nextInt() - 1;
        s.nextLine();
        System.out.println("What col would you like to move your piece to? (a-h)");
        int colTo = s.next().charAt(0) - 97;
        s.nextLine();
        System.out.println("What row would you like to move your piece to? (1-8)");
        int rowTo = s.nextInt() - 1;
        s.nextLine();
        board.movePiece(colFrom, rowFrom, colTo, rowTo);

        window.repaint();
    }
    public Board getBoard()
    {
        return board;
    }
    public static void main(String[] args) {
        ChessGame cg = new ChessGame();
        cg.playGame();
    }
}