/**
 * Move
 *
 * @author Faiza Salami, 7941056
 * <p>
 * REMARKS: A class representing a move made by a player
 */
public class Move {
    private int row;
    private int col;
    private int newRow;
    private int newCol;

    //
    //Getters
    //

    public int getRow() {
        return row;
    }

    public int getNewRow() {
        return newRow;
    }

    public int getCol() {
        return col;
    }

    public int getNewCol() {
        return newCol;
    }

    //
    //Setters
    //
    public void setRow(int row) {
        this.row = row;
    }

    public void setNewRow(int newRow) {
        this.newRow = newRow;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setNewCol(int newCol) {
        this.newCol = newCol;
    }


}
