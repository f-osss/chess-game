/**
 * King
 *
 * @author Faiza Salami, 7941056
 * <p>
 * REMARKS: A king class representing a king piece on a chess board
 */

public class King extends Piece {
    public King(char piece) {
        super(piece);
    }

    /**
     * checks if the move being made is valid for a player's king
     *
     * @param move      the move that was made
     * @param gameBoard the current game board
     * @return boolean-returns true if the move is valid and false otherwise
     */
    public boolean validMove(Move move, Board gameBoard) {
        Piece newPiece = gameBoard.getPiece(move.getNewRow(), move.getNewCol());
        boolean output = false;

        if ((move.getRow() == move.getNewRow()) && Math.abs(move.getNewCol() - move.getCol()) == 1) {
            output = (newPiece == null || newPiece.isAIPiece());
        } else if ((move.getCol() == move.getNewCol()) && Math.abs(move.getNewRow() - move.getRow()) == 1) {
            output = (newPiece == null || newPiece.isAIPiece());
        } else if (Math.abs(move.getNewCol() - move.getCol()) == Math.abs(move.getNewRow() - move.getRow())) {
            output = (newPiece == null || newPiece.isAIPiece());
        }

        if (output) {
            gameBoard.getPiece(move.getRow(), move.getCol()).setCaptured(newPiece);
        }

        return output;
    }

    /**
     * generate a move for the AI king, easy mode. this method just moves the king by one square
     *
     * @param move      the move containing where the king currently is
     * @param gameBoard the current game board
     * @return boolean-returns the move containing what location the king is moving to or null if there's no available
     *                  space for the king to move to
     */
    public Move generateMove(Move move, Board gameBoard) {

        int[][] possibleMoves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        int newRow;
        int newCol;
        Piece newPiece;

        for (int i = 0; i < possibleMoves.length; i++) {
            newRow = move.getRow() + possibleMoves[i][0];
            newCol = move.getCol() + possibleMoves[i][1];

            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {

                newPiece = gameBoard.getPiece(newRow, newCol);
                if (newPiece == null || newPiece.isPlayerPiece()) {
                    move.setNewRow(newRow);
                    move.setNewCol(newCol);
                    gameBoard.getPiece(move.getRow(), move.getCol()).setCaptured(gameBoard.getPiece(move.getNewRow(), move.getNewCol()));
                    return move;
                }
            }

        }
        return null;

    }

    /**
     * checks if any of the player's piece is in the king's vicinity to be captured
     *
     * @param move      the move containing the king's current location
     * @param gameBoard the current game board
     * @return Move-the move containing what location the king is moving to or null if the king cant capture any player
     */
    public Move capturePlayerPiece(Move move, Board gameBoard) {
        int[][] possibleMoves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, 1}, {-1, -1}, {1, 1}, {1, -1}};

        int newRow;
        int newCol;
        Piece newPiece;

        for (int i = 0; i < possibleMoves.length; i++) {
            newRow = move.getRow() + possibleMoves[i][0];
            newCol = move.getCol() + possibleMoves[i][1];

            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {

                newPiece = gameBoard.getPiece(newRow, newCol);
                if (newPiece != null && newPiece.isPlayerPiece()) {
                    move.setNewRow(newRow);
                    move.setNewCol(newCol);
                    gameBoard.getPiece(move.getRow(), move.getCol()).setCaptured(gameBoard.getPiece(move.getNewRow(), move.getNewCol()));
                    return move;
                }
            }

        }
        return null;
    }

    /**
     * the name of the piece
     *
     * @return returns the piece name
     */
    public String name() {
        return "King";
    }

}
