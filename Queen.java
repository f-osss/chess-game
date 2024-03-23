/**
 * Queen
 *
 * @author Faiza Salami, 7941056
 * <p>
 * REMARKS: A queen class representing a queen piece on a chess board
 */
public class Queen extends Piece {

    public Queen(char piece) {
        super(piece);
    }


    /**
     * checks if the move being made is valid for a player's queen
     *
     * @param move      the move that was made
     * @param gameBoard the current game board
     * @return boolean-returns true if the move is valid and false otherwise
     */
    public boolean validMove(Move move, Board gameBoard) {
        boolean output = false;
        boolean noPiecesBetween = true;
        Piece newPiece = gameBoard.getPiece(move.getNewRow(), move.getNewCol());

        //check they're moving in a straight path
        if (move.getRow() == move.getNewRow()) {
            for (int i = 1; i <= Math.abs(move.getNewCol() - move.getCol()) - 1; i++) {
                if (gameBoard.getPiece(move.getNewRow(), Math.min(move.getCol(), move.getNewCol()) + i) != null) {
                    noPiecesBetween = false;
                }
            }
        } else if (move.getCol() == move.getNewCol()) {
            for (int i = 1; i <= Math.abs(move.getNewRow() - move.getRow()) - 1; i++) {
                if (gameBoard.getPiece(Math.min(move.getRow(), move.getNewRow()) + i, move.getNewCol()) != null) {
                    noPiecesBetween = false;
                }
            }
        } else if (Math.abs(move.getNewCol() - move.getCol()) == Math.abs(move.getNewRow() - move.getRow())) {
            for (int i = 1; i <= Math.abs(move.getNewCol() - move.getCol()) - 1; i++) {
                if (gameBoard.getPiece(Math.min(move.getRow(), move.getNewRow()) + i, Math.min(move.getCol(), move.getNewCol()) + i) != null) {
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
     * generate a move for the AI queen, easy mode. this method just moves the queen by one square
     *
     * @param move      the move containing where the queen currently is
     * @param gameBoard the current game board
     * @return boolean-returns the move containing what location the queen is moving to or null if there's no available
     * space for the queen to move to
     */
    public Move generateMove(Move move, Board gameBoard) {
        int[][] possibleMoves = {{-1, 1}, {-1, -1}, {1, 1}, {1, -1}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};

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
     * checks if any of the player's piece is in the queen's vicinity to be captured
     *
     * @param move      the move containing the queen's current location
     * @param gameBoard the current game board
     * @return Move-the move containing what location the queen is moving to or null if the queen cant capture any player
     */
    public Move capturePlayerPiece(Move move, Board gameBoard) {
        //checks all way the queen can move, horizontally, vertically, diagonally


        int newRow = move.getRow() + 1;
        int newCol = move.getCol() + 1;
        Piece newPiece = null;

        while (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8 && gameBoard.getPiece(newRow, newCol) == null) {
            newPiece = gameBoard.getPiece(newRow, newCol);
            newRow++;
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

        newRow = move.getRow() + 1;
        newCol = move.getCol();


        while (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8 && gameBoard.getPiece(newRow, newCol) == null) {
            newPiece = gameBoard.getPiece(newRow, newCol);
            newRow++;
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


        newRow = move.getRow();
        newCol = move.getCol() + 1;
        while (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8 && gameBoard.getPiece(newRow, newCol) == null) {
            newPiece = gameBoard.getPiece(newRow, newCol);
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

        newRow = move.getRow();
        newCol = move.getCol() - 1;
        while (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8 && gameBoard.getPiece(newRow, newCol) == null) {
            newPiece = gameBoard.getPiece(newRow, newCol);
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
        newCol = move.getCol();
        while (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8 && gameBoard.getPiece(newRow, newCol) == null) {
            newPiece = gameBoard.getPiece(newRow, newCol);
            newRow--;
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

    /**
     * the name of the piece
     *
     * @return returns the piece name
     */
    public String name() {
        return "Queen";
    }

}
