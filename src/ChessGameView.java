import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

/**
 * Class for backend of ChessGame
 * Paint method to draw the entire board
 * Sets size, title, close operation, and visibility of window
 */
public class ChessGameView extends JFrame implements MouseListener, MouseMotionListener {
    // Size of window
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 800;
    // Instance of board and images in Resources folder
    private Board b;
    private Image[] images;
    private Piece pieceMoved;

    /**
     * Constructor for ChessGameView
     * Puts all images in Resources into an array of Images
     */
    public ChessGameView(Board b)
    {
        this.b = b;
        images = new Image[13];
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
        images[12] = new ImageIcon("Resources/background.png").getImage();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Chess");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        this.pieceMoved = null;
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
        // Sets color to white and clears the entire board
        g.setColor(Color.white);
        g.fillRect(0, 0, 1000, 800);
        // Draws background
        g.drawImage(images[12], 100, 50, 600, 700, this);
        Square[][] squares = b.getSquares();
        // Executes draw method of each square
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                squares[i][j].draw(g, this);
            }
        }
        // If a piece is currently being moved, re-draws it again so that it is superimposed over the whole board
        if (pieceMoved != null) {
            squares[pieceMoved.getRow()][pieceMoved.getCol()].draw(g, this);
        }
        // Draws banner for each player
        b.getpBlack().draw(g, this);
        b.getpWhite().draw(g, this);
        // Tells system to do the painting of the GUI before all other methods
        Toolkit.getDefaultToolkit().sync();
    }
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        // If the mouse location is within the confines of the actual chess board
        if (x >= 100 && x < 700 && y >= 100 && y < 700) {
            // Gets the row (0-7) of the piece being selected
            int boardRow = (y - 100) / Square.SQUARE_WIDTH;
            // Gets the row (0-7) of the piece being selected
            int boardCol = (x - 100) / Square.SQUARE_WIDTH;
            pieceMoved = b.getSquares()[boardRow][boardCol].getPiece();
        }
    }
    public void mouseReleased(MouseEvent e) {
        // Gets the x and y location of the mouse
        int x = e.getX();
        int y = e.getY();
        // If the mouse location is within the confines of the actual chess board and a piece is currently being moved
        if (x >= 100 && x < 700 && y >= 100 && y < 700 && pieceMoved != null) {
            // Gets the row (0-7) of the piece being released
            int boardRow = (y - 100) / Square.SQUARE_WIDTH;
            // Gets the row (0-7) of the piece being released
            int boardCol = (x - 100) / Square.SQUARE_WIDTH;
            // If chess rules allow for the piece to be moved to the square legally, moves it
            if (b.userTurn(pieceMoved, boardCol, boardRow)) {
                pieceMoved = null;
                repaint();
            }
            // If the move being made is not legal, places the piece selected back in its original square and does not move the piece
            else {
                pieceMoved.setPosition(pieceMoved.getCol() * Square.SQUARE_WIDTH + 100, pieceMoved.getRow() * Square.SQUARE_WIDTH + 100);
                pieceMoved = null;
                repaint();
            }
        }
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {
        // Gets the x and y location of the mouse
        int x = e.getX();
        int y = e.getY();
        // If the mouse location is within the confines of the actual chess board
        if (x >= 100 && x <= 700 && y >= 100 && y <= 700 && pieceMoved != null) {
            pieceMoved.setPosition(x - (Square.SQUARE_WIDTH / 2), y - (Square.SQUARE_WIDTH / 2));
            repaint();
        }
        // If the mouse goes off the chess board and a piece is currently selected, places it back in its original board location
        else if (pieceMoved != null) {
            pieceMoved.setPosition(pieceMoved.getCol() * Square.SQUARE_WIDTH + 100, pieceMoved.getRow() * Square.SQUARE_WIDTH + 100);
            repaint();
        }
    }
}
