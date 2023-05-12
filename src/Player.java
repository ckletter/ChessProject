import java.awt.*;
import java.util.ArrayList;

/**
 * Player class
 * Contains name of player (white/black) and an ArrayList of Pieces the player has taken
 */
public class Player {
    /** Instance variables for name, Pieces taken, and current pieces **/
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
     * Removes the Piece p from the ArrayList of the player's current pieces
     * @param p
     */
    public void removeCurrentPiece(Piece p) {
        int index = currentPieces.indexOf(p);
        currentPieces.remove(index);
    }
    /**
     * Removes the Piece p from the ArrayList of the player's taken pieces
     * @param p
     */
    public void removeTakenPiece(Piece p) {
        int index = piecesTaken.indexOf(p);
        piecesTaken.remove(index);
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
     * Getter method
     * Returns arraylist of the Player's current pieces
     */
    public ArrayList<Piece> getPiecesTaken() {
        return piecesTaken;
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

    /**
     * Draws the difference with a + symbol at the banner of the player who has the advantage
     * @param g
     * @param yValue
     * @param difference
     */
    public void drawDifference(Graphics g, int yValue, int difference) {
        // Determine where to draw the x location of the difference
        int xValue = piecesTaken.size() * 27 + 180;
        g.drawString("+" + difference, xValue, yValue);
    }

    /**
     * Finds the king of the current player from the ArrayList of the player's current pieces
     * @return Piece - player's king
     */
    public Piece findKing() {
        // Loops through each of the player's pieces
        int pieceCount = currentPieces.size();
        for (int i = 0; i < pieceCount; i++) {
            Piece currentPiece = currentPieces.get(i);
            // Returns the piece King once it has been found
            if (currentPiece instanceof King) {
                return currentPiece;
            }
        }
        return null;
    }
}
