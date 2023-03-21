import java.awt.*;

/**
 * Class for white player (extends Player class)
 * Contains draw method for the White banner
 */
public class White extends Player {
    /**
     * Constructor
     * Calls constructor of Player superclass and passes in name
     */
    public White(String name)
    {
        super(name);
    }

    /**
     * Draw method for White banner
     * Includes banner background, player name, and image of each piece taken
     */
    public void draw(Graphics g, ChessGameView viewer)
    {
        // Starting x and y location for the White banner
        int startX = 100;
        int startY = 700;
        g.setColor(Board.darkBrown);
        // Draws banner background
        g.fillRect(startX, startY, Board.BOARD_LENGTH, Player.BANNER_WIDTH);
        g.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
        // Draws player name
        g.setColor(Color.white);
        g.drawString(super.getName(), startX + 10, startY + 35);
        // Calls method to draw each piece taken by White over the banner
        super.drawPiecesTaken(g, viewer, 55);
    }
}
