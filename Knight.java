/**
 * Knight
 *
 * @author Faiza Salami, 7941056
 * <p>
 * REMARKS: A knight class representing a knight piece on a chess board
 */

public class Knight extends Piece {
    public Knight(char piece) {
        super(piece);
    }

    /**
     * checks if the move being made is valid for a player's knight
     *
     * @param move      the move that was made
     * @param gameBoard the current game board
     * @return boolean-returns true if the move is valid and false otherwise
     */
    public boolean validMove(Move move, Board gameBoard) {
        Piece newPiece = gameBoard.getPiece(move.getNewRow(), move.getNewCol());
        boolean output = false;


        if (Math.abs(move.getNewCol() - move.getCol()) == 2 && Math.abs(move.getNewRow() - move.getRow()) == 1) {
            output = (newPiece == null || newPiece.isAIPiece());
        } else if (Math.abs(move.getNewCol() - move.getCol()) == 1 && Math.abs(move.getNewRow() - move.getRow()) == 2) {
            output = (newPiece == null || newPiece.isAIPiece());
        }

        if (output) {
            gameBoard.getPiece(move.getRow(), move.getCol()).setCaptured(newPiece);
        }

        return output;
    }

    /**
     * generate a move for the AI knight, easy mode. this method just moves the knight by one square
     *
     * @param move      the move containing where the knight currently is
     * @param gameBoard the current game board
     * @return boolean-returns the move containing what location the knight is moving to or null if there's no available
     * space for the knight to move to
     */
    public Move generateMove(Move move, Board gameBoard) {

        int[][] possibleMoves = {{2, 1}, {2, -1}, {1, 2}, {1, -2}};

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
     * checks if any of the player's piece is in the knight's vicinity to be captured
     *
     * @param move      the move containing the knight's current location
     * @param gameBoard the current game board
     * @return Move-the move containing what location the knight is moving to or null if the knight cant capture any player
     */
    public Move capturePlayerPiece(Move move, Board gameBoard) {
        int[][] possibleMoves = {{-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}};

        int newRow;
        int newCol;
        Piece newPiece;

        //for each of the possible moves the knight can make, check if it captures a human's piece
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
        return "Knight";
    }
}
