/**
 * Move
 *
 * @author Faiza Salami, 7941056
 * <p>
 * REMARKS: A node containing a piece and its location
 */
public class Node {
    //Fields
    private Piece piece;
    private int row;
    private int col;
    private Node next;

    //Constructors
    public Node(Piece piece, int row, int col) {
        this.piece = piece;
        this.row = row;
        this.col = col;
        next = null;
    }

    //
    //Accessors
    //
    public Node getNext() {
        return next;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setNext(Node data) {
        next = data;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }


}
