/**
 * Board
 *
 * @author Faiza Salami, 7941056
 * <p>
 * REMARKS: A board class representing the chess board
 */

public class Board {
    private Piece[][] gameBoard;

    public Board() {
        gameBoard = new Piece[8][8];
        createBoard();
    }

    /**
     * create the bard and include the piece on it
     */
    public void createBoard() {
        Piece rook = new Rook('r');
        Piece knight = new Knight('n');
        Piece bishop = new Bishop('b');
        Piece king = new King('k');
        Piece queen = new Queen('q');
        Piece pawn = new Pawn('p');


        gameBoard[0] = new Piece[]{rook, knight, bishop, king, queen, bishop, knight, rook};
        gameBoard[1] = new Piece[]{pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn};


        for (int i = 2; i <= 5; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                gameBoard[i][j] = null;
            }
        }


        rook = new Rook('R');
        knight = new Knight('N');
        bishop = new Bishop('B');
        king = new King('K');
        queen = new Queen('Q');
        pawn = new Pawn('P');

        gameBoard[6] = new Piece[]{pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn};
        gameBoard[7] = new Piece[]{rook, knight, bishop, king, queen, bishop, knight, rook};
    }


    /**
     * get a piece on the board
     *
     * @param row the row of the piece needed
     * @param col the col of the piece needed
     * @return Piece-the piece that needs to be gotten
     */
    public Piece getPiece(int row, int col) {
        return gameBoard[row][col];
    }

    /**
     * set a piece on the board
     *
     * @param row the row of the piece needed to be set
     * @param col the col of the piece needed to be set
     */
    public void setPiece(Piece piece, int row, int col) {
        gameBoard[row][col] = piece;
    }

    /**
     * update the board after a move has been made
     *
     * @param move the move made that needs to be updated on the board
     */
    public void updateBoard(Move move) {
        Piece oldPiece = gameBoard[move.getRow()][move.getCol()];

        if (oldPiece.getCaptured() != null) {
            gameBoard[move.getRow()][move.getCol()] = null;
        } else {
            gameBoard[move.getRow()][move.getCol()] = gameBoard[move.getNewRow()][move.getNewCol()];
        }
        gameBoard[move.getNewRow()][move.getNewCol()] = oldPiece;

    }

    /**
     * get the game board
     *
     * @return Piece[][]-the game board array
     */
    public Piece[][] getGameBoard() {
        return gameBoard;
    }

    /**
     * checks if a king has been captured by any piece on the board
     *
     * @return boolean-returns true if the king has been captured and false otherwise
     */
    public boolean capturedKing() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[i][j] != null) {
                    if (gameBoard[i][j].capturedKing()) {
                        return true;
                    }
                }

            }
        }
        return false;
    }


}
