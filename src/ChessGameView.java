import javax.swing.*;
import java.awt.*;

/**
 * Class for backend of ChessGame
 * Paint method to draw the entire board
 * Sets size, title, close operation, and visibility of window
 */
public class ChessGameView extends JFrame {
    // Size of window
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 800;
    // Instance of board and images in Resources folder
    private Board b;
    private Image[] images;

    /**
     * Constructor for ChessGameView
     * Puts all images in Resources into an array of Images
     */
    public ChessGameView(Board b)
    {
        this.b = b;
        images = new Image[12];
        // Bishop images
        images[Board.BISHOP] = new ImageIcon("Resources/Chess_bdt60.png").getImage();
        images[Board.BISHOP + 6] = new ImageIcon("Resources/Chess_blt60.png").getImage();
        // Rook images
        images[Board.ROOK] = new ImageIcon("Resources/Chess_rdt60.png").getImage();
        images[Board.ROOK + 6] = new ImageIcon("Resources/Chess_rlt60.png").getImage();
        // Queen images
        images[Board.QUEEN] = new ImageIcon("Resources/Chess_qdt60.png").getImage();
        images[Board.QUEEN + 6] = new ImageIcon("Resources/Chess_qlt60.png").getImage();
        // Pawn images
        images[Board.PAWN] = new ImageIcon("Resources/Chess_pdt60.png").getImage();
        images[Board.PAWN + 6] = new ImageIcon("Resources/Chess_plt60.png").getImage();
        // Knight images
        images[Board.KNIGHT] = new ImageIcon("Resources/Chess_ndt60.png").getImage();
        images[Board.KNIGHT + 6] = new ImageIcon("Resources/Chess_nlt60.png").getImage();
        // King images
        images[Board.KING] = new ImageIcon("Resources/Chess_kdt60.png").getImage();
        images[Board.KING + 6] = new ImageIcon("Resources/Chess_klt60.png").getImage();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Chess");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    /**
     * Getter method
     * Returns array of images in Resources
     */
    public Image[] getImages() {
        return images;
    }

    /**
     * Paints entire board in GUI
     * Loops through each square and tells it to draw itself
     * Draws banner for each player
     */
    public void paint(Graphics g)
    {
        Square[][] squares = b.getSquares();
        // Executes draw method of each square
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                squares[i][j].draw(g, this);
            }
        }
        // Draws banner for each player
        b.getpBlack().draw(g, this);
        b.getpWhite().draw(g, this);
    }
}
