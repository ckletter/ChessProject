import java.awt.Graphics;
import java.awt.Image;
/**
 * Extends Piece class
 * Contains draw method specific to the type of Piece
 * To be implemented: Valid moves according to type of Piece
 */
public class Pawn extends Piece {
    private boolean hasMoved;
    /**
     * Constructor
     * Calls constructor of Piece class passing in type of Piece (Pawn)
     * Sets boolean hasMoved to false
     */
    public Pawn(boolean color, int row, int col, int x, int y)
    {
        super(Board.PAWN, color, row, col, x, y);
        hasMoved = false;
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
            g.drawImage(images[Board.PAWN], x, y, Piece.PIECE_TAKEN_WIDTH, Piece.PIECE_TAKEN_WIDTH, viewer);
        }
        else
        {
            g.drawImage(images[Board.PAWN + 6], x, y, Piece.PIECE_TAKEN_WIDTH, Piece.PIECE_TAKEN_WIDTH, viewer);
        }
    }
    public void hasNotMoved() {
        hasMoved = false;
    }
    @Override
    public boolean isValidJump(int row, int col, Board board) {
        // Gets the row and column of the piece's current location
        int pieceRow = super.getRow();
        int pieceCol = super.getCol();
        // Gets the 2D array of squares from board
        Square[][] squares = board.getSquares();
        /// Gets the piece at the square the pawn is moving to
        Piece pieceTo = squares[row][col].getPiece();
        // If the pawn is a black pawn
        if (super.getColor() == Piece.BLACK) {
            // If the pawn moved down one square
            if (row - pieceRow == 1 && pieceCol == col) {
                // If there is no piece where the pawn is moving forward, returns that pawn move is legal
                // Changes hasMoved instance variable to true, indicating that the pawn has now moved
                if (pieceTo == null) {
                    hasMoved = true;
                    return true;
                }
            }
            // If the pawn moved down two squares and has not moved before
            else if (!hasMoved && row - pieceRow == 2 && pieceCol == col) {
                Piece pieceBetween = squares[pieceRow + 1][col].getPiece();
                // If there is no piece where the pawn is moving forward two squares or at the square in between, returns
                // that pawn move is legal.
                // Changes hasMoved instance variable to true, indicating that the pawn has now moved
                if (pieceTo == null && pieceBetween == null) {
                    hasMoved = true;
                    return true;
                }
            }
            // If the pawn moved diagonally down left or right one square
            else if (row - pieceRow == 1 && Math.abs(col - pieceCol) == 1) {
                // If the square that the pawn is moving contains a piece of opposite color, returns that pawn move is legal
                // Changes hasMoved instance variable to true, indicating that the pawn has now moved
                if (pieceTo != null && pieceTo.getColor() == Piece.WHITE) {
                    hasMoved = true;
                    return true;
                }
            }
        }
        // If the pawn is a white pawn
        else {
            // If the pawn moved up one square
            if (pieceRow - row == 1 && pieceCol == col) {
                // If there is no piece where the pawn is moving forward, returns that pawn move is legal
                // Changes hasMoved instance variable to true, indicating that the pawn has now moved
                if (pieceTo == null) {
                    hasMoved = true;
                    return true;
                }
            }
            // If the pawn moved up two squares and has not moved before
            else if (!hasMoved && pieceRow - row == 2 && pieceCol == col) {
                Piece pieceBetween = squares[pieceRow - 1][col].getPiece();
                // If there is no piece where the pawn is moving forward two squares or at the square in between, returns
                // that pawn move is legal.
                // Changes hasMoved instance variable to true, indicating that the pawn has now moved
                if (pieceTo == null && pieceBetween == null) {
                    hasMoved = true;
                    return true;
                }
            }
            // If the pawn moved diagonally up left or right one square
            else if (pieceRow - row == 1 && Math.abs(col - pieceCol) == 1) {
                // If the square that the pawn is moving contains a piece of opposite color, returns that pawn move is legal
                // Changes hasMoved instance variable to true, indicating that the pawn has now moved
                if (pieceTo != null && pieceTo.getColor() == Piece.BLACK) {
                    hasMoved = true;
                    return true;
                }
            }
        }
        // If the pawn move does not fit the valid criteria for a pawn move, returns that pawn move is not legal
        return false;
    }
}
