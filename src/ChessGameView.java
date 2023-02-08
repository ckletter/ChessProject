import javax.swing.*;
import java.awt.*;

public class ChessGameView extends JFrame {
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 800;
    private Board b;
    private Image[] images;
    public ChessGameView(Board b)
    {
        this.b = b;
        images = new Image[12];
        images[Board.BISHOP] = new ImageIcon("Resources/Chess_bdt60.png").getImage();
        images[Board.BISHOP + 6] = new ImageIcon("Resources/Chess_blt60.png").getImage();
        images[Board.ROOK] = new ImageIcon("Resources/Chess_rdt60.png").getImage();
        images[Board.ROOK + 6] = new ImageIcon("Resources/Chess_rlt60.png").getImage();
        images[Board.QUEEN] = new ImageIcon("Resources/Chess_qdt60.png").getImage();
        images[Board.QUEEN + 6] = new ImageIcon("Resources/Chess_qlt60.png").getImage();
        images[Board.PAWN] = new ImageIcon("Resources/Chess_pdt60.png").getImage();
        images[Board.PAWN + 6] = new ImageIcon("Resources/Chess_plt60.png").getImage();
        images[Board.KNIGHT] = new ImageIcon("Resources/Chess_ndt60.png").getImage();
        images[Board.KNIGHT + 6] = new ImageIcon("Resources/Chess_nlt60.png").getImage();
        images[Board.KING] = new ImageIcon("Resources/Chess_kdt60.png").getImage();
        images[Board.KING + 6] = new ImageIcon("Resources/Chess_klt60.png").getImage();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Chess");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public Image[] getImages() {
        return images;
    }

    public void paint(Graphics g)
    {
        Square[][] squares = b.getSquares();
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                squares[i][j].draw(g, this);
            }
        }
    }
}
