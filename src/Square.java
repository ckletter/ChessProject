import java.awt.*;

public class Square {
    private int row;
    private int col;
    private Color color;
    private Piece piece;
    public final int SQUARE_WIDTH = 75;
    public Square(int row, int col, Color color)
    {
        this.row = row;
        this.col = col;
        this.color = color;
    }
    public void setPiece(Piece p)
    {
        piece = p;
    }
    public Piece getPiece()
    {
        return piece;
    }
    public void draw(Graphics g, ChessGameView c)
    {
        int startX = col * SQUARE_WIDTH + 100;
        int startY = row * SQUARE_WIDTH + 100;
        Image[] images = c.getImages();
        g.setColor(color);
        g.fillRect(startX, startY, SQUARE_WIDTH, SQUARE_WIDTH);
        if (piece == null)
        {
            return;
        }
        if (piece instanceof Rook)
        {
            if (piece.getColor() == Piece.BLACK)
            {
                g.drawImage(images[Board.ROOK], startX, startY, SQUARE_WIDTH, SQUARE_WIDTH, c);
            }
            else
            {
                g.drawImage(images[Board.ROOK + 6], startX, startY, SQUARE_WIDTH, SQUARE_WIDTH, c);
            }
        }
        else if (piece instanceof King)
        {
            if (piece.getColor() == Piece.BLACK)
            {
                g.drawImage(images[Board.KING], startX, startY, SQUARE_WIDTH, SQUARE_WIDTH, c);
            }
            else
            {
                g.drawImage(images[Board.KING + 6], startX, startY, SQUARE_WIDTH, SQUARE_WIDTH, c);
            }
        }
        else if (piece instanceof Knight) {
            if (piece.getColor() == Piece.BLACK)
            {
                g.drawImage(images[Board.KNIGHT], startX, startY, SQUARE_WIDTH, SQUARE_WIDTH, c);
            }
            else
            {
                g.drawImage(images[Board.KNIGHT + 6], startX, startY, SQUARE_WIDTH, SQUARE_WIDTH, c);
            }
        }
        else if (piece instanceof Queen) {
            if (piece.getColor() == Piece.BLACK)
            {
                g.drawImage(images[Board.QUEEN], startX, startY, SQUARE_WIDTH, SQUARE_WIDTH, c);
            }
            else
            {
                g.drawImage(images[Board.QUEEN + 6], startX, startY, SQUARE_WIDTH, SQUARE_WIDTH, c);
            }
        }
        else if (piece instanceof Bishop)
        {
            if (piece.getColor() == Piece.BLACK)
            {
                g.drawImage(images[Board.BISHOP], startX, startY, SQUARE_WIDTH, SQUARE_WIDTH, c);
            }
            else
            {
                g.drawImage(images[Board.BISHOP + 6], startX, startY, SQUARE_WIDTH, SQUARE_WIDTH, c);
            }
        }
        else
        {
            if (piece.getColor() == Piece.BLACK)
            {
                g.drawImage(images[Board.PAWN], startX, startY, SQUARE_WIDTH, SQUARE_WIDTH, c);
            }
            else
            {
                g.drawImage(images[Board.PAWN + 6], startX, startY, SQUARE_WIDTH, SQUARE_WIDTH, c);
            }
        }
    }
}
