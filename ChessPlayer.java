/**
 * Chess Player
 *
 * @author Faiza Salami, 7941056
 * <p>
 * REMARKS: interface for implementing the AI player
 */
public interface ChessPlayer {

    /**
     * It will return the next move made by the AI
     *
     * @param player the move the player made
     *
     * @return Move-the move made by the AI
     */
    public Move makeMove(Move player, Board gameBoard);
}
