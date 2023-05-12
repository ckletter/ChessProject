import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

/**
 * ChessGameView class
 * Class for backend of ChessGame
 * Paint method to draw all screens of the game
 * Includes board screen, starting screen, checkmate screen, and rules screen
 * All mouse methods contain in viewer class (dragging a piece and dropping a piece on destination square)
 */
public class ChessGameView extends JFrame implements MouseListener, MouseMotionListener {
    // Size of window
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 800;
    // Instance of board and images in Resources folder
    private Board b;
    private ChessGame cg;
    private Image[] images;
    private Piece pieceMoved;
    /** Static final ints for different screens of board **/
    public static final int WELCOME_SCREEN = 0;
    public static final int PLAYING = 1;
    public static final int RULES = 2;
    public static final int CHECKMATE = 3;
    public static final int STALEMATE = 4;
    /** Instance variables for current state of board and previous state **/
    private static int state = WELCOME_SCREEN;
    private static int prevState = WELCOME_SCREEN;

    /**
     * Constructor for ChessGameView
     * Puts all images in Resources into an array of Images
     */
    public ChessGameView(Board b, ChessGame cg)
    {
        this.b = b;
        this.cg = cg;
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
     * Paints current screen in GUI
     */
    public void paint(Graphics g)
    {
        // If state is the board, paints board
        if (state == PLAYING) {
            paintBoard(g);
        }
        // If state is welcome screen, paints welcome screen
        else if (state == WELCOME_SCREEN) {
            paintWelcomeScreen(g);
        }
        // If state is rules, paints rules
        else if (state == RULES) {
            paintRules(g);
        }
        // If state is checkmate, paints checkmate
        else if (state == CHECKMATE) {
            paintCheckmate(g, cg.getWinner());
        }
        // If state is stalemate, paints stalemate
        else {
            paintStalemate(g);
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {}

    /**
     * All actions for when a mouse gets pressed down on the GUI
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // Gets x and y location of the mouse
        int x = e.getX();
        int y = e.getY();
        // Welcome state
        if (state == WELCOME_SCREEN) {
            // If the user presses the play button, sets the screen state to playing and repaints the board
            if (x >= 100 && x <= 450 && y >= 550 && y <= 675) {
                state = PLAYING;
                repaint();
            }
            // If the user presses the rules button, sets the screen state to rules and repaints the board
            else if (x >= 550 && x <= 900 && y >= 550 && y <= 675) {
                prevState = WELCOME_SCREEN;
                state = RULES;
                repaint();
            }
        }
        // Board state
        else if (state == PLAYING) {
            // If the mouse location is within the confines of the actual chess board
            if (x >= 100 && x < 700 && y >= 100 && y < 700) {
                // Gets the row (0-7) of the piece being selected
                int boardRow = (y - 100) / Square.SQUARE_WIDTH;
                // Gets the row (0-7) of the piece being selected
                int boardCol = (x - 100) / Square.SQUARE_WIDTH;
                // Sets the instance variable pieceMoved to the piece being selected
                pieceMoved = b.getSquares()[boardRow][boardCol].getPiece();
            }
            // If selects how to button, sets screen state to rules and repaints
            else if (x >= 720 && x <= 970 && y >= 500 && y <= 580) {
                prevState = PLAYING;
                state = RULES;
                repaint();
            }
            // If selects new game button, creates a new board, sets the screen state to welcome, and repaints the board
            else if (x >= 720 && x <= 970 && y >= 300 && y <= 380) {
                state = WELCOME_SCREEN;
                b = new Board();
                cg.setBoard(b);
                repaint();
            }
        }
        // For the checkmate screen, stalemate screen, and rules screen, if the user clicks the "X",
        // sets the state back to the previous state and repaints board
        else {
            if (x >= 750 && x <= 800 && y >= 200 && y <= 250) {
                state = prevState;
                repaint();
            }
        }
    }

    /**
     * All actions for when a mouse is released on the GUI
     */
    public void mouseReleased(MouseEvent e) {
        // Gets the x and y location of the mouse
        int x = e.getX();
        int y = e.getY();
        if (state == PLAYING) {
            // If the mouse location is within the confines of the actual chess board and a piece is currently being moved
            if (x >= 100 && x < 700 && y >= 100 && y < 700 && pieceMoved != null) {
                // Gets the row (0-7) of the piece being released
                int boardRow = (y - 100) / Square.SQUARE_WIDTH;
                // Gets the row (0-7) of the piece being released
                int boardCol = (x - 100) / Square.SQUARE_WIDTH;
                // If chess rules allow for the piece to be moved to the square legally, moves it
                if (cg.userTurn(pieceMoved, boardCol, boardRow)) {
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
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

    /**
     * All actions for when a mouse is dragged on the GUI
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (state == PLAYING) {
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

    /** Setter method
     * Sets the current screen state of the game
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * Paints entire board in GUI
     * Loops through each square and tells it to draw itself
     * Draws banner for each player
     * @param g
     */
    public void paintBoard(Graphics g) {
        // Sets color to white and clears the entire board
        g.setColor(Board.lightBrown);
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
        b.getpWhite().draw(g, this);
        b.getpBlack().draw(g, this);
        // Draws advantage on either black's banner or white banner depending on who has the advantage
        b.determineAdvantage(g);
        // Draws border around board
        g.setColor(Color.black);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(6));
        g.drawRect(100, 50, 600, 700);
        // Draws buttons on side of the board
        g.setColor(Color.BLACK);
        g.fillRoundRect(720, 300, 250, 80, 20, 20);
        g.fillRoundRect(720, 500, 250, 80, 20, 20);
        g.setFont(g.getFont().deriveFont(50f));
        g.setColor(Color.white);
        g.drawString("𝐡𝐨𝐰 𝐭𝐨",770, 555);
        g.drawString("𝐧𝐞𝐰 𝐠𝐚𝐦𝐞",735, 355);
        // Tells system to do the painting of the GUI before all other methods
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Paints the entire welcome screen of the board
     * @param g
     */
    public void paintWelcomeScreen(Graphics g) {
        // Sets color to light brown and clears the entire board
        g.setColor(Board.lightBrown);
        g.fillRect(0, 0, 1000, 800);
        // Draws the title of the game
        g.setColor(Color.black);
        g.setFont(g.getFont().deriveFont(200f));
        g.drawString("𝐜𝐡𝐞𝐬𝐬", 200, 350);
        // Draws a pawn
        g.drawImage(images[Board.PAWN + 6], 625, 200, 200, 200, this);
        // Draws each button
        g.setColor(Color.BLACK);
        g.fillRoundRect(100, 550, 350, 125, 50, 50);
        g.fillRoundRect(550, 550, 350, 125, 50, 50);
        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(75f));
        g.drawString("𝐩𝐥𝐚𝐲", 205, 630);
        g.drawString("𝐡𝐨𝐰 𝐭𝐨",625, 630);
        // Tells system to do the painting of the GUI before all other methods
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Paints how-to of the game in the GUI
     */
    public void paintRules(Graphics g) {
        // Draws border and background of how-to pop-up
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(6));
        g.setColor(Color.black);
        g.drawRect(200, 200, 600, 400);
        g.setColor(Board.lightBrown);
        g.fillRect(200, 200, 600, 400);
        g.setColor(Color.black);
        // Draws all text in the how-to rectangle
        g.setFont(g.getFont().deriveFont(70f));
        g.drawString("𝐇𝐎𝐖 𝐓𝐎:", 250, 275);
        g.setFont(g.getFont().deriveFont(30f));
        g.drawString("𝐃𝐫𝐚𝐠 𝐚 𝐩𝐢𝐞𝐜𝐞 𝐭𝐨 𝐦𝐨𝐯𝐞 𝐢𝐭 𝐭𝐨 𝐚 𝐧𝐞𝐰 𝐬𝐪𝐮𝐚𝐫𝐞.", 250, 350);
        g.drawString("𝐂𝐥𝐢𝐜𝐤 '𝐍𝐞𝐰 𝐆𝐚𝐦𝐞' 𝐭𝐨 𝐩𝐥𝐚𝐲 𝐚 𝐧𝐞𝐰 𝐠𝐚𝐦𝐞.", 250, 450);
        g.drawString("𝐓𝐫𝐲 𝐭𝐨 𝐜𝐡𝐞𝐜𝐤𝐦𝐚𝐭𝐞 𝐲𝐨𝐮𝐫 𝐨𝐩𝐩𝐨𝐧𝐞𝐧𝐭.", 250, 550);
        // Draws the x in the top right corner
        g.setColor(Color.red);
        g.fillRect(750,200,50,50);
        g.setFont(g.getFont().deriveFont(40f));
        g.setColor(Color.white);
        g.drawString("X",765, 240);
        // Tells system to do the painting of the GUI before all other methods
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Paints the entire checkmate screen on the GUI
     */
    public void paintCheckmate(Graphics g, boolean winner) {
        prevState = PLAYING;
        // Draws background and border for pop-up
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(6));
        g.setColor(Color.black);
        g.drawRect(200, 200, 600, 400);
        g.setColor(Board.lightBrown);
        g.fillRect(200, 200, 600, 400);
        g.setColor(Color.black);
        g.setFont(g.getFont().deriveFont(80f));
        // Draws text for the winner fo the game
        if (winner == Piece.BLACK) {
            g.drawString("𝐛𝐥𝐚𝐜𝐤 𝐰𝐢𝐧𝐬", 250, 375);
            g.drawImage(images[Board.QUEEN], 650, 300, 100, 100, this);
        }
        else {
            g.drawString("𝐰𝐡𝐢𝐭𝐞 𝐰𝐢𝐧𝐬", 250, 375);
            g.drawImage(images[Board.QUEEN + 6], 650, 300, 100, 100, this);
        }
        g.setFont(g.getFont().deriveFont(60f));
        g.drawString("𝐛𝐲 𝐜𝐡𝐞𝐜𝐤𝐦𝐚𝐭𝐞!", 300, 500);
        // Draws "X" in the top-right corner of the pop-up
        g.setColor(Color.red);
        g.fillRect(750,200,50,50);
        g.setFont(g.getFont().deriveFont(40f));
        g.setColor(Color.white);
        g.drawString("X",765, 240);
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Draws entire stalemate screen in GUI
     */
    public void paintStalemate(Graphics g) {
        // Draws border and background of pop-up
        prevState = PLAYING;
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(6));
        g.setColor(Color.black);
        g.drawRect(200, 200, 600, 400);
        g.setColor(Board.lightBrown);
        g.fillRect(200, 200, 600, 400);
        g.setColor(Color.black);
        // Draws all text of the pop-up on the screen
        g.setFont(g.getFont().deriveFont(80f));
        g.drawString("𝐭𝐢𝐞", 500, 375);
        g.setFont(g.getFont().deriveFont(60f));
        g.drawString("𝐛𝐲 𝐬𝐭𝐚𝐥𝐞𝐦𝐚𝐭𝐞!", 300, 500);
        g.drawImage(images[Board.QUEEN], 650, 300, 100, 100, this);
        // Draws an "X" in the top right corner of the pop-up
        g.setColor(Color.red);
        g.fillRect(750,200,50,50);
        g.setFont(g.getFont().deriveFont(40f));
        g.setColor(Color.white);
        g.drawString("X",765, 240);
        Toolkit.getDefaultToolkit().sync();
    }
}
