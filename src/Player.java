import java.util.ArrayList;
import java.awt.Graphics;

/**
 * Player class
 * Contains name of player (white/black) and an ArrayList of Pieces the player has taken
 */
public class Player {
    /** Instance variables for name and Pieces taken **/
    private String name;
    private ArrayList<Piece> piecesTaken;
    // Vertical width of the banner
    public static final int BANNER_WIDTH = 50;

    /**
     * Constructor Player
     */
    public Player(String name)
    {
        this.name = name;

        // Initializes taken Pieces ArrayList
        piecesTaken = new ArrayList<Piece>();
    }

    /**
     * Adds taken piece to array list of Pieces taken
     */
    public void addPiece(Piece p)
    {
        piecesTaken.add(p);
    }

    /**
     * Getter method
     * Returns name of Player
     */
    public String getName() {
        return name;
    }

    /**
     * Draws all taken Pieces over the banner of each player
     */
    public void drawPiecesTaken(Graphics g, ChessGameView viewer, int yValue)
    {
        // Loops through each Piece in the taken Pieces array and tells it to draw itself
        int size = piecesTaken.size();
        for (int i = 0; i < size; i++)
        {
            // Determines x coordinate location to draw the taken Piece
            int xValue = i * 25 + 175;
            // Calls method to draw Piece
            piecesTaken.get(i).draw(g, viewer, xValue, yValue);
        }
    }
}
