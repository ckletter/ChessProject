import java.util.Scanner;

/**
 * Chess Game - Cody Kletter 2/12/23
 * Operates a chess game and user turns with GUI window
 *
 * To be implemented: valid moves, checks, checkmate
 */
public class ChessGame {
    /** Instance Variables **/
    private Board board;
    private boolean isWin;
    private ChessGameView window;

    /** Constructor for ChessGame
     * Instantiates the isWin boolean, Board class, and ChessGameView class
     */
    public ChessGame()
    {
        board = new Board();
        isWin = false;
        window = new ChessGameView(board);
    }

    /** All methods to run the chess game
     * Prints the board
     * Calls each user turn until the game has been won
     * After each turn, repaints the board
     */
    public void playGame()
    {
        board.printBoard();
        while (!isWin)
        {
            turn();
            window.repaint();
            board.printBoard();
        }
    }

    /** Method to run one full user turn
     * Asks for user input on the row and column to move the piece from
     * Asks the user for input on the row and column to move the piece to
     * Calls method to move the piece
     */
    public void turn()
    {
        Scanner s = new Scanner(System.in);

        // Gets letter for column to move piece from
        System.out.println("What col would you like to move your piece from? (a-h)");
        // Converts letter to number between 0-7 corresponding to row
        int colFrom = s.next().charAt(0) - 97;
        s.nextLine();
        // Gets number for row to move piece from
        System.out.println("What row would you like to move your piece from? (1-8)");
        int rowFrom = s.nextInt();
        s.nextLine();
        // Gets letter for column to move piece to
        System.out.println("What col would you like to move your piece to? (a-h)");
        // Converts letter to number between 0-7 corresponding to row
        int colTo = s.next().charAt(0) - 97;
        s.nextLine();
        // Gets number for row to move piece to
        System.out.println("What row would you like to move your piece to? (1-8)");
        int rowTo = s.nextInt();
        s.nextLine();

        // Moves the piece to the desired square on the board
        board.movePiece(colFrom, rowFrom, colTo, rowTo);
    }
    /** Gets instance of the Board class board and returns it **/
    public Board getBoard()
    {
        return board;
    }

    /** Main method */
    public static void main(String[] args) {
        // Instantiates the class ChessGame and calls playGame() on it
        ChessGame cg = new ChessGame();
        cg.playGame();
    }
}