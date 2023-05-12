import java.awt.*;
/**
 * Extends Piece class
 * Contains draw method specific to the type of Piece
 * Valid moves according to type of Piece
 */
public class Knight extends Piece {
    /**
     * Constructor
     * Calls constructor of Piece class passing in type of Piece (Knight)
     * row and col index of piece, x and y location, and color
     */
    public Knight(boolean color, int row, int col, int x, int y)
    {
        super(Board.KNIGHT, color, row, col, x, y);
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
            g.drawImage(images[Board.KNIGHT], x, y, Piece.PIECE_TAKEN_WIDTH, Piece.PIECE_TAKEN_WIDTH, viewer);
        }
        else
        {
            g.drawImage(images[Board.KNIGHT + 6], x, y, Piece.PIECE_TAKEN_WIDTH, Piece.PIECE_TAKEN_WIDTH, viewer);
        }
    }
    /**
     * implements movements particular to a knight piece
     * @param row - the row index where the player is trying to move to
     * @param col - the col index where the player is trying to move to
     * @param board
     * @return true if the location where the player is trying to move the knight to is valid, false otherwise
     */
    @Override
    public boolean isValidJump(int row, int col, Board board) {
        // Gets the row and column of the piece's current location
        int pieceRow = super.getRow();
        int pieceCol = super.getCol();
        // If the knight has moved to the left or right one square and up or down two squares, returns knight move is legal
        if (Math.abs(pieceRow - row) == 1 && Math.abs(pieceCol - col) == 2) {
            return true;
        }
        // If the knight has moved to the left or right two squares and up or down one square, returns knight move is legal
        if (Math.abs(pieceCol - col) == 1 && Math.abs(pieceRow - row) == 2) {
            return true;
        }
        // Returns that jump is invalid
        return false;
    }
}
