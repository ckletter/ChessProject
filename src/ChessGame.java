import java.awt.*;
import java.util.ArrayList;

/**
 * Chess Game - Cody Kletter 5/10/23
 * Operates a chess game and user turns with GUI window
 */
public class ChessGame {
    /** Instance Variables **/
    private Board board;
    private ChessGameView window;
    private boolean winner;

    /** Constructor for ChessGame
     * Instantiates the isWin boolean, Board class, and ChessGameView class
     */
    public ChessGame()
    {
        board = new Board();
        window = new ChessGameView(board, this);
    }

    /**
     * Plays out a full userTurn once a piece has been dragged and dropped onto a square
     * @param pieceFrom - the piece being moved
     * @param colTo - the destination column of the piece
     * @param rowTo - the destination row of the piece
     * @return true if the move being played is valid, false otherwise
     */
    public boolean userTurn(Piece pieceFrom, int colTo, int rowTo) {
        boolean pieceFromColor = pieceFrom.getColor();
        // Gets the Piece at the corresponding square of the 2d array that the rowTo and colTo point to
        Piece pieceTo = board.getSquares()[rowTo][colTo].getPiece();
        int prevRow = pieceFrom.getRow();
        int prevCol = pieceFrom.getCol();
        // If the player is trying to castle and there are no pieces between, castles and returns true (valid move)
        if (canCastle(pieceFrom, colTo, rowTo)) {
            board.movePiece(pieceFrom, colTo, rowTo);
            return true;
        }
        // If the move being played is a valid move
        if (board.isValidMove(pieceFrom, colTo, rowTo)) {
            board.movePiece(pieceFrom, colTo, rowTo);
            // Once the piece has been moved, is there is now a check on the king whose piece has been moved,
            // Reverts the move back and resets the piece taken if any piece was taken
            // Returns false (move invalid)
            if (board.kingInCheck(pieceFromColor)) {
                board.moveReset(pieceFrom, pieceTo, prevCol, prevRow);
                return false;
            }
            // Once move has been made and the king is not now in check, checks for checkmate
            if (board.isCheckmate(!pieceFromColor)) {
                winner = pieceFromColor;
                // Sets window screen to checkmate in viewer class
                window.setState(ChessGameView.CHECKMATE);
                window.repaint();
            }
            // Once move has been made and the king is not now in check, checks for stalemate
            if (board.isStalemate(!pieceFromColor)) {
                // Sets window screen to stalemate in viewer class
                window.setState(ChessGameView.STALEMATE);
                window.repaint();
            }
            return true;
        }
        // Returns false if the move being played is invalid
        return false;
    }
    /** Getter methods **/
    public Board getBoard()
    {
        return board;
    }

    public boolean getWinner() {
        return winner;
    }
    /** Setter method
     * Sets a new board once there is a new game being played
     **/
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Determines whether the player is trying to castle and whether or not it's a valid possible move
     * @param pieceFrom
     * @param colTo
     * @param rowTo
     * @return true if there are no pieces between the king and the rook
     * and the king hasn't moved; false otherwise
     */
    public boolean canCastle(Piece pieceFrom, int colTo, int rowTo) {
        Square[][] squares = board.getSquares();
        // If the piece being moved is not a king, returns false
        if (!(pieceFrom instanceof King)) {
            return false;
        }
        // If the king has already moved, returns false
        if (((King) pieceFrom).hasMoved()) {
            return false;
        }
        // Gets the king's row and column indexes
        int kingRow = pieceFrom.getRow();
        int kingCol = pieceFrom.getCol();
        // If the king is at the white king starting location
        if (kingRow == 7) {
            // If castling to the left, returns true if there are no pieces between (valid castle move)
            if (colTo == 6 && rowTo == 7) {
                if (!board.piecesBetween(rowTo, colTo + 1, kingRow, kingCol + 1, "RIGHT")) {
                    board.movePiece(squares[7][7].getPiece(), 5, 7);
                    return true;
                }
            }
            // If castling to the right, returns true if there are no pieces between (valid castle move)
            else if (colTo == 2 && rowTo == 7) {
                if (!board.piecesBetween(rowTo, colTo - 2, kingRow, kingCol - 1, "LEFT")) {
                    board.movePiece(squares[7][0].getPiece(), 3, 7);
                    return true;
                }
            }
        }
        // If king is at black king starting location
        else if (kingRow == 0) {
            // If castling to the right, returns true if no pieces between (valid castle move)
            if (colTo == 6 && rowTo == 0) {
                if (!board.piecesBetween(rowTo, colTo + 1, kingRow, kingCol + 1, "RIGHT")) {
                    board.movePiece(squares[0][7].getPiece(), 5, 0);
                    return true;
                }
            }
            // If castling to the left, returns true if no pieces between (valid castle move)
            else if (colTo == 2 && rowTo == 0) {
                if (!board.piecesBetween(rowTo, colTo - 2, kingRow, kingCol - 1, "LEFT")) {
                    board.movePiece(squares[0][0].getPiece(), 3, 0);
                    return true;
                }
            }
        }
        // Returns false if the player is not trying to castle or castling is not a valid move for the opponent
        return false;
    }
    /** Main method */
    public static void main(String[] args) {
        // Instantiates the class ChessGame
        ChessGame cg = new ChessGame();
    }
}