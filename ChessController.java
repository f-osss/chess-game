/**
 * Chess Controller
 *
 * @author Faiza Salami, 7941056
 * <p>
 * REMARKS: interface for implementing the back end that manages the game logic
 */

public interface ChessController {

    /**
     * test whether the move is valid
     *
     * @param playerMoves the move the player made
     * @return boolean-returns true if the move made by the player is valid and false otherwise
     */
    public boolean movePiece(Move playerMoves);

    /**
     * resets the game
     */
    public void reset();

    /**
     * this method drives the game,it determines whos turn it is, checks if the king has been captured, summarizes the moves and displays the board
     */
    public void playGame();
}
