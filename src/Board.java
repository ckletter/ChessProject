import java.awt.*;

/**
 * Holds a 2D array of Square objects
 * contains methods to move pieces, create the board,
 * and print it in the console
 * Contains public final values to reference each piece
 * used in all classes in this program
 * Contains instances of each player
 */
public class Board {
    /** Board of Square objects **/
    private Square[][] squares;
    /** Square colors **/
    public static final Color darkBrown = new Color(111, 78, 55);
    public static final Color lightBrown = new Color(245,222,179);
    /** Piece integer values to reference piece names **/
    public static final int ROOK = 0;
    public static final int PAWN = 1;
    public static final int BISHOP = 2;
    public static final int KNIGHT = 3;
    public static final int QUEEN = 4;
    public static final int KING = 5;
    /** Length values **/
    public static final int SQUARE_NUM = 8;
    public static final int BOARD_LENGTH = 600;
    /** Instance of each player **/
    private White pWhite;
    private Black pBlack;

    /**
     * Constructor for Board
     * Initializes the 2D array of Squares and fills each square with a new Square object of alternating colors
     * Creates the pieces on the board
     * Initializes the players
     */
    public Board()
    {
        // Initializes the 2D array
        squares = new Square[8][8];
        // Fills 2D array with new Square objects of alternating colors
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                // Ensures alternating colors for each square adjacent
                if ((i + j) % 2 == 1)
                {
                    squares[i][j] = new Square(i, j, darkBrown);
                }
                else {
                    squares[i][j] = new Square(i, j, lightBrown);
                }
            }
        }
        // Creates all the Pieces on the board and passes them in as instance variables for each square

        // Black Pieces
        for (int i = 0; i < 8; i++)
        {
            squares[1][i].setPiece(new Pawn(Piece.BLACK));
        }
        squares[0][0].setPiece(new Rook(Piece.BLACK));
        squares[0][1].setPiece(new Knight(Piece.BLACK));
        squares[0][2].setPiece(new Bishop(Piece.BLACK));
        squares[0][3].setPiece(new Queen(Piece.BLACK));
        squares[0][4].setPiece(new King(Piece.BLACK));
        squares[0][5].setPiece(new Bishop(Piece.BLACK));
        squares[0][6].setPiece(new Knight(Piece.BLACK));
        squares[0][7].setPiece(new Rook(Piece.BLACK));
        // White pieces
        for (int i = 0; i < 8; i++)
        {
            squares[6][i].setPiece(new Pawn(Piece.WHITE));
        }
        squares[7][0].setPiece(new Rook(Piece.WHITE));
        squares[7][1].setPiece(new Knight(Piece.WHITE));
        squares[7][2].setPiece(new Bishop(Piece.WHITE));
        squares[7][3].setPiece(new Queen(Piece.WHITE));
        squares[7][4].setPiece(new King(Piece.WHITE));
        squares[7][5].setPiece(new Bishop(Piece.WHITE));
        squares[7][6].setPiece(new Knight(Piece.WHITE));
        squares[7][7].setPiece(new Rook(Piece.WHITE));

        // Initialize each player
        pWhite = new White("White");
        pBlack = new Black("Black");
    }

    /**
     * Prints the board in the console
     * Prints row and column markers in the console for user to reference
     */
    public void printBoard()
    {
        // Prints each row of the board in console
        for (int i = 0; i < 8; i++)
        {
            // Sets row marker such that it counts down each row from 8 to 1
            int row = SQUARE_NUM - i;

            // Prints row marker
            System.out.print(row + " ");

            // Prints each col of the board in console
            for (int j = 0; j < 8; j++)
            {
                // Prints a dash if the square does not contain a Piece
                if (squares[i][j].getPiece() == null) {
                    System.out.print("-\t\t");
                }
                // Prints the name of the Piece
                else {
                    System.out.print(squares[i][j].getPiece() + "\t");
                }
            }
            System.out.println();
        }
        System.out.print("  ");
        // Prints column markers at the bottom of the board
        for (int i = 0; i < 8; i++)
        {
            System.out.print((char)(97 + i) + "\t\t");
        }
        System.out.println();
    }

    /**
     * Getter method
     * Returns instance of the 2D array of squares
     */
    public Square[][] getSquares()
    {
        return squares;
    }

    /**
     * Called in ChessGame
     * Moves the piece from one spot in the 2D array to the other using row and column parameters
     */
    public void movePiece(int colFrom, int rowFrom, int colTo, int rowTo)
    {
        // Changes row markers corresponding to the indexes of the 2D array (between 0 and 7)
        rowFrom = SQUARE_NUM - rowFrom;
        rowTo = SQUARE_NUM  - rowTo;
        // Gets the Piece at the corresponding square of the 2d array that the rowTo and colTo point to
        Piece pieceTo = squares[rowTo][colTo].getPiece();

        // If the Piece is being moved to a square with another Piece, adds the Piece to the takenPieces array
        // of the player whose piece was taken
        if (pieceTo != null) {
            if (pieceTo.getColor() == Piece.BLACK) {
                pBlack.addPiece(pieceTo);
            }
            else {
                pWhite.addPiece(pieceTo);
            }
        }
        // Moves the Piece to the new Square
        squares[rowTo][colTo].setPiece(squares[rowFrom][colFrom].getPiece());
        // Sets the old square to having no Piece
        squares[rowFrom][colFrom].setPiece(null);
    }

    /**
     * Getter Method
     * Returns instance of the white player
     */
    public White getpWhite() {
        return pWhite;
    }
    /**
     * Getter Method
     * Returns instance of the black player
     */
    public Black getpBlack() {
        return pBlack;
    }
}
