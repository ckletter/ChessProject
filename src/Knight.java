import java.awt.*;
/**
 * Extends Piece class
 * Contains draw method specific to the type of Piece
 * To be implemented: Valid moves according to type of Piece
 */
public class Knight extends Piece {
    /**
     * Constructor
     * Calls constructor of Piece class passing in type of Piece (Knight)
     */
    public Knight(boolean color)
    {
        super(Board.KNIGHT, color);
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
}
