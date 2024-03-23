/**
 * Bishop
 *
 * @author Faiza Salami, 7941056
 * <p>
 * REMARKS: A bishop class representing a bishop piece on a chess board
 */

public class Bishop extends Piece {
    public Bishop(char piece) {
        super(piece);
    }

    /**
     * checks if the move being made is valid for a player's bishop
     *
     * @param move      the move that was made
     * @param gameBoard the current game board
     * @return boolean-returns true if the move is valid and false otherwise
     */
    public boolean validMove(Move move, Board gameBoard) {
        boolean output = false;
        boolean noPiecesBetween = true; //if there is any piece in between its starting point to its location

        Piece newPiece = gameBoard.getPiece(move.getNewRow(), move.getNewCol());

        if (Math.abs(move.getNewCol() - move.getCol()) == Math.abs(move.getNewRow() - move.getRow())) {
            for (int i = 1; i <= Math.abs(move.getNewCol() - move.getCol()) - 1; i++) {
                if (gameBoard.getPiece(Math.min(move.getRow(), move.getNewRow()) + i, Math.max(move.getCol(), move.getNewCol()) - i) != null) {
                    noPiecesBetween = false;
                }
            }
        } else {
            noPiecesBetween = false;
        }

        if (noPiecesBetween && (newPiece == null || newPiece.isAIPiece())) {
            gameBoard.getPiece(move.getRow(), move.getCol()).setCaptured(newPiece);
            output = true;
        }

        return output;
    }

    /**
     * the name of the piece
     *
     * @return returns the piece name
     */
    public String name() {
        return "Bishop";
    }

    /**
     * generate a move for the AI bishop, easy mode. this method just moves the bishop by one square
     *
     * @param move      the move containing where the bishop currently is
     * @param gameBoard the current game board
     * @return boolean-returns the move containing what location the bishop is moving to or null if there's no available space for the
     *                  bishop to move to
     */
    public Move generateMove(Move move, Board gameBoard) {

        int[][] possibleMoves = {{-1, 1}, {-1, -1}, {1, 1}, {1, -1}};

        int newRow;
        int newCol;
        Piece newPiece;

        //if any of the possible move is a valid move, that will be the AI's move
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
     * checks if any of the player's piece is in the bishop's vicinity to be captured
     *
     * @param move      the move containing the bishop's current location
     * @param gameBoard the current game board
     * @return Move-the move containing what location the bishop is moving to or null if the bishop cant capture any player
     */
    public Move capturePlayerPiece(Move move, Board gameBoard) {
        int newRow = move.getRow() + 1;
        int newCol = move.getCol() + 1;
        Piece newPiece = null;

        //keep moving the bishop until it meets another piece
        while (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8 && gameBoard.getPiece(newRow, newCol) == null) {
            newPiece = gameBoard.getPiece(newRow, newCol);
            newRow++;
            newCol++;
        }


        if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
            newPiece = gameBoard.getPiece(newRow, newCol);
        }
        //if the new piece the bishop meets is a players piece, capture that player's piece
        if (newPiece != null && newPiece.isPlayerPiece()) {
            move.setNewRow(newRow);
            move.setNewCol(newCol);
            gameBoard.getPiece(move.getRow(), move.getCol()).setCaptured(gameBoard.getPiece(move.getNewRow(), move.getNewCol()));
            return move;
        }


        newRow = move.getRow() + 1;
        newCol = move.getCol() - 1;
        while (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8 && gameBoard.getPiece(newRow, newCol) == null) {
            newPiece = gameBoard.getPiece(newRow, newCol);
            newRow++;
            newCol--;
        }


        if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
            newPiece = gameBoard.getPiece(newRow, newCol);
        }
        if (newPiece != null && newPiece.isPlayerPiece()) {
            move.setNewRow(newRow);
            move.setNewCol(newCol);
            gameBoard.getPiece(move.getRow(), move.getCol()).setCaptured(gameBoard.getPiece(move.getNewRow(), move.getNewCol()));
            return move;
        }

        newRow = move.getRow() - 1;
        newCol = move.getCol() + 1;
        while (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8 && gameBoard.getPiece(newRow, newCol) == null) {
            newPiece = gameBoard.getPiece(newRow, newCol);
            newRow--;
            newCol++;
        }


        if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
            newPiece = gameBoard.getPiece(newRow, newCol);
        }
        if (newPiece != null && newPiece.isPlayerPiece()) {
            move.setNewRow(newRow);
            move.setNewCol(newCol);
            gameBoard.getPiece(move.getRow(), move.getCol()).setCaptured(gameBoard.getPiece(move.getNewRow(), move.getNewCol()));
            return move;
        }


        newRow = move.getRow() - 1;
        newCol = move.getCol() - 1;
        while (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8 && gameBoard.getPiece(newRow, newCol) == null) {
            newPiece = gameBoard.getPiece(newRow, newCol);
            newRow--;
            newCol--;

        }


        if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
            newPiece = gameBoard.getPiece(newRow, newCol);
        }
        if (newPiece != null && newPiece.isPlayerPiece()) {
            move.setNewRow(newRow);
            move.setNewCol(newCol);
            gameBoard.getPiece(move.getRow(), move.getCol()).setCaptured(gameBoard.getPiece(move.getNewRow(), move.getNewCol()));
            return move;
        }


        return null;

    }
}
