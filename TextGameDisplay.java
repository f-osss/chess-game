/**
 * Game Display
 *
 * @author Faiza Salami, 7941056
 * <p>
 * REMARKS: implementing the display of the game
 */

import java.util.Scanner;

public class TextGameDisplay implements GameDisplay {
    private Scanner scanner = new Scanner(System.in);
    private int playAgain = -1;

    /**
     * the method indicates which difficulty of the AI the human player want to play against
     *
     * @param maxDifficulty the maximum difficulty of the AI
     * @return int-the human player choice of difficulty
     */
    public int promptForOpponentDifficulty(int maxDifficulty) {
        int output;

        //keep looping until the human player enters a valid difficulty
        while (true) {
            System.out.println("Please enter the desired opponent difficulty, between 0 and " + maxDifficulty + ", where 0 is easiest opponent and " + maxDifficulty + " is hardest opponent.");
            output = scanner.nextInt();
            if (output >= 0 && output <= maxDifficulty) {
                return output;
            }
        }
    }


    /**
     * ask the human user to input their chosen move
     *
     * @return Move-the move the human user made
     */
    public Move promptForMove() {
        int input = 0;
        Move playerMoves = new Move();

        System.out.println("Please enter the row of the piece you would like to move.  Enter 0 to forfeit game.");
        input = scanner.nextInt();


        if (input == 0) {
            gameOver(0);
            playerMoves = null;
        } else {
            playerMoves.setRow(input - 1);

            System.out.println("Please enter the column of the piece you would like to move.");
            playerMoves.setCol(scanner.nextInt() - 1);

            System.out.println("Please enter the row of the destination.");
            playerMoves.setNewRow(scanner.nextInt() - 1);

            System.out.println("Please enter the column of the destination.");
            playerMoves.setNewCol(scanner.nextInt() - 1);
        }

        return playerMoves;
    }

    /**
     * display the text character of the board
     *
     * @param gameBoard the current game board
     */
    public void displayBoard(Board gameBoard) {
        Piece[][] board = gameBoard.getGameBoard();

        System.out.print("  1 2 3 4 5 6 7 8");

        for (int i = 0; i < board.length; i++) {
            System.out.println();
            System.out.println("------------------");
            System.out.print(i + 1 + "|");
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == null) {
                    System.out.print(' ' + "|");

                } else {
                    System.out.print(board[i][j].getPiece() + "|");
                }
            }
        }
        System.out.println();
        System.out.println("------------------");

    }

    /**
     * summarizes the last move made
     *
     * @param moves the move that's going to be summarized
     * @param gameBoard the current game board
     */
    public void summarizeMove(Move moves, Board gameBoard) {
        Piece piece = gameBoard.getPiece(moves.getNewRow(), moves.getNewCol());


        int row = moves.getRow() + 1;
        int col = moves.getCol() + 1;
        int newRow = moves.getNewRow() + 1;
        int newCol = moves.getNewCol() + 1;

        if (piece != null) {
            System.out.print(piece.name() + " moved from (" + row + "," + col + ") to (" + newRow + "," + newCol + ").");
        }

        if (piece.getCaptured() != null) {
            System.out.println(piece.getCaptured().name() + " captured");
        } else {
            System.out.println("No capture made.");
        }
    }

    /**
     * called when the game is over
     *
     * @param winner indicates which player won, 1 if the human player won and 2 if the AI won
     */
    public void gameOver(int winner) {
        if (winner == 0) {
            System.out.println("You forfeit. Game over!\n" +
                    "Would you like to play again? Please enter 0 for yes or 1 for no.");
            playAgain = scanner.nextInt();
        } else if (winner == 1) {
            System.out.println("You won the game!");
        } else if (winner == 2) {
            System.out.println("You lost! The computer won");
        }
    }

    /**
     * called when the human player wants to play the game again
     *
     * @return boolean-returns true if the player wants to play again and false otherwise
     */
    public boolean promptPlayAgain() {
        boolean output = false;
        if (playAgain == 0) {
            output = true;
        }
        playAgain = -1;
        return output;
    }

    /**
     * called when a pawn piece reaches the end of the board and needs to be promoted
     *
     * @param move the current move made that promoted the pawn
     * @param gameBoard the current game board
     * @param AI the AI being played against
     */
    public void promotion(Move move, Board gameBoard, ChessPlayer AI) {
        Piece newPiece;

        if (gameBoard.getPiece(move.getRow(), move.getCol()).isPawn()) {
            if (gameBoard.getPiece(move.getRow(), move.getCol()).isAIPiece()) {
                newPiece = new Queen('q');
                newPiece.setCaptured(gameBoard.getPiece(move.getNewRow(), move.getNewCol()));
                gameBoard.setPiece(newPiece, move.getRow(), move.getCol());

                //if its playing against an AI2, add the new piece which was promoted to the list and remove the pawn which leaves the board
                if (AI instanceof AI2) {
                    ((AI2) AI).getList().add(newPiece, move.getNewRow(), move.getNewCol());
                    ((AI2) AI).removeFromList(new Pawn('p'), move.getNewRow(), move.getNewCol());
                }

            } else {
                System.out.println("Your pawn is ready to promote.  Please enter the desired type of piece: 0 for Queen, 1 for Bishop, 2 for Rook, 3 for Knight");
                int output = scanner.nextInt();
                if (output == 0) {
                    newPiece = new Queen('Q');
                    newPiece.setCaptured(gameBoard.getPiece(move.getNewRow(), move.getNewCol()));
                    gameBoard.setPiece(newPiece, move.getRow(), move.getCol());
                } else if (output == 1) {
                    newPiece = new Bishop('B');
                    newPiece.setCaptured(gameBoard.getPiece(move.getNewRow(), move.getNewCol()));
                    gameBoard.setPiece(newPiece, move.getRow(), move.getCol());
                } else if (output == 2) {
                    newPiece = new Rook('R');
                    newPiece.setCaptured(gameBoard.getPiece(move.getNewRow(), move.getNewCol()));
                    gameBoard.setPiece(newPiece, move.getRow(), move.getCol());
                } else if (output == 3) {
                    newPiece = new Knight('N');
                    newPiece.setCaptured(gameBoard.getPiece(move.getNewRow(), move.getNewCol()));
                    gameBoard.setPiece(newPiece, move.getRow(), move.getCol());
                }
            }
        }

    }
}
