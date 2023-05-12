import java.awt.*;

/**
 * Square class
 * Contains instance of Piece at specific square
 * Contains color, row, width, and column of square
 * Contains method to draw the square in the GUI
 */
public class Square {
    // Location of square
    private int row;
    private int col;
    private boolean blackOccupied;
    private boolean whiteOccupied;
    // Color of square
    private Color color;
    // Piece on specific square
    private Piece piece;
    // Length of square on GUI
    public static final int SQUARE_WIDTH = 75;

    /**
     * Square constructor
     * Initializes row, column, and color of square
     */
    public Square(int row, int col, Color color)
    {
        this.row = row;
        this.col = col;
        this.color = color;
        this.blackOccupied = false;
        this.whiteOccupied = false;
    }

    /**
     * Setter method to set the Piece on the square
     */
    public void setPiece(Piece p)
    {
        piece = p;
    }

    /**
     * Getter method to return the piece at the specific square
     */
    public Piece getPiece()
    {
        return piece;
    }

    /**
     * Draws the square on the screen
     * Determines the location to draw the square and which Piece image to draw over it
     */
    public void draw(Graphics g, ChessGameView c)
    {
        // Gets set of images in the Resources file
        Image[] images = c.getImages();
        // Ends draw method if there is no Piece to draw
        if (piece == null)
        {
            return;
        }
        // Determines starting x and y location to draw piece based off its x and y variables
        int startX = piece.getX();
        int startY = piece.getY();
        // Draws a rook of correct color over the square if Piece rook exists at the square
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
        // Draws a king of correct color over the square if Piece king exists at the square
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
        // Draws a knight of correct color over the square if Piece knight exists at the square
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
        // Draws a queen of correct color over the square if Piece queen exists at the square
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
        // Draws a bishop of correct color over the square if Piece bishop exists at the square
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
        // Draws a pawn of correct color over the square if Piece pawn exists at the square
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
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
}
