import java.awt.*;
import java.util.ArrayList;

/**
 * Holds a 2D array of Square objects
 * contains methods to move pieces, create the board,
 * and print it in the console
 * Contains public final values to reference each piece
 * used in all classes in this program
 * Contains instances of each player
 */
public class Board {
    /**
     * Board of Square objects
     **/
    private Square[][] squares;
    /**
     * Square colors
     **/
    public static final Color darkBrown = new Color(111, 78, 55);
    public static final Color lightBrown = new Color(245, 222, 179);
    /**
     * Piece integer values to reference piece names
     **/
    public static final int ROOK = 0;
    public static final int PAWN = 1;
    public static final int BISHOP = 2;
    public static final int KNIGHT = 3;
    public static final int QUEEN = 4;
    public static final int KING = 5;
    /**
     * Length values
     **/
    public static final int SQUARE_NUM = 8;
    public static final int BOARD_LENGTH = 600;
    /**
     * Instance of each player
     **/
    private White pWhite;
    private Black pBlack;
    /**
     * Boolean for color of last move played
     **/
    private boolean lastMove;

    /**
     * Constructor for Board
     * Initializes the 2D array of Squares and fills each square with a new Square object of alternating colors
     * Creates the pieces on the board
     * Initializes the players
     */
    public Board() {
        // Initializes the 2D array
        squares = new Square[8][8];

        // Initialize each player
        pWhite = new White("White");
        pBlack = new Black("Black");

        // Calls createBoard() to fill the 8x8 board with squares and pieces
        createBoard(pWhite, pBlack);

        // Sets boolean lastMove to black's move so that it is now white's turn when game starts
        lastMove = Piece.BLACK;
    }

    /**
     * Getter method
     * Returns instance of the 2D array of squares
     */
    public Square[][] getSquares() {
        return squares;
    }

    /**
     * Called in ChessGame
     * Moves the piece from one spot in the 2D array to the other using row and column parameters
     */
    public void movePiece(Piece pieceFrom, int colTo, int rowTo) {
        // Gets the Piece at the corresponding square of the 2d array that the rowTo and colTo point to
        Piece pieceTo = squares[rowTo][colTo].getPiece();
        // If the Piece is being moved to a square with another Piece, adds the Piece to the takenPieces array
        // of the player whose piece was taken
        if (pieceTo != null) {
            if (pieceTo.getColor() == Piece.BLACK) {
                pBlack.addTakenPiece(pieceTo);
            } else {
                pWhite.addTakenPiece(pieceTo);
            }
        }
        // Moves the Piece to the new Square
        squares[rowTo][colTo].setPiece(pieceFrom);
        // Sets the old square to having no Piece
        squares[pieceFrom.getRow()][pieceFrom.getCol()].setPiece(null);
        // Sets the new row and col locations instance variables of the piece being moved
        pieceFrom.setLocation(rowTo, colTo);
        // Sets new x and y locations of the piece being moved based off new row and col it is at
        pieceFrom.setPosition(colTo * Square.SQUARE_WIDTH + 100, rowTo * Square.SQUARE_WIDTH + 100);
    }

    public boolean isValidMove(Piece pieceFrom, int colTo, int rowTo) {
        boolean pieceFromColor = pieceFrom.getColor();
        // If the same color is trying to make two moves consecutively, returns false
        if (pieceFromColor == lastMove) {
            return false;
        }
        Piece pieceTo = squares[rowTo][colTo].getPiece();
        // If there is a piece at the square the original piece is being moved to
        if (pieceTo != null) {
            // If the piece is being moved to a square with another piece of the same color, does not move the piece
            if (pieceFromColor == squares[rowTo][colTo].getPiece().getColor()) {
                return false;
            }
        }
        if (!pieceFrom.isValidJump(rowTo, colTo, this)) {
            return false;
        }
        // Since the move being played is a valid move, sets the player color with
        // The last move played to the color of the piece moved
        lastMove = pieceFromColor;
        // Returns that piece being moved is a valid move
        return true;
    }

    public boolean piecesBetween(int rowTo, int colTo, int currentRow, int currentCol, String direction, Square[][] squares) {
        if (rowTo == currentRow && colTo == currentCol) {
            return false;
        }
        Piece thisPiece = squares[currentRow][currentCol].getPiece();
        if (thisPiece != null) {
            return true;
        }
        if (direction.contains("UP")) {
            currentRow--;
        }
        if (direction.contains("LEFT")) {
            currentCol--;
        }
        if (direction.contains("DOWN")) {
            currentRow++;
        }
        if (direction.contains("RIGHT")) {
            currentCol++;
        }
        return piecesBetween(rowTo, colTo, currentRow, currentCol, direction, squares);
    }
    public boolean userTurn(Piece pieceFrom, int colTo, int rowTo) {
        boolean pieceFromColor = pieceFrom.getColor();
        // Gets the Piece at the corresponding square of the 2d array that the rowTo and colTo point to
        Piece pieceTo = squares[rowTo][colTo].getPiece();
        int prevRow = pieceFrom.getRow();
        int prevCol = pieceFrom.getCol();
        if (isValidMove(pieceFrom, colTo, rowTo)) {
            movePiece(pieceFrom, colTo, rowTo);
            if (!kingInCheck(pieceFromColor)) {
                return true;
            }
            // TODO: CODE CHECKMATE AND STALEMATE
            movePiece(pieceFrom, prevCol, prevRow);
            if (pieceTo != null) {
                squares[rowTo][colTo].setPiece(pieceTo);
            }
            if (pieceFrom instanceof Pawn) {
                ((Pawn) pieceFrom).hasNotMoved();
            }
            lastMove = !lastMove;
        }
        return false;
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

    /**
     * Creates the board of pieces and adds pieces to each player's currentPieces
     */
    public void createBoard(Player pWhite, Player pBlack) {
        // Fills 2D array with new Square objects of alternating colors
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // Ensures alternating colors for each square adjacent
                if ((i + j) % 2 == 1) {
                    squares[i][j] = new Square(i, j, darkBrown);
                } else {
                    squares[i][j] = new Square(i, j, lightBrown);
                }
            }
        }
        // Creates all the Pieces on the board and passes them in as instance variables for each square

        // Black Pieces
        for (int i = 0; i < 8; i++) {
            squares[1][i].setPiece(new Pawn(Piece.BLACK, 1, i, i * squares[0][0].SQUARE_WIDTH + 100, 175));
        }
        squares[0][0].setPiece(new Rook(Piece.BLACK, 0, 0, 100 + squares[0][0].SQUARE_WIDTH * 0, 100));
        squares[0][1].setPiece(new Knight(Piece.BLACK, 0, 1, 100 + squares[0][0].SQUARE_WIDTH * 1, 100));
        squares[0][2].setPiece(new Bishop(Piece.BLACK, 0, 2, 100 + squares[0][0].SQUARE_WIDTH * 2, 100));
        squares[0][3].setPiece(new Queen(Piece.BLACK, 0, 3, 100 + squares[0][0].SQUARE_WIDTH * 3, 100));
        squares[0][4].setPiece(new King(Piece.BLACK, 0, 4, 100 + squares[0][0].SQUARE_WIDTH * 4, 100));
        squares[0][5].setPiece(new Bishop(Piece.BLACK, 0, 5, 100 + squares[0][0].SQUARE_WIDTH * 5, 100));
        squares[0][6].setPiece(new Knight(Piece.BLACK, 0, 6, 100 + squares[0][0].SQUARE_WIDTH * 6, 100));
        squares[0][7].setPiece(new Rook(Piece.BLACK, 0, 7, 100 + squares[0][0].SQUARE_WIDTH * 7, 100));
        // White pieces
        for (int i = 0; i < 8; i++) {
            squares[6][i].setPiece(new Pawn(Piece.WHITE, 6, i, 100 + squares[0][0].SQUARE_WIDTH * i, 550));
        }
        squares[7][0].setPiece(new Rook(Piece.WHITE, 7, 0, 100 + squares[0][0].SQUARE_WIDTH * 0, 625));
        squares[7][1].setPiece(new Knight(Piece.WHITE, 7, 1, 100 + squares[0][0].SQUARE_WIDTH * 1, 625));
        squares[7][2].setPiece(new Bishop(Piece.WHITE, 7, 2, 100 + squares[0][0].SQUARE_WIDTH * 2, 625));
        squares[7][3].setPiece(new Queen(Piece.WHITE, 7, 3, 100 + squares[0][0].SQUARE_WIDTH * 3, 625));
        squares[7][4].setPiece(new King(Piece.WHITE, 7, 4, 100 + squares[0][0].SQUARE_WIDTH * 4, 625));
        squares[7][5].setPiece(new Bishop(Piece.WHITE, 7, 5, 100 + squares[0][0].SQUARE_WIDTH * 5, 625));
        squares[7][6].setPiece(new Knight(Piece.WHITE, 7, 6, 100 + squares[0][0].SQUARE_WIDTH * 6, 625));
        squares[7][7].setPiece(new Rook(Piece.WHITE, 7, 7, 100 + squares[0][0].SQUARE_WIDTH * 7, 625));
        // Adds each White piece to the ArrayList of White's currentPieces
        for (int row = 6; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                pWhite.addCurrentPiece(squares[row][col].getPiece());
            }
        }
        // Adds each Black piece to the ArrayList of White's currentPieces
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 8; col++) {
                pBlack.addCurrentPiece(squares[row][col].getPiece());
            }
        }
    }
    public boolean kingInCheck(boolean playerColor) {
        ArrayList<Piece> pieces;
        Piece king;
        if (playerColor == Piece.BLACK) {
            pieces = pWhite.getCurrentPieces();
            king = pBlack.findKing();
        }
        else {
            pieces = pBlack.getCurrentPieces();
            king = pWhite.findKing();
        }
        int kingRow = king.getRow();
        int kingCol = king.getCol();
        int pieceCount = pieces.size();
        for (int i = 0; i < pieceCount; i++) {
            Piece currentPiece = pieces.get(i);
            if (currentPiece.isValidJump(kingRow, kingCol, this)) {
                return true;
            }
        }
        return false;
    }
    public boolean isCheckmate(boolean playerColor) {
        if (kingInCheck(playerColor)) {
            // TODO: CODE CHECKMATE
        }
        return false;
    }
}
