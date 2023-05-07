import java.awt.*;

/**
 * Extends Piece class
 * Contains draw method specific to the type of Piece
 * To be implemented: Valid moves according to type of Piece
 */
public class Rook extends Piece {
    /**
     * Constructor
     * Calls constructor of Piece class passing in type of Piece (Rook)
     */
    public Rook(boolean color, int row, int col, int x, int y)
    {
        super(Board.ROOK, color, row, col, x, y);
    }
    /**
     * Draw method specific to type of Piece
     * Draws corresponding image at specified x and y location passed in
     * Overrides Piece's abstract method draw
     */
    @Override
    public void draw(Graphics g, ChessGameView viewer, int x, int y)
    {
        // Gets all images in Resources folder
        Image[] images = viewer.getImages();
        // Draws image of Piece with correct color
        if (super.getColor() == Piece.BLACK)
        {
            g.drawImage(images[Board.ROOK], x, y, Piece.PIECE_TAKEN_WIDTH, Piece.PIECE_TAKEN_WIDTH, viewer);
        }
        else
        {
            g.drawImage(images[Board.ROOK + 6], x, y, Piece.PIECE_TAKEN_WIDTH, Piece.PIECE_TAKEN_WIDTH, viewer);
        }
    }
    @Override
    public boolean isValidJump(int row, int col, Board board) {
        // Gets the row and column of the piece's current location
        int pieceRow = super.getRow();
        int pieceCol = super.getCol();
        // Gets the 2D array of squares from board
        Square[][] squares = board.getSquares();
        // If the rook has moved horizontally
        if (pieceRow == row && pieceCol != col) {
            // If there are no pieces in between the rooks initial location and final location, returns true
            // Else, returns false
            for (int i = Math.min(pieceCol, col) + 1; i < Math.max(pieceCol, col); i++) {
                if (squares[pieceRow][i].getPiece() != null) {
                    return false;
                }
            }
            return true;
        }
        // If the rook has moved vertically
        if (pieceCol == col && pieceRow != row) {
            // If there are no pieces in between the rooks initial location and final location, returns true
            // Else, returns false
            for (int i = Math.min(pieceRow, row) + 1; i < Math.max(pieceRow, row); i++) {
                if (squares[i][pieceCol].getPiece() != null) {
                    return false;
                }
            }
            return true;
        }
        // If the rook has not moved horizontally or vertically, returns false
        return false;
    }
}
