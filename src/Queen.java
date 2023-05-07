import java.awt.*;
/**
 * Extends Piece class
 * Contains draw method specific to the type of Piece
 * To be implemented: Valid moves according to type of Piece
 */
public class Queen extends Piece {
    /**
     * Constructor
     * Calls constructor of Piece class passing in type of Piece (Queen)
     */
    public Queen(boolean color, int row, int col, int x, int y) {
        super(Board.QUEEN, color, row, col, x, y);
    }
    /**
     * Draw method specific to type of Piece
     * Draws corresponding image at specified x and y location passed in
     * Overrides Piece's abstract method draw
     */
    @Override
    public void draw(Graphics g, ChessGameView viewer, int x, int y)
    {
        // Gets all images from resources folder
        Image[] images = viewer.getImages();
        // Draws image of Piece with correct color
        if (super.getColor() == Piece.BLACK)
        {
            g.drawImage(images[Board.QUEEN], x, y, Piece.PIECE_TAKEN_WIDTH, Piece.PIECE_TAKEN_WIDTH, viewer);
        }
        else
        {
            g.drawImage(images[Board.QUEEN + 6], x, y, Piece.PIECE_TAKEN_WIDTH, Piece.PIECE_TAKEN_WIDTH, viewer);
        }
    }

    @Override
    public boolean isValidJump(int row, int col, Board board) {
        // Gets the row and column of the piece's current location
        int pieceRow = super.getRow();
        int pieceCol = super.getCol();
        // Gets the 2D array of squares from board
        Square[][] squares = board.getSquares();
        // If the queen has moved horizontally
        if (pieceRow == row && pieceCol != col) {
            // If the queen has moved right
            if (pieceCol < col) {
                // If there are no pieces in between the queens initial location and final location, returns true
                // Else, returns false
                if (board.piecesBetween(row, col, pieceRow, pieceCol + 1, "RIGHT", squares)) {
                    return false;
                }
                return true;
            }
            // If the queen has moved left
            else {
                // If there are no pieces in between the queens initial location and final location, returns true
                // Else, returns false
                if (board.piecesBetween(row, col, pieceRow, pieceCol - 1, "LEFT", squares)) {
                    return false;
                }
                return true;
            }
        }
        // If the queen has moved vertically
        if (pieceCol == col && pieceRow != row) {
            // If the queen has moved down
            if (pieceRow < row) {
                // If there are no pieces in between the queens initial location and final location, returns true
                // Else, returns false
                if (board.piecesBetween(row, col, pieceRow + 1, pieceCol, "DOWN", squares)) {
                    return false;
                }
                return true;
            }
            // If the queen has moved up
            else {
                // If there are no pieces in between the queens initial location and final location, returns true
                // Else, returns false
                if (board.piecesBetween(row, col, pieceRow - 1, pieceCol, "UP", squares)) {
                    return false;
                }
                return true;
            }
        }
        // If the queen has moved diagonally up left or down right
        if (pieceRow - row == pieceCol - col) {
            // if the queen has moved diagonally up left
            if (pieceRow > row) {
                // If there are no pieces in between the queens initial location and final location, returns true
                // Else, returns false
                if (board.piecesBetween(row, col, pieceRow - 1, pieceCol - 1, "UPLEFT", squares)) {
                    return false;
                }
                return true;
            }
            // If the queen has moved diagonally down right
            else {
                // If there are no pieces in between the queens initial location and final location, returns true
                // Else, returns false
                if (board.piecesBetween(row, col, pieceRow + 1, pieceCol + 1, "DOWNRIGHT", squares)) {
                    return false;
                }
                return true;
            }
        }
        // If the queen has moved diagonally down left or up right
        if (pieceRow - row == -(pieceCol - col)) {
            // if the queen has moved diagonally up right
            if (pieceRow > row) {
                // If there are no pieces in between the queens initial location and final location, returns true
                // Else, returns false
                if (board.piecesBetween(row, col, pieceRow - 1, pieceCol + 1, "UPRIGHT", squares)) {
                    return false;
                }
                return true;
            }
            // if the queen has moved diagonally down left
            else {
                // If there are no pieces in between the queens initial location and final location, returns true
                // Else, returns false
                if (board.piecesBetween(row, col, pieceRow + 1, pieceCol - 1, "DOWNLEFT", squares)) {
                    return false;
                }
                return true;
            }
        }
        // If the queen has not moved horizontally, vertically, or diagonally, returns that queen has not made a valid move
        return false;
    }
}
