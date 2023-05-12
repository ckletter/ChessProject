import java.awt.*;
/**
 * Extends Piece class
 * Contains draw method specific to the type of Piece
 * Valid moves according to type of Piece
 */
public class Bishop extends Piece {
    /**
     * Constructor
     * Calls constructor of Piece class passing in type of Piece (Bishop),
     * row and col index of piece, x and y location, and color
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

    /**
     * implements movements particular to a bishop piece
     * @param row - the row index where the player is trying to move to
     * @param col - the col index where the player is trying to move to
     * @param board
     * @return true if the location where the player is trying to move the bishop to is valid
     * (must be no pieces in between bishop's starting location and end location), false otherwise
     */
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
                if (board.piecesBetween(row, col, pieceRow - 1, pieceCol - 1, "UPLEFT")) {
                    return false;
                }
                return true;
            }
            // If the bishop has moved diagonally down right
            else {
                // If there are no pieces in between the bishops initial location and final location, returns true
                // Else, returns false
                if (board.piecesBetween(row, col, pieceRow + 1, pieceCol + 1, "DOWNRIGHT")) {
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
                if (board.piecesBetween(row, col, pieceRow - 1, pieceCol + 1, "UPRIGHT")) {
                    return false;
                }
                return true;
            }
            // if the bishop has moved diagonally down left
            else {
                // If there are no pieces in between the bishops initial location and final location, returns true
                // Else, returns false
                if (board.piecesBetween(row, col, pieceRow + 1, pieceCol - 1, "DOWNLEFT")) {
                    return false;
                }
                return true;
            }
        }
        // If bishop jump is not valid, returns false
        return false;
    }
}
