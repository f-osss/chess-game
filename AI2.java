/**
 * AI2
 *
 * @author Faiza Salami, 7941056
 * <p>
 * REMARKS: Represents the second AI which is a hard AI mode.
 * The AI checks all the AI pieces on the board and check if any player piece can be captured
 */

import java.util.Random;

public class AI2 implements ChessPlayer {
    private AIPieceList AIPiece; //list of all the AI piece on the board
    private Move AI; //the move the AI is going to make

    public AI2() {
        AIPiece = new AIPieceList();
        AI = new Move();


        //store all AI piece and its location in the list
        Piece rook = new Rook('r');
        Piece knight = new Knight('n');
        Piece bishop = new Bishop('b');
        Piece king = new King('k');
        Piece queen = new Queen('q');
        Piece pawn = new Pawn('p');


        for (int i = 0; i < 8; i++) {
            AIPiece.add(pawn, 1, i);
        }


        AIPiece.add(rook, 0, 0);
        AIPiece.add(knight, 0, 1);
        AIPiece.add(bishop, 0, 2);
        AIPiece.add(king, 0, 3);
        AIPiece.add(queen, 0, 4);
        AIPiece.add(bishop, 0, 5);
        AIPiece.add(knight, 0, 6);
        AIPiece.add(rook, 0, 7);
    }


    /**
     * The method calls on other methods to get an AI Piece on the board and generate a move for the piece
     *
     * @param player    the move the player made
     * @param gameBoard the current game board
     * @return Move-the move of the AI containing what piece of the AI is moving and what location its moving to
     */
    public Move makeMove(Move player, Board gameBoard) {

        //if a player piece can be captured
        if (availablePlayerPiece(gameBoard)) {
            return AI;
        }

        int num = -1;

        //if no AI piece can capture a player's piece, look for another random AI piece and generate a move for it
        while (AI == null) {
            num = new Random().nextInt(AIPiece.size()) + 1;
            AI = new Move();
            AI.setRow(AIPiece.get(num).getRow());
            AI.setCol(AIPiece.get(num).getCol());
            AI = AIPiece.get(num).getPiece().generateMove(AI, gameBoard);
        }

        //add the new position of the AI piece to the list
        AIPiece.add(AIPiece.get(num).getPiece(), AI.getNewRow(), AI.getNewCol());

        //remove the old position
        AIPiece.remove(AIPiece.get(num).getPiece(),AIPiece.get(num).getRow(),AIPiece.get(num).getCol());
        return AI;

    }


    /**
     * The method loops through the whole AI piece on the board and check if any of them can capture a player's piece
     *
     * @param gameBoard the current game board
     * @return boolean-returns true if a player piece can be capture and false otherwise
     */
    public boolean availablePlayerPiece(Board gameBoard) {
        for (int i = 1; i < AIPiece.size() + 1; i++) {
            AI = new Move();
            AI.setRow(AIPiece.get(i).getRow());
            AI.setCol(AIPiece.get(i).getCol());

            AI = AIPiece.get(i).getPiece().capturePlayerPiece(AI, gameBoard);

            //if the AI is not null, a player's piece can be captured
            if (AI != null) {
                //add the new position of the AI piece to the list
                AIPiece.add(AIPiece.get(i).getPiece(), AI.getNewRow(), AI.getNewCol());

                //remove the old position
                AIPiece.remove(AIPiece.get(i).getPiece(),AIPiece.get(i).getRow(),AIPiece.get(i).getCol());
                return true;
            }
        }
        return false;
    }


    /**
     * The method removes a piece from the list
     *
     * @param piece the piece to be removed
     * @param row   the row of the piece to be removed
     * @param col   the col of the piece to be removed
     */
    public void removeFromList(Piece piece, int row, int col) {
        AIPiece.remove(piece, row, col);
    }

    /**
     * gets the AI Piece list
     *
     * @return boolean-returns the AI Piece list
     */
    public AIPieceList getList() {
        return AIPiece;
    }
}
