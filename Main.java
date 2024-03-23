/**
 * Main
 * <p>
 * COMP 2150 SECTION A01
 * INSTRUCTOR   Heather Matheson
 * ASSIGNMENT 3
 *
 * @author Faiza Salami, 7941056
 * @version 18th March 2024
 * <p>
 * REMARKS: Implementing a chess game
 */


public class Main {
    public static void main(String[] args) {
        GameDisplay display = new TextGameDisplay();

        ChessController gameLogic = new GameLogic(display);

        //play the game
        gameLogic.playGame();
    }
}
