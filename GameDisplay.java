/**
 * Game Display
 *
 * @author Faiza Salami, 7941056
 * <p>
 * REMARKS: interface for implementing the display of the game
 */

public interface GameDisplay {
    /**
     * the method indicates which difficulty of the AI the human player want to play against
     *
     * @param maxDifficulty the maximum difficulty of the AI
     *
     * @return int-the human player choice of difficulty
     */
    public int promptForOpponentDifficulty(int maxDifficulty);

    /**
     * ask the human user to input their chosen move
     *
     * @return Move-the move the human user made
     */
    public Move promptForMove();

    /**
     * display the text character of the board
     *
     * @param gameBoard the current game board
     */
    public void displayBoard(Board gameBoard);

    /**
     * summarize the last move made
     *
     * @param playerMoves the move thats going to be summarized
     * @param gameBoard the current game board
     */
    public void summarizeMove(Move playerMoves, Board gameBoard);

    /**
     * called when the game is over
     *
     * @param winner indicates which player won
     */
    public void gameOver(int winner);

    /**
     * called when the human player wants to play the game again
     *
     * @return boolean-returns true if the player wants to play again and false otherwise
     */
    public boolean promptPlayAgain();

    /**
     * called when a pawn piece needs to be promoted
     *
     * @param move the current move made that promoted the pawn
     * @param gameBoard the current game board
     * @param AI the AI being played against
     */
    void promotion(Move move, Board gameBoard, ChessPlayer AI);
}
