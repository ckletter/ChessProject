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
    private ArrayList<Piece> currentPieces;
    // Vertical width of the banner
    public static final int BANNER_WIDTH = 50;

    /**
     * Constructor Player
     */
    public Player(String name)
    {
        this.name = name;

        // Initializes taken Pieces ArrayList and current Pieces ArrayList
        piecesTaken = new ArrayList<Piece>();
        currentPieces = new ArrayList<Piece>();
    }

    /**
     * Adds taken piece to array list of Pieces taken
     */
    public void addTakenPiece(Piece p)
    {
        piecesTaken.add(p);
    }
    /**
     * Adds player piece to array list of current pieces the player has
     */
    public void addCurrentPiece(Piece p) {
        currentPieces.add(p);
    }
    /**
     * Getter method
     * Returns name of Player
     */
    public String getName() {
        return name;
    }
    /**
     * Getter method
     * Returns arraylist of the Player's current pieces
     */
    public ArrayList<Piece> getCurrentPieces() {
        return currentPieces;
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
            int xValue = i * 27 + 175;
            // Calls method to draw Piece
            piecesTaken.get(i).draw(g, viewer, xValue, yValue);
        }
    }
    public Piece findKing() {
        int pieceCount = currentPieces.size();
        for (int i = 0; i < pieceCount; i++) {
            Piece currentPiece = currentPieces.get(i);
            if (currentPiece instanceof King) {
                return currentPiece;
            }
        }
        return null;
    }
}
