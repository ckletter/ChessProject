import java.awt.Graphics;

/**
 * Piece class
 * Contains the color, type, name, and value of Piece
 * Superclass for each individual Piece
 * Contains abstract draw method to be overriden by each Piece subclass
 */
public class Piece {
    // Width of each taken piece to be drawn in the GUI
    public static final int PIECE_TAKEN_WIDTH = 40;
    // Instance variables for color, type, name, and value of each Piece
    private int value;
    private int type;
    private boolean color;
    private String name;
    // Static variables for color
    public static final boolean WHITE = false;
    public static final boolean BLACK = true;

    /**
     * Constructor for Piece
     * instantiates the type and the color
     * Sets the value and name of the piece corresponding to its type
     */
    public Piece(int type, boolean color)
    {
        this.type = type;
        this.color = color;
        switch (type) {
            case Board.ROOK:
                value = 5;
                name = "Rook";
                break;
            case Board.PAWN:
                value = 1;
                name = "Pawn";
                break;
            case Board.BISHOP:
                value = 3;
                name = "Bishop";
                break;
            case Board.KNIGHT:
                value = 3;
                name = "Knight";
                break;
            case Board.QUEEN:
                value = 9;
                name = "Queen";
                break;
            case Board.KING:
                value = 0;
                name = "King";
                break;
            default:
                break;
        }
    }

    /**
     * Getter method
     * Returns string of name of Piece
     */
    public String toString()
    {
        return name;
    }
    /**
     * Getter method
     * Returns boolean for color of piece
     */
    public boolean getColor() {
        return color;
    }

    /**
     * Abstract method
     * Draw method overriden by each subclass of Piece
     */
    public void draw(Graphics g, ChessGameView viewer, int x, int y) {}
}
