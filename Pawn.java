/**
 * Pawn
 *
 * @author Faiza Salami, 7941056
 * <p>
 * REMARKS: A pawn class representing a pawn piece on a chess board
 */
public class Pawn extends Piece {

    public Pawn(char piece) {
        super(piece);
    }

    /**
     * checks if the move being made is valid for a player's pawn
     *
     * @param move      the move that was made
     * @param gameBoard the current game board
     * @return boolean-returns true if the move is valid and false otherwise
     */
    public boolean validMove(Move move, Board gameBoard) {
        boolean output = false;
        Piece newPiece = gameBoard.getPiece(move.getNewRow(), move.getNewCol());

        if (move.getRow() == move.getNewRow() + 1) {
            if (newPiece == null && move.getCol() == move.getNewCol()) {
                output = true;
            } else if (newPiece != null && newPiece.isAIPiece() && (move.getNewCol() == move.getCol() + 1 || move.getNewCol() == move.getCol() - 1)) {
                output = true;
            }
        }

        if (output) {
            gameBoard.getPiece(move.getRow(), move.getCol()).setCaptured(newPiece);
        }

        return output;
    }

    /**
     * generate a move for the AI pawn, easy mode. this method just moves the pawn by one square
     *
     * @param move      the move containing where the pawn currently is
     * @param gameBoard the current game board
     * @return boolean-returns the move containing what location the pawn is moving to or null if there's no available
     * space for the pawn to move to
     */
    public Move generateMove(Move move, Board gameBoard) {

        //list of way the pawn can go diagonal
        int[][] diagonal = {{1, -1}, {1, 1}};

        int newRow;
        int newCol;

        for (int i = 0; i < diagonal.length; i++) {
            newRow = move.getRow() + diagonal[i][0]; //get the new row the pawn is moving to
            newCol = move.getCol() + diagonal[i][1]; //get the new col the pawn is moving to

            //check if its within bounds of the board
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                if (gameBoard.getPiece(newRow, newCol) != null && gameBoard.getPiece(newRow, newCol).isPlayerPiece()) {
                    move.setNewRow(newRow);
                    move.setNewCol(newCol);
                    gameBoard.getPiece(move.getRow(), move.getCol()).setCaptured(gameBoard.getPiece(move.getNewRow(), move.getNewCol()));
                    return move;
                }
            }
        }

        newRow = move.getRow() + 1;
        newCol = move.getCol();
        if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
            if (gameBoard.getPiece(newRow, newCol) == null) {
                move.setNewRow(newRow);
                move.setNewCol(newCol);
                gameBoard.getPiece(move.getRow(), move.getCol()).setCaptured(gameBoard.getPiece(move.getNewRow(), move.getNewCol()));
                return move;
            }
        }
        return null;


    }

    /**
     * checks if any of the player's piece is in the pawn's vicinity to be captured
     *
     * @param move      the move containing the pawn's current location
     * @param gameBoard the current game board
     * @return Move-the move containing what location the pawn is moving to or null if the pawn cant capture any player
     */
    public Move capturePlayerPiece(Move move, Board gameBoard) {
        int[][] diagonal = {{1, -1}, {1, 1}}; //the only possible moves are the ones that can capture a humans piece diagonally

        int newRow;
        int newCol;

        for (int i = 0; i < diagonal.length; i++) {
            newRow = move.getRow() + diagonal[i][0]; //get the new row the pawn is moving to
            newCol = move.getCol() + diagonal[i][1]; //get the new col the pawn is moving to

            //check if its within bounds of the board
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                if (gameBoard.getPiece(newRow, newCol) != null && gameBoard.getPiece(newRow, newCol).isPlayerPiece()) {
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
        return "Pawn";
    }


}
