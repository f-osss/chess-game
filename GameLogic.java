/**
 * Game Logic
 *
 * @author Faiza Salami, 7941056
 * <p>
 * REMARKS: implementing the logic of the game which is the back end that manages the game logic
 */

public class GameLogic implements ChessController {

    private GameDisplay game;
    private Board gameBoard;
    private ChessPlayer AI;
    private int player;

    public GameLogic(GameDisplay game) {
        this.game = game;
        gameBoard = new Board();
        player = 1;
    }

    /**
     * test whether the move is valid
     *
     * @param move the move the player made
     * @return boolean-returns true if the move made by the player is valid and false otherwise
     */
    public boolean movePiece(Move move) {
        Piece piece;
        boolean output = false;

        if (move.getRow() >= 0 && move.getRow() <= 7 && move.getCol() >= 0 && move.getCol() <= 7 && move.getNewRow() >= 0 && move.getNewRow() <= 7 && move.getNewCol() >= 0 && move.getNewCol() <= 7) {
            piece = gameBoard.getPiece(move.getRow(), move.getCol());

            if (piece != null && piece.isPlayerPiece()) {
                if (piece.validMove(move, gameBoard)) {
                    output = true;
                } else {
                    output = false;

                }

            }
        }
        return output;
    }

    /**
     * resets the game
     */
    public void reset() {
        if (game.promptPlayAgain()) {
            gameBoard = new Board();
            playGame();
        } else {
            System.exit(0);

        }
    }

    /**
     * this method drives the game,it determines whos turn it is, checks if the king has been captured,
     * summarizes the moves and displays the board
     */
    public void playGame() {
        Move move = null;

        int difficulty = game.promptForOpponentDifficulty(1);

        //determines which AI is going to be played against
        if (difficulty == 0) {
            AI = new AI1();
        } else {
            AI = new AI2();
        }

        game.displayBoard(gameBoard);

        //keeps playing the game until the king has been captured
        while (!gameBoard.capturedKing()) {
            if (player == 1) {
                move = game.promptForMove();
            } else if (player == 2) {
                if (difficulty != 0 && gameBoard.getPiece(move.getNewRow(), move.getNewCol()).getCaptured() != null) {
                    ((AI2) AI).removeFromList(gameBoard.getPiece(move.getNewRow(), move.getNewCol()).getCaptured(), move.getNewRow(), move.getNewCol());
                }
                move = AI.makeMove(move, gameBoard);

            }

            if (move == null) {
                reset();
            }

            if ((movePiece(move) && player == 1) || player == 2) {
                if (((move.getNewRow() == 0 && player == 1) || (move.getNewRow() == 7 && player == 2)) && !gameBoard.capturedKing()) {
                    game.promotion(move, gameBoard, AI);
                }
                gameBoard.updateBoard(move);
                game.summarizeMove(move, gameBoard);
                game.displayBoard(gameBoard);

                //change whichever player's turn it is
                if (player == 1) {
                    player = 2;
                } else {
                    player = 1;

                }
            } else {
                System.out.println("The move you have entered is invalid.  Please enter another move.");
            }
        }

        //get the winner
        if (gameBoard.getPiece(move.getNewRow(), move.getNewCol()).isAIPiece()) {
            game.gameOver(2);
        } else {
            game.gameOver(1);
        }


    }


}
