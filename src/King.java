import java.awt.*;
/**
 * Extends Piece class
 * Contains draw method specific to the type of Piece
 * Valid moves according to type of Piece
 */
public class King extends Piece {
    /** Instance variable hasMoved for if king has moved **/
    private boolean hasMoved;
    /**
     * Constructor
     * Calls constructor of Piece class passing in type of Piece (King), row and col index of piece,
     * x and y location, and color
     */
    public King(boolean color, int row, int col, int x, int y) {
        super(Board.KING, color, row, col, x, y);
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
            g.drawImage(images[Board.KING], x, y, Piece.PIECE_TAKEN_WIDTH, Piece.PIECE_TAKEN_WIDTH, viewer);
        }
        else
        {
            g.drawImage(images[Board.KING + 6], x, y, Piece.PIECE_TAKEN_WIDTH, Piece.PIECE_TAKEN_WIDTH, viewer);
        }
    }
    /**
     * implements movements particular to a king piece
     * If king move is valid, sets boolean hasMoved to true
     * @param row - the row index where the player is trying to move the king to
     * @param col - the col index where the player is trying to move the king to
     * @param board
     * @return true if the location where the player is trying to move the king to is one square in any direction,
     * false otherwise
     */
    @Override
    public boolean isValidJump(int row, int col, Board board) {
        // Gets the row and column of the piece's current location
        int pieceRow = super.getRow();
        int pieceCol = super.getCol();
        // If the king has moved left or right one square but not up or down, returns that the king move is a valid jump
        if (pieceRow == row && Math.abs(pieceCol - col) == 1) {
            hasMoved = true;
            return true;
        }
        // If the king has moved up or down one square but not left or right, returns that the king move is a valid jump
        if (pieceCol == col && Math.abs(pieceRow - row) == 1) {
            hasMoved = true;
            return true;
        }
        // If the king has moved one square diagonally in any direction, returns that the king move is a valid jump
        if ((Math.abs(pieceCol - col) == 1 && Math.abs(pieceRow - row) == 1)) {
            hasMoved = true;
            return true;
        }
        // If the king jump is not valid, returns false
        return false;
    }

    /**
     * Getter method
     * returns boolean hasMoved
     */
    public boolean hasMoved() {
        return hasMoved;
    }
}
