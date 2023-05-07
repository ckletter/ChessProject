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
    }
    /** Prints instructions in the console for how to play the Chess game**/
    public void printInstructions()
    {
        System.out.println("\nWelcome to Cody's Chess Game! This game will function as an actual chess game, but for now,"
                + " you can just move and take pieces on the board.\n\nYou can choose the row and column of the squares "
                + "you want to move pieces from and to by entering it in the console. Have fun!\n\n");
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