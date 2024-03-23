/**
 * Piece
 *
 * @author Faiza Salami, 7941056
 * <p>
 * REMARKS: An abstract piece class representing the pieces on the chess board
 */
public abstract class Piece {
    char piece;
    Piece captured;

    public Piece(char piece) {
        this.piece = piece;
        captured = null;
    }

    /**
     * checks if a piece captured a king
     *
     * @return boolean-returns true if the king has been captured and false otherwise
     */
    public boolean capturedKing() {
        if (captured != null) {
            return captured.name().equals("King");
        }
        return false;
    }

    /**
     * checks if its a player's piece
     *
     * @return boolean-returns true if its a player's piece and false otherwise
     */
    public boolean isPlayerPiece() {
        return Character.isUpperCase(piece);
    }

    /**
     * checks if its an AI's piece
     *
     * @return boolean-returns true if its an AI's piece and false otherwise
     */
    public boolean isAIPiece() {
        return Character.isLowerCase(piece);
    }

    //getter
    public char getPiece() {
        return piece;
    }

    public Piece getCaptured() {
        return captured;
    }

    //setter

    public void setPiece(char piece) {
        this.piece = piece;
    }

    public void setCaptured(Piece captured) {
        this.captured = captured;
    }


    /**
     * checks if the move being made is valid for a player's piece
     *
     * @param move      the move that was made
     * @param gameBoard the current game board
     * @return boolean-returns true if the move is valid and false otherwise
     */
    public abstract boolean validMove(Move move, Board gameBoard);


    /**
     * generate a move for the AI piece, easy mode. this method just moves the piece by one square
     *
     * @param move      the move containing where the piece currently is
     * @param gameBoard the current game board
     * @return boolean-returns the move containing what location the piece is moving to or null if there's no available
     * space for the piece to move to
     */
    public abstract Move generateMove(Move move, Board gameBoard);


    /**
     * checks if any of the player's piece is in the AI piece's vicinity to be captured
     *
     * @param move      the move containing the piece's current location
     * @param gameBoard the current game board
     * @return Move-the move containing what location the piece is moving to or null if the piece cant capture any player's piece
     */
    public abstract Move capturePlayerPiece(Move move, Board gameBoard);

    /**
     * checks if the piece is a pawn
     *
     * @return boolean-returns true if it's a pawn and false otherwise
     */
    public boolean isPawn() {
        return piece == 'P' || piece == 'p';
    }

    /**
     * checks if the piece is a rook
     *
     * @return boolean-returns true if it's a rook and false otherwise
     */
    public boolean isRook() {
        return piece == 'R' || piece == 'r';
    }

    /**
     * checks if the piece is a knight
     *
     * @return boolean-returns true if it's a knight and false otherwise
     */
    public boolean isKnight() {
        return piece == 'N' || piece == 'n';
    }

    /**
     * checks if the piece is a bishop
     *
     * @return boolean-returns true if it's a bishop and false otherwise
     */
    public boolean isBishop() {
        return piece == 'B' || piece == 'b';
    }

    /**
     * checks if the piece is a king
     *
     * @return boolean-returns true if it's a king and false otherwise
     */
    public boolean isKing() {
        return piece == 'K' || piece == 'k';
    }

    /**
     * checks if the piece is a queen
     *
     * @return boolean-returns true if it's a queen and false otherwise
     */
    public boolean isQueen() {
        return piece == 'Q' || piece == 'q';
    }

    /**
     * the name of the piece
     *
     * @return returns the piece name
     */
    public abstract String name();


}
