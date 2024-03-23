/**
 * AI1
 *
 * @author Faiza Salami, 7941056
 * <p>
 * REMARKS: Represents the first AI which is an easy AI mode which a player can play against.
 * The AI selects a random AI piece on the board and moves it
 */

import java.util.Random;

public class AI1 implements ChessPlayer {

    /**
     * The method calls on other methods to get an AI Piece on the board and generate a move for the piece
     *
     * @param player    the move the player made
     * @param gameBoard the current game board
     * @return Move-the move of the AI containing what piece of the AI is moving and what location its moving to
     */
    public Move makeMove(Move player, Board gameBoard) {
        Move AIMove = new Move();

        AIMove = getMove(gameBoard, AIMove);

        //gets the AI piece
        Piece piece = gameBoard.getPiece(AIMove.getRow(), AIMove.getCol());

        //generate a move for the piece
        AIMove = piece.generateMove(AIMove, gameBoard);


        //if the move is invalid, look for another AI piece and generate a move for it
        while (AIMove == null) {
            AIMove = getMove(gameBoard, AIMove);
            AIMove = piece.generateMove(AIMove, gameBoard);
        }

        return AIMove;

    }

    /**
     * The method gets an AI piece that would move
     *
     * @param gameBoard the current game board
     * @param moveAI-   the move of the AI
     * @return Move-the move of the AI containing which piece that's going to be moved
     */
    public Move getMove(Board gameBoard, Move moveAI) {
        //start by moving the pawn on position [0][3] as the AI's first move
        int row = 1;
        int col = 3;
        Piece randomPiece = gameBoard.getPiece(row, col);

        //keep generating a random number for an AI piece on the board
        while (randomPiece == null || randomPiece.isPlayerPiece()) {
            row = new Random().nextInt(8);
            col = new Random().nextInt(8);
            randomPiece = gameBoard.getPiece(row, col);

        }

        if (moveAI == null) {
            moveAI = new Move();
        }

        moveAI.setRow(row);
        moveAI.setCol(col);
        return moveAI;
    }
}
