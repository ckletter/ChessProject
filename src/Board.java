import java.awt.*;
import java.util.ArrayList;

/**
 * Board Class
 * Holds a 2D array of Square objects
 * contains methods to (1.) move pieces, (2.) create the board, (3.) check is a move is valid,
 * (4.) check if pieces are between an initial and final location, (5.) check if king is in check,
 * (6.) take back the previous move made, (7.) if checkmate or (8.) stalemate has occurred, (9.) promote a pawn,
 * and (10.) check if the king is still in check after all possible player moves have been made
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
     * Sets boolean lastMove to Black's move, so white move is the first turn to start
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
     * Moves the piece from one spot in the 2D array to the other using row and column parameters
     * If piece has been taken, adds that piece to current player's taken pieces ArrayList
     * and removes it from opponent's current pieces ArrayList
     * Updates the row, col, x, and y location of the piece that has moved
     * Updated boolean lastMove according to the color of the piece moved
     * If the piece moved is a pawn that has promoted, promotes pawn
     */
    public void movePiece(Piece pieceFrom, int colTo, int rowTo) {
        // Gets the Piece at the corresponding square of the 2d array that the rowTo and colTo point to
        Piece pieceTo = squares[rowTo][colTo].getPiece();
        // If the Piece is being moved to a square with another Piece, adds the Piece to the takenPieces array
        // of the player who took the piece
        if (pieceTo != null) {
            // If the piece taken color is black, removes the piece from Black's current pieces ArrayList
            // And adds it to White's taken pieces ArrayList
            if (pieceTo.getColor() == Piece.BLACK) {
                pBlack.removeCurrentPiece(pieceTo);
                pWhite.addTakenPiece(pieceTo);
            }
            // If the piece taken color is white, removes the piece from white's current pieces ArrayList
            // And adds it to black's taken pieces ArrayList
            else {
                pWhite.removeCurrentPiece(pieceTo);
                pBlack.addTakenPiece(pieceTo);
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
        // Sets the player color with the last move played to the color of the piece moved
        lastMove = pieceFrom.getColor();
        // If the piece being moved is a pawn and the pawn has promoted, promotes the pawn
        if (pieceFrom instanceof Pawn && ((Pawn) pieceFrom).hasPromoted()) {
                promotePawn(pieceFrom);
        }
    }

    /**
     * Checks if the user's move being made is valid
     * Makes sure it's the correct player's' turn, the piece is not moving to a square of the same color piece,
     * and that the piece is moving in the correct way pieces of that type move
     * @param pieceFrom - the piece that has been selected
     * @param colTo - the column the piece is being moved to
     * @param rowTo - the row the piece is being moved to
     * @return - true if the move being made is a valid move, false otherwise
     */
    public boolean isValidMove(Piece pieceFrom, int colTo, int rowTo) {
        boolean pieceFromColor = pieceFrom.getColor();
        // If the same color is trying to make two moves consecutively, returns false
        if (pieceFromColor == lastMove) {
            return false;
        }
        // If the move being made is outside the bounds of the board, returns false
        if (colTo < 0 || colTo > 7 || rowTo < 0 || rowTo > 7) {
            return false;
        }
        // Gets the piece where the user is trying to move the piece to
        Piece pieceTo = squares[rowTo][colTo].getPiece();
        // If there is a piece at the square the original piece is being moved to
        if (pieceTo != null) {
            // If the piece is being moved to a square with another piece of the same color, does not move the piece
            if (pieceFromColor == squares[rowTo][colTo].getPiece().getColor()) {
                return false;
            }
        }
        // If the move being made is not a valid jump for the specific piece being moved, returns false
        if (!pieceFrom.isValidJump(rowTo, colTo, this)) {
            return false;
        }
        // Returns that piece being moved is a valid move
        return true;
    }

    /**
     * Recursive method
     * Checks to see if there are any pieces from a starting square to a destination square
     * Continually increases currentRow and currentCol given parameters until destination square is reached
     * @param rowTo - initial row location
     * @param colTo - initial col location
     * @param currentRow - destination row location
     * @param currentCol - destination col location
     * @param direction - string that tells the function to move up, down, left, right, or diagonally
     * @return - Returns true if there are any pieces at squares between the starting square and destionation square
     */
    public boolean piecesBetween(int rowTo, int colTo, int currentRow, int currentCol, String direction) {
        // Base case - the destination square and current square point to the same location,
        // (if has reached, no pieces between initial and final location)
        if (rowTo == currentRow && colTo == currentCol) {
            return false;
        }
        Piece thisPiece = squares[currentRow][currentCol].getPiece();
        // If there is a piece at the square currentRow, currentCol, returns true (that there is a piece between)
        if (thisPiece != null) {
            return true;
        }
        // If direction says to go up, decreases currentRow by 1
        if (direction.contains("UP")) {
            currentRow--;
        }
        // If direction says to go left, decreases currentCol by 1
        if (direction.contains("LEFT")) {
            currentCol--;
        }
        // If direction says to go down, increases currentRow by 1
        if (direction.contains("DOWN")) {
            currentRow++;
        }
        // If direction says to go right, increases currentRow by 1
        if (direction.contains("RIGHT")) {
            currentCol++;
        }
        // Recursive call: now that currentRow and currentCol are changed, calls the function again with new parameters
        return piecesBetween(rowTo, colTo, currentRow, currentCol, direction);
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
            squares[1][i].setPiece(new Pawn(Piece.BLACK, 1, i, i * Square.SQUARE_WIDTH + 100, 175));
        }
        squares[0][0].setPiece(new Rook(Piece.BLACK, 0, 0, 100 + Square.SQUARE_WIDTH * 0, 100));
        squares[0][1].setPiece(new Knight(Piece.BLACK, 0, 1, 100 + Square.SQUARE_WIDTH * 1, 100));
        squares[0][2].setPiece(new Bishop(Piece.BLACK, 0, 2, 100 + Square.SQUARE_WIDTH * 2, 100));
        squares[0][3].setPiece(new Queen(Piece.BLACK, 0, 3, 100 + Square.SQUARE_WIDTH * 3, 100));
        squares[0][4].setPiece(new King(Piece.BLACK, 0, 4, 100 + Square.SQUARE_WIDTH * 4, 100));
        squares[0][5].setPiece(new Bishop(Piece.BLACK, 0, 5, 100 + Square.SQUARE_WIDTH * 5, 100));
        squares[0][6].setPiece(new Knight(Piece.BLACK, 0, 6, 100 + Square.SQUARE_WIDTH * 6, 100));
        squares[0][7].setPiece(new Rook(Piece.BLACK, 0, 7, 100 + Square.SQUARE_WIDTH * 7, 100));
        // White pieces
        for (int i = 0; i < 8; i++) {
            squares[6][i].setPiece(new Pawn(Piece.WHITE, 6, i, 100 + Square.SQUARE_WIDTH * i, 550));
        }
        squares[7][0].setPiece(new Rook(Piece.WHITE, 7, 0, 100 + Square.SQUARE_WIDTH * 0, 625));
        squares[7][1].setPiece(new Knight(Piece.WHITE, 7, 1, 100 + Square.SQUARE_WIDTH * 1, 625));
        squares[7][2].setPiece(new Bishop(Piece.WHITE, 7, 2, 100 + Square.SQUARE_WIDTH * 2, 625));
        squares[7][3].setPiece(new Queen(Piece.WHITE, 7, 3, 100 + Square.SQUARE_WIDTH * 3, 625));
        squares[7][4].setPiece(new King(Piece.WHITE, 7, 4, 100 + Square.SQUARE_WIDTH * 4, 625));
        squares[7][5].setPiece(new Bishop(Piece.WHITE, 7, 5, 100 + Square.SQUARE_WIDTH * 5, 625));
        squares[7][6].setPiece(new Knight(Piece.WHITE, 7, 6, 100 + Square.SQUARE_WIDTH * 6, 625));
        squares[7][7].setPiece(new Rook(Piece.WHITE, 7, 7, 100 + Square.SQUARE_WIDTH * 7, 625));
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

    /**
     * Checks whether the king of the player color passed in is currently in check
     * Iterates through each of the opponent's pieces and sees if any of them look at the king's square
     * @param playerColor
     * @return true if the player's king is currently in check (another piece looks  at the king's square),
     * false otherwise
     */
    public boolean kingInCheck(boolean playerColor) {
        ArrayList<Piece> pieces;
        Piece king;
        // Gets the opponent's ArrayList current pieces and the player's king
        if (playerColor == Piece.BLACK) {
            pieces = pWhite.getCurrentPieces();
            king = pBlack.findKing();
        }
        else {
            pieces = pBlack.getCurrentPieces();
            king = pWhite.findKing();
        }
        // Gets the player's king current row and column indexes
        int kingRow = king.getRow();
        int kingCol = king.getCol();
        int pieceCount = pieces.size();
        // For each of the opponent's pieces
        for (int i = 0; i < pieceCount; i++) {
            Piece currentPiece = pieces.get(i);
            // If the opponent's piece is looking at the king's square, returns true (that king is in check)
            if (currentPiece.isValidJump(kingRow, kingCol, this)) {
                return true;
            }
        }
        // If all of the opponent's pieces do not look at the king's square, returns false
        return false;
    }

    /**
     * Looks for checkmate
     * @param playerColor
     * @return true if the player passed in's king has been checkmated, false otherwise
     */
    public boolean isCheckmate(boolean playerColor) {
        // If the king is currently in check and all of the player's valid moves lead to the king still being in check,
        // Returns true
        if (kingInCheck(playerColor) && kingStillInCheck(playerColor)) {
            return true;
        }
        // Returns false if there is not checkmate
        return false;
    }

    /**
     * Resets the last move that has been played
     * @param pieceFrom
     * @param pieceTo
     * @param prevCol
     * @param prevRow
     */
    public void moveReset(Piece pieceFrom, Piece pieceTo, int prevCol, int prevRow) {
        // Gets the piece's current row and column
        int rowTo = pieceFrom.getRow();
        int colTo = pieceFrom.getCol();
        // Moves the piece back to its initial location
        movePiece(pieceFrom, prevCol, prevRow);
        // If there was a piece taken, resets the piece at the square on the board
        // Adds the taken piece back to the ArrayList of the player's pieces and removes it from the opponent's taken pieces
        if (pieceTo != null) {
            squares[rowTo][colTo].setPiece(pieceTo);
            if (pieceTo.getColor() == Piece.BLACK) {
                pBlack.addCurrentPiece(pieceTo);
                pWhite.removeTakenPiece(pieceTo);
            }
            else {
                pWhite.addCurrentPiece(pieceTo);
                pBlack.removeTakenPiece(pieceTo);
            }
        }
        // If the piece moved was a pawn, when resetting the move, sets the pawn to hasNotMoved
        if (pieceFrom instanceof Pawn) {
            ((Pawn) pieceFrom).hasNotMoved();
        }
        // Sets the lastMove played to the opponent's turn
        lastMove = !lastMove;
    }

    /**
     * Checks to see if the king is still in check despite all of a player's possible moves
     * Helps determine checkmate
     * @param playerColor
     * @return true if after all of a player's possible moves on the board are made, the king is still in check,
     * false otherwise
     */
    public boolean kingStillInCheck(boolean playerColor) {
        // Gets all of the player's current pieces still on the board
        ArrayList<Piece> playerPieces;
        if (playerColor == Piece.WHITE) {
            playerPieces = pWhite.getCurrentPieces();
        }
        else {
            playerPieces = pBlack.getCurrentPieces();
        }
        // For each of the player's pieces
        for (int i = 0; i < playerPieces.size(); i++) {
            Piece currentPiece = playerPieces.get(i);
            // Gets the row and column of the current piece
            int prevCol = currentPiece.getCol();
            int prevRow = currentPiece.getRow();
            // Retrieves the ArrayList of possibleSquares that the piece looks at
            ArrayList<Square> possibleSquares = currentPiece.getPossibleSquares(this);
            // For each of the piece's possible squares it looks at
            for (int j = 0; j < possibleSquares.size(); j++) {
                Square currentSquare = possibleSquares.get(j);
                Piece pieceTo = currentSquare.getPiece();
                int squareRow = currentSquare.getRow();
                int squareCol = currentSquare.getCol();
                // Moves the piece to the current square
                movePiece(currentPiece, squareCol, squareRow);
                // If the king is no longer in check, returns false and resets the move
                if (!kingInCheck(playerColor)) {
                    moveReset(currentPiece, pieceTo, prevCol, prevRow);
                    return false;
                }
                // Resets the move that was just made
                moveReset(currentPiece, pieceTo, prevCol, prevRow);
            }
        }
        // Returns true if after all of a player's legal moves, the king is still in check
        return true;
    }

    /**
     * Checks for stalemate
     * @param playerColor
     * @return true if the player is in stalemate, false otherwise
     */
    public boolean isStalemate(boolean playerColor) {
        // If the player's king is not currently in check and every one of the player's legal moves would result
        // In the king being in check, returns that stalemate is true
        if (!kingInCheck(playerColor) && kingStillInCheck(playerColor)) {
            return true;
        }
        // Returns false if stalemate has not been reached
        return false;
    }

    /**
     * Determines the advantage based off the material count of each of the pieces
     * @param g
     */
    public void determineAdvantage(Graphics g) {
        // Gets white and black's current pieces on the board
        ArrayList<Piece> blackCurrentPieces = pBlack.getCurrentPieces();
        ArrayList<Piece> whiteCurrentPieces = pWhite.getCurrentPieces();
        int blackTotal = 0;
        int whiteTotal = 0;
        // Sums the total material value of black's pieces on the board
        for (int i = 0; i < blackCurrentPieces.size(); i++) {
            blackTotal += blackCurrentPieces.get(i).getValue();
        }
        // Sums the total material value of white's pieces on the board
        for (int i = 0; i < whiteCurrentPieces.size(); i++) {
            whiteTotal += whiteCurrentPieces.get(i).getValue();
        }
        // If white's material value is greater than black's, draws the difference on white's banner
        if (whiteTotal > blackTotal) {
            pWhite.drawDifference(g,  730, whiteTotal - blackTotal);
        }
        // If black's material value is greater than white's, draws the difference on black's banner
        else if (whiteTotal < blackTotal) {
            pBlack.drawDifference(g,  80, blackTotal - whiteTotal);
        }
    }

    /**
     * Promotes a pawn to a queen once it has reached the end of the board
     * @param pawn - takes in a pawn and promotes it to a queen
     */
    public void promotePawn(Piece pawn) {
        boolean pawnColor = pawn.getColor();
        // Gets the row and column indexes of the pawn
        int row = pawn.getRow();
        int col = pawn.getCol();
        // Makes a new queen and puts it at the position of where the pawn once was
        Queen newQueen = new Queen(pawnColor, row, col, 100 + Square.SQUARE_WIDTH * col, 100 + Square.SQUARE_WIDTH * row);
        squares[row][col].setPiece(newQueen);
        // Removes the pawn from the player's current pieces and adds the new queen to the player's current pieces
        if (pawnColor == Piece.BLACK) {
            pBlack.getCurrentPieces().remove(pawn);
            pBlack.addCurrentPiece(newQueen);
        }
        else {
            pWhite.getCurrentPieces().remove(pawn);
            pWhite.addCurrentPiece(newQueen);
        }
    }
}
