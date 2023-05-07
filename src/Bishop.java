import java.awt.*;
/**
 * Extends Piece class
 * Contains draw method specific to the type of Piece
 * To be implemented: Valid moves according to type of Piece
 */
public class Bishop extends Piece {
    /**
     * Constructor
     * Calls constructor of Piece class passing in type of Piece (Bishop)
     */
    public Bishop(boolean color, int row, int col, int x, int y) {
        super(Board.BISHOP, color, row, col, x, y);
    }
    /**
     * Draw method specific to type of Piece
     * Draws corresponding image at specified x and y location passed in
     * Overrides Piece's abstract method draw
     */
    @Override
    public void draw(Graphics g, ChessGameView viewer, int x, int y)
    {
        // Gets all images from Resources folder
        Image[] images = viewer.getImages();
        // Draws image of Piece with correct color
        if (super.getColor() == Piece.BLACK)
        {
            g.drawImage(images[Board.BISHOP], x, y, Piece.PIECE_TAKEN_WIDTH, Piece.PIECE_TAKEN_WIDTH, viewer);
        }
        else
        {
            g.drawImage(images[Board.BISHOP + 6], x, y, Piece.PIECE_TAKEN_WIDTH, Piece.PIECE_TAKEN_WIDTH, viewer);
        }
    }
    @Override
    public boolean isValidJump(int row, int col, Board board) {
        // Gets the row and column of the piece's current location
        int pieceRow = super.getRow();
        int pieceCol = super.getCol();
        Square[][] squares = board.getSquares();
        // If the bishop has moved diagonally up left or down right
        if (pieceRow - row == pieceCol - col) {
            // if the bishop has moved diagonally up left
            if (pieceRow > row) {
                // If there are no pieces in between the bishops initial location and final location, returns true
                // Else, returns false
                if (board.piecesBetween(row, col, pieceRow - 1, pieceCol - 1, "UPLEFT", squares)) {
                    return false;
                }
                return true;
            }
            // If the bishop has moved diagonally down right
            else {
                // If there are no pieces in between the bishops initial location and final location, returns true
                // Else, returns false
                if (board.piecesBetween(row, col, pieceRow + 1, pieceCol + 1, "DOWNRIGHT", squares)) {
                    return false;
                }
                return true;
            }
        }
        // If the bishop has moved diagonally down left or up right
        if (pieceRow - row == -(pieceCol - col)) {
            // if the queen has moved diagonally up right
            if (pieceRow > row) {
                // If there are no pieces in between the bishops initial location and final location, returns true
                // Else, returns false
                if (board.piecesBetween(row, col, pieceRow - 1, pieceCol + 1, "UPRIGHT", squares)) {
                    return false;
                }
                return true;
            }
            // if the bishop has moved diagonally down left
            else {
                // If there are no pieces in between the bishops initial location and final location, returns true
                // Else, returns false
                if (board.piecesBetween(row, col, pieceRow + 1, pieceCol - 1, "DOWNLEFT", squares)) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }
}
