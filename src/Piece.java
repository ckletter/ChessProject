public class Piece {
    private static final int ROOK = 1;
    private static final int PAWN = 2;
    private static final int BISHOP = 3;
    private static final int KNIGHT = 4;
    private static final int QUEEN = 5;
    private static final int KING = 6;
    private int value;
    public static final boolean WHITE = false;
    public static final boolean BLACK = true;
    private int type;
    private boolean color;
    private String name;
    public Piece(int type, boolean color)
    {
        this.type = type;
        this.color = color;
        switch (type) {
            case ROOK:
                value = 5;
                name = "Rook";
                break;
            case PAWN:
                value = 1;
                name = "Pawn";
                break;
            case BISHOP:
                value = 3;
                name = "Bishop";
                break;
            case KNIGHT:
                value = 3;
                name = "Knight";
                break;
            case QUEEN:
                value = 9;
                name = "Queen";
                break;
            case KING:
                value = 0;
                name = "King";
                break;
            default:
                break;
        }
    }
    public String toString()
    {
        return name;
    }

    public boolean getColor() {
        return color;
    }
}
