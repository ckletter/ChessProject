import java.awt.*;

public class Board {
    private Square[][] squares;
    public static final Color darkBrown = new Color(139, 69, 19);
    public static final Color lightBrown = new Color(245,222,179);
    public static final int ROOK = 0;
    public static final int PAWN = 1;
    public static final int BISHOP = 2;
    public static final int KNIGHT = 3;
    public static final int QUEEN = 4;
    public static final int KING = 5;
    public static final int BOARD_LENGTH = 8;

    public Board()
    {
        squares = new Square[8][8];
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if ((i + j) % 2 == 1)
                {
                    squares[i][j] = new Square(i, j, darkBrown);
                }
                else {
                    squares[i][j] = new Square(i, j, lightBrown);
                }
            }
        }
        for (int i = 0; i < 8; i++)
        {
            squares[1][i].setPiece(new Pawn(Piece.BLACK));
        }
        for (int i = 0; i < 8; i++)
        {
            squares[6][i].setPiece(new Pawn(Piece.WHITE));
        }
        squares[0][0].setPiece(new Rook(Piece.BLACK));
        squares[0][1].setPiece(new Knight(Piece.BLACK));
        squares[0][2].setPiece(new Bishop(Piece.BLACK));
        squares[0][3].setPiece(new Queen(Piece.BLACK));
        squares[0][4].setPiece(new King(Piece.BLACK));
        squares[0][5].setPiece(new Bishop(Piece.BLACK));
        squares[0][6].setPiece(new Knight(Piece.BLACK));
        squares[0][7].setPiece(new Rook(Piece.BLACK));

        squares[7][0].setPiece(new Rook(Piece.WHITE));
        squares[7][1].setPiece(new Knight(Piece.WHITE));
        squares[7][2].setPiece(new Bishop(Piece.WHITE));
        squares[7][3].setPiece(new Queen(Piece.WHITE));
        squares[7][4].setPiece(new King(Piece.WHITE));
        squares[7][5].setPiece(new Bishop(Piece.WHITE));
        squares[7][6].setPiece(new Knight(Piece.WHITE));
        squares[7][7].setPiece(new Rook(Piece.WHITE));
    }
    public void printBoard()
    {
        for (int i = 0; i < 8; i++)
        {
            int row = BOARD_LENGTH - i;
            System.out.print(row + " ");
            for (int j = 0; j < 8; j++)
            {
                if (squares[i][j].getPiece() == null) {
                    System.out.print("-\t\t");
                }
                else {
                    System.out.print(squares[i][j].getPiece() + "\t");
                }
            }
            System.out.println();
        }
        System.out.print("  ");
        for (int i = 0; i < 8; i++)
        {
            System.out.print((char)(97 + i) + "\t\t");
        }
        System.out.println();
    }
    public Square[][] getSquares()
    {
        return squares;
    }
    public void movePiece(int colFrom, int rowFrom, int colTo, int rowTo)
    {
        rowFrom = BOARD_LENGTH - 1 - rowFrom;
        rowTo = BOARD_LENGTH - 1 - rowTo;
        squares[rowTo][colTo].setPiece(squares[rowFrom][colFrom].getPiece());
        squares[rowFrom][colFrom].setPiece(null);
    }
}
